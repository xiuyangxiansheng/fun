package com.springboot.fun.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UsedCommentReply {

  private String rId;
  private String commentId;
  private String replyId;
  private Integer replyType;
  private String commentContentReply;
  private String commentUserIdReply;
  private Integer commentUserSexReply;
  private String commentUserNameReply;
  private String commentUserUrlReply;
  private String commentToUserIdReply;
  private Integer commentToUserSexReply;
  private String commentToUserNameReply;
  private String commentToUserUrlReply;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date commentCreateTimeReply;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date commentUpdateTimeReply;

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }

    public Integer getReplyType() {
        return replyType;
    }

    public void setReplyType(Integer replyType) {
        this.replyType = replyType;
    }

    public String getCommentContentReply() {
        return commentContentReply;
    }

    public void setCommentContentReply(String commentContentReply) {
        this.commentContentReply = commentContentReply;
    }

    public String getCommentUserIdReply() {
        return commentUserIdReply;
    }

    public void setCommentUserIdReply(String commentUserIdReply) {
        this.commentUserIdReply = commentUserIdReply;
    }

    public Integer getCommentUserSexReply() {
        return commentUserSexReply;
    }

    public void setCommentUserSexReply(Integer commentUserSexReply) {
        this.commentUserSexReply = commentUserSexReply;
    }

    public String getCommentUserNameReply() {
        return commentUserNameReply;
    }

    public void setCommentUserNameReply(String commentUserNameReply) {
        this.commentUserNameReply = commentUserNameReply;
    }

    public String getCommentUserUrlReply() {
        return commentUserUrlReply;
    }

    public void setCommentUserUrlReply(String commentUserUrlReply) {
        this.commentUserUrlReply = commentUserUrlReply;
    }

    public String getCommentToUserIdReply() {
        return commentToUserIdReply;
    }

    public void setCommentToUserIdReply(String commentToUserIdReply) {
        this.commentToUserIdReply = commentToUserIdReply;
    }

    public Integer getCommentToUserSexReply() {
        return commentToUserSexReply;
    }

    public void setCommentToUserSexReply(Integer commentToUserSexReply) {
        this.commentToUserSexReply = commentToUserSexReply;
    }

    public String getCommentToUserNameReply() {
        return commentToUserNameReply;
    }

    public void setCommentToUserNameReply(String commentToUserNameReply) {
        this.commentToUserNameReply = commentToUserNameReply;
    }

    public String getCommentToUserUrlReply() {
        return commentToUserUrlReply;
    }

    public void setCommentToUserUrlReply(String commentToUserUrlReply) {
        this.commentToUserUrlReply = commentToUserUrlReply;
    }

    public Date getCommentCreateTimeReply() {
        return commentCreateTimeReply;
    }

    public void setCommentCreateTimeReply(Date commentCreateTimeReply) {
        this.commentCreateTimeReply = commentCreateTimeReply;
    }

    public Date getCommentUpdateTimeReply() {
        return commentUpdateTimeReply;
    }

    public void setCommentUpdateTimeReply(Date commentUpdateTimeReply) {
        this.commentUpdateTimeReply = commentUpdateTimeReply;
    }
}
