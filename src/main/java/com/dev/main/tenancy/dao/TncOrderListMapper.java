package com.dev.main.tenancy.dao;

import com.dev.main.tenancy.domain.*;
import com.dev.main.tenancy.vo.TncCarVo;
import com.dev.main.tenancy.vo.TncOrderListVo;

import java.util.List;

public interface TncOrderListMapper {
    List<TncOrderListVo> selectOrderList(String phone);
}