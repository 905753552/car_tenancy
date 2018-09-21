package com.dev.main.tenancy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dev.main.tenancy.service.IRegisterService;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements IRegisterService {

    @Override
    public int doRegister(String data) {
        System.out.println(data);
        JSONObject jsonObject = JSONObject.parseObject(data);
        System.out.println(jsonObject.get("tnc"));
        System.out.println(jsonObject.get("code"));
        System.out.println(jsonObject.get("coupon"));

        return 0;
    }
}
