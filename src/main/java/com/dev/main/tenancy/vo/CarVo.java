package com.dev.main.tenancy.vo;

public class CarVo {
    //图片路径
    private String path;

    private Long carId;
    //品牌
    private String bandname;

    // 系列
    private String series;
    // 排量
    private Integer displacement;
    // 厢数
    private Integer boxQuantity;
    // 变速箱类型
    private String transmissionType;
    // 座 位 数
    private Integer seatQuantity;

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "CarVo{" +
                "path='" + path + '\'' +
                ", bandname='" + bandname + '\'' +
                ", series='" + series + '\'' +
                ", displacement=" + displacement +
                ", boxQuantity=" + boxQuantity +
                ", transmissionType='" + transmissionType + '\'' +
                ", seatQuantity=" + seatQuantity +
                '}';
    }

    public void setPath(String path) {
        this.path = "/api/pic/item?imagePath="+path;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getBandname() {
        return bandname;
    }

    public void setBandname(String bandname) {
        this.bandname = bandname;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Integer getDisplacement() {
        return displacement;
    }

    public void setDisplacement(Integer displacement) {
        this.displacement = displacement;
    }

    public Integer getBoxQuantity() {
        return boxQuantity;
    }

    public void setBoxQuantity(Integer boxQuantity) {
        this.boxQuantity = boxQuantity;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public Integer getSeatQuantity() {
        return seatQuantity;
    }

    public void setSeatQuantity(Integer seatQuantity) {
        this.seatQuantity = seatQuantity;
    }
}
