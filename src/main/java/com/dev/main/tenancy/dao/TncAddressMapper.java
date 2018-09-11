package com.dev.main.tenancy.dao;

import com.dev.main.tenancy.domain.TncAddress;

public interface TncAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TncAddress record);

    int insertSelective(TncAddress record);

    TncAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TncAddress record);

    int updateByPrimaryKey(TncAddress record);
}