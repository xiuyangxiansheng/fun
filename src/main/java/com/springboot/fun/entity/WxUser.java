package com.springboot.fun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
/*
* 微信用户
* */
public class WxUser {
    @TableId(value = "id", type = IdType.UUID)//指定自增策略
  private String id;
    private String userId;//用户唯一id
    private String momentsUrl;//朋友圈图
    private String userName;//用户姓名
    private String userPhone;//电话
    private String userAddress;//用户地址
    private String shareId;//好友
  private String openid;
  private String unionid;
  private String nickname;//昵称

  private Integer subscribe;
  private Integer subscribeAt;
  private Integer sex;//性别
  private String country;//国家
  private String province;//省份
  private String city;//城市
  private String headimgurl;//头像

  private String code;

  private String wxid;
  private String opBy;
  private Integer opAt;
  private Integer delFlag;
  private String pId;
  private String schoolId;//学校id
  private String schoolName;//学校名称
  private Integer status;//用户状态（正常为1，拉黑为0）
  private Integer sortRank;
  private String remark;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date createTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date updateTime;//更新时间
  private String description;

  private BigDecimal coin;//用户的动力币
  private BigDecimal totalMoney;//历史总额
  private int usedNum;//发布二手的数量
  private  String supplierState;//是否为商家
    private Integer accredit;//是否授权，授权为1，不授权为0
    private Integer jigaungState;//是否注册极光，注册1，未注册0

  private Integer memberState;//是否是会员，1是；0不是
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
    private Date memberStartTime;//会员开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
    private Date memberDeadlineTime;//会员截止时间
    private Integer memberNum;//邀请的人数
    public WxUser() {
    }

    public Integer getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(Integer memberNum) {
        this.memberNum = memberNum;
    }

    public Integer getMemberState() {
        return memberState;
    }

    public void setMemberState(Integer memberState) {
        this.memberState = memberState;
    }

    public Date getMemberStartTime() {
        return memberStartTime;
    }

    public void setMemberStartTime(Date memberStartTime) {
        this.memberStartTime = memberStartTime;
    }

    public Date getMemberDeadlineTime() {
        return memberDeadlineTime;
    }

    public void setMemberDeadlineTime(Date memberDeadlineTime) {
        this.memberDeadlineTime = memberDeadlineTime;
    }

    public Integer getJigaungState() {
        return jigaungState;
    }

    public void setJigaungState(Integer jigaungState) {
        this.jigaungState = jigaungState;
    }

    public String getSupplierState() {
        return supplierState;
    }

    public void setSupplierState(String supplierState) {
        this.supplierState = supplierState;
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

    public String getMomentsUrl() {
        return momentsUrl;
    }

    public void setMomentsUrl(String momentsUrl) {
        this.momentsUrl = momentsUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    public Integer getSubscribeAt() {
        return subscribeAt;
    }

    public void setSubscribeAt(Integer subscribeAt) {
        this.subscribeAt = subscribeAt;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getWxid() {
        return wxid;
    }

    public void setWxid(String wxid) {
        this.wxid = wxid;
    }

    public String getOpBy() {
        return opBy;
    }

    public void setOpBy(String opBy) {
        this.opBy = opBy;
    }

    public Integer getOpAt() {
        return opAt;
    }

    public void setOpAt(Integer opAt) {
        this.opAt = opAt;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
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

    public BigDecimal getCoin() {
        return coin;
    }

    public void setCoin(BigDecimal coin) {
        this.coin = coin;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getUsedNum() {
        return usedNum;
    }

    public void setUsedNum(int usedNum) {
        this.usedNum = usedNum;
    }

    public Integer getAccredit() {
        return accredit;
    }

    public void setAccredit(Integer accredit) {
        this.accredit = accredit;
    }
}
