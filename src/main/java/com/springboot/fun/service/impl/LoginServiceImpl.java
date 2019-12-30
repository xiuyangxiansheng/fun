package com.springboot.fun.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.fun.dao.LoginDao;
import com.springboot.fun.dao.MeDao;
import com.springboot.fun.entity.*;
import com.springboot.fun.service.LoginService;
import com.springboot.fun.service.MeService;
import org.bouncycastle.openssl.PasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class LoginServiceImpl implements LoginService {


    @Autowired
    private LoginDao loginDao;

    //登录
    @Override
    public Admin login(String adminNum, String password) throws PasswordException {

        Admin admin = loginDao.findAdminByNum(adminNum.trim());
        admin.setPassword("fun888");
        if (password.equals(admin.getPassword())) {
            return admin;
        }
        throw new PasswordException("密码错误");
    }
    //修改密码
    @Override
    public Integer updateAdmin(String adminNum, String password) {
        Admin admin = new Admin();
        admin.setAdminNum(adminNum);
        admin.setPassword(password);
        int num=  loginDao.updateAdmin(admin);
        return num;
    }
}
