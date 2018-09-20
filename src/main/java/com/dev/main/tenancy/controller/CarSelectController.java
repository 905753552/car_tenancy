package com.dev.main.tenancy.controller;

import com.dev.main.common.util.JsonUtils;
import com.dev.main.common.util.ResultMap;
import com.dev.main.tenancy.controller.exception.TenancyExceptionResolver;
import com.dev.main.tenancy.dao.TncBrandMapper;
import com.dev.main.tenancy.dao.TncPackageSchemeMapper;
import com.dev.main.tenancy.domain.TncBrand;
import com.dev.main.tenancy.domain.TncPackageScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/CarSelect")
public class CarSelectController {
    @Autowired
    private TncPackageSchemeMapper tncPackageSchemeMapper;
    @Autowired
    private TncBrandMapper tncBrandMapper;
    @GetMapping("/listP")
    public ResultMap listMenuAndBrand(){
        List<TncPackageScheme> ltp = tncPackageSchemeMapper.listPackageScheme();
        List<TncBrand> ltc = tncBrandMapper.listTncBrand();
        ResultMap resultMap = new ResultMap();
        resultMap.put("TncPackageScheme",ltp);
        resultMap.put("TncBrand",ltc);
        System.out.println(resultMap);
     //   System.out.println(JsonUtils.toJsonStr(resultMap));
        return resultMap;
    }
}
