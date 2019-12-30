package com.springboot.fun.dao;

import com.springboot.fun.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SignDao {
    /*
     * 小程序端
     *
     * */
    //根据用户id查到用户的openID
    String selectUserOpenId(String userId);
    //查找用户步数情况
    YlqUserStep selectYlqUserSteInf(Map<String,Object> ylqUserStepMap);
    //修改步数
    void updateYlqUserSteInfById(YlqUserStep ylqUserStep);
    //存步数信息
    void insertYlqUserSteInf(YlqUserStep ylqUserStep);
    //更新钱包
    void addUserCoin(Map<String,Object> moneyMap);

    //查找用户签到信息
    SignEntity selectsignEntity(String userId);
    //修改签到信息
    void updateSignEntityById(SignEntity signEntity);
    //查找连续签到的天数
    SignSettingEntity selectSignSettingEntity(int lianQian);
    //第一次签到，插入数据
    void insertSignEntity(SignEntity signEntity);
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
