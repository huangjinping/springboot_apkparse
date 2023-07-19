package com.example.demo.utils;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonFilter {

    private Map<String, String> fieldMap = new HashMap<>();
    private Map<String, String> pathMap = new HashMap<>();


    public Map<String, String> getFieldMap() {
        return fieldMap;
    }

    public Map<String, String> getPathMap() {
        return pathMap;
    }

    public void readJsonBean(String json) {
//        String json = readJsonFile(appName);

        Map<String, Object> map = JSON.parseObject(json, Map.class);

        Map<String, Object> draftsField = (Map<String, Object>) map.get("draftsField");
        Map<String, Object> commonResp = (Map<String, Object>) map.get("commonResp");
        Map<String, Object> header = (Map<String, Object>) map.get("header");
        draftsField.putAll(commonResp);
        draftsField.putAll(header);
        int i = 0;
        for (Map.Entry<String, Object> entry : draftsField.entrySet()) {
            String value = entry.getValue() + "";
            if (entry.getValue().equals("client-id")) {
                i++;
                value = value + i;
            }

            fieldMap.put(value, entry.getKey());
        }

        List<String> tempList = new ArrayList<>();

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof String) {

            } else if (value instanceof Object) {
                Map<String, Object> objectMap = (Map<String, Object>) entry.getValue();
                String url = (String) objectMap.get("url");
                tempList.add(url);
                pathMap.put(url, entry.getKey());
            }
        }
        LogUtils.logJson(tempList);
    }


    private String readJsonFile(String appName) {
        String json = null;
        json = FileUtils.getTextByPath("/Users/huhuijie/Documents/GitHub/springboot_apkparse/json/movcolombiapro.json");

        return json;
    }
}
