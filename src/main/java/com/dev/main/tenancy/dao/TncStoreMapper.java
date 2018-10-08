package com.dev.main.tenancy.dao;

import com.dev.main.tenancy.domain.TncStore;

import java.util.List;

public interface TncStoreMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TncStore record);

    int insertSelective(TncStore record);

    TncStore selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TncStore record);

    int updateByPrimaryKey(TncStore record);

    /**
     * 根据门店名称查ID
     * @param name
     * @return
     */
    Long selectPrimaryKeyByName(String name);

    /**
     * 根据区Id拿门店
     * @param id
     * @return
     */
    List<TncStore> selectStoreByAreaId(Long id);
}