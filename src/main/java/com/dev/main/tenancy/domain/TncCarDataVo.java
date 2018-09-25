package com.dev.main.tenancy.domain;

import java.math.BigDecimal;

public class TncCarDataVo {
    private long carId;
    private String carImg;
    private String carName;
    private String carVer;
    private String carVan;
    private String carPower;
    private Integer carSeat;
    private BigDecimal carPrice;
    private BigDecimal carTotalPrice;
    private BigDecimal carPackagePrice;
    private BigDecimal carTotalPackagePrice;
    private Long carHot;
    private String carType;

    private Long priceId;

    public TncCarDataVo(long carId, String carImg, String carName, String carVer, String carVan, String carPower, Integer carSeat, BigDecimal carPrice, BigDecimal carTotalPrice, BigDecimal carPackagePrice, BigDecimal carTotalPackagePrice, Long carHot, String carType) {
        this.carId = carId;
        this.carImg = carImg;
        this.carName = carName;
        this.carVer = carVer;
        this.carVan = carVan;
        this.carPower = carPower;
        this.carSeat = carSeat;
        this.carPrice = carPrice;
        this.carTotalPrice = carTotalPrice;
        this.carPackagePrice = carPackagePrice;
        this.carTotalPackagePrice = carTotalPackagePrice;
        this.carHot = carHot;
        this.carType = carType;
    }

    public TncCarDataVo() {
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getCarImg() {
        return carImg;
    }

    public void setCarImg(String carImg) {
        this.carImg = carImg;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarVer() {
        return carVer;
    }

    public void setCarVer(String carVer) {
        this.carVer = carVer;
    }

    public String getCarVan() {
        return carVan;
    }

    public void setCarVan(String carVan) {
        this.carVan = carVan;
    }

    public String getCarPower() {
        return carPower;
    }

    public void setCarPower(String carPower) {
        this.carPower = carPower;
    }

    public Integer getCarSeat() {
        return carSeat;
    }

    public void setCarSeat(Integer carSeat) {
        this.carSeat = carSeat;
    }

    public BigDecimal getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(BigDecimal carPrice) {
        this.carPrice = carPrice;
    }

    public BigDecimal getCarTotalPrice() {
        return carTotalPrice;
    }

    public void setCarTotalPrice(BigDecimal carTotalPrice) {
        this.carTotalPrice = carTotalPrice;
    }

    public BigDecimal getCarPackagePrice() {
        return carPackagePrice;
    }

    public void setCarPackagePrice(BigDecimal carPackagePrice) {
        this.carPackagePrice = carPackagePrice;
    }

    public BigDecimal getCarTotalPackagePrice() {
        return carTotalPackagePrice;
    }

    public void setCarTotalPackagePrice(BigDecimal carTotalPackagePrice) {
        this.carTotalPackagePrice = carTotalPackagePrice;
    }

    public Long getCarHot() {
        return carHot;
    }

    public void setCarHot(Long carHot) {
        this.carHot = carHot;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public Long getPriceId() {
        return priceId;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

    @Override
    public String toString() {
        return "TncCarDataVo{" +
                "carId=" + carId +
                ", carImg='" + carImg + '\'' +
                ", carName='" + carName + '\'' +
                ", carVer='" + carVer + '\'' +
                ", carVan='" + carVan + '\'' +
                ", carPower='" + carPower + '\'' +
                ", carSeat=" + carSeat +
                ", carPrice=" + carPrice +
                ", carTotalPrice=" + carTotalPrice +
                ", carPackagePrice=" + carPackagePrice +
                ", carTotalPackagePrice=" + carTotalPackagePrice +
                ", carHot=" + carHot +
                ", carType='" + carType + '\'' +
                ", priceId=" + priceId +
                '}';
    }
}
