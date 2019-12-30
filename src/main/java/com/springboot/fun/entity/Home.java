package com.springboot.fun.entity;

import java.math.BigDecimal;

/*
*
* 后台首页数据统计
* */
public class Home {

    //活动首页
    private Integer userCount;//累计用户
    private Integer yesterdayUserCount;//昨日新增用户
    private Integer todayOrder;//今日总订单
    private Integer todayUpOrder;//今日线上订单
    private Integer todayDownOrder;//今日线下订单
    private Integer schoolUsed;//校园二手动态
    private BigDecimal coinCount;//总动力币


    //用户
    private Integer todayAddUser;//今日新增用户
    private Integer todayAccreditUser;//今日授权用户
    private Integer todayNoAccreditUser;//今日未授权用户
    private Integer yesterdayAddUser;//昨日新增用户
    private Integer yesterdayAccreditUser;//昨日授权用户
    private Integer yesterdayNoAccreditUser;//昨日未授权用户

    //订单
    private Integer waitShipmentsOrderCount;//待发货
    private Integer waitTakeOrderCount;//待自提
    private Integer sendOrderCount;//已发货
    private Integer codeSuccessOrderCount;//已核销
    private Integer accomplishOrderCount;//已完成
    private Integer failedOrderCount;//已过期


    //校园动态
    private Integer todayTrends;//今日动态
    private Integer yesterdayTrends;//昨日发布
    private Integer otherTrends;//其他
    private Integer usedTrends;//二手
    private Integer buyTrends;//求购
    private Integer wishTrends;//许愿
    private Integer yesterdayWishTrends;//昨日许愿


    //动力币
                                        //总余额
    private BigDecimal customerCoinCount;//总消费
    private BigDecimal todayAddCoinCount;//今日增加
    private BigDecimal todayCustomerCoinCount;//今日消费
    private BigDecimal yesterdayAddCoinCount;//昨日增加
    private BigDecimal yesterdayCustomerCoinCount;//昨日消费

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Integer getYesterdayUserCount() {
        return yesterdayUserCount;
    }

    public void setYesterdayUserCount(Integer yesterdayUserCount) {
        this.yesterdayUserCount = yesterdayUserCount;
    }

    public Integer getTodayOrder() {
        return todayOrder;
    }

    public void setTodayOrder(Integer todayOrder) {
        this.todayOrder = todayOrder;
    }

    public Integer getTodayUpOrder() {
        return todayUpOrder;
    }

    public void setTodayUpOrder(Integer todayUpOrder) {
        this.todayUpOrder = todayUpOrder;
    }

    public Integer getTodayDownOrder() {
        return todayDownOrder;
    }

    public void setTodayDownOrder(Integer todayDownOrder) {
        this.todayDownOrder = todayDownOrder;
    }

    public Integer getSchoolUsed() {
        return schoolUsed;
    }

    public void setSchoolUsed(Integer schoolUsed) {
        this.schoolUsed = schoolUsed;
    }

    public BigDecimal getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(BigDecimal coinCount) {
        this.coinCount = coinCount;
    }

    public Integer getTodayAddUser() {
        return todayAddUser;
    }

    public void setTodayAddUser(Integer todayAddUser) {
        this.todayAddUser = todayAddUser;
    }

    public Integer getTodayAccreditUser() {
        return todayAccreditUser;
    }

    public void setTodayAccreditUser(Integer todayAccreditUser) {
        this.todayAccreditUser = todayAccreditUser;
    }

    public Integer getTodayNoAccreditUser() {
        return todayNoAccreditUser;
    }

    public void setTodayNoAccreditUser(Integer todayNoAccreditUser) {
        this.todayNoAccreditUser = todayNoAccreditUser;
    }

    public Integer getYesterdayAddUser() {
        return yesterdayAddUser;
    }

    public void setYesterdayAddUser(Integer yesterdayAddUser) {
        this.yesterdayAddUser = yesterdayAddUser;
    }

    public Integer getYesterdayAccreditUser() {
        return yesterdayAccreditUser;
    }

    public void setYesterdayAccreditUser(Integer yesterdayAccreditUser) {
        this.yesterdayAccreditUser = yesterdayAccreditUser;
    }

    public Integer getYesterdayNoAccreditUser() {
        return yesterdayNoAccreditUser;
    }

