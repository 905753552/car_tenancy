package com.dev.main.tenancy.dao;

import com.dev.main.tenancy.domain.TncPackageScheme;

public interface TncPackageSchemeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TncPackageScheme record);

    int insertSelective(TncPackageScheme record);

    TncPackageScheme selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TncPackageScheme record);

    int updateByPrimaryKey(TncPackageScheme record);
}