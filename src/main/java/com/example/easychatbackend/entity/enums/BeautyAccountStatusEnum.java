package com.example.easychatbackend.entity.enums;

public enum BeautyAccountStatusEnum {
    USE(1, "已使用"),
    NO_USE(0, "未使用");

    private String desc;
    private Integer status;

    BeautyAccountStatusEnum(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static BeautyAccountStatusEnum getByStatus(Integer status) {
        for (BeautyAccountStatusEnum statusEnum : BeautyAccountStatusEnum.values()) {
            if (statusEnum.status.equals(status))
                return statusEnum;
        }

        return null;
    }


    public String getDesc() {
        return desc;
    }

    public Integer getStatus() {
        return status;
    }
}
