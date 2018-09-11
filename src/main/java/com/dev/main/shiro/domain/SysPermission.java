package com.dev.main.shiro.domain;

import com.dev.main.common.domain.BaseDomain;
import java.util.Date;

public class SysPermission extends BaseDomain {
    // 主键
    private Long id;

    // 角色名 唯一
    private String name;

    // 描述
    private String description;

    // 权限url
    private String url;

    // 权限类型 0-菜单 1-api
    private Byte type;

    // 权限代码，使用分隔符":"
    private String code;

    // 排序字符串
    private String sortString;

    // 直接父权限 外键
    private Long parentId;

    // 所有父权限，使用“/”分隔
    private String parentIds;

    // 状态：1-可用，0-禁用
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getSortString() {
        return sortString;
    }

    public void setSortString(String sortString) {
        this.sortString = sortString == null ? null : sortString.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds == null ? null : parentIds.trim();
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