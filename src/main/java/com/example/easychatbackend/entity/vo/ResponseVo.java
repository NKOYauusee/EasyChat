package com.example.easychatbackend.entity.vo;

import com.alibaba.fastjson2.JSON;
import com.example.easychatbackend.exception.BusinessException;

import java.io.Serializable;

public class ResponseVo<T> implements Serializable {
    private final String status;
    private final int code;
    private final String info;
    private T data;

    private ResponseVo() {
        this.status = "success";
        this.code = 200;
        this.info = "请求成功";
        this.data = null;
    }

    private ResponseVo(String status, int code, String info, T data) {
        this.status = status;
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public ResponseVo(BusinessException e) {
        this.status = "FAIL";
        this.code = e.getCode();
        this.info = e.getMsg();
        this.data = null;
    }

    public static ResponseVo success() {
        return new ResponseVo<>();
    }

    public static <E> ResponseVo success(String info, E data) {
        return new ResponseVo<>("success", 200, info, data);
    }

    public static ResponseVo info(String info) {
        return new ResponseVo("success", 200, info, null);
    }

    public static ResponseVo fail(String info) {
        return new ResponseVo<>("fail", 404, info, null);
    }

    private static ResponseVo fail(BusinessException e) {
        return new ResponseVo<>("fail", e.getCode(), e.getMsg(), null);
    }

    public static ResponseVo fail(Exception e) {
        if(e instanceof BusinessException)
            return ResponseVo.fail((BusinessException) e);
        return new ResponseVo<>("fail", 404, e.getMessage(), null);
    }

    public String getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public T getData() {
        return data;
    }
}
