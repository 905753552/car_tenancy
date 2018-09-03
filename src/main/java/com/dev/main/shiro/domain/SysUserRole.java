package com.dev.main.shiro.domain;

public class SysUserRole extends BaseDomain {
    // 用户ID
    private Long uid;

    // 角色ID
    private Long rid;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }
}