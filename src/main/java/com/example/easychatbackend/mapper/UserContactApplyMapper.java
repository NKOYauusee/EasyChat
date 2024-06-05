package com.example.easychatbackend.mapper;

import com.example.easychatbackend.entity.po.UserContactApply;
import com.example.easychatbackend.entity.po.UserContactApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserContactApplyMapper {
    long countByExample(UserContactApplyExample example);

    int deleteByExample(UserContactApplyExample example);

    int deleteByPrimaryKey(Integer applyId);

    int insert(UserContactApply record);

    int insertSelective(UserContactApply record);

    List<UserContactApply> selectByExample(UserContactApplyExample example);

    UserContactApply selectByPrimaryKey(Integer applyId);

    int updateByExampleSelective(@Param("record") UserContactApply record, @Param("example") UserContactApplyExample example);

    int updateByExample(@Param("record") UserContactApply record, @Param("example") UserContactApplyExample example);

    int updateByPrimaryKeySelective(UserContactApply record);

    int updateByPrimaryKey(UserContactApply record);
}