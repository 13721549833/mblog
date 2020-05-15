package com.mtons.mblog.modules.service;

import com.mtons.mblog.modules.vo.OpenOauthVO;
import com.mtons.mblog.modules.vo.UserVO;

/**
 * @ClassName: OpenOauthNewServiceImpl
 * @Auther: Jerry
 * @Date: 2020/4/17 17:26
 * @Desctiption: TODO
 * @Version: 1.0
 */
public interface OpenOauthService {
    /**
     * 通过 oauth_token 查询 user
     *
     * @param oauth_token oauth令牌
     * @return {@link UserVO}
     */
    UserVO getUserByOauthToken(String oauth_token);

    OpenOauthVO getOauthByToken(String oauth_token);

    OpenOauthVO getOauthByOauthUserId(String oauthUserId);

    OpenOauthVO getOauthByUid(long userId);

    boolean checkIsOriginalPassword(long userId);

    void saveOauthToken(OpenOauthVO oauth);
}
