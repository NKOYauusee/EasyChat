package com.example.easychatbackend.entity.dto;

import com.example.easychatbackend.utils.RedisService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

import java.io.Serializable;

public class TokenUserInfoDto implements Serializable {

    private static final long serialVersionUID =-3244262035649152692L;
    private String token;
    private String userId;
    private String nickname;
    private boolean admin;

    public String getToken() {
        return token;
    }

    public String getUserId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean getAdmin() {
        return admin;
    }


}
