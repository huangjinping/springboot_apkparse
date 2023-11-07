package com.example.demo;

import com.example.demo.bean.CommonModel;
import com.example.demo.utils.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MainTest {


    public static void msgFeatureV3() {
        try {
//            Map<String, String> mapParam = new HashMap<>();
//            Map<String, String> header = new HashMap<>();
//            Map<String, File> fileMap = new HashMap<>();
//            String textByPath = FileUtils.getTextByPath("/Users/huhuijie/Documents/GitHub/springboot_apkparse/json/新建文本sss文档.txt");
//            LogUtils.logJson(textByPath);
//            String compress = GzipUtil.compress(textByPath);
////             compress = FileUtils.getTextByPath("/Users/huhuijie/Documents/GitHub/springboot_apkparse/json/after.txt");
//
//            LogUtils.logJson(compress);
//            String encrypt = AESUtil.encrypt(compress, "7f43434a595cf2487c29c563217955f1");
////          String encrypt = AESUtil.encrypt(compress, "833145a1c66db7519277de45de749097");
//            LogUtils.logJson(encrypt);
//
//            String respStr = OkHttpUtils.postJson("http://10.1.2.40:8092/msgFeature", encrypt, header);
//
//            LogUtils.logJson(respStr);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        System.out.println(RegexUtils.isTel1("123416789"));
//
        try {
//            String time1 = "2023-09-08 09:53:40";
//            String strDateFormat = "yyyy-MM-dd HH:mm:ss";//设置日期格式
//            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(strDateFormat);
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//            Calendar instance = Calendar.getInstance();
//            instance.setTime(simpleDateFormat2.parse(time1));
//            instance.add(Calendar.DAY_OF_MONTH, 13);
//            String format = simpleDateFormat.format(instance.getTime());
//            LogUtils.log("-----" + format);

//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            String format = simpleDateFormat.format(instance.getTime());
////        LogUtils.log("-----" + format);
//            instance.add(Calendar.DAY_OF_MONTH, 21);
//            format = simpleDateFormat.format(instance.getTime());
//            List<Integer> dataList = new ArrayList<>();
//            dataList.add(0, 12);
//            dataList.add(0, 13);
//            dataList.add(0, 14);
//            dataList.add(0, 15);
//            LogUtils.logJson(dataList);
//            onPreAmount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean result = CheckUtils.checkVersionName("0.0.1");
        System.out.println("checkVersionName:" + result);
        boolean codeResult = CheckUtils.checkVersionCode("1");
        System.out.println("checkVersionCode:" + codeResult);
//        LogUtils.log("-----" + format);

//        LogUtils.log("com.prestamo.profin.loan.super.dinero.efectivo.credito.credit.cash".length());

//        LogUtils.log("com.eastb.com.eastb.com.eastb.com.eastb.com.eastb.c".length());
//
//        LogUtils.log("com.bolivia.paypal.iqoption.pacifico.bolivariano".length());
        String HOME_PATH = "/Users/huhuijie/Documents/git/dart-cli-tools/project/";
//        String s = buildCMD("/Users/huhuijie/Downloads/BelArgent");
//        System.out.println(s);
//        msgFeatureV3();

//        onScanFile("/Users/huhuijie/Downloads/react_native_jz");
        try {


            {
                String json = FileUtils.getTextByPath(HOME_PATH + "peru/luckyprestamo.json");
                String fileName = "luckyprestamo";
                String appssid = "210";
                String domainname = "https://www.luckyprestamo.com/";
                String phoneNo = "183223118";
                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
                inxServerSpiderLocal.start();
            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "chile/prestamocredito.json");
//                String fileName = "prestamocredito";
//                String appssid = "950";
//                String domainname = "https://chile.ultracreditosmx.com/";
//                String phoneNo = "18200000";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }
//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "columbia/estrellacredito.json");
//                String fileName = "estrellacredito";
//                String appssid = "182";
//                String domainname = "https://glby.ultracreditosmx.com/";
////                https://test.felizsolpe.com/felizsol/nearbyPiano/recommendInternationalJazz
//                String phoneNo = "18200000";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }


//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "chile/jamboloan.json");
//                String fileName = "jamboloan";
//                String appssid = "197";
//                String domainname = "https://chile.ultracreditosmx.com";
//                String phoneNo = "182000000";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }
//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "peru/felizsol.json");
//                String fileName = "felizsol";
//                String appssid = "162";
//                String domainname = "https://test.felizsolpe.com";
////                https://test.felizsolpe.com/felizsol/nearbyPiano/recommendInternationalJazz
//                String phoneNo = "18200000";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "columbia/chburocredito.json");
//                String fileName = "chburocredito";
//                String appssid = "50";
//                String domainname = "https://chile.ultracreditosmx.com";
//                String phoneNo = "18200000";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "egypt/lendhome.json");
//                String fileName = "easyprestamo";
//                String appssid = "120";
//                String domainname = "https://glby.ultracreditosmx.com";
//                String phoneNo = "183660047";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }


//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "columbia/onlinecredito.json");
//                String fileName = "onlinecredito";
//                String appssid = "177";
//                String domainname = "https://glby.ultracreditosmx.com";
//                String phoneNo = "183660047";
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
//                String json = FileUtils.getTextByPath(HOME_PATH + "columbia/rapicredito.json");
//                String fileName = "rapicredito";
//                String appssid = "204";
//                String domainname = "https://test.rapicreditoco.com";
//                String phoneNo = "182100605";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }


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
//                String json = FileUtils.getTextByPath(HOME_PATH + "chile/elefante.json");
//                String fileName = "elefante";
//                String appssid = "186";
//                String domainname = "https://www.elefantecl.com";
//                String phoneNo = "183100611";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }
//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "chile/gogocredito.json");
//                String fileName = "gogocredito";
//                String appssid = "185";
//                String domainname = "https://chile.ultracreditosmx.com";
//                String phoneNo = "183909093";
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

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "chile/holaprestamo.json");
//                String fileName = "holaprestamo";
//                String appssid = "185";
//                String domainname = "https://test.holaprestamocl.com";
//                String phoneNo = "183353637";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

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
//                String json = FileUtils.getTextByPath(HOME_PATH + "columbia/amorcredito.json");
//                String fileName = "amorcredito";
//                String appssid = "211";
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

//            https://test.nuevocreditogt.com/nuevocreditogts/privacy.html
//            https://www.nuevocreditogt.com/nuevocreditogts/privacy.html
//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "columbia/nuevocreditoplus.json");
//                String fileName = "nuevocreditoplus";
//                String appssid = "195";
//                String domainname = "https://test.nuevocreditogt.com";
//                String phoneNo = "18255555";
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

    public static void onScanFile(String path) {

        int poolLength = 10;//需要用的线程个数

        for (int i = 0; i < poolLength; i++) {
            final int index = i;
            StringTask stringTask = new StringTask();
            List<CommonModel> strings = stringTask.searchLogs(path, StringTask.getLenIndex(StringTask.searchLogs2, poolLength, index));
            onPrintLog(strings);
        }
        for (int i = 0; i < poolLength; i++) {
            final int index = i;
            StringTask stringTask = new StringTask();
            List<CommonModel> strings = stringTask.searchLogs(path, StringTask.getLenIndex(StringTask.searchLogs2, poolLength, index));
            onPrintLog(strings);
        }
        for (int i = 0; i < poolLength; i++) {
            final int index = i;
            StringTask stringTask = new StringTask();
            List<CommonModel> strings = stringTask.searchLogs(path, StringTask.getLenIndex(StringTask.searchLogs3, poolLength, index));
            onPrintLog(strings);
        }

    }

    private static void onPrintLog(List<CommonModel> commonModelList) {
        for (CommonModel commonModel : commonModelList
        ) {
            LogUtils.logJson(commonModel.getName());
        }
    }

    public static void onPreAmount() {
        LogUtils.logJson("============onPreAmount========start==============");

        BigDecimal maxCreditAmount = new BigDecimal("100000");
        BigDecimal minCreditAmount = new BigDecimal("34000");
//        BigDecimal incrAmount = new BigDecimal("17000");
//        while (minCreditAmount.compareTo(maxCreditAmount) < 0) {
//
//        }

        String minCreditAmount_s = "34000";
        String maxCreditAmount_s = "10000";

        LogUtils.logJson(minCreditAmount_s.compareTo(maxCreditAmount_s));
        LogUtils.logJson("============onPreAmount========end==============");


    }


}
