package com.dev.main.aliyun_sms.service;

public interface ISMSService {

    /**
     * 发送验证码
     * @param phone 手机号
     */
    void sendVerifyCode(String phone);

    /**
     * 验证短信验证码
     * @param uuidInCookie 存在cookie中的验证码的键，用于从缓存中查询验证码
     * @param phone 手机号，用于验证是否为发送验证码的手机号
     * @param codeInput 用户输入的验证码
     */
    void verifyCode(String uuidInCookie, String phone, String codeInput);

}
