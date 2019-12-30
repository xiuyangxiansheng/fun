package com.springboot.fun.util.pay;

public class WXConst {

    //微信小程序appid
    public static String appId = "wx26b767a5139f9fbb";
    //微信小程序appsecret
    public static String appSecret = "c6897fd33cee0a54a689698fe25eb189";
    //微信支付主体
    public static String title = "YOU乐趣会员";
    public static String orderNo = "2019121800002001";
    //微信商户号1551060201
    public static String mch_id="1502628901";
    //微信支付的商户密钥
    public static final String key = "iwind5i2bcpmm15bxhzxpkbbdf4x3aqg";
    //获取微信Openid的请求地址
    public static String WxGetOpenIdUrl = "";
    //支付成功后的服务器回调url
    public static final String notify_url="https://api.weixin.qq.com/sns/jscode2session";
    //签名方式
    public static final String SIGNTYPE = "MD5";
    //交易类型
    public static final String TRADETYPE = "JSAPI";
    //微信统一下单接口地址
    public static final String pay_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
}
