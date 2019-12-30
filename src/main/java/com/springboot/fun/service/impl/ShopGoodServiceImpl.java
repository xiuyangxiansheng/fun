package com.springboot.fun.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.fun.dao.ShopGoodDao;
import com.springboot.fun.entity.GoodOrder;
import com.springboot.fun.entity.ShopGood;
import com.springboot.fun.entity.ShopGoodType;
import com.springboot.fun.service.ShopGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class ShopGoodServiceImpl implements ShopGoodService {


    @Autowired
    private ShopGoodDao shopGoodDao;

    /*
     * 小程序端
     *
     * */
    //获取好物兑换列表
    @Override
    public PageInfo<ShopGood> finGoodShopList(int page, int pageSize, int type) {
        PageHelper.startPage(page, pageSize);
        List<ShopGood> lists = new ArrayList<>();
        lists = shopGoodDao.finGoodShopList(type);
        PageInfo<ShopGood> pi = new PageInfo<>(lists);
        return pi;
    }
    //根据商品id查看好物兑换商品详情
    @Override
    public List<ShopGood> findShopGoodById(String id) {
        return shopGoodDao.findShopGoodById(id);
    }
    //获取好物兑换分类
    @Override
    public List<ShopGoodType> findShopGoodType() {
        return shopGoodDao.findShopGoodType();
    }
    /*
     * 立即兑换
     * */
    //查找是否以前兑换过(次数+时间限制)--好物兑换不限次数，查找动力币是否足够  减库存， 减去动力币， 生成订单，销量加一，动力币明细
    //查找用户动力币是否足够
    @Override
    public BigDecimal selectUserCoin(String userId) {
        return shopGoodDao.selectUserCoin(userId);
    }
    //查找库存是否足够
    @Override
    public Integer selectStore(String orderGoodId) {
        return shopGoodDao.selectStore(orderGoodId);
    }

    //减库存
    @Override
    public void updateShopGoodStore(String orderGoodId) {
        shopGoodDao.updateShopGoodStore(orderGoodId);
    }
    //减动力币
    @Override
    public void updateUserCoin(Map<String, Object> coinMap) {
        shopGoodDao.updateUserCoin(coinMap);
    }
    //生成订单
    @Override
    public Integer insertShopGoodOrder(String id, String orderGoodId, String orderUserId, String orderName,
                                       String orderLitimg, String orderSerialNumber, Integer orderNumber,
                                       String orderMessage, BigDecimal orderCoin, Integer orderTypeNum,String orderType,
                                       String orderMemberName, String orderMemberPhone, String orderMemberAddress) {
        GoodOrder goodOrder=new GoodOrder();
        goodOrder.setId(id);
        goodOrder.setOrderGoodId(orderGoodId);
        goodOrder.setOrderUserId(orderUserId);
        goodOrder.setOrderName(orderName);
        goodOrder.setOrderLitimg(orderLitimg);
        goodOrder.setOrderSerialNumber(orderSerialNumber);
        goodOrder.setOrderSerialNumber(orderSerialNumber);
        goodOrder.setOrderNumber(orderNumber);
        goodOrder.setOrderMessage(orderMessage);
        goodOrder.setOrderCoin(orderCoin);
        goodOrder.setOrderTypeNum(orderTypeNum);
        goodOrder.setOrderType(orderType);
        goodOrder.setOrderMemberName(orderMemberName);
        goodOrder.setOrderMemberPhone(orderMemberPhone);
        goodOrder.setOrderMemberAddress(orderMemberAddress);
        Integer num= shopGoodDao.insertShopGoodOrder(goodOrder);
        return num;
    }
    //已兑换（真实，虚拟加1）
    @Override
    public void updateShopGoodCash(String orderGoodId) {
        shopGoodDao.updateShopGoodCash(orderGoodId);
    }
    //生成动力币明细
    @Override
    public void insertCoinInf(Map<String, Object> coinInfMap) {
        shopGoodDao.insertCoinInf(coinInfMap);
    }
    //获取好物兑换订单列表
    @Override
    public PageInfo<GoodOrder> finGoodShopOrderAllList(int page, int pageSize, int type) {
        PageHelper.startPage(page, pageSize);
        List<GoodOrder> lists = new ArrayList<>();
        lists = shopGoodDao.finGoodShopOrderAllList(type);
        PageInfo<GoodOrder> pi = new PageInfo<>(lists);
        return pi;
    }
    //获取好物兑换订单列表
    @Override
    public PageInfo<GoodOrder> finGoodShopOrderList(int page, int pageSize, int type) {
        PageHelper.startPage(page, pageSize);
        List<GoodOrder> lists = new ArrayList<>();
        lists = shopGoodDao.finGoodShopOrderList(type);
        PageInfo<GoodOrder> pi = new PageInfo<>(lists);
        return pi;
    }
    //根据商品id查看好物兑换订单详情
    @Override
    public List<GoodOrder> finGoodShopOrderById(String id) {
        return shopGoodDao.finGoodShopOrderById(id);
    }
    //确认收货
    @Override
    public Integer receiveGoodShop(String id) {
        return shopGoodDao.receiveGoodShop(id);
    }
    //分享商品
    @Override
    public Integer shareGoodShop(String id) {
        return shopGoodDao.shareGoodShop(id);
    }
    //根据商品id，商品名称模糊搜索好物兑换商品
    @Override
    public List<ShopGood> searchShopGoodById(String search) {
        return shopGoodDao.searchShopGoodById(search);
    }
}
