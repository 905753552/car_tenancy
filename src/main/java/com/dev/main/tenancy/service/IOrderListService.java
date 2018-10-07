package com.dev.main.tenancy.service;

import com.dev.main.common.util.ResultMap;

public interface IOrderListService {
    ResultMap getOrderList(String phone);

    /*
    获取myorderlist_detail页面的数据(已不用)
     */
    ResultMap getDetail(Long id);

    /*
    传递凌兴的参数（已不用）
     */
    ResultMap getOrderDetail(Long id);

    ResultMap getOrderData(Long id);
}
