package com.springboot.fun.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.fun.dao.MeDao;
import com.springboot.fun.entity.*;
import com.springboot.fun.service.MeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class MeServiceImpl implements MeService {


    @Autowired
    private MeDao meDao;


    /*
     * 小程序端
     *
     * */
    /*用户地址管理*/
    @Override
    public Integer insertUserAddress(WxUser wxUsers) {
        return meDao.insertUserAddress(wxUsers);
    }
    /*商务合作*/
    @Override
    public Integer insertCooperation(FunCooperation funCooperations) {
        return meDao.insertCooperation(funCooperations);
    }
    /*意见建议*/
    @Override
    public Integer insertOpinion(FunOpinion funOpinions) {
        return meDao.insertOpinion(funOpinions);
    }
    /*获取formid*/
    @Override
    public Integer insertFormId(UserFormEntity userFormEntitys) {
        return meDao.insertFormId(userFormEntitys);
    }
    //修改用户的朋友圈配图
    @Override
    public Integer updateUserMomentsUrl(WxUser wxUser1) {
        return meDao.updateUserMomentsUrl(wxUser1);
    }
    /*商务合作*/

    @Override
    public PageInfo<FunCooperation> selectCooperation(int page, int pageSize, Map<String,Object> searchMap) {
        PageHelper.startPage(page, pageSize);
        List<FunCooperation> lists = new ArrayList<>();
        lists = meDao.selectCooperation(searchMap);
        PageInfo<FunCooperation> pi = new PageInfo<>(lists);
        return pi;
    }
    /*意见建议*/
    @Override
    public PageInfo<FunOpinion> selectOpinion(int page, int pageSize, Map<String,Object> searchMap) {
        PageHelper.startPage(page, pageSize);
        List<FunOpinion> lists = new ArrayList<>();
        lists = meDao.selectOpinion(searchMap);
        PageInfo<FunOpinion> pi = new PageInfo<>(lists);
        return pi;

    }
    //查看会员的配置信息
    @Override
    public FunMemberDeploy selectMemberDeploy() {
        return meDao.selectMemberDeploy();
    }
    //把这个用户的邀请人数兑换的置为已兑换
    @Override
    public void updateUserMemberNum(Map<Object, Object> numMap) {
        meDao.updateUserMemberNum(numMap);
    }
    //用户修改为会员，修改兑换时间和截止时间
    @Override
    public void updateWxuser(WxUser wxUser) {
        meDao.updateWxuser(wxUser);
    }
    //修改用户的会员状态为不是会员
    @Override
    public void updateWxuserMemberState(String id) {
        meDao.updateWxuserMemberState(id);
    }
}
