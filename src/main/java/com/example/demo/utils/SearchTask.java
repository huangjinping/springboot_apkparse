package com.example.demo.utils;

import com.example.demo.bean.AppConfig;
import com.example.demo.bean.CommonModel;
import com.example.demo.bean.GrepModel;
import com.example.demo.bean.MethodSolr;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://blog.csdn.net/qq_33697094/article/details/122237127 //https://blog.csdn.net/qq_33697094/article/details/122237127
//https://blog.csdn.net/raqsoft/article/details/125534351  java 搜索指定文件夹下指定字符串并将其写入 txt 文件中
//参考文献
public class SearchTask {
    public Map<String, String> mWhiteList = new HashMap<>();
    public Map<String, String> mBlackList = new HashMap<>();

    public List<GrepModel> grepModelList = new ArrayList<>();

    public SearchTask() {

        initWhiteListList();
        initBlackListList();
        createGrepModelList();
    }

    private void createGrepModelList() {
        grepModelList.add(new GrepModel("TrustManager", "TrustManager"));
        grepModelList.add(new GrepModel("ObtainUserData", "ObtainUserData"));
    }

    private String buildCMD(String dir) {
        StringBuilder builder = new StringBuilder();
        builder.append("grep -rnR '");
        for (int i = 0; i < grepModelList.size(); i++) {
            GrepModel model = grepModelList.get(i);
            builder.append(model.getSearchCode());
            if (i < grepModelList.size() - 1) {
                builder.append("\\|");
            }
        }
        builder.append("' " + dir + " /*");
        return builder.toString();
    }


    private void initWhiteListList() {

        List<String> list = new ArrayList<>();
        list.add("http://schemas.android.com/apk/res/android");
        list.add("http://www.apache.org/licenses/LICENSE-2.0");
        list.add("http://schema.org/ActiveActionStatus");
        list.add("http://www.android.com/");
        list.add("http://www.w3.org/2000/svg");
        list.add("http://play.google.com/store/apps/details?id");
        list.add("https://www.googleapis.com/auth/games_lite");
        list.add("https://google.com/search");
        list.add("https://firebaseremoteconfig.googleapis.com");
        list.add("https://www.example.com");
        list.add("https://facebook.com");
        list.add("https://developers.facebook.com");
        list.add("https://.facebook.com");
        list.add("https://api2.branch.io/");
        list.add("https://api.branch.io/");
        list.add("https://cdn.branch.io");
        list.add("https://api.whatsapp.com/send?phone=");
        list.add("https://plus.google.com");
        list.add("https://localhost");
        list.add("https://%s/%s/%s");
        list.add("https://twitter.com/");
        list.add("https://telegram.me/");
        list.add("http://localhost");
        list.add("http://www.example.com");
        list.add("http://www.google.com");
        list.add("https://graph.%s");
        list.add("https://graph-video.%s");
        list.add("https://goo.gl/NAOOOI.");
        list.add("https://bnc.lt/a/");
        list.add("https://branch.app.link/link-settings-page\n");
        list.add("https://www.reactnavigation.org.cn/img/react_white.png");
        list.add("https://reactnavigation.org/docs/nesting-navigators");
        list.add("https://reactjs.org/link/strict-mode-string-ref\n");
        list.add("https://help.branch.io/developers-hub/docs/android-basic-integration#section-configure-branch-dashboard\n");
        list.add("https://branch.app.link/link-settings-page\n");
        list.add("https://mozilla.org/MPL/2.0/");
        list.add("https://registry.npmjs.org/react-native/-/react-native-0.69.1.tgz\n");
        list.add("https://pagead2.googlesyndication.com/pagead/gen_204?id=gmob-apps");
        list.add("https://fb.gg/me/media_asset/");
        list.add("https://app-measurement.com");
        list.add("https://.app.link/NdJ6nFzRbK");
        list.add("https://support.google.com/dfp_premium/answer/7160685#push\n");
        list.add("https://adservice.google.com/getconfig/pubvendors\n");
        list.add("https://crisp.chat/en/");
        list.add("https://image.crisp.chat/\n");
        list.add("https://github.com/uuidjs/uuid#getrandomvalues-not-supported\n");
        list.add("https://ask.dcloud.net.cn/article/36199");
        list.add("https://settings.crisp.chat/client/website/\n");
        list.add("https://publicsuffix.org/list/public_suffix_list.dat\n");
        list.add("http://momentjs.com/guides/#/warnings/define-locale/\n");
        list.add("http://ns.adobe.com/xap/1.0/\n");
        list.add("https://icon-icons.com/downloadimage.php?id=89781&root=1368/PNG/512/&file=-avatar_89781.png");
        list.add("http://ask.dcloud.net.cn/article/283\n");
        list.add("https://stream.mobihtml5.com/\n");
        list.add("https://exoplayer.dev/issues/player-accessed-on-wrong-thread\n");
        list.add("http://m3w.cn/s/");
        list.add("https://docs.swmansion.com/react-native-gesture-handler/docs/#installation\n");
        list.add("http://fb.me/use-check-prop-types");

        for (String item : list) {
            mWhiteList.put(item.substring(0, 14), item);
        }


    }


