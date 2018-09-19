package com.dev.main.shiro.realm;

import com.dev.main.tenancy.domain.TncCustomer;
import com.dev.main.tenancy.service.ICustomerService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.security.auth.login.AccountLockedException;
import java.util.HashSet;
import java.util.Set;

public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    private ICustomerService customerService;

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
    }

    public void setCustomerService(ICustomerService customerService) {
        this.customerService = customerService;
    }
}
