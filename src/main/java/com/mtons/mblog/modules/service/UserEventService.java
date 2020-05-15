package com.mtons.mblog.modules.service;

import java.util.Set;

/**
 * @ClassName: UserEventNewService
 * @Auther: Jerry
 * @Date: 2020/4/17 16:20
 * @Desctiption: TODO
 * @Version: 1.0
 */
public interface UserEventService {

    /**
     * 自增发布文章数
     * @param userId
     */
    void identityPost(Long userId, boolean plus);

    /**
     * 自增评论数
     * @param userId
     */
    void identityComment(Long userId, boolean plus);

    /**
     * 批量自动评论数
     * @param userIds
     * @param plus
     */
    void identityComment(Set<Long> userIds, boolean plus);
}
