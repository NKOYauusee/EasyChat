package com.example.easychatbackend.entity.enums;

public enum UserStatusEnum {
    ENABLE(1, "启用", true),
    DISABLE(0, "禁用", false);

    private Integer status;
    private String desc;
    private boolean isEnable;

    UserStatusEnum(int status, String desc, boolean isEnable) {
        this.desc = desc;
        this.status = status;
        this.isEnable = isEnable;
    }

    public static UserStatusEnum getByStatus(Integer status) {
        for (UserStatusEnum item : UserStatusEnum.values()) {
            if (item.getStatus().equals(status))
                return item;
        }

        return null;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
