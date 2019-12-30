package com.springboot.fun.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.fun.entity.PFTChangeOrder;
import com.springboot.fun.entity.PFTOrder;
import com.springboot.fun.service.PiaoFuTongService;
import com.springboot.fun.util.JsonResult;
import com.springboot.fun.util.RandomImgName;
import com.springboot.fun.util.UuidUtils;
import com.springboot.fun.util.piaofutong.HttpPostTest;
import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/*
* 票付通
* */
@CrossOrigin(origins = "*",maxAge = 3600)
@Controller
@RequestMapping(value = "/ticket")
public class PiaoFuTong {
    @Autowired
    private PiaoFuTongService piaoFuTongService;
/*产品列表*/
    @RequestMapping(value = "/Get_ScenicSpot_List.fun")
    @ResponseBody
    public Object selectScenicSpot(String ac,String pw,String n,String m) {
        String url = "http://open.12301dev.com/openService/pftMX.php";
        /*  HttpClientUtil.doPost(url);*/
        /* sendPost(url);*/
        String xmlInf = "<soap:Envelope xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:types=\"urn:PFTMX/encodedTypes\" xmlns:tns=\"urn:PFTMX\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + " <soap:Body soap:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"
                +        " <tns:Get_ScenicSpot_List>"
                +            "<ac>"+ac+"</ac>"
                +            " <pw>"+pw+"</pw>"
                +            "<n>"+n+"</n>"
                +            " <m>"+m+"</m>"
                +            "</tns:Get_ScenicSpot_List>"
                +            " </soap:Body>"
                +            "</soap:Envelope>";
        String xml=  new HttpPostTest().testPost(url,xmlInf);
        XMLSerializer cacheXmlSerializer = new XMLSerializer();
        JSON referJsonResult = cacheXmlSerializer.read(xml);
        JSONObject referJson = JSONObject.parseObject(referJsonResult.toString());
        System.out.println(referJson);

        String sessionkey = referJson.get("SOAP-ENV:Body").toString();
        System.out.println(sessionkey);
        int strStartIndex = sessionkey.indexOf("<Data>");
        int endStartIndex = sessionkey.indexOf("</Data>")+"</Data>".length();
        System.out.println(strStartIndex+"pp"+endStartIndex);
        String re=sessionkey.substring(strStartIndex,endStartIndex);

        re=re.replaceAll("\\\\","");
        StringEscapeUtils.unescapeJava(re);
        System.out.println(re);
        String xml2="<class id="
                + "'1'"
                + ">"+re+"</class>";
        JSON referJsonResult2 = cacheXmlSerializer.read(xml2);
        JSONObject referJson2 = JSONObject.parseObject(referJsonResult2.toString());
        System.out.println(referJson2);
        return new JsonResult(referJson2);
    }
    /*产品详情*/
    @RequestMapping(value = "/Get_ScenicSpot_Info.fun")
    @ResponseBody
    public Object selectScenicSpotInfo(String ac,String pw,String n) {
        String url = "http://open.12301dev.com/openService/pftMX.php";
        /*  HttpClientUtil.doPost(url);*/
        /* sendPost(url);*/
        String xmlInf = "<soap:Envelope xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:types=\"urn:PFTMX/encodedTypes\" xmlns:tns=\"urn:PFTMX\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + " <soap:Body soap:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"
                +        " <tns:Get_ScenicSpot_Info>"
                +            "<ac>"+ac+"</ac>"
                +            " <pw>"+pw+"</pw>"
                +            "<n>"+n+"</n>"
                +            "</tns:Get_ScenicSpot_Info>"
                +            " </soap:Body>"
                +            "</soap:Envelope>";
        String xml=  new HttpPostTest().testPost(url,xmlInf);
        XMLSerializer cacheXmlSerializer = new XMLSerializer();
        JSON referJsonResult = cacheXmlSerializer.read(xml);
        JSONObject referJson = JSONObject.parseObject(referJsonResult.toString());
        System.out.println(referJson);

        String sessionkey = referJson.get("SOAP-ENV:Body").toString();
        System.out.println(sessionkey);
        int strStartIndex = sessionkey.indexOf("<Data>");
        int endStartIndex = sessionkey.indexOf("</Data>")+"</Data>".length();
        System.out.println(strStartIndex+"pp"+endStartIndex);
        String re=sessionkey.substring(strStartIndex,endStartIndex);

        re=re.replaceAll("\\\\","");
        StringEscapeUtils.unescapeJava(re);
        System.out.println(re);
        String xml2="<class id="
                + "'1'"
                + ">"+re+"</class>";
        JSON referJsonResult2 = cacheXmlSerializer.read(xml2);
        JSONObject referJson2 = JSONObject.parseObject(referJsonResult2.toString());
        System.out.println(referJson2);
        return new JsonResult(referJson2);
    }
    /*门票列表详情*/
    @RequestMapping(value = "/Get_Ticket_List.fun")
    @ResponseBody
    public Object selectTicket(String ac,String pw,String n,String m) {
        String url = "http://open.12301dev.com/openService/pftMX.php";
        /*  HttpClientUtil.doPost(url);*/
        /* sendPost(url);*/
        String xmlInf = "<soap:Envelope xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:types=\"urn:PFTMX/encodedTypes\" xmlns:tns=\"urn:PFTMX\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + " <soap:Body soap:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"
                +        " <tns:Get_Ticket_List>"
                +            "<ac>"+ac+"</ac>"
                +            " <pw>"+pw+"</pw>"
                +            "<n>"+n+"</n>"
                +            " <m>"+m+"</m>"
                +            "</tns:Get_Ticket_List>"
                +            " </soap:Body>"
                +            "</soap:Envelope>";
        String xml=  new HttpPostTest().testPost(url,xmlInf);
        XMLSerializer cacheXmlSerializer = new XMLSerializer();
        JSON referJsonResult = cacheXmlSerializer.read(xml);
        JSONObject referJson = JSONObject.parseObject(referJsonResult.toString());
        System.out.println(referJson);

        String sessionkey = referJson.get("SOAP-ENV:Body").toString();
        System.out.println(sessionkey);
        int strStartIndex = sessionkey.indexOf("<Data>");
        int endStartIndex = sessionkey.indexOf("</Data>")+"</Data>".length();
        System.out.println(strStartIndex+"pp"+endStartIndex);
        String re=sessionkey.substring(strStartIndex,endStartIndex);

        re=re.replaceAll("\\\\","");
        StringEscapeUtils.unescapeJava(re);
        System.out.println(re);
        String xml2="<class id="
                + "'1'"
                + ">"+re+"</class>";
        JSON referJsonResult2 = cacheXmlSerializer.read(xml2);
        JSONObject referJson2 = JSONObject.parseObject(referJsonResult2.toString());
        System.out.println(referJson2);
        return new JsonResult(referJson2);
    }
    /*场次信息（演出产品类型；可选）*/
    @RequestMapping(value = "/Get_Screenings_List.fun")
    @ResponseBody
    public Object selectScreenings(String ac,String pw,String aid,String tid,String date) {
        String url = "http://open.12301dev.com/openService/pftMX.php";
        /*  HttpClientUtil.doPost(url);*/
        /* sendPost(url);*/
        String xmlInf = "<soap:Envelope xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:types=\"urn:PFTMX/encodedTypes\" xmlns:tns=\"urn:PFTMX\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + " <soap:Body soap:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"
                +        " <tns:Get_Screenings_List>"
                +            "<ac>"+ac+"</ac>"
                +            " <pw>"+pw+"</pw>"
                +            "<aid>"+aid+"</aid>"
                +            " <tid>"+tid+"</tid>"
                +            " <date>"+date+"</date>"
                +            "</tns:Get_Screenings_List>"
                +            " </soap:Body>"
                +            "</soap:Envelope>";
        String xml=  new HttpPostTest().testPost(url,xmlInf);
        XMLSerializer cacheXmlSerializer = new XMLSerializer();
        JSON referJsonResult = cacheXmlSerializer.read(xml);
        JSONObject referJson = JSONObject.parseObject(referJsonResult.toString());
        System.out.println(referJson);

        String sessionkey = referJson.get("SOAP-ENV:Body").toString();
        System.out.println(sessionkey);
        int strStartIndex = sessionkey.indexOf("<Data>");
        int endStartIndex = sessionkey.indexOf("</Data>")+"</Data>".length();
        System.out.println(strStartIndex+"pp"+endStartIndex);
        String re=sessionkey.substring(strStartIndex,endStartIndex);

        re=re.replaceAll("\\\\","");
        StringEscapeUtils.unescapeJava(re);
        System.out.println(re);
        String xml2="<class id="
                + "'1'"
                + ">"+re+"</class>";
        JSON referJsonResult2 = cacheXmlSerializer.read(xml2);
        JSONObject referJson2 = JSONObject.parseObject(referJsonResult2.toString());
        System.out.println(referJson2);
        return new JsonResult(referJson2);
    }
    /*1.2.价格（日历价格；可选）*/
    @RequestMapping(value = "/GetRealTimeStorage.fun")
    @ResponseBody
    public Object selectGetRealTimeStorage(String ac,String pw,String aid,String tid,String start_date,String  end_date) {
        String url = "http://open.12301dev.com/openService/pftMX.php";
        /*  HttpClientUtil.doPost(url);*/
        /* sendPost(url);*/
        String xmlInf = "<soap:Envelope xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:types=\"urn:PFTMX/encodedTypes\" xmlns:tns=\"urn:PFTMX\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + " <soap:Body soap:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"
                +        " <tns:Get_Screenings_List>"
                +            "<ac>"+ac+"</ac>"
                +            " <pw>"+pw+"</pw>"
                +            "<aid>"+aid+"</aid>"
                +            " <tid>"+tid+"</tid>"
                +            " <start_date>"+start_date+"</start_date>"
                +           " <end_date>"+end_date+"</end_date>"
                +            "</tns:Get_Screenings_List>"
                +            " </soap:Body>"
                +            "</soap:Envelope>";
        String xml=  new HttpPostTest().testPost(url,xmlInf);
        XMLSerializer cacheXmlSerializer = new XMLSerializer();
        JSON referJsonResult = cacheXmlSerializer.read(xml);
        JSONObject referJson = JSONObject.parseObject(referJsonResult.toString());
        System.out.println(referJson);

        String sessionkey = referJson.get("SOAP-ENV:Body").toString();
        System.out.println(sessionkey);
        int strStartIndex = sessionkey.indexOf("<Data>");
        int endStartIndex = sessionkey.indexOf("</Data>")+"</Data>".length();
        System.out.println(strStartIndex+"pp"+endStartIndex);
        String re=sessionkey.substring(strStartIndex,endStartIndex);

        re=re.replaceAll("\\\\","");
        StringEscapeUtils.unescapeJava(re);
        System.out.println(re);
        String xml2="<class id="
                + "'1'"
                + ">"+re+"</class>";
        JSON referJsonResult2 = cacheXmlSerializer.read(xml2);
        JSONObject referJson2 = JSONObject.parseObject(referJsonResult2.toString());
        System.out.println(referJson2);
        return new JsonResult(referJson2);
    }
    /*身份证校验*/
    @RequestMapping(value = "/Check_PersonID.fun")
    @ResponseBody
    public Object selectPersonID(String ac,String pw,String personId) {
        String url = "http://open.12301dev.com/openService/pftMX.php";
        /*  HttpClientUtil.doPost(url);*/
        /* sendPost(url);*/
        String xmlInf = "<soap:Envelope xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:types=\"urn:PFTMX/encodedTypes\" xmlns:tns=\"urn:PFTMX\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + " <soap:Body soap:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"
                +        " <tns:Check_PersonID>"
                +            "<ac>"+ac+"</ac>"
                +            " <pw>"+pw+"</pw>"
                +            "<personId>"+personId+"</personId>"
                +            "</tns:Check_PersonID>"
                +            " </soap:Body>"
                +            "</soap:Envelope>";
        String xml=  new HttpPostTest().testPost(url,xmlInf);
        XMLSerializer cacheXmlSerializer = new XMLSerializer();
        JSON referJsonResult = cacheXmlSerializer.read(xml);
        JSONObject referJson = JSONObject.parseObject(referJsonResult.toString());
        System.out.println(referJson);

        String sessionkey = referJson.get("SOAP-ENV:Body").toString();
        System.out.println(sessionkey);
        int strStartIndex = sessionkey.indexOf("<Data>");
        int endStartIndex = sessionkey.indexOf("</Data>")+"</Data>".length();
        System.out.println(strStartIndex+"pp"+endStartIndex);
        String re=sessionkey.substring(strStartIndex,endStartIndex);

        re=re.replaceAll("\\\\","");
        StringEscapeUtils.unescapeJava(re);
        System.out.println(re);
        String xml2="<class id="
                + "'1'"
                + ">"+re+"</class>";
        JSON referJsonResult2 = cacheXmlSerializer.read(xml2);
        JSONObject referJson2 = JSONObject.parseObject(referJsonResult2.toString());
        System.out.println(referJson2);
        return new JsonResult(referJson2);
    }

