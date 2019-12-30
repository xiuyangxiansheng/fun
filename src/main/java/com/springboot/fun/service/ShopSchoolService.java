package com.springboot.fun.service;


import com.github.pagehelper.PageInfo;
import com.springboot.fun.entity.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public interface ShopSchoolService {
    /*
     * 小程序端
     *
     * */
    //获取校园福利首页列表--根据兑换量排序
    PageInfo<ShopSchool> finSchoolShopConvertList(int page, int pageSize);
    //获取校园福利列表
    PageInfo<ShopSchool> finSchoolShopList(int page, int pageSize);
    //根据商品id查看校园福利商品详情
    List<ShopSchool> findShopSchoolById(String id);
    //根据商品id查看校园福利商家详情
    List<NideShopSupplier> findSchoolSupplierById(String id);
    //根据商品id查看商家活动（商家标签）
    List<NideshopSupplierLabel> findSchoolSupplierLabelById(String id);
    //浏览量真实都加1
    void updateBrowseNum(String id);
    /*
     * 立即兑换
     * */
    //查找是否以前兑换过(次数+时间限制)，查找动力币是否足够  减库存， 减去动力币， 生成订单，动力币明细
    //查找用户兑换了多少次
    Integer selectUserConvertNum(Map<Object,Object> userConvertNumMap);
    //查找上一次兑换这件商品的时间
    //查找商家详情
    ShopSchool selectShopSchool(String orderSupplierId);
     Date selectUserLastConverTime(Map<Object,Object> userLastConverTimeMap);
    //根据id获取商家其他商品列表
    PageInfo<ShopSchool> finSchoolShopSupplierList(int page, int pageSize, String id);
    //查找用户动力币是否足够
    BigDecimal selectUserCoin(String userId);
    //查找库存是否足够
    Integer selectStore(String orderSchoolshopId);
    //减库存
    void updateShopSchoolStore(String orderSchoolshopId);
    //加销量
    void updateShopSchoolSold(String orderSchoolshopId);
    //减动力币
    void updateUserCoin(Map<String,Object> coinMap);
    //生成订单
    Integer insertShopSchoolOrder(String id, String orderSupplierId, String orderSchoolshopId, String userId, String orderSupplierName,String orderSupplierUrl, String orderSupplierPhone, Integer orderNumber, String orderSchoolshopName, String orderSchoolshopUrl, String orderSchoolshopAddress,String orderSerialNumber,String orderCodeNumber, Integer orderNumber1, BigDecimal orderCoin, BigDecimal orderOriginalCost, BigDecimal orderCurrentPrice);
    //生成动力币明细
    Integer insertCoinInf(Map<String,Object> coinInfMap);
    //根据类型获取校园福利订单列表
    PageInfo<SchoolOrder> finSchoolShopOrderList(int page, int pageSize, int type);
    //根据商品id查看校园福利订单详情
    List<SchoolOrder> finSchoolShopOrderById(String id);
    //查找核销码有没有重复，有，就再次生成
    String findCodeNumber(String orderCodeNumber);

}
