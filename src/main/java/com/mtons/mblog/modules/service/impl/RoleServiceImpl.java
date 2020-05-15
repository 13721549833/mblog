package com.mtons.mblog.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtons.mblog.modules.mapper.RoleMapper;
import com.mtons.mblog.modules.mapper.UserRoleMapper;
import com.mtons.mblog.modules.pojo.Permission;
import com.mtons.mblog.modules.pojo.Role;
import com.mtons.mblog.modules.pojo.RolePermission;
import com.mtons.mblog.modules.pojo.UserRole;
import com.mtons.mblog.modules.service.RoleService;
import com.mtons.mblog.modules.service.RolePermissionService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

/**
 * @ClassName: RoleNewServiceImpl
 * @Auther: Jerry
 * @Date: 2020/4/11 12:03
 * @Desctiption: TODO
 * @Version: 1.0
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    public IPage<Role> paging(Page page, String name) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like("name", name);
        }
        queryWrapper.orderByDesc("id");
        return roleMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<Role> list() {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", Role.STATUS_NORMAL);
        return roleMapper.selectList(queryWrapper);
    }

    @Override
    public Map<Long, Role> findByIds(Set<Long> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            List<Role> list = roleMapper.selectBatchIds(ids);
            Map<Long, Role> ret = new LinkedHashMap<>();
            list.forEach(po -> {
                Role vo = toVO(po);
                ret.put(vo.getId(), vo);
            });
            return ret;
        }
        return Collections.emptyMap();
    }

    @Override
    public Role get(long id) {
        return toVO(roleMapper.selectById(id));
    }

    @Override
    public void update(Role r, Set<Permission> permissions) {
        Role role = roleMapper.selectById(r.getId());
        if (role == null) {
            role = new Role();
            role.setName(r.getName());
            role.setDescription(r.getDescription());
            role.setStatus(r.getStatus());
            roleMapper.insert(role);
        } else {
            role.setName(r.getName());
            role.setDescription(r.getDescription());
            role.setStatus(r.getStatus());
            roleMapper.updateById(role);
        }

        rolePermissionService.deleteByRoleId(role.getId());

        if (permissions != null && permissions.size() > 0) {
            List<RolePermission> list = new ArrayList<>();
            long roleId = role.getId();
            permissions.forEach(p -> {
                RolePermission rp = new RolePermission();
                rp.setRoleId(roleId);
                rp.setPermissionId(p.getId());
                list.add(rp);
            });
            rolePermissionService.add(list);
        }
    }

    @Override
    public boolean delete(long id) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", id);
        List<UserRole> urs = userRoleMapper.selectList(queryWrapper);
        Assert.state(urs == null || urs.size() == 0, "该角色已经被使用,不能被删除");
        roleMapper.deleteById(id);
        rolePermissionService.deleteByRoleId(id);
        return true;
    }

    @Override
    public void activate(long id, boolean active) {
        Role role = roleMapper.selectById(id);
        role.setStatus(active ? Role.STATUS_NORMAL : Role.STATUS_CLOSED);
    }

    private Role toVO(Role po) {
        Role r = new Role();
        r.setId(po.getId());
        r.setName(po.getName());
        r.setDescription(po.getDescription());
        r.setStatus(po.getStatus());
        r.setPermissions(rolePermissionService.findPermissions(r.getId()));
        return r;
    }
}
