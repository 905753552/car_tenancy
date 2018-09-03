package com.dev.main.shiro.dao;

import com.dev.main.shiro.domain.SysUserRole;

public interface SysUserRoleMapper {
    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);
}