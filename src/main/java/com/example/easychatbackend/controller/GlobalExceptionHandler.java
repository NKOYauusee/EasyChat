package com.example.easychatbackend.controller;


import com.example.easychatbackend.entity.vo.ResponseVo;
import com.example.easychatbackend.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理 BusinessException 异常
     */
    @ExceptionHandler(value = BusinessException.class)
    public ResponseVo businessExceptionHandler(BusinessException e) {
        logger.info(" >>>>> 业务异常 errCode:" + e.getCode() + " msg:" + e.getMsg());
        return ResponseVo.fail(e);
    }
}
