package com.dev.main.tenancy.controller;

import com.dev.main.common.controller.exception.GlobalExceptionResolver;
import com.dev.main.common.util.ResultMap;
import com.dev.main.tenancy.service.ITncStoreVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/store")
@GlobalExceptionResolver
public class StoreVoController {

    @Autowired
    private ITncStoreVoService tncStoreVoService;

    @GetMapping("vo")
    public ResultMap getVo() {
        return ResultMap.success().put("data", tncStoreVoService.findStores());
    }

    public void setTncStoreVoService(ITncStoreVoService tncStoreVoService) {
        this.tncStoreVoService = tncStoreVoService;
    }
}
