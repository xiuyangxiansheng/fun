package com.springboot.fun.service.impl;


import com.springboot.fun.dao.ShopGoodDao;
import com.springboot.fun.dao.SignDao;
import com.springboot.fun.dao.SysConfigDao;
import com.springboot.fun.entity.*;
import com.springboot.fun.service.SignService;
import com.springboot.fun.util.UuidUtils;
import com.springboot.fun.util.utils.ApiRRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SignServiceImpl implements SignService {


    @Autowired
    private SignDao signDao;
    @Autowired
    private SysConfigDao sysConfigDao;
    @Autowired
    private ShopGoodDao shopGoodDao;
    /*
     * 小程序端
     *
     * */
    //今日步数兑换成动力币
    @Override
    public Map<String, Object> stepConvertMoney(String userId) {
        Map result = new HashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       /* QueryWrapper queryWrapperStep=new QueryWrapper();
        queryWrapperStep.eq("user_id", wxUserEntity.getId());
        queryWrapperStep.eq("day", sdf.format(new Date()));
        UserStepEntity userStepEntity = userStepDao.selectOne(queryWrapperStep);*/
        /*查找用户的步数情况*/
        Map<String, Object> ylqUserStepMap = new HashMap<String, Object>();
        ylqUserStepMap.put("userId", userId);
        ylqUserStepMap.put("day", sdf.format(new Date()));
        YlqUserStep ylqUserStep = signDao.selectYlqUserSteInf(ylqUserStepMap);


        int step_max =Integer.parseInt(sysConfigDao.queryByKey("step_max"));
        System.out.println("step_max"+step_max);
        String step_ratio = sysConfigDao.queryByKey("step_ratio");//比率
        if(ylqUserStep.getStepConvert()>=step_max){
            throw new ApiRRException("每人每日最多兑换"+step_max+"步!");
        }
        int canConvert=(ylqUserStep.getStepTotal()>step_max?step_max:ylqUserStep.getStepTotal())-ylqUserStep.getStepConvert();
        if(canConvert<=0){
            throw new ApiRRException("可兑换步数不足!");
        }
        BigDecimal convertMoney=new BigDecimal(canConvert).divide(new BigDecimal(step_ratio)).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println("convertMoney"+convertMoney);
        ylqUserStep.setStepConvert(ylqUserStep.getStepConvert()+canConvert);
        System.out.println("convertMoney"+convertMoney);
        //更新步数信息(待核验)
        signDao.updateYlqUserSteInfById(ylqUserStep);
        //更新钱包
        Map<String, Object> moneyMap = new HashMap<String, Object>();
        moneyMap.put("userId", userId);
        moneyMap.put("convertMoney", convertMoney);
        signDao.addUserCoin(moneyMap);
        //生成动力币明细
        Map<String, Object> coinInfMap = new HashMap<String, Object>();
        BigDecimal userCoin=shopGoodDao.selectUserCoin(userId);
        BigDecimal balance=userCoin.add(convertMoney);
        String coinId = UuidUtils.creatUUID();//主键
        coinInfMap.put("id", coinId);
        coinInfMap.put("userId", userId);
        coinInfMap.put("orderCoin", convertMoney);
        coinInfMap.put("balance", userCoin);
        coinInfMap.put("state", 1);
        coinInfMap.put("type", 2);
        coinInfMap.put("remark", "成功兑换"+canConvert+"步");
        shopGoodDao.insertCoinInf(coinInfMap);
        /*apiWalletService.accountEntry(userId,convertMoney, WalletDetailConstants.TYPE_BSDH,"成功兑换"+canConvert+"步",null);*/
        result.put("todayConvertStep",ylqUserStep.getStepConvert());
        result.put("convertMoney",convertMoney);
        return result;
    }
    //根据用户id查到用户的openID
    @Override
    public String selectUserOpenId(String userId) {
        return signDao.selectUserOpenId(userId);
    }
    //查找用户步数情况
    @Override
    public YlqUserStep selectYlqUserSteInf(Map<String, Object> ylqUserStepMap) {
        return signDao.selectYlqUserSteInf(ylqUserStepMap);
    }
    //修改步数
    @Override
    public void updateYlqUserSteInfById(YlqUserStep ylqUserStep) {
        signDao.updateYlqUserSteInfById(ylqUserStep);
    }
    //存步数信息
    @Override
    public void insertYlqUserSteInf(YlqUserStep ylqUserStep) {
        signDao.insertYlqUserSteInf(ylqUserStep);
    }
    //签到
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SignEntity sign(String userId) {
        //查找用户签到信息
        SignEntity signEntity = signDao.selectsignEntity(userId);
        System.out.println(signEntity.getSignCount()+"天数");
        Date updateTime2=signEntity.getUpdateTime();
        updateTime2.setTime(updateTime2.getTime() - 8*60*60*1000);//时差，减8小时
        if (signEntity != null && signEntity.getSignCount()!=0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println(signEntity.getUpdateTime()+"77777777777777777");
            if (!sdf.format(updateTime2).equals(sdf.format(new Date()))) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.add(Calendar.DATE, -1);
                int nowYear = calendar.get(Calendar.YEAR);
                int nowMouth = calendar.get(Calendar.MONTH);
                int yesterday = calendar.get(Calendar.DAY_OF_MONTH);
                calendar.setTime(signEntity.getUpdateTime());
                int lastYear = calendar.get(Calendar.YEAR);
                int lastMouth = calendar.get(Calendar.MONTH);
                int lastDay = calendar.get(Calendar.DAY_OF_MONTH);
                if (nowYear == lastYear && nowMouth == lastMouth && yesterday == lastDay &&signEntity.getSignCount()<7) {
                    signEntity.setSignCount(signEntity.getSignCount() + 1);
                    signEntity.setSignHistory(1L);
                    Date updateTime=new Date();
                    updateTime.setTime(updateTime.getTime() + 8*60*60*1000);
                    signEntity.setUpdateTime(updateTime);
                    System.out.println(updateTime+"99999999999999999999999999999");
                } else {
                    signEntity.setSignCount(1);
                    signEntity.setSignHistory(1L);
                    Date updateTime=new Date();
                    updateTime.setTime(updateTime.getTime() + 8*60*60*1000);
                    signEntity.setUpdateTime(updateTime);
                    System.out.println((new Date()));
                    System.out.println(updateTime+"88888888888888888888");
                }
                //修改签到信息
                signDao.updateSignEntityById(signEntity);
                int lianQian=signEntity.getSignCount();
/*                QueryWrapper queryWrapperSignSetting=new QueryWrapper();
                queryWrapperSignSetting.eq("day", lianQian);*/
                //查找连续签到的天数
                SignSettingEntity signSettingEntity = signDao.selectSignSettingEntity(lianQian);
                Map<String, Object> coinInfMap = new HashMap<String, Object>();
                //更新钱包
                Map<String, Object> moneyMap = new HashMap<String, Object>();
                moneyMap.put("userId", userId);
                moneyMap.put("convertMoney", signSettingEntity.getMoney());
                signDao.addUserCoin(moneyMap);

                BigDecimal userCoin=shopGoodDao.selectUserCoin(userId);
                /*BigDecimal balance=userCoin.add(signSettingEntity.getMoney());*/
                String coinId = UuidUtils.creatUUID();//主键
                coinInfMap.put("id", coinId);
                coinInfMap.put("userId", userId);
                coinInfMap.put("orderCoin", signSettingEntity.getMoney());
                coinInfMap.put("balance", userCoin);
                coinInfMap.put("state", 1);
                coinInfMap.put("type", 2);
                coinInfMap.put("remark", "连续签到"+lianQian+"天获得"+signSettingEntity.getMoney()+"动力币");
                shopGoodDao.insertCoinInf(coinInfMap);
            }

        } else {//改到controller层去添加
            System.out.println("到这表了");
            /*   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            signEntity = new SignEntity();
            String id = UuidUtils.creatUUID();//主键
            signEntity.setId(id);
            signEntity.setUserId(userId);
            signEntity.setSignCount(1);
            signEntity.setSignHistory(1L);
            signEntity.setStatus(1);
           *//* signEntity.setCreateTime(new Date());
            signEntity.setUpdateTime(new Date());*//*
            //第一次签到，插入数据
            signDao.insertSignEntity(signEntity);*/
            signEntity.setSignCount(1);
            signEntity.setSignHistory(1L);
            Date updateTime=new Date();
            updateTime.setTime(updateTime.getTime() + 8*60*60*1000);
            signEntity.setUpdateTime(updateTime);
            System.out.println((new Date()));
            signDao.updateSignEntityById(signEntity);
            int lianQian=signEntity.getSignCount();
            //查找连续签到的天数
            SignSettingEntity signSettingEntity = signDao.selectSignSettingEntity(lianQian);
            //更新钱包
            Map<String, Object> moneyMap = new HashMap<String, Object>();
            moneyMap.put("userId", userId);
            moneyMap.put("convertMoney", signSettingEntity.getMoney());
            signDao.addUserCoin(moneyMap);

            Map<String, Object> coinInfMap = new HashMap<String, Object>();
            BigDecimal userCoin=shopGoodDao.selectUserCoin(userId);
            /*BigDecimal balance=userCoin.add(signSettingEntity.getMoney());*/
            String coinId = UuidUtils.creatUUID();//主键
            coinInfMap.put("id", coinId);
            coinInfMap.put("userId", userId);
            coinInfMap.put("orderCoin", signSettingEntity.getMoney());
            coinInfMap.put("balance", userCoin);
            coinInfMap.put("state", 1);
            coinInfMap.put("type", 2);
            coinInfMap.put("remark", "连续签到"+lianQian+"天获得"+signSettingEntity.getMoney()+"动力币");
            shopGoodDao.insertCoinInf(coinInfMap);
        }
        System.out.println(signEntity);
        return signEntity;
    }
    //查找用户签到信息
    @Override
    public SignEntity selectsignEntity(String userId) {

        return signDao.selectsignEntity(userId);
    }
    //查找签到配置表
    @Override
    public List<SignSettingEntity> selectSettingSign() {
        return signDao.selectSettingSign();
    }
    //任务规则配置
    @Override
    public List<YlqTask> taskSetting() {
        return signDao.taskSetting();
    }
    //查找用户动力币的奖励规则
    @Override
    public YlqTask selectTask(int type) {
        return signDao.selectTask(type);
    }
    /*查找用户的任务明细*/
    @Override
    public YlqUserTag selectUserTag(String userId) {
        return signDao.selectUserTag(userId);
    }
    //用户任务明细
    @Override
    public List<YlqUserStep> selectUserStep(String userId) {
        return signDao.selectUserStep(userId);
    }
    //随机一张图片
    @Override
    public String selectClockUrl() {
        return signDao.selectClockUrl();
    }
    //随机一段文字
    @Override
    public String selectClockWord() {
        return signDao.selectClockWord();
    }

}
