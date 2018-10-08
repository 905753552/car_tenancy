package com.dev.main.tenancy.service.impl;

import com.dev.main.common.util.ResultMap;
import com.dev.main.tenancy.dao.*;
import com.dev.main.tenancy.domain.*;
import com.dev.main.tenancy.service.IOrderListService;
import com.dev.main.tenancy.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderListServiceImpl implements IOrderListService {
    @Autowired
    private TncOrderListMapper tncOrderListMapper;
    @Autowired
    private TncCarMapper tncCarMapper;
    @Autowired
    private TncCarItemMapper tncCarItemMapper;
    @Autowired
    private TncOrderMapper tncOrderMapper;
    @Autowired
    private TncPriceSchemeMapper tncPriceSchemeMapper;
    @Autowired
    private  TncPackageSchemeMapper tncPackageSchemeMapper;
    @Autowired
    private TncStoreMapper tncStoreMapper;
    @Autowired
    private TncCouponMapper tncCouponMapper;

    @Override
    public ResultMap getOrderList(String phone) {
        List<TncOrderListVo> list = tncOrderListMapper.selectOrderList(phone);
//        for (TncOrderListVo vo: list) {
//            System.out.println(vo.toString());
//        }
        return ResultMap.success("获取订单成功").put("order",list);
    }

    @Override
    public ResultMap getOrderData(Long id) {
        TncOrderDetailVo vo  = new TncOrderDetailVo();
        //获取订单对象
        TncOrder order = tncOrderMapper.selectByPrimaryKey(id);
        BigDecimal baseAmount = order.getBaseAmount();
        BigDecimal serviceAmount = order.getServiceAmount();
        vo.setTotal_base_price(baseAmount);
        vo.setTotal_service_price(serviceAmount);
        vo.setOther_cost(order.getOtherAmount());
        vo.setOrder_price_sum(order.getTotalAmount());
        vo.setOrder_detail(id);
        String descript = order.getDescription();
        vo.setDescription(descript);
        String descri = "异地还车费:200;维修费:1000;整备费:20;异店还车费:50";
        String[] des = descri.split(";");
        for(int i = 0;i < des.length;i++){
            String state = des[i].split(":")[0];
            String money = des[i].split(":")[1];
            if(state.trim().equals("异地还车费")) vo.setForeign_land_cost(Integer.parseInt(money));
            if(state.trim().equals("整备费")) vo.setPrepare_cost(Integer.parseInt(money));
            if(state.trim().equals("异店还车费")) vo.setForeign_store_cost(Integer.parseInt(money));
        }
        //获取优惠券面值
        TncCoupon tncCoupon = tncCouponMapper.selectByPrimaryKey(order.getCouponId());
        if(tncCoupon!=null) vo.setCoupon(tncCoupon.getAmount());
        else vo.setCoupon(null);
        //获取汽车对象
        Long carId = tncOrderListMapper.selectCarItem(order.getCarItemId()).getCarId();
        TncCar car = tncCarMapper.selectByPrimaryKey(carId);
        vo.setCar_info(car);
        //获取时间
        Date getdate = order.getStartDate();
        Date returndate = order.getReturnDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String gd = DateFormat.getDateInstance().format(getdate);
        String rd = DateFormat.getDateInstance().format(returndate);
        String gd1 = dateFormat.format(getdate);
        String rd1 = dateFormat.format(returndate);
        int days = (int) ((returndate.getTime() - getdate.getTime()) / (1000*3600*24));
        Long plus = (returndate.getTime() - getdate.getTime()) % (1000*3600*24);
        int hour = 0;
        if(plus>(1000*60*4)) days++;
        else {
            hour = (int) (plus / (1000*60)) + 1;
        }
        vo.setOvertime_count(hour);
        vo.setDays(days);
        vo.setGetDate(gd);
        vo.setGetTime(gd1);
        vo.setReturnDate(rd);
        vo.setReturnTime(rd1);
        //获取门店名，城市
        TncStoreVo getStore = tncOrderListMapper.selectStore(order.getGetStoreId());
        vo.setGetStore(getStore.getName());
        vo.setGetCity(getStore.getCity());
        TncStoreVo returnStore = tncOrderListMapper.selectStore(order.getReturnStoreId());
        vo.setReturnStore(returnStore.getName());
        vo.setReturnCity(returnStore.getCity());
        //获取价格方案
       // System.out.println("carid"+carId);
        TncPriceScheme tncPriceScheme = tncOrderListMapper.getPrice(carId);
        //System.out.println(tncPriceScheme);
        BigDecimal discount = tncPriceScheme.getDiscount();//折扣
        vo.setDiscount_total_base(discount.multiply(baseAmount));//折扣基础总价
        vo.setDiscount_total_service(discount.multiply(serviceAmount));//折扣基础服务费总价
        vo.setBase_price(tncPriceScheme.getBasePrice());//基础单价
        //System.out.println("baseprice"+tncPriceScheme.getBasePrice());
        vo.setService_price(tncPriceScheme.getServicePrice());//服务单价
       // System.out.println("serviceprice"+tncPriceScheme.getServicePrice());
        vo.setDeposit(tncPriceScheme.getDeposit());//押金

        BigDecimal hourprice = tncPriceScheme.getBaseHourPrice();//小时单价
        if(hour!=0&hourprice!=null) vo.setOvertime_cost(hourprice.multiply(new BigDecimal(hour)));
        //获取图片
        vo.setCarPicPath(tncOrderListMapper.selectCarItem(order.getCarItemId()).getPath());
        System.out.println(vo.toString());
        return ResultMap.success("获取成功").put("order_detail",vo);
    }

    /*
            获取myorderlist_detail页面的数据(已不用)
        */
    @Override
    public ResultMap getDetail(Long id) {
        TncOrderListVo base = tncOrderListMapper.selectOrderDetail(id);
        TncOrder ord = tncOrderMapper.selectByPrimaryKey(id);
        System.out.println(ord.toString());
        TncCarItem cari = tncCarItemMapper.selectByPrimaryKey(ord.getCarItemId());
        TncCar car = tncCarMapper.selectByPrimaryKey(cari.getCarId());
        TncPriceScheme price = tncOrderListMapper.selectPrice(cari.getCarId());
        //System.out.println(car);
        ResultMap rs = new ResultMap();
        rs.put("base",base);rs.put("order",ord);rs.put("price",price);
        return rs;
    }

    /*
    传递凌兴的参数（已不用）
     */
    @Override
    public ResultMap getOrderDetail(Long id) {
        TncOrder order = tncOrderMapper.selectByPrimaryKey(id);
        TncOrderData orderData = new TncOrderData();
        Long carId = tncCarItemMapper.selectByPrimaryKey(order.getCarItemId()).getCarId();
        orderData.setCarId(carId);
        TncPriceScheme priceScheme = tncOrderListMapper.selectPrice(carId);
        if(priceScheme!=null) {
            orderData.setPriceId(priceScheme.getId());
            Long packageid = tncPackageSchemeMapper.selectByPrimaryKey(priceScheme.getPackageId()).getId();
            orderData.setPackageId(packageid);
        }
        orderData.setGetCarPlaceId(order.getGetStoreId());
        orderData.setReturnCarPlaceId(order.getReturnStoreId());
        orderData.setGetCarPlace(tncStoreMapper.selectByPrimaryKey(order.getGetStoreId()).getName());
        orderData.setReturnCarPlace(tncStoreMapper.selectByPrimaryKey(order.getReturnStoreId()).getName());
        //System.out.println(orderData);
        Date getdate = order.getStartDate();
        Date returndate = order.getReturnDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String gd = DateFormat.getDateInstance().format(getdate);
        String rd = DateFormat.getDateInstance().format(returndate);
        String gd1 = dateFormat.format(getdate);
        String rd1 = dateFormat.format(returndate);
        //System.out.println(gd);System.out.println(gd1);System.out.println(rd);System.out.println(rd1);
        int days = (int) ((returndate.getTime() - getdate.getTime()) / (1000*3600*24));
        Long plus = (returndate.getTime() - getdate.getTime()) % (1000*3600*24);
        //System.out.println(plus);
        if(plus>(1000*60*4)) days++;
        //System.out.println(days);
        orderData.setDays(days);
        orderData.setGetCarDate(gd);
        orderData.setGetCarTime(gd1);
        orderData.setReturnCarDate(rd);
        orderData.setReturnCarTime(rd1);
        //System.out.println(orderData);
        return ResultMap.success("ok").put("orderData",orderData);
    }
}
