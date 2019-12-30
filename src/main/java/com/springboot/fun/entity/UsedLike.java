package com.springboot.fun.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UsedLike {
/*
* 二手点赞
* */
  private String id;//主键
  private String likeUsedId;//关联发布二手id

  private String likeUserId;//发布用户id
    private String likeUserUrl;//发布用户头像
    private String likeUserNikeName;//发布用户昵称

  private String likeUserDoId;//点赞人的id
    private String likeUseDorUrl;//点赞人的头像
    private String likeUserDoNikeName;//点赞人的昵称
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date likeCreateTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date likeUpdateTime;//更新时间


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLikeUsedId() {
        return likeUsedId;
    }

    public void setLikeUsedId(String likeUsedId) {
        this.likeUsedId = likeUsedId;
    }

    public String getLikeUserId() {
        return likeUserId;
    }

    public void setLikeUserId(String likeUserId) {
        this.likeUserId = likeUserId;
    }

    public String getLikeUserUrl() {
        return likeUserUrl;
    }

    public void setLikeUserUrl(String likeUserUrl) {
        this.likeUserUrl = likeUserUrl;
    }

    public String getLikeUserNikeName() {
        return likeUserNikeName;
    }

    public void setLikeUserNikeName(String likeUserNikeName) {
        this.likeUserNikeName = likeUserNikeName;
    }

    public String getLikeUserDoId() {
        return likeUserDoId;
    }

    public void setLikeUserDoId(String likeUserDoId) {
        this.likeUserDoId = likeUserDoId;
    }

    public String getLikeUseDorUrl() {
        return likeUseDorUrl;
    }

    public void setLikeUseDorUrl(String likeUseDorUrl) {
        this.likeUseDorUrl = likeUseDorUrl;
    }

    public String getLikeUserDoNikeName() {
        return likeUserDoNikeName;
    }

    public void setLikeUserDoNikeName(String likeUserDoNikeName) {
        this.likeUserDoNikeName = likeUserDoNikeName;
    }

    public Date getLikeCreateTime() {
        return likeCreateTime;
    }

    public void setLikeCreateTime(Date likeCreateTime) {
        this.likeCreateTime = likeCreateTime;
    }

    public Date getLikeUpdateTime() {
        return likeUpdateTime;
    }

    public void setLikeUpdateTime(Date likeUpdateTime) {
        this.likeUpdateTime = likeUpdateTime;
    }

    public UsedLike() {
    }
}
