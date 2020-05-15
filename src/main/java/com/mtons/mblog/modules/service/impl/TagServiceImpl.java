package com.mtons.mblog.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtons.mblog.base.lang.Consts;
import com.mtons.mblog.base.utils.BeanMapUtil;
import com.mtons.mblog.modules.mapper.PostTagMapper;
import com.mtons.mblog.modules.mapper.TagMapper;
import com.mtons.mblog.modules.pojo.PostTag;
import com.mtons.mblog.modules.pojo.Tag;
import com.mtons.mblog.modules.service.PostService;
import com.mtons.mblog.modules.service.TagService;
import com.mtons.mblog.modules.vo.PostTagVO;
import com.mtons.mblog.modules.vo.PostVO;
import com.mtons.mblog.modules.vo.TagVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: TagNewServiceImpl
 * @Auther: Jerry
 * @Date: 2020/4/8 11:48
 * @Desctiption: TODO
 * @Version: 1.0
 */
@Service
@Transactional(readOnly = true)
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private PostTagMapper postTagMapper;

    @Autowired
    private PostService postService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchUpdate(String names, long latestPostId) {

        if (StringUtils.isBlank(names.trim())) {
            return;
        }

        String[] ns = names.split(Consts.SEPARATOR);
        for (String n : ns) {
            String name = n.trim();
            if (StringUtils.isBlank(name)) {
                continue;
            }

            Tag po = tagMapper.selectOne(new QueryWrapper<Tag>().eq("name", name));
            if (po != null) {
                //查询文章标签
                QueryWrapper<PostTag> QueryWrapper = new QueryWrapper<>();
                QueryWrapper.eq("post_id", latestPostId).
                        eq("tag_id", po.getId());
                PostTag postTag = postTagMapper.selectOne(QueryWrapper);
                if (null != postTag) {
                    postTagMapper.updateById(postTag);
                    continue;
                }
                po.setPosts(po.getPosts() + 1);
                po.setLatestPostId(latestPostId);
                tagMapper.updateById(po);
            } else {
                po = new Tag();
                po.setName(name);
                po.setPosts(1);
                po.setLatestPostId(latestPostId);
                tagMapper.insert(po);
            }

            PostTag pt = new PostTag();
            pt.setPostId(latestPostId);
            pt.setTagId(po.getId());
            pt.setWeight(System.currentTimeMillis());
            postTagMapper.insert(pt);
        }
    }

    @Override
    public IPage<TagVO> pagingQueryTags(Page page, String order) {
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc(order);
        List<Tag> list = tagMapper.selectPage(page, queryWrapper).getRecords();
        Set<Long> postIds = new HashSet<>();
        List<TagVO> rets = list.stream().map(po -> {
            postIds.add(po.getLatestPostId());
            return BeanMapUtil.copy(po);
        }).collect(Collectors.toList());

        Map<Long, PostVO> posts = postService.findMapByIds(postIds);
        rets.forEach(n -> n.setPost(posts.get(n.getLatestPostId())));
        page.setRecords(rets);
        return page;
    }

    @Override
    public IPage<PostTagVO> pagingQueryPosts(Page page, String order, String name) {
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        Tag tag = tagMapper.selectOne(queryWrapper);
        Assert.notNull(tag, "标签不存在");

        QueryWrapper<PostTag> postTagQueryWrapper = new QueryWrapper<>();
        if (null != tag) {
            postTagQueryWrapper.eq("tag_id", tag.getId());
        }
        postTagQueryWrapper.orderByDesc(order);
        List<PostTag> list = postTagMapper.selectPage(page, postTagQueryWrapper).getRecords();
        Set<Long> postIds = new HashSet<>();
        List<PostTagVO> rets = list.stream().map(po -> {
            postIds.add(po.getPostId());
            return BeanMapUtil.copy(po);
        }).collect(Collectors.toList());

        Map<Long, PostVO> posts = postService.findMapByIds(postIds);
        rets.forEach(n -> n.setPost(posts.get(n.getPostId())));
        page.setRecords(rets);
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deteleByPostId(long postId) {
        QueryWrapper<PostTag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("post_id", postId);
        List<PostTag> postTags = postTagMapper.selectList(queryWrapper);
        Set<Long> tagIds = new HashSet<>();
        postTags.forEach(pt -> tagIds.add(pt.getTagId()));
        if (CollectionUtils.isNotEmpty(tagIds)) {
            tagMapper.decrementPosts(tagIds);
        }
        postTagMapper.delete(queryWrapper);
    }
}
