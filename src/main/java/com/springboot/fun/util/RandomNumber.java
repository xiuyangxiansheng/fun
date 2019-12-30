package com.springboot.fun.util;

public class RandomNumber {
    public static String random(int length) {
        String random = "";
        /*随机数函数*/
        java.util.Random r = new java.util.Random();
        for (int i = 0; i < length; i++) {
            /*生成36以内的随机数，不含36，并转化为36位*/
            random += Integer.toString(r.nextInt(9)+1, 10);//参数里面含0
            /*random += Integer.toString(r.nextInt(9) + 1, 10);*/
        }
        System.out.println(random);
        return random;
    }
}