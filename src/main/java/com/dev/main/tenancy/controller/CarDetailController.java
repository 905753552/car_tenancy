package com.dev.main.tenancy.controller;

import com.dev.main.common.util.ResultMap;
import com.dev.main.tenancy.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/Car")
public class CarDetailController {
    @Autowired
    ICarService carService;

    @RequestMapping("/detail")
    public ResultMap getDetail(Long cid){
        return carService.TncCardetail(cid);
    }
}
