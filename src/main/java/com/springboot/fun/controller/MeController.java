package com.springboot.fun.controller;

import com.github.pagehelper.PageInfo;
import com.springboot.fun.entity.*;
import com.springboot.fun.service.AppletLoginService;
import com.springboot.fun.service.MeService;
import com.springboot.fun.util.JsonResult;
import com.springboot.fun.util.UuidUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@CrossOrigin(origins = "*",maxAge = 3600)
@Controller
@RequestMapping(value = "/me")
public class MeController {
    @Autowired
    private AppletLoginService appletLoginService;
    @Autowired
    private MeService meService;

    /*用户地址管理*/
    @RequestMapping(value = "/insertUserAddress.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object insertUserAddress(@RequestBody WxUser wxUser, HttpServletRequest request) {
        String userId=request.getHeader("userId");
        String userName=wxUser.getUserName();//用户名
        String userPhone=wxUser.getUserPhone();
        String userAddress=wxUser.getUserAddress();
        WxUser wxUsers=new WxUser();
        wxUsers.setUserId(userId);
        wxUsers.setUserName(userName);
        wxUsers.setUserPhone(userPhone);
        wxUsers.setUserAddress(userAddress);
        Integer num = meService.insertUserAddress(wxUsers);
        return new JsonResult(num);
    }
    /*商务合作*/
    @RequestMapping(value = "/insertCooperation.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object insertCooperation(@RequestBody FunCooperation  funCooperation,HttpServletRequest request) {
        String userId=request.getHeader("userId");
        String userName=funCooperation.getUserName();
        String userPhone=funCooperation.getUserPhone();
        String type=funCooperation.getType();
        FunCooperation funCooperations=new FunCooperation();
        String id = UuidUtils.creatUUID();

        funCooperations.setId(id);
        funCooperations.setUserId(userId);
        funCooperations.setUserName(userName);
        funCooperations.setUserPhone(userPhone);
        funCooperations.setType(type);
        Integer num = meService.insertCooperation(funCooperations);
        return new JsonResult(num);
    }
    /*意见建议*/
    @RequestMapping(value = "/insertOpinion.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object insertOpinion(@RequestBody FunOpinion funOpinion,HttpServletRequest request) {
        String userId=request.getHeader("userId");
        String userOpinion=funOpinion.getUserOpinion();
        String userQQ=funOpinion.getUserQQ();
        String userPhone=funOpinion.getUserPhone();
        FunOpinion funOpinions=new FunOpinion();
        String id = UuidUtils.creatUUID();

        funOpinions.setId(id);
        funOpinions.setUserId(userId);
        funOpinions.setUserOpinion(userOpinion);
        funOpinions.setUserQQ(userQQ);
        funOpinions.setUserPhone(userPhone);
        Integer num = meService.insertOpinion(funOpinions);
        return new JsonResult(num);
    }
    /*获取formid*/
    @RequestMapping(value = "/insertFormId.fun" ,method = RequestMethod.POST)
    @ResponseBody
    public Object insertFormId(@RequestBody UserFormEntity userFormEntity,HttpServletRequest request) {
        String userId=request.getHeader("userId");
        UserFormEntity userFormEntitys=new UserFormEntity();
        userFormEntitys.setUserId(userId);
        userFormEntitys.setFormId(userFormEntity.getFormId());
        userFormEntitys.setFormIdInvalidTime(userFormEntity.getFormIdInvalidTime());
        userFormEntitys.setType(1);
        userFormEntitys.setStatus(0);
       Integer num= meService.insertFormId(userFormEntitys);
        return new JsonResult(num);
    }

    //修改用户的朋友圈配图
    @RequestMapping(value = "/updateUserMomentsUrl.fun", method = RequestMethod.POST)
    @ResponseBody
    public Object updateUserMomentsUrl(@RequestBody WxUser wxUser,HttpServletRequest request) {
        String id=request.getHeader("userId");
        WxUser wxUser1=new WxUser();
        wxUser1.setId(id);
        wxUser1.setMomentsUrl(wxUser.getMomentsUrl());
        Integer num = meService.updateUserMomentsUrl(wxUser1);
        return new JsonResult(num);
    }
    //返回会员配置信息
    @RequestMapping(value = "/selectMemberDeploy.fun")
    @ResponseBody
    public Object selectMemberDeploy() {
        FunMemberDeploy memberDeploy=meService.selectMemberDeploy();
        return new JsonResult(memberDeploy);
    }

