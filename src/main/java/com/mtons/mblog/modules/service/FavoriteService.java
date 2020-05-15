package com.mtons.mblog.modules.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtons.mblog.modules.vo.FavoriteVO;

/**
 * @ClassName: FavoriteNewService
 * @Auther: Jerry
 * @Date: 2020/4/17 16:42
 * @Desctiption: TODO
 * @Version: 1.0
 */
public interface FavoriteService {
    /**
     * 查询用户收藏记录
     *
     * @param page   页面
     * @param userId 用户Id
     * @return {@link IPage<FavoriteVO>}
     */
    IPage<FavoriteVO> pagingByUserId(Page page, long userId);

    /**
     * 添加
     *
     * @param userId 用户Id
     * @param postId post Id
     */
    void add(long userId, long postId);

    /**
     * 删除
     *
     * @param userId 用户Id
     * @param postId post Id
     */
    void delete(long userId, long postId);

    /**
     * 根据文章删除收藏记录
     *
     * @param postId post Id
     */
    void deleteByPostId(long postId);
}
