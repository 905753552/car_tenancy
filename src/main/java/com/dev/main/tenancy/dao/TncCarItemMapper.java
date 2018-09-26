package com.dev.main.tenancy.dao;

import com.dev.main.tenancy.domain.TncCarItem;

public interface TncCarItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TncCarItem record);

    int insertSelective(TncCarItem record);

    TncCarItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TncCarItem record);

    int updateByPrimaryKey(TncCarItem record);

    /**
     * 统计某系列车可出租数量
     * @param i 车系列ID
     * @return
     */
    int countUnusedCar(Long i);
}