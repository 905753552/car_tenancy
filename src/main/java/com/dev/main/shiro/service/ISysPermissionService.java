package com.dev.main.shiro.service;

import com.dev.main.shiro.bo.SysPermissionBo;
import com.dev.main.shiro.domain.SysPermission;
import com.dev.main.shiro.util.Pagination;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ISysPermissionService {
    int deleteByPrimaryKey(Long id);

    SysPermission insert(SysPermission record);

    SysPermission insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

    Map<String, Object> deletePermissionById(String ids);

    Pagination<SysPermissionBo> findPage(Map<String,Object> resultMap, Integer pageNo,
                        Integer pageSize);
    List<SysPermissionBo> selectPermissionById(Long id);

    Map<String, Object> addPermission2Role(Long roleId,String ids);

    Map<String, Object> deleteByRids(String roleIds);
    //根据用户ID查询权限（permission），放入到Authorization里。
    Set<String> findPermissionByUserId(Long userId);
}
