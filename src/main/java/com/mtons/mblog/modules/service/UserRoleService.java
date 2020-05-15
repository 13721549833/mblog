package com.mtons.mblog.modules.service;

import com.mtons.mblog.modules.pojo.Role;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: UserRoleNewService
 * @Auther: Jerry
 * @Date: 2020/4/11 11:49
 * @Desctiption: TODO
 * @Version: 1.0
 */
public interface UserRoleService {
    /**
     * 查询用户已有的角色Id
     *
     * @param userId 用户Id
     * @return {@link List<Long>}
     */
    List<Long> listRoleIds(long userId);

    /**
     * 查询用户已有的角色 和 权限
     *
     * @param userId 用户Id
     * @return {@link List<Role>}
     */
    List<Role> listRoles(long userId);

    /**
     * 根据用户id集合查询角色信息
     * @param userIds
     * @return {@link Map<Long, List<Role>>}
     */
    Map<Long, List<Role>> findMapByUserIds(List<Long> userIds);

    /**
     * 修改用户角色
     *
     * @param userId  用户Id
     * @param roleIds 角色id
     */
    void updateRole(long userId, Set<Long> roleIds);
}
