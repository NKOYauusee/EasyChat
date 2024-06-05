package com.example.easychatbackend.aspect;


import com.example.easychatbackend.annotation.GlobalInterceptor;
import com.example.easychatbackend.entity.constants.Constants;
import com.example.easychatbackend.entity.dto.TokenUserInfoDto;
import com.example.easychatbackend.entity.enums.ResponseCodeEnum;
import com.example.easychatbackend.exception.BusinessException;
import com.example.easychatbackend.utils.RedisUtils;
import com.example.easychatbackend.utils.StringUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;


@Component
@Aspect
public class GlobalOperationAspect {

    @Resource
    RedisUtils redisUtils;

    private static final Logger logger = LoggerFactory.getLogger(GlobalOperationAspect.class);


    @Before("@annotation(com.example.easychatbackend.annotation.GlobalInterceptor)")
    public void interceptorDo(JoinPoint point) throws BusinessException {
        try {
            Method method = ((MethodSignature)point.getSignature()).getMethod();
            GlobalInterceptor interceptor = method.getAnnotation(GlobalInterceptor.class);

            if(interceptor == null){
                return;
            }
            if(interceptor.checkLogin() || interceptor.checkAdmin()){
                checkLogin(interceptor.checkAdmin());
            }
        } catch (BusinessException e){
            throw e;
        } catch (Exception e){
            throw new BusinessException(ResponseCodeEnum.CODE_500);
        }
    }

    private void checkLogin(boolean checkAdmin) throws Exception {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String token = request.getHeader("token");

        if(StringUtils.isEmpty(token)){
            logger.error("用户未登录");
            throw new BusinessException(ResponseCodeEnum.CODE_901);
        }

        TokenUserInfoDto userInfoDto = (TokenUserInfoDto) redisUtils.getValue(Constants.REDIS_KEY_WS_TOKEN + token);

        if(userInfoDto == null){
            logger.error("用户未登录");
            throw new BusinessException(ResponseCodeEnum.CODE_901);
        }
        if(checkAdmin){
            logger.error("用户不是管理员");
            throw new BusinessException(ResponseCodeEnum.CODE_404);
        }
    }
}
