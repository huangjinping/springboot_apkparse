package com.example.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.LoginUser;

import java.util.HashMap;
import java.util.Map;

public class InxSpider {
    //    public static final String reiterateheaderticketexplanation = "http://52.40.151.34/eastbay";
//    public static final String host = "http://192.8.20.201:5700";
    public static final String host = "https://glby.ultracreditosmx.com/api/app/";
    LoginUser loginUser;
    String currentPhoneNo = "1829999881";

    public Map<String, String> commMap() {
        Map<String, String> comm = new HashMap<>();
        comm.put("appssid", "17");
        comm.put("client-id", "17");

        if (loginUser != null) {
            comm.put("token", loginUser.getToken());
            comm.put("currentUserId", loginUser.getUserId());
            comm.put("userId", loginUser.getUserId());
        }
        return comm;
    }

//    public static final String host = "https://www.glbyeastbay.com/eastbay";

    public void start() {
        getVerifCode();
        if (loginUser != null) {
            getImageList();
            getVip();
            getIdentificationResult();
        }
    }

    //    https://www.glbyeastbay.com/eastbay/friend/forceNicePioneer
    public void getVerifCode() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        mapParam.put("phoneNo", currentPhoneNo);
        mapParam.put("smsCode", "1234");
        Map<String, String> header = commMap();
        header.put("v-flag", "true");
        header.put("apiName", AESUtil.encrypt("/login/getVerifCode", "0121170eedf910c65bf10b2cf5820202"));
//        LogUtils.logJson(commMap());
        String respStr = OkHttpUtils.postForm(host, header, mapParam);
        JSONObject jsonObject = JSON.parseObject(respStr);
        String smsCode = jsonObject.getString("code");
        LogUtils.logJson(respStr);

        loginForSms(currentPhoneNo, "1234");
//        object.getString("");


    }

    private void loginForSms(String phone, String smsCode) {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        mapParam.put("phoneNo", phone);
        mapParam.put("smsCode", smsCode);
        Map<String, String> header = commMap();
        header.put("v-flag", "true");
        header.put("apiName", AESUtil.encrypt("/login/loginForSms", "0121170eedf910c65bf10b2cf5820202"));
        String respStr = OkHttpUtils.postForm(host, header, mapParam);
        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString("code");
        if ("1000".equals(code)) {
            JSONObject data = jsonObject.getJSONObject("data");
            String userId = data.getString("userId");
            String token = data.getString("token");
            LoginUser user = new LoginUser();
            user.setUserId(userId);
            user.setToken(token);
            loginUser = user;
        }
    }

    public void getImageList() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        mapParam.put("type", "03");
        Map<String, String> header = commMap();
        header.put("v-flag", "true");
        header.put("apiName", AESUtil.encrypt("/anon/getAppImageList", "0121170eedf910c65bf10b2cf5820202"));
        String respStr = OkHttpUtils.postForm(host, header, mapParam);
        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);

        String code = jsonObject.getString("code");
        if ("1000".equals(code)) {

        }
    }

    public void getVip() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        header.put("v-flag", "true");
        header.put("apiName", AESUtil.encrypt("/cust/vipProducts", "0121170eedf910c65bf10b2cf5820202"));
        String respStr = OkHttpUtils.postForm(host, header, mapParam);
        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);

        String code = jsonObject.getString("code");
        if ("1000".equals(code)) {
        }
    }

    public void getIdentificationResult() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        header.put("v-flag", "true");
        header.put("apiName", AESUtil.encrypt("/cust/getIdentificationResult", "0121170eedf910c65bf10b2cf5820202"));
        String respStr = OkHttpUtils.postForm(host, header, mapParam);
        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString("code");
        if ("1000".equals(code)) {

        }
    }



}