    public void setYesterdayNoAccreditUser(Integer yesterdayNoAccreditUser) {
        this.yesterdayNoAccreditUser = yesterdayNoAccreditUser;
    }

    public Integer getWaitShipmentsOrderCount() {
        return waitShipmentsOrderCount;
    }

    public void setWaitShipmentsOrderCount(Integer waitShipmentsOrderCount) {
        this.waitShipmentsOrderCount = waitShipmentsOrderCount;
    }

    public Integer getWaitTakeOrderCount() {
        return waitTakeOrderCount;
    }

    public void setWaitTakeOrderCount(Integer waitTakeOrderCount) {
        this.waitTakeOrderCount = waitTakeOrderCount;
    }

    public Integer getSendOrderCount() {
        return sendOrderCount;
    }

    public void setSendOrderCount(Integer sendOrderCount) {
        this.sendOrderCount = sendOrderCount;
    }

    public Integer getCodeSuccessOrderCount() {
        return codeSuccessOrderCount;
    }

    public void setCodeSuccessOrderCount(Integer codeSuccessOrderCount) {
        this.codeSuccessOrderCount = codeSuccessOrderCount;
    }

    public Integer getAccomplishOrderCount() {
        return accomplishOrderCount;
    }

    public void setAccomplishOrderCount(Integer accomplishOrderCount) {
        this.accomplishOrderCount = accomplishOrderCount;
    }

    public Integer getFailedOrderCount() {
        return failedOrderCount;
    }

    public void setFailedOrderCount(Integer failedOrderCount) {
        this.failedOrderCount = failedOrderCount;
    }

    public Integer getTodayTrends() {
        return todayTrends;
    }

    public void setTodayTrends(Integer todayTrends) {
        this.todayTrends = todayTrends;
    }

    public Integer getYesterdayTrends() {
        return yesterdayTrends;
    }

    public void setYesterdayTrends(Integer yesterdayTrends) {
        this.yesterdayTrends = yesterdayTrends;
    }

    public Integer getOtherTrends() {
        return otherTrends;
    }

    public void setOtherTrends(Integer otherTrends) {
        this.otherTrends = otherTrends;
    }

    public Integer getUsedTrends() {
        return usedTrends;
    }

    public void setUsedTrends(Integer usedTrends) {
        this.usedTrends = usedTrends;
    }

    public Integer getBuyTrends() {
        return buyTrends;
    }

    public void setBuyTrends(Integer buyTrends) {
        this.buyTrends = buyTrends;
    }

    public Integer getWishTrends() {
        return wishTrends;
    }

    public void setWishTrends(Integer wishTrends) {
        this.wishTrends = wishTrends;
    }

    public Integer getYesterdayWishTrends() {
        return yesterdayWishTrends;
    }

    public void setYesterdayWishTrends(Integer yesterdayWishTrends) {
        this.yesterdayWishTrends = yesterdayWishTrends;
    }

    public BigDecimal getCustomerCoinCount() {
        return customerCoinCount;
    }

    public void setCustomerCoinCount(BigDecimal customerCoinCount) {
        this.customerCoinCount = customerCoinCount;
    }

    public BigDecimal getTodayAddCoinCount() {
        return todayAddCoinCount;
    }

    public void setTodayAddCoinCount(BigDecimal todayAddCoinCount) {
        this.todayAddCoinCount = todayAddCoinCount;
    }

    public BigDecimal getTodayCustomerCoinCount() {
        return todayCustomerCoinCount;
    }

    public void setTodayCustomerCoinCount(BigDecimal todayCustomerCoinCount) {
        this.todayCustomerCoinCount = todayCustomerCoinCount;
    }

    public BigDecimal getYesterdayAddCoinCount() {
        return yesterdayAddCoinCount;
    }

    public void setYesterdayAddCoinCount(BigDecimal yesterdayAddCoinCount) {
        this.yesterdayAddCoinCount = yesterdayAddCoinCount;
    }

    public BigDecimal getYesterdayCustomerCoinCount() {
        return yesterdayCustomerCoinCount;
    }

    public void setYesterdayCustomerCoinCount(BigDecimal yesterdayCustomerCoinCount) {
        this.yesterdayCustomerCoinCount = yesterdayCustomerCoinCount;
    }
}
