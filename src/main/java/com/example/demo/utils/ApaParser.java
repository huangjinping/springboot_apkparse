package com.example.demo.utils;

import com.example.demo.bean.Jentity;

import java.io.File;
import java.util.*;

public class ApaParser {

    public static String PERMISSION_END = "UsageDescription";

    public String appType;

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public Map<String, Object> parseIOSipa(String rootPath) {
        Map<String, Object> resultMap = new HashMap<>();
        LogUtils.logJson("======resultFile111111111111========" + rootPath);
        Map<String, Object> objectMap = parseIOSPlistInfo(rootPath);

        resultMap.putAll(objectMap);
        LogUtils.logJson(objectMap);

        Threadipa threadipa = new Threadipa(this);
        resultMap.putAll(threadipa.parseIpaData(rootPath, "3"));


        return resultMap;
    }

    public Map<String, Object> parseIOSPlistInfo(String rootPath) {
        String plistInfo = rootPath + File.separator + "Info.plist.xml";
        Map<String, Object> info = InfoPlistParser.parsePlist(plistInfo);
        LogUtils.logJson(info);

//        parsePermission(info);
        Map<String, Object> result = new HashMap<>();

        List<Jentity> permissionList = new ArrayList<>();
        List<Jentity> configList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : info.entrySet()) {
            String key = entry.getKey();
            if (key.endsWith(PERMISSION_END)) {
                permissionList.add(new Jentity(key, entry.getValue(), 1));
                LogUtils.logJson("======resultFile111111111111========" + key);
            }

            if (key.equals("CFBundleIdentifier")) {
                configList.add(new Jentity(key, entry.getValue(), 1));
            }
            if (key.equals("MinimumOSVersion")) {
                configList.add(new Jentity(key, entry.getValue(), 1));
            }
            if (key.equals("CFBundleShortVersionString")) {
                configList.add(new Jentity(key, entry.getValue(), 1));
            }
            if (key.equals("CFBundleVersion")) {
                configList.add(new Jentity(key, entry.getValue(), 1));
            }
            if (key.equals("CFBundleDisplayName")) {
                configList.add(new Jentity(key, entry.getValue(), 1));
            }

            if (key.equals("BuildMachineOSBuild")) {
                configList.add(new Jentity(key, entry.getValue(), 1));
            }
            if (key.equals("DTSDKName")) {
                configList.add(new Jentity(key, entry.getValue(), 1));
            }
            if (key.equals("DTXcode")) {
                configList.add(new Jentity(key, entry.getValue(), 1));
            }
            if (key.equals("DTSDKBuild")) {
                configList.add(new Jentity(key, entry.getValue(), 1));
            }


        }


//        UISupportedInterfaceOrientations~ipad
        configList.add(CheckUtils.getJentityByMapForArrayOrientationsIpad("UISupportedInterfaceOrientations~ipad", info));

        try {
            Collections.sort(permissionList, new AppComparator());
            Collections.sort(configList, new AppComparator());
        } catch (Exception e) {
            e.printStackTrace();
        }


        LogUtils.logJson(permissionList);

        if ("3".equals(appType)) {
            IpaPermissionsFactory factory = new IpaPermissionsFactory(permissionList, PermissionUtils.permissionsIPAAll, PermissionUtils.permissionsIPAReleaseMast);
            List<Jentity> jentities = factory.create();
            result.put("permission", new Jentity("permission", new Jentity("permission", jentities, 1), 1));
            configList.add(CheckUtils.getJentityByMap("FacebookAppID", info));
            configList.add(CheckUtils.getJentityByMap("FacebookClientToken", info));
            configList.add(CheckUtils.getJentityByMap("FacebookDisplayName", info));
        } else if ("4".equals(appType)) {
            IpaPermissionsFactory factory = new IpaPermissionsFactory(permissionList, PermissionUtils.permissions2024ipaAll, PermissionUtils.permissions2024DebugipaMast);
            List<Jentity> jentities = factory.create();
            result.put("permission", new Jentity("permission", new Jentity("permission", jentities, 1), 1));
        }
        result.put("configInfo", new Jentity("configInfo", new Jentity("configInfo", configList, 1), 1));
        return result;
    }


    public void parsePermission(Map<String, Object> info) {
        for (Map.Entry<String, Object> entry : info.entrySet()) {
            String key = entry.getKey();
            if (key.endsWith(PERMISSION_END)) {
                LogUtils.logJson("======resultFile111111111111========" + key);
            }
        }
    }


}
