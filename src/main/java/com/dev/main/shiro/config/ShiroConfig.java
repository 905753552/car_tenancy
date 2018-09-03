package com.dev.main.shiro.config;

import com.dev.main.shiro.realm.ExampleRealm;

import com.dev.main.shiro.redis.JedisCacheManager;
import com.dev.main.shiro.redis.RedisSessionDao;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class ShiroConfig {

    @Bean
    public ExampleRealm exampleRealm() {
        return new ExampleRealm();
    }

    @Bean
    public ShiroFilterFactoryBean createShiroFilter(SecurityManager securityManager) {
        System.out.println("--------ShiroFilterFactoryBean-------");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filterMap = new HashMap<>();
        //map里面key值要为authc才能使用自定义的过滤器
        //filterMap.put("authc", formValid());

        // can go to login
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        //doLogin success go to page
        shiroFilterFactoryBean.setSuccessUrl("/success.html");
        //do not Unauthorized page
        shiroFilterFactoryBean.setUnauthorizedUrl("/403.html");
        Map<String, String> map = new LinkedHashMap<String, String>();
        //验证码的路径   不要跟下面需要认证的写在一个路径里  会被拦截的
        //map.put("/servlet/**", "anon");
        //需要把要授权的URL  全部装到filterChain中去过滤
        /*UserInfo userInfo = userReposisty.findByUid(1);
        for (Role role : userInfo.getRoles()) {
            for (Permission permission : role.getPermissions()) {
                if (permission.getUrl() != "") {
                    String permissions = "perms[" + permission.getPermission() + "]";
                    map.put(permission.getUrl(), permissions);
                }
            }
        }*/
        //map.put("/user*/*", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        shiroFilterFactoryBean.setFilters(filterMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(exampleRealm());
        //缓存管理
        securityManager.setCacheManager(jedisCacheManager());
        //会话管理
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    //密码盐   可以不必实现    因为一般密码可以自己定义自己的密码加密规则
/*    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }*/

    //开启aop注解
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean(name = "simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver
    createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.setProperty("DatabaseException", "databaseError");//数据库异常处理
        mappings.setProperty("UnauthorizedException", "403");
        r.setExceptionMappings(mappings);  // None by default
        r.setDefaultErrorView("error");    // No default
        r.setExceptionAttribute("ex");     // Default is "exception"
        //r.setWarnLogCategory("example.MvcLogger");     // No default
        return r;
    }

    //jedis缓存
    @Bean
    public JedisCacheManager jedisCacheManager() {
        return new JedisCacheManager();
    }

    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setSessionIdCookie(simpleCookie());
        defaultWebSessionManager.setSessionDAO(sessionDAO());
        //可以设置shiro提供的会话管理机制
        //defaultWebSessionManager.setSessionDAO(new EnterpriseCacheSessionDAO());
        return defaultWebSessionManager;
    }



    //这里就是会话管理的操作类
    @Bean
    public SessionDAO sessionDAO() {
        return new RedisSessionDao();
    }

    //这里需要设置一个cookie的名称  原因就是会跟原来的session的id值重复的
    @Bean
    public SimpleCookie simpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("REDISSESSION");
        return simpleCookie;
    }


}
