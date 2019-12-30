package com.springboot.fun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 签到配置实体
 */
@TableName("ylq_sign_setting")
public class SignSettingEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.UUID)//指定自增策略
    private String id;
                    /**
     * 连续签到天数
     */
    private Integer day;
                    /**
     * 连续签到获得动力币
     */
    private BigDecimal money;
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
     * 设置：连续签到天数
     */
    public void setDay(Integer day) {
        this.day = day;
    }
    /**
     * 获取：连续签到天数
     */
    public Integer getDay() {
        return day;
    }
                /**
     * 设置：连续签到获得动力币
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    /**
     * 获取：连续签到获得动力币
     */
    public BigDecimal getMoney() {
        return money;
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
        this.createTime = createTime==null?null:(Date) createTime .clone();
    }
    /**
     * 获取：
     */
    public Date getCreateTime() {
        return createTime==null?null:(Date) createTime .clone();
    }
                /**
     * 设置：
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime==null?null:(Date) updateTime .clone();
    }
    /**
     * 获取：
     */
    public Date getUpdateTime() {
        return updateTime==null?null:(Date) updateTime .clone();
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
    }
