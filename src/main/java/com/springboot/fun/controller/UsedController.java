package com.springboot.fun.controller;


import com.github.pagehelper.PageInfo;
import com.springboot.fun.entity.*;
import com.springboot.fun.service.AppletLoginService;
import com.springboot.fun.service.ShopSchoolService;
import com.springboot.fun.service.SignService;
import com.springboot.fun.service.UsedService;
import com.springboot.fun.util.JsonResult;
import com.springboot.fun.util.UuidUtils;
import com.springboot.fun.util.word.filtration;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*",maxAge = 3600)
@Controller
@RequestMapping(value = "/used")
public class UsedController {

    @Autowired
    private UsedService usedService;
    @Autowired
    private ShopSchoolService shopSchoolService;
    @Autowired
    private SignService signService;
    @Autowired
    private AppletLoginService appletLoginService;
    /*
    * 小程序端
    *
    * */
    //获取二手类型
    @RequestMapping(value = "/finUsedType.fun")
    @ResponseBody
    public Object finUsedType() {
        //获取二手类型
        List<UsedLabel> usedLabels=usedService.selectUsedLabelsType();
        System.out.println(usedLabels);
        return new JsonResult(usedLabels);
    }
    //获取二手标签
    @RequestMapping(value = "/finUsedLabel.fun")
    @ResponseBody
    public Object finUsedLabel() {
        //获取二手标签
        List<UsedLabel> usedLabels=usedService.selectUsedLabelsName();
        System.out.println(usedLabels);
        return new JsonResult(usedLabels);
    }
    //获取二手列表
    @RequestMapping(value = "/finUsedList.fun")
    @ResponseBody
    public Object finUsedList(int page, int pageSize, int type, HttpServletRequest request) {

        Used used=new Used();
        String usedId=used.getUsedUserId();
        String userId=request.getHeader("userId");
        System.out.println(userId+"88888999922");
        //获取学校id
        String schoolId=usedService.findSchoolIdByUserId(userId);
        /*page 第几页，pageSize 每页条数*/
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> usedMap = new HashMap<String, Object>();
        usedMap.put("userId",userId);
        usedMap.put("schoolId",schoolId);
        usedMap.put("type",type);
        PageInfo<Used> pager;
        //给刷新的浏览量+1
        int start=(page-1)*pageSize;
        int finish=page*pageSize;
        Map<String, Object> browseMap = new HashMap<String, Object>();
        browseMap.put("start",start);
        browseMap.put("finish",finish);
        if(type==8){
            //获取全部的二手（不分学校）
              pager = usedService.finAllUsedList(page, pageSize, usedMap);
            //给列表刷新的浏览量+1
            usedService.updateUsedComplainListAdd(browseMap);

        }else {
              pager = usedService.finUsedList(page, pageSize, usedMap);
            //给刷新的浏览量+1
            usedService.updateUsedComplainListAdd(browseMap);
        }
        //总条数
        resultMap.put("total", pager.getTotal());
        //获取每页数据
        resultMap.put("rows", pager.getList());

      /*  Map<String, Object> map = new HashMap<String, Object>();
        map.put("resultMap",resultMap);
        map.put("usedComment",usedComment);*/
        return new JsonResult(resultMap);
    }
    //用户点赞
    @RequestMapping(value = "/insertUsedLike.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object updateCommodityOrderSend(@RequestBody UsedLike usedLike,HttpServletRequest request) {
        String likeUserDoId=request.getHeader("userId");
        String id = UuidUtils.creatUUID();
        String likeUsedId=usedLike.getLikeUsedId();
        //根据主键id查询用户id
        String likeUserId=usedService.selectLikeUserId(likeUsedId);
        /*String likeUserDoId=usedLike.getLikeUserDoId();*/
        Map<String, Object> likeStateMap = new HashMap<String, Object>();
        likeStateMap.put("likeUsedId",likeUsedId);
        likeStateMap.put("likeUserDoId",likeUserDoId);
        //查询用户是否已经点过赞了
        Integer num=usedService.selectUsedLikeById(likeStateMap);
        Integer list = null;
        if(num==0) {
            list = usedService.insertUsedLike(id, likeUsedId, likeUserId, likeUserDoId);
            //给发布二手用户赞加一
            usedService.updateUsedLikeAdd(likeUsedId);
        }else{
            Map<String, Object> type = new HashMap<String, Object>();
            type.put("type",1);
            return new JsonResult(type);
        }
        return new JsonResult(list);
    }
    //二手发布详情 请求一次，给浏览量加一
    //根据商品id查看商品详情
    @RequestMapping(value = "/findUsedById.fun")
    @ResponseBody
    public Object findUsedById(String id,HttpServletRequest request) {
        String userId=request.getHeader("userId");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId",userId);
        map.put("id",id);
        //二手发布详情
        List<Used> usedList = usedService.findUsedById(map);
        //点赞列表
        List<UsedLike> likeList = usedService.findUsedLikeById(id);
        //评论列表
       /* List<UsedComment> commentList = usedService.findUsedCommentById(id);*/

        //给浏览量加一
        usedService.updateUsedComplainAdd(id);
        Map<String, Object> usedInfMap = new HashMap<String, Object>();
        usedInfMap.put("usedList",usedList);
        usedInfMap.put("likeList",likeList);
       /* usedInfMap.put("commentList",commentList);*/
        return new JsonResult(usedInfMap);
    }
    //获取用户发布所有评论(分页)
    @RequestMapping(value = "findUsedAllComment.fun")
    @ResponseBody
    public Object findUsedAllComment(int page,int pageSize,String id) {
        //评论列表
        Map<String, Object> resultMap = new HashMap<String, Object>();
        PageInfo<UsedComment> pager = usedService.findUsedAllComment(page,pageSize,id);
        //总条数
        resultMap.put("total", pager.getTotal());
        //获取每页数据
        resultMap.put("rows", pager.getList());
        return new JsonResult(resultMap);
    }
    //查看用户评论的详情(点击查看更多的评论)
    @RequestMapping(value = "/findUsedCommentById.fun")
    @ResponseBody
    public Object findUsedCommentById(String id) {
        //评论列表()评论主键id
        List<UsedCommentReply> commentList = usedService.findUsedCommentById(id);

        return new JsonResult(commentList);
    }
    //给用户评论
    @RequestMapping(value = "/insertUsedComment.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object insertUsedComment(@RequestBody UsedComment usedComment,HttpServletRequest request) {
        String commentUserId=request.getHeader("userId");//评论人id
        String id = UuidUtils.creatUUID();
        String commentUsedId=usedComment.getCommentUsedId();//二手主键id
        String commentContent=usedComment.getCommentContent();//评论内容
        //过滤敏感词
        Integer wordNum=  filtration.checkWords(commentContent);
        JSONObject userCoinState = null;
        if(wordNum==1){
            System.out.println("不能过");
            String usedSuccess="评论内容中含有敏感词";
            /* String usedSuccess="动态已发布，等待后台审核";*/
            Map<Object, Object> map = new HashMap<Object, Object>();
            map.put("state", 2);
            map.put("usedSuccess", usedSuccess);
            userCoinState = JSONObject.fromObject(map);
            return new JsonResult(userCoinState);
        }else if(wordNum==0) {
            System.out.println("能过");
            //查找用户信息
            WxUser wxUser1 = appletLoginService.selectByUserId(commentUserId);

            UsedComment usedComments = new UsedComment();
            usedComments.setId(id);
            usedComments.setCommentUsedId(commentUsedId);
            usedComments.setCommentUserId(commentUserId);
            usedComments.setCommentUserSex(wxUser1.getSex());
            usedComments.setCommentUserName(wxUser1.getNickname());
            usedComments.setCommentUserUrl(wxUser1.getHeadimgurl());
            usedComments.setCommentContent(commentContent);
            Integer list = usedService.insertUsedComment(usedComments);
            String usedSuccess = "评论成功";
            /* String usedSuccess="评论已发布，等待后台审核";*/
            Map<Object, Object> map = new HashMap<Object, Object>();
            map.put("state", list);
            map.put("usedSuccess", usedSuccess);
              userCoinState = JSONObject.fromObject(map);
        }
            return new JsonResult(userCoinState);
    }

