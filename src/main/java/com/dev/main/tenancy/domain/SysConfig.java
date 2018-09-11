package com.dev.main.tenancy.domain;

import com.dev.main.common.domain.BaseDomain;

public class SysConfig extends BaseDomain {
    // 主键
    private Long id;

    // 键
    private String key;

    // 值
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
}