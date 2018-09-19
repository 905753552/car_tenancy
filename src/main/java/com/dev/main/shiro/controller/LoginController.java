package com.dev.main.shiro.controller;

import com.dev.main.common.util.ResultMap;
import com.dev.main.shiro.controller.exception.ShiroExceptionResolver;
import com.dev.main.shiro.util.ShiroUtils;
import com.dev.main.shiro.util.UserNamePasswordPhoneToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope(value = "prototype")
@RequestMapping("/api/shiro")
@ShiroExceptionResolver
public class LoginController extends ShiroBaseController {

    /**
     * 密码登录
     */
    @RequestMapping(value = "/passwordLogin", method = RequestMethod.POST)
    public ResultMap loginbByPassword(String phone, String password) {
        Subject subject = ShiroUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(phone, password);
        subject.login(token);
        return ResultMap.success();
    }

    /**
     * 手机短信验证码登录
     */
    @GetMapping(value = "/smsLogin")
    public ResultMap loginBySms(String phone, String codeInput) {
        Subject subject = ShiroUtils.getSubject();
        String phone_code = phone + "_" + codeInput;
        UserNamePasswordPhoneToken token = new UserNamePasswordPhoneToken(phone_code);
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