    //给用户回复
    @RequestMapping(value = "/insertUsedCommentReply.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object insertUsedCommentReply(@RequestBody UsedCommentReply usedCommentReply,HttpServletRequest request) {
        String commentUserId=request.getHeader("userId");//评论人id
        String id = UuidUtils.creatUUID();
        String commentId=usedCommentReply.getCommentId();//评论主键id
        String replyId=usedCommentReply.getReplyId();//回复目标主键id
        String commentToUserIdReply=usedCommentReply.getCommentToUserIdReply();//目标用户id@的人
        String commentContentReply=usedCommentReply.getCommentContentReply();//评论内容
        //过滤敏感词
        Integer wordNum=  filtration.checkWords(commentContentReply);
        JSONObject userCoinState = null;
        if(wordNum==1){
            System.out.println("不能过");
            String usedSuccess="回复内容中含有敏感词";
            /* String usedSuccess="动态已发布，等待后台审核";*/
            Map<Object, Object> map = new HashMap<Object, Object>();
            map.put("state", 2);
            map.put("usedSuccess", usedSuccess);
            userCoinState = JSONObject.fromObject(map);
            return new JsonResult(userCoinState);
        }else if(wordNum==0) {
            System.out.println("能过");

            int replyType;
            Integer num;
            UsedCommentReply usedCommentReplys = new UsedCommentReply();
            if (commentId.equals(replyId)) {
                replyType = 1;
                //查找用户信息
                WxUser wxUser1 = appletLoginService.selectByUserId(commentUserId);

                usedCommentReplys.setrId(id);
                usedCommentReplys.setCommentId(commentId);
                usedCommentReplys.setReplyId(replyId);
                usedCommentReplys.setReplyType(1);
                usedCommentReplys.setCommentContentReply(commentContentReply);
                usedCommentReplys.setCommentUserIdReply(commentUserId);
                usedCommentReplys.setCommentUserNameReply(wxUser1.getNickname());
                usedCommentReplys.setCommentUserSexReply(wxUser1.getSex());
                usedCommentReplys.setCommentUserUrlReply(wxUser1.getHeadimgurl());
                num = usedService.insertUsedCommentRelpy(usedCommentReplys);
            } else {
                replyType = 2;
                //查找用户信息
                WxUser wxUser1s = appletLoginService.selectByUserId(commentUserId);
                WxUser wxUser1To = appletLoginService.selectByUserId(commentToUserIdReply);
                /* UsedCommentReply usedCommentReplyTo=new UsedCommentReply();*/
                usedCommentReplys.setrId(id);
                usedCommentReplys.setCommentId(commentId);
                usedCommentReplys.setReplyId(replyId);
                usedCommentReplys.setReplyType(2);
                usedCommentReplys.setCommentContentReply(commentContentReply);
                usedCommentReplys.setCommentUserIdReply(commentUserId);
                usedCommentReplys.setCommentUserNameReply(wxUser1s.getNickname());
                usedCommentReplys.setCommentUserSexReply(wxUser1s.getSex());
                usedCommentReplys.setCommentUserUrlReply(wxUser1s.getHeadimgurl());

                usedCommentReplys.setCommentToUserIdReply(commentToUserIdReply);
                usedCommentReplys.setCommentToUserNameReply(wxUser1To.getNickname());
                usedCommentReplys.setCommentToUserSexReply(wxUser1To.getSex());
                usedCommentReplys.setCommentToUserUrlReply(wxUser1To.getHeadimgurl());
                num = usedService.insertUsedCommentRelpy(usedCommentReplys);
            }
            String usedSuccess = "回复成功";
            /*String usedSuccess="回复已发布，等待后台审核";*/
            Map<Object, Object> map = new HashMap<Object, Object>();
            map.put("state", num);
            map.put("usedSuccess", usedSuccess);
              userCoinState = JSONObject.fromObject(map);
        }
        return new JsonResult(userCoinState);
    }
    /*
    * 返回发布需要的信息
    * */
    @RequestMapping(value = "/findUsedIssue.fun")
    @ResponseBody
    public Object findUsedIssue(HttpServletRequest request) {
        String usedUserId=request.getHeader("userId");
        //二手发布类型
        List<UsedLabel> usedLabelType=usedService.selectUsedLabelsType();
        //标签
        List<UsedLabel> usedLabels=usedService.selectUsedLabelsName();
        //校区
        String schoolName = usedService.selectSchoolName(usedUserId);

        Map<String, Object> usedIssueMap = new HashMap<String, Object>();
        usedIssueMap.put("usedLabelType",usedLabelType);
        usedIssueMap.put("usedLabels",usedLabels);
        usedIssueMap.put("schoolName",schoolName);
        return new JsonResult(usedIssueMap);
    }
    //发布二手
     @RequestMapping(value = "/insertUsed.fun" , method = RequestMethod.POST ,produces="application/json; utf-8")
    @ResponseBody
    public Object insertUsed(@RequestBody Used used, HttpServletRequest request) {
        String id = UuidUtils.creatUUID();
        String usedUserId=request.getHeader("userId");
         //获取学校id
         String schoolId=usedService.findSchoolIdByUserId(usedUserId);

        Integer usedType=used.getUsedType();
        String usedLable=used.getUsedLable();
        String usedName=used.getUsedName();
         //过滤敏感词
        Integer wordNum=  filtration.checkWords(usedName);
         JSONObject userCoinState = null;
         if(wordNum==1){
          System.out.println("不能过");
          String usedSuccess="发布内容中含有敏感词";
          /* String usedSuccess="动态已发布，等待后台审核";*/
          Map<Object, Object> map = new HashMap<Object, Object>();
          map.put("state", 2);
          map.put("usedSuccess", usedSuccess);
           userCoinState = JSONObject.fromObject(map);
          return new JsonResult(userCoinState);
      }else if(wordNum==0){
          System.out.println("能过");
      
        String usedUrl=used.getUsedUrl();
        String usedPrice=used.getUsedPrice();
        String phone=used.getPhone();
        Integer usedVideoState=used.getUsedVideoState();
         Integer usedPriceState = 0;
        if(usedType==200001 || usedType==200002){//价格打开状态
             usedPriceState=1;
        }
        Integer list = usedService.insertUsed(id,usedUserId,usedType,usedLable,usedName,usedUrl,usedPrice,phone,schoolId,usedPriceState,usedVideoState);
        /*查找用户的任务明细*/
         YlqUserTag ylqUserTag=signService.selectUserTag(usedUserId);
        if(ylqUserTag.getNewUsedTag()==0) {
            //动力币明细
            //查找用户动力币的奖励规则
            int type = 9;
            YlqTask ylqTask = signService.selectTask(type);
            //奖励运动比
            Map<String, Object> addCoinMap = new HashMap<String, Object>();
            addCoinMap.put("userId", usedUserId);
            addCoinMap.put("orderCoin", ylqTask.getMoney());
            appletLoginService.addCoinByUserId(addCoinMap);
            //查找用户动力币
            BigDecimal userCoin = shopSchoolService.selectUserCoin(usedUserId);//查找的奖励过的
            Map<String, Object> coinInfMap = new HashMap<String, Object>();
            /*BigDecimal balance=userCoin.add(BigDecimal.valueOf(5));*/
            String coinId = UuidUtils.creatUUID();//主键
            coinInfMap.put("id", coinId);
            coinInfMap.put("userId", usedUserId);
            coinInfMap.put("balance", userCoin);
            coinInfMap.put("state", 1);
            coinInfMap.put("orderCoin", ylqTask.getMoney());
            coinInfMap.put("type", type);//
            coinInfMap.put("remark", "首次发布二手奖励");
            Integer num = shopSchoolService.insertCoinInf(coinInfMap);
            System.out.println("222222");
            //改变用户的领取发布二手完成状态
            appletLoginService.updateTagUsed(usedUserId);
        }
        
        String usedSuccess="发布动态成功";
        /* String usedSuccess="动态已发布，等待后台审核";*/
         Map<Object, Object> map = new HashMap<Object, Object>();
         map.put("state", list);
         map.put("usedSuccess", usedSuccess);
         userCoinState = JSONObject.fromObject(map);
      }
        return new JsonResult(userCoinState);
     
     }
    //个人中心 我的发布
    @RequestMapping(value = "/findMyUsedList.fun")
    @ResponseBody
    public Object findMyUsedList(int page, int pageSize, HttpServletRequest request) {
        String userId=request.getHeader("userId");
        System.out.println(userId+"88888999922");
        //获取学校id
        String schoolId=usedService.findSchoolIdByUserId(userId);
            /*page 第几页，pageSize 每页条数*/
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> usedMap = new HashMap<String, Object>();
        usedMap.put("userId",userId);
        usedMap.put("schoolId",schoolId);
        PageInfo<Used> pager = usedService.findAllSuccessUserList(page,pageSize,usedMap);
        //总条数
        resultMap.put("total", pager.getTotal());
        //获取每页数据
        resultMap.put("rows", pager.getList());
        return new JsonResult(resultMap);
    }
    /*删除评论*/
    @RequestMapping(value = "/deleteUsedComment.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object insertUsed(@RequestBody CommentDelete commentDelete) {
        String commentId = commentDelete.getCommentId();//评论主键id
        String commentReply = commentDelete.getCommentReply();//回复主键id
        Integer state = commentDelete.getState();//点击的那一块
        Integer num;
        if(state==1){
            //删除评论
             num=usedService.deleteComment(commentId);
        }else{
            //删除回复
            num=usedService.deleteCommentReply(commentReply);
        }
        return new JsonResult(num);
    }
    //下架
    @RequestMapping(value = "/outUsed.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object outUsed(@RequestBody Used used) {
        String id =used.getId();
        //下架
        Integer num=  usedService.outUsed(id);
        return new JsonResult(num);
    }
    //上架
    @RequestMapping(value = "/upUsed.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object upUsed(@RequestBody Used used) {
        String id =used.getId();
        //下架
        Integer num=  usedService.upUsed(id);
        return new JsonResult(num);
    }
    //投诉
    @RequestMapping(value = "/addComplain.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object addComplain(@RequestBody Used used) {
        String id =used.getId();
            //投诉加一
          Integer num=  usedService.addComplain(id);
        return new JsonResult(num);
    }
    //删除
    @RequestMapping(value = "/deleteUsed.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object deleteUsed(@RequestBody Used used) {
        String id =used.getId();
        //删除
        Integer num=  usedService.deleteUsed(id);
        return new JsonResult(num);
    }
    //获取发布和评论的返回内容
    @RequestMapping(value = "/findUsedState.fun")
    @ResponseBody
    public Object findUsedState(int type) {
        List<FunUsedState> usedState=usedService.findUsedState(type);
        System.out.println(usedState);
        return new JsonResult(usedState);
    }

