package com.springboot.fun.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.PageInfo;
import com.springboot.fun.entity.*;
import com.springboot.fun.service.AppletLoginService;
import com.springboot.fun.service.CodeService;
import com.springboot.fun.util.JsonResult;
import com.springboot.fun.util.UuidUtils;
import com.springboot.fun.util.http.WeChatLogin;
import com.springboot.fun.util.word.Singleton;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@CrossOrigin(origins = "*",maxAge = 3600)
@Controller
@RequestMapping(value = "/code")
public class CodeController {

    @Autowired
    private CodeService codeService;
    @Autowired
    private AppletLoginService appletLoginService;
    /*
    * 小程序端(核销相关)
    *
    * */
    /*获取二维码---用这个*/
    @PostMapping(value = "/findWXQR.fun")
    @ResponseBody
    public Object findWXQR(@RequestBody Code code) {
        String page=code.getPage();
        String scene=code.getScene();
        //获取小程序的token，直接用单例模式替换
/*        String url_1 = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + "wx26b767a5139f9fbb"
                + "&secret=" + "c6897fd33cee0a54a689698fe25eb189";
        System.out.println(url_1 + "88888");
        JSONObject jsonObject = WeChatLogin.doGet(url_1);
        System.out.println(jsonObject + "999999");

         String access_tokens = jsonObject.getString("access_token");
        System.out.println("dddddd" + access_tokens);*/
        String qrcodeUrl = "https://api.weixin.qq.com/wxa/getwxacodeunlimit";  // 获取小程序码的接口头部
        Map<String, Object> accessTokenAndJsapiTicket = Singleton.getInstance().getAccessTokenAndJsapiTicket();
        String access_token = (String) accessTokenAndJsapiTicket.get("access_token");
        String url = qrcodeUrl + "?access_token=" + access_token;  // 拼接完整的URl
        Map<String, Object> requestParam = new HashMap<String, Object>();  // 小程序的参数可查看官方文档
        requestParam.put("page",page );  // 扫码后需要跳转的页面
        requestParam.put("scene",scene );  // 携带的参数
        String param = JSON.toJSONString(requestParam);
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
    //扫一扫二维码，识别出来这个参数，查看参数返回的值  eg id:89cca2804fa7da18f9cf6e72ccb4afd1
    // 1,扫描的人是否有权限，是不是这个商家的管理员。如果是，查看这个商品，返回商品信息（是否是待核销的，如果是，核销成功)
    @RequestMapping(value = "/findWXQRInf.fun")
    @ResponseBody
    public Object findUsedCommentById(String scene,HttpServletRequest request) {
            String userId=request.getHeader("userId");//用户id
            /*String orderNoWhich=scene.substring(0,1);*/
        FunCommodityOrder funCommodityOrder = null;
        SupplierEmployeeEntity supplierEmployeeEntity=null;
        WxUser wxUser1=null;
        net.sf.json.JSONObject commodity = null;
            //根据这个订单号，查到商品订单信息
                  funCommodityOrder=codeService.selectCommodityOrderByOrderNo(scene);
         if(funCommodityOrder!=null) {
             String id = funCommodityOrder.getUserId();
             wxUser1 = appletLoginService.selectByUserId(id);
             //根据用户id，和商家id，去员工表里面，查询这个扫描用户的信息
             Map<String, Object> employeeMap = new HashMap<String, Object>();
             employeeMap.put("userId", userId);
             employeeMap.put("supplierId", funCommodityOrder.getSupplierId());
             supplierEmployeeEntity = codeService.selectEmployeeByUserId(employeeMap);
             if (supplierEmployeeEntity != null) {
                 if (funCommodityOrder.getOrderState()!=4) {
                     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                     Map<String, Object> schoolMap = new HashMap<String, Object>();
                     schoolMap.put("pname", funCommodityOrder.getOrderCommodityName());//商品名称
                     schoolMap.put("pnameId", funCommodityOrder.getCommodityId());//商品id
                     schoolMap.put("img", funCommodityOrder.getOrderCommodityImg());//商品图片
                     schoolMap.put("orderNumber", funCommodityOrder.getOrderNumber());//数量
                     schoolMap.put("price", funCommodityOrder.getOrderCoin());//动力币（商品价格）
                     schoolMap.put("supplierId", funCommodityOrder.getSupplierId());//店铺id
                     schoolMap.put("supplierName", funCommodityOrder.getOrderSupplierName());//店铺
                     schoolMap.put("orderId", funCommodityOrder.getId());//订单主键
                     schoolMap.put("orderNo", funCommodityOrder.getOrderSerialNumber());//订单号
                     schoolMap.put("orderCode", funCommodityOrder.getOrderCode());//核销码
                     String payTime = simpleDateFormat.format(funCommodityOrder.getOrderPayTime().getTime());
                     schoolMap.put("orderPayTime", payTime);//下单时间
                     /*coinMap.put("orderNumber", schoolOrder.getOrderNumber());//申请时间*/
                     schoolMap.put("applyName", wxUser1.getNickname());//申请用户
                     schoolMap.put("customerId", supplierEmployeeEntity.getCustomerId());//核销人id
                     schoolMap.put("customerName", supplierEmployeeEntity.getNickname());//核销人
                     schoolMap.put("customerUrl", supplierEmployeeEntity.getWximgurl());//核销人头像
                     commodity = net.sf.json.JSONObject.fromObject(schoolMap);
                 } else {
                     //已经核销过了
                     return new JsonResult(3);
                 }
             } else {
                 //没有权限
                 return new JsonResult(2);
             }
             return new JsonResult(commodity);
         }else {
              //订单不存在
             return new JsonResult(4);
         }
    }

    // 快捷核销
    @RequestMapping(value = "/findWXQRInfByCode.fun")
    @ResponseBody
    public Object findWXQRInfByCode(String scene,HttpServletRequest request) {
        String userId=request.getHeader("userId");//用户id
        FunCommodityOrder funCommodityOrder = null;
        SupplierEmployeeEntity supplierEmployeeEntity=null;
        WxUser wxUser1=null;
        net.sf.json.JSONObject commodity = null;
        //根据这个订单号，查到商品订单信息
        funCommodityOrder=codeService.selectCommodityOrderByOrderCode(scene);
        if(funCommodityOrder!=null) {
            String id = funCommodityOrder.getUserId();
            wxUser1 = appletLoginService.selectByUserId(id);
            //根据用户id，和商家id，去员工表里面，查询这个扫描用户的信息
            Map<String, Object> employeeMap = new HashMap<String, Object>();
            employeeMap.put("userId", userId);
            employeeMap.put("supplierId", funCommodityOrder.getSupplierId());
            supplierEmployeeEntity = codeService.selectEmployeeByUserId(employeeMap);
            if (supplierEmployeeEntity != null) {
                if (funCommodityOrder.getSupplierId().equals(supplierEmployeeEntity.getSupplierId())) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Map<String, Object> schoolMap = new HashMap<String, Object>();
                    schoolMap.put("pname", funCommodityOrder.getOrderCommodityName());//商品名称
                    schoolMap.put("pnameId", funCommodityOrder.getCommodityId());//商品id
                    schoolMap.put("img", funCommodityOrder.getOrderCommodityImg());//商品图片
                    schoolMap.put("orderNumber", funCommodityOrder.getOrderNumber());//数量
                    schoolMap.put("price", funCommodityOrder.getOrderCoin());//动力币（商品价格）
                    schoolMap.put("supplierId", funCommodityOrder.getSupplierId());//店铺id
                    schoolMap.put("supplierName", funCommodityOrder.getOrderSupplierName());//店铺
                    schoolMap.put("orderId", funCommodityOrder.getId());//订单主键
                    schoolMap.put("orderNo", funCommodityOrder.getOrderSerialNumber());//订单号
                    schoolMap.put("orderCode", funCommodityOrder.getOrderCode());//核销码
                    String payTime = simpleDateFormat.format(funCommodityOrder.getOrderPayTime().getTime());
                    schoolMap.put("orderPayTime", payTime);//下单时间
                    /*coinMap.put("orderNumber", schoolOrder.getOrderNumber());//申请时间*/
                    schoolMap.put("applyName", wxUser1.getNickname());//申请用户
                    schoolMap.put("customerId", supplierEmployeeEntity.getCustomerId());//核销人id
                    schoolMap.put("customerName", supplierEmployeeEntity.getNickname());//核销人
                    schoolMap.put("customerUrl", supplierEmployeeEntity.getWximgurl());//核销人头像
                    commodity = net.sf.json.JSONObject.fromObject(schoolMap);
                } else {
                    //已经核销过了
                    return new JsonResult(3);
                }
            } else {
                //没有权限
                return new JsonResult(2);
            }
            return new JsonResult(commodity);
        }else{
             //没有这个订单
            return new JsonResult(4);
        }
    }

