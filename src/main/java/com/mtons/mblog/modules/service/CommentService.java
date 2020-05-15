package com.mtons.mblog.modules.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtons.mblog.modules.pojo.Comment;
import com.mtons.mblog.modules.vo.CommentVO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: CommentNewService
 * @Auther: Jerry
 * @Date: 2020/4/9 14:42
 * @Desctiption: TODO
 * @Version: 1.0
 */
public interface CommentService {

    /**
     * 分页获取用户评论数
     *
     * @param page     页面
     * @param authorId 作者Id
     * @return {@link IPage<CommentVO>}
     */
    IPage<CommentVO> pagingByAuthorId(Page page, long authorId);

    /**
     * 查询管理员评论列表
     *
     * @param page 页面
     * @return {@link IPage<CommentVO>}
     */
    IPage<CommentVO> paging4Admin(Page page);

    /**
     * 根据文章查询评论列表
     *
     * @param page   页面
     * @param postId post Id
     * @return {@link IPage<CommentVO>}
     */
    IPage<CommentVO> paging4PostId(Page page, long postId);

    /**
     * 获取用户评论信息
     *
     * @param parentIds 父id
     * @return {@link Map<Long, CommentVO>}
     */
    Map<Long, CommentVO> findByIds(Set<Long> parentIds);

    /**
     * 找到最新的评论
     *
     * @param size 大小
     * @return {@link List<CommentVO>}
     */
    List<CommentVO> findLatestComments(int size);

    /**
     * 根据作者id和文章id统计数评论数量
     *
     * @param authorId 作者Id
     * @param postId   post Id
     * @return int
     */
    int countByAuthorIdAndPostId(long authorId, long postId);

    /**
     * 根据id获取评论信息
     *
     * @param id id
     * @return {@link Comment}
     */
    Comment findById(long id);

    /**
     * 发表评论
     *
     * @param commentVO 评论签证官
     * @return long
     */
    long post(CommentVO commentVO) throws Exception;

    /**
     * 删除批处理
     *
     * @param ids id
     */
    void deleteBatch(List<Long> ids);

    /**
     * 删除评论
     *
     * @param id       id
     * @param authorId 作者Id
     */
    void delete(long id, long authorId);

    /**
     * 根据文章id删除
     *
     * @param postId post Id
     */
    void deleteByPostId(long postId);

    /**
     * 统计评论数
     *
     * @return long
     */
    long count();

}
