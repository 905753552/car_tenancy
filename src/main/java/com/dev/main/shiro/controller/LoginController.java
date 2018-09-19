package com.dev.main.shiro.controller;

import com.dev.main.common.util.ResultMap;
import com.dev.main.shiro.controller.exception.ShiroExceptionResolver;
import com.dev.main.shiro.util.ShiroUtils;
import com.google.code.kaptcha.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope(value = "prototype")
@RequestMapping("/api/shiro")
@ShiroExceptionResolver
public class LoginController extends ShiroBaseController {

    /**
     * 登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultMap login(String username, String password) {
        Subject subject = ShiroUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);
        return ResultMap.success();
    }

    /**
     * 退出
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ResultMap logout() {
        ShiroUtils.logout();
        return ResultMap.success();
    }
}
