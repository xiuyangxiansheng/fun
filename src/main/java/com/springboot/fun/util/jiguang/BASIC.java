package com.springboot.fun.util.jiguang;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BASIC {
public static void main(String[] args) throws ClientProtocolException, IOException {
//认证信息对象，用于包含访问翻译服务的用户名和密码
String path ="https://report.im.jpush.cn/v2/users/li123/messages?count=100&begin_time=2019-10-19%2008:10:10&end_time=2019-10-25%2013:10:12";
//1.创建客户端访问服务器的httpclient对象 打开浏览器
HttpClient httpclient = new DefaultHttpClient();
//2.以请求的连接地址创建get请求对象
HttpGet httpget = new HttpGet(path);
//appKey:masterSecret—>appKey，masterSecret,并使用base64进行加密，将加密的字节信息转化为string类型，encoding—>token
String encoding = DatatypeConverter.printBase64Binary("048000c77e38b1ce6272c9df:d1e27d58eea80e5f0145b8b6".getBytes("UTF-8"));
httpget.setHeader("Authorization", "Basic "+encoding);
//3.向服务器端发送请求 并且获取响应对象 浏览器中输入网址点击回车
HttpResponse response = httpclient.execute(httpget);
//4.获取响应对象中的响应码
StatusLine statusLine = response.getStatusLine();//获取请求对象中的响应行对象
int responseCode = statusLine.getStatusCode();//从状态行中获取状态码
System.out.println(responseCode);
if (responseCode == 200) {
//5. 可以接收和发送消息
HttpEntity entity = response.getEntity();
//6.从消息载体对象中获取操作的读取流对象
InputStream input = entity.getContent();
BufferedReader br = new BufferedReader(new InputStreamReader(input));
String str1 = br.readLine();
String result = new String(str1.getBytes("utf-8"), "utf-8");
System.out.println("服务器的响应是:" + result);
br.close();
input.close();
} else {
System.out.println("响应失败!");
}
}
}