package com.springboot.fun.controller;


import com.alibaba.druid.sql.ast.statement.SQLIfStatement;
import com.springboot.fun.dao.ShopGoodDao;
import com.springboot.fun.dao.SignDao;
import com.springboot.fun.entity.*;
import com.springboot.fun.service.AppletLoginService;
import com.springboot.fun.service.SignService;
import com.springboot.fun.service.SysConfigService;
import com.springboot.fun.util.JsonResult;
import com.springboot.fun.util.UuidUtils;
import com.springboot.fun.util.sign.AESUtil;
import com.springboot.fun.util.sign.StepInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.springboot.fun.util.utils.StringUtils.isNotEmpty;

@CrossOrigin(origins = "*",maxAge = 3600)
@Controller
@RequestMapping(value = "/sign")
public class SignController {

    @Autowired
    private SignService signService;
    @Autowired
    private SysConfigService sysConfigService;
    @Autowired
    private AppletLoginService appletLoginService;

    /*
     * 小程序端
     *
     * */
    //今日剩余可兑换步数
    @RequestMapping(value = "/todayStepNumber.fun")
    @ResponseBody
    public Object todayStepNumber(String encryptedData, String iv, HttpServletRequest request) {
        String userId = request.getHeader("userId");//用户id
        System.out.println("encryptedData" + encryptedData);
        System.out.println("iv" + iv);
      /*  String encryptedData="+MR1ud0tVQ6sDiQZ/f0qvXDrAGFk/NSe3NjEuWY4EOK/GUYkdUgzaqmUIBd6bDYQnZYtsIfRr42PEWoGdxe7qa7sDfX+qYVL0mQmNxj51ycwVk26OnFw7vCHBVRmRgxK+6kQeYZ6QJA63fPMdmUjhgLr5stdNYt1fyPpmxAdkBVAdhiiV3IrL6kibk8IKTbsuqkeko32oHVBUMfII1Vo0+TZ1X7XdmyCm2YSzvXnXLxu7l8zb7vNIqDzp4z6Drov9D17stkg+q7QhHaiMGaRnRHEIZEoHUCPa2vo+E6CvzM7rjHxcE9UYUqf9HyO9t5T0EQ5itHH1zHxeb+3xuWuF1tXG/c1QeLcWwGl8BMLseuKtMCRaUyPSvZYt8Y+VljUSGOzsr234WpfXc5rhUIqN52NhCtUhcOrzyWYNnAOXR26bJjGjGvbXfSL9eVD8G53oIU7q4GuASVitJtIbVI5xRrEw+7p3Mfn8ogMdOpHA4rjrB0YzUmaA217eucS4LPcCUhXBNQ556qRTejbbQ8y6SeOcwBH/gUGlGPCsBks8MC1dh/PRz44RRmQ3DhgDPyCuTI+lbj9fFVrghxjK286f8seKBO7Pug5JLhEogSUXjmW9AJjo1+YSssj5Hcft5U4kx/I3AGu4siSgSP0irBoDGMbYinz/p+EhnvofqvARTQ1TZ5V8d0JZnelVqUcVLPdK5T1NOBppjjfvaUjUGL+yYBaGP2akixTiyjMoNOVALjV5OCOB/EBa1RBnq99P+NbRGPHZXtx9zJ/NlcHJ/o+NBx0+cVLHvpuw2JbTypnsuSLAfgNQQsssWbAHl2QiwiEixwfM8v4x8/XQAyWg/xlA9ucsr7JAz4IuoqhdFOMUmlWROy04vumuXYgfLsh3IWF46LVxEg9/xKiHFywzzM6+4sh8JTR0SgheQGXjtMoEAc+rE2q6jghhHTyHyYOJ2poT6DdkuR3kH8IZ+y1sGzMVByBoDGU5CtM8rGrdywxeMQhesAJOX2shBPe/S7AY2ogNfEPbEUvnmBprdjl1sYG8SxA9RZXjrlyEXYjJCu4Pp+hxXMxhboTFvL5NN0aA7gPy0MMdvIUDdpGhG/WaaVaC88ErZ/s2FcAoAF7ov54tixywXbdZMVld3fdgkMFnOyOE91rPIAcJV4FYhEIdhO61eSGAvcWwJobJ4zJeCkMiSoZ2M58O2HorHzgB8NEzpmJBKqq4zjMya2D+wR0xp5cboIOxpEQp5JEQNkj3AOcLa+M3Vg8uUTyI9LB4DA+BsaQsrWH9SxRw/yt1ikGYTtVJ/DMwUT8YN7EB5uFUrp77fPi0E88n3O0ak6qIzvb+hNWSRqqQExRJV7bkAIsTX4nU0ezWUMu7t41zAxyTNg/qcuO3gnSUkFKJFst+mfbqCoQB8fHm+VE3OHjcBhuOxTFcac09KvgrFYyF3uht4AVY54HQTB7plLRINhrT/SonvgGfWQGCQFse59la+1bYoxrMRaTviQanafOTlBDBo0hLBoD9gq0wMVh+rs0CX+C7NKOKLa09LIwuZ0ZWPdj9LJlhyZMDqVZlX15CNA6N9yBQ73BO2WcoAsyXwqiTs2CSjxmqDXxxeZKWvbYlRXlznAnqqRIZkitDouhd5Kj53KMWb7tLY0nFxIZLMXSFf3bV3bX";
        String iv="2DzWIkFmHhZvtxl+UbVRMw==";*/
        //根据用户id查到用户的openID
        String openId = signService.selectUserOpenId(userId);
        System.out.println("openId" + openId);
        //根据openID查到用户的Xcx3rdSessionEntity信息
        Xcx3rdSession xcx3rdSession = appletLoginService.selectXcx3rdSession(openId);
        String sessionkey = xcx3rdSession.getSessionkey();
        System.out.println("sessionkey" + sessionkey);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StepInfo stepInfo = AESUtil.decryptStepInfo(sessionkey, encryptedData, iv);
        System.out.println("打印");
        System.out.println("stepInfo" + stepInfo);
        //根据用户的id和今天的day，查询用户的步数数据，如果没有，则创建一条，如果有，则更新
        Map<String, Object> ylqUserStepMap = new HashMap<String, Object>();
        ylqUserStepMap.put("userId", userId);
        ylqUserStepMap.put("day", sdf.format(new Date()));
        System.out.println(sdf.format(new Date()));
        //查找用户步数情况
        YlqUserStep ylqUserStep = signService.selectYlqUserSteInf(ylqUserStepMap);
        int stepTotal = Integer.parseInt(stepInfo.getStepInfoList().get(stepInfo.getStepInfoList().size() - 1).getStep());
        System.out.println("step" + stepTotal);
        if (null != ylqUserStep) {
            ylqUserStep.setStepTotal(stepTotal);
            //修改步数
            signService.updateYlqUserSteInfById(ylqUserStep);

        } else {
            ylqUserStep = new YlqUserStep();
            String id = UuidUtils.creatUUID();//主键
            ylqUserStep.setId(id);
            ylqUserStep.setUserId(userId);
            ylqUserStep.setStepTotal(stepTotal);
            ylqUserStep.setStepConvert(0);
            ylqUserStep.setDay(new Date());
            ylqUserStep.setVersion(0);
            ylqUserStep.setStatus(0);
            ylqUserStep.setSortRank(0);
            signService.insertYlqUserSteInf(ylqUserStep);
        }
        /*ylqUserStep = signService.selectYlqUserSteInf(ylqUserStepMap);*/
        //查找每日可兑换最大步数
        int step_max = Integer.parseInt(sysConfigService.getValue("step_max"));
        System.out.println("step_max" + step_max);
        int canConvert = (ylqUserStep.getStepTotal() > step_max ? step_max : ylqUserStep.getStepTotal()) - ylqUserStep.getStepConvert();

        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("canConvert", canConvert);
        JSONObject userCoinState = JSONObject.fromObject(map);
        return new JsonResult(userCoinState);
    }

