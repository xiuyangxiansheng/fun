package com.springboot.fun.entity;

import java.util.Date;

public class YlqFriend {
/*
* 好友关系
* */
  private String uUid;
  private String shareId;//用户自己的
  private String friendUserId;//朋友的id
  private Integer type;
  private Integer status;
  private Integer sortRank;
  private String remark;
  private Date createTime;
  private Date updateTime;
  private String description;

    public String getuUid() {
        return uUid;
    }

    public void setuUid(String uUid) {
        this.uUid = uUid;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public String getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(String friendUserId) {
        this.friendUserId = friendUserId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSortRank() {
        return sortRank;
    }

    public void setSortRank(Integer sortRank) {
        this.sortRank = sortRank;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public YlqFriend() {
        super();
    }
}
