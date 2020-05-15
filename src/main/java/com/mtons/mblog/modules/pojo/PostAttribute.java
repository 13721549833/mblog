package com.mtons.mblog.modules.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author langhsu
 * @date 2015/10/25
 */
@Data
@TableName("mto_post_attribute")
public class PostAttribute implements Serializable {
	private static final long serialVersionUID = 7829351358884064647L;

    @TableId(type = IdType.AUTO)
    private long id;

    private long postId;

	private String editor;

    /**
     * 内容
     */
    private String content; // 内容

}
