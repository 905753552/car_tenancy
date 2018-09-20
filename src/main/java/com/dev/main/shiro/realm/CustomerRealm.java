package com.dev.main.shiro.realm;

import com.dev.main.aliyun_sms.service.ISMSService;
import com.dev.main.aliyun_sms.statics.SmsConstant;
import com.dev.main.common.exception.CommonException;
import com.dev.main.common.util.CookieUtils;
import com.dev.main.common.util.CryptographyUtil;
import com.dev.main.common.util.MD5Util;
import com.dev.main.shiro.util.ShiroConstant;
import com.dev.main.tenancy.domain.TncCustomer;
import com.dev.main.tenancy.service.ICustomerService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.security.auth.login.AccountLockedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ISMSService smsService;

    public CustomerRealm() {
    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 获取权限
        // List<Authority> authorities = exampleService.findAuthorityByUsername(username);
        Set<String> permissionSets = new HashSet<>();
        // 添加权限
        /*for (Authority perm: authorities) {
            permissionSets.add(perm.getAuthorName());
        }*/
        info.setStringPermissions(permissionSets);

        // 获取角色
        // List<Role> roles = exampleService.findRolesByUsername(username);
        Set<String> rolenames = new HashSet<>();
        // 添加角色
        /*for (Role role : roles) {
            rolenames.add(role.getRoleName());
        }*/
        info.addRoles(rolenames);

        return info;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse resp = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        String loginType = req.getParameter("loginType");
        if (ShiroConstant.PASSWORD.equalsIgnoreCase(loginType)) { // 密码登录
            UsernamePasswordToken account = (UsernamePasswordToken) token;
            String username = ((UsernamePasswordToken) token).getUsername();
            TncCustomer customer = customerService.findByPhone(username);
            //账号不存在
            if (customer == null) {
                throw new UnknownAccountException("账号或密码不正确");
            }
            //账号锁定
            if (customer.getStatus() == 0) {
                throw new LockedAccountException("账号不可用");
            }
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(customer, customer.getPassword(), ByteSource.Util.bytes(customer.getSalt()), getName());
            return info;

        } else { // 短信验证码登录
            String keyInCookie = CookieUtils.findCookieByName(req, SmsConstant.COOKIE_NAME);
            String phone = (String) token.getPrincipal();
            String[] strs = StringUtils.split(phone); // 传进来的手机号与验证码使用"_"链接
            String codeInput = req.getParameter("code");
            TncCustomer customer = customerService.findByPhone(phone);
            customer = new TncCustomer();
            // 验证验证码
            boolean ok = smsService.verifyCode(keyInCookie, phone, codeInput, req, resp);
            //账号不存在
            if (customer == null) {
                throw new UnknownAccountException("账号或密码不正确");
            }
            //账号锁定
            if (customer.getStatus() == 0) {
                throw new LockedAccountException("账号不可用");
            }
            AuthenticationInfo info = new SimpleAuthenticationInfo(customer, MD5Util.encodeByMD5("ok"), getName());
            return info;
        }

    }

    public void setCustomerService(ICustomerService customerService) {
        this.customerService = customerService;
    }

    public void setSmsService(ISMSService smsService) {
        this.smsService = smsService;
    }
}
