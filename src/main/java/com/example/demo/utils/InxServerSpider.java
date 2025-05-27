package com.example.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Jentity;
import com.example.demo.bean.LoginUser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class InxServerSpider {
    //    public static final String reiterateheaderticketexplanation = "http://52.40.151.34/eastbay";
//    public static final String host = "http://192.8.20.201:5700";
//    public static final String host = "https://glby.ultracreditosmx.com/movcolombiapro";
    public String host = "https://www.movcolombiapro.com/movcolombiapro";
    public String host_URL = "https://www.movcolombiapro.com";
    LoginUser loginUser;
    String currentPhoneNo = "1832511111";
    String appssid = "40";
    Map<String, String> mPathMap;
    Map<String, String> mFieldMap;
    private final JsonFilter mJsonFilter;


    public InxServerSpider(String json, String fileName, String appssid, String domainname, String phoneNo) {
        this.appssid = appssid;
        this.host_URL = domainname;
        this.currentPhoneNo = phoneNo;
        if (domainname.endsWith("/")) {
            this.host = domainname + fileName;
        } else {
            this.host = domainname + "/" + fileName;
        }
        LogUtils.logJson(appssid);
        LogUtils.logJson(this.host_URL);
        LogUtils.logJson(this.host);

        mJsonFilter = new JsonFilter();
        mJsonFilter.readJsonBean(json);
        mPathMap = mJsonFilter.getPathMap();
        mFieldMap = mJsonFilter.getFieldMap();


    }

    public int checkRealPath(String srcUrl) {
        try {
            if (srcUrl.contains(host_URL)) {
                return 1;
            }
            if (RegexUtils.isIP(srcUrl)) {
                return -1;
            }
            if (srcUrl.contains("ultracreditosmx.com")) {
                return -1;
            }
            if (srcUrl.contains("www.baidu.com")) {
                return -1;
            }
        } catch (Exception e) {
        }

        return 0;
    }

//    public static final String host = "https://www.glbyeastbay.com/eastbay";

    public Map<String, String> commMap() {
        Map<String, String> comm = new HashMap<>();
        comm.put(mFieldMap.get("appssid"), appssid);
        comm.put(mFieldMap.get("client-id1"), appssid);
        comm.put(mFieldMap.get("client-id2"), appssid);

        if (loginUser != null) {
            comm.put(mFieldMap.get("token"), loginUser.getToken());
            comm.put(mFieldMap.get("currentUserId"), loginUser.getUserId());
            comm.put(mFieldMap.get("userId"), loginUser.getUserId());
        }
        return comm;
    }

    public Map<String, Object> start() {
        LogUtils.logJson("=============start=================");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 50, 4, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.CallerRunsPolicy());
        int poolLength = 4;//需要用的线程个数
        CountDownLatch countDownLatch = new CountDownLatch(poolLength);


        Map<String, Object> root = new HashMap<>();
        getVerifCode();
        if (loginUser != null) {
//            uploadImage();

            threadPoolExecutor.execute(new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        getPayChannelList();
                        Map<String, Object> stringStorage = getImageList();
                        Jentity getAppImageList = new Jentity("getAppImageList", stringStorage, stringStorage.isEmpty() ? 0 : 1);
                        root.put("getAppImageList", getAppImageList);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // 任务个数 - 1, 直至为0时唤醒await()
                    countDownLatch.countDown();
                }
            }));


            threadPoolExecutor.execute(new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Map<String, Object> vip = getVip();
                        Jentity vipProducts = new Jentity("vipProducts", vip, vip.isEmpty() ? 0 : 1);
                        root.put("vipProducts", vipProducts);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // 任务个数 - 1, 直至为0时唤醒await()
                    countDownLatch.countDown();
                }
            }));


            threadPoolExecutor.execute(new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Map<String, Object> identificationResult = getIdentificationResult();
                        Jentity getIdentificationResult = new Jentity("getIdentificationResult", identificationResult, identificationResult.isEmpty() ? 0 : 1);
                        root.put("getIdentificationResult", getIdentificationResult);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // 任务个数 - 1, 直至为0时唤醒await()
                    countDownLatch.countDown();
                }
            }));


            threadPoolExecutor.execute(new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Map<String, Object> queryProduct = queryProduct();
                        Jentity queryProductResult = new Jentity("preSubmitOrder", queryProduct, queryProduct.isEmpty() ? 0 : 1);
                        root.put("preSubmitOrder", queryProductResult);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // 任务个数 - 1, 直至为0时唤醒await()
                    countDownLatch.countDown();
                }
            }));
            LogUtils.logJson("=============end=================");
            try {
                // 让当前线程处于阻塞状态，直到锁存器计数为零
                countDownLatch.await();
            } catch (InterruptedException e) {
                throw new NullPointerException(e.getMessage());
            }
        }


        return root;
    }

    //    https://www.glbyeastbay.com/eastbay/friend/forceNicePioneer
    public void getVerifCode() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        mapParam.put(mFieldMap.get("phoneNo"), currentPhoneNo);
        Map<String, String> header = commMap();
