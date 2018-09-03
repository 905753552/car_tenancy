package com.dev.main.kaptcha.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ICaptchaService {

    void generate(HttpServletRequest request, HttpServletResponse response) throws IOException;

    /**
     *
     * @param key 缓存中二维码的键，来自Cookie
     * @param codeInput 用户输入的只
     * @param request
     * @param response
     * @return
     */
    boolean verify(String key, String codeInput, HttpServletRequest request, HttpServletResponse response);

}
