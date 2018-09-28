package com.dev.main.tenancy.service.impl;

import com.dev.main.common.exception.CommonException;
import com.dev.main.common.util.ResultMap;
import com.dev.main.shiro.util.ShiroUtils;
import com.dev.main.tenancy.dao.*;
import com.dev.main.tenancy.domain.*;
import com.dev.main.tenancy.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *  * Description: main
 *  * Created by sf on 2018/9/25 8:37
 *  
 */
@Service
public class OrderService implements IOrderService {
    @Autowired
    private TncCarMapper tncCarMapper;
    @Autowired
    private TncPriceSchemeMapper tncPriceSchemeMapper;
    @Autowired
    private TncStoreMapper tncStoreMapper;
    @Autowired
    private TncOrderMapper tncOrderMapper;
    @Autowired
    private TncCustomerMapper tncCustomerMapper;
    @Autowired
    private TncPackageSchemeMapper tncPackageSchemeMapper;


    @Override
    public ResultMap selectByPrimaryKey(Long id) {
        TncCar tncCar = tncCarMapper.selectByPrimaryKey(id);
        if(tncCar!=null)
            return ResultMap.success("获取车辆信息成功").put("car",tncCar);
        else
            throw new CommonException("获取车辆信息失败");
    }

    @Override
    public ResultMap selectPriceSchemeByPrimaryKey(Long id) {
        TncPriceScheme tncPriceScheme = tncPriceSchemeMapper.selectByPrimaryKey(id);
        TncPackageScheme tncPackageScheme = tncPackageSchemeMapper.selectByPrimaryKey(tncPriceScheme.getPackageId());
        if(tncPriceScheme!=null){
            ResultMap resultMap = ResultMap.success("获取价格方案成功");
            resultMap.put("priceScheme",tncPriceScheme);
            resultMap.put("packageScheme",tncPackageScheme);
            return resultMap;
        }
        else
            throw new CommonException("获取价格方案失败");
    }

    @Override
    public ResultMap selectAddressByStoreId(Long id) {
        TncStore tncStore = tncStoreMapper.selectByPrimaryKey(id);
        TncAddress tncAddress = tncStore.getTncAddress();
        if(tncAddress!=null)
            return ResultMap.success("获取门店地址成功").put("storeAddress",tncAddress);
        else
            throw new CommonException("获取门店地址失败");
    }

    @Override
    public ResultMap getCustomerCoupons() {
        TncCustomer tncCustomer = tncCustomerMapper.selectByPrimaryKey(ShiroUtils.getUserId());
        List<TncCoupon> list = tncOrderMapper.selectCouponsByCid(tncCustomer.getId());
        ResultMap resultMap = ResultMap.success("获取顾客优惠券成功");
        resultMap.put("list",list);
        resultMap.put("customer",tncCustomer);
        return resultMap;
    }

    @Override
    public ResultMap updateCustomerInfo(TncCustomer tncCustomer) {
        TncCustomer tncCustomer1 = ShiroUtils.getUserEntity();
        tncCustomer.setId(tncCustomer1.getId());
        int result = tncOrderMapper.updateCustomerInfo(tncCustomer);
        if(result>0)
            return ResultMap.success("修改顾客信息成功");
        else
            throw new CommonException("修改顾客信息失败");
    }

    @Override
    public ResultMap insertOrder(TncOrder tncOrder) {
        tncOrder.setGmtCreate(new Date());
        tncOrder.setGmtModified(new Date());
        tncOrder.setPayTime(null);
        int num = tncOrderMapper.insert(tncOrder);
        if(num>0){
            ResultMap resultMap = ResultMap.success("添加订单成功");
            resultMap.put("order",tncOrder);
            return resultMap;
        }else
            throw new CommonException("添加订单失败");
    }

    @Override
    public ResultMap savePay(Long id) {
        Byte status = 2;
        TncOrder tncOrder = tncOrderMapper.selectByPrimaryKey(id);
        tncOrder.setPayTime(new Date());
        tncOrder.setStatus(status);
        int res = tncOrderMapper.updateByPrimaryKey(tncOrder);
        List<TncCarItem> tncCarItems = tncOrderMapper.selectCarItemByPrimaryKey(tncOrder.getCarItemId());
        if(res>0){
            if(tncCarItems.size()>0){
                ResultMap resultMap = ResultMap.success("支付订单成功");
                resultMap.put("order",tncOrder);
                resultMap.put("carItem",tncCarItems.get(0));
                return resultMap;
            }else{
                throw new CommonException("无可租车辆");
            }
        }else{
            throw new CommonException("支付订单失败");
        }
    }

    @Override
    public ResultMap cancelOrder(Long id) {
        Byte status = 3;
        TncOrder tncOrder = tncOrderMapper.selectByPrimaryKey(id);
        tncOrder.setStatus(status);
        int res = tncOrderMapper.updateByPrimaryKey(tncOrder);
        if (res>0){
            return ResultMap.success("取消订单成功");
        }else{
            throw new CommonException("取消订单失败");
        }
    }

    @Override
    public ResultMap getOrder(Long id) {
        TncOrder order = tncOrderMapper.selectByPrimaryKey(id);
        if(order!=null){
            return  ResultMap.success("获取订单成功").put("order",order);
        }else{
            throw new CommonException("获取订单失败");
        }
    }
}
