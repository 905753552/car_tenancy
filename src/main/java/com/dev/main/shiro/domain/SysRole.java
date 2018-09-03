package com.dev.main.shiro.domain;

public class SysRole extends BaseDomain {
    // 主键
    private Long id;

    // 角色名称
    private String name;

    // 描述
    private String description;

    // 状态：1-可用，0-禁用
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}