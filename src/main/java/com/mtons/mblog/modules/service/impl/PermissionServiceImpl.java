package com.mtons.mblog.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtons.mblog.modules.mapper.PermissionMapper;
import com.mtons.mblog.modules.pojo.Permission;
import com.mtons.mblog.modules.service.PermissionService;
import com.mtons.mblog.modules.vo.PermissionTree;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName: PermissionNewService
 * @Auther: Jerry
 * @Date: 2020/4/17 17:57
 * @Desctiption: TODO
 * @Version: 1.0
 */
@Service
@Transactional(readOnly = true)
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public IPage<Permission> paging(Page page, String name) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name).orderByDesc("id");
        return permissionMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<PermissionTree> tree() {
        List<Permission> data = getPermissionList(null);

        List<PermissionTree> results = new LinkedList<>();
        Map<Long, PermissionTree> map = new LinkedHashMap<>();

        for (Permission po : data) {
            PermissionTree m = new PermissionTree();
            BeanUtils.copyProperties(po, m);
            map.put(po.getId(), m);
        }

        for (PermissionTree m : map.values()) {
            if (m.getParentId() == 0) {
                results.add(m);
            } else {
                PermissionTree p = map.get(m.getParentId());
                if (p != null) {
                    p.addItem(m);
                }
            }
        }
        return results;
    }

    /**
     * 获取权限列表
     * @param parentId（可不填）
     * @return {@link List<Permission>}
     */
    private List<Permission> getPermissionList(Integer parentId){
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("weight").orderByAsc("id");
        if (parentId != null) {
            queryWrapper.eq("parent_id", parentId);
        }
        return permissionMapper.selectList(queryWrapper);
    }

    @Override
    public List<PermissionTree> tree(int parentId) {
        List<Permission> list = getPermissionList(parentId);

        List<PermissionTree> results = new ArrayList<>();
        list.forEach(po -> {
            PermissionTree menu = new PermissionTree();
            BeanUtils.copyProperties(po, menu);
            results.add(menu);
        });
        return results;
    }

    @Override
    public List<Permission> list() {
        return permissionMapper.selectList(new QueryWrapper<Permission>().orderByDesc("id"));
    }

    @Override
    public Permission get(long id) {
        return permissionMapper.selectById(id);
    }
}
