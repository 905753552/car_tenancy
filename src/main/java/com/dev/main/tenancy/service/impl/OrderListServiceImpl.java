package com.dev.main.tenancy.service.impl;

import com.dev.main.common.util.ResultMap;
import com.dev.main.tenancy.dao.TncOrderListMapper;
import com.dev.main.tenancy.service.IOrderListService;
import com.dev.main.tenancy.vo.TncOrderListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderListServiceImpl implements IOrderListService {
    @Autowired
    private TncOrderListMapper tncOrderListMapper;

    @Override
    public ResultMap getOrderList(String phone) {
        List<TncOrderListVo> list = tncOrderListMapper.selectOrderList("13824865025");
        for (TncOrderListVo vo: list) {
            System.out.println(vo.toString());
        }
        return ResultMap.success("获取订单成功").put("order",list);
    }
}
