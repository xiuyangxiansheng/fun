package com.springboot.fun.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.fun.dao.CodeDao;
import com.springboot.fun.entity.*;
import com.springboot.fun.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class CodeServiceImpl implements CodeService {


    @Autowired
    private CodeDao codeDao;

    /*
     * 小程序端
     *
     * */

    //根据这个订单号，查到校园福利订单信息
    @Override
    public FunCommodityOrder selectCommodityOrderByOrderNo(String scene) {
        return codeDao.selectCommodityOrderByOrderNo(scene);
    }
    //根据用户id，去员工表里面，查询这个扫描用户的信息
    @Override
    public SupplierEmployeeEntity selectEmployeeByUserId(Map<String, Object> employeeMap) {
        return codeDao.selectEmployeeByUserId(employeeMap);
    }
    //插入到核销记录表
    @Override
    public Integer insertHexiaoInf(OrderHexiaoEntity orderHexiao) {
        return codeDao.insertHexiaoInf(orderHexiao);
    }
    //改变订单状态 已完成
    @Override
    public void updateCommodityOrderState(String orderId) {
        codeDao.updateCommodityOrderState(orderId);
    }
    //查看核销列表(一个人有多个商家核销身份)
    @Override
    public PageInfo<OrderHexiaoEntity> findAllSuccessList(int page, int pageSize,String userId) {
        PageHelper.startPage(page, pageSize);
        List<OrderHexiaoEntity> lists = new ArrayList<>();
        lists = codeDao.findAllSuccessList(userId);
        PageInfo<OrderHexiaoEntity> pi = new PageInfo<>(lists);
        return pi;
    }
    //查看核销人列表
    @Override
    public PageInfo<OrderHexiaoEntity> findAllSuccessUserList(int page, int pageSize, String pnameId) {
        PageHelper.startPage(page, pageSize);
        List<OrderHexiaoEntity> lists = new ArrayList<>();
        lists = codeDao.findAllSuccessUserList(pnameId);
        PageInfo<OrderHexiaoEntity> pi = new PageInfo<>(lists);
        return pi;
    }
    // 快捷核销 根据这个订单号，查到校园福利订单信息
    @Override
    public FunCommodityOrder selectCommodityOrderByOrderCode(String scene) {
        return codeDao.selectCommodityOrderByOrderCode(scene);
    }


}
