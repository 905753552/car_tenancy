package com.dev.main.shiro.controller.exception;

import com.dev.main.common.controller.exception.CommonExceptionHandler;
import com.dev.main.common.statics.Constant;
import com.dev.main.common.statics.StatusCode;
import com.dev.main.common.util.JsonUtils;
import com.dev.main.common.util.ResultMap;
import com.dev.main.shiro.util.ShiroFilterUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 最佳实践, 把处于顶层的异常类搁置到代码最尾端
 */
@ControllerAdvice(annotations = {ShiroExceptionResolver.class})
public class ShiroExceptionHandler extends CommonExceptionHandler {
    /**
     * 登录认证异常
     */
    @ExceptionHandler({ UnauthenticatedException.class, AuthenticationException.class })
    public String authenticationException(HttpServletRequest request, HttpServletResponse response) {
        if (ShiroFilterUtils.isAjax(request)) {
            // 输出JSON
            Map<String, Object> map = new HashMap<>();
            map.put("code", StatusCode.NO_LOGIN);
            map.put("msg", "账户未登录");
            writeJson(map, response);
            return null;
        } else {
            return "redirect:" + Constant.LOGIN_URL;
        }
    }

    /**
     * 权限异常
     */
    @ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
    public String authorizationException(HttpServletRequest request, HttpServletResponse response) {
        if (ShiroFilterUtils.isAjax(request)) {
            // 输出JSON
            Map<String, Object> map = new HashMap<>();
            map.put("code", StatusCode.NOT_UNAUTHORIZED);
            map.put("msg", "无权限");
            writeJson(map, response);
            return null;
        } else {
            return "redirect:" + Constant.UNAUTHORIZED_URL;
        }
    }

    /**
     * 输出JSON
     */
    private void writeJson(Map<String, Object> map, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            out = response.getWriter();
            out.write(JsonUtils.toJsonStr(map));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    @ResponseBody
    @ExceptionHandler({IncorrectCredentialsException.class})
    public ResultMap incorrectCredentialsException(IncorrectCredentialsException e) {
        e.printStackTrace();
        return ResultMap.fail("用户名或密码错误");
    }
}