    //今日步数兑换成动力币
    @RequestMapping(value = "/stepConvertMoney.fun", method = RequestMethod.POST)
    @ResponseBody
    public Object insertUsedComment(HttpServletRequest request) {
        String userId = request.getHeader("userId");//用户id
        Map<String, Object> resultObj = signService.stepConvertMoney(userId);
        return new JsonResult(resultObj);
    }

    /* 每日可兑换最大步数*/
    @RequestMapping(value = "/stepConvertMax.fun")
    @ResponseBody
    public Object stepConvertMax() {
        String step_max = sysConfigService.getValue("step_max", "30000");
        return new JsonResult(step_max);
    }


    /*用户今日兑换步数*/
    @RequestMapping(value = "/todayConvertStep.fun")
    @ResponseBody
    public Object todayConvertStep(HttpServletRequest request) {
        String userId = request.getHeader("userId");//用户id
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Object> ylqUserStepMap = new HashMap<String, Object>();
        ylqUserStepMap.put("userId", userId);
        ylqUserStepMap.put("day", sdf.format(new Date()));
        YlqUserStep ylqUserStep = signService.selectYlqUserSteInf(ylqUserStepMap);
        Map<String, Object> resultObj = new HashMap();
        resultObj.put("todayConvertStep", ylqUserStep.getStepConvert());
        return new JsonResult(resultObj);
    }

