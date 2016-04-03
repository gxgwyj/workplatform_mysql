package com.junyang.oa.dao;

import com.junyang.oa.model.Leave;

public interface LeaveMapper {
    int deleteByPrimaryKey(String id);

    int insert(Leave record);

    int insertSelective(Leave record);

    Leave selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Leave record);

    int updateByPrimaryKey(Leave record);
}