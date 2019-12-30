package com.springboot.fun.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*
* 校园福利--商家
* */
@TableName("nideshop_supplier")
public class NideShopSupplier implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.UUID)//指定自增策略
    private String id;//主键
    private String name;//名称
    private String address;//地址
    private Double longitude;//经度
    private Double latitude;//纬度
    private String contactMobie;//联系电话
    private String primaryPicUrl;//主图
    private String thumbnailUrl;//缩略图
    private String introduction;//简介
    private String openTime;//营业时间
    private String schoolId;//学校id
    private String schoolName;//学校名称
    @TableLogic
    private Integer status;//0关闭1开启br
    private Integer sortRank;//排序
    private String remark;//备注
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
    private Date createTime;//
    @TableField(fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
    private Date updateTime;//
    private String description;//
    private Integer browseNum;//浏览数量
    private  Integer browseTrueNum;//浏览 真
    private Integer shareNum;//分享数量
    private Integer shareTrueNum;//分享 真
    private String label;//商家标签，中间有“，”隔开
    private Integer schoolState;//商家校区状态

    private List<FunSchoolSupplier> funSchoolSupplierList;//校区
    private String supplierEmployee;//商家的收银员，用“，”隔开
    private List<SupplierEmployeeEntity> supplierEmployeeEntityList;//收银员列表

    public Integer getSchoolState() {
        return schoolState;
    }

    public List<SupplierEmployeeEntity> getSupplierEmployeeEntityList() {
        return supplierEmployeeEntityList;
    }

    public void setSupplierEmployeeEntityList(List<SupplierEmployeeEntity> supplierEmployeeEntityList) {
        this.supplierEmployeeEntityList = supplierEmployeeEntityList;
    }

    public String getSupplierEmployee() {
        return supplierEmployee;
    }

    public void setSupplierEmployee(String supplierEmployee) {
        this.supplierEmployee = supplierEmployee;
    }

    public List<FunSchoolSupplier> getFunSchoolSupplierList() {
        return funSchoolSupplierList;
    }

    public void setFunSchoolSupplierList(List<FunSchoolSupplier> funSchoolSupplierList) {
        this.funSchoolSupplierList = funSchoolSupplierList;
    }

    public void setSchoolState(Integer schoolState) {
        this.schoolState = schoolState;
    }

    public NideShopSupplier() {
        super();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getContactMobie() {
        return contactMobie;
    }

    public void setContactMobie(String contactMobie) {
        this.contactMobie = contactMobie;
    }

    public String getPrimaryPicUrl() {
        return primaryPicUrl;
    }

    public void setPrimaryPicUrl(String primaryPicUrl) {
        this.primaryPicUrl = primaryPicUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSortRank() {
        return sortRank;
    }

    public void setSortRank(Integer sortRank) {
        this.sortRank = sortRank;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
    }

    public Integer getBrowseTrueNum() {
        return browseTrueNum;
    }

    public void setBrowseTrueNum(Integer browseTrueNum) {
        this.browseTrueNum = browseTrueNum;
    }

    public Integer getShareNum() {
        return shareNum;
    }

    public void setShareNum(Integer shareNum) {
        this.shareNum = shareNum;
    }

    public Integer getShareTrueNum() {
        return shareTrueNum;
    }

    public void setShareTrueNum(Integer shareTrueNum) {
        this.shareTrueNum = shareTrueNum;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }



}
