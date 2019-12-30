package com.springboot.fun.entity;

/*
* 校园动态的发布的评论和回复的列表
* */
public class UsedCommentReplyMessage {

    private String id;//发布的主键id
    private String userId;//用户id
    private String commentId;//评论的主键id
    private String replyId;//回复的主键id
    private String nickName;//用户昵称
    private String userUrl;//用户头像
    private String nickNameReply;//用户昵称
    private String userUrlReply;//用户头像
    private String nickNameToReply;//回复的用户昵称
    private String userUrlToReply;//回复的用户头像
    private String nickNameLike;//点赞的用户昵称
    private String userUrlLike;//点赞的用户头像
    private String content;//评论的内容
    private String contentReply;//回复的内容
    private String usedMessage;//发布的内容

    public String getNickNameLike() {
        return nickNameLike;
    }

    public void setNickNameLike(String nickNameLike) {
        this.nickNameLike = nickNameLike;
    }

    public String getUserUrlLike() {
        return userUrlLike;
    }

    public void setUserUrlLike(String userUrlLike) {
        this.userUrlLike = userUrlLike;
    }

    public String getNickNameToReply() {
        return nickNameToReply;
    }


    public void setNickNameToReply(String nickNameToReply) {
        this.nickNameToReply = nickNameToReply;
    }

    public String getUserUrlToReply() {
        return userUrlToReply;
    }

    public void setUserUrlToReply(String userUrlToReply) {
        this.userUrlToReply = userUrlToReply;
    }

    public String getNickNameReply() {
        return nickNameReply;
    }

    public void setNickNameReply(String nickNameReply) {
        this.nickNameReply = nickNameReply;
    }

    public String getUserUrlReply() {
        return userUrlReply;
    }

    public void setUserUrlReply(String userUrlReply) {
        this.userUrlReply = userUrlReply;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentReply() {
        return contentReply;
    }

    public void setContentReply(String contentReply) {
        this.contentReply = contentReply;
    }

    public String getUsedMessage() {
        return usedMessage;
    }

    public void setUsedMessage(String usedMessage) {
        this.usedMessage = usedMessage;
    }

    public UsedCommentReplyMessage() {
    }
}
