package com.dev.main.tenancy.domain;

import com.dev.main.common.domain.BaseDomain;
import java.math.BigDecimal;
import java.util.Date;

public class TncOrder extends BaseDomain {
    // 主键
    private Long id;

    // 姓名
    private String name;

    // 证件类型 1-身份证 2-台湾居民来往大陆通行证 3-港澳居民来往内地通行
    private Byte credentialsType;

    // 证件号码
    private String credentialsNumber;

    // 邮箱
    private String email;

    // 手机号
    private String phone;

    // 总价 = (下单时)订单价格 + 其它费用
    private BigDecimal totalAmount;

    // (下单时)订单价格=天数*(基础价 + 服务费)*折扣-优惠券面值
    private BigDecimal orderAmount;

    // 租赁费用 天数*基础价
    private BigDecimal baseAmount;

    // 服务费用 天数*服务费
    private BigDecimal serviceAmount;

    // 其它费用(租赁过程中产生的额外收费)
    private BigDecimal otherAmount;

    // 优惠券 外键
    private Long couponId;

    // 备注，用于说明扣费项及其它特殊状况
    private String description;

    // 折扣, 0<折扣<=1, 默认为1
    private BigDecimal discount;

    // 已收押金
    private BigDecimal deposit;

    // 退还押金
    private BigDecimal returnDeposit;

    // 是否已退押 0-未退 1-已退
    private Byte isDepositReturned;

    // 套餐名
    private String packageName;

    // 取车门店
    private Long getStoreId;

    // 还车门店
    private Long returnStoreId;

    // 开始时间
    private Date startDate;

    // 应还时间
    private Date returnDate;

    // 实际归还时间
    private Date realReturnDate;

    // 支付时间
    private Date payTime;

    // 状态：0-提交订单 1-失效 2-已支付 3-用户取消（退款） 4-完成
    private Byte status;

    // 是否删除 1-删除 
    private Byte isDeleted;

    // 创建时间
    private Date gmtCreate;

    // 修改时间
    private Date gmtModified;

    // 车item 外键
    private Long carItemId;

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

    public Byte getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(Byte credentialsType) {
        this.credentialsType = credentialsType;
    }

    public String getCredentialsNumber() {
        return credentialsNumber;
    }

    public void setCredentialsNumber(String credentialsNumber) {
        this.credentialsNumber = credentialsNumber == null ? null : credentialsNumber.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(BigDecimal baseAmount) {
        this.baseAmount = baseAmount;
    }

    public BigDecimal getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(BigDecimal serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

    public BigDecimal getOtherAmount() {
        return otherAmount;
    }

    public void setOtherAmount(BigDecimal otherAmount) {
        this.otherAmount = otherAmount;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getReturnDeposit() {
        return returnDeposit;
    }

    public void setReturnDeposit(BigDecimal returnDeposit) {
        this.returnDeposit = returnDeposit;
    }

    public Byte getIsDepositReturned() {
        return isDepositReturned;
    }

    public void setIsDepositReturned(Byte isDepositReturned) {
        this.isDepositReturned = isDepositReturned;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName == null ? null : packageName.trim();
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

    public Date getRealReturnDate() {
        return realReturnDate;
    }

    public void setRealReturnDate(Date realReturnDate) {
        this.realReturnDate = realReturnDate;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
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

    public Long getCarItemId() {
        return carItemId;
    }

    public void setCarItemId(Long carItemId) {
        this.carItemId = carItemId;
    }
}