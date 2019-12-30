package com.springboot.fun.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.springboot.fun.dao.SignDao;
import com.springboot.fun.entity.*;
import com.springboot.fun.service.*;
import com.springboot.fun.util.JsonResult;
import com.springboot.fun.util.RandomNumber;
import com.springboot.fun.util.UuidUtils;
import com.springboot.fun.util.applet.UserConstantInterface;
import com.springboot.fun.util.http.HttpClientUtil;
import com.springboot.fun.util.http.WeChatLogin;
import com.springboot.fun.util.jiguang.regJiguang;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static com.springboot.fun.util.utils.StringUtils.isNotEmpty;

/*
*
* 小程序登录
* 极光注册
*
* */
@CrossOrigin(origins = "*",maxAge = 3600)
@Controller
@RequestMapping(value = "/applet/login")
public class AppletLoginController {


//微信登录
    @Autowired
    private AppletLoginService appletLoginService;
    @Autowired
    private ShopSchoolService shopSchoolService;
    @Autowired
    private UsedService usedService;
    @Autowired
    private SignService signService;
    @Autowired
    private SignDao signDao;
    @Autowired
    private MeService meService;
    @PostMapping("/codeLogin.fun")
    @ResponseBody
    public Object codeLogin(@RequestBody WxUser wxUser) {
        String code=wxUser.getCode();
        String shareId=wxUser.getShareId();

        // 配置请求参数
        Map<String, String> param = new HashMap<>();
        param.put("appid", UserConstantInterface.WX_LOGIN_APPID);
        param.put("secret", UserConstantInterface.WX_LOGIN_SECRET);
        param.put("js_code", code);
        param.put("grant_type", UserConstantInterface.WX_LOGIN_GRANT_TYPE);
        // 发送请求
        String wxResult = HttpClientUtil.doGet(UserConstantInterface.WX_LOGIN_URL, param);
        System.out.println("888888888888888888"+wxResult);
        JSONObject jsonObject = JSONObject.parseObject(wxResult);
        // 获取参数返回的
        String sessionkey = jsonObject.get("session_key").toString();
        String openId = jsonObject.get("openid").toString();
        System.out.println(sessionkey+"8888"+openId);
        String unionId=null;

        //查看用户在 xcx_3rd_session有没有值，有的话加，没有的话更新
        Xcx3rdSession xcx3rdSession = appletLoginService.selectXcx3rdSession(openId);
        if (xcx3rdSession != null) {
            xcx3rdSession.setSessionkey(sessionkey);
            xcx3rdSession.setCode(code);
            appletLoginService.updateXcx3rdSessionById(xcx3rdSession);
        }else{
            String sKey = RandomStringUtils.randomAlphanumeric(64);
            Xcx3rdSession rdSession = new Xcx3rdSession();
           String id = UuidUtils.creatUUID();
            rdSession.setId(id);
            rdSession.setSkey(sKey);
            rdSession.setSessionkey(sessionkey);
            rdSession.setUnionid(unionId);
            rdSession.setOpenid(openId);
            rdSession.setCode(code);
            appletLoginService.insertXcx3rdSession(rdSession);
        }


        // 根据返回的user实体类，判断用户是否是新用户，不是的话，更新最新登录时间，是的话，将用户信息存到数据库
        WxUser wxUser1 = appletLoginService.selectByOpenId(openId);
        String wxNickName=wxUser.getNickname();
        String id="";
        if(wxUser1 != null){
            wxUser1.setCreateTime(new Date());
            //查看用户有没有签到，没有的话生成
            //查看签到表
            //生成用户签到表单
            SignEntity signEntity = signService.selectsignEntity(wxUser1.getId());//查找有没有签到表单

            if (signEntity != null) {
                System.out.println("已经有签到表单了");
            }
            else {
                signEntity = new SignEntity();
                String signid = UuidUtils.creatUUID();//主键
                signEntity.setId(signid);
                signEntity.setUserId(wxUser1.getId());
                signEntity.setSignCount(0);
                signEntity.setSignHistory(1L);
                signEntity.setStatus(1);
           /* signEntity.setCreateTime(new Date());
            signEntity.setUpdateTime(new Date());*/
                //第一次签到，插入数据
                signDao.insertSignEntity(signEntity);
            }


            WxUser wxUser2 = appletLoginService.selectByUserId(wxUser1.getId());
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            long newTime = c.getTimeInMillis();//当前时间
            Date memberDeadlineTime = wxUser2.getMemberDeadlineTime();
            if(memberDeadlineTime!=null) {  //时间不为空
            memberDeadlineTime.setTime(memberDeadlineTime.getTime() - 8 * 60 * 60 * 1000);//时差，减8小时
            Calendar member = Calendar.getInstance();
            member.setTime(memberDeadlineTime);
            long memberTime = member.getTimeInMillis();//会员到期时间
            System.out.println("memberTime"+memberTime);

                if (newTime > memberTime) {
                    meService.updateWxuserMemberState(wxUser1.getId());//修改用户的会员状态为不是会员
                    System.out.println("会员到期");
                } else {
                    System.out.println("是会员");
                }
            }
        }else{
            //生成不会重复的6位自增码
           /* String userId=RandomNumber.random(6);*/
            String userId="524855";
            System.out.println("userId"+userId);
            //查找用户id有没有重复，有，就再次生成
            String userIdNum = appletLoginService.findUserId(userId);
            System.out.println("userIdNum"+userIdNum);
            while (userIdNum != null){
                userId=RandomNumber.random(6);
                System.out.println("userId6666"+userId);
                userIdNum = appletLoginService.findUserId(userId);

                System.out.println("userIdNum6666"+userIdNum);
            }
            WxUser insertWxUser = new WxUser();
             id = UuidUtils.creatUUID();
            insertWxUser.setId(id);
            insertWxUser.setUserId(userId);
            insertWxUser.setCreateTime(new Date());
            insertWxUser.setOpenid(openId);
            // 添加到数据库
            Boolean flag = appletLoginService.insertMember(insertWxUser);
            System.out.println("9999999");
                //创建用户钱包
                YlqWallet ylqWallet = new YlqWallet();
                ylqWallet.setId(UuidUtils.creatUUID());
                ylqWallet.setUserId(id);
                ylqWallet.setBalance(BigDecimal.valueOf(0));
                ylqWallet.setTotalMoney(BigDecimal.valueOf(0));
                appletLoginService.insertWallet(ylqWallet);
                //用户总量加一
                appletLoginService.addNoticeUserNum();

            //生成用户任务表单
            YlqUserTag ylqUserTag=appletLoginService.selectylqUserTag(id);//查询有没有任务表
            if (ylqUserTag != null) {
                System.out.println("已经有任务表了");
            }else {
                ylqUserTag= new YlqUserTag();
                String tagId = UuidUtils.creatUUID();//主键
                ylqUserTag.setId(tagId);
                ylqUserTag.setUserId(id);
                appletLoginService.insertUserTag(ylqUserTag);
            }
            //生成用户签到表单
            SignEntity signEntity = signService.selectsignEntity(id);//查找有没有签到表单

            if (signEntity != null) {
                System.out.println("已经有签到表单了");
            }
            else {
                signEntity = new SignEntity();
                String signid = UuidUtils.creatUUID();//主键
                signEntity.setId(signid);
                signEntity.setUserId(id);
                signEntity.setSignCount(0);
                signEntity.setSignHistory(1L);
                signEntity.setStatus(1);
           /* signEntity.setCreateTime(new Date());
            signEntity.setUpdateTime(new Date());*/
                //第一次签到，插入数据
                signDao.insertSignEntity(signEntity);
            }

            //添加到好友列表
            String uUid = UuidUtils.creatUUID();
            appletLoginService.insertShareMember(uUid,id,shareId);
            System.out.println("_________________添加好友了");
            //查找用户动力币的奖励规则
            int type=8;
            YlqTask ylqTask=signService.selectTask(type);
            //奖励运动比
            Map<String, Object> addCoinMap = new HashMap<String, Object>();
            addCoinMap.put("userId", shareId);
            addCoinMap.put("orderCoin", ylqTask.getMoney());
            appletLoginService.addCoinByUserId(addCoinMap);
            //动力币明细
            //查找用户动力币
            BigDecimal userCoin=shopSchoolService.selectUserCoin(shareId);//查找的奖励过的
            Map<String, Object> coinInfMap = new HashMap<String, Object>();
            /*BigDecimal balance=userCoin.add(BigDecimal.valueOf(5));*/
            String coinId = UuidUtils.creatUUID();//主键
            coinInfMap.put("id", coinId);
            coinInfMap.put("userId", shareId);
            coinInfMap.put("balance", userCoin);
            coinInfMap.put("state", 1);
            coinInfMap.put("orderCoin", ylqTask.getMoney());
            coinInfMap.put("type",type);//
            coinInfMap.put("remark", "完成邀请新用户任务奖励");
                shopSchoolService.insertCoinInf(coinInfMap);


            if(!flag){
                return new JsonResult("失败");
            }
        }
        WxUser result = appletLoginService.selectByOpenId(openId);
        // 封装返回小程序
        /*Map<String, String> result = new HashMap<>();
        result.put("id", id);
        result.put("open_id", openId);
        result.put("wxNickName", wxNickName);*/
        return new JsonResult(result);

    }

