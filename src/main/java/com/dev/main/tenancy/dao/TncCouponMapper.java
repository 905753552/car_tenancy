package com.dev.main.tenancy.dao;

import com.dev.main.tenancy.domain.TncCoupon;

import java.util.List;

public interface TncCouponMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TncCoupon record);

    int insertSelective(TncCoupon record);

    TncCoupon selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TncCoupon record);

    int updateByPrimaryKey(TncCoupon record);

    /**根据用户id查找用户优惠券*/
    List<TncCoupon> selectByUserKey(Long uid);

    int countUnuse(Long uid);
}