package com.springboot.fun.dao;

import com.springboot.fun.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PiaoFuTongDao {
    /*
     * 小程序端
     *
     * */

    //票付通订单保存到数据库
    Integer insertPiaoFuTongOrder(PFTOrder pftOrders);
    //数据库 修改取消订单
    Integer changePiaoFuTongOrder(PFTChangeOrder pftChangeOrder1);
    //修改手机号
    Integer changePhonePiaoFuTongOrder(PFTChangeOrder pftChangeOrder1);
}
