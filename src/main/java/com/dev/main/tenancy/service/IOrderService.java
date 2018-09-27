package com.dev.main.tenancy.service;

import com.dev.main.common.util.ResultMap;
import com.dev.main.tenancy.domain.TncCar;
import com.dev.main.tenancy.domain.TncCustomer;
import com.dev.main.tenancy.domain.TncOrder;

/**
 *  * Description: main
 *  * Created by sf on 2018/9/25 8:36
 *  
 */
public interface IOrderService {

    ResultMap selectByPrimaryKey(Long id);

    ResultMap selectPriceSchemeByPrimaryKey(Long id);

    ResultMap selectAddressByStoreId(Long id);

    ResultMap getCustomerCoupons();

    ResultMap updateCustomerInfo(TncCustomer tncCustomer);

    ResultMap insertOrder(TncOrder tncOrder);

    ResultMap savePay(Long id);
}
