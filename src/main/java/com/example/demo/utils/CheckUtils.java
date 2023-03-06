package com.example.demo.utils;

import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class CheckUtils {


    final static String regex = "[0-9]+";

    /**
     * @param d1：分子；
     * @param zs：分母；
     * @param dot：要保留的小数
     * @return
     * @Description: 计算百分比
     * @date 2022/4/2 16:50
     */
    public static double percentage(double d1, double zs, int dot) {
        double bs = Math.pow(10.0, toDouble(dot + 2, 0.0));
        double cs = Math.pow(10.0, toDouble(dot, 0.0));
        double num = 0.0;
        if (zs > 0) {
            num = Math.floor(d1 * bs / zs) / cs;
        }
        return num;
    }

    /**
     * @param d1：分子；
     * @param zs：分母；
     * @param dot：要保留的小数
     * @return
     * @Description: 计算百分比
     * @date 2022/4/2 16:50
     */
    public static double percentage(int d1, int zs, int dot) {
        double bs = Math.pow(10.0, toDouble(dot + 2, 0.0));
        double cs = Math.pow(10.0, toDouble(dot, 0.0));
        double num = 0.0;
        if (zs > 0) {
            num = Math.floor(d1 * bs / zs) / cs;
        }
        return num;
    }

    private static Double toDouble(Object value, Double defaultValue) {
        if (null == value) {
            return defaultValue;
        }
        return Double.parseDouble(value.toString());
    }


    public static String filterValue(String input) {
        input = input.replace("&", "&amp;");
        input = input.replace("\"", "&quot;");
        input = input.replace("<", "&lt;");
        input = input.replace(">", "&gt;");
        input = input.replace("'", "'");

        return input;
    }

    static int getSaferLimitInt(JSONObject doc, String key, int limit) {
        if (doc.containsKey(key)) {
            try {
                String value = doc.getString(key);
                if (Integer.parseInt(value) >= limit) {
                    return 1;
                }
            } catch (Exception e) {
//                e.printStackTrace();
            }
            doc.getString(key);

        }
        return 0;
    }

    public static int getSaferLimitDouble(JSONObject doc, String key, double limit) {
        if (doc.containsKey(key)) {
            try {
                String value = doc.getString(key);
                if (Double.parseDouble(value) >= limit) {
                    return 1;
                }
            } catch (Exception e) {
//                e.printStackTrace();
            }
            doc.getString(key);

        }
        return 0;
    }

    public static int getSaferStringWithLimit(JSONObject doc, String key, String... args) {

        if (doc.containsKey(key)) {
            try {
                String value = doc.getString(key);
                for (String item : args) {
                    if (item.equals(value)) {
                        return 1;
                    }
                }
            } catch (Exception e) {
//                e.printStackTrace();
            }
            doc.getString(key);

        }


        return 0;
    }

    public static boolean checkNumber(String number) {
        return RegexUtils.isMatch(RegexConstants.REGEX_All_NUM, number);
    }

    public static int getSaferStringPhoneNumber(JSONObject doc, String key) {
        try {
            String string = doc.getString(key);
            if (TextUtils.isEmpty(string)) {
                return 0;
            }

            if (!checkNumber(string)) {
                return 0;
            }


            return 1;
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return 0;
    }

    public static int getSaferStringWithTimeTemp01(JSONObject doc, String key) {

        try {
            String string = doc.getString(key);
            if (TextUtils.isEmpty(string)) {
                return 0;
            }

            Date date = new Date();//为系统当前时间
            String strDateFormat = "yyyy-MM-dd HH:mm:ss";//设置日期格式

            long longValue = doc.getLongValue(key);
            if (longValue < 1000) {
                return 0;
            }
            date.setTime(longValue);
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
            sdf.format(date);
            return 1;
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return 0;
    }

    public static int getSaferStringWithTimeTemp03(JSONObject doc, String key) {

        try {
            String string = doc.getString(key);
            if (TextUtils.isEmpty(string)) {
                return 0;
            }

//            Date date = new Date();//为系统当前时间
            String strDateFormat = "yyyy-MM-dd HH:mm:ss";//设置日期格式
//            date.setTime(longValue);
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
            sdf.parse(string);
            LogUtils.log("=======startTime=====format=2=====");


            return 1;
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return 0;
    }

    public static int getSaferStringWithTimeTempApplication(JSONObject doc, String key) {

        try {


            String string = doc.getString(key);


            if (string.endsWith("000")) {
                int saferLimitDouble = getSaferLimitDouble(doc, key, 1000000);
                if (saferLimitDouble == 1) {
                    return 1;
                }
            }

            if (TextUtils.isEmpty(string)) {
                return 0;
            }


            if (string.length() != 13) {
                return 0;
            }

            Date date = new Date();//为系统当前时间
            String strDateFormat = "yyyy-MM-dd HH:mm:ss";//设置日期格式


            long longValue = Long.parseLong(string);
//            long longValue = doc.getLongValue(key);
            date.setTime(longValue);
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
            sdf.format(date);
            return 1;
        } catch (Exception e) {
//            e.printStackTrace();
        }


        return 0;
    }

    public static int getSaferStringWithTimeTemp(JSONObject doc, String key) {

        try {


            String string = doc.getString(key);
            if (TextUtils.isEmpty(string)) {
                return 0;
            }

            if (string.length() != 13) {
                return 0;
            }

            Date date = new Date();//为系统当前时间
            String strDateFormat = "yyyy-MM-dd HH:mm:ss";//设置日期格式
//            long longValue = doc.getLongValue(key);
            long longValue = Long.parseLong(string);
            date.setTime(longValue);
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
            sdf.format(date);
            return 1;
        } catch (Exception e) {
//            e.printStackTrace();
        }


        return 0;
    }


    public static int getSaferStringWithTime01(JSONObject doc, String key) {

        try {
            String strDateFormat = "yyyy/MM/dd HH:mm:ss";//设置日期格式

            String value = doc.getString(key);
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
            sdf.parse(value);
            return 1;
        } catch (Exception e) {
//            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Ip校验
     *
     * @param ipAddress 单个ip
     * @return
     */
    public static boolean isValidIPAddress(String ipAddress) {
        if ((ipAddress != null) && (!ipAddress.isEmpty())) {
            return Pattern.matches("^([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}$", ipAddress);
        }
        return false;
    }


    public static boolean isMac(String mac) {
        if (!TextUtils.isEmpty(mac) && mac.length() > 10 && mac.contains(":")) {
            return true;
        }
        return false;
    }


    /**
     * IP校验
     *
     * @param str 字符串多个ip ,分割
     * @return
     */
    public static boolean isValidIPAddressMore(String str) {
        String[] ips = str.split(",");
        for (String ipAddress : ips) {
            if (Pattern.matches("^([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}$", ipAddress)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }


    public static int checkRealPath(String realPath) {
        try {
            realPath = realPath.replace(":", "/");
            realPath = realPath.replace("?", "/");
            String[] split = realPath.split("/");
            if (split.length > 0 && RegexUtils.isIP(split[0])) {
                return -1;
            }

            if (split.length > 0 && split[0].contains("ultracreditosmx.com")) {
                return -1;
            }
        } catch (Exception e) {
        }

        return 0;
    }

}
