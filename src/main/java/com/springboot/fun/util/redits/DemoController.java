package com.springboot.fun.util.redits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {


    @Autowired
    private RedisService redisService ;


    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public void demoTest(){
      /*  redisService.set("1","value22222");*/
        System.out.println(redisService.get("1"));
    }

}