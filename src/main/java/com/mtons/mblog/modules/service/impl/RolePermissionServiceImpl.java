package com.mtons.mblog.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtons.mblog.modules.mapper.PermissionMapper;
import com.mtons.mblog.modules.mapper.RolePermissionMapper;
import com.mtons.mblog.modules.pojo.Permission;
import com.mtons.mblog.modules.pojo.RolePermission;
import com.mtons.mblog.modules.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: RolePermissionNewServiceImpl
 * @Auther: Jerry
 * @Date: 2020/4/11 12:49
 * @Desctiption: TODO
 * @Version: 1.0
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByRoleId(long id) {
        QueryWrapper<RolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", id);
        return rolePermissionMapper.delete(queryWrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Permission> findPermissions(long roleId) {
        QueryWrapper<RolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        List<RolePermission> rps = rolePermissionMapper.selectList(queryWrapper);

        List<Permission> rets = null;
        if (rps != null && rps.size() > 0) {
            Set<Long> pids = new HashSet<>();
            rps.forEach(rp -> pids.add(rp.getPermissionId()));
            rets = permissionMapper.selectBatchIds(pids);
        }
        return rets;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(List<RolePermission> rolePermissions) {
        rolePermissionMapper.insertBatch(rolePermissions);
    }
}
