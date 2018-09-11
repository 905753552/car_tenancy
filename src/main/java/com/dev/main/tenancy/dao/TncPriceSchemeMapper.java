package com.dev.main.tenancy.dao;

import com.dev.main.tenancy.domain.TncPriceScheme;

public interface TncPriceSchemeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TncPriceScheme record);

    int insertSelective(TncPriceScheme record);

    TncPriceScheme selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TncPriceScheme record);

    int updateByPrimaryKey(TncPriceScheme record);
}