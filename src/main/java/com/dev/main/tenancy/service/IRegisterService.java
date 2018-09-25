package com.dev.main.tenancy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IRegisterService {
    int doRegister(String keyInCookie, String data, HttpServletRequest request, HttpServletResponse response);
}