    /*预判下单 （判断提交的参数是否满足下单条件，没有生成订单；可选）*/
    @RequestMapping(value = "/OrderPreCheck.fun")
    @ResponseBody
    public Object selectOrderPreCheck(String ac,String pw,String tid,String tnum,String playtime,
                                      String  ordertel,String ordername,String m,String paymode,String personid) {
        String url = "http://open.12301dev.com/openService/pftMX.php";
        /*  HttpClientUtil.doPost(url);*/
        /* sendPost(url);*/
        String xmlInf = "<soap:Envelope xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:types=\"urn:PFTMX/encodedTypes\" xmlns:tns=\"urn:PFTMX\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + " <soap:Body soap:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"
                +        " <tns:OrderPreCheck>"
                +            "<ac>"+ac+"</ac>"
                +            " <pw>"+pw+"</pw>"
                +            "<tid>"+tid+"</tid>"
                +            " <tnum>"+tnum+"</tnum>"
                +            " <playtime>"+playtime+"</playtime>"
                +           " <ordertel>"+ordertel+"</ordertel>"
                +           " <ordername>"+ordername+"</ordername>"
                +           " <m>"+m+"</m>"
                +           " <paymode>"+paymode+"</paymode>"
                +           " <personid>"+personid+"</personid>"
                +            "</tns:OrderPreCheck>"
                +            " </soap:Body>"
                +            "</soap:Envelope>";
        String xml=  new HttpPostTest().testPost(url,xmlInf);
        XMLSerializer cacheXmlSerializer = new XMLSerializer();
        JSON referJsonResult = cacheXmlSerializer.read(xml);
        JSONObject referJson = JSONObject.parseObject(referJsonResult.toString());
        System.out.println(referJson);

        String sessionkey = referJson.get("SOAP-ENV:Body").toString();
        System.out.println(sessionkey);
        int strStartIndex = sessionkey.indexOf("<Data>");
        int endStartIndex = sessionkey.indexOf("</Data>")+"</Data>".length();
        System.out.println(strStartIndex+"pp"+endStartIndex);
        String re=sessionkey.substring(strStartIndex,endStartIndex);

        re=re.replaceAll("\\\\","");
        StringEscapeUtils.unescapeJava(re);
        System.out.println(re);
        String xml2="<class id="
                + "'1'"
                + ">"+re+"</class>";
        JSON referJsonResult2 = cacheXmlSerializer.read(xml2);
        JSONObject referJson2 = JSONObject.parseObject(referJsonResult2.toString());
        System.out.println(referJson2);
        return new JsonResult(referJson2);
    }
    /*1.3.3提交订单 （已支付订单提交，必接）*/
    @RequestMapping(value = "/PFT_Order_Submit.fun")
    @ResponseBody
    public Object selectOrderSubmit(String ac,String pw,String lid,String tid,
                                      String  tprice,String tnum,String playtime,String ordername,String ordertel,
                                    String  contactTEL,String smsSend,String paymode,String ordermode,String assembly,
                                    String  series,String concatID,String pCode,String m,String personID,
                                    String  memo,String callbackUrl) {
        String remotenum=RandomImgName.getOrderIdByUUId();//订单编号
        String url = "http://open.12301dev.com/openService/pftMX.php";
        /*  HttpClientUtil.doPost(url);*/
        /* sendPost(url);*/
        String xmlInf = "<soap:Envelope xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:types=\"urn:PFTMX/encodedTypes\" xmlns:tns=\"urn:PFTMX\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + " <soap:Body soap:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"
                +        " <tns:PFT_Order_Submit>"
                +            "<ac>"+ac+"</ac>"
                +            " <pw>"+pw+"</pw>"
                +            "<lid>"+lid+"</lid>"
                +            " <tid>"+tid+"</tid>"
                +            " <remotenum>"+remotenum+"</remotenum>"
                +            " <tprice>"+tprice+"</tprice>"
                +           " <tnum>"+tnum+"</tnum>"
                +           " <playtime>"+playtime+"</playtime>"
                +           " <contactTEL>"+contactTEL+"</contactTEL>"
                +           " <smsSend>"+smsSend+"</smsSend>"
                +           " <ordername>"+ordername+"</ordername>"
                +           " <ordertel>"+ordertel+"</ordertel>"
                +           " <paymode>"+paymode+"</paymode>"
                +            " <ordermode>"+ordermode+"</ordermode>"
                +            "<assembly>"+assembly+"</assembly>"
                +            " <series>"+series+"</series>"
                +            " <concatID>"+concatID+"</concatID>"
                +           " <pCode>"+pCode+"</pCode>"
                +           " <m>"+m+"</m>"
                +           " <personID>"+personID+"</personID>"
                +           " <memo>"+memo+"</memo>"
                +            " <callbackUrl>"+callbackUrl+"</callbackUrl>"
                +            "</tns:PFT_Order_Submit>"
                +            " </soap:Body>"
                +            "</soap:Envelope>";
        String xml=  new HttpPostTest().testPost(url,xmlInf);
        XMLSerializer cacheXmlSerializer = new XMLSerializer();
        JSON referJsonResult = cacheXmlSerializer.read(xml);
        JSONObject referJson = JSONObject.parseObject(referJsonResult.toString());
        System.out.println(referJson);

        String sessionkey = referJson.get("SOAP-ENV:Body").toString();
        System.out.println(sessionkey);
        int strStartIndex = sessionkey.indexOf("<Data>");
        int endStartIndex = sessionkey.indexOf("</Data>")+"</Data>".length();
        System.out.println(strStartIndex+"pp"+endStartIndex);
        String re=sessionkey.substring(strStartIndex,endStartIndex);

        re=re.replaceAll("\\\\","");
        StringEscapeUtils.unescapeJava(re);
        System.out.println(re);
        String xml2="<class id="
                + "'1'"
                + ">"+re+"</class>";
        JSON referJsonResult2 = cacheXmlSerializer.read(xml2);
        JSONObject referJson2 = JSONObject.parseObject(referJsonResult2.toString());
        System.out.println(referJson2);
        return new JsonResult(referJson2);
    }