//        header.put("v-flag", "true");
//        header.put("apiName", AESUtil.encrypt("/login/getVerifCode", "0121170eedf910c65bf10b2cf5820202"));
//        LogUtils.logJson(commMap());
        String respStr = OkHttpUtils.postForm(host + mPathMap.get("/login/getVerifCode"), header, mapParam);
        JSONObject jsonObject = JSON.parseObject(respStr);
        JSONObject data = jsonObject.getJSONObject(mFieldMap.get("data"));
        LogUtils.logJson(respStr);
        String smsCode = "1234";

        try {
            if (data != null && !TextUtils.isEmpty(data.getString(mFieldMap.get("smsCode")))) {
                smsCode = data.getString(mFieldMap.get("smsCode"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        loginForSms(currentPhoneNo, smsCode);

    }

    private void loginForSms(String phone, String smsCode) {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        mapParam.put(mFieldMap.get("phoneNo"), phone);
        mapParam.put(mFieldMap.get("smsCode"), smsCode);
        Map<String, String> header = commMap();
        String respStr = OkHttpUtils.postForm(host + mPathMap.get("/login/loginForSms"), header, mapParam);
        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString(mFieldMap.get("code"));
        if ("1000".equals(code)) {
            JSONObject data = jsonObject.getJSONObject(mFieldMap.get("data"));
            String userId = data.getString(mFieldMap.get("userId"));
            String token = data.getString(mFieldMap.get("token"));
            LoginUser user = new LoginUser();
            user.setUserId(userId);
            user.setToken(token);
            loginUser = user;
        }
    }

    public Map<String, Object> getImageList() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        mapParam.put(mFieldMap.get("type"), "03");
        Map<String, String> header = commMap();
//        header.put("v-flag", "true");
//        header.put("apiName", AESUtil.encrypt("/anon/getAppImageList", "0121170eedf910c65bf10b2cf5820202"));
        String respStr = OkHttpUtils.postForm(host + mPathMap.get("/anon/getAppImageList"), header, mapParam);
        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);

        String code = jsonObject.getString(mFieldMap.get("code"));
        JSONArray appArr = jsonObject.getJSONArray(mFieldMap.get("data"));
        List<Jentity> appList = new ArrayList<>();
        int appAllState = 1;
        if ("1000".equals(code)) {

            if (jsonObject.containsKey("data")) {
                for (int i = 0; i < appArr.size(); i++) {
                    int appListState = 1;
                    Map<String, Object> app = new HashMap<>();

                    JSONObject item = appArr.getJSONObject(i);
                    String key = "url";
                    String value = item.getString(mFieldMap.get("url"));
                    int i1 = checkRealPath(value);
                    app.put(key, new Jentity(key, value, i1));
                    if (i1 == 0) {
                        appListState = 0;
                    }
                    if (appListState == 0) {
                        appAllState = appListState;
                    }
                    appList.add(new Jentity(key, app, appListState));
                }
            } else {
                appAllState = 0;
            }
        } else {
            appAllState = 0;
        }
        Map<String, Object> result = new HashMap<>();
        result.put("value", appList);
        result.put("state", appAllState);
        result.put("msg", "");
        return result;
    }

    public Map<String, Object> getVip() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
//        header.put("v-flag", "true");
//        header.put("apiName", AESUtil.encrypt("/cust/vipProducts", "0121170eedf910c65bf10b2cf5820202"));
        String respStr = OkHttpUtils.postForm(host + mPathMap.get("/cust/vipProducts"), header, mapParam);
        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);


        String code = jsonObject.getString(mFieldMap.get("code"));
        JSONObject jsonData = jsonObject.getJSONObject(mFieldMap.get("data"));

        int appAllState = 1;
        String msg = "no message";
        Map<String, Object> vipResult = new HashMap<>();

        if ("1000".equals(code)) {

            LogUtils.logJson(jsonData);
            String vipProductImageUrl = jsonData.getString(mFieldMap.get("vipProductImageUrl"));
//            List<Jentity> appList = new ArrayList<>();
//            appList.add(new Jentity(item.getString("app_name"), app, appListState));
            int viewState = checkRealPath(vipProductImageUrl);
            if (viewState == 0) {
                appAllState = 0;
            }
            vipResult.put("vipProductImageUrl", new Jentity("vipProductImageUrl", vipProductImageUrl, viewState));
            int vipProductListStats = 1;
            String vipProductListMsg = "";
            List<Jentity> vipProductList = new ArrayList<>();

            if (jsonData.containsKey(mFieldMap.get("vipProductList"))) {

                JSONArray sensor_list = jsonData.getJSONArray(mFieldMap.get("vipProductList"));
                int vipCCStats = 1;

                if (sensor_list != null && sensor_list.size() > 0) {
                    for (int i = 0; i < sensor_list.size(); i++) {
                        Map<String, Object> sensor_0 = new HashMap<>();
                        JSONObject sensor = sensor_list.getJSONObject(i);
                        String skey = "";
                        skey = mFieldMap.get("centralIndeedGovernment");
                        String value = sensor.getString(skey);
                        int typeStats = checkRealPath(value);
                        sensor_0.put(skey, new Jentity(skey, value, typeStats));
                        if (typeStats != 1) {
                            vipCCStats = 0;
                            appAllState = 0;
                        }
                        vipProductList.add(new Jentity(value, sensor_0, vipCCStats));
                    }
                } else {
                    vipCCStats = 0;
                }

                if (vipProductList.size() == 0) {
                    vipCCStats = 0;

                }

                Jentity jentity = new Jentity("vipProductList", vipProductList, vipCCStats);

                vipResult.put("vipProductList", jentity);
            } else {
                appAllState = 0;
                Jentity jentity = new Jentity("vipProductList", vipProductList, 0);
                vipResult.put("vipProductList", jentity);
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("value", vipResult);
        result.put("state", appAllState);
        result.put("msg", msg);
        return result;
    }

    public void uploadImage() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        Map<String, File> fileMap = new HashMap<>();
        fileMap.put(mFieldMap.get("frontImage"), new File("/Users/huhuijie/Documents/GitHub/springboot_apkparse/json/face.jpg"));
        mapParam.put(mFieldMap.get("type"), "02");
//        String respStr = OkHttpUtils.postFormWithImge(host + mPathMap.get("/cust/identification"), fileMap, mapParam, header);
        String respStr = OkHttpUtils.postFormWithImge(host + mPathMap.get("/cust/saveImage"), fileMap, mapParam, header);

        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString(mFieldMap.get("code"));
        if ("1000".equals(code)) {

        }
    }

    public Map<String, Object> getIdentificationResult() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();

        String respStr = OkHttpUtils.postForm(host + mPathMap.get("/cust/getIdentificationResult"), header, mapParam);
        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString(mFieldMap.get("code"));
        JSONObject jsonData = jsonObject.getJSONObject(mFieldMap.get("data"));

        Map<String, Object> indentificationResult = new HashMap<>();
        String afaceUrl = jsonData.getString(mFieldMap.get("afaceUrl"));
        String cardFrontUrl = jsonData.getString(mFieldMap.get("cardFrontUrl"));
        String cardBackUrl = jsonData.getString(mFieldMap.get("cardBackUrl"));
        String bankCardImagUrl = jsonData.getString(mFieldMap.get("bankCardImagUrl"));
        int stats = 1;
        if ("1000".equals(code)) {

            if (!TextUtils.isEmpty(afaceUrl)) {
                int i = checkRealPath(afaceUrl);
                if (i == 0) {
                    stats = 0;
                }
                indentificationResult.put("afaceUrl", new Jentity("afaceUrl", afaceUrl, i));
            } else {
                stats = 0;
            }
            if (!TextUtils.isEmpty(cardFrontUrl)) {
                int i = checkRealPath(cardFrontUrl);
                if (i == 0) {
                    stats = 0;
                }
                indentificationResult.put("cardFrontUrl", new Jentity("cardFrontUrl", cardFrontUrl, i));
            }
            if (!TextUtils.isEmpty(cardBackUrl)) {
                int i = checkRealPath(cardBackUrl);
                if (i == 0) {
                    stats = 0;
                }
                indentificationResult.put("cardBackUrl", new Jentity("cardBackUrl", cardBackUrl, i));
            }
            if (!TextUtils.isEmpty(bankCardImagUrl)) {
                int i = checkRealPath(bankCardImagUrl);
                if (i == 0) {
                    stats = 0;
                }
                indentificationResult.put("bankCardImagUrl", new Jentity("bankCardImagUrl", bankCardImagUrl, i));
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("value", indentificationResult);
        result.put("state", stats);
        result.put("msg", "");
        return result;
    }


    public Map<String, Object> queryProduct() {
        Map<String, Object> preSubmitOrderResult = new HashMap<>();
        int appAllState = 1;
        List<Jentity> appList = new ArrayList<>();

        try {
            Map<String, String> mapParam = new HashMap<>();
            mapParam.putAll(commMap());
            Map<String, String> header = commMap();
            String respStr = OkHttpUtils.postForm(host + mPathMap.get("/product/queryProduct"), header, mapParam);
            LogUtils.logJson(respStr);
            JSONObject jsonObject = JSON.parseObject(respStr);
            JSONObject jsonData = jsonObject.getJSONObject(mFieldMap.get("data"));


            String productId = jsonData.getString(mFieldMap.get("productId"));
            JSONArray prodetailList = jsonData.getJSONArray(mFieldMap.get("prodetailList"));
            JSONObject item = prodetailList.getJSONObject(0);
            mapParam.put(mFieldMap.get("applyAmount"), item.getString(mFieldMap.get("maxCreditAmount")));
            mapParam.put(mFieldMap.get("detailId"), item.getString(mFieldMap.get("detailId")));
            mapParam.put(mFieldMap.get("productId"), productId);


            respStr = OkHttpUtils.postForm(host + mPathMap.get("/order/preSubmitOrder"), header, mapParam);
            jsonObject = JSON.parseObject(respStr);
            jsonData = jsonObject.getJSONObject(mFieldMap.get("data"));
            LogUtils.logJson(respStr);
            LogUtils.logJson("=====================0");

            LogUtils.logJson(jsonData);

            if (jsonData.containsKey(mFieldMap.get("contractList"))) {


                JSONArray contractList = jsonData.getJSONArray(mFieldMap.get("contractList"));

                for (int i = 0; i < contractList.size(); i++) {
                    int appListState = 1;
                    JSONObject item1 = contractList.getJSONObject(i);
                    String key = "url";
                    Map<String, Object> app = new HashMap<>();
                    String value = item1.getString(mFieldMap.get("url"));
                    int i1 = checkRealPath(value);
                    app.put(key, new Jentity(key, value, i1));
                    if (i1 == 0) {
                        appListState = 0;
                    }
                    if (appListState == 0) {
                        appAllState = appListState;
                    }
                    appList.add(new Jentity(key, app, appListState));

                }
                LogUtils.logJson("=====================3");

            } else {
                appAllState = 0;
            }
        } catch (Exception e) {
            appAllState = 0;
            LogUtils.logJson("=====================5");

            e.printStackTrace();
        }
        Map<String, Object> result = new HashMap<>();
        result.put("value", appList);
        result.put("state", appAllState);
        result.put("msg", "");
        return result;
    }


    public void getPayChannelList() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();

        String respStr = OkHttpUtils.postForm(host + mPathMap.get("/pay/getPayChannelList"), header, mapParam);
        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);

        String code = jsonObject.getString(mFieldMap.get("code"));
        JSONArray appArr = jsonObject.getJSONArray(mFieldMap.get("data"));
        if (appArr.size() > 0) {
            JSONObject firstObject = appArr.getJSONObject(0);
            mapParam.put(mFieldMap.get("payChannel"), firstObject.getString(mFieldMap.get("payChannel")));
            mapParam.put(mFieldMap.get("payType"), "00");
            respStr = OkHttpUtils.postForm(host + mPathMap.get("/pay/getPayInfo"), header, mapParam);
            LogUtils.logJson(respStr);

        }

    }


}
