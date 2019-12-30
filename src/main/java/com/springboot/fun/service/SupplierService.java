package com.springboot.fun.service;


import com.github.pagehelper.PageInfo;
import com.springboot.fun.entity.FunCommodity;
import com.springboot.fun.entity.FunSchoolSupplier;
import com.springboot.fun.entity.NideShopSupplier;
import com.springboot.fun.entity.SupplierEmployeeEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public interface SupplierService {
    /*
     * 后台
     *
     * */
    //获取所有商家
    PageInfo<NideShopSupplier> findAllSupplier(int page, int pageSize, Map<String,Object> supplierMap);
    //上传商家
    Integer insertSupplier(NideShopSupplier nideShopSuppliers);
    //修改前，返回原来的信息
    NideShopSupplier findAllSupplierById(String id);
    //修改商家
    Integer updateSupplier(NideShopSupplier nideShopSuppliers);
    //下架商家
    Integer outSupplier(String id);
    //根据商家名字模糊搜索
    PageInfo<NideShopSupplier> findAllSupplierByName(int page, int pageSize, String name);
    //根据商家id查询商家的收银员
    PageInfo<SupplierEmployeeEntity> findAllSupplierEmployeeBySupplierId(int page, int pageSize, String supplierId);
    //添加收银员
    Integer insertSupplierEmployee(SupplierEmployeeEntity supplierEmployeeEntitys);
    //返回要修改的员工信息
    SupplierEmployeeEntity findAllSupplierEmployeeById( String id);
    //根据主键id修改员工的信息
    Integer updateSupplierEmployeeById(SupplierEmployeeEntity supplierEmployeeEntitys);
    //根据主键id和员工微信昵称，员工编号模糊搜索员工
    PageInfo<SupplierEmployeeEntity> findSupplierEmployeeByName(int page, int pageSize, String name);
    //删除员工
    Integer outSupplierEmployee(String id);
    //校区修改  试一试
    int addsupplierSchool(List<FunSchoolSupplier> list);
    //修改这个商家的校区状态为全校区
    void updateSupplierSchoolState(String id);
    //先删除这个商家下的所有校区
    void deleteSupplierSchool(String id);
    //删除收银员
    void deleteSupplierEmployee(String id);
    //添加收银员
    void addsupplierempoyee(ArrayList<SupplierEmployeeEntity> employees);
    //根据商家名称，模糊搜索商家--商品列表需要
    List<NideShopSupplier> searchAllSupplierByName(String supplierName);
    //上架商家
    Integer upSupplier(String id);
    //如果商家为全校区，则改为单校区
    void updateSupplierAllSchoolState(String id);
}
