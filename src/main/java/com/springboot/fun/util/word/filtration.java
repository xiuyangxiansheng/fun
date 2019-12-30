package com.springboot.fun.util.word;

import com.alibaba.fastjson.JSONObject;
import com.springboot.fun.util.http.HttpClientUtil;

import java.util.HashMap;
import java.util.Map;

public class filtration {

    //	小程序的access_token

    String ACCESS_TOKEN = "";
    /**
     * 小程序敏感词过滤
     *
     * @param content 待校验的正文
     * @return 0 无敏感词，1 有敏感词，2 校验失败
     */
    public static Integer checkWords(String content) {
            Map<String, Object> accessTokenAndJsapiTicket = Singleton.getInstance().getAccessTokenAndJsapiTicket();
        String access_token = (String) accessTokenAndJsapiTicket.get("access_token");
        String url = "https://api.weixin.qq.com/wxa/msg_sec_check?access_token=" +access_token ;
        System.out.println(Singleton.getInstance().getAccessTokenAndJsapiTicket());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("content", content);
        String result = HttpClientUtil.sendPost(url, JSONObject.toJSONString(map));
        System.out.println("敏感词校验结果：" + result);
        /*Map<String, Object> resultMap = JSONObject.parseObject(result, Map.class);*/
        Map<String, Object> resultMap = JSONObject.parseObject(result, Map.class);
        if (resultMap.containsKey("errcode") && resultMap.get("errcode").toString().equals("0")) {
            return 0;
        }
        if (resultMap.get("errcode").toString().equals("42001")) {
            System.out.println("小程序accessToken过期");
            return 2;
        }
        return 1;
    }
}
