package com.example.easychatbackend.utils;


import com.example.easychatbackend.entity.constants.Constants;
import com.example.easychatbackend.entity.dto.TokenUserInfoDto;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class RedisService {

    @Resource
    private RedisUtils redisUtils;


    public Long getUserHeartBeat(String userid){
        return (Long) redisUtils.getValue(Constants.REDIS_KEY_USER_HEARTBEAT + userid);
    }

    public void saveHeartBeat(String userId){
        redisUtils.setExpiredKey(Constants.REDIS_KEY_USER_HEARTBEAT + userId,System.currentTimeMillis(),Constants.REDIS_KEY_EXPIRED_HEARTBEAT);
    }


    /**
     * 存储用户基本信息
     *
     */
    public void saveTokenUserInfoDto(TokenUserInfoDto infoDto){
        redisUtils.setExpiredKey(Constants.REDIS_KEY_WS_TOKEN + infoDto.getToken(),
                infoDto,Constants.REDIS_TIME_EXPIRED_1DAY * 2);

        redisUtils.setExpiredKey(Constants.REDIS_KEY_WS_TOKEN_USERID + infoDto.getUserId(),
                infoDto.getToken(),Constants.REDIS_TIME_EXPIRED_1DAY * 2);

    }

    //保存连接 ws Token
    public TokenUserInfoDto getUserInfoFromToken(String token){
        TokenUserInfoDto userInfoDto = (TokenUserInfoDto) redisUtils.getValue( Constants.REDIS_KEY_WS_TOKEN + token);

        return userInfoDto;
    }


    /**
     *
     * @param userId 用户ID
     * @return
     */
    public String isRepeatedLogin(String userId) {
        return (String) redisUtils.getValue(Constants.REDIS_KEY_IS_LOGIN_CODE + userId);
    }

    public void setUserId(String userId) {
        redisUtils.setKey(Constants.REDIS_KEY_IS_LOGIN_CODE + userId, StringUtils.getRandomString(20));
    }
}
