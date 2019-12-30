package com.springboot.fun.util;

import java.util.UUID;

/**
 * UUID 工具类
 * @author lin
 *
 */
public class UuidUtils {

    public static String creatUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }	

     public static void main(String[] args) {
         for(int i=0;i<10000;  i++){
             System.out.println(UuidUtils.creatUUID());
         }

	}
}
