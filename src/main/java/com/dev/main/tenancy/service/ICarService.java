package com.dev.main.tenancy.service;

import com.dev.main.common.util.ResultMap;
import com.dev.main.tenancy.domain.TncBrand;
import com.dev.main.tenancy.domain.TncCarDataVo;
import com.dev.main.tenancy.domain.TncPackageScheme;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ICarService {
    /**
     * 查询汽车信息
     * @param storeName 门店名
     * @param Pid 套餐 ID
     * @return
     */
    List<TncCarDataVo> listCarData(String storeName,Long Pid);

    List<TncPackageScheme> listPackageScheme();

    List<TncBrand> listTncBrand();
}
