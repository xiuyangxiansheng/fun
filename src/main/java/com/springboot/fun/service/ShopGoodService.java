package com.springboot.fun.service;


import com.github.pagehelper.PageInfo;
import com.springboot.fun.entity.GoodOrder;
import com.springboot.fun.entity.ShopGood;
import com.springboot.fun.entity.ShopGoodType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Service
public interface ShopGoodService {
    /*
     * 小程序端
     *
     * */
    //获取好物兑换列表
    PageInfo<ShopGood> finGoodShopList(int page, int pageSize, int type);
    //根据商品id查看好物兑换商品详情
    List<ShopGood> findShopGoodById(String id);
    //获取好物兑换分类
    List<ShopGoodType> findShopGoodType();
    /*
     * 立即兑换
     * */
    //查找是否以前兑换过(次数+时间限制)--好物兑换不限次数，查找动力币是否足够  减库存， 减去动力币， 生成订单，销量加一，动力币明细
    //查找用户动力币是否足够
    BigDecimal selectUserCoin(String userId);
    //查找库存是否足够
    Integer selectStore(String orderGoodId);
    //减库存
    void updateShopGoodStore(String orderGoodId);
    //减动力币
    void updateUserCoin(Map<String,Object> coinMap);
    //生成订单
    Integer insertShopGoodOrder(String id, String orderGoodId, String orderUserId, String orderName, String orderLitimg, String orderSerialNumber, Integer orderNumber, String orderMessage, BigDecimal orderCoin, Integer orderTypeNum,String orderType, String orderMemberName, String orderMemberPhone, String orderMemberAddress);
    //已兑换（真实，虚拟加1）
    void updateShopGoodCash(String orderGoodId);
    //生成动力币明细
    void insertCoinInf(Map<String,Object> coinInfMap);

    //获取好物兑换订单列表
    PageInfo<GoodOrder> finGoodShopOrderAllList(int page, int pageSize, int type);
    //获取好物兑换订单列表
    PageInfo<GoodOrder> finGoodShopOrderList(int page, int pageSize, int type);
    //根据商品id查看好物兑换订单详情
    List<GoodOrder> finGoodShopOrderById(String id);
    //确认收货
    Integer receiveGoodShop(String id);
    //分享商品
    Integer shareGoodShop(String id);
    //根据商品id，商品名称模糊搜索好物兑换商品
    List<ShopGood> searchShopGoodById(String search);
}
