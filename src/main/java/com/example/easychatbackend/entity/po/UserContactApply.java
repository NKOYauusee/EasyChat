package com.example.easychatbackend.entity.po;

public class UserContactApply {
    private Integer applyId;

    private String applyUserId;

    private String receiverUserId;

    private Boolean contactType;

    private String contactId;

    private Long lastApplyTime;

    private Boolean status;

    private String applyInfo;

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId == null ? null : applyUserId.trim();
    }

    public String getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(String receiverUserId) {
        this.receiverUserId = receiverUserId == null ? null : receiverUserId.trim();
    }

    public Boolean getContactType() {
        return contactType;
    }

    public void setContactType(Boolean contactType) {
        this.contactType = contactType;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId == null ? null : contactId.trim();
    }

    public Long getLastApplyTime() {
        return lastApplyTime;
    }

    public void setLastApplyTime(Long lastApplyTime) {
        this.lastApplyTime = lastApplyTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getApplyInfo() {
        return applyInfo;
    }

    public void setApplyInfo(String applyInfo) {
        this.applyInfo = applyInfo == null ? null : applyInfo.trim();
    }
}