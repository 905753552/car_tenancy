package com.dev.main.tenancy.vo;

import java.util.Date;

public class TncOrderData {
    private String getCarPlace;
    private Long getCarPlaceId;
    private String returnCarPlace;
    private Long returnCarPlaceId;
    private String getCarDate;
    private String getCarTime;
    private String returnCarDate;
    private String returnCarTime;
    private int days;
    private Long packageId;
    private Long carId;
    private Long priceId;

    @Override
    public String toString() {
        return "TncOrderData{" +
                "getCarPlace='" + getCarPlace + '\'' +
                ", getCarPlaceId=" + getCarPlaceId +
                ", returnCarPlace='" + returnCarPlace + '\'' +
                ", returnCarPlaceId=" + returnCarPlaceId +
                ", getCarDate='" + getCarDate + '\'' +
                ", getCarTime='" + getCarTime + '\'' +
                ", returnCarDate='" + returnCarDate + '\'' +
                ", returnCarTime='" + returnCarTime + '\'' +
                ", days=" + days +
                ", packageId=" + packageId +
                ", carId=" + carId +
                ", priceId=" + priceId +
                '}';
    }

    public String getGetCarPlace() {
        return getCarPlace;
    }

    public void setGetCarPlace(String getCarPlace) {
        this.getCarPlace = getCarPlace;
    }

    public Long getGetCarPlaceId() {
        return getCarPlaceId;
    }

    public void setGetCarPlaceId(Long getCarPlaceId) {
        this.getCarPlaceId = getCarPlaceId;
    }

    public String getReturnCarPlace() {
        return returnCarPlace;
    }

    public void setReturnCarPlace(String returnCarPlace) {
        this.returnCarPlace = returnCarPlace;
    }

    public Long getReturnCarPlaceId() {
        return returnCarPlaceId;
    }

    public void setReturnCarPlaceId(Long returnCarPlaceId) {
        this.returnCarPlaceId = returnCarPlaceId;
    }

    public String getGetCarDate() {
        return getCarDate;
    }

    public void setGetCarDate(String getCarDate) {
        this.getCarDate = getCarDate;
    }

    public String getGetCarTime() {
        return getCarTime;
    }

    public void setGetCarTime(String getCarTime) {
        this.getCarTime = getCarTime;
    }

    public String getReturnCarDate() {
        return returnCarDate;
    }

    public void setReturnCarDate(String returnCarDate) {
        this.returnCarDate = returnCarDate;
    }

    public String getReturnCarTime() {
        return returnCarTime;
    }

    public void setReturnCarTime(String returnCarTime) {
        this.returnCarTime = returnCarTime;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getPriceId() {
        return priceId;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }
}
