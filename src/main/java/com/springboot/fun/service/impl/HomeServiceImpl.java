package com.springboot.fun.service.impl;

import com.springboot.fun.dao.HomeDao;
import com.springboot.fun.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class HomeServiceImpl implements HomeService {


    @Autowired
    private HomeDao homeDao;

    //活动首页
    //累计用户
    @Override
    public Integer userCount() {
        return homeDao.userCount();
    }
    //昨日新增用户
    @Override
    public Integer yesterdayUserCount() {
        return homeDao.yesterdayUserCount();
    }
    //今日总订单
    @Override
    public Integer todayOrder() {
        return homeDao.todayOrder();
    }
    //今日线上订单
    @Override
    public Integer todayUpOrder() {
        return homeDao.todayUpOrder();
    }
    //今日线下订单
    @Override
    public Integer todayDownOrder() {
        return homeDao.todayDownOrder();
    }
    //校园二手动态
    @Override
    public Integer schoolUsed() {
        return homeDao.schoolUsed();
    }
    //总动力币
    @Override
    public BigDecimal coinCount() {
        return homeDao.coinCount();
    }
    //用户
    //今日新增用户
    @Override
    public Integer todayAddUser() {
        return homeDao.todayAddUser();
    }
   //今日授权用户
    @Override
    public Integer todayAccreditUser() {
        return homeDao.todayAccreditUser();
    }
    //今日未授权用户
    @Override
    public Integer todayNoAccreditUser() {
        return homeDao.todayNoAccreditUser();
    }
    //昨日新增用户
    @Override
    public Integer yesterdayAddUser() {
        return homeDao.yesterdayAddUser();
    }
    //昨日授权用户
    @Override
    public Integer yesterdayAccreditUser() {
        return homeDao.yesterdayAccreditUser();
    }
    //昨日未授权用户
    @Override
    public Integer yesterdayNoAccreditUser() {
        return homeDao.yesterdayNoAccreditUser();
    }
    //订单
    //待发货
    @Override
    public Integer waitShipmentsOrderCount() {
        return homeDao.waitShipmentsOrderCount();
    }
    //待自提
    @Override
    public Integer waitTakeOrderCount() {
        return homeDao.waitTakeOrderCount();
    }
    //已发货
    @Override
    public Integer sendOrderCount() {
        return homeDao.sendOrderCount();
    }
    //已核销
    @Override
    public Integer codeSuccessOrderCount() {
        return homeDao.codeSuccessOrderCount();
    }
    //已完成
    @Override
    public Integer accomplishOrderCount() {
        return homeDao.accomplishOrderCount();
    }
    //已过期
    @Override
    public Integer failedOrderCount() {
        return homeDao.failedOrderCount();
    }
    //校园动态
    //今日动态
    @Override
    public Integer todayTrends() {
        return homeDao.todayTrends();
    }
    //昨日发布
    @Override
    public Integer yesterdayTrends() {
        return homeDao.yesterdayTrends();
    }
    //其他
    @Override
    public Integer otherTrends() {
        return homeDao.otherTrends();
    }
    //二手
    @Override
    public Integer usedTrends() {
        return homeDao.usedTrends();
    }
    //求购
    @Override
    public Integer buyTrends() {
        return homeDao.buyTrends();
    }
    //许愿
    @Override
    public Integer wishTrends() {
        return homeDao.wishTrends();
    }
    //昨日许愿
    @Override
    public Integer yesterdayWishTrends() {
        return homeDao.yesterdayWishTrends();
    }
    //动力币
    //总余额
    //总消费
    @Override
    public BigDecimal customerCoinCount() {
        return homeDao.customerCoinCount();
    }
    //今日增加
    @Override
    public BigDecimal todayAddCoinCount() {
        return homeDao.todayAddCoinCount();
    }
    //今日消费
    @Override
    public BigDecimal todayCustomerCoinCount() {
        return homeDao.todayCustomerCoinCount();
    }
    //昨日增加
    @Override
    public BigDecimal yesterdayAddCoinCount() {
        return homeDao.yesterdayAddCoinCount();
    }
    //昨日消费
    @Override
    public BigDecimal yesterdayCustomerCoinCount() {
        return homeDao.yesterdayCustomerCoinCount();
    }
}
