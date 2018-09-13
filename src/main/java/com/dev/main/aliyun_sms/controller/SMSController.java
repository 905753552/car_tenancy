package com.dev.main.aliyun_sms.controller;

import com.dev.main.aliyun_sms.service.ISMSService;
import com.dev.main.common.util.RandomUtil;
import com.dev.main.common.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/alisms")
public class SMSController {

    @Autowired
    private ISMSService smsService;

    @GetMapping("{phone}")
    public ResultMap send(@PathVariable("phone") String phone, HttpServletRequest request, HttpServletResponse response) {
        return  ResultMap.success();
    }

    public void setSmsService(ISMSService smsService) {
        this.smsService = smsService;
    }
}
