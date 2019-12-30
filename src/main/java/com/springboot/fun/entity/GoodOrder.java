package com.springboot.fun.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
/*
*
* 好物兑换订单
* */
public class GoodOrder {

  private String id;//主键
  private String orderGoodId;//关联商品id
  private String orderUserId;//关联用户id
  private String orderName;//商品名称
  private String orderLitimg;//商品缩略图
  private String orderSerialNumber;//订单编号
  private String orderExpressNumber;//物流单号
  private String orderLogistics;//配送方式(快递公司)
  private Integer orderState;//物流状态(待发货1；待收货2；已完成3
  private Integer orderNumber;//数量
  private String orderMessage;//买家留言100字
  private BigDecimal orderCoin;//动力币
  private Integer orderTypeNum;//类型编号
  private String orderType;//类型
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date orderPayTime;//下单时间
  private String orderMemberName;//用户姓名
  private String orderMemberPhone;//用户电话
  private String orderMemberAddress;//用户地址
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date orderSendTime;//发货时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date orderPutTime;//收货时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date orderUpdateTime;//更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date orderCreateTime;//创建时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderGoodId() {
        return orderGoodId;
    }

    public void setOrderGoodId(String orderGoodId) {
        this.orderGoodId = orderGoodId;
    }

    public String getOrderUserId() {
        return orderUserId;
    }

    public void setOrderUserId(String orderUserId) {
        this.orderUserId = orderUserId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderLitimg() {
        return orderLitimg;
    }

    public void setOrderLitimg(String orderLitimg) {
        this.orderLitimg = orderLitimg;
    }

    public String getOrderSerialNumber() {
        return orderSerialNumber;
    }

    public void setOrderSerialNumber(String orderSerialNumber) {
        this.orderSerialNumber = orderSerialNumber;
    }

    public String getOrderExpressNumber() {
        return orderExpressNumber;
    }

    public void setOrderExpressNumber(String orderExpressNumber) {
        this.orderExpressNumber = orderExpressNumber;
    }

    public String getOrderLogistics() {
        return orderLogistics;
    }

    public void setOrderLogistics(String orderLogistics) {
        this.orderLogistics = orderLogistics;
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

    public String getOrderMessage() {
        return orderMessage;
    }

    public void setOrderMessage(String orderMessage) {
        this.orderMessage = orderMessage;
    }

    public BigDecimal getOrderCoin() {
        return orderCoin;
    }

    public void setOrderCoin(BigDecimal orderCoin) {
        this.orderCoin = orderCoin;
    }

    public Integer getOrderTypeNum() {
        return orderTypeNum;
    }

    public void setOrderTypeNum(Integer orderTypeNum) {
        this.orderTypeNum = orderTypeNum;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Date getOrderPayTime() {
        return orderPayTime;
    }

    public void setOrderPayTime(Date orderPayTime) {
        this.orderPayTime = orderPayTime;
    }

    public String getOrderMemberName() {
        return orderMemberName;
    }

    public void setOrderMemberName(String orderMemberName) {
        this.orderMemberName = orderMemberName;
    }

    public String getOrderMemberPhone() {
        return orderMemberPhone;
    }

    public void setOrderMemberPhone(String orderMemberPhone) {
        this.orderMemberPhone = orderMemberPhone;
    }

    public String getOrderMemberAddress() {
        return orderMemberAddress;
    }

    public void setOrderMemberAddress(String orderMemberAddress) {
        this.orderMemberAddress = orderMemberAddress;
    }

    public Date getOrderSendTime() {
        return orderSendTime;
    }

    public void setOrderSendTime(Date orderSendTime) {
        this.orderSendTime = orderSendTime;
    }

    public Date getOrderPutTime() {
        return orderPutTime;
    }

    public void setOrderPutTime(Date orderPutTime) {
        this.orderPutTime = orderPutTime;
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

    public GoodOrder() {
    }
}
