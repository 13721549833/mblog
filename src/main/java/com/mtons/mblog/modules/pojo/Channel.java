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

import java.io.Serializable;

/**
 * 模块/内容分组
 * @author langhsu
 *
 */
@Data
@TableName("mto_channel")
public class Channel implements Serializable {

	private static final long serialVersionUID = -1023691122715228897L;

	@TableId(type = IdType.AUTO)
	private int id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 唯一关键字
	 */
	@TableField("key_")
	private String key;

	/**
	 * 预览图
	 */
	private String thumbnail;

	/**
	 * 状态 0 显示, 1隐藏
	 */
	private int status;

	/**
	 * 排序值
	 */
	private int weight;

}
