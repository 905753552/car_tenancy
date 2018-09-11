package com.dev.main.tenancy.dao;

import com.dev.main.tenancy.domain.TncCoupon;

public interface TncCouponMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TncCoupon record);

    int insertSelective(TncCoupon record);

    TncCoupon selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TncCoupon record);

    int updateByPrimaryKey(TncCoupon record);
}