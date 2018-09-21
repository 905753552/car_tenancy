package com.dev.main.shiro.controller;

import com.dev.main.common.util.ResultMap;
import com.dev.main.shiro.util.ShiroConstant;
import com.dev.main.shiro.util.ShiroUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/shiro")
public class LoginController extends ShiroBaseController {

    /**
     *
     * @param phone
     * @param input 用户的输入，密码或短信验证码
     * @param loginType "phone"或“password”
     * @return
     */
    @GetMapping(value = "/login")
    public ResultMap login(String phone, String input, String loginType, HttpServletRequest request) {
        Subject subject = ShiroUtils.getSubject();
        UsernamePasswordToken token = null;
        if (ShiroConstant.PASSWORD.equalsIgnoreCase(loginType)) {
            token = new UsernamePasswordToken(phone, input);
        } else {
            request.setAttribute("loginType", loginType);
            request.setAttribute("code", input);
            // 短信验证码登录,不需要密码，约定密码字段为“ok”
            token = new UsernamePasswordToken(phone, "ok");
        }
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
