package com.dev.main.tenancy.controller;

import com.dev.main.common.controller.exception.GlobalExceptionResolver;
import com.dev.main.common.util.ResultMap;
import com.dev.main.shiro.util.ShiroUtils;
import com.dev.main.tenancy.dao.TncCarMapper;
import com.dev.main.tenancy.domain.TncCar;
import com.dev.main.tenancy.domain.TncCustomer;
import com.dev.main.tenancy.domain.TncOrder;
import com.dev.main.tenancy.service.IOrderService;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  * Description: main
 *  * Created by sf on 2018/9/18 9:08
 *  
 */
//@GlobalExceptionResolver
@RestController
@RequestMapping("/api/order")
@GlobalExceptionResolver
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @GetMapping("/car/{cid}")
    @RequiresUser
    public ResultMap selectCar(@PathVariable("cid") Long id){
        ResultMap resultMap = orderService.selectByPrimaryKey(id);
        return resultMap;
    }

    @GetMapping("/pSchemeAndPName/{pid}")
    @RequiresUser
    public ResultMap selectPSchemeAndPName(@PathVariable("pid") Long id){
        ResultMap resultMap = orderService.selectPriceSchemeByPrimaryKey(id);
        return resultMap;
    }
    @GetMapping("/storeAddress/{sid}")
    @RequiresUser
    public ResultMap selectStoreAddress(@PathVariable("sid") Long id){
        ResultMap resultMap = orderService.selectAddressByStoreId(id);
        return resultMap;
    }
    @GetMapping("/customer")
    @RequiresUser
    public ResultMap getUserCoupons(){
        ResultMap resultMap = orderService.getCustomerCoupons();
        return resultMap;
    }
    @GetMapping("/updCusInfo")
    @RequiresUser
    public ResultMap updateCustomerInfo(TncCustomer tncCustomer){
        return orderService.updateCustomerInfo(tncCustomer);
    }
    @GetMapping("/submitOrder")
    @RequiresUser
    public ResultMap beginReserve(TncOrder tncOrder){
        if(tncOrder.getId()==null){
            return orderService.insertOrder(tncOrder);
        }else {
            return orderService.updateOrder(tncOrder);
        }
    }
    @GetMapping("/pay/{oid}")
    @RequiresUser
    public ResultMap savePay(@PathVariable("oid") Long id){
        return orderService.savePay(id);
    }
    @GetMapping("/cancel/{oid}")
    @RequiresUser
    public ResultMap cancelOrder(@PathVariable("oid") Long id){
        return orderService.cancelOrder(id);
    }
    @GetMapping("/getOrder/{oid}")
    @RequiresUser
    public ResultMap getOrder(@PathVariable("oid") Long id){
        return orderService.getOrder(id);
    }

}
