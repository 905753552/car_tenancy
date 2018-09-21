package com.dev.main.shiro.controller;

import com.aliyuncs.exceptions.ClientException;
import com.dev.main.common.controller.exception.GlobalExceptionResolver;
import com.dev.main.common.exception.CommonException;
import com.dev.main.common.statics.Constant;
import com.dev.main.common.statics.StatusCode;
import com.dev.main.common.util.JsonUtils;
import com.dev.main.common.util.ResultMap;
import com.dev.main.shiro.util.ShiroConstant;
import com.dev.main.shiro.util.ShiroFilterUtils;
import com.dev.main.shiro.util.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/shiro")
@GlobalExceptionResolver
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

    @ExceptionHandler(value = CommonException.class)
    @ResponseBody
    public ResultMap commonExceptionExceptionHandler(CommonException e) {
        e.printStackTrace();
        return ResultMap.fail(e.getMessage());
    }


}
