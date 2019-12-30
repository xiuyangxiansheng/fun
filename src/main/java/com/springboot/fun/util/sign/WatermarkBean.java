package com.springboot.fun.util.sign;

/**
 * @Author: lvliangjun
 * @Date: 2019/8/5 15:55
 */
public class WatermarkBean {
    /**
     * appid : wx26b767a5139f9fbb
     * timestamp : 1564990762
     */

    private String appid;
    private String timestamp;

    public WatermarkBean() {
    }

    /**
     * appid : wx26b767a5139f9fbb
     * timestamp : 1564990762
     */



    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "WatermarkBean{" +
                "appid='" + appid + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
