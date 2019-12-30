package com.springboot.fun.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class FunHomeUrl {

  private String id;
  private String homeUrl;//首页轮播图
  private Integer homeState;//用户状态 单双校区1，全校区3
  private String homeSchoolId;//学校id
  private String homePath;//跳转路径/pgaes/index/index", //跳转路径
  private Integer homePathType;//0为非tabbar界面 1为tabbar界面
  private String homeResult;// "id=0&name=123", //携带参数
  private Integer homeDeleteState;//删除状态，正常1，删除2
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHomeUrl() {
        return homeUrl;
    }

    public void setHomeUrl(String homeUrl) {
        this.homeUrl = homeUrl;
    }

    public Integer getHomeState() {
        return homeState;
    }

    public void setHomeState(Integer homeState) {
        this.homeState = homeState;
    }

    public String getHomeSchoolId() {
        return homeSchoolId;
    }

    public void setHomeSchoolId(String homeSchoolId) {
        this.homeSchoolId = homeSchoolId;
    }

    public String getHomePath() {
        return homePath;
    }

    public void setHomePath(String homePath) {
        this.homePath = homePath;
    }

    public Integer getHomePathType() {
        return homePathType;
    }

    public void setHomePathType(Integer homePathType) {
        this.homePathType = homePathType;
    }

    public String getHomeResult() {
        return homeResult;
    }

    public void setHomeResult(String homeResult) {
        this.homeResult = homeResult;
    }

    public Integer getHomeDeleteState() {
        return homeDeleteState;
    }

    public void setHomeDeleteState(Integer homeDeleteState) {
        this.homeDeleteState = homeDeleteState;
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

    public FunHomeUrl() {
        super();
    }
}
