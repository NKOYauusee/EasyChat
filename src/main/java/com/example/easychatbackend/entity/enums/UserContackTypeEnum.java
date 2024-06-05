package com.example.easychatbackend.entity.enums;


import com.example.easychatbackend.utils.StringUtils;

public enum UserContackTypeEnum {
    USER(0, "U", "好友"),
    GROUP(1, "G", "群组");

    private Integer type;
    private String prefix;
    private String desc;

    UserContackTypeEnum(int type, String prefix, String desc) {
        this.type = type;
        this.prefix = prefix;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getDesc() {
        return desc;
    }


    public static UserContackTypeEnum getByName(String name) {
        try {
            if (StringUtils.isEmpty(name)) {
                return null;
            }
            return UserContackTypeEnum.valueOf(name.toUpperCase());
        } catch (Exception e) {
            return null;
        }

    }

    public static UserContackTypeEnum getByPrefix(String prefix) {
        try {
            if (StringUtils.isEmpty(prefix)) {
                return null;
            }

            prefix = prefix.substring(0, 1);

            for (UserContackTypeEnum userContackTypeEnum : UserContackTypeEnum.values()) {
                if (userContackTypeEnum.getPrefix().equals(prefix))
                    return userContackTypeEnum;
            }

            return null;
        } catch (Exception e) {
            return null;
        }

    }
}
