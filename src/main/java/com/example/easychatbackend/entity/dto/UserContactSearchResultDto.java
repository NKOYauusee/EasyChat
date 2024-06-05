package com.example.easychatbackend.entity.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class UserContactSearchResultDto {
    private String contactID;
    private String name;
    private String notice;
    private Boolean status;

}