    //点击兑换会员 查看是否够人数， 够兑换，并且把兑换的人数状态改变，用户表修改会员状态，加入兑换时间和截止时间
    @RequestMapping(value = "/exchangeMember.fun" ,method = RequestMethod.POST)
    @ResponseBody
    public Object exchangeMember(HttpServletRequest request) throws ParseException {
        String userId=request.getHeader("userId");
    int state=1;
        WxUser wxUser1 = appletLoginService.selectByUserId(userId);
        //查看用户邀请的人数
        Integer memberNum= wxUser1.getMemberNum();
        //查看会员的配置信息
        FunMemberDeploy memberDeploy=meService.selectMemberDeploy();
        Integer DeployMemberNumber=memberDeploy.getMemberNumber();
        Integer DeployMemberDay=memberDeploy.getMemberDay();
        //转换时间
        String str = "yyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        Date memberDeadlineTime = null;
        String memberDeadlineTimeString;
        if(memberNum>=DeployMemberNumber){
            //可以兑换
            //把这个用户的邀请人数兑换的置为已兑换
            Map<Object, Object> numMap = new HashMap<Object, Object>();
            numMap.put("DeployMemberNumber", DeployMemberNumber);
            numMap.put("userId", userId);
            meService.updateUserMemberNum(numMap);
            //用户修改为会员，修改兑换时间和截止时间

            //开始时间
            Date memberStartTimeDate = new Date();
            Calendar cat=Calendar.getInstance();
            cat.setTime(memberStartTimeDate);
            long memberStartTimeLong = cat.getTimeInMillis()+ 8 * 60 * 60 * 1000;
            String memberStartTimeString = sdf.format(memberStartTimeLong);
            Date memberStartTime = sdf.parse(memberStartTimeString);

            System.out.println(sdf.format(memberStartTime));
           /* Date memberStartTime = new Date();
            memberStartTime.setTime(memberStartTime.getTime() + 8 * 60 * 60 * 1000);*/

           /* memberDeadlineTime.setTime(memberDeadlineTime.getTime() + (DeployMemberDay*24+8) * 60 * 60 * 1000);*/
            //如果是年，跨年
            Date memberDeadlineTimeDate = new Date();//截止时间
            Calendar ca=Calendar.getInstance();
            ca.setTime(memberDeadlineTimeDate);
            ca.add(Calendar.DAY_OF_YEAR, +DeployMemberDay);//加会员的天数


            if(wxUser1.getMemberState()==1){//是会员(截止时间加会员时间)
                Calendar cam=Calendar.getInstance();
                Date   memberDeadlineTimeDateMe =wxUser1.getMemberDeadlineTime();
                cam.setTime(memberDeadlineTimeDateMe);
                cam.add(Calendar.DAY_OF_YEAR, +DeployMemberDay);//加会员的天数
                long memberDeadlineTimeLong = cam.getTimeInMillis();//获取时间会有时间差，抵消掉
                 memberDeadlineTimeString = sdf.format(memberDeadlineTimeLong);
                memberDeadlineTime = sdf.parse(memberDeadlineTimeString);
                System.out.println("是会员"+memberDeadlineTime);
            }else{//不是会员
                long memberDeadlineTimeLong = ca.getTimeInMillis()+ 8 * 60 * 60 * 1000;
                 memberDeadlineTimeString = sdf.format(memberDeadlineTimeLong);
                 memberDeadlineTime = sdf.parse(memberDeadlineTimeString);
            }
/*
            System.out.println("推移后的 年份：" + ca.get(Calendar.YEAR) + " 月份：" + (ca.get(Calendar.MONTH) + 1) + " 日期：" + ca.get(Calendar.DATE));
*/
            WxUser wxUser=new WxUser();
            wxUser.setId(userId);
            wxUser.setMemberNum(1);
            wxUser.setMemberStartTime(memberStartTime);
            wxUser.setMemberDeadlineTime(memberDeadlineTime);
            meService.updateWxuser(wxUser);//修改用户的状态
        }else{
            System.out.println("人数不够");
            //查看用户邀请的人数
            WxUser wxUser2 = appletLoginService.selectByUserId(userId);
            Integer memberNum2= wxUser2.getMemberNum();
            //截止时间
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            wxUser2.getMemberDeadlineTime().setTime(wxUser2.getMemberDeadlineTime().getTime() - 8 * 60 * 60 * 1000);//时差，减8小时
            String memberDeadlineTime3 = simpleDateFormat.format(wxUser2.getMemberDeadlineTime());

            Map<Object, Object> map = new HashMap<Object, Object>();
            map.put("state", 2);//成功
            map.put("memberDeadlineTime", memberDeadlineTime3);//会员截止时间
            map.put("memberNum", memberNum2);//剩余可兑换人数
            JSONObject userCoinState = JSONObject.fromObject(map);
            return new JsonResult(userCoinState);
        }

        //查看用户邀请的人数
         WxUser wxUser2 = appletLoginService.selectByUserId(userId);
        Integer memberNum2= wxUser2.getMemberNum();
        //截止时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        wxUser2.getMemberDeadlineTime().setTime(wxUser2.getMemberDeadlineTime().getTime() - 8 * 60 * 60 * 1000);//时差，减8小时
        String memberDeadlineTime3 = simpleDateFormat.format(wxUser2.getMemberDeadlineTime());

        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("state", 1);//成功
       map.put("memberDeadlineTime", memberDeadlineTime3);//会员截止时间
        map.put("memberNum", memberNum2);//剩余可兑换人数
        JSONObject userCoinState = JSONObject.fromObject(map);
        return new JsonResult(userCoinState);
    }


