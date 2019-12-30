package com.springboot.fun.service;


import com.github.pagehelper.PageInfo;
import com.springboot.fun.entity.*;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public interface MeService {

    /*
     * 小程序端
     *
     * */
    /*用户地址管理*/
    Integer insertUserAddress(WxUser wxUsers);
    /*商务合作*/
    Integer insertCooperation(FunCooperation funCooperations);
    /*意见建议*/
    Integer insertOpinion(FunOpinion funOpinions);
    /*获取formid*/
    Integer insertFormId(UserFormEntity userFormEntitys);
    //修改用户的朋友圈配图
    Integer updateUserMomentsUrl(WxUser wxUser1);
    /*商务合作*/
    PageInfo<FunCooperation> selectCooperation(int page, int pageSize, Map<String,Object> searchMap);
    /*意见建议*/
    PageInfo<FunOpinion> selectOpinion(int page, int pageSize, Map<String,Object> searchMap);

    //查看会员的配置信息
    FunMemberDeploy selectMemberDeploy();
    //把这个用户的邀请人数兑换的置为已兑换
    void updateUserMemberNum(Map<Object,Object> numMap);
    //用户修改为会员，修改兑换时间和截止时间
    void updateWxuser(WxUser wxUser);
    //修改用户的会员状态为不是会员
    void updateWxuserMemberState(String id);
}
