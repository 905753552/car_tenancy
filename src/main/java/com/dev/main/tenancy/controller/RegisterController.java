package com.dev.main.tenancy.controller;

import com.dev.main.aliyun_sms.service.ISMSService;
import com.dev.main.common.util.ResultMap;
import com.dev.main.tenancy.domain.TncCustomer;
import com.dev.main.tenancy.service.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class RegisterController {

    @Autowired
    private ISMSService smsService;

    @Autowired
    private IRegisterService registerService;

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResultMap doRegister(@RequestBody String data){
        int n =registerService.doRegister(data);
        return ResultMap.success();
    }

}
