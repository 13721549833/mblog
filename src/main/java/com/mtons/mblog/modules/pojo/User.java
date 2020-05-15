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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 *
 * @author langhsu
 *
 */
@Data
@TableName("mto_user")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

	private static final long serialVersionUID = -9023384476157042761L;

	@TableId(type = IdType.AUTO)
	private long id;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码(密文)
	 */
	private String password;

	/**
	 * 头像
	 */
	private String avatar;

	/**
	 * 昵称
	 */
	private String name;

	/**
	 * 性别
	 */
	private int gender;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 文章数
	 */
	private int posts;

	/**
	 * 发布评论数
	 */
	private int comments;

	/**
	 * 注册时间
	 */
	@TableField(value = "created", fill = FieldFill.INSERT)
	private Date created;

	/**
	 * 修改时间
	 */
	@TableField(value = "updated", fill = FieldFill.UPDATE)
	private Date updated;

	/**
	 * 最后登录时间
	 */
	private Date lastLogin;

	/**
	 * 个性签名
	 */
	private String signature;

	/**
	 * 用户状态
	 */
	private int status;

}
