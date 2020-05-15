/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.mtons.mblog.modules.vo;

import lombok.Data;

/**
 * 第三方开发授权登录
 * @author langhsu
 */
@Data
public class OpenOauthVO {
    private long id;

    private long userId; // 系统中的用户ID

    private int oauthType; // 认证类型：QQ、新浪

    private String oauthUserId; // 对应第三方用户ID

    private String oauthCode; // 第三方返回的code

    private String accessToken; // 访问令牌

    private String expireIn;

    private String refreshToken;

    // extends
    private String username;
    private String nickname;
    private String email;
    private String avatar;

}
