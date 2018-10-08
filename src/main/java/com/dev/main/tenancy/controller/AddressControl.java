package com.dev.main.tenancy.controller;

import com.dev.main.common.controller.exception.GlobalExceptionResolver;
import com.dev.main.common.util.ResultMap;
import com.dev.main.tenancy.domain.TncAddress;
import com.dev.main.tenancy.domain.TncStore;
import com.dev.main.tenancy.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/storeAddress")
@GlobalExceptionResolver
public class AddressControl {

    @Autowired
    private IAddressService iAddressService;

    @GetMapping("list")
    public ResultMap list(){
        ResultMap result = new ResultMap();
        List<TncAddress> tncAddressList = iAddressService.findAllStoreCity();
        result.put("data",tncAddressList);
        return  result;
    }

    @GetMapping("list2")
    public ResultMap list2(Long id){
        ResultMap result = new ResultMap();
        List<TncAddress> tncAddressList = iAddressService.findAllStoreArea(id);
        result.put("data",tncAddressList);
        return  result;
    }

    @GetMapping("list3")
    public ResultMap list3(Long id){
        ResultMap result = new ResultMap();
        List<TncStore> tncStoreList = iAddressService.findStoreByAreaId(id);
        result.put("data",tncStoreList);
        return  result;
    }
}
