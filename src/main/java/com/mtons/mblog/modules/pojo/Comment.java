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
 * 评论
 *
 * @author langhsu
 */
@Data
@TableName("mto_comment")
public class Comment {
    @TableId(type = IdType.AUTO)
    private long id;

    /**
     * 父评论ID
     */
    private long pid;

    /**
     * 所属内容ID
     */
    private long postId;

    /**
     * 评论内容
     */
    private String content;

    @TableField(value = "created", fill = FieldFill.INSERT)
    private Date created;

    private long authorId;

    private int status;

}
