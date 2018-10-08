package com.dev.main.tenancy.domain;

import com.dev.main.common.domain.BaseDomain;
import java.math.BigDecimal;
import java.util.Date;

public class TncPriceScheme extends BaseDomain {
    // 主键
    private Long id;

    // 基础价(天)
    private BigDecimal basePrice;
    // 基础价(时)
    private BigDecimal baseHourPrice;

    // 服务费(天)
    private BigDecimal servicePrice;

    // 押金
    private BigDecimal deposit;

    // 折扣, 0<折扣<=1, 默认为1
    private BigDecimal discount;

    // 车（型号） 外键
    private Long carId;

    // 套餐 外键
    private Long packageId;

    // 是否删除 1-删除 
    private Byte isDeleted;

    // 创建时间
    private Date gmtCreate;

    // 修改时间
    private Date gmtModified;

    @Override
    public String toString() {
        return "TncPriceScheme{" +
                "id=" + id +
                ", basePrice=" + basePrice +
                ", baseHourPrice=" + baseHourPrice +
                ", servicePrice=" + servicePrice +
                ", deposit=" + deposit +
                ", discount=" + discount +
                ", carId=" + carId +
                ", packageId=" + packageId +
                ", isDeleted=" + isDeleted +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getBaseHourPrice() {
        return baseHourPrice;
    }

    public void setBaseHourPrice(BigDecimal baseHourPrice) {
        this.baseHourPrice = baseHourPrice;
    }

    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
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