package com.springboot.fun.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.fun.dao.SupplierDao;
import com.springboot.fun.entity.*;
import com.springboot.fun.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.function.SupplierUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class SupplierServiceImpl implements SupplierService {


    @Autowired
    private SupplierDao supplierDao;


    /*
     * 后台
     *
     * */
    //获取所有商家
    @Override
    public PageInfo<NideShopSupplier> findAllSupplier(int page, int pageSize, Map<String,Object> supplierMap) {
        PageHelper.startPage(page, pageSize);
        List<NideShopSupplier> lists = new ArrayList<>();
        lists = supplierDao.findAllSupplier(supplierMap);
        PageInfo<NideShopSupplier> pi = new PageInfo<>(lists);
        return pi;
    }
    //上传商家
    @Override
    public Integer insertSupplier(NideShopSupplier nideShopSuppliers) {
        return supplierDao.insertSupplier(nideShopSuppliers);
    }
    //修改前，返回原来的信息
    @Override
    public NideShopSupplier findAllSupplierById(String id) {
        return supplierDao.findAllSupplierById(id);
    }
    //修改商家
    @Override
    public Integer updateSupplier(NideShopSupplier nideShopSuppliers) {
        return supplierDao.updateSupplier(nideShopSuppliers);
    }
    //下架商家
    @Override
    public Integer outSupplier(String id) {
        return supplierDao.outSupplier(id);
    }
    //根据商家名字模糊搜索
    @Override
    public PageInfo<NideShopSupplier> findAllSupplierByName(int page, int pageSize, String name) {
        PageHelper.startPage(page, pageSize);
        List<NideShopSupplier> lists = new ArrayList<>();
        lists = supplierDao.findAllSupplierByName(name);
        PageInfo<NideShopSupplier> pi = new PageInfo<>(lists);
        return pi;
    }
    //根据商家id查询商家的收银员
    @Override
    public PageInfo<SupplierEmployeeEntity> findAllSupplierEmployeeBySupplierId(int page, int pageSize, String supplierId) {
        PageHelper.startPage(page, pageSize);
        List<SupplierEmployeeEntity> lists = new ArrayList<>();
        lists = supplierDao.findAllSupplierEmployeeBySupplierId(supplierId);
        PageInfo<SupplierEmployeeEntity> pi = new PageInfo<>(lists);
        return pi;
    }
    //添加收银员
    @Override
    public Integer insertSupplierEmployee(SupplierEmployeeEntity supplierEmployeeEntitys) {
        return supplierDao.insertSupplierEmployee(supplierEmployeeEntitys);
    }
    //返回要修改的员工信息
    @Override
    public SupplierEmployeeEntity findAllSupplierEmployeeById( String id) {
        return supplierDao.findAllSupplierEmployeeById(id);
    }
    //根据主键id修改员工的信息
    @Override
    public Integer updateSupplierEmployeeById(SupplierEmployeeEntity supplierEmployeeEntitys) {
        return supplierDao.updateSupplierEmployeeById(supplierEmployeeEntitys);
    }
    //根据主键id和员工微信昵称，员工编号模糊搜索员工
    @Override
    public PageInfo<SupplierEmployeeEntity> findSupplierEmployeeByName(int page, int pageSize, String name) {
        PageHelper.startPage(page, pageSize);
        List<SupplierEmployeeEntity> lists = new ArrayList<>();
        lists = supplierDao.findSupplierEmployeeByName(name);
        PageInfo<SupplierEmployeeEntity> pi = new PageInfo<>(lists);
        return pi;
    }
    //删除员工
    @Override
    public Integer outSupplierEmployee(String id) {
        return supplierDao.outSupplierEmployee(id);
    }
    //校区修改  试一试
    @Override
    public int addsupplierSchool(List<FunSchoolSupplier> list) {
        return supplierDao.addsupplierSchool(list);
    }
    //修改这个商家的校区状态为全校区
    @Override
    public void updateSupplierSchoolState(String id) {
        supplierDao.updateSupplierSchoolState(id);
    }
    //先删除这个商家下的所有校区
    @Override
    public void deleteSupplierSchool(String id) {
        supplierDao.deleteSupplierSchool(id);
    }
    //删除收银员
    @Override
    public void deleteSupplierEmployee(String id) {
        supplierDao.deleteSupplierEmployee(id);
    }
    //添加收银员
    @Override
    public void addsupplierempoyee(ArrayList<SupplierEmployeeEntity> employees) {
        supplierDao.addsupplierempoyee(employees);
    }
    //根据商家名称，模糊搜索商家--商品列表需要
    @Override
    public List<NideShopSupplier> searchAllSupplierByName(String supplierName) {
        return supplierDao.searchAllSupplierByName(supplierName);
    }
    //上架商家
    @Override
    public Integer upSupplier(String id) {
        return supplierDao.upSupplier(id);
    }
    //如果商家为全校区，则改为单校区
    @Override
    public void updateSupplierAllSchoolState(String id) {
        supplierDao.updateSupplierAllSchoolState(id);
    }


}
