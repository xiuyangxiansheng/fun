package com.springboot.fun.entity;
/*
* 欢迎页
* */
public class YlqWelcomeBanner {

  private String id;
  private long stopTime;
  private String img;
  private String link;
  private long status;
  private long sortRank;
  private String remark;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;
  private String description;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public long getStopTime() {
    return stopTime;
  }

  public void setStopTime(long stopTime) {
    this.stopTime = stopTime;
  }


  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }


  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public long getSortRank() {
    return sortRank;
  }

  public void setSortRank(long sortRank) {
    this.sortRank = sortRank;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public java.sql.Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.sql.Timestamp updateTime) {
    this.updateTime = updateTime;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
