package com.springboot.fun.service.impl;

import com.springboot.fun.dao.PiaoFuTongDao;
import com.springboot.fun.entity.PFTChangeOrder;
import com.springboot.fun.entity.PFTOrder;
import com.springboot.fun.service.PiaoFuTongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PiaoFuTongServiceImpl implements PiaoFuTongService {


    @Autowired
    private PiaoFuTongDao piaoFuTongDao;

    /*
     * 小程序端
     *
     * */
    //提交订单
    @Override
    public Integer insertPiaoFuTongOrder(PFTOrder pftOrders) {
        return piaoFuTongDao.insertPiaoFuTongOrder(pftOrders);
    }
    //数据库 修改取消订单
    @Override
    public Integer changePiaoFuTongOrder(PFTChangeOrder pftChangeOrder1) {
        return piaoFuTongDao.changePiaoFuTongOrder(pftChangeOrder1);
    }
    //修改手机号
    @Override
    public Integer changePhonePiaoFuTongOrder(PFTChangeOrder pftChangeOrder1) {
        return piaoFuTongDao.changePiaoFuTongOrder(pftChangeOrder1);
    }

}
