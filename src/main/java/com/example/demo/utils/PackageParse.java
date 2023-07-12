package com.example.demo.utils;

import com.example.demo.bean.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;
import org.dom4j.xpath.DefaultXPath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackageParse {


    String mRealFilePath;

    String mApkFastPath;
    String mOutFilePath;
    String mAppType;
    String mApktoolPath;

    public static Map<String, Object> parseMethod(String filePath) {
        Map<String, Object> parseDomainNameResult = new HashMap<>();
        try {
            SearchTask searchTask = new SearchTask();

            List<MethodSolr> resultAll = new ArrayList<>();
            resultAll.addAll(searchTask.getMethodSolr_ssl(filePath));
            resultAll.addAll(searchTask.getMethodSolr_phoneNumber(filePath));
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

    public static Map<String, Object> parsePackage(String filePath) {
        Map<String, Object> parsePackageResult = new HashMap<>();
        try {
            LogUtils.log("parsePackage====:" + filePath);
            ArrayList<Object> fileList = FolderFileScanner.startScanFilesWithRecursion(filePath);
            parsePackageResult.put("keepPackage", getKeepList(fileList));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parsePackageResult;
    }

    private static List<KeepPackage> getKeepList(ArrayList<Object> fileList) {
        Map<String, String> catchMap = new HashMap<>();
        List<KeepPackage> packageList = new ArrayList<>();


        List<KeepPackage> shouldList = new ArrayList<>();
        for (String shouldItem : FolderFileScanner.PACKAGE_SHOULD_LIST) {
            KeepPackage keepPackage = new KeepPackage();
            keepPackage.setName(shouldItem);
            keepPackage.setState(0);
            shouldList.add(keepPackage);
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


//    public static Map<String, Object> parseAndroidManifestByCmd(String miniFastPath, String outFilePath) throws Exception {
//        String cmd = "apktool d " + miniFastPath + " -o " + outFilePath;
//        Process process = Runtime.getRuntime().exec(cmd);
//        int value = process.waitFor();
//        Map<String, Object> map = ManiParse.parseAndroidManifest(outFilePath + "/AndroidManifest.xml");
//        return map;
//    }

    public Map<String, Object> parseAndroidManifestByCmd(String apktoolPath, String apkFastPath, String outFilePath, String appType) throws Exception {

//        LogUtils.log("==parseAndroidManifestByCmd==============appType===========" + appType);

        String cmd = "java -jar " + apktoolPath + " d " + apkFastPath + " -o " + outFilePath;
//        System.out.println(cmd);
        Process process = Runtime.getRuntime().exec(cmd);
        int value = process.waitFor();

        ThreadM threadM = new ThreadM();
        Map<String, Object> map = threadM.parseApkData(apktoolPath, apkFastPath, outFilePath, appType);

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
//                        if (line.contains("compileSdkVersion") && line.contains("versionName")) {
                        line = line.replace("package: name", "packageName");
                        String[] verline = line.split(" ");
                        for (String item : verline) {
//                            System.out.println(item);

                            String[] split = item.split("=");
//                            System.out.println(split[0]);

                            result.put(split[0], split[1].replace("'", "").replace("'", ""));
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
        Activity activity = parseLauncherActivity(document);
        List<MetaData> metaData = parseMetadata(document);
        List<Query> queries = parseQueries(document);
        List<Provider> providers = parseProviders(document);
        Map<String, Object> map = new HashMap<>();
        map.put("activity", activity);
        map.put("metaData", metaData);
        map.put("permission", appPermissions);
        map.put("application", application);
        map.put("queries", queries);
        map.put("providers", providers);
        return map;
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
            metaData.setValue(value);
            dataList.add(metaData);
        }
        return dataList;
    }

    private Activity parseLauncherActivity(Document document) {
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
                        System.out.println("===parseLauncherActivity===3=" + scheme);

                        Scheme sch = new Scheme();
                        sch.setScheme(scheme);
                        sch.setHost(host);
                        activity.setScheme(sch);
                    }
                }
            }

            LogUtils.logJson(activity);
            System.out.println("===parseLauncherActivity===2=");

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
            OkPermissionsFactory factory = new OkPermissionsFactory(list, PermissionUtils.permissions531All, PermissionUtils.permissionsDebugMast531, userParam);
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
