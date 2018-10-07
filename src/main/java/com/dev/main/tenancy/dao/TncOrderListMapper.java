package com.dev.main.tenancy.dao;

import com.dev.main.tenancy.domain.*;
import com.dev.main.tenancy.vo.CarVo;
import com.dev.main.tenancy.vo.TncCarVo;
import com.dev.main.tenancy.vo.TncOrderListVo;
import com.dev.main.tenancy.vo.TncStoreVo;

import java.util.List;

public interface TncOrderListMapper {
    //根据用户电话获取订单列表
    List<TncOrderListVo> selectOrderList(String phone);
    TncOrderListVo selectOrderDetail(Long id);
    TncPriceScheme selectPrice(Long carid);

//    根据汽车item的id获取汽车车id,品牌,车系,图片,厢数,排量,座位数
    CarVo selectCarItem(Long carItemId);
//    根据门店id获取门店名,门店省,市,区,具体地址
    TncStoreVo selectStore(Long StoreId);
//    根据汽车id获取价格方案
    TncPriceScheme getPrice(Long carId);
}