    //签到
    @RequestMapping(value = "/userSign.fun", method = RequestMethod.POST)
    @ResponseBody
    public Object sign(HttpServletRequest request) {
        String userId = request.getHeader("userId");//用户id
   /* @RequestMapping(value ="/sign",method = RequestMethod.POST)
    @ApiOperation(value = "签到",response=Map.class, notes = "签到")
    public ServerResponse sign(@ApiIgnore @LoginUser WxUserEntity wxUserEntity) {*/
        //apiSignService.save(sign);
        SignEntity signEntity = signService.sign(userId);
        Map resultMap = new HashMap();
        resultMap.put("signCount", signEntity.getSignCount());
        return new JsonResult(resultMap);
    }

    //查找用户是否签到
    @RequestMapping(value = "/todayUserSign.fun")
    @ResponseBody
    public Object todayUserSign(HttpServletRequest request) {
        String userId = request.getHeader("userId");//用户id
        //查找用户签到信息
        SignEntity signEntity = signService.selectsignEntity(userId);
        Integer state = 0;
        if (signEntity != null && signEntity.getSignCount() != 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println(sdf.format(new Date()));
            System.out.println(signEntity.getUpdateTime());
            System.out.println(sdf.format(signEntity.getUpdateTime()));
            Date updateTime = signEntity.getUpdateTime();
            updateTime.setTime(updateTime.getTime() - 8 * 60 * 60 * 1000);//时差，减8小时
            if ((sdf.format(updateTime)).equals(sdf.format(new Date()))) {
                state = 1;//已经签到
                Map<Object, Object> map = new HashMap<Object, Object>();
                map.put("state", state);
                map.put("day", signEntity.getSignCount());
                JSONObject userCoinState = JSONObject.fromObject(map);
                return new JsonResult(userCoinState);
            } else {
                Date today = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String yesterday = simpleDateFormat.format(today);//获取昨天日期

                System.out.println(yesterday + "yesterday");
                if (sdf.format(signEntity.getUpdateTime()).equals(yesterday)) {
                    Map<Object, Object> map = new HashMap<Object, Object>();
                    map.put("state", state);
                    map.put("day", signEntity.getSignCount());
                    JSONObject userCoinState = JSONObject.fromObject(map);
                    return new JsonResult(userCoinState);

                } else {
                    Map<Object, Object> map = new HashMap<Object, Object>();
                    map.put("state", state);
                    map.put("day", 0);
                    JSONObject userCoinState = JSONObject.fromObject(map);
                    return new JsonResult(userCoinState);
                }
            }
        } else {
            System.out.println("从来没有签到过");
            Map<Object, Object> map = new HashMap<Object, Object>();
            map.put("state", 3);//从来没有签到
            JSONObject userCoinState = JSONObject.fromObject(map);
            return new JsonResult(userCoinState);
        }

    }

    //查找签到配置表
    @RequestMapping(value = "/signSetting.fun")
    @ResponseBody
    public Object signSetting() {
        List<SignSettingEntity> signSettingEntities = signService.selectSettingSign();
        return new JsonResult(signSettingEntities);
    }

    //任务规则配置
    @RequestMapping(value = "/taskSetting.fun")
    @ResponseBody
    public Object taskSetting() {
        List<YlqTask> taskSettings = signService.taskSetting();
        return new JsonResult(taskSettings);
    }

    //用户任务明细
    @RequestMapping(value = "/selectUserStep.fun")
    @ResponseBody
    public Object selectUserStep(HttpServletRequest request) {
        String userId = request.getHeader("userId");
        List<YlqUserStep> userStep = signService.selectUserStep(userId);
        return new JsonResult(userStep);
    }

    //打卡鸡汤
    @RequestMapping(value = "/selectClock.fun")
    @ResponseBody
    public Object selectClock(HttpServletRequest request) {
        String userId=request.getHeader("userId");
        //查到这个用户的信息
        WxUser wxUser=appletLoginService.finUsedUserInf(userId);
        String nickName=wxUser.getNickname();
        String userUrl=wxUser.getHeadimgurl();
        String schoolName=wxUser.getSchoolName();
        //随机一张图片
        String clockUrl = signService.selectClockUrl();
        //不为空
        while (!isNotEmpty(clockUrl)){
            clockUrl=  signService.selectClockUrl();
        }
        //随机一段文字
        String clockWord = signService.selectClockWord();
        Map<String, Object> listMap = new HashMap<String, Object>();
        listMap.put("clockUrl", clockUrl);
        listMap.put("clockWord", clockWord);
        listMap.put("schoolName", schoolName);
        listMap.put("nickName", nickName);
        listMap.put("userUrl", userUrl);
        net.sf.json.JSONObject clockListMap = net.sf.json.JSONObject.fromObject(listMap);
        return new JsonResult(clockListMap);
    }
}