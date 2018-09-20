package com.dev.main.tenancy.controller;

import com.dev.main.tenancy.dao.TncCarMapper;
import com.dev.main.tenancy.domain.TncCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  * Description: main
 *  * Created by sf on 2018/9/18 9:08
 *  
 */
@RestController
@RequestMapping("/tnc/order")
public class OrderController {
    @Autowired
    private TncCarMapper tncCarMapper;
    @GetMapping("/select/{id}")
    public TncCar selectOne(@PathVariable("id") Long id){
        return tncCarMapper.selectByPrimaryKey(id);

    }
}
