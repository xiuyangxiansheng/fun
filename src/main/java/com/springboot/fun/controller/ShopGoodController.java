package com.springboot.fun.controller;
import com.github.pagehelper.PageInfo;
import java.util.Map;

/*好物兑换*/

import com.springboot.fun.entity.*;
import com.springboot.fun.service.ShopGoodService;
import com.springboot.fun.util.JsonResult;
import com.springboot.fun.util.RandomImgName;
import com.springboot.fun.util.UuidUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
@CrossOrigin(origins = "*",maxAge = 3600)
@Controller
@RequestMapping(value = "/goodShop")
public class ShopGoodController {
    @Autowired
    private ShopGoodService shopGoodService;
    /*
     * 小程序端
     *
     * */

    //获取好物兑换分类
    @RequestMapping(value = "/findShopGoodType.fun")
    @ResponseBody
    public Object findShopGoodType() {
        //获取好物兑换分类
        List<ShopGoodType> shopGoodTypes=shopGoodService.findShopGoodType();
        System.out.println(shopGoodTypes);
        return new JsonResult(shopGoodTypes);
    }
    //获取好物兑换列表
    @RequestMapping(value = "/finGoodShopList.fun")
    @ResponseBody
    public Object finGoodShopList(int page, int pageSize, int type) {

        /*page 第几页，pageSize 每页条数*/
        Map<String, Object> resultMap = new HashMap<String, Object>();
        PageInfo<ShopGood> pager = shopGoodService.finGoodShopList(page,pageSize,type);
        //总条数
        resultMap.put("total", pager.getTotal());
        //获取每页数据
        resultMap.put("rows", pager.getList());
        return new JsonResult(resultMap);
    }
    //根据商品id查看好物兑换商品详情
    @RequestMapping(value = "/findShopGoodById.fun")
    @ResponseBody
    public Object findShopGoodById(String id) {

        List<ShopGood> shopGoodList = shopGoodService.findShopGoodById(id);

        return new JsonResult(shopGoodList);
    }
    /*
    * 立即兑换
    * */
    //查找是否以前兑换过(次数+时间限制)--好物兑换不限次数，查找动力币是否足够  减库存， 减去动力币， 生成订单，销量加一，动力币明细
    @RequestMapping(value = "/insertCoinOrder.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object insertUsedComment(@RequestBody GoodOrder goodOrder , HttpServletRequest request) {
        String id = UuidUtils.creatUUID();//主键
        String  orderGoodId=goodOrder.getOrderGoodId();//商品关联id
        String userId=request.getHeader("userId");//用户id
        String orderName=goodOrder.getOrderName();//商品名称
        String orderLitimg=goodOrder.getOrderLitimg();//商品图片
        String orderSerialNumber=RandomImgName.getOrderIdByUUId();//订单编号
        Integer orderNumber=goodOrder.getOrderNumber();//数量
        String orderMessage=goodOrder.getOrderMessage();//留言
        BigDecimal orderCoin=goodOrder.getOrderCoin();//动力币
        Integer orderTypeNum=goodOrder.getOrderTypeNum();//类型编号
        String orderType=goodOrder.getOrderType();//类型名称
        String orderMemberName=goodOrder.getOrderMemberName();//姓名
        String orderMemberPhone=goodOrder.getOrderMemberPhone();//电话
        String orderMemberAddress=goodOrder.getOrderMemberAddress();//地址
        //查找用户动力币是否足够
        BigDecimal userCoin=shopGoodService.selectUserCoin(userId);
        //查找库存是否足够
        Integer storeNum=shopGoodService.selectStore(orderGoodId);
        int state = 1;
        Integer num;
        if (orderCoin.compareTo(userCoin) == 1 || storeNum<=0) {
            state = 0;//不能兑换
            Map<Object, Object> map = new HashMap<Object, Object>();
            map.put("state", state);
            JSONObject userCoinState = JSONObject.fromObject(map);
            return new JsonResult(userCoinState);
        }else {
            //减库存
            shopGoodService.updateShopGoodStore(orderGoodId);
            //减动力币
            Map<String, Object> coinMap = new HashMap<String, Object>();
            coinMap.put("userId", userId);
            coinMap.put("orderCoin", orderCoin);
            shopGoodService.updateUserCoin(coinMap);
            //生成订单
            num= shopGoodService.insertShopGoodOrder(id,orderGoodId,userId,orderName,orderLitimg,orderSerialNumber,orderNumber,orderMessage, orderCoin,
                    orderTypeNum,orderType, orderMemberName, orderMemberPhone, orderMemberAddress);
            //已兑换（真实，虚拟加1）
            shopGoodService.updateShopGoodCash(orderGoodId);

            //查找用户动力币的奖励规则
            //生成动力币明细
            Map<String, Object> coinInfMap = new HashMap<String, Object>();
            BigDecimal balance=userCoin.subtract(orderCoin);
            String coinId = UuidUtils.creatUUID();//主键
            coinInfMap.put("id", coinId);
            coinInfMap.put("userId", userId);
            coinInfMap.put("balance", balance);
            coinInfMap.put("orderCoin", orderCoin);
            coinInfMap.put("state", 2);
            coinInfMap.put("type", 5);//兑换商品
            coinInfMap.put("orderId", id);
            coinInfMap.put("remark", "兑换好物兑换商品");
            shopGoodService.insertCoinInf(coinInfMap);
        }
        return new JsonResult(num);
    }
    //获取好物兑换订单列表
    @RequestMapping(value = "/finGoodShopOrderList.fun")
    @ResponseBody
    public Object finGoodShopOrderList(int page, int pageSize, int type) {
        /*
         * 加查找状态，状态为0 全部
         *           状态为1 待发货订单
         *           状态为2 待收货订单
         *           状态为3 已完成订单
         * */
        PageInfo<GoodOrder> pager=null;
        if(type==0){
            pager = shopGoodService.finGoodShopOrderAllList(page,pageSize,type);
        }else{
            pager = shopGoodService.finGoodShopOrderList(page,pageSize,type);
        }

        /*page 第几页，pageSize 每页条数*/
        Map<String, Object> resultMap = new HashMap<String, Object>();

        //总条数
        resultMap.put("total", pager.getTotal());
        //获取每页数据
        resultMap.put("rows", pager.getList());
        return new JsonResult(resultMap);
    }
    //根据商品id查看好物兑换订单详情
    @RequestMapping(value = "/finGoodShopOrderById.fun")
    @ResponseBody
    public Object finGoodShopOrderById(String id) {

        List<GoodOrder> shopGoodOrder = shopGoodService.finGoodShopOrderById(id);

        return new JsonResult(shopGoodOrder);
    }