   //找到用户发布相关的评论和回复相关的数据（状态为0的）的和
   @RequestMapping(value = "/selectCommentReplySum.fun")
   @ResponseBody
   public Object selectCommentReplySum(HttpServletRequest request) {
       String userId=request.getHeader("userId");

       //评论未看的
       Integer commentSum = usedService.selectCommentSum(userId);
        //回复未看的
       Integer replaySum = usedService.selectReplaySum(userId);
       System.out.println(commentSum+replaySum);
       Map<String, Object> messageMap = new HashMap<String, Object>();
       messageMap.put("messageSum",commentSum+replaySum);
       return new JsonResult(messageMap);
   }
    //点击展示未读内容的消息列表，并把次消息的状态改为已读
    @RequestMapping(value = "/selectCommentReplyList.fun")
    @ResponseBody
    public Object selectCommentReplyList(int page, int pageSize, HttpServletRequest request) {
        String userId=request.getHeader("userId");
        /*page 第几页，pageSize 每页条数*/
        Map<String, Object> resultMap = new HashMap<String, Object>();
        PageInfo<UsedCommentReplyMessage> pager = usedService.selectCommentReplyList(page,pageSize,userId);
        //把评论消息改为已读
          usedService.updateCommentMessage(userId);
        //把回复消息改为已读
          usedService.updateReplayMessage(userId);
        //总条数
        resultMap.put("total", pager.getTotal());
        //获取每页数据
        resultMap.put("rows", pager.getList());
        return new JsonResult(resultMap);
    }


