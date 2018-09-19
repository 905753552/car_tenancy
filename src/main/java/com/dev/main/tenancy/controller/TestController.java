package com.dev.main.tenancy.controller;

import com.dev.main.common.exception.CommonException;
import com.dev.main.common.util.ResultMap;
import com.dev.main.shiro.dao.SysUserMapper;
import com.dev.main.shiro.domain.SysUser;
import com.dev.main.tenancy.controller.exception.TenancyExceptionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@TenancyExceptionResolver
public class TestController {

    @Autowired
    private SysUserMapper sysUserMapper;

    public void setSysUserMapper(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @GetMapping("/tnc/test")
    public ResultMap test() {
        SysUser user = sysUserMapper.selectByPrimaryKey(1L);
        if (user == null) {
            throw new CommonException("用户不存在");
        }
        return ResultMap.success().put("user", user);
    }
}