    /*1.3.3提交订单 （已支付订单提交，必接）*/
    @RequestMapping(value = "/Fun_PFT_Order_Submit",method = RequestMethod.POST)
    @ResponseBody
    public Object selectSignSettingById(@RequestBody PFTOrder pftOrder) {
        PFTOrder pftOrders=new PFTOrder();
        String id=UuidUtils.creatUUID();//UUid
        pftOrders.setId(id);
        pftOrders.setLid(pftOrder.getLid());
        pftOrders.setTid(pftOrder.getTid());
        //订单号
        String orderSerialNumber=RandomImgName.getOrderIdByUUId();//订单编号
        pftOrders.setRemotenum(orderSerialNumber);
        pftOrders.setTprice(pftOrder.getTprice());
        pftOrders.setTnum(pftOrder.getTnum());
        pftOrders.setPlaytime(pftOrder.getPlaytime());
        pftOrders.setOrdername(pftOrder.getOrdername());
        pftOrders.setOrdertel(pftOrder.getOrdertel());
        pftOrders.setContactTEL(pftOrder.getContactTEL());
        pftOrders.setSmsSend(pftOrder.getSmsSend());
        pftOrders.setPaymode(pftOrder.getPaymode());
        pftOrders.setAssembly(pftOrder.getAssembly());
        pftOrders.setSeries(pftOrder.getSeries());
        pftOrders.setConcatID(pftOrder.getConcatID());
        pftOrders.setpCode(pftOrder.getpCode());
        pftOrders.setM(pftOrder.getM());
        pftOrders.setPersonID(pftOrder.getPersonID());
        pftOrders.setMemo(pftOrder.getMemo());
        pftOrders.setCallbackUrl(pftOrder.getCallbackUrl());
        //票付通订单保存到数据库
        Integer num=piaoFuTongService.insertPiaoFuTongOrder(pftOrders);

        return new JsonResult(num);
    }
    /*1.3.4查询订单（可选）*/
    @RequestMapping(value = "/OrderQuery.fun")
    @ResponseBody
    public Object selectOrderQuery(String ac,String pw,String pftOrdernum,String remoteOrdernum) {
        String url = "http://open.12301dev.com/openService/pftMX.php";
        /*  HttpClientUtil.doPost(url);*/
        /* sendPost(url);*/
        String xmlInf = "<soap:Envelope xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:types=\"urn:PFTMX/encodedTypes\" xmlns:tns=\"urn:PFTMX\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + " <soap:Body soap:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"
                +        " <tns:OrderQuery>"
                +            "<ac>"+ac+"</ac>"
                +            " <pw>"+pw+"</pw>"
                +            "<pftOrdernum>"+pftOrdernum+"</pftOrdernum>"
                +            " <remoteOrdernum>"+remoteOrdernum+"</remoteOrdernum>"
                +            "</tns:OrderQuery>"
                +            " </soap:Body>"
                +            "</soap:Envelope>";
        String xml=  new HttpPostTest().testPost(url,xmlInf);
        XMLSerializer cacheXmlSerializer = new XMLSerializer();
        JSON referJsonResult = cacheXmlSerializer.read(xml);
        JSONObject referJson = JSONObject.parseObject(referJsonResult.toString());
        System.out.println(referJson);

        String sessionkey = referJson.get("SOAP-ENV:Body").toString();
        System.out.println(sessionkey);
        int strStartIndex = sessionkey.indexOf("<Data>");
        int endStartIndex = sessionkey.indexOf("</Data>")+"</Data>".length();
        System.out.println(strStartIndex+"pp"+endStartIndex);
        String re=sessionkey.substring(strStartIndex,endStartIndex);

        re=re.replaceAll("\\\\","");
        StringEscapeUtils.unescapeJava(re);
        System.out.println(re);
        String xml2="<class id="
                + "'1'"
                + ">"+re+"</class>";
        JSON referJsonResult2 = cacheXmlSerializer.read(xml2);
        JSONObject referJson2 = JSONObject.parseObject(referJsonResult2.toString());
        System.out.println(referJson2);
        return new JsonResult(referJson2);
    }
    /*1.3.5修改/取消订单（必接）*/
    @RequestMapping(value = "/Order_Change_Pro.fun")
    @ResponseBody
    public Object selectChangePro(String ac,String pw,String ordern,String num,String ordertel,String m) {
        String url = "http://open.12301dev.com/openService/pftMX.php";
        /*  HttpClientUtil.doPost(url);*/
        /* sendPost(url);*/
        String xmlInf = "<soap:Envelope xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:types=\"urn:PFTMX/encodedTypes\" xmlns:tns=\"urn:PFTMX\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + " <soap:Body soap:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"
                +        " <tns:Order_Change_Pro>"
                +            "<ac>"+ac+"</ac>"
                +            " <pw>"+pw+"</pw>"
                +            "<ordern>"+ordern+"</ordern>"
                +            " <num>"+num+"</num>"
                +            " <ordertel>"+ordertel+"</ordertel>"
                +            " <m>"+m+"</m>"
                +            "</tns:Order_Change_Pro>"
                +            " </soap:Body>"
                +            "</soap:Envelope>";
        String xml=  new HttpPostTest().testPost(url,xmlInf);
        XMLSerializer cacheXmlSerializer = new XMLSerializer();
        JSON referJsonResult = cacheXmlSerializer.read(xml);
        JSONObject referJson = JSONObject.parseObject(referJsonResult.toString());
        System.out.println(referJson);

        String sessionkey = referJson.get("SOAP-ENV:Body").toString();
        System.out.println(sessionkey);
        int strStartIndex = sessionkey.indexOf("<Data>");
        int endStartIndex = sessionkey.indexOf("</Data>")+"</Data>".length();
        System.out.println(strStartIndex+"pp"+endStartIndex);
        String re=sessionkey.substring(strStartIndex,endStartIndex);

        re=re.replaceAll("\\\\","");
        StringEscapeUtils.unescapeJava(re);
        System.out.println(re);
        String xml2="<class id="
                + "'1'"
                + ">"+re+"</class>";
        JSON referJsonResult2 = cacheXmlSerializer.read(xml2);
        JSONObject referJson2 = JSONObject.parseObject(referJsonResult2.toString());
        System.out.println(referJson2);
        return new JsonResult(referJson2);
    }
    /*
    * 数据库 修改取消订单
    * */
    @RequestMapping(value = "/Fun_Order_Change_Pro.fun",method = RequestMethod.POST)
    @ResponseBody
    public Object selectSignSettingById(@RequestBody PFTChangeOrder pftChangeOrder) {
        PFTChangeOrder pftChangeOrder1=new PFTChangeOrder();
        pftChangeOrder1.setOrdern(pftChangeOrder.getOrdern());
        pftChangeOrder1.setNum(pftChangeOrder.getNum());
        pftChangeOrder1.setOrdertel(pftChangeOrder.getOrdertel());
        pftChangeOrder1.setM(pftChangeOrder.getM());
        Integer num = null;
        if(pftChangeOrder.getNum().equals("-1")){
            //修改手机号
             num=piaoFuTongService.changePhonePiaoFuTongOrder(pftChangeOrder1);
        }else if(pftChangeOrder.getNum().equals("0")){
            // 取消订单
            num=piaoFuTongService.changePiaoFuTongOrder(pftChangeOrder1);
        }

        return new JsonResult(num);
    }
    /*1.3.6重发短信 （可选）-------------------------------和上一个接口相同*/
    @RequestMapping(value = "/Order_Change_Pro.fun")
    @ResponseBody
    public Object selectChangePro(String ac,String pw,String ordern,String m) {
        String url = "http://open.12301dev.com/openService/pftMX.php";
        /*  HttpClientUtil.doPost(url);*/
        /* sendPost(url);*/
        String xmlInf = "<soap:Envelope xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:types=\"urn:PFTMX/encodedTypes\" xmlns:tns=\"urn:PFTMX\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + " <soap:Body soap:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"
                +        " <tns:Order_Change_Pro>"
                +            "<ac>"+ac+"</ac>"
                +            " <pw>"+pw+"</pw>"
                +            "<ordern>"+ordern+"</ordern>"
                +            " <m>"+m+"</m>"
                +            "</tns:Order_Change_Pro>"
                +            " </soap:Body>"
                +            "</soap:Envelope>";
        String xml=  new HttpPostTest().testPost(url,xmlInf);
        XMLSerializer cacheXmlSerializer = new XMLSerializer();
        JSON referJsonResult = cacheXmlSerializer.read(xml);
        JSONObject referJson = JSONObject.parseObject(referJsonResult.toString());
        System.out.println(referJson);

        String sessionkey = referJson.get("SOAP-ENV:Body").toString();
        System.out.println(sessionkey);
        int strStartIndex = sessionkey.indexOf("<Data>");
        int endStartIndex = sessionkey.indexOf("</Data>")+"</Data>".length();
        System.out.println(strStartIndex+"pp"+endStartIndex);
        String re=sessionkey.substring(strStartIndex,endStartIndex);

        re=re.replaceAll("\\\\","");
        StringEscapeUtils.unescapeJava(re);
        System.out.println(re);
        String xml2="<class id="
                + "'1'"
                + ">"+re+"</class>";
        JSON referJsonResult2 = cacheXmlSerializer.read(xml2);
        JSONObject referJson2 = JSONObject.parseObject(referJsonResult2.toString());
        System.out.println(referJson2);
        return new JsonResult(referJson2);
    }

