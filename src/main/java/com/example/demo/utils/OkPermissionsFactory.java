package com.example.demo.utils;

import com.example.demo.bean.AppPermissions;
import com.example.demo.bean.UserParam;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OkPermissionsFactory {

    public List<Element> currentElementList;
    private final String[] permissionsAll;
    private final String[] permissionsNeed;
    private final UserParam userParam;

    public OkPermissionsFactory(List<Element> currentElementList, String[] permissionsAll, String[] permissionsNeed, UserParam userParam) {
        this.currentElementList = currentElementList;
        this.permissionsAll = permissionsAll;
        this.permissionsNeed = permissionsNeed;
        this.userParam = userParam;
    }


    public List<AppPermissions> create() {
        List<AppPermissions> resultList = new ArrayList<>();

        List<AppPermissions> dataList = new ArrayList<>();
        for (int j = 0; j < permissionsNeed.length; j++) {
            AppPermissions appPermisstions = new AppPermissions();
            appPermisstions.setPermission(permissionsNeed[j]);
            dataList.add(appPermisstions);
        }

        for (Element element : currentElementList) {
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
                if (!insideAllPermission(appPermisstions.getPermission())) {
                    appPermisstions.setState(3);
                } else {
                }
                resultList.add(appPermisstions);
            }
        }
        resultList.addAll(dataList);
        Collections.sort(resultList);
        return resultList;
    }


    private boolean insideAllPermission(String currentPermission) {
        if (!isSystemPermission(currentPermission)) {
            return true;
        }
        for (String item : permissionsAll) {
            if (item.equals(currentPermission)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSystemPermission(String currentPermission) {

        if (currentPermission.startsWith("android.permission.")) {
            return true;
        }
        return currentPermission.startsWith("com.google.android.");
    }


}
