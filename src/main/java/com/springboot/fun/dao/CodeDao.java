package com.springboot.fun.dao;

import com.springboot.fun.entity.FunCommodityOrder;
import com.springboot.fun.entity.OrderHexiaoEntity;
import com.springboot.fun.entity.SchoolOrder;
import com.springboot.fun.entity.SupplierEmployeeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CodeDao {
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
    List<OrderHexiaoEntity> findAllSuccessList(String userId);
    //查看核销人列表
    List<OrderHexiaoEntity> findAllSuccessUserList(String pnameId);
    // 快捷核销 根据这个订单号，查到校园福利订单信息
    FunCommodityOrder selectCommodityOrderByOrderCode(String scene);
}
