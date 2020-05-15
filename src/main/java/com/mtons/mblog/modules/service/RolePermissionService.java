package com.mtons.mblog.modules.service;

import com.mtons.mblog.modules.pojo.Permission;
import com.mtons.mblog.modules.pojo.RolePermission;

import java.util.List;

/**
 * @ClassName: RolePermissionNewService
 * @Auther: Jerry
 * @Date: 2020/4/11 12:48
 * @Desctiption: TODO
 * @Version: 1.0
 */
public interface RolePermissionService {
    /**
     * 按角色Id删除权限信息
     *
     * @param id id
     * @return int
     */
    int deleteByRoleId(long id);

    /**
     * 根据角色id查询权限信息
     *
     * @param roleId 角色Id
     * @return {@link List<Permission>}
     */
    List<Permission> findPermissions(long roleId);

    /**
     * 添加角色权限
     *
     * @param rolePermissions 角色权限
     */
    void add(List<RolePermission> rolePermissions);
}
