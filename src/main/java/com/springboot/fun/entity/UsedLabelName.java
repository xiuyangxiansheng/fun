package com.springboot.fun.entity;

public class UsedLabelName {
/*
 * 校园二手标签类型
 * */
  private String labelName;//标签名
  private String labelUrl;//标签图标

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

    public UsedLabelName() {
    }
}
