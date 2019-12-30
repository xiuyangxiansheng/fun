package com.springboot.fun.util.applet;

public interface UserConstantInterface {
	// 请求的网址
    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";
	// 你的appid
    public static final String WX_LOGIN_APPID = "wx26b767a5139f9fbb";
	// 你的密匙
    public static final String WX_LOGIN_SECRET = "c6897fd33cee0a54a689698fe25eb189";
	// 固定参数
    public static final String WX_LOGIN_GRANT_TYPE = "authorization_code";

}
