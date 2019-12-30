package com.springboot.fun.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
/*
* 管理员登录
* */
public class Admin {
    private String adminId;//主键
    private String adminName;//管理员姓名
    private String adminNum;//管理员账号
    private String password;//用户密码
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT")
    private Date createTime;//用户创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT")
    private Date updateTime;//用户更新时间

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminNum() {
        return adminNum;
    }

    public void setAdminNum(String adminNum) {
        this.adminNum = adminNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Admin() {
    }

    public Admin(String adminId, String adminName, String adminNum, String password, Date createTime, Date updateTime) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminNum = adminNum;
        this.password = password;
        this.createTime = createTime;
        this.updateTime = updateTime;


    }
}
