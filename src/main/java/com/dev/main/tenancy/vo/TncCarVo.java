package com.dev.main.tenancy.vo;

import com.dev.main.common.domain.BaseDomain;
import com.dev.main.tenancy.domain.TncBrand;
import com.dev.main.tenancy.domain.TncCarType;
import com.dev.main.tenancy.domain.TncStore;

public class TncCarVo extends BaseDomain {
    // 主键
    private Long id;

    // 车型 外键
    private TncCarType tncCarType;

    // 门店 外键
    private TncStore tncStore;

    // 品牌 外键
    private TncBrand tncBrand;

    // 数量
    private Integer quantity;

    // 剩余
    private Integer residual;

    // 访问次数
    private Long accessTimes;

    // 状态：1-上架 2-下架
    private Byte status;

    // 是否删除 1-删除
    private Byte isDeleted;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TncCarType getTncCarType() {
        return tncCarType;
    }

    public void setTncCarType(TncCarType tncCarType) {
        this.tncCarType = tncCarType;
    }

    public TncStore getTncStore() {
        return tncStore;
    }

    public void setTncStore(TncStore tncStore) {
        this.tncStore = tncStore;
    }

    public TncBrand getTncBrand() {
        return tncBrand;
    }

    public void setTncBrand(TncBrand tncBrand) {
        this.tncBrand = tncBrand;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getResidual() {
        return residual;
    }

    public void setResidual(Integer residual) {
        this.residual = residual;
    }

    public Long getAccessTimes() {
        return accessTimes;
    }

    public void setAccessTimes(Long accessTimes) {
        this.accessTimes = accessTimes;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }


}
