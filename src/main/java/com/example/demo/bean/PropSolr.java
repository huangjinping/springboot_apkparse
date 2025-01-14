package com.example.demo.bean;

import java.util.HashMap;

public class PropSolr {
    private int limit = 3;
    private final String propName;
    private HashMap<String, Integer> hashMap;

    private int max = 0;

    public PropSolr(String propName) {
        this.propName = propName;
        initProp();
    }


    public HashMap<String, Integer> getHashMap() {
        return hashMap;
    }

    public PropSolr(String propName, int limit) {
        this.propName = propName;
        this.limit = limit;
        initProp();
    }

    public String getPropName() {
        return propName;
    }

    public int getMax() {
        return max;
    }

    public void addProp(String value) {
        String val = value + "";
        int count = 1;
        if (hashMap.containsKey(val)) {
            count = hashMap.get(val) + 1;
            if (count > max) {
                max = count;
            }
        }
        hashMap.put(val, count);
    }

    private void initProp() {
        hashMap = new HashMap<>();
    }

    public int getResult() {
        if (max < limit) {
            return 1;
        }
        return 0;
    }

}
