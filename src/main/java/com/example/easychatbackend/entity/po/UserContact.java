package com.example.easychatbackend.entity.po;

import java.util.Date;

public class UserContact extends UserContactKey {
    private Boolean contactType;

    private Date createTime;

    private Boolean status;

    private Date lastUpdateTime;

    public Boolean getContactType() {
        return contactType;
    }

    public void setContactType(Boolean contactType) {
        this.contactType = contactType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}