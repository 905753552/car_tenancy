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
    @Autowired
    private TncCouponMapper tncCouponMapper;


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
        if (tncCustomer != null ){
            ResultMap resultMap = ResultMap.success("获取顾客优惠券成功");
            resultMap.put("list",list);
            resultMap.put("customer",tncCustomer);
            return resultMap;
        }else{
            throw new CommonException("对不去！您还没登录");
        }
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
        int num = tncOrderMapper.insertSelective(tncOrder);
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

                //更新订单
                Byte status = 2;
                tncOrder.setPayTime(new Date());
                tncOrder.setStatus(status);
                int res = tncOrderMapper.updateByPrimaryKey(tncOrder);
                if(res>0){
//                    优惠券
                    if (tncOrder.getCouponId() != null){
                        Byte status1 = 1;
                        TncCoupon tncCoupon = tncCouponMapper.selectByPrimaryKey(tncOrder.getCouponId());
                        tncCoupon.setStatus(status1);
                        tncCouponMapper.updateByPrimaryKeySelective(tncCoupon);
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
                    int point = tncOrderMapper.insertPoint(tncPoint1);
                    if (point <= 0){
                        throw new CommonException("创建积分失败");
                    }
                }else{
                    tncPoint1.setPoint(tncPoint1.getPoint()+tncOrder.getTotalAmount().intValue()/100);
                    int point = tncPointMapper.updateByPrimaryKeySelective(tncPoint1);
                    if (point <= 0){
                        throw new CommonException("更新积分失败");
                    }
                }
//              更新积分记录表
                TncPointLog tncPointLog = new TncPointLog();
                tncPointLog.setPid(tncPoint1.getId());
                tncPointLog.setChange(+(tncOrder.getTotalAmount().intValue()/100));
                tncPointLog.setResource("消费 "+tncOrder.getTotalAmount()+" 元");
                tncPointLog.setGmtCreate(new Date());
                tncPointLog.setGmtModified(new Date());
                tncPointLog.setIsDeleted(isDeleted);
                int point = tncOrderMapper.insertPointLog(tncPointLog);
                if (point <= 0){
                    throw new CommonException("更新积分记录失败");
                }
                //返回数据
                ResultMap resultMap = ResultMap.success("支付订单成功");
                resultMap.put("order",tncOrder);
                resultMap.put("carItem",tncCarItemMapper.selectByPrimaryKey(tncOrder.getCarItemId()));
                return resultMap;
                }else{
                    throw new CommonException("支付订单失败");
                }

    }

    @Override
    public ResultMap cancelOrder(Long id) {
        Byte status ;
        String msg;
        TncOrder tncOrder = tncOrderMapper.selectByPrimaryKey(id);
        if(tncOrder.getStatus() == 2){
            status = 5;
            msg = "已提交申请";
        }
        else{
            status = 3;
            msg = "取消订单成功";
        }
        tncOrder.setStatus(status);
        int res = tncOrderMapper.updateByPrimaryKey(tncOrder);
        if (res>0){
            return ResultMap.success(msg);
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
        tncOrder.setGmtModified(new Date());
        int res = tncOrderMapper.updateByPrimaryKeySelective(tncOrder);
        if(res>0){
            return ResultMap.success("修改订单成功").put("order",tncOrder);
        }else{
            throw new CommonException("修改订单失败");
        }
    }

    @Override
    public ResultMap setCarItemByOid(Long car_id,Long order_id) {
        Byte status = 1;
        TncOrder tncOrder = tncOrderMapper.selectByPrimaryKey(order_id);
        TncCarItem tncCarItem = tncCarItemMapper.selectByPrimaryKey(tncOrder.getCarItemId());
        if (tncCarItem.getStatus() == 0 && tncCarItem.getIsDeleted() == 0){
            tncCarItem.setStatus(status);
            tncCarItemMapper.updateByPrimaryKeySelective(tncCarItem);
            //              车辆信息更新
            TncCar tncCar = tncCarMapper.selectByPrimaryKey(car_id);
            if (tncCar.getResidual()>0){
                tncCar.setResidual(tncCar.getResidual()-1);
                tncCarMapper.updateByPrimaryKeySelective(tncCar);
            }else{
                throw new CommonException("该车辆库存为0");
            }
            return ResultMap.success("获取车辆成功");
        }else{
//            重新分配车辆
            List<TncCarItem> list = tncOrderMapper.selectCarItemBycid(car_id);
            if(list.size()>0){
                for (TncCarItem tncCarItem1:list) {
//                判断车辆是否可租用
                    if (tncCarItem1.getStatus() == 0 && tncCarItem1.getIsDeleted() == 0){
                        tncCarItem1.setStatus(status);
                        tncCarItemMapper.updateByPrimaryKeySelective(tncCarItem1);
                        TncOrder tncOrder1 = tncOrderMapper.selectByPrimaryKey(order_id);
                        tncOrder1.setCarItemId(tncCarItem1.getId());
                        tncOrderMapper.updateByPrimaryKeySelective(tncOrder1);
                        //              车辆信息更新
                        TncCar tncCar = tncCarMapper.selectByPrimaryKey(car_id);
                        if (tncCar.getResidual()>0){
                            tncCar.setResidual(tncCar.getResidual()-1);
                            tncCarMapper.updateByPrimaryKeySelective(tncCar);
                        }else{
                            throw new CommonException("该车辆库存为0");
                        }
                        return ResultMap.success("获取车辆成功");
                    }
                }
                throw new CommonException("该车辆无可租用");
            }else{
                throw new CommonException("获取车辆失败");
            }
        }
    }
    @Override
    public ResultMap getCarItemByCarid(Long id) {
        List<TncCarItem> list = tncOrderMapper.selectCarItemBycid(id);
        TncCar tncCar = tncCarMapper.selectByPrimaryKey(id);
        if (tncCar.getResidual()>0){
            if(list.size()>0){
                for (TncCarItem tncCarItem:list) {
//                判断车辆是否可租用
                    if (tncCarItem.getStatus() == 0 && tncCarItem.getIsDeleted() == 0){
                        return ResultMap.success("获取车辆成功").put("carItem",tncCarItem);
                    }
                }
                throw new CommonException("该车辆无可租用");
            }else{
                throw new CommonException("获取车辆失败");
            }
        }else{
            throw new CommonException("车辆库存为0");
        }
    }
}