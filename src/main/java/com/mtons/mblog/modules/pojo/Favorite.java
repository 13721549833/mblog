package com.mtons.mblog.modules.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 喜欢/收藏
 * @author langhsu on 2015/8/31.
 */
@Data
@TableName("mto_favorite")
public class Favorite {
    @TableId(type = IdType.AUTO)
    private long id;

    /**
     * 所属用户
     */
    private long userId;

    /**
     * 内容ID
     */
    private long postId;

    @TableField(value = "created", fill = FieldFill.INSERT)
    private Date created;

}
