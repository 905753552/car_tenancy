package com.dev.main.tenancy.dao;

import com.dev.main.tenancy.domain.TncCar;

import java.util.List;

public interface TncCarMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TncCar record);

    int insertSelective(TncCar record);

    TncCar selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TncCar record);

    int updateByPrimaryKey(TncCar record);

    /**
     * 查找符合门店的车型
     * @param id 门店ID
     * @return
     */
    List<TncCar> listCarByStoreId(Long id);
}