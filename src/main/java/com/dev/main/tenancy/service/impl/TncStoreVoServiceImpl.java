package com.dev.main.tenancy.service.impl;

import com.dev.main.tenancy.dao.StoreVoMapper;
import com.dev.main.tenancy.service.ITncStoreVoService;
import com.dev.main.tenancy.vo.TncStoreVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TncStoreVoServiceImpl implements ITncStoreVoService {

    @Autowired
    private StoreVoMapper storeVoMapper;

    public void setStoreVoMapper(StoreVoMapper storeVoMapper) {
        this.storeVoMapper = storeVoMapper;
    }

    @Override
    public List<TncStoreVo> findStores() {
        return storeVoMapper.findStores();
    }
}
