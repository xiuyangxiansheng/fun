package com.springboot.fun.dao;

import com.springboot.fun.entity.Admin;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface LoginDao {

    //登录
    Admin findAdminByNum(String trim);
    //修改密码
    int updateAdmin(Admin admin);

}
