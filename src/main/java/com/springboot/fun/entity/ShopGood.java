package com.springboot.fun.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

//好物兑换
public class ShopGood {

  private String id;//
  private String goodName;//商品名称
  private String goodLitimg;//缩略图
  private String goodImg;//主图
  private Integer goodTypeNum;//商品类型
    private String goodTypeName;//商品类型名称
  private Integer goodStore;//库存
  private Integer goodCashTrue;//已兑换真实
  private Integer goodCashVirtual;//已兑换虚拟
  private BigDecimal goodCoin;//需要的动力币
  private String goodText;//商品详情
  private String goodPlatform;//商品的平台
  private String goodUrl;//平台商品的链接
  private String goodSpec;//商品规格
  private BigDecimal goodMoney;//商品价格
  private Integer goodState;//商品状态：上架1，下架2
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date goodCreateTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date goodUpdateTime;//更新时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodLitimg() {
        return goodLitimg;
    }

    public void setGoodLitimg(String goodLitimg) {
        this.goodLitimg = goodLitimg;
    }

    public String getGoodImg() {
        return goodImg;
    }

    public void setGoodImg(String goodImg) {
        this.goodImg = goodImg;
    }

    public Integer getGoodTypeNum() {
        return goodTypeNum;
    }

    public void setGoodTypeNum(Integer goodTypeNum) {
        this.goodTypeNum = goodTypeNum;
    }

    public String getGoodTypeName() {
        return goodTypeName;
    }

    public void setGoodTypeName(String goodTypeName) {
        this.goodTypeName = goodTypeName;
    }

    public Integer getGoodStore() {
        return goodStore;
    }

    public void setGoodStore(Integer goodStore) {
        this.goodStore = goodStore;
    }

    public Integer getGoodCashTrue() {
        return goodCashTrue;
    }

    public void setGoodCashTrue(Integer goodCashTrue) {
        this.goodCashTrue = goodCashTrue;
    }

    public Integer getGoodCashVirtual() {
        return goodCashVirtual;
    }

    public void setGoodCashVirtual(Integer goodCashVirtual) {
        this.goodCashVirtual = goodCashVirtual;
    }

    public BigDecimal getGoodCoin() {
        return goodCoin;
    }

    public void setGoodCoin(BigDecimal goodCoin) {
        this.goodCoin = goodCoin;
    }

    public String getGoodText() {
        return goodText;
    }

    public void setGoodText(String goodText) {
        this.goodText = goodText;
    }

    public String getGoodPlatform() {
        return goodPlatform;
    }

    public void setGoodPlatform(String goodPlatform) {
        this.goodPlatform = goodPlatform;
    }

    public String getGoodUrl() {
        return goodUrl;
    }

    public void setGoodUrl(String goodUrl) {
        this.goodUrl = goodUrl;
    }

    public String getGoodSpec() {
        return goodSpec;
    }

    public void setGoodSpec(String goodSpec) {
        this.goodSpec = goodSpec;
    }

    public BigDecimal getGoodMoney() {
        return goodMoney;
    }

    public void setGoodMoney(BigDecimal goodMoney) {
        this.goodMoney = goodMoney;
    }

    public Integer getGoodState() {
        return goodState;
    }

    public void setGoodState(Integer goodState) {
        this.goodState = goodState;
    }

    public Date getGoodCreateTime() {
        return goodCreateTime;
    }

    public void setGoodCreateTime(Date goodCreateTime) {
        this.goodCreateTime = goodCreateTime;
    }

    public Date getGoodUpdateTime() {
        return goodUpdateTime;
    }

    public void setGoodUpdateTime(Date goodUpdateTime) {
        this.goodUpdateTime = goodUpdateTime;
    }

    public ShopGood() {
        super();
    }
}
