package com.springboot.fun.util.jiguang;

/*import com.sun.org.apache.xml.internal.security.utils.Base64;*/
import cn.jiguang.common.utils.Base64;

import java.net.HttpURLConnection;
import java.net.URL;

/*
* 没有成功
*
* */
public class CodUtils {
    public static int connection(String address, String username, String password)
            throws Exception {
        URL url = new URL(address);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        String author = "Basic " + Base64.encode((username + ":" + password).getBytes());
        conn.setRequestProperty("Authorization", author);
       /* conn.setRequestProperty("start", "0");*/
      /*  conn.setRequestProperty("count", "100");*/

        conn.connect();
        return conn.getResponseCode();
    }

    public static void main(String[] args) {
        try {
            System.out.println(connection("https://report.im.jpush.cn/v2/users/li123/messages?count=100&begin_time=2019-10-19%2008:10:10&end_time=2019-10-25%2013:10:12", "048000c77e38b1ce6272c9df", "d1e27d58eea80e5f0145b8b6"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}