package com.example.easychatbackend.entity.po;

import java.util.Date;

public class GroupInfo {
    private String groupId;

    private String groupName;

    private String groupownerId;

    private Date createTime;

    private String groupNotice;

    private Boolean joinType;

    private Boolean status;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getGroupownerId() {
        return groupownerId;
    }

    public void setGroupownerId(String groupownerId) {
        this.groupownerId = groupownerId == null ? null : groupownerId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getGroupNotice() {
        return groupNotice;
    }

    public void setGroupNotice(String groupNotice) {
        this.groupNotice = groupNotice == null ? null : groupNotice.trim();
    }

    public Boolean getJoinType() {
        return joinType;
    }

    public void setJoinType(Boolean joinType) {
        this.joinType = joinType;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}