package com.springboot.fun.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.fun.dao.CommodityDao;
import com.springboot.fun.entity.*;
import com.springboot.fun.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class CommodityServiceImpl implements CommodityService{


    @Autowired
    private CommodityDao commodityDao;

    /*
     * 小程序端
     *
     * */
    //获取商品列表
    @Override
    public PageInfo<FunCommodity> findCommodityList(int page, int pageSize, Map<String,Object> commodityMap) {
        PageHelper.startPage(page, pageSize);
        List<FunCommodity> lists = new ArrayList<>();
        lists = commodityDao.findCommodityList(commodityMap);
        PageInfo<FunCommodity> pi = new PageInfo<>(lists);
        return pi;
    }
    //获取会员列表
    @Override
    public PageInfo<FunCommodity> findMemberCommodityList(int page, int pageSize, Map<String, Object> commodityMap) {
        PageHelper.startPage(page, pageSize);
        List<FunCommodity> lists = new ArrayList<>();
        lists = commodityDao.findMemberCommodityList(commodityMap);
        PageInfo<FunCommodity> pi = new PageInfo<>(lists);
        return pi;
    }
    //根据商品id查看商品详情
    @Override
    public List<FunCommodity> findCommodityInf(String id) {
        return commodityDao.findCommodityInf(id);
    }
    //根据商家id查看校园福利商家详情
    public List<NideShopSupplier> findCommoditySupplierById(String supplierId) {
        return commodityDao.findCommoditySupplierById(supplierId);
    }
    //浏览量真实都加1
    @Override
    public void updateBrowseNum(String supplierId) {
        commodityDao.updateBrowseNum(supplierId);
    }
    //查找核销码有没有重复，有，就再次生成
    @Override
    public String findCodeNumber(String orderCode) {
        return commodityDao.findCodeNumber(orderCode);
    }
    //查找用户动力币是否足够
    @Override
    public BigDecimal selectUserCoin(String userId) {
        return commodityDao.selectUserCoin(userId);
    }
    //查找库存是否足够
    @Override
    public Integer selectStore(String commodityId) {
        return commodityDao.selectStore(commodityId);
    }
    //减库存
    @Override
    public void updateCommodityStore(String commodityId) {
        commodityDao.updateCommodityStore(commodityId);
    }
    //减动力币
    @Override
    public void updateUserCoin(Map<String, Object> coinMap) {
        commodityDao.updateUserCoin(coinMap);
    }
    //生成订单
    @Override
    public Integer insertCommodityOrder(FunCommodityOrder funCommodityOrders) {
        return commodityDao.insertCommodityOrder(funCommodityOrders);
    }
    //已兑换（真实，虚拟加1）
    @Override
    public void updateCommodityCash(String commodityId) {
        commodityDao.updateCommodityCash(commodityId);
    }
    //生成动力币明细
    @Override
    public void insertCoinInf(Map<String, Object> coinInfMap) {
        commodityDao.insertCoinInf(coinInfMap);
    }
    //获取好物兑换订单列表
    @Override
    public PageInfo<FunCommodityOrder> findCommodityOrder(int page, int pageSize, Map<String, Object> stateMap) {
        PageHelper.startPage(page, pageSize);
        List<FunCommodityOrder> lists = new ArrayList<>();
        lists = commodityDao.findCommodityOrder(stateMap);
        PageInfo<FunCommodityOrder> pi = new PageInfo<>(lists);
        return pi;
    }
    //根据商品id查看订单详情
    @Override
    public List<FunCommodityOrder> findCommodityOrderById(String id) {
        return commodityDao.findCommodityOrderById(id);
    }
    //确认收货
    @Override
    public Integer receiveCommodityOrder(String id) {
        return commodityDao.receiveCommodityOrder(id);
    }
    //查找用户的订单信息
    @Override
    public List<FunCommodityOrder> selectCommodityOrder(String userId) {
        return commodityDao.selectCommodityOrder(userId);
    }
    //修改核销状态为已失效
    @Override
    public void updateMyCommodityOrderState(String id) {
        commodityDao.updateMyCommodityOrderState(id);
    }
    //查找用户兑换了多少次
    @Override
    public Integer selectUserConvertNum(Map<Object, Object> userConvertNumMap) {
        return commodityDao.selectUserConvertNum(userConvertNumMap);
    }
    //查找商品的信息（兑换的次数）
    @Override
    public FunCommodity selectCommodityOrderNum(String commodityId) {
        return commodityDao.selectCommodityOrderNum(commodityId);
    }
    //把用户头像添加到商品表的购买集合里面
    @Override
    public void addUserUrlToCommodity(FunCommodity funCommodity1) {
        commodityDao.addUserUrlToCommodity(funCommodity1);
    }
    //分享商品
    @Override
    public Integer shareCommodity(String id) {
        return commodityDao.shareCommodity(id);
    }
    //搜索获取商品列表
    @Override
    public PageInfo<FunCommodity> searchCommodityList(int page, int pageSize, Map<String, Object> commodityMap) {
        PageHelper.startPage(page, pageSize);
        List<FunCommodity> lists = new ArrayList<>();
        lists = commodityDao.searchCommodityList(commodityMap);
        PageInfo<FunCommodity> pi = new PageInfo<>(lists);
        return pi;
    }
    //上传商品
    @Override
    public Integer insertCommodity(FunCommodity funCommoditys) {
        return commodityDao.insertCommodity(funCommoditys);
    }
    //编辑商品
    @Override
    public Integer updateCommodity(FunCommodity funCommoditys) {

        return commodityDao.updateCommodity(funCommoditys);
    }
    //返回需要编辑的商品信息
    @Override
    public FunCommodity searchCommodity(String id) {
        return commodityDao.searchCommodity(id);
    }
    //下架商品
    @Override
    public Integer outCommodity(String id) {
        return commodityDao.outCommodity(id);
    }
    //上架商品
    @Override
    public Integer upCommodity(String id) {
        return commodityDao.upCommodity(id);
    }
    //查找筛选订单列表
    @Override
    public PageInfo<FunCommodityOrder> searchCommodityOrder(int page, int pageSize, Map<String, Object> commodityMap) {
        PageHelper.startPage(page, pageSize);
        List<FunCommodityOrder> lists = new ArrayList<>();
        lists = commodityDao.searchCommodityOrder(commodityMap);
        PageInfo<FunCommodityOrder> pi = new PageInfo<>(lists);
        return pi;
    }
    //查找订单详情
    @Override
    public FunCommodityOrder searchCommodityOrderById(String id) {
        return commodityDao.searchCommodityOrderById(id);
    }
    //返回快递
    @Override
    public List<NideshopShipping> selectLogistics() {
        return commodityDao.selectLogistics();
    }
    //商品发货
    @Override
    public Integer sendCommodityOrder(FunCommodityOrder funCommodityOrders) {
        return commodityDao.sendCommodityOrder(funCommodityOrders);
    }

    @Override
    public void updateCommodityOrderStateSend(String id) {
        commodityDao.updateCommodityOrderStateSend(id);
    }



}
