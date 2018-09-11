package com.dev.main.tenancy.dao;

import com.dev.main.tenancy.domain.TncCarPic;

public interface TncCarPicMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TncCarPic record);

    int insertSelective(TncCarPic record);

    TncCarPic selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TncCarPic record);

    int updateByPrimaryKey(TncCarPic record);
}