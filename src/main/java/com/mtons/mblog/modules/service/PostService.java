package com.mtons.mblog.modules.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtons.mblog.modules.pojo.Post;
import com.mtons.mblog.modules.vo.PostVO;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: PostNewService
 * @Auther: Jerry
 * @Date: 2020/4/4 10:27
 * @Desctiption: TODO
 * @Version: 1.0
 */
public interface PostService {
    /**
     * 分页查询所有文章
     *
     * @param page              页面
     * @param orderBy           排序方式
     * @param order             排序
     * @param channelId         渠道id
     * @param excludeChannelIds 排除渠道id集合
     * @return {@link IPage<PostVO>}
     */
    IPage<PostVO> paging(Page page, String orderBy, String order, int channelId, Set<Integer> excludeChannelIds);

    /**
     * 分页查询所有文章(管理员)
     *
     * @param page      页面
     * @param channelId 渠道id
     * @param title     标题
     * @return {@link IPage<PostVO>}
     */
    IPage<PostVO> paging4Admin(Page page, int channelId, String title);

    /**
     * 查询个人发布文章
     *
     * @param page   页面
     * @param userId 用户Id
     * @return {@link IPage<PostVO>}
     */
    IPage<PostVO> pagingByAuthorId(Page page, long userId);

    /**
     * 查询最近更新 - 按发布时间排序
     *
     * @param maxResults 最大的结果
     * @return {@link List<PostVO>}
     */
    List<PostVO> findLatestPosts(int maxResults);

    /**
     * 查询热门文章 - 按浏览次数排序
     *
     * @param maxResults 最大的结果
     * @return {@link List<PostVO>}
     */
    List<PostVO> findHottestPosts(int maxResults);

    /**
     * 根据Ids查询
     *
     * @param ids id
     * @return {@link Map<Long, PostVO>}
     */
    Map<Long, PostVO> findMapByIds(Set<Long> ids);

    /**
     * 发布文章
     *
     * @param post 文章
     * @return long
     */
    long post(PostVO post);

    /**
     * 文章详情
     *
     * @param id id
     * @return {@link PostVO}
     */
    PostVO get(long id);

    /**
     * 通过Id获取文章
     *
     * @param id id
     * @return {@link Post}
     */
    Post getPostById(long id);

    /**
     * 更新文章方法
     *
     * @param postVO
     */
    void update(PostVO postVO);

    /**
     * 推荐/精华
     *
     * @param id       id
     * @param featured 0: 取消, 1: 加精
     */
    void updateFeatured(long id, int featured);

    /**
     * 置顶
     *
     * @param id       id
     * @param weighted 0: 取消, 1: 置顶
     */
    void updateWeight(long id, int weighted);

    /**
     * 带作者验证的删除 - 验证是否属于自己的文章
     *
     * @param id       id
     * @param authorId 作者Id
     */
    void delete(long id, long authorId);

    /**
     * 批量删除文章, 且刷新缓存
     *
     * @param ids id
     */
    void delete(Collection<Long> ids);

    /**
     * 自增浏览数
     *
     * @param id id
     */
    void identityViews(long id);

    /**
     * 自增评论数
     *
     * @param id id
     */
    void identityComments(long id);

    /**
     * 喜欢文章
     *
     * @param userId 用户Id
     * @param postId 文章Id
     */
    void favor(long userId, long postId);

    /**
     * 取消喜欢文章
     *
     * @param userId 用户Id
     * @param postId 文章Id
     */
    void unfavor(long userId, long postId);

    /**
     * 统计文章数
     *
     * @return long
     */
    long count();
}
