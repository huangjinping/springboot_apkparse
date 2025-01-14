package com.example.demo.bean;

import java.util.HashMap;
import java.util.Map;

public class PropSolrGroup {


    private final HashMap<String, PropSolr> hashMap;

    public PropSolrGroup() {
        hashMap = new HashMap<>();
    }

    public void addPropSolr(String propName, String propValue) {
        PropSolr propSolr = null;

        if (hashMap.containsKey(propName)) {
            propSolr = hashMap.get(propName);
        } else {
            propSolr = new PropSolr(propName);
            hashMap.put(propName, propSolr);
        }
        propSolr.addProp(propValue);
    }

    public void addPropSolr(String propName, String propValue, int limit) {
        PropSolr propSolr = null;
        if (hashMap.containsKey(propName)) {
            propSolr = hashMap.get(propName);
        } else {
            propSolr = new PropSolr(propName, limit);
            hashMap.put(propName, propSolr);
        }
        propSolr.addProp(propValue);
    }


    public Jentity getResult() {
        int state = 1;
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, PropSolr> entry : hashMap.entrySet()) {
            PropSolr value = entry.getValue();
            if (value.getResult() < 1) {
                state = 0;
//                LogUtils.log("===142===="+value.getPropName()+"=====");
//                LogUtils.log(value.getHashMap());
                builder.append(value.getPropName() + "重复" + value.getMax() + "次");
            }
        }

        builder.append("（关注重复是否合理）　");
        Jentity jentity = new Jentity("", "", state);
        jentity.setMsg(builder.toString());
        return jentity;
    }

}
