package com.dev.main.shiro.util;

import java.io.Serializable;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UserNamePasswordPhoneToken extends UsernamePasswordToken implements Serializable {

    private static final long serialVersionUID = 4812793519945855483L;

    // 手机号码
    private String phone;

    /**
     * 重写getPrincipal方法
     */
    @Override
    public Object getPrincipal() {
        if (phone == null) {
            return getUsername();
        } else {
            return getPhone();
        }
    }

    /**
     * 重写getCredentials方法
     */
    @Override
    public Object getCredentials() {
        // 返回不需要密码，返回ok
        if (phone == null) {
            return getPassword();
        } else {
            return "ok";
        }
    }

    public UserNamePasswordPhoneToken() {
    }

    public UserNamePasswordPhoneToken(final String phone) {
        this.phone = phone;
    }

    public UserNamePasswordPhoneToken(final String userName, final String password) {
        super(userName, password);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "phone [phone=" + phone + "]";
    }

}
