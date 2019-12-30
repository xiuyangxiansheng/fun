package com.springboot.fun.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//把时间转为时间戳
public class DateFormat {
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
}
