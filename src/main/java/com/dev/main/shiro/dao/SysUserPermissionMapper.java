package com.dev.main.shiro.dao;

import com.dev.main.shiro.domain.SysUserPermission;

public interface SysUserPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUserPermission record);

    int insertSelective(SysUserPermission record);

    SysUserPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserPermission record);

    int updateByPrimaryKey(SysUserPermission record);
}