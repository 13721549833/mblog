package com.mtons.mblog.modules.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtons.mblog.modules.pojo.Permission;
import com.mtons.mblog.modules.pojo.Role;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: RoleNewService
 * @Auther: Jerry
 * @Date: 2020/4/11 12:03
 * @Desctiption: TODO
 * @Version: 1.0
 */
public interface RoleService {

    /**
     * 分页查询角色
     *
     * @param page 页面
     * @param name 的名字
     * @return {@link IPage<Role>}
     */
    IPage<Role> paging(Page page, String name);

    /**
     * 查询所有活动角色
     *
     * @return {@link List<Role>}
     */
    List<Role> list();

    /**
     * 角色信息
     *
     * @param ids id
     * @return {@link Map<Long, Role>}
     */
    Map<Long, Role> findByIds(Set<Long> ids);

    /**
     * 根据角色ID获得角色信息
     *
     * @param id id
     * @return {@link Role}
     */
    Role get(long id);

    /**
     * 保存角色信息。如果角色存在，则更新其信息，如果角色不存在，则添加新角色
     *
     * @param r           r
     * @param permissions 权限
     */
    void update(Role r, Set<Permission> permissions);

    /**
     * 删除角色，已被授权的角色不允许删除
     *
     * @param id id
     * @return boolean
     */
    boolean delete(long id);

    /**
     * 激活、停用角色
     *
     * @param id     id
     * @param active 活跃的
     */
    void activate(long id, boolean active);
}
