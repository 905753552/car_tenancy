package com.dev.main.shiro.realm;


import com.dev.main.aliyun_sms.service.ISMSService;
import com.dev.main.aliyun_sms.statics.SmsConstant;
import com.dev.main.common.exception.CommonException;
import com.dev.main.common.util.CookieUtils;
import com.dev.main.tenancy.domain.TncCustomer;
import com.dev.main.tenancy.service.ICustomerService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PhoneRealm extends AuthorizingRealm {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ISMSService smsService;

    public PhoneRealm() {
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse resp = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        String keyInCookie = CookieUtils.findCookieByName(req, SmsConstant.COOKIE_NAME);
        String phone_codeInput = (String) token.getPrincipal();
        String[] strs = StringUtils.split(phone_codeInput, "_"); // 传进来的手机号与验证码使用"_"链接
        String phone = strs[0];
        String codeInput = strs[1];
        TncCustomer customer = customerService.findByPhone(phone);
        customer = new TncCustomer();

        // 验证验证码
        boolean ok = false;
        try {
            ok = smsService.verifyCode(keyInCookie, phone, codeInput, req, resp);
        } catch (CommonException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(e.getMessage());
        }
        if (!ok) {
            throw new CommonException("验证码错误");
        }
        AuthenticationInfo info = new SimpleAuthenticationInfo(customer, "ok", getName());
        return info;
    }

    public void setCustomerService(ICustomerService customerService) {
        this.customerService = customerService;
    }

    public void setSmsService(ISMSService smsService) {
        this.smsService = smsService;
    }
}
