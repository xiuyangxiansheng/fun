package com.springboot.fun.util.pay;

/**
 * 微信支付属性配置表
 */
public class WeChatPayProperties {
    
    
    /**
     * 预支付请求地址
     */
    public static final String  PrepayUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    
    /**
     * 查询订单地址
     */
    public static final String  OrderUrl = "https://api.mch.weixin.qq.com/pay/orderquery";
    
    /**
     * 关闭订单地址
     */
    public static final String  CloseOrderUrl = "https://api.mch.weixin.qq.com/pay/closeorder";
    
    /**
     * 申请退款地址
     */
    public static final String  RefundUrl = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    
    /**
     * 查询退款地址
     */
    public static final String  RefundQueryUrl = "https://api.mch.weixin.qq.com/pay/refundquery";
    
    /**
     * 下载账单地址
     */
    public static final String  DownloadBillUrl = "https://api.mch.weixin.qq.com/pay/downloadbill";
    
    /**
     * 商户APPID
     */
    public static final String  AppId = "wx67cb9cd7b84e3849";
    
    /**
     * 商户账户 获取支付能力后，从邮件中得到
     */
    public static final String  MchId = "1529967961";
    
    /**
     * 商户秘钥  32位，在微信商户平台中设置 api安全下的设置密钥(不是开放平台 )  注意！！！ 注意！！！！
     */
    public static final String  AppSercret = "604969f4aab35d1eef56fabb5ab8f43e";
    
    /**
     * sign type
     */
    public static final String  Sign = "MD5";
    
    /**
     * 服务器异步通知页面路径
     */
    public static String notify_url = "http://www.koumen2.com:8080/live/pay/getWxPayNotify.live";
    
    /**
     * 页面跳转同步通知页面路径
     */
    public static String return_url = "此处为后台url";
    
    /**
     * 退款通知地址
     */
    public static String refund_notify_url = "此处为后台url";
    
    /**
     * 退款需要证书文件，证书文件的地址
     */
    public static String refund_file_path = "此处为后台url";
    
    /**
     * 商品名称
     */
    public static String subject =  "subject（自己按需要更改，也可以传值进入）";
    
    /**
     * 商品描述
     */
    public static String body = "微信支付（自己按需要更改，也可以传值进入）";
	 
	/*   private static  Properties properties;
	 
	   public static synchronized Properties getProperties(){
	      if(properties == null){
	         String path = System.getenv(RSystemConfig.KEY_WEB_HOME_CONF) + "/weichart.properties";
	         properties = PropertiesUtil.getInstance().getProperties(path);
	      }
	      return properties;
*/

}
