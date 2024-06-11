package com.example.easychatbackend.service.impl;


import com.example.easychatbackend.entity.dto.TokenUserInfoDto;
import com.example.easychatbackend.entity.dto.UserContactSearchResultDto;
import com.example.easychatbackend.entity.enums.ResponseCodeEnum;
import com.example.easychatbackend.entity.enums.UserContackTypeEnum;
import com.example.easychatbackend.entity.po.*;
import com.example.easychatbackend.exception.BusinessException;
import com.example.easychatbackend.mapper.GroupInfoMapper;
import com.example.easychatbackend.mapper.UserContactApplyMapper;
import com.example.easychatbackend.mapper.UserContactMapper;
import com.example.easychatbackend.mapper.UserInfoMapper;
import com.example.easychatbackend.service.UserContactService;
import com.example.easychatbackend.utils.RedisService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserContactServiceImpl implements UserContactService {
    @Resource
    UserInfoMapper userInfoMapper;

    @Resource
    GroupInfoMapper groupInfoMapper;

    @Resource
    UserContactMapper userContactMapper;

    @Resource
    UserContactApplyMapper contactApplyMapper;


    //搜索 服务
    @Override
    public UserContactSearchResultDto searchContact(String fromID, String searchContactID) {
        UserContackTypeEnum userContackTypeEnum = UserContackTypeEnum.getByPrefix(searchContactID);

        UserContactSearchResultDto searchResult = new UserContactSearchResultDto();

        if (userContackTypeEnum == null)
            return null;
        else if (userContackTypeEnum.equals(UserContackTypeEnum.USER)) {
            UserInfoExample userInfoExample = new UserInfoExample();
            userInfoExample.createCriteria().andUserIdEqualTo(searchContactID);
            UserInfo res = userInfoMapper.selectByExample(userInfoExample).get(0);

            if (res == null)
                return null;

            searchResult.setContactID(res.getUserId());
            searchResult.setStatus(res.getStatus());
            searchResult.setNotice(res.getPersonalSignature());
            searchResult.setName(res.getNickName());
        } else if (userContackTypeEnum.equals(UserContackTypeEnum.GROUP)) {
            GroupInfoExample groupInfoExample = new GroupInfoExample();
            groupInfoExample.createCriteria().andGroupIdEqualTo(searchContactID);
            GroupInfo groupRes = groupInfoMapper.selectByExample(groupInfoExample).get(0);

            if (groupRes == null)
                return null;

            searchResult.setContactID(groupRes.getGroupId());
            searchResult.setStatus(groupRes.getStatus());
            searchResult.setNotice(groupRes.getGroupNotice());
            searchResult.setName(groupRes.getGroupName());
        }

        //TODO 查询目标是否已经是好友

        return searchResult;
    }

    // 删除 服务
    @Override
    public Integer deleteContact(String fromID, String searchContactId) {
        UserContactExample contactExample = new UserContactExample();
        contactExample.createCriteria().andContactIdEqualTo(searchContactId);
        int res = userContactMapper.deleteByExample(contactExample);

        return res;
    }


    // 申请 服务
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void applyNewFriend(TokenUserInfoDto userInfoDto, String contactId, String applyInfo) throws BusinessException {
        //不能向自己申请
        if (userInfoDto.getUserId().equals(contactId))
            throw new BusinessException("不能添加自己为好友");

        UserContackTypeEnum typeEnum = UserContackTypeEnum.getByPrefix(contactId);
        if (typeEnum == null) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }

        //判断是群申请还是好友申请
        String receiver_id = "";

        if (typeEnum.equals(UserContackTypeEnum.GROUP)) {
            UserContactExample userContactExample = new UserContactExample();
            userContactExample.createCriteria()
                    .andUserIdEqualTo(userInfoDto.getUserId())
                    .andContactIdEqualTo(contactId);
            if (!userContactMapper.selectByExample(userContactExample).isEmpty())
                throw new BusinessException("已在好友列表中");


            GroupInfoExample groupInfoExample = new GroupInfoExample();
            groupInfoExample.createCriteria().andGroupIdEqualTo(contactId);

            GroupInfo groupInfo = groupInfoMapper.selectByExample(groupInfoExample).get(0);

            if (groupInfo == null || !groupInfo.getStatus())
                throw new BusinessException("该群不存在或已解散");

            receiver_id = groupInfo.getGroupownerId();

        } else {
            //TODO 理应判断对方的好友申请状态

            receiver_id = contactId;
        }
        //添加申请表信息

        String senderID = userInfoDto.getUserId();   //ApplyId
        Long curDate = System.currentTimeMillis();   //last_apply_time


        UserContactApplyExample contactApplyExample = new UserContactApplyExample();
        contactApplyExample.createCriteria().andApplyUserIdEqualTo(senderID).andReceiverUserIdEqualTo(receiver_id);

        //判断是否已经申请

        //不存在
        if (contactApplyMapper.selectByExample(contactApplyExample).isEmpty()) {
            UserContactApply userContactApply = new UserContactApply();

            userContactApply.setApplyUserId(senderID); //申请人
            userContactApply.setReceiverUserId(receiver_id); //收到申请的人
            userContactApply.setContactId(contactId);
            userContactApply.setLastApplyTime(curDate);
            userContactApply.setApplyInfo(applyInfo);
            userContactApply.setContactType(typeEnum.getType() == 1);

            contactApplyMapper.insert(userContactApply);
        }
        //存在
        else {
            UserContactApply userContactApply = contactApplyMapper.selectByExample(contactApplyExample).get(0);

            userContactApply.setLastApplyTime(curDate);
            userContactApply.setApplyInfo(applyInfo);
            contactApplyMapper.updateByExample(userContactApply, contactApplyExample);
        }

        //TODO 发送ws消息
    }
}
