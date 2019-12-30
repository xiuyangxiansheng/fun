package com.springboot.fun.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
/*
* 校园福利---订单
* */
public class SchoolOrder {

  private String id;//主键
  private String orderSupplierId;//关联商家id
  private String orderSchoolshopId;//关联商品id
  private String orderUserId;//关联用户id
  private String orderSupplierName;//商家名称
    private String orderSupplierUrl;//商家图片
  private String orderSupplierPhone;//联系电话
  private String orderSchoolshopName;//商品名称
  private String orderSchoolshopUrl;//商品图片
  private String orderSchoolshopAddress;//地址
  private String orderSerialNumber;//订单编号
    private String orderCodeNumber;//快捷核销码
  private Integer orderState;//订单状态(待自提1，已完成2）
  private Integer orderNumber;//数量
  private BigDecimal orderCoin;//动力币
  private BigDecimal orderOriginalCost;//原价
  private BigDecimal orderCurrentPrice;//折后价
  private Integer orderPayWay;//支付方式(动力币1)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date orderPayTime;//下单时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date orderSuccessTime;//完成时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date orderUpdateTime;//更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date orderCreateTime;//创建时间

    public SchoolOrder() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderSupplierId() {
        return orderSupplierId;
    }

    public void setOrderSupplierId(String orderSupplierId) {
        this.orderSupplierId = orderSupplierId;
    }

    public String getOrderSchoolshopId() {
        return orderSchoolshopId;
    }

    public void setOrderSchoolshopId(String orderSchoolshopId) {
        this.orderSchoolshopId = orderSchoolshopId;
    }

    public String getOrderUserId() {
        return orderUserId;
    }

    public void setOrderUserId(String orderUserId) {
        this.orderUserId = orderUserId;
    }

    public String getOrderSupplierName() {
        return orderSupplierName;
    }

    public void setOrderSupplierName(String orderSupplierName) {
        this.orderSupplierName = orderSupplierName;
    }

    public String getOrderSupplierUrl() {
        return orderSupplierUrl;
    }

    public void setOrderSupplierUrl(String orderSupplierUrl) {
        this.orderSupplierUrl = orderSupplierUrl;
    }

    public String getOrderSupplierPhone() {
        return orderSupplierPhone;
    }

    public void setOrderSupplierPhone(String orderSupplierPhone) {
        this.orderSupplierPhone = orderSupplierPhone;
    }

    public String getOrderSchoolshopName() {
        return orderSchoolshopName;
    }

    public void setOrderSchoolshopName(String orderSchoolshopName) {
        this.orderSchoolshopName = orderSchoolshopName;
    }

    public String getOrderSchoolshopUrl() {
        return orderSchoolshopUrl;
    }

    public void setOrderSchoolshopUrl(String orderSchoolshopUrl) {
        this.orderSchoolshopUrl = orderSchoolshopUrl;
    }

    public String getOrderSchoolshopAddress() {
        return orderSchoolshopAddress;
    }

    public void setOrderSchoolshopAddress(String orderSchoolshopAddress) {
        this.orderSchoolshopAddress = orderSchoolshopAddress;
    }

    public String getOrderSerialNumber() {
        return orderSerialNumber;
    }

    public void setOrderSerialNumber(String orderSerialNumber) {
        this.orderSerialNumber = orderSerialNumber;
    }

    public String getOrderCodeNumber() {
        return orderCodeNumber;
    }

    public void setOrderCodeNumber(String orderCodeNumber) {
        this.orderCodeNumber = orderCodeNumber;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getOrderCoin() {
        return orderCoin;
    }

    public void setOrderCoin(BigDecimal orderCoin) {
        this.orderCoin = orderCoin;
    }

    public BigDecimal getOrderOriginalCost() {
        return orderOriginalCost;
    }

    public void setOrderOriginalCost(BigDecimal orderOriginalCost) {
        this.orderOriginalCost = orderOriginalCost;
    }

    public BigDecimal getOrderCurrentPrice() {
        return orderCurrentPrice;
    }

    public void setOrderCurrentPrice(BigDecimal orderCurrentPrice) {
        this.orderCurrentPrice = orderCurrentPrice;
    }

    public Integer getOrderPayWay() {
        return orderPayWay;
    }

    public void setOrderPayWay(Integer orderPayWay) {
        this.orderPayWay = orderPayWay;
    }

    public Date getOrderPayTime() {
        return orderPayTime;
    }

    public void setOrderPayTime(Date orderPayTime) {
        this.orderPayTime = orderPayTime;
    }

    public Date getOrderSuccessTime() {
        return orderSuccessTime;
    }

    public void setOrderSuccessTime(Date orderSuccessTime) {
        this.orderSuccessTime = orderSuccessTime;
    }

    public Date getOrderUpdateTime() {
        return orderUpdateTime;
    }

    public void setOrderUpdateTime(Date orderUpdateTime) {
        this.orderUpdateTime = orderUpdateTime;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }
}
