package com.springboot.fun.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
/*用户*/
public class Member {

    private int id;//id
    private String memberId;//用户id
    private String shareMemberId;//邀请人id
    private String activityId;//关联活动配置id
    private String code;//code
    private String openId;//openID
    private String nikeName;//昵称
    private Integer sex;//性别
    private String province;//省份
    private String city;//城市
    private String imgUrl;//头像地址
    private String img;//头像
    private Integer credits;//积分
    private String address;//地址
    private BigDecimal useMoney;//可提现余额
    private Integer state;//状态 游客1；充值成功2；已充值3（邀请）
    private Date inTime;//入列时间
    private Date outTime;//出列时间

    private String name;//姓名
    private String phone;//手机号
    private String bankNum;//银行卡号
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
    private Date updateTime;//更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
    private Date createTime;//创建时间
    private String url;//签名的url

    public Member(int id, String memberId, String shareMemberId, String activityId, String code, String openId, String nikeName, Integer sex, String province, String city, String imgUrl, String img, Integer credits, String address, BigDecimal useMoney, Integer state, Date inTime, Date outTime, String name, String phone, String bankNum, Date updateTime, Date createTime, String url) {
        this.id = id;
        this.memberId = memberId;
        this.shareMemberId = shareMemberId;
        this.activityId = activityId;
        this.code = code;
        this.openId = openId;
        this.nikeName = nikeName;
        this.sex = sex;
        this.province = province;
        this.city = city;
        this.imgUrl = imgUrl;
        this.img = img;
        this.credits = credits;
        this.address = address;
        this.useMoney = useMoney;
        this.state = state;
        this.inTime = inTime;
        this.outTime = outTime;
        this.name = name;
        this.phone = phone;
        this.bankNum = bankNum;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getShareMemberId() {
        return shareMemberId;
    }

    public void setShareMemberId(String shareMemberId) {
        this.shareMemberId = shareMemberId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getUseMoney() {
        return useMoney;
    }

    public void setUseMoney(BigDecimal useMoney) {
        this.useMoney = useMoney;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", memberId='" + memberId + '\'' +
                ", shareMemberId='" + shareMemberId + '\'' +
                ", activityId='" + activityId + '\'' +
                ", code='" + code + '\'' +
                ", openId='" + openId + '\'' +
                ", nikeName='" + nikeName + '\'' +
                ", sex=" + sex +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", img='" + img + '\'' +
                ", credits=" + credits +
                ", address='" + address + '\'' +
                ", useMoney=" + useMoney +
                ", state=" + state +
                ", inTime=" + inTime +
                ", outTime=" + outTime +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", bankNum='" + bankNum + '\'' +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", url='" + url + '\'' +
                '}';
    }

}
