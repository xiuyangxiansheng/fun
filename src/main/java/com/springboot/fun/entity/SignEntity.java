package com.springboot.fun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


/*
* 用户签到表
* */
@TableName("ylq_sign")
public class SignEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.UUID)//指定自增策略
    private String id;
                    /**
     * 
     */
    private String userId;
                    /**
     * 累计签到
     */
    private Integer signCount;
                    /**
     * 签到历史
     */
    private Long signHistory;
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
     * 设置：
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * 获取：
     */
    public String getUserId() {
        return userId;
    }
                /**
     * 设置：累计签到
     */
    public void setSignCount(Integer signCount) {
        this.signCount = signCount;
    }
    /**
     * 获取：累计签到
     */
    public Integer getSignCount() {
        return signCount;
    }
                /**
     * 设置：签到历史
     */
    public void setSignHistory(Long signHistory) {
        this.signHistory = signHistory;
    }
    /**
     * 获取：签到历史
     */
    public Long getSignHistory() {
        return signHistory;
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
    //public void setCreateTime(Date createTime) {
    //    this.createTime = createTime==null?null:(Date) createTime .clone();
    //}
    ///**
    // * 获取：
    // */
    //public Date getCreateTime() {
    //    return createTime==null?null:(Date) createTime .clone();
    //}
    //            /**
    // * 设置：
    // */
    //public void setUpdateTime(Date updateTime) {
    //    this.updateTime = updateTime==null?null:(Date) updateTime .clone();
    //}
    ///**
    // * 获取：
    // */
    //public Date getUpdateTime() {
    //    return updateTime==null?null:(Date) updateTime .clone();
    //}

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

    @Override
    public String toString() {
        return "SignEntity{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", signCount=" + signCount +
                ", signHistory=" + signHistory +
                ", status=" + status +
                ", sortRank=" + sortRank +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", description='" + description + '\'' +
                '}';
    }
}
