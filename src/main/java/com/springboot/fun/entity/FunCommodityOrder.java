package com.springboot.fun.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
/*
* 商品订单
* */
public class FunCommodityOrder {

  private String id;//主键
  private String commodityId;//关联商品id
  private String supplierId;//关联商家id
  private String userId;//关联用户id
  private Integer orderState;//订单状态(待发货1，待自提2，已发货3，已核销4，已完成5，已过期6）
  private String orderSupplierName;//商家名称
  private String orderSupplierImg;//商家图片
  private String orderSupplierPhone;//商家电话
  private String orderSupplierAddress;//商家地址
  private String orderCommodityName;//商品名称
  private String orderCommodityImg;//商品图片
  private String orderSerialNumber;//订单编号
  private String orderExpressNumber;//物流单号
  private String orderLogistics;//配送方式（快递）
  private BigDecimal orderStorePrice;//门店价
  private BigDecimal orderSubscribePrice;//预约价
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date orderPayTime;//下单时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date orderCodeTime;//核销时间
  private String orderCode;//核销码
  private String orderMemberName;//用户名
  private String orderMemberPhone;//用户电话
  private String orderMemberAddress;//用户地址
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date orderGetTime;//订单领取时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date orderLoseTime;//订单失效时间
  private BigDecimal orderCoin;//预约动力币
  private String orderMessage;//买家留言
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date orderSendTime;//发货时间.
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date orderPutTime;//收货时间
  private Integer orderType;//商品类型（吃1，喝2，嗨3，学4）
  private Integer orderAttribute;//商品属性（线上1；线下2）
  private Integer orderNumber;//数量
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date orderCreateTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date orderUpdateTime;//更新时间

    private WxUser wxUserList;//下单的用户

    private Double longitude;//经度
    private Double latitude;//纬度
    private OrderHexiaoEntity orderHexiaoList;//核销的订单信息
    private String nickName;//下单用户昵称

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }



    public Date getOrderGetTime() {
        return orderGetTime;
    }

    public void setOrderGetTime(Date orderGetTime) {
        this.orderGetTime = orderGetTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public FunCommodityOrder() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }


    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public String getOrderSupplierName() {
        return orderSupplierName;
    }

    public void setOrderSupplierName(String orderSupplierName) {
        this.orderSupplierName = orderSupplierName;
    }

    public String getOrderSupplierImg() {
        return orderSupplierImg;
    }

    public void setOrderSupplierImg(String orderSupplierImg) {
        this.orderSupplierImg = orderSupplierImg;
    }

    public String getOrderSupplierPhone() {
        return orderSupplierPhone;
    }

    public void setOrderSupplierPhone(String orderSupplierPhone) {
        this.orderSupplierPhone = orderSupplierPhone;
    }

    public String getOrderSupplierAddress() {
        return orderSupplierAddress;
    }

    public void setOrderSupplierAddress(String orderSupplierAddress) {
        this.orderSupplierAddress = orderSupplierAddress;
    }

    public String getOrderCommodityName() {
        return orderCommodityName;
    }

    public void setOrderCommodityName(String orderCommodityName) {
        this.orderCommodityName = orderCommodityName;
    }

    public String getOrderCommodityImg() {
        return orderCommodityImg;
    }

    public void setOrderCommodityImg(String orderCommodityImg) {
        this.orderCommodityImg = orderCommodityImg;
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

    public BigDecimal getOrderStorePrice() {
        return orderStorePrice;
    }

    public void setOrderStorePrice(BigDecimal orderStorePrice) {
        this.orderStorePrice = orderStorePrice;
    }

    public BigDecimal getOrderSubscribePrice() {
        return orderSubscribePrice;
    }

    public void setOrderSubscribePrice(BigDecimal orderSubscribePrice) {
        this.orderSubscribePrice = orderSubscribePrice;
    }

    public Date getOrderPayTime() {
        return orderPayTime;
    }

    public void setOrderPayTime(Date orderPayTime) {
        this.orderPayTime = orderPayTime;
    }

    public Date getOrderCodeTime() {
        return orderCodeTime;
    }

    public void setOrderCodeTime(Date orderCodeTime) {
        this.orderCodeTime = orderCodeTime;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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

    public Date getOrderLoseTime() {
        return orderLoseTime;
    }

    public void setOrderLoseTime(Date orderLoseTime) {
        this.orderLoseTime = orderLoseTime;
    }

    public BigDecimal getOrderCoin() {
        return orderCoin;
    }

    public void setOrderCoin(BigDecimal orderCoin) {
        this.orderCoin = orderCoin;
    }

    public String getOrderMessage() {
        return orderMessage;
    }

    public void setOrderMessage(String orderMessage) {
        this.orderMessage = orderMessage;
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

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderAttribute() {
        return orderAttribute;
    }

    public void setOrderAttribute(Integer orderAttribute) {
        this.orderAttribute = orderAttribute;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public Date getOrderUpdateTime() {
        return orderUpdateTime;
    }

    public void setOrderUpdateTime(Date orderUpdateTime) {
        this.orderUpdateTime = orderUpdateTime;
    }

    public WxUser getWxUserList() {
        return wxUserList;
    }

    public void setWxUserList(WxUser wxUserList) {
        this.wxUserList = wxUserList;
    }

    public OrderHexiaoEntity getOrderHexiaoList() {
        return orderHexiaoList;
    }

    public void setOrderHexiaoList(OrderHexiaoEntity orderHexiaoList) {
        this.orderHexiaoList = orderHexiaoList;
    }
}
