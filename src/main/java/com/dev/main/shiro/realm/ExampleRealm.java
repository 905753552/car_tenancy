package com.dev.main.shiro.realm;

import com.dev.main.shiro.domain.SysUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExampleRealm extends AuthorizingRealm {

    public ExampleRealm() {
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
        UsernamePasswordToken account = (UsernamePasswordToken)token;
        String username = ((UsernamePasswordToken) token).getUsername();

        /*User user = exampleService.findByUsername(username);
        if (user == null) {
            throw new UnknownAccountException();
        }
        String password = user.getPassword();*/
        // String saltStr = "example";
        String password = "example";
        // 盐值
        //ByteSource salt = ByteSource.Util.bytes(saltStr);
        SysUser user = new SysUser();
        user.setId("12542");
        user.setUsername("example");
        user.setSalt("dsfsdf");
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }
}
