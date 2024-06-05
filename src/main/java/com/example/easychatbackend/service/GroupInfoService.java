package com.example.easychatbackend.service;

import com.example.easychatbackend.entity.po.GroupInfo;
import com.example.easychatbackend.exception.BusinessException;
import org.springframework.web.multipart.MultipartFile;

public interface GroupInfoService {
    
    void saveGroup(GroupInfo groupInfo, MultipartFile avatarFile,
                   MultipartFile avatarCover) throws BusinessException;
}
