package com.example.easychatbackend.entity.constants;

public class Constants {
    public static Integer REDIS_KEY_EXPIRED_HEARTBEAT = 10;
    public static String REDIS_KEY_USER_HEARTBEAT = "easyChat:heartbeat:";

    public static String REDIS_KEY_CHECK_CODE = "easyChat:checkCode:";

    // 是否重复登陆
    public static String REDIS_KEY_IS_LOGIN_CODE = "easyChat:isLogin:";
    //ws
    public static String REDIS_KEY_WS_TOKEN = "easyChat:ws:token:";
    public static String REDIS_KEY_WS_TOKEN_USERID = "easyChat:ws:token:userid:";
    public static Integer REDIS_TIME_1MIN = 60;
    public static Integer REDIS_TIME_EXPIRED_1DAY = REDIS_TIME_1MIN * 60 * 24;

    public static Integer USERID_LENGTH = 11;
}
