package com.springboot.fun.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.springboot.fun.entity.*;
import com.springboot.fun.service.DeployService;
import com.springboot.fun.util.JsonResult;
import com.springboot.fun.util.UuidUtils;
import com.springboot.fun.util.piaofutong.HttpPostTest;
import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
* 后台端配置信息
* */
@CrossOrigin(origins = "*",maxAge = 3600)
@Controller
@RequestMapping(value = "/deploy")
public class DeployController {

    @Autowired
    private DeployService deployService;

    //签到配置
    //查询签到列表
    @RequestMapping(value = "/selectSignSetting.fun")
    @ResponseBody
    public Object selectSignSetting() {
        List<YlqSignSetting> signList = deployService.selectSignSetting();
        return new JsonResult(signList);
    }
    //编辑前返回签到信息
    @RequestMapping(value = "/selectSignSettingById.fun")
    @ResponseBody
    public Object selectSignSettingById(String id) {
        List<YlqSignSetting> signList = deployService.selectSignSettingById(id);
        return new JsonResult(signList);
    }
    //编辑签到配置信息
    @RequestMapping(value = "/updateSignSettingById.fun",method = RequestMethod.POST)
    @ResponseBody
    public Object selectSignSettingById(@RequestBody YlqSignSetting ylqSignSetting) {

        YlqSignSetting ylqSignSettings=new YlqSignSetting();
        ylqSignSettings.setId(ylqSignSetting.getId());
        ylqSignSettings.setDay(ylqSignSetting.getDay());
        ylqSignSettings.setMoney(ylqSignSetting.getMoney());
        Integer num = deployService.updateSignSettingById(ylqSignSettings);
        return new JsonResult(num);
    }

    //增加签到配置信息
    @RequestMapping(value = "/insertSignSetting.fun",method = RequestMethod.POST)
    @ResponseBody
    public Object insertSignSetting(@RequestBody YlqSignSetting ylqSignSetting) {
        String id = UuidUtils.creatUUID();
        YlqSignSetting ylqSignSettings=new YlqSignSetting();
        ylqSignSettings.setId(id);
        ylqSignSettings.setDay(ylqSignSetting.getDay());
        ylqSignSettings.setMoney(ylqSignSetting.getMoney());
        Integer num = deployService.insertSignSetting(ylqSignSettings);
        return new JsonResult(num);
    }
    //获取校区列表
    @RequestMapping(value = "/searchSchoolList.fun")
    @ResponseBody
    public Object searchSchoolList(int page, int pageSize,String search) {
        if (search.equals("")){
            search=null;
        }
        //*page 第几页，pageSize 每页条数*//*
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> schoolMap = new HashMap<String, Object>();
        schoolMap.put("search",search);
        PageInfo<YlqSchool> pager = deployService.searchSchoolList(page,pageSize,schoolMap);
        //总条数
        resultMap.put("total", pager.getTotal());
        //获取每页数据
        resultMap.put("rows", pager.getList());
        return new JsonResult(resultMap);
    }
    //编辑前返回学校信息
    @RequestMapping(value = "/searchSchoolById.fun")
    @ResponseBody
    public Object searchSchoolById(String id) {
        List<YlqSchool> signList = deployService.searchSchoolById(id);
        return new JsonResult(signList);
    }
    //编辑学校信息
    @RequestMapping(value = "/updateSchoolById.fun",method = RequestMethod.POST)
    @ResponseBody
    public Object updateSchoolById(@RequestBody YlqSchool YlqSchool) {

        YlqSchool ylqSchools=new YlqSchool();
        ylqSchools.setId(YlqSchool.getId());
        ylqSchools.setSchoolName(YlqSchool.getSchoolName());
        ylqSchools.setSchoolNumber(YlqSchool.getSchoolNumber());
        ylqSchools.setFirstLetter(YlqSchool.getFirstLetter());
        Integer num = deployService.updateSchoolById(ylqSchools);
        return new JsonResult(num);
    }
    //添加学校
    @RequestMapping(value = "/addSchool.fun",method = RequestMethod.POST)
    @ResponseBody
    public Object addSchool(@RequestBody YlqSchool YlqSchool) {
        String id = UuidUtils.creatUUID();
        YlqSchool ylqSchools=new YlqSchool();
        ylqSchools.setId(id);
        ylqSchools.setSchoolName(YlqSchool.getSchoolName());
        ylqSchools.setSchoolNumber(YlqSchool.getSchoolNumber());
        ylqSchools.setFirstLetter(YlqSchool.getFirstLetter());
        Integer num = deployService.addSchool(ylqSchools);
        return new JsonResult(num);
    }

