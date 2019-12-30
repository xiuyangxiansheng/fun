package com.springboot.fun.util.jiguang;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.ServiceHelper;
import cn.jiguang.common.connection.ApacheHttpClient;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jiguang.common.resp.ResponseWrapper;
import cn.jmessage.api.JMessageClient;
import cn.jmessage.api.common.model.RegisterInfo;
import cn.jmessage.api.common.model.RegisterPayload;
import cn.jmessage.api.user.UserClient;
/*
*
* 极光用户注册
* */
public class regJiguang {
	protected static final Logger LOG = LoggerFactory.getLogger(regJiguang.class);
	private static final String appKey = "048000c77e38b1ce6272c9df";
	private static final String masterSecret = "d1e27d58eea80e5f0145b8b6";
	
	//方法一:利用ApacheHttpClient代替JMessageClient进行用户的注册
	public static void regPP(String username,String password) {
		JMessageClient client = new JMessageClient(appKey, masterSecret);
		String authCode = ServiceHelper.getBasicAuthorization(appKey, masterSecret);
		ApacheHttpClient httpClient = new ApacheHttpClient(authCode, null, ClientConfig.getInstance());
		client.setHttpClient(httpClient);
		try {
            List<RegisterInfo> users = new ArrayList<RegisterInfo>();
            RegisterInfo user = RegisterInfo.newBuilder()
                    .setUsername(username)
                    .setPassword(password)
                    .build();
            users.add(user);          
            RegisterInfo[] regUsers = new RegisterInfo[users.size()];
 
            String res = client.registerUsers(users.toArray(regUsers));
            LOG.info(res);
        } catch (APIConnectionException e) {
            LOG.error("连接错误。请稍后重试。 ", e);
        } catch (APIRequestException e) {
            LOG.error("JPush服务器的错误响应。请检查并改正。", e);
            LOG.info("网络状态: " + e.getStatus());
            LOG.info("错误信息: " + e.getMessage());
        }
    }
	
	//方法二 ：利用UserClient来进行用户的注册
	public static void regPP1(String username,String password,String nikeName,String headimgurl) {
		UserClient client = new UserClient(appKey, masterSecret);
 
		try {
            List<RegisterInfo> users = new ArrayList<RegisterInfo>();
            RegisterInfo user = RegisterInfo.newBuilder()
                    .setUsername(username)
                    .setPassword(password)
                    .setNickname(nikeName)
                    .setAvatar(headimgurl)
                    .build();
            users.add(user);          
            RegisterInfo[] regUsers = new RegisterInfo[users.size()];
 
            RegisterPayload payload = RegisterPayload.newBuilder()
                    .addUsers(users.toArray(regUsers)).build();
            ResponseWrapper registerUsers = client.registerUsers(payload);
           
            System.out.println(registerUsers);
           // LOG.info(res);
        } catch (APIConnectionException e) {
            LOG.error("连接错误。请稍后重试。 ", e);
        } catch (APIRequestException e) {
            LOG.error("JPush服务器的错误响应。请检查并改正。", e);
            LOG.info("网络状态: " + e.getStatus());
            LOG.info("错误信息: " + e.getMessage());
        }
    }
	public static void main(String[] args) {
		regPP1("testAHK10","test123456","测试","888");
		
 
	}
 
}