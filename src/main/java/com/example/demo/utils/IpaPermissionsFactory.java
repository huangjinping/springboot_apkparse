package com.example.demo.utils;

import com.example.demo.bean.Jentity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IpaPermissionsFactory {

    public List<Jentity> currentElementList;
    private String[] permissionsAll;
    private String[] permissionsNeed;

    public IpaPermissionsFactory(List<Jentity> currentElementList, String[] permissionsAll, String[] permissionsNeed) {
        this.currentElementList = currentElementList;
        this.permissionsAll = permissionsAll;
        this.permissionsNeed = permissionsNeed;
    }

    public List<Jentity> create() {
        List<Jentity> resultList = new ArrayList<>();

        List<Jentity> dataList = new ArrayList<>();
        for (int j = 0; j < permissionsNeed.length; j++) {
            Jentity appPermisstions = new Jentity(permissionsNeed[j], "", 0);
            appPermisstions.setName(permissionsNeed[j]);
            dataList.add(appPermisstions);
        }

        for (Jentity element : currentElementList) {
//            element.setState(2);

            Jentity jentity = new Jentity(element.getName(), element.getValue(), 0);
            jentity.setState(2);
            boolean isOutside = true;
            for (int j = 0; j < dataList.size(); j++) {
                Jentity tempPer = dataList.get(j);
                if (tempPer.getName().equals(element.getName())) {
                    tempPer.setState(1);
                    tempPer.setValue(element.getValue());
                    isOutside = false;
                    break;
                }
            }
            if (isOutside) {

                /**
                 * 判断
                 */
                if (!insideAllPermission(jentity.getName())) {
                    jentity.setState(3);
                } else {
                }
                resultList.add(jentity);
            }
            jentity.setState(jentity.getState());
        }
        resultList.addAll(dataList);
        Collections.sort(resultList, new AppComparatorStats());
        return resultList;
    }

    private boolean insideAllPermission(String currentPermission) {
//        if (!isSystemPermission(currentPermission)) {
//            return true;
//        }
        for (String item : permissionsAll) {
            if (item.equals(currentPermission)) {
                return true;
            }
        }
        return false;
    }


}
