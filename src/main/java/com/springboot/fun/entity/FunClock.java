package com.springboot.fun.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class FunClock {

  private String id;
  private String clockUrl;//打卡图片
  private String clockWord;//打卡内容
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT")
  private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT")
  private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClockUrl() {
        return clockUrl;
    }

    public void setClockUrl(String clockUrl) {
        this.clockUrl = clockUrl;
    }

    public String getClockWord() {
        return clockWord;
    }

    public void setClockWord(String clockWord) {
        this.clockWord = clockWord;
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

    public FunClock() {
        super();
    }
}
