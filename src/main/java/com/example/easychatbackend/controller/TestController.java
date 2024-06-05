package com.example.easychatbackend.controller;


import com.alibaba.fastjson2.JSON;
import com.example.easychatbackend.entity.po.UserInfo;
import com.example.easychatbackend.entity.po.UserInfoExample;
import com.example.easychatbackend.entity.vo.ResponseVo;
import com.example.easychatbackend.exception.BusinessException;
import com.example.easychatbackend.mapper.UserInfoMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class TestController {

    @Resource
    UserInfoMapper userInfoMapper;

    @RequestMapping(value = "/test")
    public ResponseVo test(@RequestParam(required = false, value = "email") String email) {
        List<UserInfo> res = new ArrayList<>();
        if (email != null && !email.isEmpty()) {
            UserInfoExample userInfoExample = new UserInfoExample();
            userInfoExample.createCriteria().andEmailEqualTo(email);

            res = userInfoMapper.selectByExample(userInfoExample);
        }

        return ResponseVo.success("",res);
    }

}
