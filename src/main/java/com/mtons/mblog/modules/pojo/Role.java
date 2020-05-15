package com.mtons.mblog.modules.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: Role
 * @Auther: Jerry
 * @Date: 2020/4/4 10:08
 * @Desctiption: TODO
 * @Version: 1.0
 */
@Data
@TableName("shiro_role")
public class Role implements Serializable {

    private static final long serialVersionUID = -6939937505608877542L;
    public static int STATUS_NORMAL = 0;
    public static int STATUS_CLOSED = 1;

    public static String ROLE_ADMIN = "admin";

    public static long ADMIN_ID = 1;

    @TableId(type = IdType.AUTO)
    private long id;

    private String name;

    private String description;

    private int status;

    @TableField(exist = false)
    private List<Permission> permissions;
}
