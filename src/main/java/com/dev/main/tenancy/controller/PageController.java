package com.dev.main.tenancy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    @GetMapping("/tenancy/p/{pageName}")
    public String page(@PathVariable("pageName") String pageName) {
        System.out.println("page:" + pageName);
        return pageName;
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/cardetail")
    public String cardetailPage() {
        return "cardetail";
    }

    @GetMapping("/order2step")
    public String order2stepPage() {
        return "beginReserve";
    }

    @GetMapping("/headList")
    public String headListPage() {
        return "headList";
    }

    @GetMapping("/storesMap")
    public String storesMapPage() {
        return "storesMap";
    }

    @GetMapping("/myOrderList")
    public String myOrderListPage() {
        return "myOrderList";
    }

    @GetMapping("/personInfo")
    public String personInfoPage() {
        return "personInfo";
    }

}
