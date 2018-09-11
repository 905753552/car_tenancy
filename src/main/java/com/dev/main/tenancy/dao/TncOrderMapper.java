package com.dev.main.tenancy.dao;

import com.dev.main.tenancy.domain.TncOrder;

public interface TncOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TncOrder record);

    int insertSelective(TncOrder record);

    TncOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TncOrder record);

    int updateByPrimaryKey(TncOrder record);
}