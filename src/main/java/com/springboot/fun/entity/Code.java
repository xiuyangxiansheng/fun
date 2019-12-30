package com.springboot.fun.entity;

public class Code {
    //生成二维码
    private String page;//跳转的url
    private String scene;//携带的参数

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public Code() {
    }
}
