package com.dev.main.tenancy.dao;

import com.dev.main.tenancy.domain.TncBrand;

public interface TncBrandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TncBrand record);

    int insertSelective(TncBrand record);

    TncBrand selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TncBrand record);

    int updateByPrimaryKey(TncBrand record);
}