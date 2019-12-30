package com.springboot.fun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * 学校列表
 */
@TableName("ylq_school")
public class SchoolEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.UUID)//指定自增策略
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 编号
     */
    private String number;
    /**
     * 首字母
     */
    private String firstLetter;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    private Integer isDefault;

    /**
     * 0关闭1开启
     */
    private Integer status;
    /**
     * 排序
     */
    private Integer sortRank;
    /**
     * 备注
     */
    private String remark;
    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
    private Date createTime;
    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
    private Date updateTime;
    /**
     *
     */
    private String description;

    /**
     * 设置：
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public String getId() {
        return id;
    }

    /**
     * 设置：名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：编号
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * 获取：编号
     */
    public String getNumber() {
        return number;
    }

    /**
     * 设置：首字母
     */
    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    /**
     * 获取：首字母
     */
    public String getFirstLetter() {
        return firstLetter;
    }

    /**
     * 设置：经度
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取：经度
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 设置：纬度
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * 获取：纬度
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 设置：0关闭1开启
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：0关闭1开启
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置：排序
     */
    public void setSortRank(Integer sortRank) {
        this.sortRank = sortRank;
    }

    /**
     * 获取：排序
     */
    public Integer getSortRank() {
        return sortRank;
    }

    /**
     * 设置：备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置：
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime == null ? null : (Date) createTime.clone();
    }

    /**
     * 获取：
     */
    public Date getCreateTime() {
        return createTime == null ? null : (Date) createTime.clone();
    }

    /**
     * 设置：
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime == null ? null : (Date) updateTime.clone();
    }

    /**
     * 获取：
     */
    public Date getUpdateTime() {
        return updateTime == null ? null : (Date) updateTime.clone();
    }

    /**
     * 设置：
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取：
     */
    public String getDescription() {
        return description;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public SchoolEntity() {
        super();
    }
}
