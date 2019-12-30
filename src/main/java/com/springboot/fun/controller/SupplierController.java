package com.springboot.fun.controller;

import com.github.pagehelper.PageInfo;
import com.springboot.fun.entity.*;
import com.springboot.fun.service.AppletLoginService;
import com.springboot.fun.service.SupplierService;
import com.springboot.fun.util.JsonResult;
import com.springboot.fun.util.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/*
后台
* 商家管理
* */

@CrossOrigin(origins = "*",maxAge = 3600)
@Controller
@RequestMapping(value = "/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;
    @Autowired
    private AppletLoginService appletLoginService;
    //获取所有商家
    @RequestMapping(value = "/findAllSupplier.fun")
    @ResponseBody
    public Object findAllSupplier(int page,int pageSize,String schoolId,String search) {
        if (schoolId.equals("")){
            schoolId=null;
        }if (search.equals("")){
            search=null;
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> supplierMap = new HashMap<String, Object>();
        supplierMap.put("schoolId",schoolId);
        supplierMap.put("search",search);
        PageInfo<NideShopSupplier> pager = supplierService.findAllSupplier(page,pageSize,supplierMap);
        //总条数
        resultMap.put("total", pager.getTotal());
        //获取每页数据
        resultMap.put("rows", pager.getList());
        return new JsonResult(resultMap);
    }
    //上传商家
    @RequestMapping(value = "/insertSupplier.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object insertSupplier(@RequestBody NideShopSupplier nideShopSupplier) {

        NideShopSupplier nideShopSuppliers=new NideShopSupplier();
        String id = UuidUtils.creatUUID();
        nideShopSuppliers.setId(id);
        nideShopSuppliers.setName(nideShopSupplier.getName());
        nideShopSuppliers.setAddress(nideShopSupplier.getAddress());
        nideShopSuppliers.setLongitude(nideShopSupplier.getLongitude());
        nideShopSuppliers.setLatitude(nideShopSupplier.getLatitude());
        nideShopSuppliers.setContactMobie(nideShopSupplier.getContactMobie());
        nideShopSuppliers.setPrimaryPicUrl(nideShopSupplier.getPrimaryPicUrl());
        nideShopSuppliers.setThumbnailUrl(nideShopSupplier.getThumbnailUrl());
        nideShopSuppliers.setIntroduction(nideShopSupplier.getIntroduction());
        nideShopSuppliers.setOpenTime(nideShopSupplier.getOpenTime());
        /*nideShopSuppliers.setSchoolId(nideShopSupplier.getSchoolId());*/
        nideShopSuppliers.setBrowseNum(nideShopSupplier.getBrowseNum());
        nideShopSuppliers.setBrowseTrueNum(nideShopSupplier.getBrowseTrueNum());
        nideShopSuppliers.setShareNum(nideShopSupplier.getShareNum());
        nideShopSuppliers.setShareTrueNum(nideShopSupplier.getShareTrueNum());
        nideShopSuppliers.setLabel(nideShopSupplier.getLabel());
        Integer list = supplierService.insertSupplier(nideShopSuppliers);
        //给商家添加校区
        List<FunSchoolSupplier> users;
        if(nideShopSupplier.getSchoolId().equals("3")){//全校区
            //修改这个商家的校区状态为全校区
            supplierService.updateSupplierSchoolState(id);//
            List<SchoolEntity> schoolEntityList = appletLoginService.findAllSchool();
            users = new ArrayList<FunSchoolSupplier>();
            for (SchoolEntity x : schoolEntityList) {
                String uuid = UuidUtils.creatUUID();
                users.add(new FunSchoolSupplier(uuid, x.getId(), id));
            }
        }else {
            //校区
            String[] s1 = (nideShopSupplier.getSchoolId()).split(",");
            users = new ArrayList<FunSchoolSupplier>();
            for (int i = 0; i < s1.length; i++) {
                String uuid = UuidUtils.creatUUID();
                users.add(new FunSchoolSupplier(uuid, s1[i], id));
            }
        }
          supplierService.addsupplierSchool(users);

        //上传收银员
        ArrayList<SupplierEmployeeEntity> employees;
        String[] employee = (nideShopSupplier.getSupplierEmployee()).split(",");//收银员id集合
        employees = new ArrayList<SupplierEmployeeEntity>();
        for (int i = 0; i < employee.length; i++) {
            //用用户id查找个人信息
            WxUser wxUser1 = appletLoginService.selectByUserId(employee[i]);
            System.out.println("employee[i]"+employee[i]);
            String eeid = UuidUtils.creatUUID();
            employees.add(new SupplierEmployeeEntity(eeid,id,employee[i],
                    wxUser1.getUserName(),wxUser1.getHeadimgurl(),wxUser1.getNickname(),wxUser1.getOpenid()));
        }
        supplierService.addsupplierempoyee(employees);//添加收银员

        return new JsonResult(list);
    }

    //商家校区修改(调试)
    @RequestMapping(value = "/updateSchoolSupplier.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object outSupplier(@RequestBody FunSchoolSupplier funSchoolSupplier) {
        List<FunSchoolSupplier> users;
        String id = UuidUtils.creatUUID();
        if(funSchoolSupplier.getSchoolId().equals("3")){
            List<SchoolEntity> schoolEntityList = appletLoginService.findAllSchool();
            users = new ArrayList<FunSchoolSupplier>();
            for (SchoolEntity x : schoolEntityList) {
                String uuid = UuidUtils.creatUUID();
                users.add(new FunSchoolSupplier(uuid, x.getId(), id));
                System.out.println("777"+users);
            }
        }else {
            //校区
            String[] s1 = (funSchoolSupplier.getSchoolId()).split(",");
             users = new ArrayList<FunSchoolSupplier>();
            for (int i = 0; i < s1.length; i++) {
                String uuid = UuidUtils.creatUUID();
                System.out.println("uuid" + uuid);
                users.add(new FunSchoolSupplier(uuid, s1[i], id));
            }
        }
        System.out.println("到这边了");
       int num= supplierService.addsupplierSchool(users);
        return new JsonResult(num);
    }


    //修改前，返回原来的信息
    @RequestMapping(value = "/findAllSupplierById.fun")
    @ResponseBody
    public Object findAllSupplierById(String id) {
         NideShopSupplier nideShopSupplier = supplierService.findAllSupplierById(id);
         if(nideShopSupplier.getSchoolState()==3){//如果为三，则为全部校区，校区返回空
             nideShopSupplier.setFunSchoolSupplierList(null);
         }
        return new JsonResult(nideShopSupplier);
    }
    //修改商家
    @RequestMapping(value = "/updateSupplier.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object updateSupplier(@RequestBody NideShopSupplier nideShopSupplier) {

        NideShopSupplier nideShopSuppliers=new NideShopSupplier();
        nideShopSuppliers.setId(nideShopSupplier.getId());
        nideShopSuppliers.setName(nideShopSupplier.getName());
        nideShopSuppliers.setAddress(nideShopSupplier.getAddress());
        nideShopSuppliers.setLongitude(nideShopSupplier.getLongitude());
        nideShopSuppliers.setLatitude(nideShopSupplier.getLatitude());
        nideShopSuppliers.setContactMobie(nideShopSupplier.getContactMobie());
        nideShopSuppliers.setPrimaryPicUrl(nideShopSupplier.getPrimaryPicUrl());
        nideShopSuppliers.setThumbnailUrl(nideShopSupplier.getThumbnailUrl());
        nideShopSuppliers.setIntroduction(nideShopSupplier.getIntroduction());
        nideShopSuppliers.setOpenTime(nideShopSupplier.getOpenTime());
        /*nideShopSuppliers.setSchoolId(nideShopSupplier.getSchoolId());*/
        nideShopSuppliers.setBrowseNum(nideShopSupplier.getBrowseNum());
        nideShopSuppliers.setBrowseTrueNum(nideShopSupplier.getBrowseTrueNum());
        nideShopSuppliers.setShareNum(nideShopSupplier.getShareNum());
        nideShopSuppliers.setShareTrueNum(nideShopSupplier.getShareTrueNum());
        nideShopSuppliers.setLabel(nideShopSupplier.getLabel());
        Integer list = supplierService.updateSupplier(nideShopSuppliers);

        //修改校区
        //先删除这个商家下的所有校区，再增加

        supplierService.deleteSupplierSchool(nideShopSupplier.getId());
        //如果商家为全校区，则改为单校区
        supplierService.updateSupplierAllSchoolState(nideShopSupplier.getId());
        List<FunSchoolSupplier> users;
        if(nideShopSupplier.getSchoolId().equals("3")){//全校区
            //修改这个商家的校区状态为全校区
            supplierService.updateSupplierSchoolState(nideShopSupplier.getId());//
            List<SchoolEntity> schoolEntityList = appletLoginService.findAllSchool();
            users = new ArrayList<FunSchoolSupplier>();
            for (SchoolEntity x : schoolEntityList) {
                String uuid = UuidUtils.creatUUID();
                users.add(new FunSchoolSupplier(uuid, x.getId(), nideShopSupplier.getId()));
            }
        }else {
            //校区
            String[] s1 = (nideShopSupplier.getSchoolId()).split(",");
            users = new ArrayList<FunSchoolSupplier>();
            for (int i = 0; i < s1.length; i++) {
                String uuid = UuidUtils.creatUUID();
                users.add(new FunSchoolSupplier(uuid, s1[i], nideShopSupplier.getId()));
            }
        }
        supplierService.addsupplierSchool(users);

            //上传收银员
            //先删除这个商家下的所有收银员，再增加
            ArrayList<SupplierEmployeeEntity> employees;
            supplierService.deleteSupplierEmployee(nideShopSupplier.getId());
            String[] employee = (nideShopSupplier.getSupplierEmployee()).split(",");//收银员id集合
            employees = new ArrayList<SupplierEmployeeEntity>();
            for (int i = 0; i < employee.length; i++) {
                //用用户id查找个人信息
                WxUser wxUser1 = appletLoginService.selectByUserId(employee[i]);
                String eeid = UuidUtils.creatUUID();
                employees.add(new SupplierEmployeeEntity(eeid,nideShopSupplier.getId(),employee[i],
                        wxUser1.getUserName(),wxUser1.getHeadimgurl(),wxUser1.getNickname(),wxUser1.getOpenid()));
        }
        supplierService.addsupplierempoyee(employees);//添加收银员
        return new JsonResult(list);
    }
    //下架商家
    @RequestMapping(value = "/outSupplier.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object outSupplier(@RequestBody NideShopSupplier nideShopSupplier) {
        String id =nideShopSupplier.getId();
        Integer num=  supplierService.outSupplier(id);
        return new JsonResult(num);
    }
    //上架商家
    @RequestMapping(value = "/upSupplier.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object upSupplier(@RequestBody NideShopSupplier nideShopSupplier) {
        String id =nideShopSupplier.getId();
        Integer num=  supplierService.upSupplier(id);
        return new JsonResult(num);
    }
    //根据商家名字模糊搜索(作废)
    @RequestMapping(value = "/findAllSupplierByName.fun")
    @ResponseBody
    public Object findAllSupplierByName(int page,int pageSize,String name) {
        //评论列表
        Map<String, Object> resultMap = new HashMap<String, Object>();
        PageInfo<NideShopSupplier> pager = supplierService.findAllSupplierByName(page,pageSize,name);
        //总条数
        resultMap.put("total", pager.getTotal());
        //获取每页数据
        resultMap.put("rows", pager.getList());
        return new JsonResult(resultMap);
    }
    //根据商家id查询商家的收银员(作废)
    @RequestMapping(value = "/findAllSupplierEmployeeBySupplierId.fun")
    @ResponseBody
    public Object findAllSupplierEmployeeBySupplierId(int page,int pageSize,String supplierId) {
        //评论列表
        Map<String, Object> resultMap = new HashMap<String, Object>();
        PageInfo<SupplierEmployeeEntity> pager = supplierService.findAllSupplierEmployeeBySupplierId(page,pageSize,supplierId);
        //总条数
        resultMap.put("total", pager.getTotal());
        //获取每页数据
        resultMap.put("rows", pager.getList());
        return new JsonResult(resultMap);
    }
    //根据用户唯一id或者昵称模糊查找用户(商家的添加员工需返回)
    @RequestMapping(value = "/searchMapWXUserByName.fun")
    @ResponseBody
    public Object searchMapWXUserByName(String search) {
        /*page 第几页，pageSize 每页条数*/
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<WxUser> pager = appletLoginService.searchMapWXUserByName(search);
        return new JsonResult(pager);
    }
    //添加收银员(作废)
    @RequestMapping(value = "/insertSupplierEmployee.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object insertSupplierEmployee(@RequestBody SupplierEmployeeEntity supplierEmployeeEntity) {

        SupplierEmployeeEntity supplierEmployeeEntitys=new SupplierEmployeeEntity();
        String id = UuidUtils.creatUUID();
        supplierEmployeeEntitys.setId(id);
        supplierEmployeeEntitys.setName(supplierEmployeeEntity.getName());
        supplierEmployeeEntitys.setSupplierId(supplierEmployeeEntity.getSupplierId());
        supplierEmployeeEntitys.setCustomerId(supplierEmployeeEntity.getCustomerId());
        supplierEmployeeEntitys.setWxrealname(supplierEmployeeEntity.getWxrealname());
        supplierEmployeeEntitys.setWximgurl(supplierEmployeeEntity.getWximgurl());
        supplierEmployeeEntitys.setNickname(supplierEmployeeEntity.getNickname());
        supplierEmployeeEntitys.setOpenId(supplierEmployeeEntity.getOpenId());
        Integer list = supplierService.insertSupplierEmployee(supplierEmployeeEntitys);
        return new JsonResult(list);
    }
    //返回要修改的员工信息(作废)
    @RequestMapping(value = "/findAllSupplierEmployeeById.fun")
    @ResponseBody
    public Object findAllSupplierEmployeeById(String id) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        SupplierEmployeeEntity supplierEmployeeEntity = supplierService.findAllSupplierEmployeeById(id);
        return new JsonResult(supplierEmployeeEntity);
    }
    //根据主键id修改员工的信息(作废)
    @RequestMapping(value = "/updateSupplierEmployeeById.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object updateSupplierEmployeeById(@RequestBody SupplierEmployeeEntity supplierEmployeeEntity) {

        SupplierEmployeeEntity supplierEmployeeEntitys=new SupplierEmployeeEntity();
        supplierEmployeeEntitys.setId(supplierEmployeeEntity.getId());
        supplierEmployeeEntitys.setName(supplierEmployeeEntity.getName());
        Integer list = supplierService.updateSupplierEmployeeById(supplierEmployeeEntitys);
        return new JsonResult(list);
    }
    //根据主键id和员工微信昵称，员工编号模糊搜索员工(作废)
    @RequestMapping(value = "/findSupplierEmployeeByName.fun")
    @ResponseBody
    public Object findSupplierEmployeeByName(int page,int pageSize,String name) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        PageInfo<SupplierEmployeeEntity> pager = supplierService.findSupplierEmployeeByName(page,pageSize,name);
        //总条数
        resultMap.put("total", pager.getTotal());
        //获取每页数据
        resultMap.put("rows", pager.getList());
        return new JsonResult(resultMap);
    }
    //删除员工(作废)
    @RequestMapping(value = "/outSupplierEmployee.fun" , method = RequestMethod.POST)
    @ResponseBody
    public Object outSupplierEmployee(@RequestBody SupplierEmployeeEntity supplierEmployeeEntity) {
        String id =supplierEmployeeEntity.getId();
        Integer num=  supplierService.outSupplierEmployee(id);
        return new JsonResult(num);
    }
}