    private void initBlackListList() {

        List<String> list = new ArrayList<>();
        list.add("http://schemas.android.com/apk/res/android");
        list.add("http://www.apache.org/licenses/LICENSE-2.0");

        for (String item : list) {
            mBlackList.put(item.substring(0, 14), item);
        }


    }


    public List<CommonModel> getHttpsList(String dir) {
        List<String> commands = new ArrayList<>();
//        commands.add("ls");
//        commands.add("pwd");
//            commands.add("cd /Users/huhuijie/Documents/bundletool/__UNI__C5B5A12_0921120719");
        commands.add("grep -rnR 'https://' " + dir + "/*");
        System.out.println("===================before=====》》》》》");
        List<String> strings = CommandLineTool.executeNewFlow(commands);
        return getDomainNameByCmd(strings, "https://");
    }

    public List<CommonModel> getHttpList(String dir) {
        List<String> commands = new ArrayList<>();
//        commands.add("ls");
//        commands.add("pwd");
//            commands.add("cd /Users/huhuijie/Documents/bundletool/__UNI__C5B5A12_0921120719");
        commands.add("grep -rnR 'http://' " + dir + "/*");
        System.out.println("===================before=====》》》》》");
        List<String> strings = CommandLineTool.executeNewFlow(commands);
        return getDomainNameByCmd(strings, "http://");
    }

