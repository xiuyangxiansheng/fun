package com.springboot.fun.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/*
* 商品
* */
public class FunCommodity {

  private String id;//主键
  private String supplierId;//关联商家id
  private String commodityName;//商品名称
  private String commodityLitimg;//商品缩略图
  private String commodityImg;//商品主图
  private Integer commodityAttributeState;//商品属性（线上1；线下2）
  private Integer commodityType;//商品分类（吃1，喝2，嗨3，学4）
  private BigDecimal commodityStorePrice;//门店价
  private BigDecimal commoditySubscribePrice;//预约价
      private Integer commodityStore;//库存
  private Integer commoditySoldTrue;//销量真
  private Integer commoditySoldVirtual;//销量虚拟
  private Integer commodityState;//商品状态(上架1，下架2，售罄3)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT")
  private Date commodityDeadline;//预约截止时间
  private Long commodityIndate;//有效期
  private BigDecimal commodityCoin;//预约动力币的币值
  private Integer commodityNumber;//用户兑换次数上线
  private String commodityRole;//使用规则
  private String commodityText;//商品详情
  private Integer commoditySort;//商品排序
  private String commodityMemberList;//用户预约头像集合
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
    private Date commodityGetTime;//取货时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
    private Date commodityGetDeadline;//取货截止时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date commodityCreateTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date commodityUpdateTime;//更新时间
    private String supplierName;//商家名称
    private  Integer commodityMemberState;//是否是会员商品。0不是；1是

    public Integer getCommodityMemberState() {
        return commodityMemberState;
    }

    public void setCommodityMemberState(Integer commodityMemberState) {
        this.commodityMemberState = commodityMemberState;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public FunCommodity() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityLitimg() {
        return commodityLitimg;
    }

    public void setCommodityLitimg(String commodityLitimg) {
        this.commodityLitimg = commodityLitimg;
    }

    public String getCommodityImg() {
        return commodityImg;
    }

    public void setCommodityImg(String commodityImg) {
        this.commodityImg = commodityImg;
    }

    public Integer getCommodityAttributeState() {
        return commodityAttributeState;
    }

    public void setCommodityAttributeState(Integer commodityAttributeState) {
        this.commodityAttributeState = commodityAttributeState;
    }

    public Integer getCommodityType() {
        return commodityType;
    }

    public void setCommodityType(Integer commodityType) {
        this.commodityType = commodityType;
    }

    public BigDecimal getCommodityStorePrice() {
        return commodityStorePrice;
    }

    public void setCommodityStorePrice(BigDecimal commodityStorePrice) {
        this.commodityStorePrice = commodityStorePrice;
    }

    public BigDecimal getCommoditySubscribePrice() {
        return commoditySubscribePrice;
    }

    public void setCommoditySubscribePrice(BigDecimal commoditySubscribePrice) {
        this.commoditySubscribePrice = commoditySubscribePrice;
    }

    public Integer getCommodityStore() {
        return commodityStore;
    }

    public void setCommodityStore(Integer commodityStore) {
        this.commodityStore = commodityStore;
    }

    public Integer getCommoditySoldTrue() {
        return commoditySoldTrue;
    }

    public void setCommoditySoldTrue(Integer commoditySoldTrue) {
        this.commoditySoldTrue = commoditySoldTrue;
    }

    public Integer getCommoditySoldVirtual() {
        return commoditySoldVirtual;
    }

    public void setCommoditySoldVirtual(Integer commoditySoldVirtual) {
        this.commoditySoldVirtual = commoditySoldVirtual;
    }

    public Integer getCommodityState() {
        return commodityState;
    }

    public void setCommodityState(Integer commodityState) {
        this.commodityState = commodityState;
    }

    public Date getCommodityDeadline() {
        return commodityDeadline;
    }

    public void setCommodityDeadline(Date commodityDeadline) {
        this.commodityDeadline = commodityDeadline;
    }

    public Long getCommodityIndate() {
        return commodityIndate;
    }

    public void setCommodityIndate(Long commodityIndate) {
        this.commodityIndate = commodityIndate;
    }

    public BigDecimal getCommodityCoin() {
        return commodityCoin;
    }

    public void setCommodityCoin(BigDecimal commodityCoin) {
        this.commodityCoin = commodityCoin;
    }

    public Integer getCommodityNumber() {
        return commodityNumber;
    }

    public void setCommodityNumber(Integer commodityNumber) {
        this.commodityNumber = commodityNumber;
    }

    public String getCommodityRole() {
        return commodityRole;
    }

    public void setCommodityRole(String commodityRole) {
        this.commodityRole = commodityRole;
    }

    public String getCommodityText() {
        return commodityText;
    }

    public void setCommodityText(String commodityText) {
        this.commodityText = commodityText;
    }

    public Integer getCommoditySort() {
        return commoditySort;
    }

    public void setCommoditySort(Integer commoditySort) {
        this.commoditySort = commoditySort;
    }

    public String getCommodityMemberList() {
        return commodityMemberList;
    }

    public void setCommodityMemberList(String commodityMemberList) {
        this.commodityMemberList = commodityMemberList;
    }

    public Date getCommodityGetTime() {
        return commodityGetTime;
    }

    public void setCommodityGetTime(Date commodityGetTime) {
        this.commodityGetTime = commodityGetTime;
    }

    public Date getCommodityGetDeadline() {
        return commodityGetDeadline;
    }

    public void setCommodityGetDeadline(Date commodityGetDeadline) {
        this.commodityGetDeadline = commodityGetDeadline;
    }

    public Date getCommodityCreateTime() {
        return commodityCreateTime;
    }

    public void setCommodityCreateTime(Date commodityCreateTime) {
        this.commodityCreateTime = commodityCreateTime;
    }

    public Date getCommodityUpdateTime() {
        return commodityUpdateTime;
    }

    public void setCommodityUpdateTime(Date commodityUpdateTime) {
        this.commodityUpdateTime = commodityUpdateTime;
    }
}
