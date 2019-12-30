package com.springboot.fun.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class RandomImgName {
	public static String getOrderIdByUUId() {
        int machineId = 1;//�?大支�?1-9个集群机器部�?
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数
            hashCodeV = - hashCodeV;
        }
        // 0 代表前面补充0     
        // 4 代表长度�?4     
        // d 代表参数为正数型
        return new SimpleDateFormat("yyyyMMdd").format(new Date())+machineId + String.format("%07d", hashCodeV);
    }
    public static void main(String[] args) {
        System.out.println(getOrderIdByUUId());
    }		 
}
