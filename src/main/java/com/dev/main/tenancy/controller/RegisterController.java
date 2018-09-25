package com.dev.main.tenancy.controller;

import com.dev.main.aliyun_sms.service.ISMSService;
import com.dev.main.aliyun_sms.statics.SmsConstant;
import com.dev.main.common.controller.exception.GlobalExceptionResolver;
import com.dev.main.common.util.ResultMap;
import com.dev.main.tenancy.domain.TncCustomer;
import com.dev.main.tenancy.service.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@GlobalExceptionResolver
@RequestMapping("/api/user")
public class RegisterController {



    @Autowired
    private IRegisterService registerService;

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResultMap doRegister(@CookieValue(value = SmsConstant.COOKIE_NAME, required = false) String keyInCookie,
                                @RequestBody String data,
                                HttpServletRequest request,
                                HttpServletResponse response){
        int n =registerService.doRegister(keyInCookie,data,request,response);
        return ResultMap.success();
    }

}
