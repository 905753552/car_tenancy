package com.dev.main.shiro.service;

import com.dev.main.shiro.bo.RolePermissionAllocationBo;
import com.dev.main.shiro.domain.SysRole;
import com.dev.main.shiro.util.Pagination;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ISysRoleService {
    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    Pagination<SysRole> findPage(Map<String, Object> resultMap, Integer pageNo,
                                 Integer pageSize);

    Map<String, Object> deleteRoleById(String ids);

    Pagination<RolePermissionAllocationBo> findRoleAndPermissionPage(
            Map<String, Object> resultMap, Integer pageNo, Integer pageSize);
    //根据用户ID查询角色（role），放入到Authorization里。
    Set<String> findRoleByUserId(Long userId);

    List<SysRole> findNowAllPermission();
    //初始化数据
    void initData();
}
