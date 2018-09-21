package com.dev.main.tenancy.controller;

import com.dev.main.common.util.ResultMap;
import com.dev.main.tenancy.dao.TncBrandMapper;
import com.dev.main.tenancy.dao.TncPackageSchemeMapper;
import com.dev.main.tenancy.domain.TncBrand;
import com.dev.main.tenancy.domain.TncCarDataVo;
import com.dev.main.tenancy.domain.TncPackageScheme;
import com.dev.main.tenancy.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/CarSelect")
public class CarSelectController {
    @Autowired
    private ICarService iCarService;

    @GetMapping("/listP")
    public ResultMap listMenuAndBrand(){
        List<TncPackageScheme> ltp = iCarService.listPackageScheme();
        List<TncBrand> ltc = iCarService.listTncBrand();
        ResultMap resultMap = new ResultMap();
        resultMap.put("TncPackageScheme",ltp);
        resultMap.put("TncBrand",ltc);
        System.out.println(resultMap);
     //   System.out.println(JsonUtils.toJsonStr(resultMap));
        return resultMap;
    }

    @GetMapping("/listCar")
    public ResultMap listCar(){

        ResultMap resultMap = new ResultMap();
        List<TncCarDataVo> li = iCarService.listCarData("租车派", (long) 1);
        System.out.println(resultMap);
        resultMap.put("carData",li);
        return resultMap;
    }


}
