package com.mtons.mblog.modules.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mtons.mblog.modules.pojo.RolePermission;

import java.util.List;

/**
 * @ClassName: RolePermissionMapper
 * @Auther: Jerry
 * @Date: 2020/4/11 12:58
 * @Desctiption: TODO
 * @Version: 1.0
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
    void insertBatch(List<RolePermission> rolePermissions);
}
