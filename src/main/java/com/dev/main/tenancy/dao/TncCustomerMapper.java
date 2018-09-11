package com.dev.main.tenancy.dao;

import com.dev.main.tenancy.domain.TncCustomer;

public interface TncCustomerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TncCustomer record);

    int insertSelective(TncCustomer record);

    TncCustomer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TncCustomer record);

    int updateByPrimaryKey(TncCustomer record);
}