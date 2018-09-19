package com.dev.main.aliyun_sms.service.impl;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.dev.main.aliyun_sms.component.AliSmsCodeSender;
import com.dev.main.aliyun_sms.service.ISMSService;
import com.dev.main.aliyun_sms.statics.SmsConstant;
import com.dev.main.common.exception.CommonException;
import com.dev.main.common.util.CookieUtils;
import com.dev.main.common.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class SMSServiceImpl implements ISMSService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private AliSmsCodeSender smsSender;

    @Override
    public void sendVerifyCode(String phone, HttpServletRequest request, HttpServletResponse response) throws ClientException, InterruptedException  {
        if (StringUtils.isBlank(phone)) {
            throw new CommonException("请填写手机号");
        }
        // 生成6位数字验证码
        String code = RandomUtil.getRandomNumString(6);
        // 存在缓存中的值
        Map<String, String> data = new HashMap();
        data.put(SmsConstant.PHONE, phone);
        data.put(SmsConstant.CODE, code);
        //发短信
        SendSmsResponse smsResponse = smsSender.sendSms(phone, code);
        // 把验证码存放在缓存中
        Integer cookieExp = 60 * 100; // 过期时间 10 分钟
        String key = SmsConstant.SMS_CODE_PREFIX + phone;
        // 把数据存放到缓存中
        redisTemplate.opsForValue().set(key, data, cookieExp, TimeUnit.SECONDS);
        // 把验证码的key存在Cookie中，验证时使用
        CookieUtils.addCookie(response, SmsConstant.COOKIE_NAME, key, cookieExp, SmsConstant.COOKIE_PATH);
        System.out.println("短信接口返回的数据----------------");
        System.out.println("Code=" + smsResponse.getCode());
        System.out.println("Message=" + smsResponse.getMessage());
        System.out.println("RequestId=" + smsResponse.getRequestId());
        System.out.println("BizId=" + smsResponse.getBizId());

        Thread.sleep(3000L);

        //查明细
        if(smsResponse.getCode() != null && smsResponse.getCode().equals("OK")) {
            QuerySendDetailsResponse querySendDetailsResponse = smsSender.querySendDetails(smsResponse.getBizId());
            System.out.println("短信明细查询接口返回数据----------------");
            System.out.println("Code=" + querySendDetailsResponse.getCode());
            System.out.println("Message=" + querySendDetailsResponse.getMessage());
            int i = 0;
            for(QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse.getSmsSendDetailDTOs())
            {
                System.out.println("SmsSendDetailDTO["+i+"]:");
                System.out.println("Content=" + smsSendDetailDTO.getContent());
                System.out.println("ErrCode=" + smsSendDetailDTO.getErrCode());
                System.out.println("OutId=" + smsSendDetailDTO.getOutId());
                System.out.println("PhoneNum=" + smsSendDetailDTO.getPhoneNum());
                System.out.println("ReceiveDate=" + smsSendDetailDTO.getReceiveDate());
                System.out.println("SendDate=" + smsSendDetailDTO.getSendDate());
                System.out.println("SendStatus=" + smsSendDetailDTO.getSendStatus());
                System.out.println("Template=" + smsSendDetailDTO.getTemplateCode());
            }
            System.out.println("TotalCount=" + querySendDetailsResponse.getTotalCount());
            System.out.println("RequestId=" + querySendDetailsResponse.getRequestId());
        }
       System.out.println(phone + "_" + code);

    }

    @Override
    public boolean verifyCode(String keyInCookie, String phone, String codeInput, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isBlank(keyInCookie)) {
            throw new CommonException("验证码已失效，请重新获取");
        }
        if (StringUtils.isBlank(codeInput)) {
            throw new CommonException("请输入验证码");
        }
        Map<String, String> dataInCache = (HashMap<String, String>) redisTemplate.opsForValue().get(keyInCookie);
        String phoneInCache = dataInCache.get(SmsConstant.PHONE);  // 获取缓存中的手机号
        String codeInCache = dataInCache.get(SmsConstant.CODE); // 获取缓存中的验证码
        System.out.println("phoneInCache:" + phoneInCache);
        System.out.println("codeInCache:" + codeInCache);
        System.out.println("codeInput:" + codeInput);
        if (dataInCache == null) {
            throw new CommonException("验证码已失效，请重新获取");
        }
        System.out.println(phoneInCache);
        System.out.println(phone);
        System.out.println(phone.equals(phoneInCache));
        // 手机号不匹配
        if (!phone.equalsIgnoreCase(phoneInCache)) {
            throw new CommonException("验证的手机号与接收验证码的手机号不一致");
        }
        // 验证码不正确
        if (!codeInput.equalsIgnoreCase(codeInCache)) {
            return false;
        }
        CookieUtils.clearCookie(request, response,  SmsConstant.COOKIE_NAME,  SmsConstant.COOKIE_PATH);
        redisTemplate.delete(keyInCookie);
        return true;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setSmsSender(AliSmsCodeSender smsSender) {
        this.smsSender = smsSender;
    }
}
