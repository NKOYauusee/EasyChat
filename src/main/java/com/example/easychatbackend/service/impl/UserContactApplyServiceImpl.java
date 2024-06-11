package com.example.easychatbackend.service.impl;

import com.example.easychatbackend.entity.enums.UserContackTypeEnum;
import com.example.easychatbackend.entity.po.UserContact;
import com.example.easychatbackend.entity.po.UserContactExample;
import com.example.easychatbackend.mapper.UserContactMapper;
import com.example.easychatbackend.service.UserContactApplyService;
import com.example.easychatbackend.service.UserContactService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

@Service
public class UserContactApplyServiceImpl implements UserContactApplyService {
    @Resource
    UserContactMapper userContactMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserContactApplyServiceImpl.class);

    // 同意申请
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addApply(String applyUserID, String receiverId, String contactID, Integer contactType) {
        Date curDate = new Date();

        //如果是申请群组
        if (Objects.equals(contactType, UserContackTypeEnum.GROUP.getType())) {
            addFriend(applyUserID, contactID, contactType.equals(1), curDate);
            return;
        }

        //申请人添加对方为好友 同时 对方也要添加申请人为好友
        addFriend(applyUserID, receiverId, contactType.equals(1), curDate);
        addFriend(receiverId, applyUserID, contactType.equals(1), curDate);

        //TODO 创建会话
    }

    private void addFriend(String userID, String contactID, boolean contactType, Date createTime) {
        if(userID.equals(contactID))
            return;

        UserContact userContact = new UserContact();

        userContact.setUserId(userID);
        userContact.setContactId(contactID);
        userContact.setContactType(contactType);
        userContact.setCreateTime(createTime);
        userContact.setStatus(true);

        userContactMapper.insert(userContact);
    }
}
