package com.springboot.fun.entity;

import java.util.Date;

public class PFTOrder {
    /*票付通订单*/
    private String id;//主键id
   private String ac;//	账号	string	Y
    private String pw;//	密码	string	Y
    private String  lid;//	产品 id	string	Y
    private String tid;//	门票 id	string	Y
    private String remotenum;//	远端订单号	string	Y	贵方订单号,请确保唯一
    private String tprice;//	结算价	string	Y	供应商配置的结算单价，单位：分
    private String tnum;//	购买数量	string	Y
    private String playtime;//	游玩日期	string	Y	格式：Y-m-d
    private String ordername;//	游客姓名	string	Y	多个用英文逗号隔开，不支持特殊符号：/|[]等
    private String ordertel;//	游客手机号	string	Y
    private String contactTEL;//	联系人手机号	string	Y
    private String smsSend;//	是否选择票付通发送短信	string	Y	0-票付通发送短信（下单成功只返回双方订单号）1-票付通不发送短信（ 凭证信息一起返回）
    private String paymode;//	支付方式	string	Y	0-账户余额，2-供应商授信额度，4-现场支付
    private String ordermode;//	下单方式	string	Y	默认传输 0-正常下单
    private String assembly;//	集合地点	string	Y	线路时需要，参数必传，值可传输空
    private String series;//	团号	string	Y	线路，演出时需要， 参数必传，值可传输空；演出需要时传输格 式 ： json_encode(array(int) 场 馆 id,(int) 场 次id,(string)分区 id));
    private String concatID;//	联票 id	string	Y	（未开放，请填 0）
    private String pCode;//	套票 id	string	Y	（未开放，请填 0）
    private String m;//	供应商 id	string	Y
    private String personID;//	身份证号	string	Y
    private String memo;//	备注	string	Y	参数必传，值可为空 传输
    private String callbackUrl;//	回调地址	string	Y	参数必传，值可为空传输
    private Date  createTime;//创建时间
    private Date updateTime;//更新时间
    private Integer orderState;//订单状态

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getRemotenum() {
        return remotenum;
    }

    public void setRemotenum(String remotenum) {
        this.remotenum = remotenum;
    }

    public String getTprice() {
        return tprice;
    }

    public void setTprice(String tprice) {
        this.tprice = tprice;
    }

    public String getTnum() {
        return tnum;
    }

    public void setTnum(String tnum) {
        this.tnum = tnum;
    }

    public String getPlaytime() {
        return playtime;
    }

    public void setPlaytime(String playtime) {
        this.playtime = playtime;
    }

    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername;
    }

    public String getOrdertel() {
        return ordertel;
    }

    public void setOrdertel(String ordertel) {
        this.ordertel = ordertel;
    }

    public String getContactTEL() {
        return contactTEL;
    }

    public void setContactTEL(String contactTEL) {
        this.contactTEL = contactTEL;
    }

    public String getSmsSend() {
        return smsSend;
    }

    public void setSmsSend(String smsSend) {
        this.smsSend = smsSend;
    }

    public String getPaymode() {
        return paymode;
    }

    public void setPaymode(String paymode) {
        this.paymode = paymode;
    }

    public String getOrdermode() {
        return ordermode;
    }

    public void setOrdermode(String ordermode) {
        this.ordermode = ordermode;
    }

    public String getAssembly() {
        return assembly;
    }

    public void setAssembly(String assembly) {
        this.assembly = assembly;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getConcatID() {
        return concatID;
    }

    public void setConcatID(String concatID) {
        this.concatID = concatID;
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public PFTOrder() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
