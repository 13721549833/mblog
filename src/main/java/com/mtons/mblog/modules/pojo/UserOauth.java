/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.mtons.mblog.modules.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 第三方开发授权登录
 *
 * @author langhsu on 2015/8/12.
 */
@Data
@TableName("mto_user_oauth")
public class UserOauth {
    @TableId(type = IdType.AUTO)
    private long id;

    /**
     * 系统中的用户ID
     */
    private long userId;

    /**
     * 认证类型：QQ、新浪
     */
    private int oauthType;

    /**
     * 对应第三方用户ID
     */
    private String oauthUserId;

    /**
     * 第三方返回的code
     */
    private String oauthCode;

    /**
     * 访问令牌
     */
    private String accessToken;

    private String expireIn;

    private String refreshToken;

}