    /*1.3.7查看凭证码/消费码 （可选）-------------------------------和上一个接口相同*/
    @RequestMapping(value = "/Order_Change_Pro.fun")
    @ResponseBody
    public Object selectChange(String ac,String pw,String ordern,String m) {
        String url = "http://open.12301dev.com/openService/pftMX.php";
        /*  HttpClientUtil.doPost(url);*/
        /* sendPost(url);*/
        String xmlInf = "<soap:Envelope xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:types=\"urn:PFTMX/encodedTypes\" xmlns:tns=\"urn:PFTMX\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + " <soap:Body soap:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"
                +        " <tns:Order_Change_Pro>"
                +            "<ac>"+ac+"</ac>"
                +            " <pw>"+pw+"</pw>"
                +            "<ordern>"+ordern+"</ordern>"
                +            " <m>"+m+"</m>"
                +            "</tns:Order_Change_Pro>"
                +            " </soap:Body>"
                +            "</soap:Envelope>";
        String xml=  new HttpPostTest().testPost(url,xmlInf);
        XMLSerializer cacheXmlSerializer = new XMLSerializer();
        JSON referJsonResult = cacheXmlSerializer.read(xml);
        JSONObject referJson = JSONObject.parseObject(referJsonResult.toString());
        System.out.println(referJson);

        String sessionkey = referJson.get("SOAP-ENV:Body").toString();
        System.out.println(sessionkey);
        int strStartIndex = sessionkey.indexOf("<Data>");
        int endStartIndex = sessionkey.indexOf("</Data>")+"</Data>".length();
        System.out.println(strStartIndex+"pp"+endStartIndex);
        String re=sessionkey.substring(strStartIndex,endStartIndex);

        re=re.replaceAll("\\\\","");
        StringEscapeUtils.unescapeJava(re);
        System.out.println(re);
        String xml2="<class id="
                + "'1'"
                + ">"+re+"</class>";
        JSON referJsonResult2 = cacheXmlSerializer.read(xml2);
        JSONObject referJson2 = JSONObject.parseObject(referJsonResult2.toString());
        System.out.println(referJson2);
        return new JsonResult(referJson2);
    }
}
