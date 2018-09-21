package com.dev.main.common.controller.exception;

import com.aliyuncs.exceptions.ClientException;
import com.dev.main.common.exception.CommonException;
import com.dev.main.common.statics.Constant;
import com.dev.main.common.statics.StatusCode;
import com.dev.main.common.util.JsonUtils;
import com.dev.main.common.util.ResultMap;
import com.dev.main.shiro.util.ShiroFilterUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理,最佳实践, 把处于顶层的异常类搁置到代码最尾端
 */
@ControllerAdvice(annotations = {GlobalExceptionResolver.class})
public class CommonExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultMap exceptionHandler(Exception e) {
        e.printStackTrace();
        return ResultMap.fail(e.getMessage());
    }

    /**
     * 阿里云短信异常处理
     * @param e
     * @return
     */
    @ExceptionHandler({ClientException.class})
    @ResponseBody
    public ResultMap clientException(ClientException e) {
        e.printStackTrace();
        return ResultMap.fail();
    }

    @ExceptionHandler({InterruptedException.class})
    @ResponseBody
    public ResultMap interruptedException(InterruptedException e) {
        e.printStackTrace();
        return ResultMap.fail();
    }

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

    @ResponseBody
    @ExceptionHandler({UnknownAccountException.class})
    public ResultMap unknownAccountException(UnknownAccountException e) {
        e.printStackTrace();
        return ResultMap.fail("用户名或密码错误");
    }

    @ResponseBody
    @ExceptionHandler({LockedAccountException.class})
    public ResultMap lockedAccountException(LockedAccountException e) {
        e.printStackTrace();
        return ResultMap.fail("账号已被锁定,请联系管理员");
    }

    @ExceptionHandler(value = CommonException.class)
    @ResponseBody
    public ResultMap commonExceptionExceptionHandler(CommonException e) {
        e.printStackTrace();
        return ResultMap.fail(e.getMessage());
    }

}
