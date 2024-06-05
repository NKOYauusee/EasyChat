package com.example.easychatbackend.mapper;

import com.example.easychatbackend.entity.po.GroupInfo;
import com.example.easychatbackend.entity.po.GroupInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupInfoMapper {
    long countByExample(GroupInfoExample example);

    int deleteByExample(GroupInfoExample example);

    int deleteByPrimaryKey(String groupId);

    int insert(GroupInfo record);

    int insertSelective(GroupInfo record);

    List<GroupInfo> selectByExample(GroupInfoExample example);

    GroupInfo selectByPrimaryKey(String groupId);

    int updateByExampleSelective(@Param("record") GroupInfo record, @Param("example") GroupInfoExample example);

    int updateByExample(@Param("record") GroupInfo record, @Param("example") GroupInfoExample example);

    int updateByPrimaryKeySelective(GroupInfo record);

    int updateByPrimaryKey(GroupInfo record);
}