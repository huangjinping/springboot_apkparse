package com.example.demo;

import com.example.demo.utils.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainTest {


    public static void msgFeatureV3() {
        try {
            Map<String, String> mapParam = new HashMap<>();
            Map<String, String> header = new HashMap<>();
            Map<String, File> fileMap = new HashMap<>();
            String textByPath = FileUtils.getTextByPath("/Users/huhuijie/Documents/GitHub/springboot_apkparse/json/新建文本sss文档.txt");
            LogUtils.logJson(textByPath);
            String compress = GzipUtil.compress(textByPath);
//             compress = FileUtils.getTextByPath("/Users/huhuijie/Documents/GitHub/springboot_apkparse/json/after.txt");

            LogUtils.logJson(compress);
            String encrypt = AESUtil.encrypt(compress, "7f43434a595cf2487c29c563217955f1");
//          String encrypt = AESUtil.encrypt(compress, "833145a1c66db7519277de45de749097");
            LogUtils.logJson(encrypt);

            String respStr = OkHttpUtils.postJson("http://10.1.2.40:8092/msgFeature", encrypt, header);

            LogUtils.logJson(respStr);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        System.out.println(RegexUtils.isTel1("123416789"));

//        Calendar instance = Calendar.getInstance();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String format = simpleDateFormat.format(instance.getTime());
//        LogUtils.log("-----" + format);
//        instance.add(Calendar.DAY_OF_MONTH,21);
//         format = simpleDateFormat.format(instance.getTime());
//        LogUtils.log("-----" + format);

        String HOME_PATH = "/Users/huhuijie/Documents/git/dart-cli-tools/project/";
//        String s = buildCMD("/Users/huhuijie/Downloads/BelArgent");
//        System.out.println(s);
//        msgFeatureV3();
        try {

//                        {
//                String json = FileUtils.getTextByPath(HOME_PATH + "mexico/guayabacash.json");
//                String fileName = "guayabacash";
//                String appssid = "28";
//                String domainname = "https://mx.ultracreditosmx.com";
//                String phoneNo = "183465458";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }
//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "chile/gogocredito.json");
//                String fileName = "gogocredito";
//                String appssid = "185";
//                String domainname = "https://chile.ultracreditosmx.com";
//                String phoneNo = "183353637";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }
//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "senegal/ouestcredit.json");
//                String fileName = "ouestcredit";
//                String appssid = "149";
//                String domainname = "https://snjr.ultracreditosmx.com";
//                String phoneNo = "183465458";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }


//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "columbia/credibus.json");
//                String fileName = "credibus";
//                String appssid = "26";
//                String domainname = "https://www.credibusco.com";
//                String phoneNo = "18324111";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }
//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "columbia/platalibre.json");
//                String fileName = "platalibre";
//                String appssid = "216";
//                String domainname = "https://glby.ultracreditosmx.com";
//                String phoneNo = "1825396142";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }
//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "senegal/senepret.json");
//                String fileName = "senepret";
//                String appssid = "174";
//                String domainname = "https://snjr.ultracreditosmx.com";
//                String phoneNo = "1839788497";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }
//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "columbia/estrellacredito.json");
//                String fileName = "estrellacredito";
//                String appssid = "182";
//                String domainname = "https://glby.ultracreditosmx.com";
//                String phoneNo = "1821234567";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }
//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "chile/holaprestamo.json");
//                String fileName = "holaprestamo";
//                String appssid = "50";
//                String domainname = "https://chile.ultracreditosmx.com";
//                String phoneNo = "183353637";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

            {
                String json = FileUtils.getTextByPath(HOME_PATH + "chile/holaprestamo.json");
                String fileName = "holaprestamo";
                String appssid = "185";
                String domainname = "https://test.holaprestamocl.com";
                String phoneNo = "183353637";
                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
                inxServerSpiderLocal.start();
            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "columbia/guatemalacash.json");
//                String fileName = "guatemalacash";
//                String appssid = "169";
//                String domainname = "https://wdml.ultracreditosmx.com";
//                String phoneNo = "182333333";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }
//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "guatemala/guatemalacash.json");
//                String fileName = "guatemalacash";
//                String appssid = "169";
//                String domainname = "https://wdml.ultracreditosmx.com";
//                String phoneNo = "182333333";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "peru/prestamoplus.json");
//                String fileName = "prestamoplus";
//                String appssid = "175";
//                String domainname = "https://peru.ultracreditosmx.com/";
////                String domainname = "https://www.prestamopluspe.com/";
//                String phoneNo = "183474747";
////                String phoneNo = "183980089";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "peru/carteraprestamo.json");
//                String fileName = "carteraprestamo";
//                String appssid = "03";
//                String domainname = "https://peru.ultracreditosmx.com/";
//                String phoneNo = "18255551";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }


//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "columbia/plataya.json");
//                String fileName = "plataya";
//                String appssid = "180";
//                String domainname = "https://glby.ultracreditosmx.com";
//                String phoneNo = "183377411";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "columbia/dinercolplus.json");
//                String fileName = "dinercolplus";
//                String appssid = "180";
//                String domainname = "https://www.dinercolplus.com";
//                String phoneNo = "183377411";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//
//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "tanzania/jamboloan.json");
//                String fileName = "jamboloan";
//                String appssid = "197";
//                String domainname = "https://tz.ultracreditosmx.com";
//                String phoneNo = "183333333";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }
//
//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "guatemala/chburocredito.json");
//                String fileName = "chburocredito";
//                String appssid = "50";
//                String domainname = "https://wdml.ultracreditosmx.com";
//                String phoneNo = "1825555";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//
//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "tanzania/mkopofasta.json");
//                String fileName = "mkopofasta";
//                String appssid = "154";
//                String domainname = "https://www.mkopofasta.com";
//                String phoneNo = "183123456";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }


            //
//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "guatemala/nuevocredito.json");
//                String fileName = "nuevocredito";
//                String appssid = "195";
//                String domainname = "https://wdml.ultracreditosmx.com";
//                String phoneNo = "18255551";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "tanzania/mkopofasta.json");
//                String fileName = "mkopofasta";
//                String appssid = "154";
//                String domainname = "https://tz.ultracreditosmx.com";
//                String phoneNo = "183123456";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            Map<String, String> header = new HashMap<>();
////        header.put("v-flag", "true");
////        header.put("apiName", AESUtil.encrypt("/anon/getAppImageList", "0121170eedf910c65bf10b2cf5820202"));
//            String respStr = OkHttpUtils.postForm("https://web.cocoaloanug.com/api/collection/login", header, mapParam);
//            LogUtils.logJson(respStr);
//            JSONObject jsonObject = JSON.parseObject(respStr);

//            InxServerSpider inxSpider = new InxServerSpider();
//            inxSpider.start();
//            Map<String, Object> map = ManiParse.parseAndroidManifest("/Users/huhuijie/Documents/GitHub/springboot_apkparse/src/main/resources/static/AndroidManifest.xml");
//            Gson gson = new Gson();
//            String result = gson.toJson(map);
//            System.out.println(result);
//            String textByPath = FileUtils.getTextByPath("/Users/huhuijie/Documents/GitHub/springboot_apkparse/json/movcolombiapro.json");
//            LogUtils.log(5/20);
//            JsonParser.parseRoot(textByPath);

//            LogUtils.log(CheckUtils.percentage(2,10,2));
//            InxSpider inxSpider = new InxSpider();
//            inxSpider.start();

//            SearchTask searchTask = new SearchTask();
//
//            List<MethodSolr> methodSolr = searchTask.getMethodSolr("/Users/huhuijie/Documents/git/inx-sdk/app/release/app-v3.5-141120221444-release");
//            LogUtils.logJson(methodSolr);
//            List<DomainName> httpList = searchTask.getHttpList("/Users/huhuijie/Documents/bundletool/__UNI__C5B5A12_0921120719");
//            LogUtils.logJson(httpList);
//            List<DomainName> httpsList = searchTask.getHttpsList("/Users/huhuijie/Documents/bundletool/__UNI__C5B5A12_0921120719");
//            LogUtils.logJson(httpsList);

//            List<String> commands = new ArrayList<>();
//            commands.add("ls");
//            commands.add("pwd");
////            commands.add("cd /Users/huhuijie/Documents/bundletool/__UNI__C5B5A12_0921120719");
//            commands.add("grep -rnR 'onReceivedSslError' /Users/huhuijie/Documents/bundletool/__UNI__C5B5A12_0921120719/*");
//            commands.add("pwd");
////            System.out.println("===================before=====》》》》》");
//            List<String> strings = searchTask.executeNewFlow(commands);
////
////            List<DomainName> result = searchTask.getDomainNameByCmd(strings, "https://");
//            LogUtils.logJson(strings);
//            LogUtils.logJson(result.size());

//            Map<String, String> stringObjectMap = PackageParse.parseStringXML("/Users/huhuijie/Documents/git/inx-sdk/app/release/app-v3.5-141120221444-release/res/values/strings.xml", "0");
//            String textByPath = FileUtils.getTextByPath("/Users/huhuijie/Downloads/online11.json");
//
//            Gson gson = new Gson();
//            String key = "c73c94fad68f55df6d5f46e7c79ba9f5";
//
//            String compress = GzipUtil.compress(textByPath);
//            compress = AESUtil.encrypt(compress, key);
//            LogUtils.logJson("----------------1-");
//
//            LogUtils.logJson(compress);
//
//            LogUtils.logJson("----------------2-");
//            compress = AESUtil.decrypt(compress, key);
//            LogUtils.logJson(GzipUtil.unCompress(compress));

//            searchTask.getUrlList("/Users/huhuijie/Documents/bundletool/__UNI__C5B5A12_0921120719/smali");
//            String encrypt = AESUtil.encrypt("/login/getVerifCode", "0121170eedf910c65bf10b2cf5820202");
//
//            LogUtils.log(encrypt);

//            ThreadM threadM=new ThreadM();
//            threadM.threadMethod();

        } catch (Exception e) {
            LogUtils.logJson("---------222-------1-");

            e.printStackTrace();
        }
    }


    public static String buildCMD(String dir) {
        List<String> grepModelList = new ArrayList<>();
        grepModelList.add("\"samsung\"");
        grepModelList.add("\"Meizu\"");
        grepModelList.add("\"Xiaomi\"");
        grepModelList.add("\"OPPO\"");
        StringBuilder builder = new StringBuilder();
        builder.append("grep -rnR '");
        for (int i = 0; i < grepModelList.size(); i++) {
            String res = grepModelList.get(i);
            builder.append(res);
            if (i < grepModelList.size() - 1) {
                builder.append("\\|");
            }
        }
        builder.append("' " + dir);
        return builder.toString();
    }


}
