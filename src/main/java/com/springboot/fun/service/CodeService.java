package com.springboot.fun.service;


import com.github.pagehelper.PageInfo;
import com.springboot.fun.entity.FunCommodityOrder;
import com.springboot.fun.entity.OrderHexiaoEntity;
import com.springboot.fun.entity.SchoolOrder;
import com.springboot.fun.entity.SupplierEmployeeEntity;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public interface CodeService {

    /*
     * 小程序端
     *
     * */
    //根据这个订单号，查到校园福利订单信息
    FunCommodityOrder selectCommodityOrderByOrderNo(String scene);

    //根据用户id，去员工表里面，查询这个扫描用户的信息
    SupplierEmployeeEntity selectEmployeeByUserId(Map<String, Object> employeeMap);
    //插入到核销记录表
    Integer insertHexiaoInf(OrderHexiaoEntity orderHexiao);
    //改变订单状态 已完成
    void updateCommodityOrderState(String orderId);
    //查看核销列表(一个人有多个商家核销身份)
    PageInfo<OrderHexiaoEntity> findAllSuccessList(int page, int pageSize,String userId);
    //查看核销人列表
    PageInfo<OrderHexiaoEntity> findAllSuccessUserList(int page, int pageSize, String pnameId);
    // 快捷核销 根据这个订单号，查到单信息
    FunCommodityOrder selectCommodityOrderByOrderCode(String scene);
}
