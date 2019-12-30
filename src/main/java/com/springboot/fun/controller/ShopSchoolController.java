package com.springboot.fun.controller;
import com.github.pagehelper.PageInfo;
import com.springboot.fun.entity.NideShopSupplier;
import com.springboot.fun.entity.NideshopSupplierLabel;
import com.springboot.fun.entity.SchoolOrder;
import com.springboot.fun.entity.ShopSchool;
import com.springboot.fun.service.ShopSchoolService;
import com.springboot.fun.util.JsonResult;
import com.springboot.fun.util.RandomImgName;
import com.springboot.fun.util.RandomNumber;
import com.springboot.fun.util.UuidUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/*校园福利*/

@CrossOrigin(origins = "*",maxAge = 3600)
@Controller
@RequestMapping(value = "/schoolShop")
public class ShopSchoolController {
    @Autowired
    private ShopSchoolService shopSchoolService;
    /*
     * 小程序端
     *
     * */

    //获取校园福利首页列表--根据兑换量排序
    @RequestMapping(value = "/finSchoolShopConvertList.fun")
    @ResponseBody
    public Object finSchoolShopConvertList(int page, int pageSize) {

        //*page 第几页，pageSize 每页条数*//*
        Map<String, Object> resultMap = new HashMap<String, Object>();
        PageInfo<ShopSchool> pager = shopSchoolService.finSchoolShopConvertList(page,pageSize);
        //总条数
        resultMap.put("total", pager.getTotal());
        //获取每页数据
        resultMap.put("rows", pager.getList());
        return new JsonResult(resultMap);
    }
    //获取校园福利列表
    @RequestMapping(value = "/finSchoolShopList.fun")
    @ResponseBody
    public Object finSchoolShopList(int page, int pageSize) {

        /*page 第几页，pageSize 每页条数*/
        Map<String, Object> resultMap = new HashMap<String, Object>();
        PageInfo<ShopSchool> pager = shopSchoolService.finSchoolShopList(page,pageSize);
        //总条数
        resultMap.put("total", pager.getTotal());
        //获取每页数据
        resultMap.put("rows", pager.getList());
        return new JsonResult(resultMap);
    }
    //根据商品id查看校园福利商品详情
    @RequestMapping(value = "/findShopSchoolById.fun")
    @ResponseBody
    public Object findShopSchoolById(String id) {

        List<ShopSchool> shopSchool = shopSchoolService.findShopSchoolById(id);

        return new JsonResult(shopSchool);
    }
    //根据商家id查看校园福利商家详情
    @RequestMapping(value = "/findSchoolSupplierById.fun")
    @ResponseBody
    public Object findSchoolSupplierById(String id) {

        List<NideShopSupplier> shopSchoolSupplier = shopSchoolService.findSchoolSupplierById(id);
        //浏览量真实都加1
        shopSchoolService.updateBrowseNum(id);
        return new JsonResult(shopSchoolSupplier);
    }