    //查询公告列表
    @RequestMapping(value = "/selectNotice.fun")
    @ResponseBody
    public Object selectNotice() {
        List<FunNotice> signList = deployService.selectNotice();
        return new JsonResult(signList);
    }
    //编辑前返回公告信息
    @RequestMapping(value = "/selectNoticeById.fun")
    @ResponseBody
    public Object selectNoticeById(String id) {
        List<FunNotice> signList = deployService.selectNoticeById(id);
        return new JsonResult(signList);
    }
    //编辑公告配置信息
    @RequestMapping(value = "/updateNoticeById.fun",method = RequestMethod.POST)
    @ResponseBody
    public Object updateNoticeById(@RequestBody FunNotice funNotice) {

        FunNotice funNotice1=new FunNotice();
        funNotice1.setId(funNotice.getId());
        funNotice1.setUserNum(funNotice.getUserNum());
        funNotice1.setBrowseNum(funNotice.getBrowseNum());
        funNotice1.setNotice(funNotice.getNotice());
        funNotice1.setType(funNotice.getType());
        Integer num = deployService.updateNoticeById(funNotice1);
        return new JsonResult(num);
    }

    //增加公告配置信息
    @RequestMapping(value = "/insertNotice.fun",method = RequestMethod.POST)
    @ResponseBody
    public Object insertNotice(@RequestBody FunNotice funNotice) {
        String id = UuidUtils.creatUUID();
        FunNotice funNotice1=new FunNotice();
        funNotice1.setId(id);
        funNotice1.setUserNum(funNotice.getUserNum());
        funNotice1.setBrowseNum(funNotice.getBrowseNum());
        funNotice1.setNotice(funNotice.getNotice());
        funNotice1.setType(funNotice.getType());
        Integer num = deployService.insertNotice(funNotice1);
        return new JsonResult(num);
    }
    //关闭公告
    @RequestMapping(value = "/closeNotice.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object closeNotice(@RequestBody FunNotice funNotice) {
        String id =funNotice.getId();
        Integer num=  deployService.closeNotice(id);
        return new JsonResult(num);
    }
    //打开公告
    @RequestMapping(value = "/openNotice.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object openNotice(@RequestBody FunNotice funNotice) {
        String id =funNotice.getId();
        Integer num=  deployService.openNotice(id);
        return new JsonResult(num);
    }


    //查询轮播图列表
    @RequestMapping(value = "/selectHomeUrl.fun")
    @ResponseBody
    public Object selectHomeUrl() {
        List<FunHomeUrl> signList = deployService.selectHomeUrl();
        return new JsonResult(signList);
    }
    //编辑前返回轮播图信息
    @RequestMapping(value = "/selectHomeUrlById.fun")
    @ResponseBody
    public Object selectHomeUrlById(String id) {
        List<FunHomeUrl> signList = deployService.selectHomeUrlById(id);
        return new JsonResult(signList);
    }
    //编辑轮播图配置信息
    @RequestMapping(value = "/updateHomeUrlById.fun",method = RequestMethod.POST)
    @ResponseBody
    public Object updateHomeUrlById(@RequestBody FunHomeUrl funHomeUrl) {

        FunHomeUrl funHomeUrl1=new FunHomeUrl();
        funHomeUrl1.setId(funHomeUrl.getId());
        funHomeUrl1.setHomeUrl(funHomeUrl.getHomeUrl());
        funHomeUrl1.setHomeState(funHomeUrl.getHomeState());
        funHomeUrl1.setHomeSchoolId(funHomeUrl.getHomeSchoolId());
        funHomeUrl1.setHomePath(funHomeUrl.getHomePath());
        funHomeUrl.setHomePathType(funHomeUrl.getHomePathType());
        funHomeUrl.setHomePathType(funHomeUrl.getHomePathType());
        Integer num = deployService.updateHomeUrlById(funHomeUrl1);
        return new JsonResult(num);
    }

