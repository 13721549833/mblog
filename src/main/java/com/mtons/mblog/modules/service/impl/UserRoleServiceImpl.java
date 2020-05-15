package com.mtons.mblog.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mtons.mblog.modules.mapper.UserRoleMapper;
import com.mtons.mblog.modules.pojo.Role;
import com.mtons.mblog.modules.pojo.UserRole;
import com.mtons.mblog.modules.service.RoleService;
import com.mtons.mblog.modules.service.UserRoleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName: UserRoleNewServiceImpl
 * @Auther: Jerry
 * @Date: 2020/4/11 11:53
 * @Desctiption: TODO
 * @Version: 1.0
 */
@Service
@Transactional(readOnly = true)
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleService roleService;

    @Override
    public List<Role> listRoles(long userId) {
        List<Long> list = listRoleIds(userId);
        return new ArrayList<>(roleService.findByIds(new HashSet<>(list)).values());
    }

    @Override
    public List<Long> listRoleIds(long userId) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<UserRole> list = userRoleMapper.selectList(queryWrapper);

        List<Long> roleIds = new ArrayList<>();
        if (null != list) {
            list.forEach(po -> roleIds.add(po.getRoleId()));
        }
        return roleIds;
    }

    @Override
    public Map<Long, List<Role>> findMapByUserIds(List<Long> userIds) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        if (CollectionUtils.isNotEmpty(userIds)) {
            queryWrapper.in("user_id", userIds);
        }
        List<UserRole> list = userRoleMapper.selectList(queryWrapper);
        Map<Long, Set<Long>> map = new HashMap<>();

        list.forEach(po -> {
            Set<Long> roleIds = map.computeIfAbsent(po.getUserId(), k -> new HashSet<>());
            roleIds.add(po.getRoleId());
        });

        Map<Long, List<Role>> ret = new HashMap<>();
        map.forEach((k, v) -> {
            ret.put(k, new ArrayList<>(roleService.findByIds(v).values()));
        });
        return ret;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(long userId, Set<Long> roleIds) {
        //查询条件
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        // 判断是否清空已授权角色
        if (null == roleIds || roleIds.isEmpty()) {
            userRoleMapper.delete(queryWrapper);
        } else {
            List<UserRole> list = userRoleMapper.selectList(queryWrapper);
            List<Long> exitIds = new ArrayList<>();

            // 如果已有角色不在 新角色列表中, 执行删除操作
            if (null != list) {
                list.forEach(po -> {
                    if (!roleIds.contains(po.getRoleId())) {
                        userRoleMapper.deleteById(po.getId());
                    } else {
                        exitIds.add(po.getRoleId());
                    }
                });
            }

            // 保存不在已有角色中的新角色ID
            roleIds.stream().filter(id -> !exitIds.contains(id)).forEach(roleId -> {
                UserRole po = new UserRole();
                po.setUserId(userId);
                po.setRoleId(roleId);

                userRoleMapper.insert(po);
            });
        }
    }
}
