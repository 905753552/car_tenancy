package com.dev.main.shiro.service;

import com.dev.main.shiro.bo.SysRoleBo;
import com.dev.main.shiro.bo.UserRoleAllocationBo;
import com.dev.main.shiro.domain.SysUser;
import com.dev.main.shiro.util.Pagination;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

public interface ISysUserService {
    int deleteByPrimaryKey(Long id);

    SysUser insert(SysUser record);

    SysUser insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser login(String email ,String pswd);

    SysUser findUserByEmail(String email);

    Pagination findByPage(Map<String, Object> resultMap, Integer pageNo,
                          Integer pageSize);

    Map<String, Object> deleteUserById(String ids);

    Map<String, Object> updateForbidUserById(Long id, Long status);

    Pagination<UserRoleAllocationBo> findUserAndRole(ModelMap modelMap,
                                                     Integer pageNo, Integer pageSize);

    List<SysRoleBo> selectRoleByUserId(Long id);

    Map<String, Object> addRole2User(Long userId, String ids);

    Map<String, Object> deleteRoleByUserIds(String userIds);
}