    //增加轮播图配置信息
    @RequestMapping(value = "/insertHomeUrl.fun",method = RequestMethod.POST)
    @ResponseBody
    public Object insertHomeUrl(@RequestBody FunHomeUrl funHomeUrl) {
        String id = UuidUtils.creatUUID();
        FunHomeUrl funHomeUrl1=new FunHomeUrl();
        funHomeUrl1.setId(id);
        funHomeUrl1.setHomeUrl(funHomeUrl.getHomeUrl());
        funHomeUrl1.setHomeState(funHomeUrl.getHomeState());
        funHomeUrl1.setHomeSchoolId(funHomeUrl.getHomeSchoolId());
        funHomeUrl1.setHomePath(funHomeUrl.getHomePath());
        funHomeUrl1.setHomePathType(funHomeUrl.getHomePathType());
        funHomeUrl1.setHomeResult(funHomeUrl.getHomeResult());
        Integer num = deployService.insertHomeUrl(funHomeUrl1);
        return new JsonResult(num);
    }
    //关闭轮播图
    @RequestMapping(value = "/closeHomeUrl.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object closeHomeUrl(@RequestBody FunHomeUrl funHomeUrl) {
        String id =funHomeUrl.getId();
        Integer num=  deployService.closeHomeUrl(id);
        return new JsonResult(num);
    }
    //打开轮播图
    @RequestMapping(value = "/openHomeUrl.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object openHomeUrl(@RequestBody FunHomeUrl funHomeUrl) {
        String id =funHomeUrl.getId();
        Integer num=  deployService.openHomeUrl(id);
        return new JsonResult(num);
    }

    //查询任务列表
    @RequestMapping(value = "/selectTask.fun")
    @ResponseBody
    public Object selectTask() {
        List<YlqTask> signList = deployService.selectTask();
        return new JsonResult(signList);
    }
    //编辑前返回任务信息
    @RequestMapping(value = "/selectTaskById.fun")
    @ResponseBody
    public Object selectTaskById(String id) {
        List<YlqTask> signList = deployService.selectTaskById(id);
        return new JsonResult(signList);
    }
    //编辑任务配置信息
    @RequestMapping(value = "/updateTaskById.fun",method = RequestMethod.POST)
    @ResponseBody
    public Object updateTaskById(@RequestBody YlqTask ylqTask) {

        YlqTask ylqTask1=new YlqTask();
        ylqTask1.setId(ylqTask.getId());
        ylqTask1.setType(ylqTask.getType());
        ylqTask1.setName(ylqTask.getName());
        ylqTask1.setImg(ylqTask.getImg());
        ylqTask1.setMoney(ylqTask.getMoney());
        ylqTask1.setPath(ylqTask.getPath());
        ylqTask1.setPathType(ylqTask.getPathType());
        ylqTask1.setResult(ylqTask.getResult());
        Integer num = deployService.updateTaskById(ylqTask1);
        return new JsonResult(num);
    }

    //增加任务配置信息
    @RequestMapping(value = "/insertTask.fun",method = RequestMethod.POST)
    @ResponseBody
    public Object insertTask(@RequestBody YlqTask ylqTask) {
        String id = UuidUtils.creatUUID();
        YlqTask ylqTask1=new YlqTask();
        ylqTask1.setId(id);
        ylqTask1.setType(ylqTask.getType());
        ylqTask1.setName(ylqTask.getName());
        ylqTask1.setImg(ylqTask.getImg());
        ylqTask1.setMoney(ylqTask.getMoney());
        ylqTask1.setPath(ylqTask.getPath());
        ylqTask1.setPathType(ylqTask.getPathType());
        ylqTask1.setResult(ylqTask.getResult());
        Integer num = deployService.insertTask(ylqTask1);
        return new JsonResult(num);
    }
    //关闭任务
    @RequestMapping(value = "/closeTask.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object closeTask(@RequestBody YlqTask ylqTask) {
        String id =ylqTask.getId();
        Integer num=  deployService.closeTask(id);
        return new JsonResult(num);
    }
    //打开任务
    @RequestMapping(value = "/openTask.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object openTask(@RequestBody YlqTask ylqTask) {
        String id =ylqTask.getId();
        Integer num=  deployService.openTask(id);
        return new JsonResult(num);
    }
    /*
    * 测试票付通
    * */
    @RequestMapping(value = "/selectsun.fun")
    @ResponseBody
    public Object selectsun(String ac,String pw,String n,String m) {
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
        return new JsonResult(referJson);
    }

}
