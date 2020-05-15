package com.mtons.mblog.modules.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色-权限值
 * @author - langhsu on 2018/2/11
 */
@Data
@TableName("shiro_role_permission")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = -3971912193013097317L;

    @TableId(type = IdType.AUTO)
    private long id;

    private long roleId;

    private long permissionId;

}
