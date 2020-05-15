package com.mtons.mblog.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtons.mblog.base.utils.BeanMapUtil;
import com.mtons.mblog.modules.mapper.CommentMapper;
import com.mtons.mblog.modules.pojo.Comment;
import com.mtons.mblog.modules.service.CommentService;
import com.mtons.mblog.modules.service.PostService;
import com.mtons.mblog.modules.service.UserEventService;
import com.mtons.mblog.modules.service.UserService;
import com.mtons.mblog.modules.vo.CommentVO;
import com.mtons.mblog.modules.vo.PostVO;
import com.mtons.mblog.modules.vo.UserVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

/**
 * @ClassName: CommentNewServiceImpl
 * @Auther: Jerry
 * @Date: 2020/4/9 14:42
 * @Desctiption: TODO
 * @Version: 1.0
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private UserEventService userEventService;

    @Override
    public IPage<CommentVO> pagingByAuthorId(Page page, long authorId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_id", authorId);
        List<Comment> records = commentMapper.selectPage(page, queryWrapper).getRecords();

        List<CommentVO> rets = new ArrayList<>();
        Set<Long> parentIds = new HashSet<>();
        Set<Long> uids = new HashSet<>();
        Set<Long> postIds = new HashSet<>();

        records.forEach(po -> {
            CommentVO c = BeanMapUtil.copy(po);
            if (c.getPid() > 0) {
                parentIds.add(c.getPid());
            }
            uids.add(c.getAuthorId());
            postIds.add(c.getPostId());
            rets.add(c);
        });

        // 加载父节点
        buildParent(rets, parentIds);

        buildUsers(rets, uids);
        buildPosts(rets, postIds);

        page.setRecords(rets);
        return page;
    }

    @Override
    public IPage<CommentVO> paging4Admin(Page page) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created");
        IPage<Comment> iPage = commentMapper.selectPage(page, queryWrapper);
        List<CommentVO> rets = new ArrayList<>();
        HashSet<Long> uids = new HashSet<>();
        iPage.getRecords().forEach(po ->{
            uids.add(po.getAuthorId());
            rets.add(BeanMapUtil.copy(po));
        });
        buildUsers(rets, uids);
        page.setRecords(rets);
        return page;
    }

    @Override
    public IPage<CommentVO> paging4PostId(Page page, long postId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created");
        queryWrapper.eq("post_id", postId);
        IPage<Comment> iPage = commentMapper.selectPage(page, queryWrapper);
        List<CommentVO> rets = new ArrayList<>();
        Set<Long> parentIds = new HashSet<>();
        Set<Long> uids = new HashSet<>();
        iPage.getRecords().forEach(po -> {
            CommentVO c = BeanMapUtil.copy(po);
            if (c.getPid() > 0) {
                parentIds.add(c.getPid());
            }
            uids.add(c.getAuthorId());
            rets.add(c);
        });

		// 加载父节点
		buildParent(rets, parentIds);

		buildUsers(rets, uids);

        page.setRecords(rets);
        return page;
    }

    private void buildPosts(Collection<CommentVO> rets, Set<Long> postIds) {
        Map<Long, PostVO> postMap = postService.findMapByIds(postIds);
        rets.forEach(p -> p.setPost(postMap.get(p.getPostId())));
    }

    private void buildUsers(Collection<CommentVO> rets, Set<Long> uids) {
        Map<Long, UserVO> userMap = userService.findMapByIds(uids);
        rets.forEach(p -> p.setAuthor(userMap.get(p.getAuthorId())));
    }

    private void buildParent(Collection<CommentVO> rets, Set<Long> parentIds) {
        if (CollectionUtils.isNotEmpty(parentIds)) {
            Map<Long, CommentVO> pm = findByIds(parentIds);
            rets.forEach(c -> {
                if (c.getPid() > 0) {
                    c.setParent(pm.get(c.getPid()));
                }
            });
        }
    }

    @Override
    public Map<Long, CommentVO> findByIds(Set<Long> parentIds) {
        List<Comment> list = commentMapper.selectBatchIds(parentIds);
        Map<Long, CommentVO> ret = new HashMap<>();
        Set<Long> uids = new HashSet<>();
        list.forEach(po -> {
            uids.add(po.getAuthorId());
            ret.put(po.getId(), BeanMapUtil.copy(po));
        });

        buildUsers(ret.values(), uids);
        return ret;
    }

    @Override
    public List<CommentVO> findLatestComments(int size) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        IPage<Comment> iPage = commentMapper.selectPage(new Page<>(1, size), queryWrapper);
        List<Comment> list = iPage.getRecords();
		List<CommentVO> rets = new ArrayList<>();

		HashSet<Long> uids= new HashSet<>();

		list.forEach(po -> {
			uids.add(po.getAuthorId());
			rets.add(BeanMapUtil.copy(po));
		});

		buildUsers(rets, uids);
		return rets;
    }

    @Override
    public int countByAuthorIdAndPostId(long authorId, long postId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_id", authorId).eq("post_id", postId);
        return commentMapper.selectCount(queryWrapper);
    }

    @Override
    public Comment findById(long id) {
        return commentMapper.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public long post(CommentVO commentVO) throws Exception{
        Comment po = new Comment();
        BeanUtils.copyProperties(commentVO, po);
        commentMapper.insert(po);

		userEventService.identityComment(commentVO.getAuthorId(), true);
        return po.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(List<Long> ids) {
        List<Comment> list = commentMapper.selectBatchIds(ids);
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(po -> {
                userEventService.identityComment(po.getAuthorId(), false);
            });
        }
        commentMapper.deleteBatchIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(long id, long authorId) {
        Comment comment = commentMapper.selectById(id);
        Assert.isTrue(comment.getAuthorId() == authorId, "认证失败");
        commentMapper.deleteById(id);
        userEventService.identityComment(authorId, false);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByPostId(long postId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("post_id", postId);
        List<Comment> list = commentMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(list)) {
            Set<Long> userIds = new HashSet<>();
            list.forEach(u -> userIds.add(u.getAuthorId()));
            userEventService.identityComment(userIds, false);
        }
        commentMapper.delete(queryWrapper);
    }

    @Override
    public long count() {
        return commentMapper.selectCount(null);
    }
}
