package com.dev.main.tenancy.dao;

import com.dev.main.tenancy.domain.TncAds;

public interface TncAdsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TncAds record);

    int insertSelective(TncAds record);

    TncAds selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TncAds record);

    int updateByPrimaryKey(TncAds record);
}