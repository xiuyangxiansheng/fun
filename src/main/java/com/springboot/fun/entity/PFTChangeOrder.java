package com.springboot.fun.entity;

public class PFTChangeOrder {
    //票付通修改订单、
    private String ordern;//票付通订单号
    private String num;//剩余数量
    private String ordertel;//游玩人手机号
    private String m;//预留参数

    public String getOrdern() {
        return ordern;
    }

    public void setOrdern(String ordern) {
        this.ordern = ordern;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getOrdertel() {
        return ordertel;
    }

    public void setOrdertel(String ordertel) {
        this.ordertel = ordertel;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public PFTChangeOrder() {
        super();
    }
}
