package com.example.easychatbackend.entity.enums;

public enum ResponseCodeEnum {
    CODE_200(200,"请求成功"),
    CODE_404(404,"请求地址不存在"),
    CODE_600(600,"请求参数错误"),
    CODE_601(601,"信息已经存在"),
    CODE_901(901,"登录超时"),
    CODE_500(500,"服务器错误");


    private Integer resCode;
    private String msg;
    ResponseCodeEnum(int resCode, String msg) {
        this.resCode = resCode;
        this.msg = msg;
    }

    public Integer getResCode() {
        return resCode;
    }

    public String getMsg() {
        return msg;
    }
}
