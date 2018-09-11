package com.dev.main.tenancy.domain;

import com.dev.main.common.domain.BaseDomain;

public class AddressRegion extends BaseDomain {
    // 主键
    private Long id;

    // 代码
    private String code;

    // 名称
    private String name;

    // 父ID 外键
    private Long parentId;

    // 首字母
    private String firstLetter;

    // 等级 0-省(直辖市) 1-地级市 2-区(县)
    private Byte level;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter == null ? null : firstLetter.trim();
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }
}