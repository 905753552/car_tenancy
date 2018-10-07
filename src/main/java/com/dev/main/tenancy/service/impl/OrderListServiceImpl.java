package com.dev.main.tenancy.service.impl;

import com.dev.main.common.util.ResultMap;
import com.dev.main.tenancy.dao.*;
import com.dev.main.tenancy.domain.TncCar;
import com.dev.main.tenancy.domain.TncCarItem;
import com.dev.main.tenancy.domain.TncOrder;
import com.dev.main.tenancy.domain.TncPriceScheme;
import com.dev.main.tenancy.service.IOrderListService;
import com.dev.main.tenancy.vo.TncOrderData;
import com.dev.main.tenancy.vo.TncOrderListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public ResultMap getOrderList(String phone) {
        List<TncOrderListVo> list = tncOrderListMapper.selectOrderList("13824865025");
//        for (TncOrderListVo vo: list) {
//            System.out.println(vo.toString());
//        }
        return ResultMap.success("获取订单成功").put("order",list);
    }

    @Override
    public ResultMap getDetail(Long id) {
        TncOrderListVo base = tncOrderListMapper.selectOrderDetail(id);
        TncOrder ord = tncOrderMapper.selectByPrimaryKey(id);
        TncCarItem cari = tncCarItemMapper.selectByPrimaryKey(ord.getCarItemId());
        TncCar car = tncCarMapper.selectByPrimaryKey(cari.getCarId());
        TncPriceScheme price = tncOrderListMapper.selectPrice(cari.getCarId());
        //System.out.println(car);
        ResultMap rs = new ResultMap();
        rs.put("base",base);rs.put("order",ord);rs.put("price",price);
        return rs;
    }

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
