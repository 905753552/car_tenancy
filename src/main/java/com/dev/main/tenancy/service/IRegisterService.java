package com.dev.main.tenancy.service;

import com.dev.main.tenancy.domain.TncCustomer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IRegisterService {
    int doRegister(String keyInCookie, String data, HttpServletRequest request, HttpServletResponse response);

    TncCustomer checkRepeatPhone(String phone);
}