    //确认核销,生成核销表，修改订单状态为已完成
    @RequestMapping(value = "/commodityOrderSuccess.fun", method = RequestMethod.POST)
    @ResponseBody
    public Object sign(@RequestBody OrderHexiaoEntity orderHexiaoEntity) {
        String id=UuidUtils.creatUUID();//UUid
        String customerId=orderHexiaoEntity.getCustomerId();//核销人id
        String customerName=orderHexiaoEntity.getCustomerName();//核销人名称
        String customerUrl=orderHexiaoEntity.getCustomerUrl();//核销人头像
        String orderNo=orderHexiaoEntity.getOrderNo();//订单编号
        String orderCode=orderHexiaoEntity.getOrderCode();//核销码
        String orderId=orderHexiaoEntity.getOrderId();//订单主键
        String supplierName=orderHexiaoEntity.getSupplierName();//商家名称
        String supplierId=orderHexiaoEntity.getSupplierId();//商家id
        String pname=orderHexiaoEntity.getPname();//商品名称
        String pnameId=orderHexiaoEntity.getPnameId();//商品id
        BigDecimal price=orderHexiaoEntity.getPrice();//商品价格
        String img=orderHexiaoEntity.getImg();//商品图片

        OrderHexiaoEntity orderHexiao=new OrderHexiaoEntity();
        orderHexiao.setId(id);
        orderHexiao.setCustomerId(customerId);
        orderHexiao.setCustomerName(customerName);
        orderHexiao.setCustomerUrl(customerUrl);
        orderHexiao.setOrderNo(orderNo);
        orderHexiao.setOrderCode(orderCode);
        orderHexiao.setOrderId(orderId);
        orderHexiao.setSupplierName(supplierName);
        orderHexiao.setSupplierId(supplierId);
        orderHexiao.setPname(pname);
        orderHexiao.setPnameId(pnameId);
        orderHexiao.setPrice(price);
        orderHexiao.setImg(img);
        //插入到核销记录表
        Integer num=codeService.insertHexiaoInf(orderHexiao);
        //改变订单状态 已完成,修改订单核销时间
        codeService.updateCommodityOrderState(orderId);
        return new JsonResult(num);
    }
    //查看核销列表(一个人有多个商家核销身份)
    @RequestMapping(value = "/findAllSuccessList.fun")
    @ResponseBody
    public Object findAllSuccessList(int page,int pageSize,HttpServletRequest request) {
        String userId=request.getHeader("userId");//用户id
        Map<String, Object> resultMap = new HashMap<String, Object>();
        PageInfo<OrderHexiaoEntity> pager = codeService.findAllSuccessList(page,pageSize,userId);
        //总条数
        resultMap.put("total", pager.getTotal());
        //获取每页数据
        resultMap.put("rows", pager.getList());
        return new JsonResult(resultMap);
    }
    //查看核销人详情
    @RequestMapping(value = "/findAllSuccessUserList.fun")
    @ResponseBody
    public Object findAllSuccessUserList(int page,int pageSize,String pnameId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        PageInfo<OrderHexiaoEntity> pager = codeService.findAllSuccessUserList(page,pageSize,pnameId);
        //总条数
        resultMap.put("total", pager.getTotal());
        //获取每页数据
        resultMap.put("rows", pager.getList());
        return new JsonResult(resultMap);
    }
}
