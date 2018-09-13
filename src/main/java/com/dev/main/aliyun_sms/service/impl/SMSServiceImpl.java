package com.dev.main.aliyun_sms.service.impl;

import com.dev.main.aliyun_sms.service.ISMSService;
import com.dev.main.common.util.RandomUtil;
import org.springframework.stereotype.Service;

@Service
public class SMSServiceImpl implements ISMSService {

    @Override
    public void sendVerifyCode(String phone) {
        String code = RandomUtil.getRandomNumString(6);

    }

    @Override
    public void verifyCode(String uuidInCookie, String phone, String codeInput) {

    }
}
