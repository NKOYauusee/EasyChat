package com.example.easychatbackend.controller;


import com.example.easychatbackend.entity.constants.Constants;
import com.example.easychatbackend.entity.dto.TokenUserInfoDto;
import com.example.easychatbackend.entity.vo.ResponseVo;
import com.example.easychatbackend.exception.BusinessException;
import com.example.easychatbackend.service.UserInfoService;
import com.example.easychatbackend.service.impl.UserInfoServiceImpl;
import com.example.easychatbackend.utils.RedisUtils;
import com.wf.captcha.ArithmeticCaptcha;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/account")
@Validated
public class AccountController {
    @Resource
    private RedisUtils redisUtils;

    @Resource
    private UserInfoService userInfoService;

    //验证码图片获取
    @RequestMapping("/checkcode")
    public ResponseVo checkCode() {
        String checkAns = String.valueOf(new Random().nextInt(10));
        String checkCodeKey = UUID.randomUUID().toString();

        redisUtils.setExpiredKey(Constants.REDIS_KEY_CHECK_CODE + checkCodeKey, checkAns, Constants.REDIS_TIME_1MIN * 10);

        Map<String, String> res = new HashMap<>();

        res.put("checkAns", checkAns);
        res.put("checkCodeKey", checkCodeKey);

        System.out.println(res);
        return ResponseVo.success("请求验证码成功", res);
    }

    /**
     * 注册接口
     * 参数的传递方式为 form-data
     *
     * @param nickname     昵称
     * @param email        邮箱
     * @param password     密码
     * @param checkCode    验证码
     * @param checkCodeKey 用户凭证
     */
    @RequestMapping("/register")
    public ResponseVo register(
            @NotEmpty String nickname,
            @NotEmpty @Email String email,
            @NotEmpty String password,
            @NotEmpty String checkCode,
            @NotEmpty String checkCodeKey) {

        try {
            captchaCodeCheck(checkCode, checkCodeKey);
            userInfoService.register(email, password, nickname);
        } catch (Exception e) {
            ResponseVo.fail(e);
        } finally {
            redisUtils.delete(Constants.REDIS_KEY_CHECK_CODE + checkCodeKey);
        }

        return ResponseVo.success("注册成功", null);
    }


    /**
     * 登录接口
     *
     * @param email
     * @param password
     * @param checkCode
     * @param checkCodeKey
     * @return
     */
    @RequestMapping("/login")
    public ResponseVo login(
            @NotEmpty @Email String email,
            @NotEmpty String password,
            @NotEmpty String checkCode,
            @NotEmpty String checkCodeKey) {

        try {
            captchaCodeCheck(checkCode, checkCodeKey);
            TokenUserInfoDto userInfoDto = userInfoService.login(email, password);
            //TODO

            return ResponseVo.success("登录成功", userInfoDto);
        } catch (Exception e) {
            return ResponseVo.fail(e);
        } finally {
            //删除缓存中的图形验证码数据
            redisUtils.delete(Constants.REDIS_KEY_CHECK_CODE + checkCodeKey);
        }
    }


    /**
     * 验证码判断
     */
    private void captchaCodeCheck(String checkCode, String checkCodeKey) throws BusinessException {
        if (checkCode.equals(redisUtils.getValue(Constants.REDIS_KEY_CHECK_CODE + checkCodeKey)))
            return;

        throw new BusinessException(404, "图形验证码错误");
    }
}
