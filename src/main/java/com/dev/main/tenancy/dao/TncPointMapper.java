package com.dev.main.tenancy.dao;

import com.dev.main.tenancy.domain.TncPoint;

public interface TncPointMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TncPoint record);

    int insertSelective(TncPoint record);

    TncPoint selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TncPoint record);

    int updateByPrimaryKey(TncPoint record);
}