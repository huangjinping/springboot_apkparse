package com.example.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    //    yyyy-MM-dd HH:mm:ss
    public static String strDateFormat = "yyyyMMddHHmmssSSS";//设置日期格式
    
    public static String getCurrentString() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strDateFormat);
        return simpleDateFormat.format(date);
    }

}
