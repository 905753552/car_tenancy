package com.dev.main.tenancy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dev.main.aliyun_sms.service.ISMSService;
import com.dev.main.common.exception.CommonException;
import com.dev.main.common.util.JsonUtils;
import com.dev.main.tenancy.domain.TncCustomer;
import com.dev.main.tenancy.service.ICustomerService;
import com.dev.main.tenancy.service.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class RegisterServiceImpl implements IRegisterService {

    @Autowired
    private ISMSService smsService;

    @Autowired
    private ICustomerService customerService;

    @Override
    public int doRegister(String keyInCookie, String data, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = JSONObject.parseObject(data);
        TncCustomer tncCustomer =JsonUtils.toObject(jsonObject.get("tnc").toString(),TncCustomer.class);

        String code = (String) jsonObject.get("code");
        String coupon = (String) jsonObject.get("coupon");

        // 验证验证码
        boolean match = smsService.verifyCode(keyInCookie, tncCustomer.getPhone(),code, request, response);

        if (!match) {
            throw new CommonException("验证码错误");
        }

        customerService.createCustomer(tncCustomer);

        return 0;
    }
}
