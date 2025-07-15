package com.example.demo;

import com.example.demo.bean.CommonModel;
import com.example.demo.utils.FileSizeUtil;
import com.example.demo.utils.LogUtils;
import com.example.demo.utils.StringTask;
import org.eclipse.jgit.api.Git;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainTest {


//    public static void msgFeatureV3() {
//        try {
////            String textByPath = FileUtils.getTextByPath("/Users/huhuijie/Documents/GitHub/springboot_apkparse/json/9002.json");
//            String textByPath = FileUtils.getTextByPath("/Users/huhuijie/Documents/GitHub/springboot_apkparse/json/9003.json");
//
////            String textByPath = FileUtils.getTextByPath("/Users/huhuijie/Downloads/online2.json");
//            LogUtils.logJson(textByPath);
//            textByPath = "0123456789";
//            textByPath = GzipUtil.compress(textByPath);
//            LogUtils.logJson(textByPath);
//            textByPath = AESUtil.encrypt(textByPath, "c8dabcd349f334a269b183837deb43f6");//9002
////            textByPath = AESUtil.encrypt(textByPath, "eb5f389969d354350e3342b8d7a421b0");//9003
//            LogUtils.logJson("=======compress0===============");
//
//            LogUtils.logJson(textByPath);
//
//            LogUtils.logJson("=======compress前===============");
//
////            textByPath = GzipUtil.compress("0123456789");
////            System.out.println(textByPath);
////            LogUtils.logJson(textByPath);
//
//            String filename = UUID.randomUUID().toString();
//
//
//            LogUtils.logJson("=======compress后===============" + filename);
//
//
////            Map<String, String> mapParam = new HashMap<>();
////            Map<String, String> header = new HashMap<>();
////            Map<String, File> fileMap = new HashMap<>();
////            String textByPath = FileUtils.getTextByPath("/Users/huhuijie/Documents/GitHub/springboot_apkparse/json/新建文本sss文档.txt");
////            LogUtils.logJson(textByPath);
////            String compress = GzipUtil.compress(textByPath);
//////             compress = FileUtils.getTextByPath("/Users/huhuijie/Documents/GitHub/springboot_apkparse/json/after.txt");
////
////            LogUtils.logJson(compress);
////            String encrypt = AESUtil.encrypt(compress, "7f43434a595cf2487c29c563217955f1");
//////          String encrypt = AESUtil.encrypt(compress, "833145a1c66db7519277de45de749097");
////            LogUtils.logJson(encrypt);
////
////            String respStr = OkHttpUtils.postJson("http://10.1.2.40:8092/msgFeature", encrypt, header);
////
////            LogUtils.logJson(respStr);
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static List<String> filterBlockedAPIsWithUsage(String filePath) {
        List<String> blockedAPIs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder currentBlock = new StringBuilder();
            boolean isBlockedBlock = false;

            while ((line = br.readLine()) != null) {
                if (line.contains("Linking blocked") || line.contains("Reflection blocked")) {
                    // 如果是新的 blocked API，先保存之前的（如果有）
                    if (isBlockedBlock && currentBlock.length() > 0) {
                        blockedAPIs.add(currentBlock.toString());
                        currentBlock = new StringBuilder();
                    }
                    isBlockedBlock = true;
                    currentBlock.append(line).append("\n");
                } else if (isBlockedBlock) {
                    // 捕获所有关联行（包括 use(s): 和缩进行）
                    if (line.trim().startsWith("use(s):") || line.trim().startsWith("L")) {
                        currentBlock.append(line).append("\n");
                    } else {
                        // 非关联行，结束当前 block
                        if (currentBlock.length() > 0) {
                            blockedAPIs.add(currentBlock.toString());
                            currentBlock = new StringBuilder();
                        }
                        isBlockedBlock = false;
                    }
                }
            }
            // 处理最后一个未保存的 blocked API
            if (isBlockedBlock && currentBlock.length() > 0) {
                blockedAPIs.add(currentBlock.toString());
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return blockedAPIs;
    }

    private static void onAppcompat() {
        String filePath = "/Users/huhuijie/Downloads/506result.txt"; // 替换为你的文件路径
        List<String> blockedAPIs = filterBlockedAPIsWithUsage(filePath);

        System.out.println("Blocked APIs and their usage:");
        for (String api : blockedAPIs) {
            System.out.println(api);
            System.out.println(); // 空行分隔
        }
    }

    public static void main(String[] args) throws IOException {
//        System.out.println(RegexUtils.isTel1("123416789"));

        onAppcompat();
        String source = "I love  china";

//        ApaParser.parseInfoPlist("/Users/huhuijie/Documents/GitHub/springboot_apkparse/json/Info.plist");

        String filePath = "/Users/huhuijie/Downloads/Localizable1.strings"; // 替换为你的Localizable.strings文件路径
//        Map<String, String> localizedStrings = ApaParser.parseLocalizableStrings(filePath);

        try {
//            LogUtils.logJson("------000000000--------------");
//
//            Map<String, Object> objectMap = InfoPlistParser.parsePlist(new File("/Users/huhuijie/Downloads/Info.plist.xml").getAbsolutePath());
//            LogUtils.logJson("------111111--------------");
//            LogUtils.logJson(objectMap);
//            LogUtils.logJson("------222222222--------------");

            String strDateFormat = "yyyy-MM-dd HH:mm:ss";//设置日期格式
//            date.setTime(longValue);
            SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
            Date parse = sdf.parse("2025-06-30 16:00:00 +0000");
            Calendar c = Calendar.getInstance();
            c.setTime(parse);
            int year = c.get(Calendar.YEAR);    //获取年

            int month = c.get(Calendar.MONTH) + 1;   //获取月份，0表示1月份

            int day = c.get(Calendar.DAY_OF_MONTH);
            System.out.println(year + "===========" + month + "===========" + day);

        } catch (Exception e) {
            e.printStackTrace();

        }


//        LogUtils.logJson(localizedStrings);
//        // 打印解析后的键值对
//        for (Map.Entry<String, String> entry : localizedStrings.entrySet()) {
//            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//        }


//        String resultdd = "{\"build_name\":\"1.0.16\",\"images_external\":838,\"video_internal\":0}";
//        System.out.println("--------------------22------------->>>");
//        resultdd = "\u001F\u0000\u0000\u0000\u0000\u0000\u0000\u0000«VJ*ÍÌI\u0089ÏKÌMU²R2Ô3Ð34SÒQÊÌMLO-\u008EO\u00AD(I-ÊKÌQ²²0¶ÐQ*ËLIÍ\u008FÏÌ\u0083\t\u001AÔ\u0002\u00007(¥d@\u0000\u0000\u0000";
//        System.out.println(GzipUtil.compress(resultdd));
//        System.out.println("--------------------23------------->>>");
//        gitDir();
//        LogUtils.logJson("-------1---");
//
//        msgFeatureV3();
//        LogUtils.logJson("-------2---");
//
//        String ios = "A0129254EE9C469BB1B07AA64F459C69";
//        String android = "70c0b65b3a631a6d79864d038d816ce1";
//
//        System.out.println("======ios=========" + ios.length());
//        System.out.println("======android=========" + android.length());


//
        try {


//            String time1 = "2023-11-14 02:22:51";
//            String strDateFormat = "yyyy-MM-dd HH:mm:ss";//设置日期格式
//            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(strDateFormat);
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//            Calendar instance = Calendar.getInstance();
//            instance.setTime(simpleDateFormat2.parse(time1));
////            instance.add(Calendar.DAY_OF_MONTH, 13);
//            long time = instance.getTime().getTime();
//            instance.setTimeInMillis(time + 60);
//
//            String format = simpleDateFormat2.format(instance.getTime());
//            LogUtils.log("-----" + format);
//
//            Map<String, String> map = new HashMap<>();
//            String dddd = map.get("dddd");
//
//
//            System.out.println("=0=====222=======11====" + dddd);
//
//            String scheme = "led0000";
//            if (!RegexUtils.isMatch(RegexConstants.REGEX_SCHEME, scheme)) {
//                System.out.println("=0================");
//
//            } else {
//                System.out.println("=1================");
//
//            }


//            LogUtils.log(FileSizeUtil.getAutoFileOrFilesSize("/Users/huhuijie/Documents/bundletool/app-release.aab"));

//            LogUtils.log(FileSizeUtil.FormetFileSize(21068334l));


//            PackageParse.parseAABLibs("/Users/huhuijie/Downloads/app-release/app-release.aab");

//            LogUtils.logJson(PackageParse.parseLibs("/Users/huhuijie/Downloads/app-release/release_bjstDebt/lib"));
//            LogUtils.logJson(PackageParse.parseLibs("/Users/huhuijie/Documents/GitHub/springboot_apkparse/tempApp/20240103101810921/unzip/base/lib"));
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
//        boolean result = CheckUtils.checkVersionName("0.0.1");
//        System.out.println("checkVersionName:" + result);
//        boolean codeResult = CheckUtils.checkVersionCode("1");
//        System.out.println("checkVersionCode:" + codeResult);
//        LogUtils.log("-----" + format);
//        LogUtils.log("com.prestamo.profin.loan.super.dinero.efectivo.credito.credit.cash".length());
//        LogUtils.log("com.eastb.com.eastb.com.eastb.com.eastb.com.eastb.c".length());
//        LogUtils.log("com.bolivia.paypal.iqoption.pacifico.bolivariano".length());
        String HOME_PATH = "/Users/huhuijie/Documents/git/dart-cli-tools/project/";
//        String s = buildCMD("/Users/huhuijie/Downloads/BelArgent");
//        System.out.println(s);
//        msgFeatureV3();
//        onScanFile("/Users/huhuijie/Downloads/react_native_jz");
        String autoFileOrFilesSize = FileSizeUtil.getAutoFileOrFilesSize("/Users/huhuijie/Downloads/card_back_7654_094647602.jpeg");
        LogUtils.log("======autoFileOrFilesSize=========" + autoFileOrFilesSize);

        try {
//            {
//                /**
//                 * 获取验证码
//                 */
//                InxServerSpiderLocal.startWhatAppCode();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "tanzania/personalcredit.json");
//                String fileName = "personalcredit";
//                String appssid = "271";
//                String domainname = "https://test.personalcredittz.com/";
//                String phoneNo = "1821231232";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }
//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "ivorian/financevite.json");
//                String fileName = "financevite";
//                String appssid = "507";
//                String domainname = "https://temp.ekecredito.com/";
//                String phoneNo = "1821231232";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }
//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "zambia/turbocredit.json");
//                String fileName = "turbocredit";
//                String appssid = "514";
//                String domainname = "https://temp.digitalmoneyzm.com/";
//                String phoneNo = "182123123";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }
//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "ghana/zipmoney.json");
//                String fileName = "zipmoney";
//                String appssid = "513";
//                String domainname = "https://temp.angelacredito.com/";
//                String phoneNo = "1821231222";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }


//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "ecuador/cashya.json");
//                String fileName = "cashya";
//                String appssid = "502";
////                String domainname = "https://temp.fourcredy.com/";
//                String domainname = "https://ecuador.ultracreditosmx.com/";
//                String phoneNo = "182123123";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }


//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "ivorian/argentrapide.json");
//                String fileName = "argentrapide";
//                String appssid = "504";
//                String domainname = "https://temp.cimoneycote.com/";
//                String phoneNo = "1821231223";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }
//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "ivorian/creditexpress.json");
//                String fileName = "creditexpress";
//                String appssid = "505";
//                String domainname = "https://temp.adepaloan.com/";
//                String phoneNo = "182123123";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//
//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "ivorian/pretfacile.json");
//                String fileName = "pretfacile";
//                String appssid = "506";
//                String domainname = "https://temp.biviprestamo.com/";
//                String phoneNo = "1821231232";
////                String phoneNo = "2226665559";
//
//
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }


//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "ecuador/creditoya.json");
//                String fileName = "creditoya";
//                String appssid = "508";
//                String domainname = "https://temp.fourcredy.com/";
//                String phoneNo = "1836786778";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "tanzania/funloan.json");
//                String fileName = "funloan";
//                String appssid = "257";
//                String domainname = "https://tanzania.inxsac.com/";
//                String phoneNo = "182123123";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "ghana/funloan.json");
//                String fileName = "funloan";
//                String appssid = "257";
//                String domainname = "https://jn.prestaclp.com/";
//                String phoneNo = "182123123";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }


//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "ivorian/pretimmediat.json");
//                String fileName = "pretimmediat";
//                String appssid = "478";
////                String domainname = "https://test.pretimmediatpi.com/";
//                String domainname = "https://www.pretimmediatpi.com/";
//                String phoneNo = "1831006111";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "malaysia/cashnow.json");
//                String fileName = "cashnow";
//                String appssid = "9160";
////                String domainname = "https://malaysia.alpacashapple.com/";
//                String domainname = "https://temp.alpacashapple.com";
////                String domainname = "https://my.ultracreditosmx.com/";
//                String phoneNo = "183586936";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }


//                        {
//                String json = FileUtils.getTextByPath(HOME_PATH + "ecuador/prestamoexpres.json");
//                String fileName = "prestamoexpres";
//                String appssid = "482";
//                String domainname = "https://temp.massolpe.com/";
//                String phoneNo = "104625804";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "zambia/expressvoltmoney.json");
//                String fileName = "expressvoltmoney";
//                String appssid = "487";
////                String domainname = "https://zambia.ultracreditosmx.com/";
//                String domainname = "https://www.sbuildtech.com/";
//
////                String phoneNo = "183990008";
//                String phoneNo = "182111111";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "ecuador/totalcredito.json");
//                String fileName = "totalcredito";
//                String appssid = "481";
//                String domainname = "https://temp.massolpe.com/";
////                String phoneNo = "183990008";
//                String phoneNo = "182111111";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }
//            https://www.sbuildtech.com/expressvoltmoney

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "ecuador/totalcredito.json");
//                String fileName = "totalcredito";
//                String appssid = "481";
//                String domainname = "https://temp.massolpe.com/";
////                String phoneNo = "183990008";
//                String phoneNo = "182111111";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }


//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "ecuador/financiasmart.json");
//                String fileName = "financiasmart";
//                String appssid = "495";
//                String domainname = "https://temp.prestaperupr.com/";
////                String domainname = "https://www.movilprestamo.com/";
//                String phoneNo = "183990099";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "ecuador/totalcredito.json");
//                String fileName = "totalcredito";
//                String appssid = "481";
//                String domainname = "https://temp.muchsolpe.com/";
//                String phoneNo = "104625804";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "ecuador/prestamorapido.json");
//                String fileName = "prestamorapido";
//                String appssid = "493";
//                String domainname = "https://temp.unipreestamocl.com/";
//                String phoneNo = "183990099";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "ecuador/efectivolisto.json");
//                String fileName = "efectivolisto";
//                String appssid = "494";
//                String domainname = "https://temp.platacolombia.com/";
//                String phoneNo = "183990099";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "ecuador/financiasmart.json");
//                String fileName = "financiasmart";
//                String appssid = "495";
//                String domainname = "https://ecuador.ultracreditosmx.com/";
//                String phoneNo = "183990099";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }


//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "ecuador/creditossinfronteras.json");
//                String fileName = "creditossinfronteras";
//                String appssid = "484";
//                String domainname = "https://temp.credipormes.com/";
//                String phoneNo = "183990099";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "ecuador/totalcredito.json");
//                String fileName = "totalcredito";
//                String appssid = "481";
//                String domainname = "https://temp.muchsolpe.com/";
//                String phoneNo = "183990099";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }


//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "ecuador/platapuntual.json");
//                String fileName = "platapuntual";
//                String appssid = "466";
//                String domainname = "https://temp.prestamogratis.com/";
//                String phoneNo = "183909091";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }


//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "malaysia/gradualfinancing.json");
//                String fileName = "gradualfinancing";
//                String appssid = "463";
//                String domainname = "https://my.ultracreditosmx.com/";
//                String phoneNo = "183266012";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "malaysia/evolvingloan.json");
//                String fileName = "evolvingloan";
//                String appssid = "321";
//                String domainname = "https://temp.aiglecredit.com/";
//                String phoneNo = "183266011";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "ecuador/monederomagico.json");
//                String fileName = "monederomagico";
//                String appssid = "475";
//                String domainname = "https://temp.lexinmx.com/";
//                String phoneNo = "182000001";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
////                inxServerSpiderLocal.msgFeatureV3ZIP();
//            }
//            https://test.pretimmediatpi.com/pretimmediat/tightThirst/divideBusyKey


//            https://test.pretimmediatpi.com/pretimmediatpis/privacy.html

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "ecuador/movilprestamo.json");
//                String fileName = "movilprestamo";
//                String appssid = "479";
//                String domainname = "https://ecuador.ultracreditosmx.com/";
////                String domainname = "https://www.movilprestamo.com/";
//                String phoneNo = "183990099";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }


//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "chile/movilprestama.json");
//                String fileName = "movilprestama";
//                String appssid = "292";
//                String domainname = "https://test.movilprestamo.com/";
//                String phoneNo = "183990099";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//
//            }


//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "tanzania/trueloan.json");
//                String fileName = "trueloan";
//                String appssid = "147";
//                String domainname = "https://tz.ultracreditosmx.com/";
//                String phoneNo = "183100701";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
////                inxServerSpiderLocal.start();
//                inxServerSpiderLocal.msgFeatureV3ZIP();
//
//            }


//            String app = "{\"applyAmount\":\"12\",\"appSsid\":\"9009\",\"appName\":\"loanapp\"}";

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "argentina/easycash.json");
//                String fileName = "quickmoneypro";
//                String appssid = "433";
//                String domainname = "https://www.argentinoprestamo.com/";
////                https://test.felizsolpe.com/felizsol/nearbyPiano/recommendInternationalJazz
//                String phoneNo = " 1839090921";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "mexico/prestamopronto.json");
//                String fileName = "prestamopronto";
//                String appssid = "273";
//                String domainname = "https://test.prontomxmx.com/";
//                String phoneNo = "18200000";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//                        {
//                String json = FileUtils.getTextByPath(HOME_PATH + "guatemala/yaprestamo.json");
//                String fileName = "yaprestamo";
//                String appssid = "213";
//                String domainname = "https://test.yaprestamo.com/";
//                String phoneNo = "182333333";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "peru/creditopronto.json");
//                String fileName = "creditopronto";
//                String appssid = "431";
////                String domainname = "https://temp.sikadua.com/";
//                String domainname = "https://peru.ultracreditosmx.com/";
////                https://test.felizsolpe.com/felizsol/nearbyPiano/recommendInternationalJazz
//                String phoneNo = "18200000";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "tanzania/cashnow.json");
//                String fileName = "cashnow";
//                String appssid = "9009";
//                String domainname = "https://tz.alpacashapple.com/";
//                String phoneNo = "183662153";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "ecuador/realdinero.json");
//                String fileName = "realdinero";
//                String appssid = "297";
//                String domainname = "https://ecuador.ultracreditosmx.com/";
//                String phoneNo = "183100701";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }
//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "ecuador/dineroflash.json");
//                String fileName = "dineroflash";
//                String appssid = "9007";
//                String domainname = "https://ecuador.ultracreditosmx.com/";
//                String phoneNo = "183100701";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }


//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "peru/ekecredito.json");
//                String fileName = "ekecredito";
//                String appssid = "151";
//                String domainname = "https://peru.ultracreditosmx.com/";
////                https://test.felizsolpe.com/felizsol/nearbyPiano/recommendInternationalJazz
//                String phoneNo = "18200000";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }


//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "peru/cashahora.json");
//                String fileName = "cashahora";
//                String appssid = "9008";
////                String domainname = "https://peru.ultracreditosmx.com/";
//                String domainname = "https://peru.movilprestamo.com/";
//
//                String phoneNo = "183100701";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "english/fasttrust.json");
//                String fileName = "fasttrust";
//                String appssid = "9005";
//                String domainname = "https://test.topcedi.com/";
//                String phoneNo = "183100701";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "malaysia/creditjoy.json");
//                String fileName = "creditjoy";
//                String appssid = "159";
//                String domainname = "https://my.ultracreditosmx.com/";
//                String phoneNo = "183366111";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "chile/competidoresrfsprestamo.json");
//                String fileName = "competidoresrfsprestamo";
//                String appssid = "9002";
//                String domainname = "https://chile.ultracreditosmx.com/";
//                String phoneNo = "182123453";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "tanzania/cashflowloan.json");
//                String fileName = "cashflowloan";
//                String appssid = "9003";
//                String domainname = "https://my.ultracreditosmx.com/";
//                String phoneNo = "183555555";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }
//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "ecuador/prestaplata.json");
//                String fileName = "prestaplata";
//                String appssid = "122";
//                String domainname = "https://www.prestaplataec.com/";
//                String phoneNo = "183425468";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//
//            }
//
//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "ghana/rapidfire.json");
//                String fileName = "rapidfire";
//                String appssid = "279";
//                String domainname = "https://www.rapidfirerf.com/";
//                String phoneNo = "1839909";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "uganda/getloan.json");
//                String fileName = "getloan";
//                String appssid = "136";
//                String domainname = "https://www.getloanug.com/";
//                String phoneNo = "183990099";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//
//            }


//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "senegal/votrepret.json");
//                String fileName = "votrepret";
//                String appssid = "391";
//                String domainname = "https://testsnjr.beaucreditbc.com/";
////                String domainname = "https://snjr.beaucreditbc.com/";
////                https://testsnjr.beaucreditbc.com/beaucreditbcs/privacy.html
//                String phoneNo = "183990099";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//
//            }

            //            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "ivorian/financiax.json");
//                String fileName = "financiax";
//                String appssid = "228";
//                String domainname = "https://www.financiaxcl.com/";
//                String phoneNo = "183990099";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "peru/luckyprestamo.json");
//                String fileName = "luckyprestamo";
//                String appssid = "326";
////                String domainname = "https://peru.ultracreditosmx.com/";
//                String domainname = "https://www.accesodinero.com/accesodinero/";
//
//
//                String phoneNo = "183248490";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "chile/financiax.json");
//                String fileName = "financiax";
//                String appssid = "267";
//                String domainname = "https://www.financiaxcl.com/";
//                String phoneNo = "183990099";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "benin/zeroinquietude.json");
//                String fileName = "zeroinquietude";
//                String appssid = "296";
//                String domainname = "https://ecowas.ultracreditosmx.com/";
//                String phoneNo = "18390908";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "southafica/creditlandpro.json");
//                String fileName = "creditlandpro";
//                String appssid = "323";
//                String domainname = "https://zaf.ultracreditosmx.com/";
//                String phoneNo = "1830909097";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }


//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "benin/mycredit.json");
//                String fileName = "mycredit";
//                String appssid = "159";
//                String domainname = "https://ecowas.ultracreditosmx.com/";
//                String phoneNo = "183366111";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "malaysia/prestamia.json");
//                String fileName = "prestamia";
//                String appssid = "159";
//                String domainname = "https://my.ultracreditosmx.com/";
//                String phoneNo = "183366111";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }
//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "english/financiarahora.json");
//                String fileName = "financiarahora";
//                String appssid = "9001";
//                String domainname = "https://tz.ultracreditosmx.com/";
//                String phoneNo = "183100701";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "peru/wolaaloan.json");
//                String fileName = "wolaaloan";
//                String appssid = "144";
//                String domainname = "https://peru.ultracreditosmx.com/";
//                String phoneNo = "183248490";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "chile/abierto.json");
//                String fileName = "abierto";
//                String appssid = "258";
//                String domainname = "https://chile.ultracreditosmx.com/";
//                String phoneNo = "183391111";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "tanzania/securecredit.json");
//                String fileName = "securecredit";
//                String appssid = "157";
//                String domainname = "https://tz.ultracreditosmx.com/";
//                String phoneNo = "183100701";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }


//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "mexico/prestamosoleado.json");
//                String fileName = "prestamosoleado";
//                String appssid = "274";
//                String domainname = "https://mx.ultracreditosmx.com/";
//                String phoneNo = "18200000";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "malaysia/trustloan.json");
//                String fileName = "trustloan";
//                String appssid = "281";
//                String domainname = "https://my.ultracreditosmx.com/";
//                String phoneNo = "18200000";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "chile/abierto.json");
//                String fileName = "abierto";
//                String appssid = "258";
//                String domainname = "https://chile.ultracreditosmx.com/";
//                String phoneNo = "183369980";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "columbia/nuevocredito.json");
//                String fileName = "nuevocredito";
//                String appssid = "195";
//                String domainname = "https://test.nuevocreditogt.com";
//                String phoneNo = "18255555";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }
//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "chile/getprestamo.json");
//                String fileName = "getprestamo";
//                String appssid = "9140";
//                String domainname = "https://ecuador.ultracreditosmx.com/";
////                String domainname = "https://chile.ultracreditosmx.com/";
//
//                String phoneNo = "18325190";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "chile/cactuscredito.json");
//                String fileName = "cactuscredito";
//                String appssid = "267";
//                String domainname = "https://chile.ultracreditosmx.com/";
//                String phoneNo = "182151515";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//           {
//                String json = FileUtils.getTextByPath(HOME_PATH + "senegal/empruntezmobile.json");
//                String fileName = "empruntezmobile";
//                String appssid = "255";
//                String domainname = "https://snjr.ultracreditosmx.com/";
//                String phoneNo = "182151515";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }


//            https://mx.getprestamocl.com/getprestamo/
//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "mexico/brillanteprestamo.json");
//                String fileName = "brillanteprestamo";
//                String appssid = "252";
////                String domainname = "https://www.creditopesocl.com/";
////                https://www.brillanteprestamo.com/brillanteprestamo
//                String domainname = "https://www.brillanteprestamo.com/";
//                String phoneNo = "99900011";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }
//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "chile/abierto.json");
//                String fileName = "abierto";
//                String appssid = "258";
////                String domainname = "https://www.creditopesocl.com/";
//                String domainname = "https://chile.ultracreditosmx.com/";
//                String phoneNo = "183369980";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "mexico/prestapeso.json");
//                String fileName = "prestapeso";
//                String appssid = "19202";
//                String domainname = "https://mx.ultracreditosmx.com/";
//                String phoneNo = "183505050";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }


//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "chile/prestasol.json");
//                String fileName = "prestasol";
//                String appssid = "01";
////                String domainname = "https://www.creditopesocl.com/";
//                String domainname = "https://chile.ultracreditosmx.com/";
//                String phoneNo = "183100802";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "chile/creditosimple.json");
//                String fileName = "creditosimple";
//                String appssid = "9050";
//                String domainname = "https://www.creditosimplecl.com/";
////                String domainname = "https://test.creditosimplecl.com/";
//                String phoneNo = "183100611";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "chile/getprestamo.json");
//                String fileName = "getprestamo";
//                String appssid = "9140";
//                String domainname = "https://www.getprestamocl.com/";
//                String phoneNo = "183100611";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "chile/onlinecredito.json");
//                String fileName = "onlinecredito";
//                String appssid = "131";
//                String domainname = "https://chile.ultracreditosmx.com/";
//
//                String phoneNo = "183100802";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "ecuador/sricredito.json");
//                String fileName = "sricredito";
//                String appssid = "117";
//                String domainname = "https://ecuador.ultracreditosmx.com/";
//                String phoneNo = "182111111";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }


//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "chile/creditopeso.json");
//                String fileName = "creditopeso";
//                String appssid = "118";
////                String domainname = "https://www.creditopesocl.com/";
//                String domainname = "https://chile.ultracreditosmx.com/";
//
//                String phoneNo = "183100802";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "ivorian/lesoutien.json");
//                String fileName = "lesoutien";
//                String appssid = "200";
//                String domainname = "https://cote.ultracreditosmx.com/";
//                String phoneNo = "1832";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }
//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "senegal/senepret.json");
//                String fileName = "senepret";
//                String appssid = "174";
//                String domainname = "https://snjr.ultracreditosmx.com/";
//                String phoneNo = "182151515";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }
//            {
//                String json = FileUtils.getTextByPath(HOME_PATH + "peru/luckyprestamo.json");
//                String fileName = "luckyprestamo";
//                String appssid = "210";
//                String domainname = "https://www.luckyprestamo.com/";
//                String phoneNo = "183223118";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

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
//                String phoneNo = "1832643691";
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
//                String appssid = "256";
//                String domainname = "https://tz.ultracreditosmx.com";
//                String phoneNo = "183109872";
//                InxServerSpiderLocal inxServerSpiderLocal = new InxServerSpiderLocal(json, fileName, appssid, domainname, phoneNo);
//                inxServerSpiderLocal.start();
//            }

            //

//            https://test.nuevocreditogt.com/nuevocreditogts/privacy.html
//            https://www.nuevocreditogt.com/nuevocreditogts/privacy.html


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

    private static void gitDir() {
        String repoUrl = "https://gitee.com/weiluTech/jamboloan.git";
        File rootDir = new File("./gitDir");
        File currentDir = new File(rootDir.getAbsolutePath() + "/jamboloan");
        if (!currentDir.exists()) {
            currentDir.mkdir();
        }
//        FileUtils.deleteDirWithPath(currentDir.getAbsolutePath());
//        String cloneDirectoryPath = currentDir.getAbsolutePath();
//        try {
//            // 克隆仓库
//            Git.cloneRepository()
//                    .setURI(repoUrl)
//                    .setDirectory(new File(cloneDirectoryPath))
//                    .setCredentialsProvider(new UsernamePasswordCredentialsProvider("huangjinping1000@163.com", "wo123456"))
//                    .call();
//
//            // 或者，如果你需要操作已存在的仓库，可以打开一个本地仓库
//            Repository repo = new FileRepository(cloneDirectoryPath);
//            Git git = new Git(repo);
//
//            // 进行仓库操作，例如拉取最新变更
////            git.pull().setCredentialsProvider(new UsernamePasswordCredentialsProvider("username", "password")).call();
//            LogCommand log = git.log();
//
//            // 关闭Git实例
//            git.close();
//            repo.close();
//
//        } catch (GitAPIException | IOException e) {
//            e.printStackTrace();
//        }

        try {
            Git git = Git.open(currentDir);
//            git.pull().setCredentialsProvider(new UsernamePasswordCredentialsProvider("username", "password")).call();
            git.log();
            git.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
