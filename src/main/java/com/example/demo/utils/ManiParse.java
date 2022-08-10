package com.example.demo.utils;

import com.example.demo.bean.*;
import com.google.gson.Gson;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;
import org.dom4j.xpath.DefaultXPath;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ManiParse {


    public static Map<String, Object> parseAndroidManifestByCmd(String apktoolPath, String miniFastPath, String outFilePath) throws Exception {
        String cmd = "java -jar " + apktoolPath + " d " + miniFastPath + " -o " + outFilePath;
        System.out.println(cmd);
        Process process = Runtime.getRuntime().exec(cmd);
        int value = process.waitFor();
        Map<String, Object> map = ManiParse.parseAndroidManifest(outFilePath + "/AndroidManifest.xml");
        return map;
    }


    public static Map<String, Object> parseAndroidManifestByCmd(String miniFastPath, String outFilePath) throws Exception {
        String cmd = "apktool d " + miniFastPath + " -o " + outFilePath;
        Process process = Runtime.getRuntime().exec(cmd);
        int value = process.waitFor();
        Map<String, Object> map = ManiParse.parseAndroidManifest(outFilePath + "/AndroidManifest.xml");
        return map;
    }


    public static Map<String, Object> parseAndroidManifest(String path) throws DocumentException {
        SAXReader reader = new SAXReader();
        File file = new File(path);
        Document document = reader.read(path);
        List<AppPermissions> appPermissions = parsePermissions(document);
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

    private static List<Provider> parseProviders(Document document) {
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

    private static List<Query> parseQueries(Document document) {
        XPath xPath = new DefaultXPath("/manifest/queries/intent/action");
        List<Element> list = xPath.selectNodes(document.getRootElement());
        List<Query> dataList = new ArrayList<>();
        for (Element element : list) {
            Query query = new Query();
            String name = element.attributeValue("name");
            query.setActionName(name);
            dataList.add(query);
        }
        return dataList;
    }


    private static List<MetaData> parseMetadata(Document document) {
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

    private static Activity parseLauncherActivity(Document document) {
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
                        Scheme sch = new Scheme();
                        sch.setScheme(scheme);
                        sch.setHost(host);
                        activity.setScheme(sch);
                    }
                }


            }
            System.out.println("======2=");

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


    private static Application parseApplication(Document document) {
        Application application = new Application();

        Element root = document.getRootElement();
        application.setCompileSdkVersion(Integer.parseInt(root.attributeValue("compileSdkVersion")));
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

    private static List<AppPermissions> parsePermissions(Document document) {
        XPath xPath = new DefaultXPath("/manifest/uses-permission");
        List<Element> list = xPath.selectNodes(document.getRootElement());
        List<AppPermissions> dataList = new ArrayList<>();
        for (int j = 0; j < PermissionUtils.permissionsMast.length; j++) {
            AppPermissions appPermisstions = new AppPermissions();
            appPermisstions.setPermission(PermissionUtils.permissionsMast[j]);
            dataList.add(appPermisstions);
        }
        List<AppPermissions> resultList = new ArrayList<>();

        for (Element element : list) {
            String name = element.attributeValue("name");
            AppPermissions appPermisstions = new AppPermissions();
            appPermisstions.setPermission(name);
            appPermisstions.setState(2);
            boolean isOutside = true;

            for (int j = 0; j < dataList.size(); j++) {
                AppPermissions tempPer = dataList.get(j);
                if (tempPer.getPermission().equals(appPermisstions.getPermission())) {
                    tempPer.setState(1);
                    isOutside = false;
                    break;
                }
            }
            if (isOutside) {
                /**
                 * 判断
                 */
                for (String permkill : PermissionUtils.permissionKillList) {
                    if (permkill.equals(appPermisstions.getPermission())) {
                        appPermisstions.setState(3);
                        break;
                    }
                }
                resultList.add(appPermisstions);
            }
        }
        resultList.addAll(dataList);

        Collections.sort(resultList);
        return resultList;
    }

}
