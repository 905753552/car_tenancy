package com.dev.main.tenancy.dao;

import com.dev.main.tenancy.domain.TncPointLog;

import javax.validation.constraints.Max;
import java.util.List;

public interface TncPointLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TncPointLog record);

    int insertSelective(TncPointLog record);

    TncPointLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TncPointLog record);

    int updateByPrimaryKey(TncPointLog record);

    List<TncPointLog> selectByPid(Long pid);
}