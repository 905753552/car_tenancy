package com.dev.main.tenancy.service;

import com.dev.main.tenancy.domain.TncCustomer;

public interface ICustomerService {

    TncCustomer findByPhone(String phone);

    void createCustomer(TncCustomer customer);

}

