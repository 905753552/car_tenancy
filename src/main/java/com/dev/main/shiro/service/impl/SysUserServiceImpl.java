package com.dev.main.shiro.service.impl;

import com.dev.main.shiro.bo.SysRoleBo;
import com.dev.main.shiro.bo.UserRoleAllocationBo;
import com.dev.main.shiro.domain.SysUser;
import com.dev.main.shiro.service.ISysUserService;
import com.dev.main.shiro.util.Pagination;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

public class SysUserServiceImpl implements ISysUserService {
    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public SysUser insert(SysUser record) {
        return null;
    }

    @Override
    public SysUser insertSelective(SysUser record) {
        return null;
    }

    @Override
    public SysUser selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(SysUser record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(SysUser record) {
        return 0;
    }

    @Override
    public SysUser login(String email, String pswd) {
        return null;
    }

    @Override
    public SysUser findUserByEmail(String email) {
        return null;
    }

    @Override
    public Pagination findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public Map<String, Object> deleteUserById(String ids) {
        return null;
    }

    @Override
    public Map<String, Object> updateForbidUserById(Long id, Long status) {
        return null;
    }

    @Override
    public Pagination<UserRoleAllocationBo> findUserAndRole(ModelMap modelMap, Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public List<SysRoleBo> selectRoleByUserId(Long id) {
        return null;
    }

    @Override
    public Map<String, Object> addRole2User(Long userId, String ids) {
        return null;
    }

    @Override
    public Map<String, Object> deleteRoleByUserIds(String userIds) {
        return null;
    }
}
