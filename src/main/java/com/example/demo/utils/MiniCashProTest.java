package com.example.demo.utils;

import java.util.HashMap;
import java.util.Map;

public class MiniCashProTest {

    public static void main(String[] args) {

        Map<String, String> mapParam = new HashMap<>();

        mapParam.putAll(commMap());
        mapParam.put("", "");
        mapParam.put("", "");
        Map<String, String> header = commMap();
        String respStr = OkHttpUtils.postForm("https://inter.minicash.ltd/minicash/", header, mapParam);
    }


    public static Map<String, String> commMap() {
        Map<String, String> comm = new HashMap<>();

        return comm;
    }
}
