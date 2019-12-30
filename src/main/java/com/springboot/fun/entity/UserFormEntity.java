package com.springboot.fun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * formid模板消息
 */
@TableName("ylq_user_form")
public class UserFormEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.UUID)//指定自增策略
    private String id;
    /**
     *
     */
    private String userId;
    /**
     * 模板消息form_id
     */
    private String formId;
    /**
     * 模板消息form_id失效时间
     */

    private Long formIdInvalidTime;
    /**
     * 1签到
     */
    private Integer type;
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
    //openid
    private String openid;

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
     * 设置：模板消息form_id
     */
    public void setFormId(String formId) {
        this.formId = formId;
    }

    /**
     * 获取：模板消息form_id
     */
    public String getFormId() {
        return formId;
    }

    /**
     * 设置：模板消息form_id失效时间
     */
    public void setFormIdInvalidTime(Long formIdInvalidTime) {
        this.formIdInvalidTime = formIdInvalidTime;
    }

    /**
     * 获取：模板消息form_id失效时间
     */
    public Long getFormIdInvalidTime() {
        return formIdInvalidTime;
    }

    /**
     * 设置：1签到
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取：1签到
     */
    public Integer getType() {
        return type;
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

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
