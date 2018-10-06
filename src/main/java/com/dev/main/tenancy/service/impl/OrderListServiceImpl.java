package com.dev.main.tenancy.service.impl;

import com.dev.main.common.util.ResultMap;
import com.dev.main.tenancy.dao.*;
import com.dev.main.tenancy.domain.TncCar;
import com.dev.main.tenancy.domain.TncCarItem;
import com.dev.main.tenancy.domain.TncOrder;
import com.dev.main.tenancy.domain.TncPriceScheme;
import com.dev.main.tenancy.service.IOrderListService;
import com.dev.main.tenancy.vo.TncOrderListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public ResultMap getOrderList(String phone) {
        List<TncOrderListVo> list = tncOrderListMapper.selectOrderList("13824865025");
        for (TncOrderListVo vo: list) {
            System.out.println(vo.toString());
        }
        return ResultMap.success("获取订单成功").put("order",list);
    }

    @Override
    public ResultMap getDetail(Long id) {
        TncOrderListVo base = tncOrderListMapper.selectOrderDetail(id);
        TncOrder ord = tncOrderMapper.selectByPrimaryKey(id);
        TncCarItem cari = tncCarItemMapper.selectByPrimaryKey(ord.getCarItemId());
        TncCar car = tncCarMapper.selectByPrimaryKey(cari.getCarId());
        TncPriceScheme price = tncOrderListMapper.selectPrice(cari.getCarId());
        System.out.println(car);
        ResultMap rs = new ResultMap();
        rs.put("base",base);rs.put("order",ord);rs.put("price",price);
        return rs;
    }
}
