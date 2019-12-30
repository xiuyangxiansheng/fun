package com.springboot.fun.controller;

import com.springboot.fun.entity.Home;
import com.springboot.fun.service.HomeService;
import com.springboot.fun.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*",maxAge = 3600)
@Controller
@RequestMapping(value = "/home")
public class HomeController {

    @Autowired
    private  HomeService homeService;
    @RequestMapping("/allHome.fun")
    @ResponseBody
    public JsonResult allHome() throws IOException {
        List<Home> list = new ArrayList<>();

        //活动首页
        Integer userCount = homeService.userCount();//累计用户
        Integer yesterdayUserCount = homeService.yesterdayUserCount();//昨日新增用户
        Integer todayOrder = homeService.todayOrder();//今日总订单
        Integer todayUpOrder = homeService.todayUpOrder();//今日线上订单
        Integer todayDownOrder = homeService.todayDownOrder();//今日线下订单
        Integer schoolUsed = homeService.schoolUsed();//校园二手动态
        BigDecimal coinCount = homeService.coinCount();//总动力币


        //用户
        Integer todayAddUser = homeService.todayAddUser();//今日新增用户
      Integer todayAccreditUser =homeService.todayAccreditUser();//今日授权用户
      Integer todayNoAccreditUser =homeService.todayNoAccreditUser();//今日未授权用户
      Integer yesterdayAddUser =homeService.yesterdayAddUser();//昨日新增用户
      Integer yesterdayAccreditUser =homeService.yesterdayAccreditUser();//昨日授权用户
      Integer yesterdayNoAccreditUser =homeService.yesterdayNoAccreditUser();//昨日未授权用户

    //订单
      Integer waitShipmentsOrderCount =homeService.waitShipmentsOrderCount();//待发货
      Integer waitTakeOrderCount =homeService.waitTakeOrderCount();//待自提
      Integer sendOrderCount =homeService.sendOrderCount();//已发货
      Integer codeSuccessOrderCount =homeService.codeSuccessOrderCount();//已核销
      Integer accomplishOrderCount =homeService.accomplishOrderCount();//已完成
      Integer failedOrderCount =homeService.failedOrderCount();//已过期


    //校园动态
      Integer todayTrends =homeService.todayTrends();//今日动态
      Integer yesterdayTrends =homeService.yesterdayTrends();//昨日发布
      Integer otherTrends =homeService.otherTrends();//其他
      Integer usedTrends =homeService.usedTrends();//二手
      Integer buyTrends =homeService.buyTrends();//求购
      Integer wishTrends =homeService.wishTrends();//许愿
      Integer yesterdayWishTrends =homeService.yesterdayWishTrends();//昨日许愿


    //动力币
    //总余额
      BigDecimal customerCoinCount =homeService.customerCoinCount();//总消费
      BigDecimal todayAddCoinCount =homeService.todayAddCoinCount();//今日增加
      BigDecimal todayCustomerCoinCount =homeService.todayCustomerCoinCount();//今日消费
      BigDecimal yesterdayAddCoinCount =homeService.yesterdayAddCoinCount();//昨日增加
      BigDecimal yesterdayCustomerCoinCount =homeService.yesterdayCustomerCoinCount();//昨日消费

        Home home=new Home();
        home.setUserCount(userCount);
        home.setYesterdayUserCount(yesterdayUserCount);
        home.setTodayOrder(todayOrder);
        home.setTodayUpOrder(todayUpOrder);
        home.setTodayDownOrder(todayDownOrder);
        home.setSchoolUsed(schoolUsed);
        home.setCoinCount(coinCount);
        //用户
        home.setTodayAddUser(todayAddUser);
        home.setTodayAccreditUser(todayAccreditUser);
        home.setTodayNoAccreditUser(todayNoAccreditUser);
        home.setYesterdayAddUser(yesterdayAddUser);
        home.setYesterdayAccreditUser(yesterdayAccreditUser);
        home.setYesterdayNoAccreditUser(yesterdayNoAccreditUser);
        //订单
        home.setWaitShipmentsOrderCount(waitShipmentsOrderCount);
        home.setWaitTakeOrderCount(waitTakeOrderCount);
        home.setSendOrderCount(sendOrderCount);
        home.setCodeSuccessOrderCount(codeSuccessOrderCount);
        home.setAccomplishOrderCount(accomplishOrderCount);
        home.setFailedOrderCount(failedOrderCount);
        //校园动态
        home.setTodayTrends(todayTrends);
        home.setYesterdayTrends(yesterdayTrends);
        home.setOtherTrends(otherTrends);
        home.setUsedTrends(usedTrends);
        home.setBuyTrends(buyTrends);
        home.setWishTrends(wishTrends);
        home.setYesterdayWishTrends(yesterdayWishTrends);
        //动力币
        home.setCustomerCoinCount(customerCoinCount);
        home.setTodayAddCoinCount(todayAddCoinCount);
        home.setTodayCustomerCoinCount(todayCustomerCoinCount);
        home.setYesterdayAddCoinCount(yesterdayAddCoinCount);
        home.setYesterdayCustomerCoinCount(yesterdayCustomerCoinCount);
        list.add(home);
        return new JsonResult(list);
    }

}
