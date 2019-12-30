package com.springboot.fun.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class FunSchoolSupplier {

  private String sId;
  private String schoolId;
  private String supplierId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
  private Date updateTime;

    private String schoolName;//学校名称


    public FunSchoolSupplier() {
        super();
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
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

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public FunSchoolSupplier(String id, String schoolId, String supplierId) {

        this.sId = id;
        this.schoolId = schoolId;
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return "FunSchoolSupplier{" +
                "sId='" + sId + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", schoolName='" + schoolName + '\'' +
                '}';
    }
}