    public List<String> getUrlList(String targetPath) {
        List<String> list = new ArrayList<>();
        try {
            String cmd = "grep -rnR \"http://\"  " + targetPath + "/* >temp.txt";
            cmd = "grep -rnR \"http://\"  " + targetPath + "/*";

//            cmd="echo this is a text line|grep -rnR '"+targetPath+"://' *";
            Process process = Runtime.getRuntime().exec(cmd);
            System.out.println("before");


//            echo this is a text line|grep -rnR 'http://' *


            System.out.println(cmd);
            BufferedReader bis = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";

            System.out.println("+ddd" + line);

            while ((line = bis.readLine()) != null) {
                try {
//                    System.out.println(line);

                    LogUtils.logJson(line);


                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

            bis.close();
        } catch (Exception var9) {
            System.out.println("parseAndroidApk7" + var9.getMessage());

            var9.printStackTrace();
        }

        System.out.println("after");


        return list;
    }


    public List<CommonModel> getDomainNameByCmd(List<String> source, String target) {
        List<CommonModel> domainNames = new ArrayList<>();
        Map<String, String> tempMap = new HashMap<>();

        for (String item : source) {
            if (item != null && item.contains(target)) {
                try {
                    String[] targetList = item.split(target);
                    if (targetList.length < 2) {
                        continue;
                    }
                    String targetRe1 = targetList[1];
                    String[] result = targetRe1.split("\"");
                    CommonModel domainName = new CommonModel();
                    int state = 0;
                    if (result.length > 0) {
                        String path = result[0];
                        String link = target + path;
                        int i = link.indexOf(" ");
                        if (i == -1) {
                            i = link.indexOf("\\u");
                        }
                        if (i != -1) {
                            link = link.substring(0, i);
                        }
                        if (link.length() > 15) {

                            String key = link.substring(0, 14);
                            if (!tempMap.containsKey(key) && !mWhiteList.containsKey(key)) {
                                String[] tope = link.split("://");
                                String[] split = tope[1].split("/");
                                String realPath = split[0];
                                tempMap.put(key, link);
                                domainName.setName(link);
//                                LogUtils.logJson("-------------------------------->>>>>");
//                                LogUtils.logJson(realPath);
//                                if (realPath.contains(":")) {
//                                    state = -1;
//                                } else {
//                                    state = 0;
//                                }
                                domainName.setState(CheckUtils.checkRealPath(realPath));
                                domainNames.add(domainName);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }
        return domainNames;
    }


    public List<MethodSolr> getMethodSolr_ByList(String dir) {
        List<String> commands = new ArrayList<>();
        String command = buildCMD(dir);

        LogUtils.logJson("------------___>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        LogUtils.logJson(command);
        commands.add(command);

        List<String> strings = CommandLineTool.executeNewFlow(commands);

        return getMethodSolrByCmdList(dir, strings);
    }

    private List<MethodSolr> getMethodSolrByCmdList(String path, List<String> result) {
        List<MethodSolr> resultSolr = new ArrayList<>();
        for (String item : result) {
            MethodSolr solr = new MethodSolr();
            try {
                String content = item.replace(path, "").replace("smali", "a").replace("/", ".");
                solr.setContent(content);
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (GrepModel model : grepModelList) {
                if (item.contains(model.getSearchCode())) {
                    solr.setName(model.getTarget());
                    solr.setState(checkFilterByTarget(item, model.getTarget()));
                }
            }


        }
        return resultSolr;
    }


    public int checkFilterByTarget(String source, String target) {

        if (source.toUpperCase().contains("OKHTTP3/")) {
            return 1;
        }


        return -1;
    }


    //    https://blog.csdn.net/volcano2339/article/details/115947208
    public List<MethodSolr> getMethodSolr_ssl(String dir) {
        List<String> commands = new ArrayList<>();
//        commands.add("grep -rnR 'onReceivedSslError' " + dir + "/*");
        commands.add("grep -rnR '.proceed(' " + dir + "/*");

        System.out.println("===================before=====》》》》》");
        List<String> strings = CommandLineTool.executeNewFlow(commands);

        return getMethodSolrByCmd(dir, strings, AppConfig.MethodTarget.onReceivedSslError);
    }

    public List<MethodSolr> getMethodSolr_phoneNumber(String dir) {
        List<String> commands = new ArrayList<>();
//        commands.add("grep -rnR 'onReceivedSslError' " + dir + "/*");
//        commands.add("grep -rnR 'getLine1Number()\\|TrustManager\\|ObtainUserData' " + dir + "/*");
        commands.add("grep -rnR 'getLine1Number()' " + dir + "/*");
//        commands.add("grep -rnR 'telephony/TelephonyManager;->getDeviceId' " + dir + "/*");
//        commands.add("grep -rnR 'telephony/TelephonyManager;->getImei' " + dir + "/*");

        List<String> strings = CommandLineTool.executeNewFlow(commands);

        return getMethodSolrByCmd(dir, strings, AppConfig.MethodTarget.getLine1Number);

    }


    public List<MethodSolr> getMethodWebViewWebSettings(String dir) {
        List<String> commands = new ArrayList<>();
        commands.add("grep -rnR 'WebSettings' " + dir + "/*");
        List<String> strings = CommandLineTool.executeNewFlow(commands);

        return getMethodSolrByCmd(dir, strings, AppConfig.MethodTarget.onReceivedSslError);

    }



    private int checkReceivedSslError(String item) {

        if (item.contains("/io/dcloud/")) {
            return 1;
        }
        if (item.contains("com/taobao/weex")) {
            return 1;
        }
        if (item.contains("com/taobao/weex")) {
            return 1;
        }


//        LogUtils.log("" + "----182-------" + item);


        if (item.contains("/SafeBrowsingResponse")) {
            return 1;
        }

        if (item.contains("211:.method public onReceivedSslError(") || item.contains("com/facebook/internal/")) {
            return 1;
        }
        if (item.contains("smali:226:    invoke-super {p0, p1, p2, p3}") || item.contains("com/facebook/internal/")) {
            return 1;
        }

        if (!item.contains("WebView") && !item.contains("webkit")) {
            return 1;
        }


        return -1;
    }


    private int checkReceivedLine1Number(String item) {

        if (item.contains("telephony/TelephonyManager") && item.contains("getLine1Number(")) {
            return -1;
        }

        return 0;
    }


    private int checkReceivedSetting(String item) {

        if (item.contains("android/webkit/WebSettings")) {
            return -1;
        }
        return 1;
    }

    private List<MethodSolr> getMethodSolrByCmd(String path, List<String> result, String target) {
        List<MethodSolr> resultSolr = new ArrayList<>();
        for (String item : result) {
//            LogUtils.log("===============" + item);
            MethodSolr solr = new MethodSolr();
            try {
                String content = item.replace(path, "").replace("smali", "a").replace("/", ".");
                solr.setContent(content);
            } catch (Exception e) {
                e.printStackTrace();
            }

            solr.setName(target);
            if (AppConfig.MethodTarget.onReceivedSslError.equals(target)) {
                int i = checkReceivedSslError(item);
                if (i == -1) {
                    solr.setState(i);
                    resultSolr.add(solr);

                }
            } else if (AppConfig.MethodTarget.getLine1Number.equals(target)) {
                int i = checkReceivedLine1Number(item);
                if (i == -1) {
                    solr.setState(i);
                    resultSolr.add(solr);
                }
            }

        }
        return resultSolr;
    }


}
