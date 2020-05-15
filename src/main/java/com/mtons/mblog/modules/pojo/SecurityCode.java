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

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 验证码
 * @author langhsu on 2015/8/14.
 */
@Data
@TableName("mto_security_code")
public class SecurityCode {
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField("key_")
    private String key;

    /**
     * 验证码
     */
    private String code;

    /**
     * 目标：邮箱
     */
    private String target;

    /**
     * 验证类型：注册验证、找回密码验证
     */
    private int type;

    /**
     * 过期时间
     */
    private Date expired;

    /**
     * 创建时间
     */
    @TableField(value = "created", fill = FieldFill.INSERT)
    private Date created;

    /**
     * 状态：正常、关闭
     */
    private int status;

}
