package com.dev.main.shiro.domain;

public class SysRolePermission extends BaseDomain {
    // 角色ID
    private Long rid;

    // 权限ID
    private Long pid;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }
}