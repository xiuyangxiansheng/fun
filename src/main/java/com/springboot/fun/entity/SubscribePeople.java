package com.springboot.fun.entity;

public class SubscribePeople {
    /*
    * 预约人数
    * */

    private String commodityUrl;//商品头像

    public SubscribePeople() {
    }

    public String getCommodityUrl() {
        return commodityUrl;
    }

    public void setCommodityUrl(String commodityUrl) {
        this.commodityUrl = commodityUrl;
    }

    @Override
    public String toString() {
        return "SubscribePeople{" +
                "commodityUrl='" + commodityUrl + '\'' +
                '}';
    }
}
