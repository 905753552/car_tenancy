package com.dev.main.tenancy.domain;

import com.dev.main.common.domain.BaseDomain;

public class TncCarType extends BaseDomain {
    // 主键
    private Long id;

    // 名称
    private String name;

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
}