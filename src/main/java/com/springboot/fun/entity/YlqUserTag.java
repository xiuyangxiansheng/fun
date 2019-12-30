package com.springboot.fun.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
/*
* 用户信息标记，是否完成了任务
* */
public class YlqUserTag {

  private String id;
  private String userId;//用户id
  private Integer newUsedTag;//是否领取了当天的发布任务红包0未领取1已领取(每天一次)
  private Integer collectionTag;//是否领取了收藏小程序奖励0未领取1已领取
  private Integer newUserRedpacketTag;//是否领取了新用户红包奖励0未领取1已领取
  private Integer appointmentSuccessTag;//完成优惠劵领取。未完成0，完成1(每天一次）
  private Integer shareGroupTag;//分享校园直供,未完成0，完成1(每天一次）
  private Integer shareSchoolTag;//分享校园福利,未完成0，完成1(每天一次）
  private Integer shareGoodTag;//分享好物兑换,未完成0，完成1(每天一次）
  private Integer wechatAccountTag;//关注公众号,未完成0，完成1
  private Integer incentiveVideoTag;//激励视频,未完成0，完成1，2,3
  private Integer status;//0关闭1开启
  private Integer sortRank;//排序
  private String remark;//备注
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date createTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date updateTime;//更新时间
  private String description;//


    public YlqUserTag() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getNewUsedTag() {
        return newUsedTag;
    }

    public void setNewUsedTag(Integer newUsedTag) {
        this.newUsedTag = newUsedTag;
    }

    public Integer getCollectionTag() {
        return collectionTag;
    }

    public void setCollectionTag(Integer collectionTag) {
        this.collectionTag = collectionTag;
    }

    public Integer getNewUserRedpacketTag() {
        return newUserRedpacketTag;
    }

    public void setNewUserRedpacketTag(Integer newUserRedpacketTag) {
        this.newUserRedpacketTag = newUserRedpacketTag;
    }

    public Integer getAppointmentSuccessTag() {
        return appointmentSuccessTag;
    }

    public void setAppointmentSuccessTag(Integer appointmentSuccessTag) {
        this.appointmentSuccessTag = appointmentSuccessTag;
    }

    public Integer getShareGroupTag() {
        return shareGroupTag;
    }

    public void setShareGroupTag(Integer shareGroupTag) {
        this.shareGroupTag = shareGroupTag;
    }

    public Integer getShareSchoolTag() {
        return shareSchoolTag;
    }

    public void setShareSchoolTag(Integer shareSchoolTag) {
        this.shareSchoolTag = shareSchoolTag;
    }

    public Integer getShareGoodTag() {
        return shareGoodTag;
    }

    public void setShareGoodTag(Integer shareGoodTag) {
        this.shareGoodTag = shareGoodTag;
    }

    public Integer getWechatAccountTag() {
        return wechatAccountTag;
    }

    public void setWechatAccountTag(Integer wechatAccountTag) {
        this.wechatAccountTag = wechatAccountTag;
    }

    public Integer getIncentiveVideoTag() {
        return incentiveVideoTag;
    }

    public void setIncentiveVideoTag(Integer incentiveVideoTag) {
        this.incentiveVideoTag = incentiveVideoTag;
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
}
