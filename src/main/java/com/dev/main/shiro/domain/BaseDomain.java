package com.dev.main.shiro.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 所有域对象的基类
 */
abstract public class BaseDomain implements Serializable {

    private Date timeStamp; // 时间戳

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
