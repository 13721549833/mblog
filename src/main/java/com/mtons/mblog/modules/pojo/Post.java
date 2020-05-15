package com.mtons.mblog.modules.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 内容表
 * @author langhsu
 *
 */
@Data
@TableName(value = "mto_post")
public class Post implements Serializable {

	private static final long serialVersionUID = -5303129826937436203L;
	@TableId(type = IdType.AUTO)
	private long id;

	/**
	 * 分组/模块ID
	 */
	private int channelId;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 摘要
	 */
	private String summary;

	/**
	 * 预览图
	 */
	private String thumbnail;

	/**
	 * 标签, 多个逗号隔开
	 */
	private String tags;

	/**
	 * 作者Id
	 */
	private long authorId;

	@TableField(value = "created", fill = FieldFill.INSERT)
	private Date created;

	/**
	 * 收藏数
	 */
	private int favors;

	/**
	 * 评论数
	 */
	private int comments;

	/**
	 * 阅读数
	 */
	private int views;

	/**
	 * 文章状态
	 */
	private int status;

	/**
	 * 推荐状态
	 */
	private int featured;

	/**
	 * 排序值
	 */
	private int weight;

}