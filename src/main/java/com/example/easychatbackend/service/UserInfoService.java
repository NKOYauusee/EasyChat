package com.example.easychatbackend.service;

import com.example.easychatbackend.entity.dto.TokenUserInfoDto;

public interface UserInfoService {

    void register(String email, String password, String nickname) throws Exception;

    TokenUserInfoDto login(String email, String password) throws Exception;
}
