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
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 系统配置
 * @author langhsu
 *
 */
@Data
@TableName("mto_options")
public class Options {
	@TableId(type = IdType.AUTO)
	private long id;

	/**
	 * 类型(冗余)
	 */
	private int type;

	/**
	 * 标识
	 */
	@TableField("key_")
	private String key;

	/**
	 * 值
	 */
	private String value;
	
}
