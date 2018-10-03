package com.dev.main.tenancy.dao;

import com.dev.main.tenancy.domain.*;

import java.util.List;

public interface TncOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TncOrder record);

    int insertSelective(TncOrder record);

    TncOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TncOrder record);

    int updateByPrimaryKey(TncOrder record);

    List<TncCoupon> selectCouponsByCid(Long id);

    int updateCustomerInfo(TncCustomer tncCustomer);

    TncCarItem selectCarItemByPrimaryKey(Long id);

    List<TncCarItem> selectCarItemBycid(Long id);

    int insertPointLog(TncPointLog tncPointLog);

    int insertPoint(TncPoint tncPoint);
}