package com.springboot.fun.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

//校园福利
public class ShopSchool {

  private String id;//
  private String schoolSupplierId;//关联商家id
        private String supplierName;//商家名称
        private String supplierAddress;//商家地址
        private String supplierUrl;//商品图片
  private String schoolName;//校园福利名称
  private String schoolLitimg;//缩略图
  private String schoolImg;//校园福利主图
  private Integer schoolStore;//库存
    private Integer schoolSold;//已售出
  private BigDecimal schoolCoin;//需要动力币
  private BigDecimal schoolOriginalCost;//原价
  private BigDecimal schoolCurrentPrice;//折后价
  private Integer schoolNumber;//商品可兑换次数
  private Integer schoolTime;//间隔时间（单位 天）
    private Integer schoolState;//商品状态 上架1，下架2
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
    private Date createTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
    private Date updateTime;//更新时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchoolSupplierId() {
        return schoolSupplierId;
    }

    public void setSchoolSupplierId(String schoolSupplierId) {
        this.schoolSupplierId = schoolSupplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierUrl() {
        return supplierUrl;
    }

    public void setSupplierUrl(String supplierUrl) {
        this.supplierUrl = supplierUrl;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolLitimg() {
        return schoolLitimg;
    }

    public void setSchoolLitimg(String schoolLitimg) {
        this.schoolLitimg = schoolLitimg;
    }

    public String getSchoolImg() {
        return schoolImg;
    }

    public void setSchoolImg(String schoolImg) {
        this.schoolImg = schoolImg;
    }

    public Integer getSchoolStore() {
        return schoolStore;
    }

    public void setSchoolStore(Integer schoolStore) {
        this.schoolStore = schoolStore;
    }

    public Integer getSchoolSold() {
        return schoolSold;
    }

    public void setSchoolSold(Integer schoolSold) {
        this.schoolSold = schoolSold;
    }

    public BigDecimal getSchoolCoin() {
        return schoolCoin;
    }

    public void setSchoolCoin(BigDecimal schoolCoin) {
        this.schoolCoin = schoolCoin;
    }

    public BigDecimal getSchoolOriginalCost() {
        return schoolOriginalCost;
    }

    public void setSchoolOriginalCost(BigDecimal schoolOriginalCost) {
        this.schoolOriginalCost = schoolOriginalCost;
    }

    public BigDecimal getSchoolCurrentPrice() {
        return schoolCurrentPrice;
    }

    public void setSchoolCurrentPrice(BigDecimal schoolCurrentPrice) {
        this.schoolCurrentPrice = schoolCurrentPrice;
    }

    public Integer getSchoolNumber() {
        return schoolNumber;
    }

    public void setSchoolNumber(Integer schoolNumber) {
        this.schoolNumber = schoolNumber;
    }

    public Integer getSchoolTime() {
        return schoolTime;
    }

    public void setSchoolTime(Integer schoolTime) {
        this.schoolTime = schoolTime;
    }

    public Integer getSchoolState() {
        return schoolState;
    }

    public void setSchoolState(Integer schoolState) {
        this.schoolState = schoolState;
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

    public ShopSchool() {
        super();
    }
}
