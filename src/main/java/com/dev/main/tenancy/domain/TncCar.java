package com.dev.main.tenancy.domain;

import com.dev.main.common.domain.BaseDomain;
import java.util.Date;

public class TncCar extends BaseDomain {
    // 主键
    private Long id;

    // 系列
    private String series;

    // 数量
    private Integer quantity;

    // 剩余
    private Integer residual;

    // 年代款
    private Integer year;

    // 配置款
    private String configSection;

    // 座 位 数
    private Integer seatQuantity;

    // 车 门 数
    private Integer doorQuantity;

    // 燃料类型
    private String fuelType;

    // 变速箱类型
    private String transmissionType;

    // 排量
    private Integer displacement;

    // 燃油标号
    private String octaneRating;

    // 驱动方式
    private String drivenMethod;

    // 发动机进气形式
    private String enItkForm;

    // 天窗 1-有 0-无
    private Byte skylight;

    // 油箱容量(升)
    private Double tankCapacity;

    // 音箱数量
    private Integer speaker;

    // 厢数
    private Integer boxQuantity;

    // 座椅
    private String seat;

    // 倒车雷达 1-有 0-无
    private Byte reversingRadar;

    // 气囊数量
    private Integer airbag;

    // DVD / CD
    private String dvdCd;

    // GPS导航 1-有 0-无
    private Byte gps;

    // 车型 外键
    private Long typeId;

    // 门店 外键
    private Long storeId;

    // 品牌 外键
    private Long brandId;

    // 访问次数
    private Long accessTimes;

    // 状态：1-上架 2-下架
    private Byte status;

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

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series == null ? null : series.trim();
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getConfigSection() {
        return configSection;
    }

    public void setConfigSection(String configSection) {
        this.configSection = configSection == null ? null : configSection.trim();
    }

    public Integer getSeatQuantity() {
        return seatQuantity;
    }

    public void setSeatQuantity(Integer seatQuantity) {
        this.seatQuantity = seatQuantity;
    }

    public Integer getDoorQuantity() {
        return doorQuantity;
    }

    public void setDoorQuantity(Integer doorQuantity) {
        this.doorQuantity = doorQuantity;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType == null ? null : fuelType.trim();
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType == null ? null : transmissionType.trim();
    }

    public Integer getDisplacement() {
        return displacement;
    }

    public void setDisplacement(Integer displacement) {
        this.displacement = displacement;
    }

    public String getOctaneRating() {
        return octaneRating;
    }

    public void setOctaneRating(String octaneRating) {
        this.octaneRating = octaneRating == null ? null : octaneRating.trim();
    }

    public String getDrivenMethod() {
        return drivenMethod;
    }

    public void setDrivenMethod(String drivenMethod) {
        this.drivenMethod = drivenMethod == null ? null : drivenMethod.trim();
    }

    public String getEnItkForm() {
        return enItkForm;
    }

    public void setEnItkForm(String enItkForm) {
        this.enItkForm = enItkForm == null ? null : enItkForm.trim();
    }

    public Byte getSkylight() {
        return skylight;
    }

    public void setSkylight(Byte skylight) {
        this.skylight = skylight;
    }

    public Double getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(Double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public Integer getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Integer speaker) {
        this.speaker = speaker;
    }

    public Integer getBoxQuantity() {
        return boxQuantity;
    }

    public void setBoxQuantity(Integer boxQuantity) {
        this.boxQuantity = boxQuantity;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat == null ? null : seat.trim();
    }

    public Byte getReversingRadar() {
        return reversingRadar;
    }

    public void setReversingRadar(Byte reversingRadar) {
        this.reversingRadar = reversingRadar;
    }

    public Integer getAirbag() {
        return airbag;
    }

    public void setAirbag(Integer airbag) {
        this.airbag = airbag;
    }

    public String getDvdCd() {
        return dvdCd;
    }

    public void setDvdCd(String dvdCd) {
        this.dvdCd = dvdCd == null ? null : dvdCd.trim();
    }

    public Byte getGps() {
        return gps;
    }

    public void setGps(Byte gps) {
        this.gps = gps;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
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