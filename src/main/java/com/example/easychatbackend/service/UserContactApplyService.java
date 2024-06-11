package com.example.easychatbackend.service;


public interface UserContactApplyService {
    void addApply(String applyUserID, String receiverId, String contactID,Integer contactType);
}
