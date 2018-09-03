package com.dev.main.shiro.dao;

import com.dev.main.shiro.domain.SysUserPermission;

public interface SysUserPermissionMapper {
    int insert(SysUserPermission record);

    int insertSelective(SysUserPermission record);
}