    /*后台*/
    //二手筛选列表
    @RequestMapping(value = "/searchUsedList.fun")
    @ResponseBody
    public Object searchUsedList(int page, int pageSize,String schoolId , String type, String nickName ,
                                  String content,String  startTime,String endTime ) {

        if (schoolId.equals("")){
            schoolId=null;
        }if (type.equals("")){
            type=null;
        }if (nickName.equals("")){
            nickName=null;
        }if (content.equals("")){
            content=null;
        }if (startTime.equals("")){
            startTime=null;
        }if (endTime.equals("")){
            endTime=null;
        }

        /*page 第几页，pageSize 每页条数*/
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> usedMap = new HashMap<String, Object>();
        usedMap.put("schoolId",schoolId);
        usedMap.put("type",type);
        usedMap.put("nickName",nickName);
        usedMap.put("content",content);
        usedMap.put("startTime",startTime);
        usedMap.put("endTime",endTime);
        PageInfo<Used>   pager = usedService.searchUsedList(page, pageSize, usedMap);
        //总条数
        resultMap.put("total", pager.getTotal());
        //获取每页数据
        resultMap.put("rows", pager.getList());
        return new JsonResult(resultMap);
    }

    //二手详情
    @RequestMapping(value = "/searchUsedListById.fun")
    @ResponseBody
    public Object searchUsedListById(String id) {

        Used  used = usedService.searchUsedListById(id);
        return new JsonResult(used);
    }
    //修改二手类型
    @RequestMapping(value = "/updateUsedType.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object updateUsedType(@RequestBody UsedLabel usedLabel) {
        UsedLabel usedLabels=new UsedLabel();
        usedLabels.setId(usedLabel.getId());
        usedLabels.setLabelName(usedLabel.getLabelName());
        usedLabels.setLabelPriceState(usedLabel.getLabelPriceState());
        Integer num=usedService.updateUsedType(usedLabels);
        return new JsonResult(num);
    }

