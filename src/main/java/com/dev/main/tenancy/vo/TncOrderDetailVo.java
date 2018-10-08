package com.dev.main.tenancy.vo;

import com.dev.main.tenancy.domain.TncCar;

import java.math.BigDecimal;

public class TncOrderDetailVo {
    private TncCar car_info;//汽车信息 -----
    private String returnDate;//还车年月日  -----
    private String returnTime;//还车时分秒  -----
    private String returnStore;//还车门店名字  -----
    private String returnCity;//还车城市  -----
    private String getDate;//取车年月日  -----
    private String getTime;//取车时分秒  -----
    private String getStore;//取车门店  -----
    private String getCity;//取车城市   -----
    private int days;//租车天数  ------
    private BigDecimal base_price;//基础费（天） -----
    private BigDecimal service_price;//服务费（天） ------
    private BigDecimal total_base_price;//总的基础费 -----
    private BigDecimal total_service_price;//总的服务费  -----
    private BigDecimal discount_total_base;//打折后的总的基础费  ------
    private BigDecimal discount_total_service;//打折后的总的服务费  -----
    private BigDecimal other_cost;//总的其他费用   -----
    private int prepare_cost;//整备费  -----
    private int foreign_land_cost;//异地还车费  -----
    private int foreign_store_cost;//异店还车费   ----
    private BigDecimal overtime_cost;//超时费用（总）  -----
    private int overtime_count;//超时数   -----
    private BigDecimal deposit;//押金  -----
    private BigDecimal order_price_sum;//订单总价ttam  -----
    private String description;//备注  -----
    private BigDecimal coupon;//优惠券面值  -------
    private Long order_detail;//订单id  -----

    private String carPicPath;


    @Override
    public String toString() {
        return "TncOrderDetailVo{" +
                "car_info=" + car_info +
                ", returnDate='" + returnDate + '\'' +
                ", returnTime='" + returnTime + '\'' +
                ", returnStore='" + returnStore + '\'' +
                ", returnCity='" + returnCity + '\'' +
                ", getDate='" + getDate + '\'' +
                ", getTime='" + getTime + '\'' +
                ", getStore='" + getStore + '\'' +
                ", getCity='" + getCity + '\'' +
                ", days=" + days +
                ", base_price=" + base_price +
                ", service_price=" + service_price +
                ", total_base_price=" + total_base_price +
                ", total_service_price=" + total_service_price +
                ", discount_total_base=" + discount_total_base +
                ", discount_total_service=" + discount_total_service +
                ", other_cost=" + other_cost +
                ", prepare_cost=" + prepare_cost +
                ", foreign_land_cost=" + foreign_land_cost +
                ", foreign_store_cost=" + foreign_store_cost +
                ", overtime_cost=" + overtime_cost +
                ", overtime_count=" + overtime_count +
                ", deposit=" + deposit +
                ", order_price_sum=" + order_price_sum +
                ", description='" + description + '\'' +
                ", coupon=" + coupon +
                ", order_detail=" + order_detail +
                ", carPicPath='" + carPicPath + '\'' +
                '}';
    }

    public String getCarPicPath() {
        return carPicPath;
    }

    public void setCarPicPath(String carPicPath) {
        this.carPicPath = carPicPath;
    }

    public TncCar getCar_info() {
        return car_info;
    }

    public void setCar_info(TncCar car_info) {
        this.car_info = car_info;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getReturnStore() {
        return returnStore;
    }

    public void setReturnStore(String returnStore) {
        this.returnStore = returnStore;
    }

    public String getReturnCity() {
        return returnCity;
    }

    public void setReturnCity(String returnCity) {
        this.returnCity = returnCity;
    }

    public String getGetDate() {
        return getDate;
    }

    public void setGetDate(String getDate) {
        this.getDate = getDate;
    }

    public String getGetTime() {
        return getTime;
    }

    public void setGetTime(String getTime) {
        this.getTime = getTime;
    }

    public String getGetStore() {
        return getStore;
    }

    public void setGetStore(String getStore) {
        this.getStore = getStore;
    }

    public String getGetCity() {
        return getCity;
    }

    public void setGetCity(String getCity) {
        this.getCity = getCity;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public BigDecimal getBase_price() {
        return base_price;
    }

    public void setBase_price(BigDecimal base_price) {
        this.base_price = base_price;
    }

    public BigDecimal getService_price() {
        return service_price;
    }

    public void setService_price(BigDecimal service_price) {
        this.service_price = service_price;
    }

    public BigDecimal getTotal_base_price() {
        return total_base_price;
    }

    public void setTotal_base_price(BigDecimal total_base_price) {
        this.total_base_price = total_base_price;
    }

    public BigDecimal getTotal_service_price() {
        return total_service_price;
    }

    public void setTotal_service_price(BigDecimal total_service_price) {
        this.total_service_price = total_service_price;
    }

    public BigDecimal getDiscount_total_base() {
        return discount_total_base;
    }

    public void setDiscount_total_base(BigDecimal discount_total_base) {
        this.discount_total_base = discount_total_base;
    }

    public BigDecimal getDiscount_total_service() {
        return discount_total_service;
    }

    public void setDiscount_total_service(BigDecimal discount_total_service) {
        this.discount_total_service = discount_total_service;
    }

    public BigDecimal getOther_cost() {
        return other_cost;
    }

    public void setOther_cost(BigDecimal other_cost) {
        this.other_cost = other_cost;
    }

    public int getPrepare_cost() {
        return prepare_cost;
    }

    public void setPrepare_cost(int prepare_cost) {
        this.prepare_cost = prepare_cost;
    }

    public int getForeign_land_cost() {
        return foreign_land_cost;
    }

    public void setForeign_land_cost(int foreign_land_cost) {
        this.foreign_land_cost = foreign_land_cost;
    }

    public int getForeign_store_cost() {
        return foreign_store_cost;
    }

    public void setForeign_store_cost(int foreign_store_cost) {
        this.foreign_store_cost = foreign_store_cost;
    }

    public BigDecimal getOvertime_cost() {
        return overtime_cost;
    }

    public void setOvertime_cost(BigDecimal overtime_cost) {
        this.overtime_cost = overtime_cost;
    }

    public int getOvertime_count() {
        return overtime_count;
    }

    public void setOvertime_count(int overtime_count) {
        this.overtime_count = overtime_count;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getOrder_price_sum() {
        return order_price_sum;
    }

    public void setOrder_price_sum(BigDecimal order_price_sum) {
        this.order_price_sum = order_price_sum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCoupon() {
        return coupon;
    }

    public void setCoupon(BigDecimal coupon) {
        this.coupon = coupon;
    }

    public Long getOrder_detail() {
        return order_detail;
    }

    public void setOrder_detail(Long order_detail) {
        this.order_detail = order_detail;
    }
}
