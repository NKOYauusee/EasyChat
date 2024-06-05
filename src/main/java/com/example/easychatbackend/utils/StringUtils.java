package com.example.easychatbackend.utils;

import com.example.easychatbackend.entity.constants.Constants;
import com.example.easychatbackend.entity.enums.UserContackTypeEnum;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

public class StringUtils {
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static String getRandomNumber(Integer count) {
        return RandomStringUtils.random(count, false, true);

    }

    public static String getRandomUserid() {
        return UserContackTypeEnum.USER.getPrefix() + RandomStringUtils.random(Constants.USERID_LENGTH, false, true);

    }

    public static String getRandomGroupId() {
        return UserContackTypeEnum.GROUP.getPrefix() + RandomStringUtils.random(Constants.USERID_LENGTH, false, true);

    }

    public static String getRandomString(Integer count) {
        return RandomStringUtils.random(count, true, true);
    }

    public static String encodeMd5(String originPwd){
        return originPwd.isEmpty() ? null: DigestUtils.md5Hex(originPwd);
    }
}
