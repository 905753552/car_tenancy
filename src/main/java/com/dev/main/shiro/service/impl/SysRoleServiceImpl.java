package com.dev.main.shiro.service.impl;

import com.dev.main.shiro.bo.RolePermissionAllocationBo;
import com.dev.main.shiro.domain.SysRole;
import com.dev.main.shiro.service.ISysRoleService;
import com.dev.main.shiro.util.Pagination;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class SysRoleServiceImpl implements ISysRoleService {
    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(SysRole record) {
        return 0;
    }

    @Override
    public int insertSelective(SysRole record) {
        return 0;
    }

    @Override
    public SysRole selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(SysRole record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(SysRole record) {
        return 0;
    }

    @Override
    public Pagination<SysRole> findPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public Map<String, Object> deleteRoleById(String ids) {
        return null;
    }

    @Override
    public Pagination<RolePermissionAllocationBo> findRoleAndPermissionPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public Set<String> findRoleByUserId(Long userId) {
        return null;
    }

    @Override
    public List<SysRole> findNowAllPermission() {
        return null;
    }

    @Override
    public void initData() {

    }
}
