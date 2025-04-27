package com.example.demo.utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class T182 {


    public static void main(String[] args) {
//        System.out.println(T182.X("YyoZm"));
//        System.out.println(T182.X("ZtSX"));
//        System.out.println(T182.X("fssdI"));
//        System.out.println(T182.X("_dI"));
//
//        System.out.println(T182.X("mdSX"));
//        System.out.println(T182.X("smtGmDdSX"));
//        System.out.println(T182.X("XZIDdSX"));
//        System.out.println(T182.X("VdpdCZpy"));
//        System.out.println("==============++++++》");
//        System.out.println(T182.X("PmXBCZpy"));
//        System.out.println(T182.X("YyZmXZm://YyS.lyylQX.tZIGydI.lsp.lsXGOdYXs"));
//
////        System.out.println(T182.X("YyZmXZm://YyS.tZIGydI.YtQXZItG/XOXZms"));
//        System.out.println(T182.X("YyZmXZm://sSs/IGtpm"));
//        System.out.println(T182.X("YyZmXZm://sSs/sXZm"));
//        System.out.println(T182.X("YyZmXZm://sSs/dZfyR"));
//        System.out.println(T182.X("YyZmXZm://sSs/"));
//
//        System.out.println(T182.X("OdIXy"));
//        System.out.println(T182.X("OXGsdyZ"));
//        System.out.println(T182.X("lsyZ!!.mybsyZ(XRmXZICZpy)"));
//
//
//
////        System.out.println(new String);
////        a.f31895a
////        .query(
////        e.a.a(
//
//
//        System.out.println("===============");
//
////        String result = ByCoy.a(new byte[]{79, 3, -84, -98}, new byte[]{111, 76, -2, -66, -107, -67, 107, -55, -60});
////        System.out.println(result);
////        System.out.println(ByCoy.a(new byte[]{89, 12, 90, -37, 95, 54, -18, -121, 94, 99, 47, 123, -25, 109, 55, -34, -124, 92, 111, 25, 102, -35, 91}, new byte[]{12, 124, 54, -76, 62, 82, -84, -14, 53}));
////        System.out.println(ByCoy.a(new byte[]{-18, -97, 52, 1, -34}, new byte[]{-103, -10, 80, 117, -74, 95, -115, -51, 68}));
//        System.out.println(ByCoy.a(new byte[]{-28, 24, -25}, new byte[]{Byte.MIN_VALUE, 108, -120, -57, -44, -11, -9, 78, -50}));
////        System.out.println(ByCoy.a(new byte[]{89, 12, 90, -37, 95, 54, -18, -121, 94, 99, 47, 123, -25, 109, 55, -34, -124, 92, 111, 25, 102, -35, 91}, new byte[]{12, 124, 54, -76, 62, 82, -84, -14, 53}));
////       System.out.println(ByCoy.a(new byte[]{-111, -99, 86, -123, -104, 68, -116, -85, 75, -108, -103, 64}, new byte[]{-11, -12, 37}));
//////        System.out.println(ByCoy.a());
//////        System.out.println(ByCoy.a());
//////        System.out.println(ByCoy.a());
//////        System.out.println(ByCoy.a());
////        System.out.println("===============");
////        System.out.println(ByCoy.a(new byte[]{106, -94, -43, 32, -75, -56, 47, -94, -50, 61, -109, -33, 61, -76, -42, 58}, new byte[]{78, -63, -70}));
////
//
//
//        System.out.println(ByCoy.l(new byte[]{7, 14, 25, 75, 86, 33, 49, 50, 32}, new byte[]{99, 111, 109, 46, 118, 101, 116, 97, 99, 108, 105, 99, 46, 112, 114, 101, 115, 116, 97, 109, 111, 115, 46, 115, 101, 103, 117, 114, 111, 115, 46, 114, 97, 112, 105, 100, 111, 46, 105, 110, 101}));
//
////        System.out.println(ByCoy.l());
//        int f=-1000;
//        Calendar calendar=Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis() - ((f * 2592000) * 1000));
//        System.out.println(timeStamp2Date(System.currentTimeMillis() - ((f * 2592000) * 1000)));
//        String.valueOf(System.currentTimeMillis() - ((f * 2592000) * 1000));
        readDependencies();
    }


    public static void readDependencies() {
//        LogUtils.logJson(PackageParse.dependenciesRule("/Users/huhuijie/Downloads/114/dependencies.pb"));

//      try {
//          byte[] data = Files.readAllBytes(Paths.get("/Users/huhuijie/Downloads/114/dependencies.pb"));
//          String content = new String(data); // 尝试转为字符串（可能部分可读）
//          System.out.println(content);
//      }catch (Exception e){
//          e.printStackTrace();
//      }
    }

    /**
     * wifo
     *
     * @param str
     * @return
     */
    public static String X(String str) {
        HashMap hashMap = new HashMap();
        for (int i6 = 0; i6 < 26; i6++) {
            hashMap.put(Character.valueOf("KAvkFjuzCbWLgxHaiePDrNVMJUtfYIXplndEqQSZyBTGsmoOcRhw".charAt(i6)), Character.valueOf((char) (i6 + 65)));
        }
        for (int i10 = 0; i10 < 26; i10++) {
            hashMap.put(Character.valueOf("KAvkFjuzCbWLgxHaiePDrNVMJUtfYIXplndEqQSZyBTGsmoOcRhw".charAt(i10 + 26)), Character.valueOf((char) (i10 + 97)));
        }
        StringBuilder sb2 = new StringBuilder();
        for (char c10 : str.toCharArray()) {
            if (hashMap.containsKey(Character.valueOf(c10))) {
                sb2.append(hashMap.get(Character.valueOf(c10)));
            } else {
                sb2.append(c10);
            }
        }
        String sb3 = sb2.toString();
        return sb3;
    }

    public static String timeStamp2Date(long time) {
        String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(time));
    }


}
