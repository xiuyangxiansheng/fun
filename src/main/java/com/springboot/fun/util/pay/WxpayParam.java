package com.springboot.fun.util.pay;

import com.springboot.fun.util.oss.RandomImgName;

import java.math.BigDecimal;

public class WxpayParam {
    
    /** 微信支付的金额是String类型 并且是以分为单位
     * 下面举个例子单位是元是怎么转为分的
     * */
    BigDecimal totalPrice  = new BigDecimal(1); //此时的单位是元
    
    private String body = "xxx等商品信息";
    private String totalFee = totalPrice.multiply(new BigDecimal(100)).toBigInteger().toString();
    /** 随机数字字符串*/
    private String outTradeNo = new RandomImgName().getOrderIdByUUId();
    
    
    
    
    
    
    // 短信应用SDK AppID
    public static int appid = 1400179343; // 1400开头
    
    // 短信应用SDK AppKey
    public static String appkey = "4bad592fe588f5f17b04bb96046e406b";
    
    
    //小程序appId
    public static String appletId = "wxef59696cb8cde799";
    
    //小程序秘钥
    public static String appletSecret = "3021b4c3849581e3e18a92e9b1a811c0";
    
    // 需要发送短信的手机号码
    public static String[] phoneNumbers = {"21212313123", "12345678902", "12345678903"};
    
    // 短信模板ID，需要在短信应用中申请
    public static int templateId = 291755; // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请
    //templateId7839对应的内容是"您的验证码是: {1}"
    // 签名
    public static String smsSign = "抠门兔直播购"; // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台申请。
    
    
    
    
    
    public String getBody() {
        return body;
    }
    
    public void setBody(String body) {
        this.body = body;
    }
    
    public String getTotalFee() {
        return totalFee;
    }
    
    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }
    
    public String getOutTradeNo() {
        return outTradeNo;
    }
    
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
    
}
