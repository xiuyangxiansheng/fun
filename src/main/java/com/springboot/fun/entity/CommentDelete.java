package com.springboot.fun.entity;

public class CommentDelete {
    private String commentId;//评论主键
    private String CommentReply;//回复主键
    private Integer state;//删除评论1，删除回复2

    public CommentDelete() {
        super();
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getCommentReply() {
        return CommentReply;
    }

    public void setCommentReply(String commentReply) {
        CommentReply = commentReply;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
