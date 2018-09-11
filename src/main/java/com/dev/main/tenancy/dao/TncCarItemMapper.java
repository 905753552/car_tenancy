package com.dev.main.tenancy.dao;

import com.dev.main.tenancy.domain.TncCarItem;

public interface TncCarItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TncCarItem record);

    int insertSelective(TncCarItem record);

    TncCarItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TncCarItem record);

    int updateByPrimaryKey(TncCarItem record);
}