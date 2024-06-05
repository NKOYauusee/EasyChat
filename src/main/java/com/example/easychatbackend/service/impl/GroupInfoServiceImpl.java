package com.example.easychatbackend.service.impl;


import com.example.easychatbackend.entity.enums.ResponseCodeEnum;
import com.example.easychatbackend.entity.enums.UserContackTypeEnum;
import com.example.easychatbackend.entity.po.GroupInfo;
import com.example.easychatbackend.entity.po.GroupInfoExample;
import com.example.easychatbackend.entity.po.UserContact;
import com.example.easychatbackend.exception.BusinessException;
import com.example.easychatbackend.mapper.GroupInfoMapper;
import com.example.easychatbackend.mapper.UserContactMapper;
import com.example.easychatbackend.service.GroupInfoService;
import com.example.easychatbackend.utils.RedisService;
import com.example.easychatbackend.utils.StringUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public class GroupInfoServiceImpl implements GroupInfoService {
    @Resource
    RedisService redisService;

    @Resource
    GroupInfoMapper groupInfoMapper;

    @Resource
    UserContactMapper userContactMapper;

    /**
     *  创建群组
     *  创立群表 并将该群作为群主的联系人
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveGroup(GroupInfo groupInfo, MultipartFile avatarFile, MultipartFile avatarCover) throws BusinessException {
        Date date = new Date();
        GroupInfoExample groupInfoExample = new GroupInfoExample();

        if (StringUtils.isEmpty(groupInfo.getGroupId())) {
            groupInfo.setCreateTime(date);
            groupInfo.setGroupId(StringUtils.getRandomGroupId());
            groupInfo.setStatus(true);
            groupInfoMapper.insert(groupInfo);

            //添加群组为群主联系人
            UserContact userContact = new UserContact();

            userContact.setUserId(groupInfo.getGroupownerId());
            userContact.setContactId(groupInfo.getGroupId());
            userContact.setContactType(UserContackTypeEnum.GROUP.getType() == 1);
            userContact.setStatus(true);
            userContact.setCreateTime(date);

            userContactMapper.insert(userContact);

            //TODO

        } else {
            GroupInfo dbInfo = groupInfoMapper.selectByPrimaryKey(groupInfo.getGroupId());
            if(!dbInfo.getGroupownerId().equals(groupInfo.getGroupownerId()))
                throw new BusinessException(ResponseCodeEnum.CODE_600);

            groupInfoExample.createCriteria().andGroupownerIdEqualTo(groupInfo.getGroupownerId());
            groupInfoMapper.updateByExample(groupInfo,groupInfoExample);

            //TODO 更新相关表冗余信息

            //TODO 更新相关信息发送ws信息
        }

        //TODO 群头像文件的保存
    }
}
