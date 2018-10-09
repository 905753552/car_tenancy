package com.dev.main.tenancy.dao;

import com.dev.main.tenancy.domain.TncAddress;

import java.util.List;

public interface TncAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TncAddress record);

    int insertSelective(TncAddress record);

    TncAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TncAddress record);

    int updateByPrimaryKey(TncAddress record);

    /**
     * 查找所有市级信息
     * @return
     */
    List<TncAddress> selectAllCity();

    /**
     * 查找相应省级的地区门店信息
     * @param id
     * @return
     */
    List<TncAddress> selectAllStoreArea(Long id);
}