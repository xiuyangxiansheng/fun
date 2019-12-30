package com.springboot.fun.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UsedComment {
/*
* 校园二手评论
* */
  private String id;//主键
  private String commentUsedId;//关联二手表Id
  private Integer commentType;//评论类型：二手评论1
  private String commentContent;//评论内容

  private String commentUserId;//评论用户id
    private String commentUserName;//评论人昵称
    private String commentUserUrl;//评论人头像
    private Integer commentUserSex;//评论人性别
    private Integer commentNum;//评论数量

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date commentCreateTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date commentUpdateTime;//更新时间
     private UsedCommentReply usedCommentReply;//回复列表

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommentUsedId() {
        return commentUsedId;
    }

    public void setCommentUsedId(String commentUsedId) {
        this.commentUsedId = commentUsedId;
    }

    public Integer getCommentType() {
        return commentType;
    }

    public void setCommentType(Integer commentType) {
        this.commentType = commentType;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(String commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getCommentUserName() {
        return commentUserName;
    }

    public void setCommentUserName(String commentUserName) {
        this.commentUserName = commentUserName;
    }

    public String getCommentUserUrl() {
        return commentUserUrl;
    }

    public void setCommentUserUrl(String commentUserUrl) {
        this.commentUserUrl = commentUserUrl;
    }

    public Integer getCommentUserSex() {
        return commentUserSex;
    }

    public void setCommentUserSex(Integer commentUserSex) {
        this.commentUserSex = commentUserSex;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Date getCommentCreateTime() {
        return commentCreateTime;
    }

    public void setCommentCreateTime(Date commentCreateTime) {
        this.commentCreateTime = commentCreateTime;
    }

    public Date getCommentUpdateTime() {
        return commentUpdateTime;
    }

    public void setCommentUpdateTime(Date commentUpdateTime) {
        this.commentUpdateTime = commentUpdateTime;
    }

    public UsedCommentReply getUsedCommentReply() {
        return usedCommentReply;
    }

    public void setUsedCommentReply(UsedCommentReply usedCommentReply) {
        this.usedCommentReply = usedCommentReply;
    }

    public UsedComment() {
        super();
    }
}
