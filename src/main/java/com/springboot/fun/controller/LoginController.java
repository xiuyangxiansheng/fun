package com.springboot.fun.controller;

import com.springboot.fun.entity.Admin;
import com.springboot.fun.service.LoginService;
import com.springboot.fun.util.JsonResult;
import org.bouncycastle.openssl.PasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@CrossOrigin(origins = "*",maxAge = 3600)
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    /*数据库druid配置
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.jdbc.Driver
    filters: stat
        maxActive: 20
        initialSize: 1
        maxWait: 60000
        minIdle: 1
        timeBetweenEvictionRunsMillis: 60000
        minEvictableIdleTimeMillis: 300000
        validationQuery: select 'x'
        testWhileIdle: true
        testOnBorrow: false
        testOnReturn: false
        poolPreparedStatements: true
        maxOpenPreparedStatements: 20*/

    //登录
    @RequestMapping(value = "/adminLogin.fun")
    @ResponseBody
    public Object login(String adminNum, String password, HttpSession session, HttpServletRequest request, ModelMap model, Admin user, Boolean rememberMe, String verifycode) throws PasswordException {
        System.out.println("进来了");
        Admin admin = loginService.login(adminNum, password);
        //登录成功时候,将admin信息保存到session用于在过滤器中检查登录情况
        session.setAttribute("loginAdmin", admin);

        //TODO 用户密码校验逻辑省略...
        //TODO 验证码...
        //登入成功
        model.addAttribute("user", user);

        return new JsonResult(admin);
    }
    //修改密码
    @RequestMapping(value = "/updateAdmin.fun" ,method = RequestMethod.POST)
    @ResponseBody
    public Object updateAdmin(@RequestBody Admin admin){
        String adminNum=admin.getAdminNum();
        String password=admin.getPassword();
        Integer  num = loginService.updateAdmin(adminNum, password);
        return new JsonResult(num);
    }

}
