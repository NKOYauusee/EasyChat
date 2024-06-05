package com.example.easychatbackend.controller;


import com.example.easychatbackend.annotation.GlobalInterceptor;
import com.example.easychatbackend.entity.dto.TokenUserInfoDto;
import com.example.easychatbackend.entity.dto.UserContactSearchResultDto;
import com.example.easychatbackend.entity.vo.ResponseVo;
import com.example.easychatbackend.exception.BusinessException;
import com.example.easychatbackend.mapper.UserInfoMapper;
import com.example.easychatbackend.service.UserContactService;
import com.example.easychatbackend.utils.RedisService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/contact")
public class UserContactController {
    @Resource
    UserContactService userContactService;

    @Resource
    RedisService redisService;


    @RequestMapping("/delete")
    @GlobalInterceptor
    public ResponseVo deleteUserContact(HttpServletRequest request, @NotEmpty String contactID) {
        TokenUserInfoDto userInfoDto = getTokenUserInfoDto(request);
        int res = userContactService.deleteContact(userInfoDto.getUserId(), contactID);

        return res != 0 ? ResponseVo.info("删除成功") : ResponseVo.fail("删除失败");
    }

    @RequestMapping("/search")
    @GlobalInterceptor
    public ResponseVo searchUserContact(HttpServletRequest request, @NotEmpty String contactID) {

        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfoDto(request);
        UserContactSearchResultDto searchResultDto = userContactService.searchContact(tokenUserInfoDto.getUserId(), contactID);

        return ResponseVo.success("搜索成功", searchResultDto);
    }

    @RequestMapping("/add")
    @GlobalInterceptor
    public ResponseVo addContact(HttpServletRequest request, @NotEmpty String contactID, String applyInfo) throws BusinessException {
        TokenUserInfoDto userInfoDto = getTokenUserInfoDto(request);

        userContactService.applyNewFriend(userInfoDto, contactID, applyInfo);

        return ResponseVo.success("已发送申请", null);
    }


    public TokenUserInfoDto getTokenUserInfoDto(HttpServletRequest request) {
        String token = request.getHeader("token");
        TokenUserInfoDto userInfoDto = redisService.getUserInfoFromToken(token);

        return userInfoDto;
    }
}
