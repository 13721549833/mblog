package com.mtons.mblog.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtons.mblog.base.utils.BeanMapUtil;
import com.mtons.mblog.base.utils.MD5;
import com.mtons.mblog.modules.mapper.UserMapper;
import com.mtons.mblog.modules.mapper.UserOauthMapper;
import com.mtons.mblog.modules.pojo.User;
import com.mtons.mblog.modules.pojo.UserOauth;
import com.mtons.mblog.modules.service.OpenOauthService;
import com.mtons.mblog.modules.vo.OpenOauthVO;
import com.mtons.mblog.modules.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: OpenOauthNewServiceImpl
 * @Auther: Jerry
 * @Date: 2020/4/17 17:29
 * @Desctiption: TODO
 * @Version: 1.0
 */
@Service
@Transactional(readOnly = true)
public class OpenOauthServiceImpl implements OpenOauthService {

    @Autowired
    private UserOauthMapper userOauthMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserVO getUserByOauthToken(String oauth_token) {
        UserOauth thirdToken = getUserOauth(oauth_token, null, null);
        User po = userMapper.selectById(thirdToken.getId());
        return BeanMapUtil.copy(po);
    }

    @Override
    public OpenOauthVO getOauthByToken(String oauth_token) {
        UserOauth po = getUserOauth(oauth_token, null, null);

        OpenOauthVO vo = null;
        if (po != null) {
            vo = new OpenOauthVO();
            BeanUtils.copyProperties(po, vo);
        }
        return vo;
    }

    /**
     * 获取用户Oauth
     *
     * @param oauthToken  oauth令牌
     * @param userId      用户Id
     * @param oauthUserId oauth用户Id
     * @return {@link UserOauth}
     */
    private UserOauth getUserOauth(String oauthToken, Long userId, String oauthUserId){
        QueryWrapper<UserOauth> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(oauthToken)) {
            queryWrapper.eq("access_token", oauthToken);
        }
        if (userId != null) {
            queryWrapper.eq("user_id", userId);
        }
        if (StringUtils.isNotBlank(oauthUserId)) {
            queryWrapper.eq("oauth_user_id", oauthUserId);
        }
        return userOauthMapper.selectOne(queryWrapper);
    }

    @Override
    public OpenOauthVO getOauthByOauthUserId(String oauthUserId) {
        UserOauth po = getUserOauth(null, null, oauthUserId);

        OpenOauthVO vo = null;
        if (po != null) {
            vo = new OpenOauthVO();
            BeanUtils.copyProperties(po, vo);
        }
        return vo;
    }

    @Override
    public OpenOauthVO getOauthByUid(long userId) {
        UserOauth po = getUserOauth(null, userId, null);

        OpenOauthVO vo = null;
        if (po != null) {
            vo = new OpenOauthVO();
            BeanUtils.copyProperties(po, vo);
        }
        return vo;
    }

    @Override
    public boolean checkIsOriginalPassword(long userId) {
        UserOauth po = getUserOauth(null, userId, null);
        if (po != null) {
            User user = userMapper.selectById(userId);

            String pwd = MD5.md5(po.getAccessToken());
            // 判断用户密码 和 登录状态
            if (user != null && pwd.equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOauthToken(OpenOauthVO oauth) {
        UserOauth po = new UserOauth();
        BeanUtils.copyProperties(oauth, po);
        userOauthMapper.insert(po);
    }
}