    //确认收货
    @RequestMapping(value = "/receiveGoodShop.fun", method = RequestMethod.POST)
    @ResponseBody
    public Object receiveGoodShop(@RequestBody GoodOrder goodOrder) {
        String id=goodOrder.getId();
        Integer num = shopGoodService.receiveGoodShop(id);
        return new JsonResult(num);
    }

    //核销列表
    @RequestMapping(value = "/findCheckList.fun")
    @ResponseBody
    public Object findCheckList() {
        //获取核销列表
        List<ShopGoodType> shopGoodTypes=shopGoodService.findShopGoodType();
        System.out.println(shopGoodTypes);
        return new JsonResult(shopGoodTypes);
    }

    //分享商品
    @RequestMapping(value = "/shareGoodShop.fun", method = RequestMethod.POST)
    @ResponseBody
    public Object ShareGoodShop(@RequestBody NideShopSupplier nideShopSupplier) {
        String id=nideShopSupplier.getId();
        Integer num = shopGoodService.shareGoodShop(id);
        return new JsonResult(num);
    }
    //根据商品id，商品名称模糊搜索好物兑换商品
    @RequestMapping(value = "/searchShopGoodById.fun")
    @ResponseBody
    public Object searchShopGoodById(String search) {

        List<ShopGood> shopGoodList = shopGoodService.searchShopGoodById(search);

        return new JsonResult(shopGoodList);
    }
}
