package com.dev.main.shiro.service.impl;

import com.dev.main.shiro.bo.SysPermissionBo;
import com.dev.main.shiro.domain.SysPermission;
import com.dev.main.shiro.service.ISysPermissionService;
import com.dev.main.shiro.util.Pagination;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class SysPermissionServiceImpl implements ISysPermissionService {
    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public SysPermission insert(SysPermission record) {
        return null;
    }

    @Override
    public SysPermission insertSelective(SysPermission record) {
        return null;
    }

    @Override
    public SysPermission selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(SysPermission record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(SysPermission record) {
        return 0;
    }

    @Override
    public Map<String, Object> deletePermissionById(String ids) {
        return null;
    }

    @Override
    public Pagination<SysPermissionBo> findPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public List<SysPermissionBo> selectPermissionById(Long id) {
        return null;
    }

    @Override
    public Map<String, Object> addPermission2Role(Long roleId, String ids) {
        return null;
    }

    @Override
    public Map<String, Object> deleteByRids(String roleIds) {
        return null;
    }

    @Override
    public Set<String> findPermissionByUserId(Long userId) {
        return null;
    }
}
