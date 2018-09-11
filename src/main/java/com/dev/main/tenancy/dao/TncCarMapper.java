package com.dev.main.tenancy.dao;

import com.dev.main.tenancy.domain.TncCar;

public interface TncCarMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TncCar record);

    int insertSelective(TncCar record);

    TncCar selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TncCar record);

    int updateByPrimaryKey(TncCar record);
}