package com.springboot.fun.util.word;

import com.alibaba.fastjson.JSONObject;
import com.springboot.fun.util.http.WeChatLogin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xulei
 * @description 单例设计模式 缓存 accessToken Jsapi_ticket
 */
public class Singleton {
    
    //缓存accessToken 的Map,map中包含 一个accessToken 和 缓存的时间戳
    private Map<String, String> map = new HashMap<String,String>();

    private Singleton() {
        
    }

    private static Singleton single = null;

    // 静态工厂方法
    public static Singleton getInstance() {
        if (single == null) {
            single = new Singleton();
        }
        return single;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public static Singleton getSingle() {
        return single;
    }

    public static void setSingle(Singleton single) {
        Singleton.single = single;
    }
    
    /**
     * 获取 accessToken Jsapi_ticket 已加入缓存机制
     * @param
     * @param
     * @return
     */
    public Map<String,Object> getAccessTokenAndJsapiTicket() {
        Map<String,Object> result = new HashMap<String,Object>();
        Singleton singleton = Singleton.getInstance();
        Map<String, String> map = singleton.getMap();
        String time = map.get("time");//从缓存中拿数据
        String accessToken = map.get("access_token");//从缓存中拿数据
        String jsapiticket = map.get("jsapiticket");//从缓存中拿数据
        Long nowDate = new Date().getTime();
        //这里设置过期时间 3000*1000就好了
        if (accessToken != null && time != null && nowDate - Long.parseLong(time) < 3000 * 1000) {
            System.out.println("-----从缓存读取access_token："+accessToken);
            //从缓存中拿数据为返回结果赋值
            result.put("access_token", accessToken);
            result.put("jsapiticket", jsapiticket);
        } else {
            String url_1 = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + "wx26b767a5139f9fbb"
                    + "&secret=" + "c6897fd33cee0a54a689698fe25eb189";
            System.out.println(url_1 + "88888");
            JSONObject jsonObject = WeChatLogin.doGet(url_1);
            System.out.println(jsonObject + "999999");

            String access_tokens = jsonObject.getString("access_token");

            /*Map<String,Object> info = JsdkUtil.getJsapiticket(appid, appsecret);*///实际中这里要改为你自己调用微信接口去获取accessToken和jsapiticket
            //将信息放置缓存中
            map.put("time", nowDate + "");
            map.put("access_token", access_tokens);
           /* map.put("access_token", String.valueOf(info.get("access_token")));*/
            //为返回结果赋值
            result.put("access_token", access_tokens);
           /* result.put("jsapiticket", info.get("jsapiticket"));*/
            }
        return result;
    }

}