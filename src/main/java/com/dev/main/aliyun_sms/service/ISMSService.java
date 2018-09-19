package com.dev.main.aliyun_sms.service;

import com.aliyuncs.exceptions.ClientException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ISMSService {

    /**
     * 发送验证码
     * @param phone 手机号
     */
    void sendVerifyCode(String phone, HttpServletRequest request, HttpServletResponse response) throws ClientException, InterruptedException;

    /**
     * 验证短信验证码
     * @param keyInCookie 存在cookie中的验证码的键，用于从缓存中查询验证码
     * @param phone 手机号，用于验证是否为发送验证码的手机号
     * @param codeInput 用户输入的验证码
     */
    boolean verifyCode(String keyInCookie, String phone, String codeInput, HttpServletRequest request, HttpServletResponse response);

}
