package com.springboot.fun.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class UsedSubComment {
/*
* 校园二手评论
* */
  private String  subId;//主键
  private String commentSubUsedId;//关联二手表Id
  private Integer commentSubType;//评论类型：二手评论1
  private String commentSubContent;//评论内容

  private String commentSubUserId;//评论用户id
    private String commentSubUserName;//评论人昵称
    private String commentSubUserUrl;//评论人头像
    private Integer commentSubUserSex;//评论人性别

  private List<UsedComment> commentList;//评论列表

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date commentSubCreateTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date commentSubUpdateTime;//更新时间

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getCommentSubUsedId() {
        return commentSubUsedId;
    }

    public void setCommentSubUsedId(String commentSubUsedId) {
        this.commentSubUsedId = commentSubUsedId;
    }

    public Integer getCommentSubType() {
        return commentSubType;
    }

    public void setCommentSubType(Integer commentSubType) {
        this.commentSubType = commentSubType;
    }

    public String getCommentSubContent() {
        return commentSubContent;
    }

    public void setCommentSubContent(String commentSubContent) {
        this.commentSubContent = commentSubContent;
    }

    public String getCommentSubUserId() {
        return commentSubUserId;
    }

    public void setCommentSubUserId(String commentSubUserId) {
        this.commentSubUserId = commentSubUserId;
    }

    public String getCommentSubUserName() {
        return commentSubUserName;
    }

    public void setCommentSubUserName(String commentSubUserName) {
        this.commentSubUserName = commentSubUserName;
    }

    public String getCommentSubUserUrl() {
        return commentSubUserUrl;
    }

    public void setCommentSubUserUrl(String commentSubUserUrl) {
        this.commentSubUserUrl = commentSubUserUrl;
    }

    public Integer getCommentSubUserSex() {
        return commentSubUserSex;
    }

    public void setCommentSubUserSex(Integer commentSubUserSex) {
        this.commentSubUserSex = commentSubUserSex;
    }

    public List<UsedComment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<UsedComment> commentList) {
        this.commentList = commentList;
    }

    public Date getCommentSubCreateTime() {
        return commentSubCreateTime;
    }

    public void setCommentSubCreateTime(Date commentSubCreateTime) {
        this.commentSubCreateTime = commentSubCreateTime;
    }

    public Date getCommentSubUpdateTime() {
        return commentSubUpdateTime;
    }

    public void setCommentSubUpdateTime(Date commentSubUpdateTime) {
        this.commentSubUpdateTime = commentSubUpdateTime;
    }

    public UsedSubComment() {
        super();
    }
}
