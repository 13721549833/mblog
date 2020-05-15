package com.mtons.mblog.modules.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 通知
 * @author langhsu on 2015/8/31.
 */
@Data
@TableName("mto_message")
public class Message {
    @TableId(type = IdType.AUTO)
    private long id;

    /**
     * 所属用户Id
     */
    private long userId;

    /**
     * 消息来源用户Id
     */
    private long fromId;

    /**
     * 事件类型 {@link com.mtons.mblog.base.lang.Consts#MESSAGE_EVENT_COMMENT}
     */
    private int event; // 事件

    /**
     * 关联文章ID
     */
    private long postId;

    @TableField(value = "created", fill = FieldFill.INSERT)
    private Date created;

    /**
     * 阅读状态 {@link com.mtons.mblog.base.lang.Consts#UNREAD}
     */
    private int status;

}
