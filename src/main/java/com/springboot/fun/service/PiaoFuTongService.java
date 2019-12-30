package com.springboot.fun.service;


import com.github.pagehelper.PageInfo;
import com.springboot.fun.entity.*;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public interface PiaoFuTongService {
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
