package com.mtons.mblog.modules.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: Photo
 * @Auther: Jerry
 * @Date: 2020/5/8 17:40
 * @Desctiption: TODO
 * @Version: 1.0
 */
@Data
@TableName(value = "mto_photo")
public class Photo implements Serializable {
    private static final long serialVersionUID = 8970338534448431149L;
    @TableId(type = IdType.AUTO)
    private long id;
    /**
     * 描述
     */
    private String description;
    /**
     * 图片地址
     */
    private String photoUrl;
    /**
     * 创建时间
     */
    @TableField(value = "created", fill = FieldFill.INSERT)
    private Date created;
    /**
     * 更新时间
     */
    @TableField(value = "updated", fill = FieldFill.UPDATE)
    private Date updated;
}