    //用户授权
  @PostMapping("/memberInf.fun")
 /* @RequestMapping(value = "/memberInf.fun", method = RequestMethod.POST)*/
   /* public JsonResult user_login(
            @RequestParam("code") String code,
            @RequestParam("userHead") String userHead,
            @RequestParam("userName") String userName,
            @RequestParam("userGender") Integer userGender,
            @RequestParam("userCity") String userCity,
            @RequestParam("userProvince") String userProvince
    ){*/

  @ResponseBody
  public Object insertCommodityType(@RequestBody WxUser wxUser, HttpServletRequest request) {
      System.out.println("金我这边来了");
      String id=request.getHeader("userId");
      String headimgurl=wxUser.getHeadimgurl();
      String nickname=wxUser.getNickname();
      Integer sex=wxUser.getSex();
      String country=wxUser.getCountry();
      String city=wxUser.getCity();
      String province=wxUser.getProvince();

        /*根据用户id查看用户信息*/
        WxUser wxUser1 = appletLoginService.selectByUserId(id);
        String openid=wxUser1.getOpenid();
                //极光注册
                regJiguang.regPP1(id,id,nickname,headimgurl);
            WxUser insertWxUser = new WxUser();
            insertWxUser.setId(id);
            insertWxUser.setHeadimgurl(headimgurl);
            insertWxUser.setNickname(nickname);
            insertWxUser.setSex(sex);
            insertWxUser.setCreateTime(new Date());
            insertWxUser.setCountry(country);
            insertWxUser.setCity(city);
            insertWxUser.setProvince(province);
            insertWxUser.setOpenid(openid);
            System.out.println("insert_user:"+insertWxUser.toString());
            //修改用户极光状态和是否授权
            appletLoginService.updateUserJiGuangState(id);
            // 更新到数据库
            Boolean flag = appletLoginService.updateMember(insertWxUser);
            if(!flag){
                return new JsonResult("失败");
            }
        // 封装返回小程序
        /*Map<String, String> result = new HashMap<>();
        result.put("id", id);
        result.put("headimgurl", headimgurl);
        result.put("nickname", nickname);
        result.put("sex", String.valueOf(sex));
        result.put("country", country);
        result.put("city", city);
        result.put("province", province);
        result.put("open_id", openid);*/
        WxUser result = appletLoginService.selectByUserId(id);
        return new JsonResult(result);

    }

