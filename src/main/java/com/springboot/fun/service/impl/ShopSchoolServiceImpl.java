package com.springboot.fun.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.fun.dao.ShopSchoolDao;
import com.springboot.fun.entity.*;
import com.springboot.fun.service.ShopSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class ShopSchoolServiceImpl implements ShopSchoolService {


    @Autowired
    private ShopSchoolDao shopSchoolDao;
    /*
     * 小程序端
     *
     * */
    //获取校园福利首页列表--根据兑换量排序
    @Override
    public PageInfo<ShopSchool> finSchoolShopConvertList(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<ShopSchool> lists = new ArrayList<>();
        lists = shopSchoolDao.finSchoolShopConvertList();
        PageInfo<ShopSchool> pi = new PageInfo<>(lists);
        return pi;
    }
    //获取校园福利列表
    @Override
    public PageInfo<ShopSchool> finSchoolShopList(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<ShopSchool> lists = new ArrayList<>();
        lists = shopSchoolDao.finSchoolShopList();
        PageInfo<ShopSchool> pi = new PageInfo<>(lists);
        return pi;
    }
    //根据商品id查看校园福利商品详情
    @Override
    public List<ShopSchool> findShopSchoolById(String id) {
        return shopSchoolDao.findShopSchoolById(id);
    }
    //根据商品id查看校园福利商家详情
    @Override
    public List<NideShopSupplier> findSchoolSupplierById(String id) {
        return shopSchoolDao.findSchoolSupplierById(id);
    }
    //根据商品id查看商家活动（商家标签）
    @Override
    public List<NideshopSupplierLabel> findSchoolSupplierLabelById(String id) {
        return shopSchoolDao.findSchoolSupplierLabelById(id);
    }
    //浏览量真实都加1
    @Override
    public void updateBrowseNum(String id) {
        shopSchoolDao.updateBrowseNum(id);
    }

    /*
     * 立即兑换
     * */
    //查找是否以前兑换过(次数+时间限制)，查找动力币是否足够  减库存， 减去动力币， 生成订单，动力币明细
    //查找用户兑换了多少次
    @Override
    public Integer selectUserConvertNum(Map<Object, Object> userConvertNumMap) {
        return shopSchoolDao.selectUserConvertNum(userConvertNumMap);
    }
    //查找上一次兑换这件商品的时间
    //查找商家详情
    @Override
    public ShopSchool selectShopSchool(String orderSupplierId) {
        return shopSchoolDao.selectShopSchool(orderSupplierId);
    }
    @Override
    public Date selectUserLastConverTime(Map<Object, Object> userLastConverTimeMap) {
        return shopSchoolDao.selectUserLastConverTime(userLastConverTimeMap);
    }
    //根据id获取商家其他商品列表
    @Override
    public PageInfo<ShopSchool> finSchoolShopSupplierList(int page, int pageSize, String id) {
        PageHelper.startPage(page, pageSize);
        List<ShopSchool> lists = new ArrayList<>();
        lists = shopSchoolDao.finSchoolShopSupplierList(id);
        PageInfo<ShopSchool> pi = new PageInfo<>(lists);
        return pi;
    }

    //查找用户动力币是否足够
    @Override
    public BigDecimal selectUserCoin(String userId) {
        return shopSchoolDao.selectUserCoin(userId);
    }
    //查找库存是否足够
    @Override
    public Integer selectStore(String orderSchoolshopId) {
        return shopSchoolDao.selectStore(orderSchoolshopId);
    }

    //减库存
    @Override
    public void updateShopSchoolStore(String orderSchoolshopId) {
        shopSchoolDao.updateShopSchoolStore(orderSchoolshopId);
    }
    //加销量
    @Override
    public void updateShopSchoolSold(String orderSchoolshopId) {
        shopSchoolDao.updateShopSchoolSold(orderSchoolshopId);
    }

    //减动力币
    @Override
    public void updateUserCoin(Map<String, Object> coinMap) {
        shopSchoolDao.updateUserCoin(coinMap);
    }
    //生成订单
    @Override
    public Integer insertShopSchoolOrder(String id, String orderSupplierId, String orderSchoolshopId,
                                         String orderUserId, String orderSupplierName,String orderSupplierUrl, String orderSupplierPhone,
                                         Integer orderNumber, String orderSchoolshopName, String orderSchoolshopUrl,
                                         String orderSchoolshopAddress,String orderSerialNumber,String orderCodeNumber,Integer orderNumber1,
                                         BigDecimal orderCoin, BigDecimal orderOriginalCost,
                                         BigDecimal orderCurrentPrice) {

            SchoolOrder schoolOrder=new SchoolOrder();
        schoolOrder.setId(id);
        schoolOrder.setOrderSupplierId(orderSupplierId);
        schoolOrder.setOrderSchoolshopId(orderSchoolshopId);
        schoolOrder.setOrderUserId(orderUserId);
        schoolOrder.setOrderSupplierName(orderSupplierName);
        schoolOrder.setOrderSupplierUrl(orderSupplierUrl);
        schoolOrder.setOrderSupplierPhone(orderSupplierPhone);
        schoolOrder.setOrderNumber(orderNumber);
        schoolOrder.setOrderSchoolshopName(orderSchoolshopName);
        schoolOrder.setOrderSchoolshopUrl(orderSchoolshopUrl);
        schoolOrder.setOrderSchoolshopAddress(orderSchoolshopAddress);
        schoolOrder.setOrderSerialNumber(orderSerialNumber);
        schoolOrder.setOrderCodeNumber(orderCodeNumber);
        schoolOrder.setOrderNumber(orderNumber);
        schoolOrder.setOrderCoin(orderCoin);
        schoolOrder.setOrderOriginalCost(orderOriginalCost);
        schoolOrder.setOrderCurrentPrice(orderCurrentPrice);
        Integer num=shopSchoolDao.insertShopSchoolOrder(schoolOrder);
        return num;
    }
    //生成动力币明细
    @Override
    public Integer insertCoinInf(Map<String, Object> coinInfMap) {

        return shopSchoolDao.insertCoinInf(coinInfMap);
    }
    //根据类型获取校园福利订单列表
    @Override
    public PageInfo<SchoolOrder> finSchoolShopOrderList(int page, int pageSize, int type) {
        PageHelper.startPage(page, pageSize);
        List<SchoolOrder> lists = new ArrayList<>();
        lists = shopSchoolDao.finSchoolShopOrderList(type);
        PageInfo<SchoolOrder> pi = new PageInfo<>(lists);
        return pi;
    }
    //根据商品id查看校园福利订单详情
    @Override
    public List<SchoolOrder> finSchoolShopOrderById(String id) {
        return shopSchoolDao.finSchoolShopOrderById(id);
    }
    //查找核销码有没有重复，有，就再次生成
    @Override
    public String findCodeNumber(String orderCodeNumber) {
        return shopSchoolDao.findCodeNumber(orderCodeNumber);
    }

}
