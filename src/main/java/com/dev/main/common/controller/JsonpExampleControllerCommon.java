package com.dev.main.common.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPObject;
import com.dev.main.common.util.ResultMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class JsonpExampleControllerCommon extends CommonBaseController {

    @RequestMapping(path="/jsonp")
    public String createByJsonp(@RequestParam(value = "callback", defaultValue = "jsonpCallback",required=false) String callback, String data){
        ResultMap resultMap = ResultMap.success();
        resultMap.put("data", JSON.parseObject(data));

        JSONPObject jpo = new JSONPObject(callback);
        jpo.addParameter(resultMap);
        return jpo.toString();
    }

}
