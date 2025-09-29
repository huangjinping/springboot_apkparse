package com.example.demo.utils;

import com.example.demo.bean.*;
import com.google.gson.Gson;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;
import org.dom4j.xpath.DefaultXPath;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackageParse {

    public static final int ANDROID_PACKAGE_TYPE_APK = 0;
    public static final int ANDROID_PACKAGE_TYPE_AAB = 1;
    static final int APP_TYPE_FLUTTER = 3;
    static final String V8A = "arm64-v8a";
    static final String V71 = "armeabi-v7a";

    //    public static Map<String, Object> parseAABLibs(String aabPath) {
//        List<String> commands = new ArrayList<>();
//        String fileNamePath = aabPath.substring(0, aabPath.length() - 5);
//        String unipp = fileNamePath + ".zip";
//        commands.add("mv " + aabPath + " " + unipp + ";");
//        commands.add("unzip " + unipp + " -d " + fileNamePath);
//        List<String> result = CommandLineTool.executeNewFlow(commands);
//        String libPath = fileNamePath + "/base/lib";
////        FileUtils.deleteDirWithPath(fileNamePath);
//        return parseLibs(libPath);
//    }
    static final String X86 = "x86";
    static final String X8664 = "x86_64";
    public Map<String, String> stringMap = new HashMap<>();
    private String mRealFilePath;

    public static Map<String, Object> parseApkLibs(String apkPath) {
//        List<String> commands = new ArrayList<>();
//        String fileNamePath = apkPath.substring(0, apkPath.length() - 5);
//        String unipp = fileNamePath + ".zip";
//        commands.add("mv " + apkPath + " " + unipp + ";");
//        commands.add("unzip " + unipp + " -d " + fileNamePath);
//        List<String> result = CommandLineTool.executeNewFlow(commands);
        String libPath = apkPath + "/lib";
        LogUtils.logJson("----------------------22-------->>>>>>");
        Map<String, Object> objectMap = parseLibs(libPath);
//        FileUtils.deleteDirWithPath(fileNamePath);
        return objectMap;
    }

    public static Map<String, Object> parseLibs(String libPath) {


        LogUtils.logJson("============parseLibs==============" + libPath);

        Map<String, Object> rootResult = new HashMap<>();
        File root = new File(libPath);

        if (root == null || !root.exists() || !root.isDirectory()) {
            return rootResult;
        }
        List result = new ArrayList();
        if (root.listFiles().length > 0) {
            Map<String, String> allMap = new HashMap<>();
            for (File dir : root.listFiles()) {
                if (dir == null || !dir.exists() || !dir.isDirectory()) {
                } else {
                    for (File file : dir.listFiles()) {
                        allMap.put(file.getName(), FileSizeUtil.getFileSize(file) + "");
                    }
                }
            }
            File v8a = new File(libPath + File.separator + V8A);
            File v7a = new File(libPath + File.separator + V71);
            File x86 = new File(libPath + File.separator + X86);
            File x8664 = new File(libPath + File.separator + X8664);
            LogUtils.logJson(allMap);
            result.add(checkFileLib(V8A, v8a, allMap));
            result.add(checkFileLib(V71, v7a, allMap));
//            result.add(checkFileLib(X86, x86, allMap));
//            result.add(checkFileLib(X8664, x8664, allMap));
        }
        rootResult.put("cpu", result);
        return rootResult;
    }

    public static Jentity checkFileLib(String name, File cpu, Map<String, String> allMap) {
        if (cpu == null || !cpu.exists() || !cpu.isDirectory()) {
            return new Jentity(name, "", 0);
        }
        if (name.equals(X86)) {
            return new Jentity(name, "", 1);
        }
        for (String key : allMap.keySet()
        ) {
            boolean dir_flag = false;
            for (File dir : cpu.listFiles()) {
                if (key.equals(dir.getName())) {
                    dir_flag = true;
                }
            }
            if (dir_flag) {
            } else {
                return new Jentity(name, "", 0);
            }
        }
        return new Jentity(name, "", 1);
    }


    public static Map<String, Object> getApkLengthByList(String apkpath) {

        Map<String, Object> result = new HashMap<>();
        try {
            List<Jentity> data = new ArrayList<>();
            data.add(new Jentity("totalSize", FileSizeUtil.getAutoFileOrFilesSize(apkpath), 1));
            result.put("packageSize", data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static Map<String, Object> getAbbLengthByList(String aabPath, List<String> request, String masterApkBPath) {


        LogUtils.logJson("============getAbbLengthByList=================" + aabPath);
        Map<String, Object> result = new HashMap<>();
        LogUtils.logJson(request);


        try {
//            Map<String, String> data = new HashMap<>();

            List<Jentity> data = new ArrayList<>();
            if (request.size() > 1) {
                String[] split = request.get(1).split(",");
                data.add(new Jentity("totalSize", FileSizeUtil.getAutoFileOrFilesSize(aabPath), 1));
                data.add(new Jentity("MIN", FileSizeUtil.FormetFileSize(Long.parseLong(split[0])), 1));
//                data.add(new Jentity("MAX", FileSizeUtil.FormetFileSize(Long.parseLong(split[1])), 1));
                data.add(createPackageMax(Long.parseLong(split[1]), masterApkBPath));

            }

            result.put("packageSize", data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    public static Jentity createPackageMax(Long max, String masterApkBPath) {
        int state = 1;
        String msg = "";
        int i = checkTypeByPath(masterApkBPath);

        LogUtils.logJson("============createPackageMax==============" + i + "===");

        switch (i) {
            case 0:
                if (max > AppConfig.PackageLimit.native_limit) {
                    state = 0;
                    msg = ":原生包10M内";
                }
                break;
            case 1:
                if (max > AppConfig.PackageLimit.OTHER_limit) {
                    state = 0;
                    msg = ":ReactNative包10M内";
                }
                break;
            case 2:
                if (max > AppConfig.PackageLimit.OTHER_limit) {
                    state = 0;
                    msg = ":Uni包20M内";
                }
                break;
            case 3:
                if (max > AppConfig.PackageLimit.OTHER_limit) {
                    state = 0;
                    msg = ":Flutter包20M内";
                }
                break;
        }
        Jentity jentity = new Jentity("MAX", FileSizeUtil.FormetFileSize(max) + " " + msg, state);
        jentity.setMsg(msg);

        return jentity;
    }

    public static Map<String, Object> parseMethod(String filePath) {
        Map<String, Object> parseDomainNameResult = new HashMap<>();
        try {
            SearchTask searchTask = new SearchTask();

            StringTask stringTask = new StringTask();
            List<MethodSolr> resultAll = new ArrayList<>();
            resultAll.addAll(searchTask.getMethodSolr_ssl(filePath));
            resultAll.addAll(searchTask.getMethodSolr_phoneNumber(filePath));

            resultAll.addAll(stringTask.searchMethodSolr(filePath, StringTask.methodList));
//            resultAll.addAll(searchTask.getMethodSolr_ByList(filePath));

//            resultAll.addAll(searchTask.getMethodWebViewWebSettings(filePath));
            parseDomainNameResult.put("methodSolr", resultAll);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parseDomainNameResult;
    }

    public static Map<String, Object> parseDomainName(String filePath) {
        Map<String, Object> parseDomainNameResult = new HashMap<>();
        try {
            SearchTask searchTask = new SearchTask();
            List<CommonModel> httpsList = searchTask.getHttpsList(filePath);
            httpsList.addAll(searchTask.getHttpList(filePath));
            parseDomainNameResult.put("domainName", httpsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parseDomainNameResult;
    }

    public static Map<String, Object> parsePackage(String filePath, String appType) {
        Map<String, Object> parsePackageResult = new HashMap<>();
        try {
            LogUtils.log("parsePackage====:" + filePath);
            ArrayList<Object> fileList = FolderFileScanner.startScanFilesWithRecursion(filePath);
            parsePackageResult.put("keepPackage", getKeepList(fileList, appType));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parsePackageResult;
    }

    public static Map<String, Object> parseFileCert(String filePath) {
        Map<String, Object> parseFileCert = new HashMap<>();
        try {

            if (filePath.endsWith(".apk")) {
                CommonModel commonModel = new CommonModel();
                commonModel.setName("--");
                commonModel.setState(1);

                parseFileCert.put("fileCert", commonModel);
                return parseFileCert;
            }

            LogUtils.log("parseFileCert====:" + filePath);
            List<String> commands = new ArrayList<>();
            commands.add("keytool -printcert -jarfile " + filePath);
            List<String> result = CommandLineTool.executeNewFlow(commands);
            LogUtils.log("parseFileCert====:" + result);

            boolean isCert = false;
            StringBuilder builder = new StringBuilder();

            try {
                for (String item : result) {
                    if (!TextUtils.isEmpty(item)) {
                        if (item.toUpperCase().contains("SHA1")) {
                            isCert = true;
                        }
                        builder.append(item).append("\n");
                    }
                }
            } catch (Exception e) {

            }
            CommonModel commonModel = new CommonModel();
            commonModel.setName(builder.toString());
            commonModel.setState(isCert ? 1 : -1);

            parseFileCert.put("fileCert", commonModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parseFileCert;
    }

    private static List<KeepPackage> getKeepList(ArrayList<Object> fileList, String appType) {
        Map<String, String> catchMap = new HashMap<>();
        List<KeepPackage> packageList = new ArrayList<>();


        List<KeepPackage> shouldList = new ArrayList<>();
        if (AppConfig.AppType.TYPE_RELEASE531.equals(appType) || AppConfig.AppType.TYPE_RELEASE.equals(appType)) {
            for (String shouldItem : FolderFileScanner.PACKAGE_SHOULD_LIST) {
                KeepPackage keepPackage = new KeepPackage();
                keepPackage.setName(shouldItem);
                keepPackage.setState(0);
                shouldList.add(keepPackage);
            }
        }


        for (int i = 0; i < fileList.size(); i++) {
            String packageNo = (String) fileList.get(i);

            for (int j = 0; j < FolderFileScanner.PACKAGE_KILL_LIST.length; j++) {
                String noItem = FolderFileScanner.PACKAGE_KILL_LIST[j];
                if (packageNo.contains(noItem)) {
                    KeepPackage keepPackage = new KeepPackage();
                    String realNamePage = FolderFileScanner.getRealNamePage(packageNo);
                    if (catchMap.containsKey(realNamePage)) {

                    } else {
                        catchMap.put(realNamePage, realNamePage);
                        keepPackage.setName(realNamePage);
                        keepPackage.setState(-1);
                        packageList.add(keepPackage);
                    }
                }
            }

            for (int j = 0; j < shouldList.size(); j++) {
                KeepPackage keepPackage = shouldList.get(j);
                if ("1".equals(keepPackage.getState())) {

                } else {
                    if (packageNo.contains(keepPackage.getName())) {
                        keepPackage.setState(1);
                    }
                }
            }
        }

        packageList.addAll(shouldList);

        return packageList;
    }


    public static List<KeepPackage> dependenciesRule(String dependenciesPath) {

        List<KeepPackage> resultList = new ArrayList<>();
        LogUtils.log("----------dependenciesRule------" + dependenciesPath);

        String reader = null;
        BufferedReader br = null;
        File f = new File(dependenciesPath);
        String result = "";
        if (f.exists()) {
            try {
                br = new BufferedReader(new FileReader(f));
                while ((reader = br.readLine()) != null) {
                    result += reader;
//                    System.out.println("" + reader);


                    Map<String, Integer> packageRule = FolderFileScanner.getPackageRule();
                    for (Map.Entry<String, Integer> entry : packageRule.entrySet()) {
                        reader = filterString(reader);
//                        LogUtils.log("----------dependenciesRule------1---------"+reader);

                        if (reader.contains(entry.getKey())) {
                            LogUtils.log("----------dependenciesRule---------------" + reader);
                            try {
                                String[] codeSp = reader.split("[*]");
                                Integer code = Integer.parseInt(codeSp[1].replace(".", ""));
                                LogUtils.log("----------dependenciesRule-------2--------" + code);

                                if (entry.getValue() > code) {
                                    KeepPackage packageParse = new KeepPackage();
                                    packageParse.setState(-2);
                                    packageParse.setName(reader);
                                    resultList.add(packageParse);
                                    break;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


//                            if (checkRuleLine(reader, entry.getKey(), entry.getValue())) {
//                                KeepPackage packageParse = new KeepPackage();
//                                packageParse.setState(3);
//                                packageParse.setName(reader);
//                                resultList.add(packageParse);
//                            }

                            break;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultList;
    }


    private static boolean checkRuleLine(String source, String name, int code) {
        source = source.replace("", "name").replace("*", "").replace(" ", "").replace("\"", "");
        LogUtils.log("=======checkRuleLine=======" + source);
        return true;
    }


    public static Map<String, String> parseStringXML(String path, String appType) {
        SAXReader reader = new SAXReader();
        Map<String, String> colMap = new HashMap<>();
        for (String key : FolderFileScanner.STRING_SHOULD_LIST) {
            colMap.put(key, "");
        }
        try {
            Document document = reader.read(path);
            XPath xPath = new DefaultXPath("/resources/string");
            List<Element> list = xPath.selectNodes(document.getRootElement());
            for (Element e : list) {
                String name = e.attributeValue("name");
                String value = e.getStringValue();
                if (colMap.containsKey(name)) {
                    colMap.put(name, value);
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return colMap;
    }

    public static String filterString(String input) {
        if (input == null) {
            return "";
        }
        // 使用正则表达式替换非数字、非英文字符、非星号的内容
        return input.replaceAll("[^a-zA-Z0-9*.]", "");
    }


    public static Map<String, String> parseStringXMLToMap(String path) {
        SAXReader reader = new SAXReader();
        Map<String, String> colMap = new HashMap<>();
        try {
            Document document = reader.read(path);
            XPath xPath = new DefaultXPath("/resources/string");
            List<Element> list = xPath.selectNodes(document.getRootElement());
            for (Element e : list) {
                String name = e.attributeValue("name");
                String value = e.getStringValue();
                colMap.put(name, value);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return colMap;
    }

    /**
     * @param path
     * @return
     */
    public static int checkTypeByPath(String path) {
        int result = 0;

        LogUtils.log("==========checkTypeByPath>>>>>222>>>2=============" + path);


        try {
            String v8a = path + "/base/lib/arm64-v8a/";
            File react = new File(v8a + "libreactnativejni.so");
            File react1 = new File(v8a + "libreactnative.so");


            File uni = new File(v8a + "libweexcore.so");
            File flutter = new File(v8a + "libflutter.so");


            if (react.exists() || react1.exists()) {
                result = 1;
            } else if (uni.exists()) {
                result = 2;
            } else if (flutter.exists()) {
                result = 3;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * @param path
     * @return
     */
    public static int checkTypeByPathV2(String path) {
        int result = 0;

//        LogUtils.log("==========checkTypeByPath>>>>>222>>>2=============" + path);


        try {
            String v8a = path + "/lib/arm64-v8a/";
//            LogUtils.log("==========checkTypeByPath>>>>>cccc>>>444============" + path);

            File react = new File(v8a + "libreactnativejni.so");
            File uni = new File(v8a + "libweexcore.so");
            File flutter = new File(v8a + "libflutter.so");


            if (react.exists()) {
                result = 1;
            } else if (uni.exists()) {
                result = 2;
            } else if (flutter.exists()) {
                result = 3;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Object> parseDebugRelease(String aaptPath, String apkPath) {
        Map<String, Object> parseDebugRelease = new HashMap<>();
        try {
            LogUtils.log("parseDebugRelease====:" + apkPath);
            List<String> commands = new ArrayList<>();
            commands.add(aaptPath + " dump badging " + apkPath + " | grep -c application-debuggable");
            List<String> result = CommandLineTool.executeNewFlow(commands);
            LogUtils.log("parseDebugRelease====:" + result);

            StringBuilder builder = new StringBuilder();

            boolean isDebug = false;

            if (result.size() > 0) {
                if ("1".equals(result.get(0).trim())) {
                    isDebug = true;
                }
            }

            CommonModel commonModel = new CommonModel();
            commonModel.setName(builder.toString());
            commonModel.setState(isDebug ? 1 : 0);

            parseDebugRelease.put("isDebug", commonModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parseDebugRelease;
    }

    public String getmRealFilePath() {
        return mRealFilePath;
    }


//    public static Map<String, Object> parseAndroidManifestByCmd(String miniFastPath, String outFilePath) throws Exception {
//        String cmd = "apktool d " + miniFastPath + " -o " + outFilePath;
//        Process process = Runtime.getRuntime().exec(cmd);
//        int value = process.waitFor();
//        Map<String, Object> map = ManiParse.parseAndroidManifest(outFilePath + "/AndroidManifest.xml");
//        return map;
//    }

    public void setmRealFilePath(String mRealFilePath) {
        this.mRealFilePath = mRealFilePath;
    }


    public Map<String, Object> parseAndroidManifestByCmd(String apktoolPath, String apkFastPath, String outFilePath, String appType, int android_package_type) throws Exception {


//        LogUtils.log("==parseAndroidManifestByCmd==============appType===========" + appType);

        String cmd = "java -jar " + apktoolPath + " d " + apkFastPath + " -o " + outFilePath;
        System.out.println(cmd);
        Process process = Runtime.getRuntime().exec(cmd);
        int value = process.waitFor();
        stringMap.putAll(PackageParse.parseStringXMLToMap(outFilePath + "/res/values/strings.xml"));

        ThreadM threadM = new ThreadM(this);

        Map<String, Object> map = threadM.parseApkData(apktoolPath, apkFastPath, outFilePath, appType, android_package_type);

//        Map<String, Object> map = ManiParse.parseAndroidManifest(outFilePath + "/AndroidManifest.xml", appType);
//        File file = new File(apktoolPath);
//        Map<String, Object> aapt = parseAndroidApk(file.getParentFile().getAbsolutePath() + "/aapt", apkFastPath);
//        map.putAll(aapt);
//        Map<String, Object> parsePackage = ManiParse.parsePackage(outFilePath);
//        map.putAll(parsePackage);
//        Map<String, Object> domainName = ManiParse.parseDomainName(outFilePath);
//        map.putAll(domainName);
        return map;
    }

    public Map<String, Object> parseAndroidApk(String aaptPath, String apkPath) {

        Map<String, Object> result = new HashMap<>();

        try {
            String cmd = aaptPath + " dump badging " + apkPath;
            Process process = Runtime.getRuntime().exec(cmd);
            System.out.println(cmd);
            BufferedReader bis = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";

            while ((line = bis.readLine()) != null) {
                try {
//                    LogUtils.logJson(line);
                    if (line.contains("sdkVersion")) {
                        try {
                            String sdkVersion = line.replace("sdkVersion:'", "").replace("'", "");
                            result.put("sdkVersion", Integer.parseInt(sdkVersion));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (line.contains("targetSdkVersion")) {
                        try {
                            String targetSdkVersion = line.replace("targetSdkVersion:'", "").replace("'", "");
                            result.put("targetSdkVersion", Integer.parseInt(targetSdkVersion));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (line.contains("versionName")) {
                        line = line.replace("package: name", "packageName");
                        String[] verline = line.split(" ");
                        for (String item : verline) {
                            String[] split = item.split("=");
                            String name = split[0];
                            String value = split[1].replace("'", "").replace("'", "");
                            if (AppConfig.APK_INFO.VERSION_NAME.equals(name)) {
                                result.put(name, new Jentity(name, value, CheckUtils.checkVersionName(value) ? 1 : 0));
                            } else if (AppConfig.APK_INFO.VERSION_CODE.equals(name)) {
                                result.put(name, new Jentity(name, value, CheckUtils.checkVersionCode(value) ? 1 : 0));
                            } else {
                                result.put(name, value);
                            }
                        }
                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

            bis.close();
        } catch (Exception var9) {
            System.out.println("parseAndroidApk7" + var9.getMessage());

            var9.printStackTrace();
        }
        return result;
    }

    public Map<String, Object> parseAndroidManifest(String path, UserParam userParam) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(path);


        List<AppPermissions> appPermissions = parsePermissions(document, userParam);
        Application application = parseApplication(document);
        Activity activity = parseLauncherActivity(path, document);
        List<MetaData> metaData = parseMetadata(document);
        List<Query> queries = parseQueries(document);
        List<Provider> providers = parseProviders(document);
        List<Jentity> usesFeature = parseUsesFeature(document);
        BackUp backUp = parseBackUp(path, document);
        Map<String, Object> map = new HashMap<>();
        map.put("activity", activity);
        map.put("metaData", metaData);
        map.put("permission", appPermissions);
        map.put("application", application);
        map.put("backUp", backUp);
        map.put("usesFeature", usesFeature);
        map.put("queries", queries);
        map.put("providers", providers);
        return map;
    }


    private List<Jentity> parseUsesFeature(Document document) {
        XPath xPath = new DefaultXPath("/manifest/uses-feature");
        List<Element> list = xPath.selectNodes(document.getRootElement());
        List<Jentity> dataList = new ArrayList<>();
        for (Element element : list) {
            String name = element.attributeValue("name");
            String required = element.attributeValue("required");
            int state = 0;
            if ("false".equals(required)) {
                state = 1;
            }
            Jentity jentity = new Jentity(name, required, state);
            dataList.add(jentity);
        }
        return dataList;
    }

    private List<Provider> parseProviders(Document document) {
        XPath xPath = new DefaultXPath("/manifest/application/provider");
        List<Element> list = xPath.selectNodes(document.getRootElement());
        List<Provider> dataList = new ArrayList<>();
        for (Element element : list) {
            Provider provider = new Provider();
            provider.setName(element.attributeValue("name"));
            provider.setAuthorities(element.attributeValue("authorities"));
            provider.setExported(element.attributeValue("exported"));
            provider.setGrantUriPermissions(element.attributeValue("grantUriPermissions"));
            Element meta = element.element("meta-data");
            if (meta != null) {
                MetaData metaData = new MetaData();
                String name = element.attributeValue("name");
                String value = element.attributeValue("value");
                String resource = element.attributeValue("resource");
                metaData.setResource(resource);
                metaData.setName(name);
                metaData.setValue(value);
                provider.setMetaData(metaData);
            }


            dataList.add(provider);
        }
        return dataList;
    }

    private List<Query> parseQueries(Document document) {
        XPath xPath = new DefaultXPath("/manifest/queries/intent/action");
        List<Element> list = xPath.selectNodes(document.getRootElement());
        List<Query> dataList = new ArrayList<>();

        String mainFlag = "android.intent.action.MAIN";
        boolean hashMain = false;
        for (Element element : list) {
            Query query = new Query();
            String name = element.attributeValue("name");
            query.setActionName(name);
            query.setState(1);
            if (name != null && name.contains(mainFlag)) {
                Element parent = element.getParent();
                List<Element> elements = parent.elements();
                if (elements.size() == 1) {
                    hashMain = true;
                    dataList.add(query);
                }
            } else {
                dataList.add(query);
            }
        }
        if (!hashMain) {
            Query query = new Query();
            query.setActionName(mainFlag);
            query.setState(0);
            dataList.add(query);
        }

        return dataList;
    }

    private BackUp parseBackUp(String path, Document document) {
        Element root = document.getRootElement();
        BackUp backUp = new BackUp();
        int state = 1;
        try {
            Element applicationElement = root.element("application");
            backUp.setAllowBackup(false);
            if ("true".equals(applicationElement.attributeValue("allowBackup"))) {
                backUp.setAllowBackup(true);
                String fullBackupContent = applicationElement.attributeValue("fullBackupContent");
                backUp.setFullBackupContent(fullBackupContent);
                File file = new File(path);

                if (!TextUtils.isEmpty(fullBackupContent)) {
                    String fileName = fullBackupContent.replace("@xml/", "");
                    String fullBackupContentPath = file.getParentFile().getAbsolutePath() + "/res/xml/" + fileName + ".xml";
                    File file1 = new File(fullBackupContentPath);
                    if (!file1.exists()) {

                    } else {
                        SAXReader reader = new SAXReader();
                        Document doc = reader.read(fullBackupContentPath);
                        XPath includeXPath = new DefaultXPath("/full-backup-content/include");
                        List<Element> includeList = includeXPath.selectNodes(doc.getRootElement());
                        int sharedprefFlag = 0;

                        for (Element element : includeList) {
                            if ("sharedpref".equals(element.attributeValue("domain")) && ".".equals(element.attributeValue("path"))) {
                                sharedprefFlag = 1;
                                continue;
                            }
                            if ("sharedpref".equals(element.attributeValue("domain")) && ".".equals(element.attributeValue("appsflyer-data"))) {
                                sharedprefFlag = 1;
                                continue;
                            }
                        }
                        if (sharedprefFlag == 1) {
                            XPath xPathExclude = new DefaultXPath("/full-backup-content/exclude");
                            List<Element> excludeList = xPathExclude.selectNodes(doc.getRootElement());
                            int appsflyer = 0;
                            for (Element element : excludeList) {
                                if ("sharedpref".equals(element.attributeValue("domain")) && "appsflyer-data".equals(element.attributeValue("path"))) {
                                    appsflyer = 1;
                                    break;
                                }
                            }
                            state = appsflyer;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Jentity jentity = new Jentity("BackUp", "", state);
        backUp.setJentity(jentity);

        return backUp;

    }

    private List<MetaData> parseMetadata(Document document) {
        XPath xPath = new DefaultXPath("/manifest/application/meta-data");
        List<Element> list = xPath.selectNodes(document.getRootElement());
        List<MetaData> dataList = new ArrayList<>();
        for (Element element : list) {
            MetaData metaData = new MetaData();
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            String resource = element.attributeValue("resource");
            metaData.setResource(resource);
            metaData.setName(name);
            if (value != null && value.startsWith("@string/")) {
                String rep = value.replace("@string/", "");
                value = stringMap.get(rep);
                Gson gson = new Gson();
                System.out.println(gson.toJson(stringMap));


                System.out.println(value + "=================rep   19999============" + rep);
            }

            metaData.setValue(value);
            dataList.add(metaData);
        }
        return dataList;
    }

    private Activity parseLauncherActivity(String path, Document document) {
        Activity activity = new Activity();
        XPath xPath = new DefaultXPath("/manifest/application/activity/intent-filter/category");
        List<Element> list = xPath.selectNodes(document.getRootElement());
        String launcherTag = "android.intent.category.LAUNCHER";
        Element launcherActivity = null;
        for (Element element : list) {
            if (launcherTag.equals(element.attributeValue("name"))) {
                launcherActivity = element.getParent().getParent();
                activity.setExported(launcherActivity.attributeValue("exported"));
                activity.setLauncherName(launcherActivity.attributeValue("name"));
                break;
            }
        }
        if (launcherActivity != null) {

            list = launcherActivity.selectNodes("intent-filter");
            for (Element element : list) {
                Element data = element.element("data");
                if (data != null) {
                    String host = data.attributeValue("host");
                    String scheme = data.attributeValue("scheme");
                    if ("https".equals(scheme)) {
                        /**
                         *AppLink
                         */
                        AppLink appLink = new AppLink();
                        appLink.setHost(host);
                        appLink.setScheme(scheme);
                        activity.setAppLink(appLink);
                    } else {
                        /**
                         * App scheme
                         */
                        List<Element> categorys = element.elements("category");
                        Element action = element.element("action");
                        List<Element> actions = element.elements("action");
                        int status = 1;
                        if (actions != null && actions.size() == 1) {
                            String name = "";
                            if (action != null) {
                                name = action.attributeValue("name");
                            }
                            if (!"android.intent.action.VIEW".equals(name)) {
                                status = 0;
                            }
                        } else {
                            status = 0;
                        }
                        if (categorys != null && categorys.size() == 2) {
                            int code = 0;
                            for (Element e : categorys) {
                                String name = e.attributeValue("name");
                                if ("android.intent.category.DEFAULT".equals(name)) {
                                    code++;
                                }
                                if ("android.intent.category.BROWSABLE".equals(name)) {
                                    code++;
                                }
                            }
                            if (code != 2) {
                                status = 0;
                            }
                        } else {
                            status = 0;
                        }


//                        File file = new File(path);
//                        String stringsPath = file.getParentFile().getAbsolutePath() + "/res/values/strings.xml";
//                        if (scheme != null && scheme.startsWith("@string/")) {
//                            String schemeKey = scheme.replace("@string/", "");
//
//                        }


                        if (scheme != null && scheme.startsWith("@string/")) {
                            String rep = scheme.replace("@string/", "");
                            scheme = stringMap.get(rep);
                            if (!RegexUtils.isMatch(RegexConstants.REGEX_SCHEME, scheme)) {
                                status = 0;
                            }
                        }

                        if (!RegexUtils.isMatch(RegexConstants.REGEX_SCHEME, scheme)) {
                            status = 0;
                        }

                        if (host != null && host.startsWith("@string/")) {
                            String rep = host.replace("@string/", "");
                            host = stringMap.get(rep);

                        }

                        if (!TextUtils.isEmpty(host) && !RegexUtils.isMatch(RegexConstants.REGEX_SCHEME, host)) {
                            status = 0;
                        }


                        Scheme sch = new Scheme();
                        sch.setScheme(scheme);
                        sch.setHost(host);
                        sch.setStatus(status);
                        String message = "<intent-filter>\n" +
                                "                <data\n" +
                                "                    android:host=\"ffff\"\n" +
                                "                    android:scheme=\"xxxx\" />\n" +
                                "                <action android:name=\"android.intent.action.VIEW\" />\n" +
                                "                <category android:name=\"android.intent.category.DEFAULT\" />\n" +
                                "                <category android:name=\"android.intent.category.BROWSABLE\" />\n" +
                                "         </intent-filter>";
                        sch.setMessage("配置错误");
                        activity.setScheme(sch);
                    }
                }
            }


        }


//        XPath xPath = new DefaultXPath("/manifest/application/activity");
//        List<Element> list = xPath.selectNodes(document.getRootElement());
//        for (Element element : list) {
//
//            Element element1 = element.element("intent-filter");
//
//        }
        return activity;
    }

    private Application parseApplication(Document document) {
        Application application = new Application();

        Element root = document.getRootElement();
        try {
            application.setCompileSdkVersion(Integer.parseInt(root.attributeValue("compileSdkVersion")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Element applicationElement = root.element("application");
        application.setRequestLegacyExternalStorage(applicationElement.attributeValue("requestLegacyExternalStorage"));
        application.setNetworkSecurityConfig(applicationElement.attributeValue("networkSecurityConfig"));
        application.setPackageName(root.attributeValue("package"));
        application.setLargeHeap(applicationElement.attributeValue("largeHeap"));

//        XPath xPath = new DefaultXPath("/manifest/application");
//        List<Element> list = xPath.selectNodes(document.getRootElement());
//        for (Element element : list) {
//            application.setRequestLegacyExternalStorage(element.attributeValue("requestLegacyExternalStorage"));
//            application.setNetworkSecurityConfig(element.attributeValue("networkSecurityConfig"));
//        }

        return application;
    }

    private List<AppPermissions> parsePermissions(Document document, UserParam userParam) {
        XPath xPath = new DefaultXPath("/manifest/uses-permission");
        List<Element> list = xPath.selectNodes(document.getRootElement());
        List<AppPermissions> resultList = null;

        System.out.println("=========TYPE_DEBUG1============" + userParam.getAppType());

        if (AppConfig.AppType.TYPE_DEBUG1.equals(userParam.getAppType())) {

            System.out.println("=========TYPE_DEBUG1============");
//            resultList = createDebug(list);
            OkPermissionsFactory factory = new OkPermissionsFactory(list, PermissionUtils.permissionsAll, PermissionUtils.permissionsDebugMast, userParam);
            resultList = factory.create();


        } else if (AppConfig.AppType.TYPE_RELEASE.equals(userParam.getAppType())) {
            /**
             * targetSDK是不是33
             */
            String targetSdk = userParam.getTargetSdk();
            int targetSdkInt = 0;
            try {
                targetSdkInt = Integer.parseInt(targetSdk);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (targetSdkInt >= 33) {
//                resultList = createRelease33(list);
                OkPermissionsFactory factory = new OkPermissionsFactory(list, PermissionUtils.permissionsAll, PermissionUtils.permissionsReleaseMast33, userParam);
                resultList = factory.create();

            } else {
//                resultList = createRelease(list);
                OkPermissionsFactory factory = new OkPermissionsFactory(list, PermissionUtils.permissionsAll, PermissionUtils.permissionsReleaseMast, userParam);
                resultList = factory.create();
            }
        } else if (AppConfig.AppType.TYPE_RELEASE531.equals(userParam.getAppType())) {
//            resultList = createRelease(list);
            OkPermissionsFactory factory = new OkPermissionsFactory(list, PermissionUtils.permissions531All, PermissionUtils.permissionsReleaseMast531, userParam);
            resultList = factory.create();
        } else if (AppConfig.AppType.TYPE_DEBUG531.equals(userParam.getAppType())) {
//            resultList = createRelease(list);
            OkPermissionsFactory factory = new OkPermissionsFactory(list, PermissionUtils.permissions2024All, PermissionUtils.permissions2024DebugMast, userParam);
//            OkPermissionsFactory factory = new OkPermissionsFactory(list, PermissionUtils.permissions531All, PermissionUtils.permissionsDebugMast531, userParam);
            resultList = factory.create();
        }

        return resultList;
    }

//    private static List<AppPermissions> createDebug(List<Element> list) {
//        List<AppPermissions> resultList = new ArrayList<>();
//
//        for (Element element : list) {
//            String name = element.attributeValue("name");
//            AppPermissions appPermisstions = new AppPermissions();
//            appPermisstions.setPermission(name);
//            appPermisstions.setState(2);
//            if (insideDebugAllPermission(appPermisstions.getPermission())) {
//                appPermisstions.setState(1);
//            } else {
//                appPermisstions.setState(3);
//            }
//            resultList.add(appPermisstions);
//        }
//        Collections.sort(resultList);
//        return resultList;
//    }
//
//
//    private static List<AppPermissions> createRelease(List<Element> list) {
//        List<AppPermissions> resultList = new ArrayList<>();
//
//        List<AppPermissions> dataList = new ArrayList<>();
//        for (int j = 0; j < PermissionUtils.permissionsReleaseMast.length; j++) {
//            AppPermissions appPermisstions = new AppPermissions();
//            appPermisstions.setPermission(PermissionUtils.permissionsReleaseMast[j]);
//            dataList.add(appPermisstions);
//        }
//
//        for (Element element : list) {
//            String name = element.attributeValue("name");
//            AppPermissions appPermisstions = new AppPermissions();
//            appPermisstions.setPermission(name);
//            appPermisstions.setState(2);
//            boolean isOutside = true;
//
//            for (int j = 0; j < dataList.size(); j++) {
//                AppPermissions tempPer = dataList.get(j);
//                if (tempPer.getPermission().equals(appPermisstions.getPermission())) {
//                    tempPer.setState(1);
//                    isOutside = false;
//                    break;
//                }
//            }
//            if (isOutside) {
//                /**
//                 * 判断
//                 */
////                for (String permkill : PermissionUtils.permissionKillList) {
////                    if (permkill.equals(appPermisstions.getPermission())) {
////                        appPermisstions.setState(3);
////                        break;
////                    }
////                }
//                if (!insideAllPermission(appPermisstions.getPermission())) {
//                    LogUtils.log("========1========" + appPermisstions.getPermission());
//                    appPermisstions.setState(3);
//                }
//                resultList.add(appPermisstions);
//            }
//        }
//        resultList.addAll(dataList);
//        Collections.sort(resultList);
//        return resultList;
//    }
//
//    private static List<AppPermissions> createRelease33(List<Element> list) {
//        List<AppPermissions> resultList = new ArrayList<>();
//
//        List<AppPermissions> dataList = new ArrayList<>();
//        for (int j = 0; j < PermissionUtils.permissionsReleaseMast33.length; j++) {
//            AppPermissions appPermisstions = new AppPermissions();
//            appPermisstions.setPermission(PermissionUtils.permissionsReleaseMast33[j]);
//            dataList.add(appPermisstions);
//        }
//
//        for (Element element : list) {
//            String name = element.attributeValue("name");
//            AppPermissions appPermisstions = new AppPermissions();
//            appPermisstions.setPermission(name);
//            appPermisstions.setState(2);
//            boolean isOutside = true;
//
//            for (int j = 0; j < dataList.size(); j++) {
//                AppPermissions tempPer = dataList.get(j);
//                if (tempPer.getPermission().equals(appPermisstions.getPermission())) {
//                    tempPer.setState(1);
//                    isOutside = false;
//                    break;
//                }
//            }
//            if (isOutside) {
//                /**
//                 * 判断
//                 */
////                for (String permkill : PermissionUtils.permissionKillList) {
////                    if (permkill.equals(appPermisstions.getPermission())) {
////                        appPermisstions.setState(3);
////                        break;
////                    }
////                }
//                if (!insideAllPermission(appPermisstions.getPermission())) {
//                    LogUtils.log("========1========" + appPermisstions.getPermission());
//                    appPermisstions.setState(3);
//                }
//                resultList.add(appPermisstions);
//            }
//        }
//        resultList.addAll(dataList);
//        Collections.sort(resultList);
//        return resultList;
//    }
//
//
//    private static boolean insideDebugAllPermission(String currentPermission) {
//        if (!currentPermission.startsWith("android.permission.")) {
//            return true;
//        }
//
//        for (String item : PermissionUtils.permissionsDebugMast) {
//            if (item.equals(currentPermission)) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    private static boolean insideAllPermission(String currentPermission) {
//        if (!currentPermission.startsWith("android.permission.")) {
//            return true;
//        }
//
//        for (String item : PermissionUtils.permissionsAll) {
//            if (item.equals(currentPermission)) {
//                return true;
//            }
//        }
//
//        return false;
//    }
}
