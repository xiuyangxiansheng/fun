package com.springboot.fun.service;


import com.springboot.fun.entity.Admin;
import org.bouncycastle.openssl.PasswordException;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    //登录
    Admin login(String adminNum, String password) throws PasswordException;
    //修改密码
    Integer updateAdmin(String adminNum, String password);
}
