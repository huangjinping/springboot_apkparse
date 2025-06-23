package com.example.demo.utils;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Jentity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
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


    public static boolean checkVersionCode(String versionCode) {
        /**
         * https://developer.android.com/studio/publish/versioning?hl=zh-cn
         */
        boolean result = true;
        try {
            if (versionCode.startsWith("0")) {
                return false;
            }

            int code = Integer.parseInt(versionCode);
            if (code > 2000000000) {
                return false;
            }
            if (code < 1) {
                return false;
            }
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    public static boolean checkVersionName(String versionName) {
        boolean result = false;
        try {
            String[] arr = versionName.split("[.]");
            if (arr != null && arr.length == 3) {
                boolean itemResult = true;
                for (String item : arr) {
                    if (item.length() != 1) {
                        itemResult = false;
                        break;
                    }
                    int intItem = Integer.parseInt(item);
                    if (intItem >= 0 && intItem < 10) {
                    } else {
                        itemResult = false;
                    }
                }
                result = itemResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
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
                value = value.toUpperCase().replace("E", "");

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


    static int getSaferLimitInt(JSONObject doc, String key, int min, int max) {
        if (doc.containsKey(key)) {
//            try {
//                if (doc.get(key) instanceof Integer) {
//
//                } else {
//                    return 0;
//                }
//            } catch (Exception e) {
//                return 0;
//            }
            try {
                String value = doc.getString(key);
                value = value.toUpperCase().replace("E", "");

                int val = Integer.parseInt(value);
                if (val >= min && val <= max) {
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
                value = value.toUpperCase().replace("E", "");
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


    public static int getSaferLimitDoubleRule1(JSONObject doc, String key, double limit) {
        if (doc.containsKey(key)) {
            try {
                String value = doc.getString(key);

                if (checkRepeat(value)) {
                    return 0;
                }

                value = value.toUpperCase().replace("E", "");
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


    public static Jentity getJentityByMapForArrayOrientationsIpad(String key, Map<String, Object> info) {

        Jentity result = new Jentity("" + key, "", 0);
        if (info != null && !TextUtils.isEmpty(key) && info.containsKey(key)) {
            Object value = info.get(key);
            LogUtils.logJson(value);
            LogUtils.logJson(value.getClass().getSimpleName());
            result.setValue(value);
            if (value != null && ((Object[]) value).length == 4) {
                result.setState(1);
            }
        }
        return result;
    }

    public static Jentity getJentityByMap(String key, Map<String, Object> info) {

        Jentity result = new Jentity("" + key, "", 0);

        if (info != null && !TextUtils.isEmpty(key) && info.containsKey(key)) {
            result.setValue(info.get(key));
            if (info.get(key) != null && !TextUtils.isEmpty(info.get(key).toString())) {
                result.setState(1);
            }
        }
        return result;
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


    public static boolean onCheckInteger(JSONObject doc, String key) {
        try {
            if (doc.get(key) instanceof Integer) {

                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }


    public static boolean checkNumber(String number) {
        return RegexUtils.isMatch(RegexConstants.REGEX_All_NUM, number);
    }

    public static int getSaferStringContractPhoneNumber(JSONObject doc, String key) {
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
            Calendar calendar = Calendar.getInstance();

            Calendar calTarget = Calendar.getInstance();

            String value = doc.getString(key);
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
            Date parse = sdf.parse(value);
            calTarget.setTime(parse);
            if (calTarget.after(calendar)) {
                return 0;
            }
            String min = "2000/12/12 10:10:10";
            Calendar minCal = Calendar.getInstance();
            minCal.setTime(sdf.parse(min));
            if (calTarget.before(minCal)) {
                return 0;
            }


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
        return !TextUtils.isEmpty(mac) && mac.length() > 10 && mac.contains(":");
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

    /**
     * true  hand repeat .false  no hanse repeat
     *
     * @param input
     * @return
     */
    public static boolean checkRepeat(String input) {
//        String input = "abcdddeffgh";
        // 定义正则表达式
        String regex = "(.)\\1\\1";
        // 创建Pattern对象
        Pattern pattern = Pattern.compile(regex);
        // 创建Matcher对象
        Matcher matcher = pattern.matcher(input);

        // 进行匹配
        return matcher.find();

    }


}
