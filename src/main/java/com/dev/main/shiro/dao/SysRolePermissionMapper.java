package com.dev.main.shiro.dao;

import com.dev.main.shiro.domain.SysRolePermission;

public interface SysRolePermissionMapper {
    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);
}