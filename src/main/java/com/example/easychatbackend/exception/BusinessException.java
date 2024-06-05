package com.example.easychatbackend.exception;

import com.example.easychatbackend.entity.enums.ResponseCodeEnum;

public class BusinessException extends Exception{
    private Integer code;
    private String msg;

    public BusinessException(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }


    public BusinessException(ResponseCodeEnum responseCodeEnum) {
        this.code = responseCodeEnum.getResCode();
        this.msg = responseCodeEnum.getMsg();
    }

    public BusinessException(String msg) {
        this.code = ResponseCodeEnum.CODE_600.getResCode();
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
