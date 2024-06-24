package com.example.demo.utils;

import com.example.demo.bean.DebtBean;

import java.util.ArrayList;
import java.util.List;

public class DebtLoop {
    private static DebtLoop debtLoop;
    private static List<DebtBean> urlList = new ArrayList<>();

    private static void getInstance() {
        if (debtLoop == null) {
            debtLoop = new DebtLoop();
            urlList.clear();
            urlList.add(new DebtBean("哥伦比亚催收", "https://www.colombiapresta.com/collection/v1"));
            urlList.add(new DebtBean("墨西哥62催收", "https://web.nubemx.com/api/collection"));
            urlList.add(new DebtBean("", ""));
            urlList.add(new DebtBean("", ""));
            urlList.add(new DebtBean("", ""));
            urlList.add(new DebtBean("", ""));
            urlList.add(new DebtBean("", ""));
        }

    }


}
