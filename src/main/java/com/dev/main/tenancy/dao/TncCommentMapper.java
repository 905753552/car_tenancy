package com.dev.main.tenancy.dao;

import com.dev.main.tenancy.domain.TncComment;

public interface TncCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TncComment record);

    int insertSelective(TncComment record);

    TncComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TncComment record);

    int updateByPrimaryKey(TncComment record);
}