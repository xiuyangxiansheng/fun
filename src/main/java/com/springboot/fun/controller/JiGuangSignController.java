package com.springboot.fun.controller;


import com.springboot.fun.util.JsonResult;
import com.springboot.fun.util.RandomStrUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.alibaba.druid.util.Utils.md5;
@CrossOrigin(origins = "*",maxAge = 3600)
@Controller
@RequestMapping(value = "/fun")
public class JiGuangSignController {
    /*极光签名*/
    @RequestMapping(value = "/findSignature.fun")
    @ResponseBody
    public Object findAllCommodityType() {
        String appkey = "048000c77e38b1ce6272c9df";
        String random_str = RandomStrUtil.generateRandomString(25);
        String timestamp = String.valueOf(Calendar.getInstance().getTimeInMillis());
        String signature = md5("appkey=048000c77e38b1ce6272c9df&timestamp=" + timestamp + "&random_str=" + random_str + "&key=d1e27d58eea80e5f0145b8b6");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("appkey", appkey);
        resultMap.put("random_str", random_str);
        resultMap.put("timestamp", timestamp);
        resultMap.put("signature", signature);
        //获取历史消息
        return new JsonResult(resultMap);
    }


}