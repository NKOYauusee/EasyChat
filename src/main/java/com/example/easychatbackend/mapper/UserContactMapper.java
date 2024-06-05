package com.example.easychatbackend.mapper;

import com.example.easychatbackend.entity.po.UserContact;
import com.example.easychatbackend.entity.po.UserContactExample;
import com.example.easychatbackend.entity.po.UserContactKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserContactMapper {
    long countByExample(UserContactExample example);

    int deleteByExample(UserContactExample example);

    int deleteByPrimaryKey(UserContactKey key);

    int insert(UserContact record);

    int insertSelective(UserContact record);

    List<UserContact> selectByExample(UserContactExample example);

    UserContact selectByPrimaryKey(UserContactKey key);

    int updateByExampleSelective(@Param("record") UserContact record, @Param("example") UserContactExample example);

    int updateByExample(@Param("record") UserContact record, @Param("example") UserContactExample example);

    int updateByPrimaryKeySelective(UserContact record);

    int updateByPrimaryKey(UserContact record);
}