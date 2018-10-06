package com.dev.main.tenancy.vo;

import java.math.BigDecimal;
import java.util.Date;

public class TncOrderListVo {
    // 主键
    private Long id;

    // 总价 = (下单时)订单价格 + 其它费用
    private BigDecimal totalAmount;

    // 取车门店
    private Long getStoreId;

    // 还车门店
    private Long returnStoreId;

    // 开始时间
    private Date startDate;

    // 应还时间
    private Date returnDate;

    // 状态：0-提交订单 1-失效 2-已支付 3-用户取消（退款） 4-完成
    private Byte status;

    // 车item 外键
    private Long carItemId;

    private CarVo carVo;
    private TncStoreVo getStore;
    private TncStoreVo returnStore;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getGetStoreId() {
        return getStoreId;
    }

    public void setGetStoreId(Long getStoreId) {
        this.getStoreId = getStoreId;
    }

    public Long getReturnStoreId() {
        return returnStoreId;
    }

    public void setReturnStoreId(Long returnStoreId) {
        this.returnStoreId = returnStoreId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getCarItemId() {
        return carItemId;
    }

    public void setCarItemId(Long carItemId) {
        this.carItemId = carItemId;
    }

    public CarVo getCarVo() {
        return carVo;
    }

    public void setCarVo(CarVo carVo) {
        this.carVo = carVo;
    }

    public TncStoreVo getGetStore() {
        return getStore;
    }

    public void setGetStore(TncStoreVo getStore) {
        this.getStore = getStore;
    }

    public TncStoreVo getReturnStore() {
        return returnStore;
    }

    public void setReturnStore(TncStoreVo returnStore) {
        this.returnStore = returnStore;
    }

    @Override
    public String toString() {
        return "TncOrderListVo{" +
                "id=" + id +
                ", totalAmount=" + totalAmount +
                ", getStoreId=" + getStoreId +
                ", returnStoreId=" + returnStoreId +
                ", startDate=" + startDate +
                ", returnDate=" + returnDate +
                ", status=" + status +
                ", carItemId=" + carItemId +
                ", carVo=" + carVo +
                ", getStore=" + getStore +
                ", returnStore=" + returnStore +
                '}';
    }
}
