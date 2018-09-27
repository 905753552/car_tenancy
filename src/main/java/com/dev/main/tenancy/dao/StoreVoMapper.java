package com.dev.main.tenancy.dao;

import com.dev.main.tenancy.vo.TncStoreVo;

import java.util.List;

public interface StoreVoMapper {

    List<TncStoreVo> findStores();

}
