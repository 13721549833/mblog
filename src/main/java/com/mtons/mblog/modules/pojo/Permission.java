package com.mtons.mblog.modules.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 权限值
 * @author - langhsu on 2018/2/11
 */
@Data
@TableName("shiro_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = -2756705420321737555L;

    @TableId(type = IdType.AUTO)
    private long id;

    private long parentId;

    /**
     * 权限值
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    private int weight;

    private Integer version;

}
