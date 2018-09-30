package com.dev.main.tenancy.controller;

import com.dev.main.common.controller.exception.GlobalExceptionResolver;
import com.dev.main.common.util.ResultMap;
import com.dev.main.shiro.util.ShiroUtils;
import com.dev.main.tenancy.domain.TncCoupon;
import com.dev.main.tenancy.domain.TncCustomer;
import com.dev.main.tenancy.domain.TncPoint;
import com.dev.main.tenancy.service.ICustomerService;
import com.dev.main.tenancy.vo.TncCustomerVo;
import org.apache.shiro.subject.Subject;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/customer")
@GlobalExceptionResolver
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/find")
    public ResultMap find() {
        ResultMap result = new ResultMap();
        TncCustomer tncCustomer = ShiroUtils.getUserEntity();
        TncCustomerVo tncCustomerVo = customerService.findCustomerVo(tncCustomer.getId());
        result.put("data", tncCustomerVo);
        return result;
    }

    @GetMapping("/address")
    public ResultMap address(Long aid, byte level) {
        ResultMap result = new ResultMap();
        result.put("data", customerService.findAddress(aid, level));
        return  result;
    }

    @PostMapping("/change")
    public ResultMap change(@RequestBody TncCustomerVo tncCustomerVo) {
        customerService.changeInfo(tncCustomerVo);
        return ResultMap.success();
    }

    @PostMapping("/changePwd")
    public ResultMap changePwd(@RequestBody Map<String, String> data) {
        TncCustomer tncCustomer1 = ShiroUtils.getUserEntity();
        data.put("uid", tncCustomer1.getId().toString());
        customerService.changePwd(data);
        return ResultMap.success();
    }

    @GetMapping("/findCoupon")
    public ResultMap findCoupon() {
        TncCustomer tncCustomer = ShiroUtils.getUserEntity();
        List<TncCoupon> tncCouponList = customerService.findCouponByUid(tncCustomer.getId());
        ResultMap result = new ResultMap();
        result.put("coupons", tncCouponList);
        return result;
    }

    @GetMapping("/countCouponAndPoint")
    public ResultMap countCouponAndPoint() {
        TncCustomer tncCustomer = ShiroUtils.getUserEntity();
        int count = customerService.countUnuse(tncCustomer.getId());
        TncPoint tncPoint = customerService.findUserPointById(tncCustomer.getId());
        int point = 0;
        if(tncPoint!=null) {
            point = tncPoint.getPoint();
        }
        ResultMap result = new ResultMap();
        result.put("count", count);
        result.put("point", point);
        return result;
    }
}
