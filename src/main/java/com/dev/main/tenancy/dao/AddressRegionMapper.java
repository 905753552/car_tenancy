package com.dev.main.tenancy.dao;

import com.dev.main.tenancy.domain.AddressRegion;

public interface AddressRegionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AddressRegion record);

    int insertSelective(AddressRegion record);

    AddressRegion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AddressRegion record);

    int updateByPrimaryKey(AddressRegion record);
}