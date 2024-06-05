package com.example.easychatbackend.mapper;

import com.example.easychatbackend.entity.po.UserInfoBeauty;
import com.example.easychatbackend.entity.po.UserInfoBeautyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserInfoBeautyMapper {
    long countByExample(UserInfoBeautyExample example);

    int deleteByExample(UserInfoBeautyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserInfoBeauty record);

    int insertSelective(UserInfoBeauty record);

    List<UserInfoBeauty> selectByExample(UserInfoBeautyExample example);

    UserInfoBeauty selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserInfoBeauty record, @Param("example") UserInfoBeautyExample example);

    int updateByExample(@Param("record") UserInfoBeauty record, @Param("example") UserInfoBeautyExample example);

    int updateByPrimaryKeySelective(UserInfoBeauty record);

    int updateByPrimaryKey(UserInfoBeauty record);
}