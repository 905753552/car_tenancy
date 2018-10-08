package com.dev.main.tenancy.controller;

import com.dev.main.common.controller.exception.GlobalExceptionResolver;
import com.dev.main.common.util.ResultMap;
import com.dev.main.shiro.util.ShiroUtils;
import com.dev.main.tenancy.domain.TncCustomer;
import com.dev.main.tenancy.service.IOrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orderList")
@GlobalExceptionResolver
public class OrderListController {

    @Autowired
    private IOrderListService orderListService ;
    @GetMapping("/getOrderList")
    public ResultMap getOrderList(){
        TncCustomer tncCustomer = ShiroUtils.getUserEntity();
        return orderListService.getOrderList(tncCustomer.getPhone());
    }
    @GetMapping("/getOrderData")
    public ResultMap getOrderData(Long id){
        return orderListService.getOrderData(id);
    }
    /*
    获取myorderlist_detail页面的数据(已不用)
     */
    @GetMapping("/detail")
    public ResultMap detail(@RequestParam Long id){
        System.out.println(id);
        return orderListService.getDetail(id);
    }
    /*
    传递凌兴的参数（已不用）
     */
    @GetMapping("/orderData")
    public ResultMap orderData(@RequestParam Long id){
        System.out.println(id);
        return orderListService.getOrderDetail(id);
    }
}
