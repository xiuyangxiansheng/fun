package com.springboot.fun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * 商家收银员(app创建的员工)实体
 */
@TableName("nideshop_supplier_employee")
public class SupplierEmployeeEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.UUID)//指定自增策略
    private String id;
    private String name;//员工编号
    private String loginName;//
    private String password;//
    private String supplierId;//商家id
    private String customerId;//用户id
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
    private Date createTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
    private Date updateTiem;//更新时间
    private String description;//
    private String wxrealname;//微信真实姓名
    private String unionid;//
    private String wximgurl;//微信头像
    private String nickname;//微信昵称
    private String openId;//
    private Integer authorization;//

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTiem() {
        return updateTiem;
    }

    public void setUpdateTiem(Date updateTiem) {
        this.updateTiem = updateTiem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWxrealname() {
        return wxrealname;
    }

    public void setWxrealname(String wxrealname) {
        this.wxrealname = wxrealname;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getWximgurl() {
        return wximgurl;
    }

    public void setWximgurl(String wximgurl) {
        this.wximgurl = wximgurl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getAuthorization() {
        return authorization;
    }

    public void setAuthorization(Integer authorization) {
        this.authorization = authorization;
    }

    public SupplierEmployeeEntity(String id, String supplierId, String customerId, String userName, String headimgurl,
                                  String nickname, String openid) {
        this.id = id;
        this.supplierId = supplierId;
        this.customerId = customerId;
        this.wxrealname = userName;
        this.wximgurl = headimgurl;
        this.nickname = nickname;
        this.openId = openid;
    }

    public SupplierEmployeeEntity() {
    }
}