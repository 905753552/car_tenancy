package com.dev.main.tenancy.dao;

import com.dev.main.tenancy.domain.TncBrand;

import java.util.List;

public interface TncBrandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TncBrand record);

    int insertSelective(TncBrand record);

    TncBrand selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TncBrand record);

    int updateByPrimaryKey(TncBrand record);

    /**
     * 查所有汽车品牌
     * 2018-09-19 11:07
     * @return
     */
    List<TncBrand> listTncBrand();

    String selectNameById(Long cid);
}