    //查看用户的截止时间，结束后，修改为非会员（测试用）
    @RequestMapping(value = "/updateUserMemberState.fun", method = RequestMethod.POST)
    @ResponseBody
    public Object updateUserMemberState(HttpServletRequest request) {
        String userId=request.getHeader("userId");
        WxUser wxUser1 = appletLoginService.selectByUserId(userId);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        long newTime = c.getTimeInMillis();//当前时间
        Date memberDeadlineTime = wxUser1.getMemberDeadlineTime();
        memberDeadlineTime.setTime(memberDeadlineTime.getTime() - 8 * 60 * 60 * 1000);//时差，减8小时
        Calendar member = Calendar.getInstance();
        member.setTime(memberDeadlineTime);
        long memberTime = member.getTimeInMillis();//会员到期时间
        if(newTime>memberTime){
            System.out.println("会员到期");
        }else{
            System.out.println("是会员");
        }
        return new JsonResult(memberTime);
    }

    /*
    * 后台
    * */

    /*商务合作*/
        @RequestMapping(value = "/selectCooperation.fun")
        @ResponseBody
        public Object selectCooperation(int page, int pageSize,String schoolId, String search) {
            if (schoolId.equals("")){
                schoolId=null;
            }if (search.equals("")){
                search=null;
            }
            Map<String, Object> searchMap = new HashMap<String, Object>();
            searchMap.put("search",search);
            searchMap.put("schoolId",schoolId);
            /*page 第几页，pageSize 每页条数*/
            Map<String, Object> resultMap = new HashMap<String, Object>();
            PageInfo<FunCooperation> pager = meService.selectCooperation(page,pageSize,searchMap);

            //总条数
            resultMap.put("total", pager.getTotal());
            //获取每页数据
            resultMap.put("rows", pager.getList());


            return new JsonResult(resultMap);
        }
    /*意见建议*/
    @RequestMapping(value = "/selectOpinion.fun")
    @ResponseBody
    public Object selectOpinion(int page, int pageSize,String schoolId ,String search) {
        if (schoolId.equals("")){
            schoolId=null;
        } if (search.equals("")){
            search=null;
        }
        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("search",search);
        searchMap.put("schoolId",schoolId);
        /*page 第几页，pageSize 每页条数*/
        Map<String, Object> resultMap = new HashMap<String, Object>();
        PageInfo<FunOpinion> pager = meService.selectOpinion(page,pageSize,searchMap);

        //总条数
        resultMap.put("total", pager.getTotal());
        //获取每页数据
        resultMap.put("rows", pager.getList());

        return new JsonResult(resultMap);
    }


}
