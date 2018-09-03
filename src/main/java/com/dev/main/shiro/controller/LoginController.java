package com.dev.main.shiro.controller;

import com.dev.main.common.exception.DataNotFoundException;
import com.dev.main.common.util.ResultMap;
import com.dev.main.shiro.controller.exception.ShiroExceptionResolver;
import com.dev.main.shiro.util.ShiroFilterUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope(value = "prototype")
@RequestMapping("/api/shiro")
@ShiroExceptionResolver
public class LoginController extends ShiroBaseController {


    @RequestMapping("/login")
    public ResultMap login() {
        UsernamePasswordToken token = new UsernamePasswordToken("exmapleUsername",
                "example");
        SecurityUtils.getSubject().login(token);
        System.out.println(SecurityUtils.getSubject().getPrincipal());
        return ResultMap.success();
    }

    @RequestMapping("/logout")
    public ResultMap logout() {
        return ResultMap.success();
    }
}
