package com.example.easychatbackend.controller;

import com.example.easychatbackend.annotation.GlobalInterceptor;
import com.example.easychatbackend.entity.dto.TokenUserInfoDto;
import com.example.easychatbackend.entity.po.GroupInfo;
import com.example.easychatbackend.entity.vo.ResponseVo;
import com.example.easychatbackend.exception.BusinessException;
import com.example.easychatbackend.service.GroupInfoService;
import com.example.easychatbackend.utils.RedisService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/group")
public class GroupInfoController {
    @Resource
    RedisService redisService;

    @Resource
    GroupInfoService groupInfoService;


    /**
     *  创建和修改群组信息接口
     */
    @GlobalInterceptor
    @RequestMapping("/saveGroup")
    public ResponseVo saveGroup(
            HttpServletRequest request,
            String groupID,
            @NotEmpty String groupName,
            String groupNotice,
            @NotEmpty String joinType,
            MultipartFile avatarFile,
            MultipartFile avatarCover
    ) throws BusinessException {

        //根据Token 获取创立者的 userID
        TokenUserInfoDto userInfoDto = getTokenUserInfoDto(request);

        GroupInfo groupInfo = new GroupInfo();                      //群简介
        groupInfo.setGroupId(groupID);                              //群ID
        groupInfo.setGroupName(groupName);                          //群名
        groupInfo.setGroupownerId(userInfoDto.getUserId());         //群主 userID
        groupInfo.setGroupNotice(groupNotice);                      //群名
        groupInfo.setJoinType(joinType.equals("1"));

        groupInfoService.saveGroup(groupInfo,avatarFile,avatarCover);


        return ResponseVo.success("创建成功", null);
    }

    public TokenUserInfoDto getTokenUserInfoDto(HttpServletRequest request) {
        String token = request.getHeader("token");

        TokenUserInfoDto userInfoDto = redisService.getUserInfoFromToken(token);

        return userInfoDto;
    }
}
