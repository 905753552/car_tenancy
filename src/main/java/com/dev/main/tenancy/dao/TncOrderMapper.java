package com.dev.main.tenancy.dao;

import com.dev.main.tenancy.domain.TncCarItem;
import com.dev.main.tenancy.domain.TncCoupon;
import com.dev.main.tenancy.domain.TncCustomer;
import com.dev.main.tenancy.domain.TncOrder;

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

    List<TncCarItem> selectCarItemByPrimaryKey(Long id);
}