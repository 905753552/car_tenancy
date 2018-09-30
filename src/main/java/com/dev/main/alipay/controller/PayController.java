package com.dev.main.alipay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.dev.main.alipay.config.AliPayConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class PayController {
    @RequestMapping("/myPay")
    @ResponseBody
    public String al(@RequestBody String orderData){
        System.out.println(orderData);
        //String orderId,String orderName,String orderAmount,String orderDes
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfig.gatewayUrl,AliPayConfig.app_id, AliPayConfig.merchant_private_key, "json", AliPayConfig.charset, AliPayConfig.alipay_public_key, AliPayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AliPayConfig.return_url);
        alipayRequest.setNotifyUrl(AliPayConfig.notify_url);
        alipayRequest.setBizContent(orderData.toString());
//        alipayRequest.setBizContent("{\"out_trade_no\":\""+ orderId +"\","
//                + "\"total_amount\":\""+ orderAmount +"\","
//                + "\"subject\":\""+ orderName +"\","
//                + "\"body\":\""+ orderDes +"\","
//                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String result="";

            try {
                result = alipayClient.pageExecute(alipayRequest).getBody();
            } catch (AlipayApiException e) {
                e.printStackTrace();
            }

        System.out.println(result);
        return result;
    }
}
