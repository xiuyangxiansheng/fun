package com.springboot.fun.service;


import com.springboot.fun.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface SignService {


    /*
     * 小程序端
     *
     * */
    //今日步数兑换成动力币
    Map<String,Object> stepConvertMoney(String userId);

    //根据用户id查到用户的openID
    String selectUserOpenId(String userId);
    //查找用户步数情况
    YlqUserStep selectYlqUserSteInf(Map<String,Object> ylqUserStepMap);
    //修改步数
    void updateYlqUserSteInfById(YlqUserStep ylqUserStep);
    //存步数信息
    void insertYlqUserSteInf(YlqUserStep ylqUserStep);
    //签到
    SignEntity sign(String userId);
    //查找用户签到信息
    SignEntity selectsignEntity(String userId);
    //查找签到配置表
    List<SignSettingEntity> selectSettingSign();
    //任务规则配置
    List<YlqTask> taskSetting();
    //查找用户动力币的奖励规则
    YlqTask selectTask(int type);
    /*查找用户的任务明细*/
    YlqUserTag selectUserTag(String userId);
    //用户任务明细
    List<YlqUserStep> selectUserStep(String userId);
    //随机一张图片
    String selectClockUrl();
    //随机一段文字
    String selectClockWord();
}
