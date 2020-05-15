package com.mtons.mblog.modules.service.impl;

import com.mtons.mblog.base.lang.Consts;
import com.mtons.mblog.modules.mapper.UserMapper;
import com.mtons.mblog.modules.service.UserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Set;

/**
 * @ClassName: UserEventNewServiceImpl
 * @Auther: Jerry
 * @Date: 2020/4/17 16:21
 * @Desctiption: TODO
 * @Version: 1.0
 */
@Service
@Transactional
public class UserEventServiceImpl implements UserEventService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void identityPost(Long userId, boolean plus) {
        userMapper.updatePosts(userId, (plus) ? Consts.IDENTITY_STEP : Consts.DECREASE_STEP);
    }

    @Override
    public void identityComment(Long userId, boolean plus) {
        userMapper.updateComments(Collections.singleton(userId), (plus) ? Consts.IDENTITY_STEP : Consts.DECREASE_STEP);
    }

    @Override
    public void identityComment(Set<Long> userIds, boolean plus) {
        userMapper.updateComments(userIds, (plus) ? Consts.IDENTITY_STEP : Consts.DECREASE_STEP);
    }
}
