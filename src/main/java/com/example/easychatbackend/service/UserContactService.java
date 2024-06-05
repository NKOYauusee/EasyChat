package com.example.easychatbackend.service;


import com.example.easychatbackend.entity.dto.TokenUserInfoDto;
import com.example.easychatbackend.entity.dto.UserContactSearchResultDto;
import com.example.easychatbackend.exception.BusinessException;

public interface UserContactService {
    //搜索联系人
    UserContactSearchResultDto searchContact(String fromID, String searchContactID);

    //删除联系人
    Integer deleteContact(String fromID,String searchContactId);

    void applyNewFriend(TokenUserInfoDto userInfoDto, String contactId, String applyInfo) throws BusinessException;
}