    //老用户极光注册
    @PostMapping("/insertJiGuang.fun")
    @ResponseBody
    public Object insertCommodityType(HttpServletRequest request) {
        String id=request.getHeader("userId");
        WxUser wxUser = appletLoginService.selectByUserId(id);
        String headimgurl=wxUser.getHeadimgurl();
        String nickname=wxUser.getNickname();
        //极光注册
        regJiguang.regPP1(id,id,nickname,headimgurl);
        //修改用户极光状态
        appletLoginService.updateUserJiGuangState(id);
        return new JsonResult(wxUser);

    }
    //查找整个学校的信息
    @RequestMapping(value = "/findAllSchool.fun")
    @ResponseBody
    public Object findAllSchool() {
        List<SchoolEntity> schoolEntityList = appletLoginService.findAllSchool();

        return new JsonResult(schoolEntityList);
    }
    //根据学校的名字搜索学校
    @RequestMapping(value = "/findSchoolByName.fun")
    @ResponseBody
    public Object findSchoolByName(String  name) {
        List schoolName = appletLoginService.findSchoolByName(name);

        return new JsonResult(schoolName);
    }
    //用户切换校区
    @RequestMapping(value = "/choseSchool.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object choseSchool(@RequestBody SchoolEntity schoolEntity, HttpServletRequest request) {
        String userId=request.getHeader("userId");
        String id=schoolEntity.getId();
        System.out.println("schoolId"+id);
        //查找这个学校的信息
        SchoolEntity schoolInf = appletLoginService.selectSchoolById(id);
        System.out.println(schoolInf.getName()+"FF"+schoolInf.getNumber());
        //更新用户信息
        Map<String, String> userInf = new HashMap<>();
        userInf.put("userId",userId);
        userInf.put("schoolName",schoolInf.getName());
        userInf.put("schoolId",id);
     Integer num=   appletLoginService.updateWxUserById(userInf);
        return new JsonResult(num);
    }
    //查找用户的动力币明细
    @RequestMapping(value = "/findUserWalletDetail.fun")
    @ResponseBody
    public Object findUserWalletDetail(int page, int pageSize,HttpServletRequest request) {
        String userId=request.getHeader("userId");
        /*page 第几页，pageSize 每页条数*/
        Map<String, Object> resultMap = new HashMap<String, Object>();
        PageInfo<YlqWalletDetail> pager = appletLoginService.findUserWalletDetail(page,pageSize,userId);
        //总条数
        resultMap.put("total", pager.getTotal());
        //获取每页数据
        resultMap.put("rows", pager.getList());
        return new JsonResult(resultMap);
    }
    //查看校友排行榜
    @RequestMapping(value = "/walletRankingList.fun")
    @ResponseBody
        public Object walletRankingList(int page, int pageSize,HttpServletRequest request) {
            String userId=request.getHeader("userId");
            String schoolId=usedService.findSchoolIdByUserId(userId);
        /*查看前100名本周的排行榜*/
        Map<Object, Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("schoolId",schoolId);
        PageInfo<YlqUserStep> pager=appletLoginService.selectWalletRankingList(page,pageSize,map);
            /*page 第几页，pageSize 每页条数*/
            Map<String, Object> resultMap = new HashMap<String, Object>();
            //总条数
            resultMap.put("total", pager.getTotal());
            //获取每页数据
            resultMap.put("rows", pager.getList());
            return new JsonResult(resultMap);
        }

    //获取历史消息
    @RequestMapping(value = "/findUserMessage.fun")
    @ResponseBody
    public Object findAllCommodityType(String userName,Integer count,String beginTime,String endTime) throws ClientProtocolException, IOException {
        String result = null;
        //格式化时间的空格
        beginTime= URLEncoder.encode(beginTime,"UTF-8");
        endTime= URLEncoder.encode(endTime,"UTF-8");
        //认证信息对象，用于包含访问翻译服务的用户名和密码
       /* String path ="https://report.im.jpush.cn/v2/users/li123/messages?count=100&begin_time=2019-10-19%2008:10:10&end_time=2019-10-25%2013:10:12";*/
        String path ="https://report.im.jpush.cn/v2/users/"+userName+"/messages?count="+count+"&begin_time="+beginTime+"&end_time="+endTime;
        System.out.println(path);
//1.创建客户端访问服务器的httpclient对象 打开浏览器
        HttpClient httpclient = new DefaultHttpClient();
//2.以请求的连接地址创建get请求对象
        HttpGet httpget = new HttpGet(path);
//appKey:masterSecret—>appKey，masterSecret,并使用base64进行加密，将加密的字节信息转化为string类型，encoding—>token
        String encoding = DatatypeConverter.printBase64Binary("048000c77e38b1ce6272c9df:d1e27d58eea80e5f0145b8b6".getBytes("UTF-8"));
        httpget.setHeader("Authorization", "Basic "+encoding);
//3.向服务器端发送请求 并且获取响应对象 浏览器中输入网址点击回车
        HttpResponse response = httpclient.execute(httpget);
//4.获取响应对象中的响应码
        StatusLine statusLine = response.getStatusLine();//获取请求对象中的响应行对象
        int responseCode = statusLine.getStatusCode();//从状态行中获取状态码
        System.out.println(responseCode);
        if (responseCode == 200) {
//5. 可以接收和发送消息
            HttpEntity entity = response.getEntity();
//6.从消息载体对象中获取操作的读取流对象
            InputStream input = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            String str1 = br.readLine();
             result = new String(str1.getBytes("utf-8"), "utf-8");
            System.out.println("服务器的响应是:" + result);
            br.close();
            input.close();
        } else {
            System.out.println("响应失败!");

            return new JsonResult(0);
        }
        return result;
    }

