package com.dev.main.tenancy.service;

import com.dev.main.tenancy.domain.AddressRegion;
import com.dev.main.tenancy.domain.TncCoupon;
import com.dev.main.tenancy.domain.TncCustomer;
import com.dev.main.tenancy.domain.TncPoint;
import com.dev.main.tenancy.vo.TncCustomerVo;

import java.util.List;
import java.util.Map;

public interface ICustomerService {

    TncCustomer findByPhone(String phone);

    void createCustomer(TncCustomer customer);

    /**单个查询*/
    TncCustomerVo findCustomerVo(Long uid);

    /**更改信息*/
    void changeInfo(TncCustomerVo tncCustomerVo);

    /**根据父级id和等级查找地区*/
    List<AddressRegion> findAddress(Long aid, byte level);

    /*修改密码*/
    void changePwd(Map<String, String> data);

    /**根据用户id查找用户优惠券*/
    List<TncCoupon> findCouponByUid(Long uid);

    /**根据用户id查找用户优惠券*/
    int countUnuse(Long uid);

    /**根据用户id查找积分*/
    TncPoint findUserPointById(Long uid);
}

