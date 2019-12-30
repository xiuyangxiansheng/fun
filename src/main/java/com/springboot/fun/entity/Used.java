package com.springboot.fun.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Used {
/*
* 校园二手
*
* */
  private String id;//主键
  private String usedUserId;//关联用户id
  private String usedName;//二手描述
  private String usedUrl;//二手图片
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date usedIssueTime;//发布时间
  private Integer usedType;//类型：二手；求购
    private String usedTypeName;//类型名称
  private Integer usedLike;//点赞
    private Integer usedLikeState;//点赞状态
  private Integer usedBrowse;//浏览量
  private Integer usedComplain;//投诉
  private Integer usedState;//状态：审核通过1；审核不通过
  private String usedReason;//下架原因
  private String usedPrice;//校园二手价格（或者自定义的）
    private Integer usedPriceState;//价格状态
  private String usedLableNum;//标签Num(暂时不用)
  private String usedLable;//标签
  private String phone;//联系方式

    private String nickname;//用户名
    private String headimgurl;//用户头像
    private Integer sex;//性别
    private String schoolId;//关联校区
    private String schoolName;//关联学校名称

    private UsedComment commentList;//评论内容
        private Integer commentNum;//评论数量

    private Integer usedDeleteState;//用户删除状态
    private Integer usedOutState;//用户上下架状态，正常1，下架2
    /*private List<UsedComment> commentList;//评论内容（多对多）*/
    private Integer usedVideoState;//是否是视频，是1，不是0
    private Integer usedSort;//置顶,为1置顶，为10不是置顶
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date usedCreateTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date usedUpdateTime;//更新时间


    public Integer getUsedSort() {
        return usedSort;
    }

    public void setUsedSort(Integer usedSort) {
        this.usedSort = usedSort;
    }

    public Integer getUsedVideoState() {
        return usedVideoState;
    }

    public void setUsedVideoState(Integer usedVideoState) {
        this.usedVideoState = usedVideoState;
    }

    public Integer getUsedOutState() {
        return usedOutState;
    }

    public void setUsedOutState(Integer usedOutState) {
        this.usedOutState = usedOutState;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsedUserId() {
        return usedUserId;
    }

    public void setUsedUserId(String usedUserId) {
        this.usedUserId = usedUserId;
    }

    public String getUsedName() {
        return usedName;
    }

    public void setUsedName(String usedName) {
        this.usedName = usedName;
    }

    public String getUsedUrl() {
        return usedUrl;
    }

    public void setUsedUrl(String usedUrl) {
        this.usedUrl = usedUrl;
    }

    public Date getUsedIssueTime() {
        return usedIssueTime;
    }

    public void setUsedIssueTime(Date usedIssueTime) {
        this.usedIssueTime = usedIssueTime;
    }

    public Integer getUsedType() {
        return usedType;
    }

    public void setUsedType(Integer usedType) {
        this.usedType = usedType;
    }

    public String getUsedTypeName() {
        return usedTypeName;
    }

    public void setUsedTypeName(String usedTypeName) {
        this.usedTypeName = usedTypeName;
    }

    public Integer getUsedLike() {
        return usedLike;
    }

    public void setUsedLike(Integer usedLike) {
        this.usedLike = usedLike;
    }

    public Integer getUsedLikeState() {
        return usedLikeState;
    }

    public void setUsedLikeState(Integer usedLikeState) {
        this.usedLikeState = usedLikeState;
    }

    public Integer getUsedBrowse() {
        return usedBrowse;
    }

    public void setUsedBrowse(Integer usedBrowse) {
        this.usedBrowse = usedBrowse;
    }

    public Integer getUsedComplain() {
        return usedComplain;
    }

    public void setUsedComplain(Integer usedComplain) {
        this.usedComplain = usedComplain;
    }

    public Integer getUsedState() {
        return usedState;
    }

    public void setUsedState(Integer usedState) {
        this.usedState = usedState;
    }

    public String getUsedReason() {
        return usedReason;
    }

    public void setUsedReason(String usedReason) {
        this.usedReason = usedReason;
    }

    public String getUsedPrice() {
        return usedPrice;
    }

    public void setUsedPrice(String usedPrice) {
        this.usedPrice = usedPrice;
    }

    public Integer getUsedPriceState() {
        return usedPriceState;
    }

    public void setUsedPriceState(Integer usedPriceState) {
        this.usedPriceState = usedPriceState;
    }

    public String getUsedLableNum() {
        return usedLableNum;
    }

    public void setUsedLableNum(String usedLableNum) {
        this.usedLableNum = usedLableNum;
    }

    public String getUsedLable() {
        return usedLable;
    }

    public void setUsedLable(String usedLable) {
        this.usedLable = usedLable;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public UsedComment getCommentList() {
        return commentList;
    }

    public void setCommentList(UsedComment commentList) {
        this.commentList = commentList;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getUsedDeleteState() {
        return usedDeleteState;
    }

    public void setUsedDeleteState(Integer usedDeleteState) {
        this.usedDeleteState = usedDeleteState;
    }

    public Date getUsedCreateTime() {
        return usedCreateTime;
    }

    public void setUsedCreateTime(Date usedCreateTime) {
        this.usedCreateTime = usedCreateTime;
    }

    public Date getUsedUpdateTime() {
        return usedUpdateTime;
    }

    public void setUsedUpdateTime(Date usedUpdateTime) {
        this.usedUpdateTime = usedUpdateTime;
    }

    public Used() {
    }
}
