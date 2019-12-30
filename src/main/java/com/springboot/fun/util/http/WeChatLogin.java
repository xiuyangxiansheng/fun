package com.springboot.fun.util.http;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeChatLogin {



    /* 获取code后，请求以下链接获取access_token：
     * 这里通过code换取的是一个特殊的网页授权access_token,与基础支持中的access_token（该access_token用于调用其他接口）不同
     */
    /*String url_1 = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" +
           "appid="+"wx67cb9cd7b84e3849"+
                   "&secret="+"fd916f14c83c403fbb9204aaff0ed908&" +
                   "code="+code+"&" +
            "grant_type=authorization_code";
    JSONObject jsonObject = WxAuthUtil.doGetJson(url_1);

    String openid = jsonObject.getString("openid");
    String tokentest= jsonObject.getString("access_token");*/


    //获取token
    private void getAccessToken(final String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid="+"wx67cb9cd7b84e3849"+
                "&secret="+"fd916f14c83c403fbb9204aaff0ed908&" +
                "code="+code+"&" +
                "grant_type=authorization_code";

        JSONObject jsonObject = WeChatLogin.doGet(url);
        String openid = jsonObject.getString("openid");
        String tokentest= jsonObject.getString("access_token");
    }


    //验证token是否有效
    private void yzAccessToken(final String access_tokens, final String openids){
        String url = "https://api.weixin.qq.com/sns/auth?" +
                "access_token="+access_tokens+"&" +
                "openid="+openids;
    }
    //获取用户信息
    private void getUserInfo(final String access_tokens, final String openids) {
        String url = "https://api.weixin.qq.com/sns/userinfo?" +
                "access_token=" + access_tokens + "&" +
                "openid=" + openids;
    }
    
    
    
    
    
    
    
    
    public static JSONObject doGet(String httpurl) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        JSONObject jo = null;
        try {
            // 创建远程url连接对象
            URL url = new URL(httpurl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
//            if (connection.getResponseCode() == 200) {
            is = connection.getInputStream();
            // 封装输入流is，并指定字符集
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            // 存放数据
            StringBuffer sbf = new StringBuffer();
            String temp = null;
            while ((temp = br.readLine()) != null) {
                sbf.append(temp);
                sbf.append("\r\n");
            }
            jo = JSONObject.parseObject(sbf.toString());
//            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
            connection.disconnect();// 关闭远程连接
        }
        return jo;
    }
    
   
    
}
