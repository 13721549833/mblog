package com.mtons.mblog.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtons.mblog.base.utils.BeanMapUtil;
import com.mtons.mblog.modules.mapper.FavoriteMapper;
import com.mtons.mblog.modules.pojo.Favorite;
import com.mtons.mblog.modules.service.FavoriteService;
import com.mtons.mblog.modules.service.PostService;
import com.mtons.mblog.modules.vo.FavoriteVO;
import com.mtons.mblog.modules.vo.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

/**
 * @ClassName: FavoriteNewServiceImpl
 * @Auther: Jerry
 * @Date: 2020/4/17 16:45
 * @Desctiption: TODO
 * @Version: 1.0
 */
@Service
@Transactional(readOnly = true)
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Autowired
    private PostService postService;

    @Override
    public IPage<FavoriteVO> pagingByUserId(Page page, long userId) {
        IPage<Favorite> iPage = favoriteMapper.selectPage(page, null);
        List<FavoriteVO> rets = new ArrayList<>();
        Set<Long> postIds = new HashSet<>();
        for (Favorite po : iPage.getRecords()) {
            rets.add(BeanMapUtil.copy(po));
            postIds.add(po.getPostId());
        }

        if (postIds.size() > 0) {
            Map<Long, PostVO> posts = postService.findMapByIds(postIds);
            for (FavoriteVO ret : rets) {
                PostVO p = posts.get(ret.getPostId());
                ret.setPost(p);
            }
        }
        page.setRecords(rets);
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(long userId, long postId) {
        Favorite po = getFavorite(userId, postId);
        Assert.isNull(po, "您已经收藏过此文章");
        // 如果没有喜欢过, 则添加记录
        po = new Favorite();
        po.setUserId(userId);
        po.setPostId(postId);
        favoriteMapper.insert(po);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(long userId, long postId) {
        Favorite po = getFavorite(userId, postId);
        Assert.notNull(po, "还没有喜欢过此文章");
        favoriteMapper.deleteById(po.getId());
    }

    private Favorite getFavorite(long userId, long postId){
        QueryWrapper<Favorite> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).eq("post_id", postId);
        return favoriteMapper.selectOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByPostId(long postId) {
        QueryWrapper<Favorite> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("post_id", postId);
        favoriteMapper.delete(queryWrapper);
    }
}
