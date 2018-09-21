package com.dev.main.tenancy.dao;

import com.dev.main.tenancy.domain.TncPriceScheme;

import java.util.List;

public interface TncPriceSchemeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TncPriceScheme record);

    int insertSelective(TncPriceScheme record);

    TncPriceScheme selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TncPriceScheme record);

    int updateByPrimaryKey(TncPriceScheme record);

    /**
     * 查询所有符合改套餐的车型
     * @param Pid 套餐ID
     * @return
     */
    List<TncPriceScheme> listCarIdByPid(Long Pid);
}