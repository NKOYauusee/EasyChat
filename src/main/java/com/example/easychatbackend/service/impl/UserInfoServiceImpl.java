package com.example.easychatbackend.service.impl;

import com.example.easychatbackend.configuration.AppConfig;
import com.example.easychatbackend.entity.dto.TokenUserInfoDto;
import com.example.easychatbackend.entity.enums.UserStatusEnum;
import com.example.easychatbackend.entity.po.UserInfo;
import com.example.easychatbackend.entity.po.UserInfoExample;
import com.example.easychatbackend.mapper.UserInfoMapper;
import com.example.easychatbackend.service.UserInfoService;
import com.example.easychatbackend.utils.RedisService;
import com.example.easychatbackend.utils.StringUtils;
import jakarta.annotation.Resource;
import org.apache.ibatis.reflection.ArrayUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private RedisService redisService;
    @Resource
    UserInfoMapper userInfoMapper;
    @Resource
    AppConfig appConfig;

    /**
     * 注册账号事务处理
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(String email, String password, String nickname) throws Exception {
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andEmailEqualTo(email);

        //判断邮箱是否已注册
        if (!userInfoMapper.selectByExample(userInfoExample).isEmpty())
            throw new Exception("该邮箱已注册!");

        //生成注册账户的 userId
        String userId = StringUtils.getRandomUserid();

        Date cur = new Date();
        UserInfo account = new UserInfo();
        account.setUserId(userId);
        account.setPassword(StringUtils.encodeMd5(password));
        account.setNickName(nickname);
        account.setEmail(email);
        account.setCreateTime(cur);
        account.setStatus(UserStatusEnum.ENABLE.isEnable());
        account.setLastOffTime(cur.getTime());

        userInfoMapper.insert(account);
        //TODO 创建机器人好友
    }


    /**
     * 登录服务
     *
     * @param email    邮箱
     * @param password 密码
     */
    @Override
    public TokenUserInfoDto login(String email, String password) throws Exception {
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andEmailEqualTo(email);
        //查询结果
        List<UserInfo> res = userInfoMapper.selectByExample(userInfoExample);

        TokenUserInfoDto userInfoDto;

        if (res.isEmpty() || res.get(0).getPassword().equals(password))
            throw new Exception("账号不存在或密码错误");

        UserInfo curUser = res.get(0);
        if (curUser.getStatus().equals(UserStatusEnum.DISABLE.isEnable()))
            throw new Exception("该账号已封禁");

        //TODO 查询群组

        //TODO 查询联系人

        userInfoDto = getTokenUserInfoDto(res.get(0)); //无token

//        //判断是否有新设备重复登陆
        String lastLogin = redisService.isRepeatedLogin(userInfoDto.getUserId());
//
//        if (lastLogin != null) {
//            throw new Exception("此账号已在别处登录，请重新登录");
//        }
//        //在Redis 存放 UserID
//        redisService.setUserId(userInfoDto.getUserId());


        //心跳判断账户是否在线 -> 判断账户是否重复登陆
        Long lastHeatBeat = redisService.getUserHeartBeat(userInfoDto.getUserId());
        if(lastHeatBeat != null){
            throw new Exception("此账号已在别处登录，请重新登录");
        }

        String token = StringUtils.encodeMd5(userInfoDto.getUserId() + StringUtils.getRandomString(20));

        userInfoDto.setToken(token);

        //保存登录信息
        redisService.saveTokenUserInfoDto(userInfoDto);

        return userInfoDto;
    }

    /**
     * 判断用户权限
     *
     * @param userInfo 用户账号信息
     */
    public TokenUserInfoDto getTokenUserInfoDto(UserInfo userInfo) {
        TokenUserInfoDto tokenUserInfoDto = new TokenUserInfoDto();

        tokenUserInfoDto.setUserId(userInfo.getUserId());
        tokenUserInfoDto.setNickname(userInfo.getNickName());

        String adminEmails = appConfig.getAdminEmail();
        tokenUserInfoDto.setAdmin(!StringUtils.isEmpty(adminEmails) && ArrayUtil.toString(adminEmails.split(",")).contains(userInfo.getEmail()));

        return tokenUserInfoDto;
    }
}
