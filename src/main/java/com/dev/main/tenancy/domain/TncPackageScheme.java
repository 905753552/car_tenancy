package com.dev.main.tenancy.domain;

import com.dev.main.common.domain.BaseDomain;
import java.util.Date;

public class TncPackageScheme extends BaseDomain {
    // 主键
    private Long id;

    // 套餐名
    private String name;

    // 天数下限
    private Integer daysMin;

    // 天数上限
    private Integer daysMax;

    // 是否删除 1-删除 
    private Byte isDeleted;

    // 创建时间
    private Date gmtCreate;

    // 修改时间
    private Date gmtModified;

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

    public Integer getDaysMin() {
        return daysMin;
    }

    public void setDaysMin(Integer daysMin) {
        this.daysMin = daysMin;
    }

    public Integer getDaysMax() {
        return daysMax;
    }

    public void setDaysMax(Integer daysMax) {
        this.daysMax = daysMax;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}