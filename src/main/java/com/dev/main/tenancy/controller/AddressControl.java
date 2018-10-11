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

    /**
     * 加载门店市级列表
     * @return
     */
    @GetMapping("listCity")
    public ResultMap listCity(){
        ResultMap resultMap = new ResultMap();
        List<TncAddress> tncAddressList = iAddressService.findAllCity();
        resultMap.put("data",tncAddressList);
        return  resultMap;
    }

    /**
     * 根据市级Id获得门店区级信息
     * @param id
     * @return
     */
    @GetMapping("listArea")
    public ResultMap listArea(Long id){
        ResultMap resultMap = new ResultMap();
        List<TncAddress> tncAddressList = iAddressService.findAllStoreArea(id);
        resultMap.put("data",tncAddressList);
        return  resultMap;
    }

    /**
     * 根据区级id获得门店信息列表
     * @param id
     * @return
     */
    @GetMapping("listStore")
    public ResultMap listStore(Long id){
        ResultMap resultMap = new ResultMap();
        List<TncStore> tncStoreList = iAddressService.findStoreByAreaId(id);
        resultMap.put("data",tncStoreList);
        return  resultMap;
    }

}