    //获取用户信息
    @RequestMapping(value = "/finUsedUserInf.fun")
    @ResponseBody
    public Object finUsedUserInf(HttpServletRequest request) {
        String id=request.getHeader("userId");
        WxUser wxUser=appletLoginService.finUsedUserInf(id);
        //给用户的浏览量加一
        appletLoginService.addNoticeBrowseNum();
        return new JsonResult(wxUser);
    }
    //查找用户信息
    @RequestMapping(value = "/findUser.fun")
    @ResponseBody
    public Object finUsedUserInf(String userId) {
        WxUser wxUser=appletLoginService.finUsedUserInf(userId);
        return new JsonResult(wxUser);
    }
    //获取公告信息
    @RequestMapping(value = "/finNoticeInf.fun")
    @ResponseBody
    public Object finNoticeInf() {

       List<FunNotice> funNotices=appletLoginService.finNoticeInf();
        return new JsonResult(funNotices);
    }

    //首页轮播图展示
    @RequestMapping(value = "/findHomeUrl.fun")
    @ResponseBody
    public Object findHomeUrl(HttpServletRequest request) {
        String userId=request.getHeader("userId");
        //查到这个学校的id
        String schoolId=usedService.findSchoolIdByUserId(userId);

        List<FunHomeUrl> funHomeUrl=appletLoginService.findHomeUrl(schoolId);

        return new JsonResult(funHomeUrl);
    }

    /*获取新人红包*/

    @RequestMapping(value = "/insertNewUserMoney.fun" ,method = RequestMethod.POST)
    @ResponseBody
    public Object insertNewUserMoney(HttpServletRequest request) {
        String userId=request.getHeader("userId");
        //动力币明细
        //查找用户动力币的奖励规则
        Integer num = 0;
        YlqUserTag ylqUserTag=signService.selectUserTag(userId);
        System.out.println("进来了"+ylqUserTag.getNewUserRedpacketTag());
        if(ylqUserTag.getNewUserRedpacketTag()<1) {
            int type = 7;
            YlqTask ylqTask = signService.selectTask(type);
            //奖励运动比
            Map<String, Object> addCoinMap = new HashMap<String, Object>();
            addCoinMap.put("userId", userId);
            addCoinMap.put("orderCoin", ylqTask.getMoney());
            appletLoginService.addCoinByUserId(addCoinMap);
            //查找用户动力币
            BigDecimal userCoin = shopSchoolService.selectUserCoin(userId);//查找的奖励过的
            Map<String, Object> coinInfMap = new HashMap<String, Object>();
            /*BigDecimal balance=userCoin.add(BigDecimal.valueOf(5));*/
            String coinId = UuidUtils.creatUUID();//主键
            coinInfMap.put("id", coinId);
            coinInfMap.put("userId", userId);
            coinInfMap.put("balance", userCoin);
            coinInfMap.put("state", 1);
            coinInfMap.put("orderCoin", ylqTask.getMoney());
            coinInfMap.put("type", type);//
            coinInfMap.put("remark", "领取新用户红包");
             num = shopSchoolService.insertCoinInf(coinInfMap);
            //改变用户的领取新用户红包完成状态
           appletLoginService.updateTagRedPacket(userId);
        }
        Map<String, Object> numberMap = new HashMap<String, Object>();
        numberMap.put("num", num);
        net.sf.json.JSONObject userVideoState = net.sf.json.JSONObject.fromObject(numberMap);
        return new JsonResult(userVideoState);
    }
    /*收藏小程序奖励*/
    @RequestMapping(value = "/insertNewUserCollect.fun" ,method = RequestMethod.POST)
    @ResponseBody
    public Object insertNewUserCollect(HttpServletRequest request) {
        String userId=request.getHeader("userId");
        //动力币明细
        //查找用户动力币的奖励规则
        int type=3;
        YlqTask ylqTask=signService.selectTask(type);
        //奖励运动比
        Map<String, Object> addCoinMap = new HashMap<String, Object>();
        addCoinMap.put("userId", userId);
        addCoinMap.put("orderCoin", ylqTask.getMoney());
        appletLoginService.addCoinByUserId(addCoinMap);
        //查找用户动力币
        BigDecimal userCoin=shopSchoolService.selectUserCoin(userId);//查找的奖励过的
        Map<String, Object> coinInfMap = new HashMap<String, Object>();
        /*BigDecimal balance=userCoin.add(BigDecimal.valueOf(5));*/
        String coinId = UuidUtils.creatUUID();//主键
        coinInfMap.put("id", coinId);
        coinInfMap.put("userId", userId);
        coinInfMap.put("balance", userCoin);
        coinInfMap.put("state", 1);
        coinInfMap.put("orderCoin",ylqTask.getMoney());
        coinInfMap.put("type", type);//
        coinInfMap.put("remark", "收藏小程序奖励");
        Integer num= shopSchoolService.insertCoinInf(coinInfMap);
        //改变用户的收藏小程序红包完成状态
        appletLoginService.updateTagCollection(userId);
        return new JsonResult(num);
    }

