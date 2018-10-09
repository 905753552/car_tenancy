package com.dev.main.tenancy.service.impl;

import com.dev.main.tenancy.dao.TncAddressMapper;
import com.dev.main.tenancy.dao.TncStoreMapper;
import com.dev.main.tenancy.domain.TncAddress;
import com.dev.main.tenancy.domain.TncStore;
import com.dev.main.tenancy.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private TncAddressMapper tncAddressMapper;

    @Autowired
    private TncStoreMapper tncStoreMapper;

    @Override
    public List<TncAddress> findAllCity() {
        List<TncAddress> tncAddressList = tncAddressMapper.selectAllCity();
        return tncAddressList;
    }

    @Override
    public List<TncAddress> findAllStoreArea(Long id) {
        List<TncAddress> tncAddressList = tncAddressMapper.selectAllStoreArea(id);
        return tncAddressList;
    }

    @Override
    public List<TncStore> findStoreByAreaId(Long id) {
        List<TncStore> TncStoreList = tncStoreMapper.selectStoreByAreaId(id);
        return TncStoreList;
    }

}
