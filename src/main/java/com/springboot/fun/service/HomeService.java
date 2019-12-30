package com.springboot.fun.service;


import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public interface HomeService {
    //活动首页
    //累计用户
    Integer userCount();
    //昨日新增用户
    Integer yesterdayUserCount();
    //今日总订单
    Integer todayOrder();
    //今日线上订单
    Integer todayUpOrder();
    //今日线下订单
    Integer todayDownOrder();
    //校园二手动态
    Integer schoolUsed();
    //总动力币
    BigDecimal coinCount();


    //用户
    //今日新增用户
    Integer todayAddUser();
   //今日授权用户
    Integer todayAccreditUser();
    //今日未授权用户
    Integer todayNoAccreditUser();
    //昨日新增用户
    Integer yesterdayAddUser();
    //昨日授权用户
    Integer yesterdayAccreditUser();
    //昨日未授权用户
    Integer yesterdayNoAccreditUser();


    //订单
    //待发货
    Integer waitShipmentsOrderCount();
    //待自提
    Integer waitTakeOrderCount();
    //已发货
    Integer sendOrderCount();
    //已核销
    Integer codeSuccessOrderCount();
    //已完成
    Integer accomplishOrderCount();
    //已过期
    Integer failedOrderCount();

    //校园动态
    //今日动态
    Integer todayTrends();
    //昨日发布
    Integer yesterdayTrends();
    //其他
    Integer otherTrends();
    //二手
    Integer usedTrends();
    //求购
    Integer buyTrends();
    //许愿
    Integer wishTrends();
    //昨日许愿
    Integer yesterdayWishTrends();


    //动力币
    //总余额
    //总消费
    BigDecimal customerCoinCount();
    //今日增加
    BigDecimal todayAddCoinCount();
    //今日消费
    BigDecimal todayCustomerCoinCount();
    //昨日增加
    BigDecimal yesterdayAddCoinCount();
    //昨日消费
    BigDecimal yesterdayCustomerCoinCount();
}
