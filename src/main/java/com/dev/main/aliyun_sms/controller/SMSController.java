package com.dev.main.aliyun_sms.controller;

import com.aliyuncs.exceptions.ClientException;
import com.dev.main.aliyun_sms.service.ISMSService;
import com.dev.main.aliyun_sms.statics.SmsConstant;
import com.dev.main.common.controller.exception.GlobalExceptionResolver;
import com.dev.main.common.util.CookieUtils;
import com.dev.main.common.util.ResultMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/alisms")
@GlobalExceptionResolver
public class SMSController {

    @Autowired
    private ISMSService smsService;

    @GetMapping("{phone}")
    public ResultMap send(@CookieValue(value = SmsConstant.COOKIE_NAME, required = false) String keyInCookie,
                          @PathVariable("phone") String phone,
                          HttpServletRequest request, HttpServletResponse response) throws ClientException, InterruptedException {
        // 清除原来的Cookie
        if (!StringUtils.isBlank(keyInCookie)) {
            CookieUtils.clearCookie(request, response, SmsConstant.COOKIE_NAME, SmsConstant.COOKIE_PATH);
        }
        smsService.sendVerifyCode(phone, request, response);
        return  ResultMap.success();
    }

    @GetMapping(path = "/verify/{phone}/{codeInput}")
    public ResultMap verify(@CookieValue(value = SmsConstant.COOKIE_NAME, required = false) String keyInCookie,
                            @PathVariable("phone") String phone,
                            @PathVariable("codeInput") String codeInput,
                            HttpServletRequest request, HttpServletResponse response) {
        boolean isMatch = smsService.verifyCode(keyInCookie, phone, codeInput, request, response);
        if (!isMatch) {
            return ResultMap.fail("验证码错误");
        }
        return ResultMap.success();
    }

    public void setSmsService(ISMSService smsService) {
        this.smsService = smsService;
    }
}
