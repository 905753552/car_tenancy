package com.dev.main.tenancy.service;

import com.dev.main.common.util.ResultMap;
import com.dev.main.tenancy.domain.TncBrand;
import com.dev.main.tenancy.vo.TncCarDataVo;
import com.dev.main.tenancy.domain.TncPackageScheme;

import java.util.List;


public interface ICarService {
    /**
     * 查询汽车信息
     * @param storeId 门店ID
     * @param Pid 套餐 ID
     * @return
     */
    List<TncCarDataVo> listCarData(Long storeId,Long Pid);

    List<TncPackageScheme> listPackageScheme();

    List<TncBrand> listTncBrand();

    /**
     * LYH
     * @param id
     * @return
     */

    ResultMap TncCardetail(Long id);

    void addCarHot(Long cid);
}
