package com.junyang.oa.dao;

import java.util.List;

import com.junyang.oa.model.Leave;
import com.junyang.oa.vo.LeaveVo;

public interface LeaveMapper {
    int deleteByPrimaryKey(String id);

    int insert(Leave record);

    int insertSelective(Leave record);

    Leave selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Leave record);

    int updateByPrimaryKey(Leave record);
    
    List<Leave> selectLeaveVoList(LeaveVo leaveVo);
}