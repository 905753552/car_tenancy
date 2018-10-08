package com.dev.main.tenancy.service.impl;

import com.dev.main.common.exception.CommonException;
import com.dev.main.common.util.ResultMap;
import com.dev.main.shiro.util.ShiroUtils;
import com.dev.main.tenancy.dao.*;
import com.dev.main.tenancy.domain.*;
import com.dev.main.tenancy.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *  * Description: main
 *  * Created by sf on 2018/9/25 8:37
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
    @Autowired
    private TncPointMapper tncPointMapper;
    @Autowired
    private TncPointLogMapper tncPointLogMapper;
    @Autowired
    private TncCarPicMapper tncCarPicMapper;
    @Autowired
    private TncCarItemMapper tncCarItemMapper;


    @Override
    public ResultMap selectByPrimaryKey(Long id) {
        TncCar tncCar = tncCarMapper.selectByPrimaryKey(id);
        String carPicPath = "/api/pic/item?imagePath=" + tncCarPicMapper.selectPathByCid(id);
        if(tncCar!=null){
            ResultMap resultMap = ResultMap.success("获取车辆信息成功");
            resultMap.put("car",tncCar);
            resultMap.put("path",carPicPath);
            return resultMap;
        }
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
            return ResultMap.success("修改成功");
        else
            throw new CommonException("修改失败");
    }

    @Override
    public ResultMap insertOrder(TncOrder tncOrder) {
        tncOrder.setGmtCreate(new Date());
        tncOrder.setGmtModified(new Date());
        tncOrder.setPayTime(null);
//        生成订单号
        SimpleDateFormat sdf =   new SimpleDateFormat( "HHmmss" );
        String phone = tncOrder.getPhone().substring(7);//电话号码后两位
        String date = sdf.format(new Date());//日期时分秒
        // String idcard = tncOrder.getCredentialsNumber().substring(10);//身份证后八位
        int ran = (int) (Math.random()*99);
        String str = phone + date + ran;
        Long uid = Long.valueOf(str);
        tncOrder.setId(uid);
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
        TncOrder tncOrder = tncOrderMapper.selectByPrimaryKey(id);
//      查看分配的车辆
        TncCarItem tncCarItem = tncOrderMapper.selectCarItemByPrimaryKey(tncOrder.getCarItemId());

            if(tncCarItem != null){
                //更新订单
                Byte status = 2;
                tncOrder.setPayTime(new Date());
                tncOrder.setStatus(status);
                int res = tncOrderMapper.updateByPrimaryKey(tncOrder);
                if(res>0){
//              车辆信息更新
                TncCar tncCar = tncCarMapper.selectByPrimaryKey(tncCarItem.getCarId());
                if (tncCar.getResidual()>0){
                    tncCar.setResidual(tncCar.getResidual()-1);
                    tncCarMapper.updateByPrimaryKeySelective(tncCar);
                }else{
                    throw new CommonException("该车辆库存为0");
                }
//              积分
                TncPoint tncPoint1 = tncPointMapper.selectByUserId(ShiroUtils.getUserId());
                Byte isDeleted = 0;
                if (tncPoint1 == null){
//                  积分+10
                    tncPoint1 = new TncPoint();
                    tncPoint1.setUid(ShiroUtils.getUserId());
                    tncPoint1.setPoint(tncOrder.getTotalAmount().intValue()/100);
                    tncPoint1.setIsDeleted(isDeleted);
                    tncPoint1.setGmtCreate(new Date());
                    tncPoint1.setGmtModified(new Date());
                    tncOrderMapper.insertPoint(tncPoint1);
                }else{
                    tncPoint1.setPoint(tncPoint1.getPoint()+tncOrder.getTotalAmount().intValue()/100);
                    tncPointMapper.updateByPrimaryKeySelective(tncPoint1);
                }
//              更新积分记录表
                TncPointLog tncPointLog = new TncPointLog();
                tncPointLog.setPid(tncPoint1.getId());
                tncPointLog.setChange(+(tncOrder.getTotalAmount().intValue()/100));
                tncPointLog.setResource("完成订单");
                tncPointLog.setGmtCreate(new Date());
                tncPointLog.setGmtModified(new Date());
                tncPointLog.setIsDeleted(isDeleted);
                tncOrderMapper.insertPointLog(tncPointLog);
                //返回数据
                ResultMap resultMap = ResultMap.success("支付订单成功");
                resultMap.put("order",tncOrder);
                resultMap.put("carItem",tncCarItem);
                return resultMap;
                }else{
                    throw new CommonException("支付订单失败");
                }

        }else{
            throw new CommonException("无可租车辆");
        }
    }

    @Override
    public ResultMap cancelOrder(Long id) {
        Byte status ;
        TncOrder tncOrder = tncOrderMapper.selectByPrimaryKey(id);
        if(tncOrder.getStatus() == 2)
            status = 5;
        else
            status = 3;
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

    @Override
    public ResultMap updateOrder(TncOrder tncOrder) {
        int res = tncOrderMapper.updateByPrimaryKeySelective(tncOrder);
        if(res>0){
            TncOrder tncOrder1 = tncOrderMapper.selectByPrimaryKey(tncOrder.getId());
            return ResultMap.success("获取订单成功").put("order",tncOrder1);
        }else{
            throw new CommonException("获取订单失败");
        }
    }

    @Override
    public ResultMap setCarItemByOid(Long car_id,Long order_id) {
        List<TncCarItem> list = tncOrderMapper.selectCarItemBycid(car_id);
        if(list.size()>0){
            for (TncCarItem tncCarItem:list) {
//                判断车辆是否可租用
                if (tncCarItem.getStatus() == 0){
                    Byte status = 1;
                    tncCarItem.setStatus(status);
                    tncCarItemMapper.updateByPrimaryKeySelective(tncCarItem);
                    TncOrder tncOrder = tncOrderMapper.selectByPrimaryKey(order_id);
                    tncOrder.setCarItemId(tncCarItem.getId());
                    tncOrderMapper.updateByPrimaryKeySelective(tncOrder);
                    return ResultMap.success("获取车辆成功").put("carItem",tncCarItem);
                }
            }
            throw new CommonException("该车辆无可租用");
        }else{
            throw new CommonException("获取车辆失败");
        }
    }
    @Override
    public ResultMap getCarItemByCarid(Long id) {
        List<TncCarItem> list = tncOrderMapper.selectCarItemBycid(id);
        if(list.size()>0){
            for (TncCarItem tncCarItem:list) {
//                判断车辆是否可租用
                if (tncCarItem.getStatus() == 0){
//                    Byte status = 1;
//                    tncCarItem.setStatus(status);
//                    tncCarItemMapper.updateByPrimaryKeySelective(tncCarItem);
                    return ResultMap.success("获取车辆成功").put("carItem",tncCarItem);
                }
            }
            throw new CommonException("该车辆无可租用");
        }else{
            throw new CommonException("获取车辆失败");
        }
    }
}