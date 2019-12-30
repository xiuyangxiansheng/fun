package com.springboot.fun.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ShopGoodType {
//好物兑换--商品类型

  private String id;
  private Integer typeNum;//商品编号
  private String typeName;//编号名称
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date typeUpdateTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date typeCreateTime;//更新时间

    public ShopGoodType() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTypeNum() {
        return typeNum;
    }

    public void setTypeNum(Integer typeNum) {
        this.typeNum = typeNum;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Date getTypeUpdateTime() {
        return typeUpdateTime;
    }

    public void setTypeUpdateTime(Date typeUpdateTime) {
        this.typeUpdateTime = typeUpdateTime;
    }

    public Date getTypeCreateTime() {
        return typeCreateTime;
    }

    public void setTypeCreateTime(Date typeCreateTime) {
        this.typeCreateTime = typeCreateTime;
    }
}
