package com.springboot.fun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 核销记录表
 */
@TableName("nideshop_order_hexiao")
public class OrderHexiaoEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.UUID)//指定自增策略
    private String id;
    private String orderNo;//订单编号
    private String orderCode;//核销码
    private String orderId;//订单主键
    private String customerName;//核销人名称
    private String customerId;//核销人id
    private String customerUrl;//核销人头像
    private String supplierName;//商家名称
    private String supplierId;//商家id
    private String pnameId;//商品id（主键）
    private String pname;//商品名称
    private BigDecimal price;//商品价格
    private Integer pnum;//商品
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
    private Date createTime;//创建时间
    private String img;//商品图片
    private Date updateTime;//更新时间

        private int soldTrue;//真实销量
        private int pnameStore;//库存
        private int orderSuccessNum;//核销份数
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
        private Date pnameTime;//上架时间

    private String userNickName;//用户昵称
    private String userUrl;//用户头像

    public int getSoldTrue() {
        return soldTrue;
    }

    public void setSoldTrue(int soldTrue) {
        this.soldTrue = soldTrue;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerUrl() {
        return customerUrl;
    }

    public void setCustomerUrl(String customerUrl) {
        this.customerUrl = customerUrl;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getPnameId() {
        return pnameId;
    }

    public void setPnameId(String pnameId) {
        this.pnameId = pnameId;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getPnum() {
        return pnum;
    }

    public void setPnum(Integer pnum) {
        this.pnum = pnum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getPnameStore() {
        return pnameStore;
    }

    public void setPnameStore(int pnameStore) {
        this.pnameStore = pnameStore;
    }

    public int getOrderSuccessNum() {
        return orderSuccessNum;
    }

    public void setOrderSuccessNum(int orderSuccessNum) {
        this.orderSuccessNum = orderSuccessNum;
    }

    public Date getPnameTime() {
        return pnameTime;
    }

    public void setPnameTime(Date pnameTime) {
        this.pnameTime = pnameTime;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }
}
