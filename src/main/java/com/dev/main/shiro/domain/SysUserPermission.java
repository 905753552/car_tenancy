package com.dev.main.shiro.domain;

import com.dev.main.common.domain.BaseDomain;

public class SysUserPermission extends BaseDomain {
    // 用户ID
    private Long uid;

    // 权限ID
    private Long pid;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }
}