package com.springboot.fun.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UsedLabel {
/*
* 校园二手标签
* */
  private String id;//主键
  private long labelType;//类型：发布类型1；标签2；价格3
  private long labelNum;//标签num
  private String labelName;//标签名
  private String labelUrl;//标签图标
  private Integer labelPriceState;//价格显示状态。不显示0，显示1
   private Integer labelState;//上下架状态
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date labelCreateTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date labelUpdateTime;//更新时间

    public Integer getLabelState() {
        return labelState;
    }

    public void setLabelState(Integer labelState) {
        this.labelState = labelState;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getLabelType() {
        return labelType;
    }

    public void setLabelType(long labelType) {
        this.labelType = labelType;
    }

    public long getLabelNum() {
        return labelNum;
    }

    public void setLabelNum(long labelNum) {
        this.labelNum = labelNum;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getLabelUrl() {
        return labelUrl;
    }

    public void setLabelUrl(String labelUrl) {
        this.labelUrl = labelUrl;
    }

    public Integer getLabelPriceState() {
        return labelPriceState;
    }

    public void setLabelPriceState(Integer labelPriceState) {
        this.labelPriceState = labelPriceState;
    }

    public Date getLabelCreateTime() {
        return labelCreateTime;
    }

    public void setLabelCreateTime(Date labelCreateTime) {
        this.labelCreateTime = labelCreateTime;
    }

    public Date getLabelUpdateTime() {
        return labelUpdateTime;
    }

    public void setLabelUpdateTime(Date labelUpdateTime) {
        this.labelUpdateTime = labelUpdateTime;
    }

    public UsedLabel() {
    }
}