    //根据商品id查看商家活动（商家标签）
    @RequestMapping(value = "/findSchoolSupplierLabelById.fun")
    @ResponseBody
    public Object findSchoolSupplierLabelById(String id) {

        List<NideshopSupplierLabel> shopSchoolSupplierLabel = shopSchoolService.findSchoolSupplierLabelById(id);

        return new JsonResult(shopSchoolSupplierLabel);
    }
    //根据id获取商家其他商品列表
    @RequestMapping(value = "/finSchoolShopSupplierList.fun")
    @ResponseBody
    public Object finSchoolShopSupplierList(int page, int pageSize,String id) {

        /*page 第几页，pageSize 每页条数*/
        Map<String, Object> resultMap = new HashMap<String, Object>();
        PageInfo<ShopSchool> pager = shopSchoolService.finSchoolShopSupplierList(page,pageSize,id);
        //总条数
        resultMap.put("total", pager.getTotal());
        //获取每页数据
        resultMap.put("rows", pager.getList());
        return new JsonResult(resultMap);
    }
    /*
    * 立即兑换
    * */
    //查找是否以前兑换过(次数+时间限制)，查找动力币是否足够  减库存， 减去动力币， 生成订单，动力币明细
    @RequestMapping(value = "/insertCoinOrder.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object insertUsedComment(@RequestBody SchoolOrder schoolOrder , HttpServletRequest request) {
        String id = UuidUtils.creatUUID();//主键
        String  orderSupplierId=schoolOrder.getOrderSupplierId();//商家关联id
        String  orderSchoolshopId=schoolOrder.getOrderSchoolshopId();//商品关联id
        String userId=request.getHeader("userId");//用户id
        String orderSupplierName=schoolOrder.getOrderSupplierName();//商家名称
        String orderSupplierUrl=schoolOrder.getOrderSupplierUrl();//商家图片
        String orderSupplierPhone=schoolOrder.getOrderSupplierPhone();//商家电话
        String orderSchoolshopName=schoolOrder.getOrderSchoolshopName();//商品名称
        String orderSchoolshopUrl=schoolOrder.getOrderSchoolshopUrl();//商品图片
        String orderSchoolshopAddress=schoolOrder.getOrderSchoolshopAddress();//商家地址
        String orderSerialNumber="f"+RandomImgName.getOrderIdByUUId();//订单编号
        //生成不会重复的6位自增码
        String orderCodeNumber="f"+RandomNumber.random(6);
        //查找核销码有没有重复，有，就再次生成
        String codeNumber = shopSchoolService.findCodeNumber(orderCodeNumber);
        while (codeNumber != null){
            orderCodeNumber="f"+RandomNumber.random(6);
        }
        Integer orderNumber=schoolOrder.getOrderNumber();//数量
        BigDecimal orderCoin=schoolOrder.getOrderCoin();//动力币
        BigDecimal orderOriginalCost=schoolOrder.getOrderOriginalCost();//原价
        BigDecimal orderCurrentPrice=schoolOrder.getOrderCurrentPrice();//折后价

        //查找用户是否满足兑换条件(查找是否以前兑换过(次数+时间限制))--
            //1，查找用户兑换的次数，和商家的次数对比,2，上一次兑换这件商品的时间加上商家的间隔时间，是否大于当前时间
        //查找用户兑换了多少次
        Map<Object, Object> userConvertNumMap = new HashMap<Object, Object>();
        userConvertNumMap.put("userId", userId);
        userConvertNumMap.put("orderSchoolshopId", orderSchoolshopId);
        Integer userConvertNum=shopSchoolService.selectUserConvertNum(userConvertNumMap);
        //查找上一次兑换这件商品的时间
        //查找商家详情
        ShopSchool shopSchool=shopSchoolService.selectShopSchool(orderSupplierId);
        Integer schoolNumber=shopSchool.getSchoolNumber();
        Integer schoolTime=shopSchool.getSchoolTime();
        System.out.println("eeeeee"+schoolNumber+schoolTime);
        Map<Object, Object> userLastConverTimeMap = new HashMap<Object, Object>();
        userLastConverTimeMap.put("userId", userId);
        userLastConverTimeMap.put("orderSchoolshopId", orderSchoolshopId);
        userLastConverTimeMap.put("schoolNumber", schoolNumber);
        userLastConverTimeMap.put("schoolTime", schoolTime);
        Date userLastConverTime =shopSchoolService.selectUserLastConverTime(userLastConverTimeMap);
        if(userLastConverTime==null){
            userLastConverTime=new Date();
        }
        System.out.println(userLastConverTime);
        userLastConverTime.setTime(userLastConverTime.getTime() - 8*60*60*1000);

       /* //转换时区
        DateFormat cstFormat = new SimpleDateFormat();
        DateFormat gmtFormat = new SimpleDateFormat();
        TimeZone gmtTime = TimeZone.getTimeZone("GMT");
        TimeZone cstTime = TimeZone.getTimeZone("CST");
        cstFormat.setTimeZone(gmtTime);
        gmtFormat.setTimeZone(cstTime);
        System.out.println("GMT Time: " + cstFormat.format(userLastConverTime));
        String time=cstFormat.format(userLastConverTime);*/

        System.out.println(userLastConverTime);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(userLastConverTime));
        //满足条件，可以兑换
        Integer num = null;
        System.out.println(userConvertNum<schoolNumber);
        System.out.println("555"+(new Date()).compareTo(userLastConverTime));
        int state = 1;

