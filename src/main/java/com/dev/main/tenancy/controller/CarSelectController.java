package com.dev.main.tenancy.controller;

import com.alibaba.fastjson.JSONObject;
import com.dev.main.common.controller.exception.GlobalExceptionResolver;
import com.dev.main.common.util.ResultMap;
import com.dev.main.tenancy.domain.TncBrand;
import com.dev.main.tenancy.vo.TncCarDataVo;
import com.dev.main.tenancy.domain.TncPackageScheme;
import com.dev.main.tenancy.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/CarSelect")
@GlobalExceptionResolver
public class CarSelectController {
    @Autowired
    private ICarService iCarService;

    /**
     * 查询所有套餐
     * @return
     */
    @GetMapping("/listP")
    public ResultMap listMenuAndBrand(){
        List<TncPackageScheme> ltp = iCarService.listPackageScheme();
        List<TncBrand> ltc = iCarService.listTncBrand();
        ResultMap resultMap = new ResultMap();
        resultMap.put("TncPackageScheme",ltp);
        resultMap.put("TncBrand",ltc);
       // System.out.println(resultMap);
     //   System.out.println(JsonUtils.toJsonStr(resultMap));
        return resultMap;
    }

    /**
     * 查询符合条件的车
     * @param data
     * @return
     */
    @PostMapping("/listCar")
    public ResultMap listCar(@RequestBody String data){
        Long cp = Long.valueOf(JSONObject.parseObject(data).get("carPlace").toString());
        Long pid =Long.valueOf(JSONObject.parseObject(data).get("carPID").toString());
        ResultMap resultMap = new ResultMap();
        List<TncCarDataVo> li = iCarService.listCarData(cp, pid);
        //System.out.println(resultMap);
        resultMap.put("carData",li);
        return resultMap;
    }


}
