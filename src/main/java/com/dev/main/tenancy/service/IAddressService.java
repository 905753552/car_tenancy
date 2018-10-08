package com.dev.main.tenancy.service;

import com.dev.main.tenancy.domain.TncAddress;
import com.dev.main.tenancy.domain.TncStore;

import java.util.List;

public interface IAddressService {

    /**
     * 查找所有门店市级信息
     * @return
     */
    List<TncAddress> findAllStoreCity();

    /**
     * 查找相应省级的地区门店信息
     * @param id
     * @return
     */
    List<TncAddress> findAllStoreArea(Long id);

    /**
     * 根据区id查找门店
     * @param id
     * @return
     */
    List<TncStore> findStoreByAreaId(Long id);
}
