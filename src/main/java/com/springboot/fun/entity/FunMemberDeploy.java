package com.springboot.fun.entity;

public class FunMemberDeploy {

  private String id;
  private Integer memberNumber;//会员人数
  private Integer memberDay;//会员天数

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(Integer memberNumber) {
        this.memberNumber = memberNumber;
    }

    public Integer getMemberDay() {
        return memberDay;
    }

    public void setMemberDay(Integer memberDay) {
        this.memberDay = memberDay;
    }
}
