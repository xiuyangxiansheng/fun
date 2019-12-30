package com.springboot.fun.service;


import com.github.pagehelper.PageInfo;
import com.springboot.fun.entity.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Service
public interface CommodityService {
    /*
     * 小程序端
     *
     * */
    //获取商品列表
    PageInfo<FunCommodity> findCommodityList(int page, int pageSize, Map<String,Object> commodityMap);
    //根据商品id查看商品详情
    List<FunCommodity> findCommodityInf(String id);
    //根据商家id查看校园福利商家详情
    List<NideShopSupplier> findCommoditySupplierById(String supplierId);
    //浏览量真实都加1
    void updateBrowseNum(String supplierId);
    //查找核销码有没有重复，有，就再次生成
    String findCodeNumber(String orderCode);
    //查找用户动力币是否足够
    BigDecimal selectUserCoin(String userId);
    //查找库存是否足够
    Integer selectStore(String commodityId);
    //减库存
    void updateCommodityStore(String commodityId);
    //减动力币
    void updateUserCoin(Map<String,Object> coinMap);
    //生成订单
    Integer insertCommodityOrder(FunCommodityOrder funCommodityOrders);
    //已兑换（真实，虚拟加1）
    void updateCommodityCash(String commodityId);
    //生成动力币明细
    void insertCoinInf(Map<String,Object> coinInfMap);
    //获取好物兑换订单列表
    PageInfo<FunCommodityOrder> findCommodityOrder(int page, int pageSize, Map<String,Object> stateMap);
    //根据商品id查看订单详情
    List<FunCommodityOrder> findCommodityOrderById(String id);
    //确认收货
    Integer receiveCommodityOrder(String id);
    //查找用户的订单信息
    List<FunCommodityOrder> selectCommodityOrder(String userId);
    //修改核销状态为已失效
    void updateMyCommodityOrderState(String id);
    //查找用户兑换了多少次
    Integer selectUserConvertNum(Map<Object,Object> userConvertNumMap);
    //查找商品的信息（兑换的次数）
    FunCommodity selectCommodityOrderNum(String commodityId);
    //把用户头像添加到商品表的购买集合里面
    void addUserUrlToCommodity(FunCommodity funCommodity1);
    //分享商品
    Integer shareCommodity(String id);
    //搜索获取商品列表
    PageInfo<FunCommodity> searchCommodityList(int page, int pageSize, Map<String,Object> commodityMap);
    //上传商品
    Integer insertCommodity(FunCommodity funCommoditys);
    //编辑商品
    Integer updateCommodity(FunCommodity funCommoditys);
    //返回需要编辑的商品信息
    FunCommodity searchCommodity(String id);
    //下架商品
    Integer outCommodity(String id);
    //上架商品
    Integer upCommodity(String id);
    //查找筛选订单列表
    PageInfo<FunCommodityOrder> searchCommodityOrder(int page, int pageSize, Map<String,Object> commodityMap);
    //查找订单详情
    FunCommodityOrder searchCommodityOrderById(String id);
    //返回快递
    List<NideshopShipping> selectLogistics();
    //商品发货
    Integer sendCommodityOrder(FunCommodityOrder funCommodityOrders);
    //修改订单状态为已发货
    void updateCommodityOrderStateSend(String id);

    //获取会员列表
    PageInfo<FunCommodity> findMemberCommodityList(int page, int pageSize, Map<String,Object> commodityMap);
}
