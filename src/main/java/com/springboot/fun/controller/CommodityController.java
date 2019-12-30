package com.springboot.fun.controller;

import com.github.pagehelper.PageInfo;
import com.springboot.fun.entity.*;
import com.springboot.fun.service.AppletLoginService;
import com.springboot.fun.service.CommodityService;
import com.springboot.fun.service.SupplierService;
import com.springboot.fun.service.UsedService;
import com.springboot.fun.util.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.springboot.fun.util.utils.StringUtils.isNotEmpty;

/*商品*/

@CrossOrigin(origins = "*",maxAge = 3600)
@Controller
@RequestMapping(value = "/commodity")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;
    @Autowired
    private AppletLoginService appletLoginService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private UsedService usedService;
    /*
     * 小程序端
     *
     * */

    //获取商品列表
    @RequestMapping(value = "/findCommodityList.fun")
    @ResponseBody
    public Object findCommodityList(int page, int pageSize,String type,HttpServletRequest request) {
        String userId=request.getHeader("userId");//用户id
        if (type.equals("")){
            type=null;
        }
        /*page 第几页，pageSize 每页条数*/
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> commodityMap = new HashMap<String, Object>();
        commodityMap.put("userId",userId);
        commodityMap.put("type",type);
        PageInfo<FunCommodity> pager = commodityService.findCommodityList(page,pageSize,commodityMap);

        //总条数
        resultMap.put("total", pager.getTotal());
        //获取每页数据
        resultMap.put("rows", pager.getList());


        return new JsonResult(resultMap);
    }

    //获取会员列表
    @RequestMapping(value = "/findMemberCommodityList.fun")
    @ResponseBody
    public Object findMemberCommodityList(int page, int pageSize,HttpServletRequest request) {
        String userId=request.getHeader("userId");//用户id
        /*page 第几页，pageSize 每页条数*/
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> commodityMap = new HashMap<String, Object>();
        commodityMap.put("userId",userId);
        PageInfo<FunCommodity> pager = commodityService.findMemberCommodityList(page,pageSize,commodityMap);

        //总条数
        resultMap.put("total", pager.getTotal());
        //获取每页数据
        resultMap.put("rows", pager.getList());


        return new JsonResult(resultMap);
    }
    //根据商品id查看商品详情
    @RequestMapping(value = "/findCommodityInf.fun")
    @ResponseBody
    public Object findCommodityInf(String id) {

        List<FunCommodity> commodityInf = commodityService.findCommodityInf(id);

        return new JsonResult(commodityInf);
    }
    //根据商家id查看校园福利商家详情
    @RequestMapping(value = "/findCommoditySupplierById.fun")
    @ResponseBody
    public Object findCommoditySupplierById(String supplierId) {

        List<NideShopSupplier> shopSchoolSupplier = commodityService.findCommoditySupplierById(supplierId);
        //浏览量真实都加1
        commodityService.updateBrowseNum(supplierId);
        return new JsonResult(shopSchoolSupplier);
    }
    /*
     * 立即兑换
     * */
    //查找是否以前兑换过(次数限制)查找动力币是否足够  减库存， 减去动力币， 生成订单，销量加一，动力币明细
    @RequestMapping(value = "/insertCoinOrder.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object insertUsedComment(@RequestBody FunCommodityOrder funCommodityOrder , HttpServletRequest request) {
        String userId=request.getHeader("userId");//用户id
        String id = UuidUtils.creatUUID();//主键
          String commodityId=funCommodityOrder.getCommodityId();//关联商品id
          String supplierId=funCommodityOrder.getSupplierId();//关联商家id
          /*Integer orderState=funCommodityOrder;//订单状态(待发货1，待自提2，已发货3，已核销4，已完成5，已过期6）*/
          String orderSupplierName =funCommodityOrder.getOrderSupplierName();//商家名称
          String orderSupplierImg =funCommodityOrder.getOrderSupplierImg();//商家图片
          String orderSupplierPhone =funCommodityOrder.getOrderSupplierPhone();//商家电话
          String orderSupplierAddress =funCommodityOrder.getOrderSupplierAddress();//商家地址
          String orderCommodityName =funCommodityOrder.getOrderCommodityName();//商品名称
          String orderCommodityImg =funCommodityOrder.getOrderCommodityImg();//商品图片
        String orderSerialNumber=RandomImgName.getOrderIdByUUId();//订单编号
          BigDecimal orderStorePrice =funCommodityOrder.getOrderStorePrice();//门店价
          BigDecimal orderSubscribePrice =funCommodityOrder.getOrderSubscribePrice();//预约价
        String orderCode=RandomNumber.random(6);
        //查找核销码有没有重复，有，就再次生成
        String orderCodeNumber = commodityService.findCodeNumber(orderCode);
        while (orderCodeNumber != null){
            orderCode=RandomNumber.random(6);
            orderCodeNumber = commodityService.findCodeNumber(orderCode);
        }
          String orderMemberName =funCommodityOrder.getOrderMemberName();//用户名
          String orderMemberPhone =funCommodityOrder.getOrderMemberPhone();//用户电话
          String orderMemberAddress =funCommodityOrder.getOrderMemberAddress();//用户地址
          Date orderGetTime=funCommodityOrder.getOrderGetTime();//订单领取时间
          Date orderLoseTime =funCommodityOrder.getOrderLoseTime();//订单失效时间
          BigDecimal orderCoin =funCommodityOrder.getOrderCoin();//预约动力币
          String orderMessage =funCommodityOrder.getOrderMessage();//买家留言
          Integer orderAttribute =funCommodityOrder.getOrderAttribute();//商品属性（线上1；线下2）
          Integer orderNumber =funCommodityOrder.getOrderNumber();//数量
          Integer commodityMemberState =funCommodityOrder.getOrderNumber();//数量
        //查找用户兑换了多少次
        Map<Object, Object> userConvertNumMap = new HashMap<Object, Object>();
        userConvertNumMap.put("userId", userId);
        userConvertNumMap.put("commodityId", commodityId);
        Integer userConvertNum=commodityService.selectUserConvertNum(userConvertNumMap);
        System.out.println("userConvertNum"+userConvertNum);
        //查找商品的信息（兑换的次数）
        FunCommodity funCommodity=commodityService.selectCommodityOrderNum(commodityId);
        Integer commodityNumber=funCommodity.getCommodityNumber();
        System.out.println("commodityNumber"+commodityNumber);
        //满足条件，可以兑换
        int state = 1;
        Integer num;

        //查找用户信息
        WxUser wxUser=appletLoginService.finUsedUserInf(userId);
        Integer memberState=wxUser.getMemberState();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        long newTime = c.getTimeInMillis();//当前时间
        Date memberDeadlineTime = wxUser.getMemberDeadlineTime();
        long memberTime;
        if(memberDeadlineTime!=null) {  //时间不为空
            memberDeadlineTime.setTime(memberDeadlineTime.getTime() - 8 * 60 * 60 * 1000);//时差，减8小时
            Calendar member = Calendar.getInstance();
            member.setTime(memberDeadlineTime);
             memberTime = member.getTimeInMillis();//会员到期时间
        }else{
            memberTime=0;
        }

        if(memberState==1 && memberTime > newTime) {
            System.out.println("能兑换有会员的商品");
            if (userConvertNum < commodityNumber) {
                //可以兑换

                //查找用户动力币是否足够
                BigDecimal userCoin = commodityService.selectUserCoin(userId);
                //查找库存是否足够
                Integer storeNum = commodityService.selectStore(commodityId);
                if (orderCoin.compareTo(userCoin) == 1 || storeNum <= 0) {
                    state = 0;//不能兑换
                    Map<Object, Object> map = new HashMap<Object, Object>();
                    map.put("state", state);
                    JSONObject userCoinState = JSONObject.fromObject(map);
                    return new JsonResult(userCoinState);
                } else {
                    //减库存
                    commodityService.updateCommodityStore(commodityId);
                    //减动力币
                    Map<String, Object> coinMap = new HashMap<String, Object>();
                    coinMap.put("userId", userId);
                    coinMap.put("orderCoin", orderCoin);
                    commodityService.updateUserCoin(coinMap);
                    //生成订单
                    FunCommodityOrder funCommodityOrders = new FunCommodityOrder();
                    funCommodityOrders.setId(id);
                    funCommodityOrders.setCommodityId(commodityId);//关联商品id
                    funCommodityOrders.setSupplierId(supplierId);//关联商家id
                    funCommodityOrders.setUserId(userId);//用户id
                    if (orderAttribute == 1) {//线上   订单状态
                        funCommodityOrders.setOrderState(1);
                    } else {
                        funCommodityOrders.setOrderState(2);
                    }
                    funCommodityOrders.setOrderSupplierName(orderSupplierName);//商家名称
                    funCommodityOrders.setOrderSupplierImg(orderSupplierImg);//商家图片
                    funCommodityOrders.setOrderSupplierPhone(orderSupplierPhone);//商家电话
                    funCommodityOrders.setOrderSupplierAddress(orderSupplierAddress);//商家地址
                    funCommodityOrders.setOrderCommodityName(orderCommodityName);//商品名称
                    funCommodityOrders.setOrderCommodityImg(orderCommodityImg);//商品图片
                    funCommodityOrders.setOrderSerialNumber(orderSerialNumber);//订单编号
                    funCommodityOrders.setOrderStorePrice(orderStorePrice);//门店价
                    funCommodityOrders.setOrderSubscribePrice(orderSubscribePrice);//预约价
                    funCommodityOrders.setOrderCode(orderCode);//核销码
                    funCommodityOrders.setOrderMemberName(orderMemberName);//用户名
                    funCommodityOrders.setOrderMemberPhone(orderMemberPhone);//用户电话
                    funCommodityOrders.setOrderMemberAddress(orderMemberAddress);//用户地址
                    funCommodityOrders.setOrderGetTime(orderGetTime);//订单领取时间
                    funCommodityOrders.setOrderLoseTime(orderLoseTime);//订单失效时间
                    funCommodityOrders.setOrderCoin(orderCoin);//预约动力币
                    funCommodityOrders.setOrderMessage(orderMessage);//买家留言
                    funCommodityOrders.setOrderAttribute(orderAttribute);//商品属性（线上1；线下2）
                    funCommodityOrders.setOrderNumber(orderNumber);//数量
                    num = commodityService.insertCommodityOrder(funCommodityOrders);
                    if (num == 1) {//兑换成功
                        state = 1;
                    }
                    //已兑换（真实，虚拟加1）
                    commodityService.updateCommodityCash(commodityId);
                    //把用户头像添加到商品表的购买集合里面
                    funCommodity.setId(commodityId);
                    System.out.println(funCommodity.getCommodityMemberList() + "77777777");

                    //如果已经有5个用户头像了，则不添加（为空则添加一个）    修改头像为空的报错
                    if (!isNotEmpty(funCommodity.getCommodityMemberList())) {

                        if (funCommodity.getCommodityMemberList().length() < 800) {
                            String memberList = funCommodity.getCommodityMemberList() + "," + wxUser.getHeadimgurl();
                            funCommodity.setCommodityMemberList(memberList);
                            System.out.println("555555555555555555555555" + memberList);
                            commodityService.addUserUrlToCommodity(funCommodity);
                        } else {
                            System.out.println("不添加");
                        }
                    } else {
                        String memberList = wxUser.getHeadimgurl();
                        funCommodity.setCommodityMemberList(memberList);
                        System.out.println("555555555555555555555555" + memberList);
                        commodityService.addUserUrlToCommodity(funCommodity);
                    }

                    if (orderCoin.compareTo(BigDecimal.ZERO) == 1) {
                        //查找用户动力币的奖励规则
                        //生成动力币明细
                        Map<String, Object> coinInfMap = new HashMap<String, Object>();
                        BigDecimal balance = userCoin.subtract(orderCoin);
                        String coinId = UuidUtils.creatUUID();//主键
                        coinInfMap.put("id", coinId);
                        coinInfMap.put("userId", userId);
                        coinInfMap.put("balance", balance);
                        coinInfMap.put("orderCoin", orderCoin);
                        coinInfMap.put("state", 2);
                        coinInfMap.put("type", 10);//兑换商品
                        coinInfMap.put("orderId", id);
                        coinInfMap.put("remark", "预约商品消耗");
                        commodityService.insertCoinInf(coinInfMap);
                    }
                }
            } else {
                state = 2;//已经兑换过了
                Map<Object, Object> map = new HashMap<Object, Object>();
                map.put("state", state);
                JSONObject userCoinState = JSONObject.fromObject(map);
                return new JsonResult(userCoinState);
            }
        }else {
            if (memberState == 0) {
                if (userConvertNum < commodityNumber) {
                    //可以兑换
                    //查找用户动力币是否足够
                    BigDecimal userCoin = commodityService.selectUserCoin(userId);
                    //查找库存是否足够
                    Integer storeNum = commodityService.selectStore(commodityId);
                    if (orderCoin.compareTo(userCoin) == 1 || storeNum <= 0) {
                        state = 0;//不能兑换
                        Map<Object, Object> map = new HashMap<Object, Object>();
                        map.put("state", state);
                        JSONObject userCoinState = JSONObject.fromObject(map);
                        return new JsonResult(userCoinState);
                    } else {
                        //减库存
                        commodityService.updateCommodityStore(commodityId);
                        //减动力币
                        Map<String, Object> coinMap = new HashMap<String, Object>();
                        coinMap.put("userId", userId);
                        coinMap.put("orderCoin", orderCoin);
                        commodityService.updateUserCoin(coinMap);
                        //生成订单
                        FunCommodityOrder funCommodityOrders = new FunCommodityOrder();
                        funCommodityOrders.setId(id);
                        funCommodityOrders.setCommodityId(commodityId);//关联商品id
                        funCommodityOrders.setSupplierId(supplierId);//关联商家id
                        funCommodityOrders.setUserId(userId);//用户id
                        if (orderAttribute == 1) {//线上   订单状态
                            funCommodityOrders.setOrderState(1);
                        } else {
                            funCommodityOrders.setOrderState(2);
                        }
                        funCommodityOrders.setOrderSupplierName(orderSupplierName);//商家名称
                        funCommodityOrders.setOrderSupplierImg(orderSupplierImg);//商家图片
                        funCommodityOrders.setOrderSupplierPhone(orderSupplierPhone);//商家电话
                        funCommodityOrders.setOrderSupplierAddress(orderSupplierAddress);//商家地址
                        funCommodityOrders.setOrderCommodityName(orderCommodityName);//商品名称
                        funCommodityOrders.setOrderCommodityImg(orderCommodityImg);//商品图片
                        funCommodityOrders.setOrderSerialNumber(orderSerialNumber);//订单编号
                        funCommodityOrders.setOrderStorePrice(orderStorePrice);//门店价
                        funCommodityOrders.setOrderSubscribePrice(orderSubscribePrice);//预约价
                        funCommodityOrders.setOrderCode(orderCode);//核销码
                        funCommodityOrders.setOrderMemberName(orderMemberName);//用户名
                        funCommodityOrders.setOrderMemberPhone(orderMemberPhone);//用户电话
                        funCommodityOrders.setOrderMemberAddress(orderMemberAddress);//用户地址
                        funCommodityOrders.setOrderGetTime(orderGetTime);//订单领取时间
                        funCommodityOrders.setOrderLoseTime(orderLoseTime);//订单失效时间
                        funCommodityOrders.setOrderCoin(orderCoin);//预约动力币
                        funCommodityOrders.setOrderMessage(orderMessage);//买家留言
                        funCommodityOrders.setOrderAttribute(orderAttribute);//商品属性（线上1；线下2）
                        funCommodityOrders.setOrderNumber(orderNumber);//数量
                        num = commodityService.insertCommodityOrder(funCommodityOrders);
                        if (num == 1) {//兑换成功
                            state = 1;
                        }
                        //已兑换（真实，虚拟加1）
                        commodityService.updateCommodityCash(commodityId);
                        //把用户头像添加到商品表的购买集合里面
                        funCommodity.setId(commodityId);
                        System.out.println(funCommodity.getCommodityMemberList() + "77777777");

                        //如果已经有5个用户头像了，则不添加（为空则添加一个）    修改头像为空的报错
                        if (funCommodity.getCommodityMemberList()!=null) {

                            if (funCommodity.getCommodityMemberList().length() < 800) {
                                String memberList = funCommodity.getCommodityMemberList() + "," + wxUser.getHeadimgurl();
                                funCommodity.setCommodityMemberList(memberList);
                                System.out.println("555555555555555555555555" + memberList);
                                commodityService.addUserUrlToCommodity(funCommodity);
                            } else {
                                System.out.println("不添加");
                            }
                        } else {
                            String memberList = wxUser.getHeadimgurl();
                            funCommodity.setCommodityMemberList(memberList);
                            System.out.println("999999" + memberList);
                            commodityService.addUserUrlToCommodity(funCommodity);
                        }

                        if (orderCoin.compareTo(BigDecimal.ZERO) == 1) {
                            //查找用户动力币的奖励规则
                            //生成动力币明细
                            Map<String, Object> coinInfMap = new HashMap<String, Object>();
                            BigDecimal balance = userCoin.subtract(orderCoin);
                            String coinId = UuidUtils.creatUUID();//主键
                            coinInfMap.put("id", coinId);
                            coinInfMap.put("userId", userId);
                            coinInfMap.put("balance", balance);
                            coinInfMap.put("orderCoin", orderCoin);
                            coinInfMap.put("state", 2);
                            coinInfMap.put("type", 10);//兑换商品
                            coinInfMap.put("orderId", id);
                            coinInfMap.put("remark", "预约商品消耗");
                            commodityService.insertCoinInf(coinInfMap);
                        }
                    }
                } else {
                    state = 2;//已经兑换过了
                    Map<Object, Object> map = new HashMap<Object, Object>();
                    map.put("state", state);
                    JSONObject userCoinState = JSONObject.fromObject(map);
                    return new JsonResult(userCoinState);
                }
            } else {
                state = 3;//不是会员
                Map<Object, Object> map = new HashMap<Object, Object>();
                map.put("state", state);
                JSONObject userCoinState = JSONObject.fromObject(map);
                return new JsonResult(userCoinState);
            }
        }
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("state", state);
        JSONObject userCoinState = JSONObject.fromObject(map);
        return new JsonResult(userCoinState);
    }

    //获取订单列表
    @RequestMapping(value = "/findCommodityOrder.fun")
    @ResponseBody
    public Object findCommodityOrder(int page, int pageSize, int type,String state, HttpServletRequest request) throws ParseException {
        String userId=request.getHeader("userId");//用户id
        //查找用户的取货时间，如果大于当前时间，则让订单失效，修改订单状态
        //查找用户的订单信息
        List<FunCommodityOrder> funCommodityOrderList=commodityService.selectCommodityOrder(userId);
        for (FunCommodityOrder x : funCommodityOrderList) {
                Date loseTime=x.getOrderLoseTime();
                Integer orderState=x.getOrderState();
            loseTime.setTime(loseTime.getTime() - 8*60*60*1000);
                if(orderState==2 && (new Date()).compareTo(loseTime)>0){
                    //修改核销状态为已失效
                    commodityService.updateMyCommodityOrderState(x.getId());
                    System.out.println("订单失效了");
                }
        }
        /*
            类型：type 线上1，线下2

         * 加查找状态state，(待发货1，待自提2，已发货3，已核销4，已完成5，已过期6）全部，不输
         * */
        if (state.equals("")){
            state=null;
        }
        Map<String, Object> stateMap = new HashMap<String, Object>();
        stateMap.put("userId",userId);
        stateMap.put("state",state);
        stateMap.put("type",type);
        PageInfo<FunCommodityOrder> pager = commodityService.findCommodityOrder(page,pageSize,stateMap);

        /*page 第几页，pageSize 每页条数*/
        Map<String, Object> resultMap = new HashMap<String, Object>();

        //总条数
        resultMap.put("total", pager.getTotal());
        //获取每页数据
        resultMap.put("rows", pager.getList());
        return new JsonResult(resultMap);
    }
    //根据商品id查看订单详情
    @RequestMapping(value = "/finCommodityOrderById.fun")
    @ResponseBody
    public Object finGoodShopOrderById(String id) {

        List<FunCommodityOrder> commodityOrder = commodityService.findCommodityOrderById(id);

        return new JsonResult(commodityOrder);
    }

    //确认收货
    @RequestMapping(value = "/receiveGoodShop.fun", method = RequestMethod.POST)
    @ResponseBody
    public Object receiveGoodShop(@RequestBody FunCommodityOrder funCommodityOrder) {
        String id=funCommodityOrder.getId();
        Integer num = commodityService.receiveCommodityOrder(id);
        return new JsonResult(num);
    }
    //分享商品
    @RequestMapping(value = "/shareCommodity.fun", method = RequestMethod.POST)
    @ResponseBody
    public Object shareCommodity(@RequestBody NideShopSupplier nideShopSupplier) {
        String id=nideShopSupplier.getId();
        Integer num = commodityService.shareCommodity(id);
        return new JsonResult(num);
    }

    //获取商品分类
    @RequestMapping(value = "/finCommodityType.fun")
    @ResponseBody
    public Object finCommodityType() {
        List<UsedLabel> usedLabels=usedService.finCommodityType();
        System.out.println(usedLabels);
        return new JsonResult(usedLabels);
    }

  /*后台端*/

  //搜索获取商品列表
  @RequestMapping(value = "/searchCommodityList.fun")
  @ResponseBody
  public Object searchCommodityList(int page, int pageSize,String schoolId,String supplier,String commodity) {
      if (schoolId.equals("")){
          schoolId=null;
      }if (supplier.equals("")){
          supplier=null;
      }if (commodity.equals("")){
          commodity=null;
      }
      /*page 第几页，pageSize 每页条数*/
      Map<String, Object> resultMap = new HashMap<String, Object>();
      Map<String, Object> commodityMap = new HashMap<String, Object>();
      commodityMap.put("schoolId",schoolId);
      commodityMap.put("supplier",supplier);
      commodityMap.put("commodity",commodity);
      PageInfo<FunCommodity> pager = commodityService.searchCommodityList(page,pageSize,commodityMap);

      //总条数
      resultMap.put("total", pager.getTotal());
      //获取每页数据
      resultMap.put("rows", pager.getList());
      return new JsonResult(resultMap);
  }
  //根据商家名称，模糊搜索商家--商品列表需要
    @RequestMapping(value = "/searchAllSupplierByName.fun")
    @ResponseBody
    public Object findAllSupplierByName(String supplierName) {
        List<NideShopSupplier> pager = supplierService.searchAllSupplierByName(supplierName);
        return new JsonResult(pager);
    }

    //上传商品
    @RequestMapping(value = "/insertCommodity.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object insertCommodity(@RequestBody FunCommodity funCommodity) {

        FunCommodity funCommoditys=new FunCommodity();
        String id = UuidUtils.creatUUID();
        funCommoditys.setId(id);
        funCommoditys.setSupplierId(funCommodity.getSupplierId());//关联商家id
        funCommoditys.setCommodityName(funCommodity.getCommodityName());//商品名称
        funCommoditys.setCommodityLitimg(funCommodity.getCommodityLitimg());//商品缩略图
        funCommoditys.setCommodityImg(funCommodity.getCommodityImg());//商品主图
        funCommoditys.setCommodityAttributeState(funCommodity.getCommodityAttributeState());//商品属性（线上1；线下2）
        funCommoditys.setCommodityStorePrice(funCommodity.getCommodityStorePrice());//门店价
        funCommoditys.setCommoditySubscribePrice(funCommodity.getCommoditySubscribePrice());//预约价
        funCommoditys.setCommodityStore(funCommodity.getCommodityStore());//库存
        funCommoditys.setCommoditySoldTrue(funCommodity.getCommoditySoldTrue());//销量真
        funCommoditys.setCommoditySoldVirtual(funCommodity.getCommoditySoldVirtual());//销量虚拟
        funCommoditys.setCommodityDeadline(funCommodity.getCommodityDeadline());//预约截止时间
        funCommoditys.setCommodityCoin(funCommodity.getCommodityCoin());//预约动力币的币值
        funCommoditys.setCommodityNumber(funCommodity.getCommodityNumber());//用户兑换次数上线
        funCommoditys.setCommodityRole(funCommodity.getCommodityRole());//使用规则
        funCommoditys.setCommodityText(funCommodity.getCommodityText());//商品详情
        funCommoditys.setCommoditySort(funCommodity.getCommoditySort());//商品排序
        funCommoditys.setCommodityGetTime(funCommodity.getCommodityGetTime());//取货时间
        funCommoditys.setCommodityGetDeadline(funCommodity.getCommodityGetDeadline());//取货截止时间
        funCommoditys.setCommodityMemberState(funCommodity.getCommodityMemberState());//是否是会员商品。0不是；1是
        funCommoditys.setCommodityType(funCommodity.getCommodityType());//商品分类
        Integer list = commodityService.insertCommodity(funCommoditys);
        return new JsonResult(list);
    }

    //编辑商品
    @RequestMapping(value = "/updateCommodity.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object updateCommodity(@RequestBody FunCommodity funCommodity) {
        System.out.println();
        FunCommodity funCommoditys=new FunCommodity();
        funCommoditys.setId(funCommodity.getId());
        funCommoditys.setSupplierId(funCommodity.getSupplierId());//关联商家id
        funCommoditys.setCommodityName(funCommodity.getCommodityName());//商品名称
        funCommoditys.setCommodityLitimg(funCommodity.getCommodityLitimg());//商品缩略图
        funCommoditys.setCommodityImg(funCommodity.getCommodityImg());//商品主图
        funCommoditys.setCommodityAttributeState(funCommodity.getCommodityAttributeState());//商品属性（线上1；线下2）
        funCommoditys.setCommodityStorePrice(funCommodity.getCommodityStorePrice());//门店价
        funCommoditys.setCommoditySubscribePrice(funCommodity.getCommoditySubscribePrice());//预约价
        funCommoditys.setCommodityStore(funCommodity.getCommodityStore());//库存
        funCommoditys.setCommoditySoldTrue(funCommodity.getCommoditySoldTrue());//销量真
        funCommoditys.setCommoditySoldVirtual(funCommodity.getCommoditySoldVirtual());//销量虚拟
        funCommoditys.setCommodityDeadline(funCommodity.getCommodityDeadline());//预约截止时间
        funCommoditys.setCommodityCoin(funCommodity.getCommodityCoin());//预约动力币的币值
        funCommoditys.setCommodityNumber(funCommodity.getCommodityNumber());//用户兑换次数上线
        funCommoditys.setCommodityRole(funCommodity.getCommodityRole());//使用规则
        funCommoditys.setCommodityText(funCommodity.getCommodityText());//商品详情
        funCommoditys.setCommoditySort(funCommodity.getCommoditySort());//商品排序
        funCommoditys.setCommodityGetTime(funCommodity.getCommodityGetTime());//取货时间
        funCommoditys.setCommodityGetDeadline(funCommodity.getCommodityGetDeadline());//取货截止时间
        funCommoditys.setCommodityMemberState(funCommodity.getCommodityMemberState());//是否是会员商品。0不是；1是
        funCommoditys.setCommodityType(funCommodity.getCommodityType());//商品分类
        Integer list = commodityService.updateCommodity(funCommoditys);
        return new JsonResult(list);
    }

    //返回需要编辑的商品信息
    @RequestMapping(value = "/searchCommodity.fun")
    @ResponseBody
    public Object searchCommodity(String  id) {

        FunCommodity commodity = commodityService.searchCommodity(id);

        return new JsonResult(commodity);
    }
    //下架商品
    @RequestMapping(value = "/outCommodity.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object outSupplier(@RequestBody FunCommodity funCommodity) {
        String id =funCommodity.getId();
        Integer num=  commodityService.outCommodity(id);
        return new JsonResult(num);
    }
    //上架商品
    @RequestMapping(value = "/upCommodity.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object upSupplier(@RequestBody FunCommodity funCommodity) {
        String id =funCommodity.getId();
        Integer num=  commodityService.upCommodity(id);
        return new JsonResult(num);
    }

    //查找筛选订单列表
    @RequestMapping(value = "/searchCommodityOrder.fun")
    @ResponseBody
    public Object findCommodityOrder(int page, int pageSize,String schoolId , String type,String state, String nickname,
                                     String orderSn,String  startTime,String endTime) {
        if (schoolId.equals("")){
            schoolId=null;
        }if (type.equals("")){
            type=null;
        }if (state.equals("")){
            state=null;
        }if (nickname.equals("")) {
            nickname = null;
        }if (orderSn.equals("")) {
            orderSn = null;
        }if (startTime.equals("")) {
            startTime = null;
        }if (endTime.equals("")) {
            endTime = null;
        }
        /*
            类型：type 线上1，线下2
         * 加查找状态state，(待发货1，待自提2，已发货3，已核销4，已完成5，已过期6）全部，不输
         * */
        Map<String, Object> commodityMap = new HashMap<String, Object>();
        commodityMap.put("schoolId",schoolId);
        commodityMap.put("type",type);
        commodityMap.put("state",state);
        commodityMap.put("nickname",nickname);
        commodityMap.put("orderSn",orderSn);
        commodityMap.put("startTime",startTime);
        commodityMap.put("endTime",endTime);
        PageInfo<FunCommodityOrder> pager = commodityService.searchCommodityOrder(page,pageSize,commodityMap);
        /*page 第几页，pageSize 每页条数*/
        Map<String, Object> resultMap = new HashMap<String, Object>();

        //总条数
        resultMap.put("total", pager.getTotal());
        //获取每页数据
        resultMap.put("rows", pager.getList());
        return new JsonResult(resultMap);
    }
    //查找订单详情
    @RequestMapping(value = "/searchCommodityOrderById.fun")
    @ResponseBody
    public Object searchCommodityOrderById(String id) {
        FunCommodityOrder  commodity = commodityService.searchCommodityOrderById(id);

        return new JsonResult(commodity);
    }
    //返回快递
    @RequestMapping(value = "/selectLogistics.fun")
    @ResponseBody
    public Object selectLogistics() {

        List<NideshopShipping> nideshopShipping = commodityService.selectLogistics();
        return new JsonResult(nideshopShipping);
    }
    //商品发货
    @RequestMapping(value = "/sendCommodityOrder.fun", method = RequestMethod.POST)
    @ResponseBody
    public Object sendCommodityOrder(@RequestBody FunCommodityOrder funCommodityOrder) throws ParseException {
        String id=funCommodityOrder.getId();
        String orderExpressNumber=funCommodityOrder.getOrderExpressNumber();
        String orderLogistics=funCommodityOrder.getOrderLogistics();
        FunCommodityOrder funCommodityOrders=new FunCommodityOrder();
        funCommodityOrders.setId(id);
        funCommodityOrders.setOrderExpressNumber(orderExpressNumber);
        funCommodityOrders.setOrderLogistics(orderLogistics);
        //发货时间
        String str = "yyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        Date memberStartTimeDate = new Date();
        Calendar cat=Calendar.getInstance();
        cat.setTime(memberStartTimeDate);
        long memberStartTimeLong = cat.getTimeInMillis()+ 8 * 60 * 60 * 1000;
        String memberStartTimeString = sdf.format(memberStartTimeLong);
        Date sendTime = sdf.parse(memberStartTimeString);
        funCommodityOrders.setOrderSendTime(sendTime);
        Integer num = commodityService.sendCommodityOrder(funCommodityOrders);
        if(num==1){
            //修改订单状态为已发货
            commodityService.updateCommodityOrderStateSend(id);
        }
        return new JsonResult(num);
    }


}