    /*看激励视频奖励*/
    @RequestMapping(value = "/insertIncentiveVideo.fun" ,method = RequestMethod.POST)
    @ResponseBody
    public Object insertIncentiveVideo(HttpServletRequest request) {
        String userId=request.getHeader("userId");
        //动力币明细
        //查找用户动力币的奖励规则
        Integer num = 0;
        Integer number = 0;
        YlqUserTag ylqUserTag=signService.selectUserTag(userId);
        System.out.println("进来了"+ylqUserTag.getIncentiveVideoTag());
        if(ylqUserTag.getIncentiveVideoTag()<3) {
            int type = 16;
            YlqTask ylqTask = signService.selectTask(type);
            //奖励运动比
            Map<String, Object> addCoinMap = new HashMap<String, Object>();
            addCoinMap.put("userId", userId);
            addCoinMap.put("orderCoin", ylqTask.getMoney());
            appletLoginService.addCoinByUserId(addCoinMap);
            //查找用户动力币
            BigDecimal userCoin = shopSchoolService.selectUserCoin(userId);//查找的奖励过的
            Map<String, Object> coinInfMap = new HashMap<String, Object>();
            /*BigDecimal balance=userCoin.add(BigDecimal.valueOf(5));*/
            String coinId = UuidUtils.creatUUID();//主键
            coinInfMap.put("id", coinId);
            coinInfMap.put("userId", userId);
            coinInfMap.put("balance", userCoin);
            coinInfMap.put("state", 1);
            coinInfMap.put("orderCoin", ylqTask.getMoney());
            coinInfMap.put("type", type);//
            coinInfMap.put("remark", "观看激励视频奖励");
              num = shopSchoolService.insertCoinInf(coinInfMap);
            //改变用户的激励小视频状态
            appletLoginService.updateVideo(userId);
            //返回用户观看激励视频的次数
            YlqUserTag ylqUserTags=signService.selectUserTag(userId);
            number=ylqUserTags.getIncentiveVideoTag();
        }
        Map<String, Object> numberMap = new HashMap<String, Object>();
        numberMap.put("number", number);
        net.sf.json.JSONObject userVideoState = net.sf.json.JSONObject.fromObject(numberMap);
        return new JsonResult(userVideoState);
    }

    /*校园优选优惠劵奖励*/
    @RequestMapping(value = "/insertSchoolCoupon.fun" ,method = RequestMethod.POST)
    @ResponseBody
    public Object insertSchoolCoupon(HttpServletRequest request) {
        String userId=request.getHeader("userId");
        //动力币明细
        //查找用户动力币的奖励规则
        Integer num = 0;
        Integer number = 0;
        YlqUserTag ylqUserTag=signService.selectUserTag(userId);
        System.out.println("进来了"+ylqUserTag.getAppointmentSuccessTag());
        if(ylqUserTag.getAppointmentSuccessTag()<1) {
            System.out.println("88888");
            int type = 10;
            YlqTask ylqTask = signService.selectTask(type);
            System.out.println(ylqTask.getMoney()+"ggggg");
            //奖励运动比
            Map<String, Object> addCoinMap = new HashMap<String, Object>();
            addCoinMap.put("userId", userId);
            addCoinMap.put("orderCoin", ylqTask.getMoney());
            appletLoginService.addCoinByUserId(addCoinMap);
            //查找用户动力币
            BigDecimal userCoin = shopSchoolService.selectUserCoin(userId);//查找的奖励过的
            System.out.println("在这里1"+userCoin);
            Map<String, Object> coinInfMap = new HashMap<String, Object>();
            /*BigDecimal balance=userCoin.add(BigDecimal.valueOf(5));*/
            String coinId = UuidUtils.creatUUID();//主键
            coinInfMap.put("id", coinId);
            coinInfMap.put("userId", userId);
            coinInfMap.put("balance", userCoin);
            coinInfMap.put("state", 1);
            coinInfMap.put("orderCoin", ylqTask.getMoney());
            coinInfMap.put("type", type);//
            coinInfMap.put("remark", "校园优选优惠劵奖励");
            num = shopSchoolService.insertCoinInf(coinInfMap);
            System.out.println("在这里2"+num);
            //改变用户的校园优选优惠劵状态
            appletLoginService.updateSchoolCoupon(userId);
            //返回校园优选优惠劵奖励次数
            YlqUserTag ylqUserTags=signService.selectUserTag(userId);
            number=ylqUserTags.getAppointmentSuccessTag();
            System.out.println("在这里3"+number);
        }
        else{
            System.out.println("已结完成了");
        }
        Map<String, Object> numberMap = new HashMap<String, Object>();
        numberMap.put("number", number);
        net.sf.json.JSONObject schoolCouponState = net.sf.json.JSONObject.fromObject(numberMap);
        return new JsonResult(schoolCouponState);
    }

    //获取二维码
    @PostMapping(value = "/findWXQR3.fun")
    @ResponseBody
    public Object findWXQR3() {
        String url_1 = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + "wx26b767a5139f9fbb"
                + "&secret=" + "c6897fd33cee0a54a689698fe25eb189";
        System.out.println(url_1 + "88888");
        JSONObject jsonObject = WeChatLogin.doGet(url_1);
        System.out.println(jsonObject + "999999");

        String access_tokens = jsonObject.getString("access_token");
        System.out.println("dddddd"+access_tokens);
      String url="https://api.weixin.0qq.com/wxa/getwxacodeunlimit?access_token="+access_tokens;
        Map<Object, Object> map = new HashMap<>();
        map.put("scene","98889");
        map.put("page", "pagesB/cooperation/cooperation");
        map.put("width", 430);
        map.put("auto_color", true);
        net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(map);
        System.out.println(json);
       /* String execute=HttpClientUtil.sendPost(url,json.toString());*/
       /*byte[] s= HttpClientUtil.post(url,json.toString());
        BASE64Encoder encoder = new BASE64Encoder();
        return s != null ? encoder.encode(s) : "";*/
        System.out.println(HttpClientUtil.sendPost3(url,json.toString()));
       return    HttpClientUtil.sendPost3(url,json.toString());
    }