    //添加二手类型
    @RequestMapping(value = "/insertUsedType.fun", method = RequestMethod.POST)
    @ResponseBody
    public Object insertCommodityType(@RequestBody UsedLabel usedLabel) {
        String labelName = usedLabel.getLabelName();
        Integer labelPriceState=usedLabel.getLabelPriceState();
        //查找类型编号的最大值
        Integer labelNum= usedService.selectCommodityType();
        if(labelNum!=null){
            labelNum=labelNum+1;
        }else{
            labelNum=200001;
        }
        UsedLabel usedLabels=new UsedLabel();
        String id=UuidUtils.creatUUID();
        usedLabels.setId(id);
        usedLabels.setLabelNum(labelNum);
        usedLabels.setLabelName(labelName);
        usedLabels.setLabelPriceState(labelPriceState);
        Integer num = usedService.insertUsedType(usedLabels);
        return new JsonResult(num);
    }

    //上架二手类型
    @RequestMapping(value = "/upUsedType.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object upUsedType(@RequestBody UsedLabel usedLabel) {
        String id =usedLabel.getId();
        //上架类型
        Integer num=  usedService.upUsedType(id);
        return new JsonResult(num);
    }
    //下架二手类型
    @RequestMapping(value = "/outUsedType.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object outUsedType(@RequestBody UsedLabel usedLabel) {
        String id =usedLabel.getId();
        //下架类型
        Integer num=  usedService.outUsedType(id);
        return new JsonResult(num);
    }
    //置顶
    @RequestMapping(value = "/stickUsed.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object stickUsed(@RequestBody Used  used ) {
        String id =used.getId();
        //置顶
        Integer num=  usedService.stickUsed(id);
        return new JsonResult(num);
    }
    //取消置顶
    @RequestMapping(value = "/cancelStickUsed.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object cancelStickUsed(@RequestBody Used  used ) {
        String id =used.getId();
        //取消置顶
        Integer num=  usedService.cancelStickUsed(id);
        return new JsonResult(num);
    }
    //获取二手类型，后台端
    @RequestMapping(value = "/findUsedTypeList.fun")
    @ResponseBody
    public Object findUsedTypeList() {
        //获取二手类型
        List<UsedLabel> usedLabels=usedService.findUsedTypeList();
        System.out.println(usedLabels);
        return new JsonResult(usedLabels);
    }
}
