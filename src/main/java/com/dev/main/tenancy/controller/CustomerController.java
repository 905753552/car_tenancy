package com.dev.main.tenancy.controller;

import com.dev.main.aliyun_sms.statics.SmsConstant;
import com.dev.main.common.controller.exception.GlobalExceptionResolver;
import com.dev.main.common.util.ResultMap;
import com.dev.main.shiro.util.ShiroUtils;
import com.dev.main.tenancy.domain.*;
import com.dev.main.tenancy.service.ICustomerService;
import com.dev.main.tenancy.vo.TncCustomerVo;
import org.apache.shiro.subject.Subject;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
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
    public ResultMap change(@RequestBody TncCustomerVo tncCustomerVo,
                            @CookieValue(value = SmsConstant.COOKIE_NAME, required = false) String keyInCookie,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        customerService.changeInfo(tncCustomerVo,keyInCookie,request,response);

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

    @GetMapping("/findMallCoupon")
    public ResultMap findMallCoupon() {
        List<TncCouponPoint> tncCouponPointList = customerService.findMallCoupon();
        ResultMap result = new ResultMap();
        result.put("coupons", tncCouponPointList);
        return result;
    }

    @GetMapping("/countCouponAndPoint")
    public ResultMap countCouponAndPoint() {
        TncCustomer tncCustomer = ShiroUtils.getUserEntity();
        int count = customerService.countUnuse(tncCustomer.getId());
        TncPoint tncPoint = customerService.findUserPointById(tncCustomer.getId());
        int point = 0;
        Long pid = null;
        if(tncPoint!=null) {
            point = tncPoint.getPoint();
            pid = tncPoint.getId();
        }
        ResultMap result = new ResultMap();
        result.put("count", count);
        result.put("point", point);
        result.put("pid", pid);
        return result;
    }
    /***
     * @param  usablePoint 用户目前积分
     * @param  pointValue 积分变动，这里要转为负值
     * @param  amount 优惠券面值
     * @param  pid 积分表id
     */
    @GetMapping("/exchange")
    public ResultMap exchange(int usablePoint, Long pid, int pointValue, BigDecimal amount) {
        TncCustomer tncCustomer = ShiroUtils.getUserEntity();
        Long uid = tncCustomer.getId();
        usablePoint = usablePoint - pointValue;
        pointValue = 0 - pointValue;
        customerService.pointExchangCoupon(amount, pid, usablePoint, pointValue, uid);
        return ResultMap.success();
    }

    @GetMapping("/getPointLog")
    public ResultMap getPointLog() {
        TncCustomer tncCustomer = ShiroUtils.getUserEntity();
        int count = customerService.countUnuse(tncCustomer.getId());
        TncPoint tncPoint = customerService.findUserPointById(tncCustomer.getId());
        Long pid = tncPoint.getId();
        ResultMap result = new ResultMap();
        List<TncPointLog> tncPointLogs = customerService.getPointLogByPid(pid);
        result.put("pointLogs", tncPointLogs);
        return result;
    }
}
