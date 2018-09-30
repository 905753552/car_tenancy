package com.dev.main.tenancy.vo;

import com.dev.main.tenancy.domain.TncAddress;
import com.dev.main.tenancy.domain.TncPoint;

import java.util.Date;

public class TncCustomerVo {
    // 主键
    private Long id;

    // 手机号 唯一
    private String phone;

    // 身份证号 唯一
    private String idCard;

    // 上次登录时间
    private Date lastAccessTime;

    // 性别
    private Byte gender;

    // 姓名
    private String name;

    // 邮箱
    private String email;

    // 用户通信地址
    private TncAddress tncAddress;

    // 紧急联系人 姓名
    private String emergencyName;

    // 紧急联系人 联系电话
    private String emergencyPhone;

    // 状态：1-可用，0-禁用
    private Byte status;

    // 是否删除 1-删除
    private Byte isDeleted;

    // 创建时间
    private Date gmtCreate;

    // 修改时间
    private Date gmtModified;

    // 积分
    private TncPoint tncPoint;

    // 密码
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TncAddress getTncAddress() {
        return tncAddress;
    }

    public void setTncAddress(TncAddress tncAddress) {
        this.tncAddress = tncAddress;
    }

    public TncPoint getTncPoint() {
        return tncPoint;
    }

    public void setTncPoint(TncPoint tncPoint) {
        this.tncPoint = tncPoint;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getEmergencyName() {
        return emergencyName;
    }

    public void setEmergencyName(String emergencyName) {
        this.emergencyName = emergencyName == null ? null : emergencyName.trim();
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone == null ? null : emergencyPhone.trim();
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
