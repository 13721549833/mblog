package com.mtons.mblog.modules.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author : langhsu
 */
@Data
@TableName("mto_tag")
public class Tag {
    @TableId(type = IdType.AUTO)
    private long id;

    /**
     * 标签名称
     */
    private String name;

    /**
     * 预览图
     */
    private String thumbnail;

    /**
     * 描述
     */
    private String description;

    /**
     * 最后发表的文章Id
     */
    private long latestPostId;

    @TableField(value = "created", fill = FieldFill.INSERT)
    private Date created;

    @TableField(value = "created", fill = FieldFill.UPDATE)
    private Date updated;

    /**
     * 标签下的文章数
     */
    private int posts;

}