        if(userConvertNum<schoolNumber && (new Date()).compareTo(userLastConverTime)>0){
            //查找用户动力币是否足够
            BigDecimal userCoin=shopSchoolService.selectUserCoin(userId);
            System.out.println("userCoin"+userCoin);
            //查找库存是否足够
            Integer storeNum=shopSchoolService.selectStore(orderSchoolshopId);
            System.out.println(storeNum);

            if (orderCoin.compareTo(userCoin) ==1 || storeNum<=0) {
                state = 0;//不能兑换
                Map<Object, Object> map = new HashMap<Object, Object>();
                map.put("state", state);
                JSONObject userCoinState = JSONObject.fromObject(map);
                return new JsonResult(userCoinState);
            }else {
                //减库存
                shopSchoolService.updateShopSchoolStore(orderSchoolshopId);
                //加销量
                shopSchoolService.updateShopSchoolSold(orderSchoolshopId);
                //减动力币
                Map<String, Object> coinMap = new HashMap<String, Object>();
                coinMap.put("userId", userId);
                coinMap.put("orderCoin", orderCoin);
                shopSchoolService.updateUserCoin(coinMap);
                //生成订单
                num= shopSchoolService.insertShopSchoolOrder(id,orderSupplierId,orderSchoolshopId,userId,orderSupplierName,orderSupplierUrl,
                        orderSupplierPhone,orderNumber,orderSchoolshopName, orderSchoolshopUrl,
                        orderSchoolshopAddress,orderSerialNumber,orderCodeNumber, orderNumber, orderCoin, orderOriginalCost,orderCurrentPrice);
                //生成动力币明细
                Map<String, Object> coinInfMap = new HashMap<String, Object>();
                BigDecimal balance=userCoin.subtract(orderCoin);
                String coinId = UuidUtils.creatUUID();//主键
                coinInfMap.put("id", coinId);
                coinInfMap.put("userId", userId);
                coinInfMap.put("balance", balance);
                coinInfMap.put("orderCoin", orderCoin);
                coinInfMap.put("state", 2);
                coinInfMap.put("type", 11);//兑换商品
                coinInfMap.put("orderId", id);
                coinInfMap.put("remark", "兑换校园福利商品");
             Integer coinInf=shopSchoolService.insertCoinInf(coinInfMap);
            }

        }
        else{
            state = 0;//不能兑换
            Map<Object, Object> map = new HashMap<Object, Object>();
            map.put("state", state);
            JSONObject userCoinState = JSONObject.fromObject(map);
            return new JsonResult(userCoinState);
        }
        return new JsonResult(num);
        }

    //根据类型获取校园福利订单列表
    @RequestMapping(value = "/finSchoolShopOrderList.fun")
    @ResponseBody
    public Object finSchoolShopOrderList(int page, int pageSize,int type) {

        /*page 第几页，pageSize 每页条数*/
        Map<String, Object> resultMap = new HashMap<String, Object>();
        PageInfo<SchoolOrder> pager = shopSchoolService.finSchoolShopOrderList(page,pageSize,type);
        //总条数
        resultMap.put("total", pager.getTotal());
        //获取每页数据
        resultMap.put("rows", pager.getList());
        return new JsonResult(resultMap);
    }
    //根据商品id查看校园福利订单详情
    @RequestMapping(value = "/finSchoolShopOrderById.fun")
    @ResponseBody
    public Object finSchoolShopOrderById(String id) {

        List<SchoolOrder> shopSchoolOrder = shopSchoolService.finSchoolShopOrderById(id);

        return new JsonResult(shopSchoolOrder);
    }
    }