    //获取二维码
    @PostMapping(value = "/findWXQR2.fun")
    @ResponseBody
    public Object findWXQR2() throws IOException {
        String url_1 = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + "wx26b767a5139f9fbb"
                + "&secret=" + "c6897fd33cee0a54a689698fe25eb189";
        System.out.println(url_1 + "88888");
        JSONObject jsonObject = WeChatLogin.doGet(url_1);
        System.out.println(jsonObject + "999999");

        String access_tokens = jsonObject.getString("access_token");
        System.out.println("dddddd"+access_tokens);
        /*String url="https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+access_tokens;
        Map<Object, Object> map = new HashMap<>();
        map.put("scene","99");
        map.put("page", "pagesB/cooperation/cooperation");
        map.put("width", 430);
        map.put("auto_color", true);
        net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(map);

        String execute=HttpClientUtil.sendPost(url,json.toString());

        BASE64Decoder decoder=new BASE64Decoder();

        byte[] bytes=decoder.decodeBuffer(execute);
        File file=new File("D:/a.png");

        FileOutputStream fos=new FileOutputStream(file);

        fos.write(bytes);

        fos.flush();

        fos.close();*/
        BufferedInputStream bis = null;
        OutputStream os=null;
        try
        {
            URL url = new URL("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+access_tokens);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
            // conn.setReadTimeout(2000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            net.sf.json.JSONObject paramJson = new net.sf.json.JSONObject();
            paramJson.put("scene", "555_999");
            paramJson.put("page", "pagesB/cooperation/cooperation");
            paramJson.put("width", 430);
            paramJson.put("auto_color", true);
            /**
             * line_color生效
             * paramJson.put("auto_color", false);
             * JSONObject lineColor = new JSONObject();
             * lineColor.put("r", 0);
             * lineColor.put("g", 0);
             * lineColor.put("b", 0);
             * paramJson.put("line_color", lineColor);
             * */

            printWriter.write(paramJson.toString());
            // flush输出流的缓冲
            printWriter.flush();
            //开始获取数据
             bis= new BufferedInputStream(httpURLConnection.getInputStream());
             os = new FileOutputStream(new File("D:/2.png"));
            int len;
            byte[] arr = new byte[1024];
            while ((len = bis.read(arr)) != -1)
            {
                os.write(arr, 0, len);
                os.flush();
            }
            os.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }




        //图片转二进制
       /* File f = new File("D:/2.png");
        BufferedImage bi;
        try {
            bi = ImageIO.read(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", baos);
            byte[] bytes = baos.toByteArray();

            return encoder.encodeBuffer(bytes).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;*/
        return null;
    }






/*获取二维码---用这个*/
    @PostMapping(value = "/findWXQR.fun")
    @ResponseBody
    public Object findWXQR() {

        String url_1 = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + "wx26b767a5139f9fbb"
                + "&secret=" + "c6897fd33cee0a54a689698fe25eb189";
        System.out.println(url_1 + "88888");
        JSONObject jsonObject = WeChatLogin.doGet(url_1);
        System.out.println(jsonObject + "999999");

        String access_tokens = jsonObject.getString("access_token");
        System.out.println("dddddd" + access_tokens);
        String qrcodeUrl = "https://api.weixin.qq.com/wxa/getwxacodeunlimit";  // 获取小程序码的接口头部
        String url = qrcodeUrl + "?access_token=" + access_tokens;  // 拼接完整的URl
        Map<String, Object> requestParam = new HashMap<String, Object>();  // 小程序的参数可查看官方文档
        requestParam.put("page", "pagesB/cooperation/cooperation");  // 扫码后需要跳转的页面
        requestParam.put("scene", "999");  // 携带的参数
       /* requestParam.put("width", 430);*/  // 二维码的宽度
       /* requestParam.put("auto_color",false);
        Map<String,Object> line_color = new HashMap<>();
        line_color.put("r", 236);
        line_color.put("g", 136);
        line_color.put("b", 185);
        requestParam.put("line_color", line_color);*/
        String param = JSON.toJSONString(requestParam);

        // 使用 RestTemplate 出现的问题，用该类代码会更简洁
        // rest debugger发过来的content-type为application/json，而jq的多了charset=utf-8
        // Could not read document: Invalid UTF-8 middle byte 0xff
	/*JSONObject jsonObject =
			restTemplate.postForObject(url, param, JSONObject.class);
	System.out.println(jsonObject.toJSONString());*/

        HttpURLConnection conn = null;
        BufferedReader bufferedReader = null;
        PrintWriter out = null;
        InputStream in = null;
        ByteArrayOutputStream bos = null;
        String base64String = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            conn = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 超时设置,防止 网络异常的情况下,可能会导致程序僵死而不继续往下执行
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();

            //生成图片
           /* BufferedInputStream bis = null;
            OutputStream osm=null;
            bis= new BufferedInputStream(conn.getInputStream());
            osm = new FileOutputStream(new File("D:/3.png"));
            int lent;
            byte[] arr = new byte[1024];
            while ((lent = bis.read(arr)) != -1)
            {
                osm.write(arr, 0, lent);
                osm.flush();
            }
            osm.close();*/

            in = conn.getInputStream();  // 得到图片的二进制内容
            int leng = in.available();  // 获取二进制流的长度，该方法不准确
            if (leng < 1000) {  // 出现错误时，获取字符长度就一百不到，图片的话有几万的长度
                // 定义BufferedReader输入流来读取URL的响应
                bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String line;
                String result = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                /*logger.debug("获取access_token出错：" + result);*/
                // 清空 access_token 缓存
                /*CacheManagerImpl cacheManagerImpl = CacheManagerImpl.getInstance();
                cacheManagerImpl.clearByKey("access_token");*/
                return result;
            }

            // 修改图片的分辨率,分辨率太大打印纸不够大
            //BufferedInputStream in2 = new BufferedInputStream(conn.getInputStream());
            // 将文件二进制流修改为图片流
            Image srcImg = ImageIO.read(in);
            // 构建图片流
            BufferedImage buffImg = new BufferedImage(430, 430, BufferedImage.TYPE_INT_RGB);
            //绘制改变尺寸后的图
            buffImg.getGraphics().drawImage(srcImg.getScaledInstance(430, 430, Image.SCALE_SMOOTH), 0, 0, null);
            // 将图片流修改为文件二进制流
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(buffImg, "png", os);
            in = new ByteArrayInputStream(os.toByteArray());
            // 刷新，将重置为类似于首次创建时的状态
            buffImg.flush();
            srcImg.flush();
            // 设null是告诉jvm此资源可以回收
            buffImg = null;  // 该io流不存在关闭函数
            srcImg = null;  // 该io流不存在关闭函数
            os.close();

            bos = new ByteArrayOutputStream();
            byte[] b1 = new byte[1024];
            int len = -1;
            while ((len = in.read(b1)) != -1) {
                bos.write(b1, 0, len);
            }
            byte[] fileByte = bos.toByteArray();  // 转换为字节数组，方便转换成base64编码


            //BASE64Encoder encoder = new BASE64Encoder();   // import sun.misc.BASE64Encoder;  该类包为内部专用以后可能会删除，sun公司
            // 对字节数组转换成Base64字符串
            //base64String = encoder.encode(fileByte);

            base64String = Base64.encodeBase64String(fileByte);  // import org.apache.commons.codec.binary.Base64;
        } catch (Exception e) {
            /*logger.error(e.getMessage(), e);*/
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                if (bos != null) {
                    bos.close();
                }
                if (conn != null) {
                    conn.disconnect();
                    conn = null;
                }
                //让系统回收资源，但不一定是回收刚才设成null的资源，可能是回收其他没用的资源。
                System.gc();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return base64String;  // 将base64格式的图片发送到前端

    }



    /*
    * 后台端
    *
    * */
    //查看微信用户列表 分页 学校id，是否授权，开始时间，结束时间，最小动力币，最大动力币
    @RequestMapping(value = "/findWXUserList.fun")
    @ResponseBody
    public Object findWXUserList(int page, int pageSize,String schoolId ,String accredit,String  startTime,
                                 String endTime,String mixCoin,String maxCoin ,String  userId,String nickname,String phone) {

        if (schoolId.equals("")){
            schoolId=null;
        } if (accredit.equals("")){
            accredit=null;
        } if (startTime.equals("")){
            startTime=null;
        } if (endTime.equals("")){
            endTime=null;
        } if (mixCoin.equals("")){
            mixCoin=null;
        }if (maxCoin.equals("")){
            maxCoin=null;
        }
        if (userId.equals("")){
            userId=null;
        }
        if (nickname.equals("")){
            nickname=null;
        }if (phone.equals("")){
            phone=null;
        }
        /*page 第几页，pageSize 每页条数*/
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> usedMap = new HashMap<String, Object>();
        usedMap.put("schoolId",schoolId);
        usedMap.put("accredit",accredit);
        usedMap.put("startTime",startTime);
        usedMap.put("endTime",endTime);
        usedMap.put("mixCoin",mixCoin);
        usedMap.put("maxCoin",maxCoin);
        usedMap.put("userId",userId);
        usedMap.put("nickname",nickname);
        usedMap.put("phone",phone);
        System.out.println("maxCoin"+maxCoin);
        System.out.println("nickname"+nickname);
        PageInfo<WxUser> pager = appletLoginService.findWXUserList(page,pageSize,usedMap);
        //总条数
        resultMap.put("total", pager.getTotal());
        //获取每页数据
        resultMap.put("rows", pager.getList());
        return new JsonResult(resultMap);
    }

   //模糊搜索用户（昵称，主键id动力币余额，手机号，唯一id）----作废
    @RequestMapping(value = "/searchMapWXUserList.fun")
    @ResponseBody
    public Object searchMapWXUserList(int page, int pageSize,String search) {
         /*page 第几页，pageSize 每页条数*/
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("search",search);
        PageInfo<WxUser> pager = appletLoginService.searchMapWXUserList(page,pageSize,searchMap);
        //总条数
        resultMap.put("total", pager.getTotal());
        //获取每页数据
        resultMap.put("rows", pager.getList());
        return new JsonResult(resultMap);
    }

    //查找用户的动力币明细
    @RequestMapping(value = "/searchUserWalletDetail.fun")
    @ResponseBody
    public Object searchUserWalletDetail(int page, int pageSize,String userId) {
        /*page 第几页，pageSize 每页条数*/
        Map<String, Object> resultMap = new HashMap<String, Object>();
        PageInfo<YlqWalletDetail> pager = appletLoginService.findUserWalletDetail(page,pageSize,userId);
        //总条数
        resultMap.put("total", pager.getTotal());
        //获取每页数据
        resultMap.put("rows", pager.getList());
        return new JsonResult(resultMap);
    }

    //修改动力币
    @RequestMapping(value = "/updateUserWallet.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object updateUserWallet(@RequestBody YlqWallet ylqWallet) {
        String userId =ylqWallet.getUserId();
        BigDecimal balance=ylqWallet.getBalance();
        YlqWallet ylqWallets=new YlqWallet();
        ylqWallets.setUserId(userId);
        ylqWallets.setBalance(balance);
        Integer num=  appletLoginService.updateUserWallet(ylqWallets);
        return new JsonResult(num);
    }

    //拉黑用户
    @RequestMapping(value = "/updateUserStatus.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object updateUserStatus(@RequestBody WxUser wxUser) {
        String id =wxUser.getId();
        Integer num=  appletLoginService.updateUserStatus(id);
        //把这个用户的动态都改为下架
        appletLoginService.updateUsedOut(id);
        return new JsonResult(num);
    }
    //解封用户
    @RequestMapping(value = "/updateUserStatusOpen.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object updateUserStatusOpen(@RequestBody WxUser wxUser) {
        String id =wxUser.getId();
        Integer num=  appletLoginService.updateUserStatusOpen(id);
        return new JsonResult(num);
    }
    //用户的愿望清单
    @RequestMapping(value = "/findTaskList.fun")
    @ResponseBody
    public Object findTaskList(HttpServletRequest request) {
        String userId=request.getHeader("userId");

        WxUser wxUser=appletLoginService.finUsedUserInf(userId);
        Integer sex=  wxUser.getSex();
        if(sex==null){
            sex=3;
        }
        String userTaskList;
        if(sex==1){//男生
        //男生随机拿去5条数据
            //其他
            int type=1;
            Map<String, Object> list = new HashMap<String, Object>();
            list.put("sex", sex);
            list.put("type", type);
            String rest=appletLoginService.selectTaskList(list);
            //去
            type=2;
            Map<String, Object> go = new HashMap<String, Object>();
            go.put("sex", sex);
            go.put("type", type);
            String goTask=appletLoginService.selectTaskList(go);
            //吃
             type=3;
            Map<String, Object> eat = new HashMap<String, Object>();
            eat.put("sex", sex);
            eat.put("type", type);
            String eatTask=appletLoginService.selectTaskList(eat);
            //学
            type=4;
            Map<String, Object> study = new HashMap<String, Object>();
            study.put("sex", sex);
            study.put("type", type);
            String studyTask=appletLoginService.selectTaskList(study);
            //感情
             type=5;
            Map<String, Object> feeling = new HashMap<String, Object>();
            feeling.put("sex", sex);
            feeling.put("type", type);
            String feelingTask=appletLoginService.selectTaskList(feeling);
            userTaskList=rest+"/"+goTask+"/"+eatTask+"/"+studyTask+"/"+feelingTask;
            System.out.println(userTaskList);
        }else if(sex==2){//女生
            //女生随机拿去5条数据
            //其他
            int type=1;
            Map<String, Object> list = new HashMap<String, Object>();
            list.put("sex", sex);
            list.put("type", type);
            String rest=appletLoginService.selectTaskList(list);
            //去
            type=2;
            Map<String, Object> go = new HashMap<String, Object>();
            go.put("sex", sex);
            go.put("type", type);
            String goTask=appletLoginService.selectTaskList(go);
            //吃
            type=3;
            Map<String, Object> eat = new HashMap<String, Object>();
            eat.put("sex", sex);
            eat.put("type", type);
            String eatTask=appletLoginService.selectTaskList(eat);
            //学
            type=4;
            Map<String, Object> study = new HashMap<String, Object>();
            study.put("sex", sex);
            study.put("type", type);
            String studyTask=appletLoginService.selectTaskList(study);
            //感情
            type=5;
            Map<String, Object> feeling = new HashMap<String, Object>();
            feeling.put("sex", sex);
            feeling.put("type", type);
            String feelingTask=appletLoginService.selectTaskList(feeling);
            userTaskList=rest+"/"+goTask+"/"+eatTask+"/"+studyTask+"/"+feelingTask;
            System.out.println("女生"+userTaskList);
        }else{//3
            //其他人随机拿去5条数据
            //其他
            int type=1;
            Map<String, Object> list = new HashMap<String, Object>();
            list.put("sex", sex);
            list.put("type", type);
            String rest=appletLoginService.selectTaskListElse(list);
            //去
            type=2;
            Map<String, Object> go = new HashMap<String, Object>();
            go.put("sex", sex);
            go.put("type", type);
            String goTask=appletLoginService.selectTaskListElse(go);
            //吃
            type=3;
            Map<String, Object> eat = new HashMap<String, Object>();
            eat.put("sex", sex);
            eat.put("type", type);
            String eatTask=appletLoginService.selectTaskListElse(eat);
            //学
            type=4;
            Map<String, Object> study = new HashMap<String, Object>();
            study.put("sex", sex);
            study.put("type", type);
            String studyTask=appletLoginService.selectTaskListElse(study);
            //感情
            type=5;
            Map<String, Object> feeling = new HashMap<String, Object>();
            feeling.put("sex", sex);
            feeling.put("type", type);
            String feelingTask=appletLoginService.selectTaskListElse(feeling);
            userTaskList=rest+"/"+goTask+"/"+eatTask+"/"+studyTask+"/"+feelingTask;
            System.out.println("其他"+userTaskList);
        }
        Map<String, Object> listMap = new HashMap<String, Object>();
        listMap.put("userTaskList", userTaskList);
        net.sf.json.JSONObject userVideoState = net.sf.json.JSONObject.fromObject(listMap);
        return new JsonResult(userVideoState);
    }
    //修改动力币
    @RequestMapping(value = "/updateWallet.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object updateWallet(@RequestBody WxUser  wxUser ) {
        String id =wxUser.getId();
        BigDecimal coin =wxUser.getCoin();
        WxUser wxUsers=new WxUser();
        wxUsers.setId(id);
        wxUsers.setCoin(coin);
        Integer num=  appletLoginService.updateWallet(wxUsers);
        return new JsonResult(num);
    }
}
