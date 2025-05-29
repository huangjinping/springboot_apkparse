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

public class InxServerSpiderLocal {
    private final JsonFilter mJsonFilter;
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


    public InxServerSpiderLocal(String json, String fileName, String appssid, String domainname, String phoneNo) {
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
        comm.put(mFieldMap.get("appSsid"), appssid);
        comm.put(mFieldMap.get("versionName"), "1.5.8");
        comm.put(mFieldMap.get("versionCode"), "158");
        comm.put(mFieldMap.get("client-id"), appssid);
        comm.put(mFieldMap.get("language"), "es");


        String gaid = "eb505a1a-14a0-4771-8450-9d6867319872";
        comm.put(mFieldMap.get("googleGaid"), gaid);
        comm.put(mFieldMap.get("gaid"), gaid);
        comm.put(mFieldMap.get("imei"), gaid);

        if (loginUser != null) {
            comm.put(mFieldMap.get("token"), loginUser.getToken());
            comm.put(mFieldMap.get("currentUserId"), loginUser.getUserId());
            comm.put(mFieldMap.get("userId"), loginUser.getUserId());
            comm.put(mFieldMap.get("custInfoId"), loginUser.getUserId());
        }
        return comm;
    }


    public Map<String, Object> start() {
        LogUtils.logJson("=============start=================");

//        getTableShardingValue("c01fdd4b-0731-44d9-9965-87c17076a7d2");


        Map<String, Object> root = new HashMap<>();
        getVerifCode();
//        getTimeout();
        if (loginUser != null) {
//            checkFavorableComment();
            getAppConfig();
//            getDocumentssmsMatcher();
//            index();
//            appIndex();
//            appIndexInstallment();
//            uploadImage();

//            orderListForMulAppInstallment();

//            saveBasicCustInfo();
//            addBank();
////            custInfoBasicQuery();
//            msgFeatureV3();
//
//            queryProductInstallment();
//            saveCustInfo();
//            custInfoQuery();
//            msgFeatureV5();
//            getAppConfig();
//            geth5Page();
//            getPayChannelList();
//            Map<String, Object> stringStorage = getImageList();
//            Jentity getAppImageList = new Jentity("getAppImageList", stringStorage, stringStorage.isEmpty() ? 0 : 1);
//            root.put("getAppImageList", getAppImageList);
//            Map<String, Object> vip = getVip();
//            Jentity vipProducts = new Jentity("vipProducts", vip, vip.isEmpty() ? 0 : 1);
//            root.put("vipProducts", vipProducts);
//            getAppSetting();
//            Map<String, Object> identificationResult = getIdentificationResult();
//            Jentity getIdentificationResult = new Jentity("getIdentificationResult", identificationResult, identificationResult.isEmpty() ? 0 : 1);
//            root.put("getIdentificationResult", getIdentificationResult);
//            getBankList();
//            getAppValueList();
//            getNewRealTerm();
//            Map<String, Object> queryProduct = queryProduct();
//            Jentity queryProductResult = new Jentity("preSubmitOrder", queryProduct, queryProduct.isEmpty() ? 0 : 1);
//            root.put("preSubmitOrder", queryProductResult);
//            uploadRiskPoint();
//            uploadOperation();
//            addBank();
//            v3indexForMulApp();
//            getAppInfo();
//            getSysSetting();
//            counponList();

//            checkAddressJsonFileExist();
//            getAppInfoUserId();
//            getUrlForApp();
//            indexForMulAppV2();
//            indexForMulAppInstallmentV2();
//            orderListForMulAppInstallment();

//            userAppList();
        }
        return root;
    }


    public void userAppList() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        Map<String, File> fileMap = new HashMap<>();

        String respStr = OkHttpUtils.postFormWithImge(host + mPathMap.get("/cust/userAppList"), fileMap, mapParam, header);
        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString(mFieldMap.get("code"));
        if ("1000".equals(code)) {
        }
    }


    public void orderListForMulAppInstallment() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        Map<String, File> fileMap = new HashMap<>();

        String respStr = OkHttpUtils.postFormWithImge(host + mPathMap.get("/installment/orderListForMulAppInstallment"), fileMap, mapParam, header);
        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString(mFieldMap.get("code"));
        if ("1000".equals(code)) {
        }
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


    private void getTableShardingValue(String gaid) {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.put("tableShardingValue", gaid);
        Map<String, String> header = new HashMap<>();

        String respStr = OkHttpUtils.postForm("https://www.mxholacash.com/api/web/test/getTableShardingValue", header, mapParam);
        LogUtils.logJson(respStr);

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
        mapParam.put(mFieldMap.get("imageType"), "03");
//        mapParam.put("blueGreyAmerica", "03");

//        mapParam.put("tinyElectricityRace", "03");

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


    public void saveBasicCustInfo() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        Map<String, File> fileMap = new HashMap<>();


//        mapParam.put(mFieldMap.get("pageType"), "1");
//        mapParam.put(mFieldMap.get("names"), "ndkkdkd");
//        mapParam.put(mFieldMap.get("surnames"), "gjjjjs");
//        mapParam.put(mFieldMap.get("sex"), "0");
//        mapParam.put(mFieldMap.get("residentialProvince"), "Eastern Cape");
//        mapParam.put(mFieldMap.get("residentialProvinceCode"), "3");
//        mapParam.put(mFieldMap.get("residentialCity"), "Buffalo City");
//        mapParam.put(mFieldMap.get("residentialCityCode"), "301");
//        mapParam.put(mFieldMap.get("residentialDistrict"), "Alfred Nzo");
//        mapParam.put(mFieldMap.get("residentialDistrict"), "Alfred Nzo");
//        mapParam.put(mFieldMap.get("residentialDistrictCode"), "30105");
//        mapParam.put(mFieldMap.get("thirdName"), "Matatiele LM");
//        mapParam.put(mFieldMap.get("email"), "222222j@hotmail.com");
//        mapParam.put(mFieldMap.get("frequencyOfPay"), "1");
//        mapParam.put(mFieldMap.get("payday"), "26");
//        mapParam.put(mFieldMap.get("incomeLevel"), "0");
//        mapParam.put(mFieldMap.get("position"), "0");


//        mapParam.put(mFieldMap.get("pageType"), "3");
//        mapParam.put(mFieldMap.get("relationship"), "1");
//        mapParam.put(mFieldMap.get("name"), "shhiwi");
//        mapParam.put(mFieldMap.get("phoneNumber"), "183555888");
//        mapParam.put(mFieldMap.get("relationshipSec"), "3");
//        mapParam.put(mFieldMap.get("nameSec"), "shhiwiSec");
//        mapParam.put(mFieldMap.get("phoneNumberSec"), "183868689");


        mapParam.put(mFieldMap.get("pageType"), "4");
        mapParam.put(mFieldMap.get("birthDay"), "10-10-2000");
        mapParam.put(mFieldMap.get("curp"), "1234567890");
        mapParam.put(mFieldMap.get("email"), "ahishia@gmail.com");
        mapParam.put(mFieldMap.get("surnames"), "akusge");
        mapParam.put(mFieldMap.get("names"), "asss");

//        mapParam.put(mFieldMap.get("email"), "ahishia@gmail.com");
//        mapParam.put(mFieldMap.get("surnames"), "akusge");
//        mapParam.put(mFieldMap.get("names"), "asss");
//        mapParam.put(mFieldMap.get("birthDay"), "11-11-2000");
//        mapParam.put(mFieldMap.get("sex"), "1");
//        mapParam.put(mFieldMap.get("maritalStatus"), "1");
//        mapParam.put(mFieldMap.get("familySize"), "1");
//        mapParam.put(mFieldMap.get("incomeLevel"), "1");
//        mapParam.put(mFieldMap.get("workType"), "1");
//        mapParam.put(mFieldMap.get("relationship"), "1");
//        mapParam.put(mFieldMap.get("name"), "shhiwi");
//        mapParam.put(mFieldMap.get("curp"), "182909872");

        mapParam.put(mFieldMap.get("relationship"), "1");
        mapParam.put(mFieldMap.get("name"), "shhiwi");
        mapParam.put(mFieldMap.get("phoneNumber"), "182110112");
        mapParam.put(mFieldMap.get("relationshipSec"), "2");
        mapParam.put(mFieldMap.get("nameSec"), "shhiwiSec");
        mapParam.put(mFieldMap.get("phoneNumberSec"), "182110110");


        LogUtils.logJson(mapParam);
//        String respStr = OkHttpUtils.postFormWithImge(host + mPathMap.get("/cust/identification"), fileMap, mapParam, header);
        String respStr = OkHttpUtils.postFormWithImge(host + mPathMap.get("/cust/saveBasicCustInfo"), fileMap, mapParam, header);

        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString(mFieldMap.get("code"));
        if ("1000".equals(code)) {

        }
    }

    public void saveCustInfo() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        Map<String, File> fileMap = new HashMap<>();
//        mapParam.put(mFieldMap.get("email"), "ahis222111a@gmail.com");

//        mapParam.put(mFieldMap.get("surnames"), "akusge");
//        mapParam.put(mFieldMap.get("names"), "asss");

        mapParam.put(mFieldMap.get("surname"), "surname");
        mapParam.put(mFieldMap.get("surnameSec"), "surnameSec");

        mapParam.put(mFieldMap.get("representThird"), "representThird");

        mapParam.put(mFieldMap.get("birthDay"), "11-10-2001");
        mapParam.put(mFieldMap.get("birthday"), "11-10-2000");
        mapParam.put(mFieldMap.get("sex"), "2");
//        mapParam.put(mFieldMap.get("maritalStatus"), "1");
//        mapParam.put(mFieldMap.get("familySize"), "1");
//        mapParam.put(mFieldMap.get("incomeLevel"), "1");
//        mapParam.put(mFieldMap.get("workType"), "1");
//        mapParam.put(mFieldMap.get("curp"), "182909872");
        mapParam.put(mFieldMap.get("idcardNum"), "35789087");

//        mapParam.put(mFieldMap.get("name"), "shhiwi");
//        mapParam.put(mFieldMap.get("relationship"), "1");
//        mapParam.put(mFieldMap.get("phoneNumber"), "1821829112");
//        mapParam.put(mFieldMap.get("relationshipSec"), "2");
//        mapParam.put(mFieldMap.get("nameSec"), "shhiwiSec");
//        mapParam.put(mFieldMap.get("phoneNumberSec"), "1822189922");

        LogUtils.logJson(mapParam);
//        String respStr = OkHttpUtils.postFormWithImge(host + mPathMap.get("/cust/identification"), fileMap, mapParam, header);
        String respStr = OkHttpUtils.postFormWithImge(host + mPathMap.get("/cust/saveCustInfo"), fileMap, mapParam, header);

        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString(mFieldMap.get("code"));
        if ("1000".equals(code)) {

        }
    }

    public void indexForMulAppV2() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        Map<String, File> fileMap = new HashMap<>();

        String respStr = OkHttpUtils.postFormWithImge(host + mPathMap.get("/mul/v3/indexForMulAppV2"), fileMap, mapParam, header);
        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString(mFieldMap.get("code"));
        if ("1000".equals(code)) {
        }
    }

    public void indexForMulAppInstallmentV2() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        Map<String, File> fileMap = new HashMap<>();

        String respStr = OkHttpUtils.postFormWithImge(host + mPathMap.get("/installment/indexForMulAppInstallmentV2"), fileMap, mapParam, header);
        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString(mFieldMap.get("code"));
        if ("1000".equals(code)) {
        }
    }

    public void v3indexForMulApp() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        Map<String, File> fileMap = new HashMap<>();
        mapParam.put(mFieldMap.get("pageType"), "1");

        String respStr = OkHttpUtils.postFormWithImge(host + mPathMap.get("/mul/v3/indexForMulApp"), fileMap, mapParam, header);
        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString(mFieldMap.get("code"));
        if ("1000".equals(code)) {
        }
    }


    public void custInfoBasicQuery() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        Map<String, File> fileMap = new HashMap<>();
        mapParam.put(mFieldMap.get("pageType"), "1");

        String respStr = OkHttpUtils.postFormWithImge(host + mPathMap.get("/cust/custInfoBasicQuery"), fileMap, mapParam, header);
        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString(mFieldMap.get("code"));
        if ("1000".equals(code)) {
        }
    }

    public void custInfoQuery() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        Map<String, File> fileMap = new HashMap<>();
        String respStr = OkHttpUtils.postFormWithImge(host + mPathMap.get("/cust/custInfoQuery"), fileMap, mapParam, header);
        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString(mFieldMap.get("code"));
        if ("1000".equals(code)) {
        }
    }


    public void getNewRealTerm() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        mapParam.put(mFieldMap.get("type"), "incomeLevel");
        mapParam.put("unhappyInformationBestConcert", "en");
        mapParam.put(mFieldMap.get("userId"), "kkkkkkkkkkk");

        String respStr = OkHttpUtils.postForm(host + mPathMap.get("/anon/getAppConfig"), header, mapParam);
        LogUtils.logJson("/anon/getAppConfig");
        LogUtils.logJson(respStr);

        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString(mFieldMap.get("code"));
        if ("1000".equals(code)) {
        }
    }

    public void geth5Page() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        mapParam.put(mFieldMap.get("frontSource"), "5");

        String respStr = OkHttpUtils.postForm(host + mPathMap.get("/anon/csH5Page"), header, mapParam);
        LogUtils.logJson("/anon/csH5Page");
        LogUtils.logJson(respStr);

        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString(mFieldMap.get("code"));
        if ("1000".equals(code)) {
        }
    }


    public void getAppConfig() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
//        mapParam.put(mFieldMap.get("type"), "sourceOfIncome");
        mapParam.put(mFieldMap.get("type"), "collectiontype");

//        mapParam.put(mFieldMap.get("type"), "prettyJeansSoftSki");


//        mapParam.put(mFieldMap.get("type"), "incomeLevel");


//        mapParam.put("aggressiveBillHandsomeConvenientRiddle", "en");
        mapParam.put(mFieldMap.get("language"), "en");

        String respStr = OkHttpUtils.postForm(host + mPathMap.get("/anon/getAppConfig"), header, mapParam);
        LogUtils.logJson("/anon/getAppConfig");
        LogUtils.logJson(respStr);

        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString(mFieldMap.get("code"));
        if ("1000".equals(code)) {
        }
    }


    public void getTimeout() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        mapParam.put(mFieldMap.get("type"), "collectionType");
        mapParam.put("ripeBasketNearbyToothacheFollowingPasser", "es");

        String respStr = OkHttpUtils.postForm("http://127.0.0.1:8092/onMishie", header, mapParam);
        LogUtils.logJson("/anon/getAppConfig");
        LogUtils.logJson(respStr);

        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString(mFieldMap.get("code"));
        if ("1000".equals(code)) {
        }
    }

    public void uploadRiskPoint() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        mapParam.put(mFieldMap.get("optType"), "SERVE_MAJOR_HALL");
        String respStr = OkHttpUtils.postForm(host + mPathMap.get("/anon/uploadRiskPoint"), header, mapParam);
        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString(mFieldMap.get("code"));
        if ("1000".equals(code)) {
        }
    }


    public void checkFavorableComment() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        String respStr = OkHttpUtils.postForm(host + mPathMap.get("/anon/checkFavorableComment"), header, mapParam);
        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString(mFieldMap.get("code"));
        if ("1000".equals(code)) {
        }
    }

    public void uploadOperation() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        mapParam.put(mFieldMap.get("optType"), "REPEAT_PAINFUL_CANDY");
        String respStr = OkHttpUtils.postForm(host + mPathMap.get("/anon/uploadOperation"), header, mapParam);
        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString(mFieldMap.get("code"));
        if ("1000".equals(code)) {
        }
    }


    public void getAppSetting() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        mapParam.put(mFieldMap.get("key"), "difficultTail,pacificCastleJam,lameProblemCanal,unhappySmogMarket,skilledSalaryTibetanPet,musicalCommunistSuffering,localChemistry");
        String respStr = OkHttpUtils.postForm(host + mPathMap.get("/anon/getAppSetting"), header, mapParam);
        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString(mFieldMap.get("code"));
        if ("1000".equals(code)) {
        }
    }

    public void index() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        String respStr = OkHttpUtils.postForm(host + mPathMap.get("/anon/index"), header, mapParam);
        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString(mFieldMap.get("code"));
        if ("1000".equals(code)) {
            JSONObject jsonData = jsonObject.getJSONObject(mFieldMap.get("data"));
            String orderId = jsonData.getString(mFieldMap.get("orderId"));
            if (!TextUtils.isEmpty(orderId)) {
//                mpRepayUrl(orderId, "00");
                getDocuments(orderId);
            }
        }
    }

    public void appIndexInstallment() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        String respStr = OkHttpUtils.postForm(host + mPathMap.get("/anon/appIndexInstallment"), header, mapParam);
        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString(mFieldMap.get("code"));
        if ("1000".equals(code)) {
            JSONObject jsonData = jsonObject.getJSONObject(mFieldMap.get("data"));
            String orderId = jsonData.getString(mFieldMap.get("orderId"));
            if (!TextUtils.isEmpty(orderId)) {
//                mpRepayUrl(orderId, "00");
                getDocuments(orderId);
            }
        }
    }

    public void appIndex() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        String respStr = OkHttpUtils.postForm(host + mPathMap.get("/anon/appIndex"), header, mapParam);
        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString(mFieldMap.get("code"));
        if ("1000".equals(code)) {
            JSONObject jsonData = jsonObject.getJSONObject(mFieldMap.get("data"));
            String orderId = jsonData.getString(mFieldMap.get("orderId"));
            if (!TextUtils.isEmpty(orderId)) {
//                mpRepayUrl(orderId, "00");
                getDocuments(orderId);
            }
        }
    }


    public void getDocumentssmsMatcher() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        mapParam.put(mFieldMap.get("configKey"), "smsMatcher");
        Map<String, String> header = commMap();
        String respStr = OkHttpUtils.postForm(host + mPathMap.get("/sysConfig/getDocuments"), header, mapParam);
        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString(mFieldMap.get("code"));
        if ("1000".equals(code)) {

        }
    }

    public void getDocuments(String orderId) {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        mapParam.put(mFieldMap.get("orderId"), orderId);
        mapParam.put(mFieldMap.get("configKey"), "documents_1");
        mapParam.put(mFieldMap.get("orderType"), "0");

        Map<String, String> header = commMap();
        String respStr = OkHttpUtils.postForm(host + mPathMap.get("/sysConfig/getDocuments"), header, mapParam);
        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString(mFieldMap.get("code"));
        if ("1000".equals(code)) {

        }
    }

    public void mpRepayUrl(String orderId, String payType) {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        LogUtils.log("======app==11==");
        mapParam.put(mFieldMap.get("orderId"), orderId);
        mapParam.put(mFieldMap.get("payType"), payType);
        mapParam.put(mFieldMap.get("payMethod"), "1");
        LogUtils.log(mapParam);
        String respStr = OkHttpUtils.postForm(host + mPathMap.get("/order/mpRepayUrl"), header, mapParam);
        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString(mFieldMap.get("code"));
    }


    public void uploadImage() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        Map<String, File> fileMap = new HashMap<>();
        fileMap.put(mFieldMap.get("frontImage"), new File("/Users/huhuijie/Documents/GitHub/springboot_apkparse/json/test.jpg"));
        mapParam.put(mFieldMap.get("type"), "02");
        String respStr = OkHttpUtils.postFormWithImge(host + mPathMap.get("/cust/saveImage"), fileMap, mapParam, header);

        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString(mFieldMap.get("code"));
        if ("1000".equals(code)) {
            JSONObject jsonData = jsonObject.getJSONObject(mFieldMap.get("data"));
        }
    }


    public Map<String, Object> getIdentificationResult() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        mapParam.put(mFieldMap.get("pageType"), "4");
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
                loadFile(afaceUrl);
                indentificationResult.put("afaceUrl", new Jentity("afaceUrl", afaceUrl, i));
            } else {
                stats = 0;
            }
            if (!TextUtils.isEmpty(cardFrontUrl)) {
                int i = checkRealPath(cardFrontUrl);
                if (i == 0) {
                    stats = 0;
                }
                loadFile(cardFrontUrl);

                indentificationResult.put("cardFrontUrl", new Jentity("cardFrontUrl", cardFrontUrl, i));
            }
            if (!TextUtils.isEmpty(cardBackUrl)) {
                int i = checkRealPath(cardBackUrl);
                if (i == 0) {
                    stats = 0;
                }
                loadFile(cardBackUrl);

                indentificationResult.put("cardBackUrl", new Jentity("cardBackUrl", cardBackUrl, i));
            }
            if (!TextUtils.isEmpty(bankCardImagUrl)) {
                int i = checkRealPath(bankCardImagUrl);
                if (i == 0) {
                    stats = 0;
                }
                loadFile(bankCardImagUrl);
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

            mapParam.put(mFieldMap.get("type"), "00");
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
            mapParam.put(mFieldMap.get("productId"), productId);


//            respStr = OkHttpUtils.postForm(host + mPathMap.get("/product/preAmount"), header, mapParam);
//            LogUtils.logJson(respStr);
//
//
//            respStr = OkHttpUtils.postForm(host + mPathMap.get("/order/preSubmitOrder"), header, mapParam);
//            jsonObject = JSON.parseObject(respStr);
//            jsonData = jsonObject.getJSONObject(mFieldMap.get("data"));
//            LogUtils.logJson(respStr);
//            LogUtils.logJson("=====================0");
//
//            LogUtils.logJson(jsonData);
//
//            if (jsonData != null && jsonData.containsKey(mFieldMap.get("contractList"))) {
//
//                LogUtils.logJson("=====================1");
//
//                JSONArray contractList = jsonData.getJSONArray(mFieldMap.get("contractList"));
//                LogUtils.logJson("=====================2");
//
//                for (int i = 0; i < contractList.size(); i++) {
//                    int appListState = 1;
//                    JSONObject item1 = contractList.getJSONObject(i);
//                    String key = "url";
//                    Map<String, Object> app = new HashMap<>();
//                    String value = item1.getString(mFieldMap.get("url"));
//                    int i1 = checkRealPath(value);
//                    app.put(key, new Jentity(key, value, i1));
//                    if (i1 == 0) {
//                        appListState = 0;
//                    }
//                    if (appListState == 0) {
//                        appAllState = appListState;
//                    }
//                    appList.add(new Jentity(key, app, appListState));
//
//                }
//            } else {
//                appAllState = 0;
//            }
        } catch (Exception e) {
            appAllState = 0;
            e.printStackTrace();
        }
        Map<String, Object> result = new HashMap<>();
        result.put("value", appList);
        result.put("state", appAllState);
        result.put("msg", "");
        return result;
    }


    public void addBank() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        mapParam.put(mFieldMap.get("bankAccountNumber"), "82918372");
        mapParam.put(mFieldMap.get("collectionType"), "2");

        LogUtils.log("getAppConfig=============" + mPathMap.get("/cust/addBank"));
        String respStr = OkHttpUtils.postForm(host + mPathMap.get("/cust/addBank"), header, mapParam);
        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);
        String code = jsonObject.getString(mFieldMap.get("code"));
        if ("1000".equals(code)) {
        }

    }

    public void getAppValueList() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());

        Map<String, String> header = commMap();

        String respStr = OkHttpUtils.postForm(host + mPathMap.get("/anon/getAppValueList"), header, mapParam);

        LogUtils.logJson(respStr);
//        JSONObject jsonObject = JSON.parseObject(respStr);
//
//        String code = jsonObject.getString(mFieldMap.get("code"));
//        JSONArray appArr = jsonObject.getJSONArray(mFieldMap.get("data"));
//        if (appArr.size() > 0) {
//            JSONObject firstObject = appArr.getJSONObject(0);
//            mapParam.put(mFieldMap.get("payChannel"), firstObject.getString(mFieldMap.get("payChannel")));
//            mapParam.put(mFieldMap.get("payType"), "00");
//            respStr = OkHttpUtils.postForm(host + mPathMap.get("/pay/getPayInfo"), header, mapParam);
//            LogUtils.logJson(respStr);
//
//        }

    }


    public void getSysSetting() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.put("key", "crispKey");
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        String respStr = OkHttpUtils.postForm(host + mPathMap.get("/anon/getSysSetting"), header, mapParam);
        LogUtils.logJson(respStr);
    }


    public void getAppInfoUserId() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        String respStr = OkHttpUtils.postForm(host + mPathMap.get("/order/getAppInfoUserId"), header, mapParam);
        LogUtils.logJson(respStr);
    }

    public void getUrlForApp() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        String respStr = OkHttpUtils.postForm(host + mPathMap.get("/anon/apk/getUrlForApp"), header, mapParam);
        LogUtils.logJson(respStr);
    }

    public void txlFeature() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        String respStr = OkHttpUtils.postForm(host + mPathMap.get("/feature/txlFeature"), header, mapParam);
        LogUtils.logJson(respStr);
    }

    public void checkAddressJsonFileExist() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        String respStr = OkHttpUtils.postForm(host + mPathMap.get("/feature/checkAddressJsonFileExist"), header, mapParam);
        LogUtils.logJson(respStr);
    }

    public void getAppInfo() {
        Map<String, String> mapParam = new HashMap<>();

        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        String respStr = OkHttpUtils.postForm(host + mPathMap.get("/anon/getAppInfo"), header, mapParam);
        LogUtils.logJson(respStr);
    }

    public void getBankList() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();

        String respStr = OkHttpUtils.postForm(host + mPathMap.get("/cust/bankCardList"), header, mapParam);
        LogUtils.logJson(respStr);
//        JSONObject jsonObject = JSON.parseObject(respStr);
//
//        String code = jsonObject.getString(mFieldMap.get("code"));
//        JSONArray appArr = jsonObject.getJSONArray(mFieldMap.get("data"));
//        if (appArr.size() > 0) {
//            JSONObject firstObject = appArr.getJSONObject(0);
//            mapParam.put(mFieldMap.get("payChannel"), firstObject.getString(mFieldMap.get("payChannel")));
//            mapParam.put(mFieldMap.get("payType"), "00");
//            respStr = OkHttpUtils.postForm(host + mPathMap.get("/pay/getPayInfo"), header, mapParam);
//            LogUtils.logJson(respStr);
//
//        }
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

    public void counponList() {
        Map<String, String> mapParam = new HashMap<>();
        mapParam.putAll(commMap());
        Map<String, String> header = commMap();
        mapParam.put(mFieldMap.get("listType"), "1");
        mapParam.put(mFieldMap.get("couponStatus"), "1");
        String respStr = OkHttpUtils.postForm(host + mPathMap.get("/product/counponList"), header, mapParam);
        LogUtils.logJson(respStr);
        JSONObject jsonObject = JSON.parseObject(respStr);

        String code = jsonObject.getString(mFieldMap.get("code"));
        JSONArray appArr = jsonObject.getJSONArray(mFieldMap.get("data"));
    }


    public void msgFeatureV3ZIP() {
        try {
            Map<String, String> mapParam = new HashMap<>();
            mapParam.putAll(commMap());
            Map<String, String> header = commMap();
            Map<String, File> fileMap = new HashMap<>();
//            String textByPath = FileUtils.getTextByPath("/Users/huhuijie/Documents/GitHub/springboot_apkparse/json/204.txt");
//            String textByPath = FileUtils.getTextByPath("/Users/huhuijie/Documents/GitHub/springboot_apkparse/json/182before.txt");

            String textByPath = FileUtils.getTextByPath("/Users/huhuijie/Documents/GitHub/springboot_apkparse/json/204.txt");
//            String textByPath = FileUtils.getTextByPath("/Users/huhuijie/Documents/GitHub/springboot_apkparse/json/9003.json");


            textByPath = GzipUtil.compress(textByPath);
//            LogUtils.logJson(textByPath);
//            textByPath = GzipUtil.compress(textByPath);
//            LogUtils.logJson("---------compress-----------------");
//
//            FileUtils.witermessage(textByPath, "/Users/huhuijie/Documents/GitHub/springboot_apkparse/json", appssid + "_compress.json");
//            LogUtils.logJson("---------compress----1-------------");
//
//            LogUtils.logJson(textByPath);
////            textByPath = AESUtil.encrypt(textByPath, "c8dabcd349f334a269b183837deb43f6");//9002
//            textByPath = AESUtil.encrypt(textByPath, "eb5f389969d354350e3342b8d7a421b0");//9003
//
//            LogUtils.logJson("---------encrypt-----------------");
//            FileUtils.witermessage(textByPath, "/Users/huhuijie/Documents/GitHub/springboot_apkparse/json", appssid + "_AES.json");

//            textByPath = AESUtil.encrypt(textByPath, "c17f62eb9b624c86a777e25998683baa");//151
//            textByPath = AESUtil.encrypt(textByPath, "cd417c070216a9d3da10f1d03b9446bd");//151
//            textByPath = AESUtil.encrypt(textByPath, "eb5f389969d354350e3342b8d7a421b0");//9003

            LogUtils.logJson(textByPath);
            String url = "http://127.0.0.1:8092/msgFeatureV3";

            url = "http://10.1.2.8:8092/msgFeatureV3";
            String respStr = OkHttpUtils.postJson(url, textByPath, header);
            LogUtils.logJson(respStr);
            JSONObject jsonObject = JSON.parseObject(respStr);
            String code = jsonObject.getString(mFieldMap.get("code"));
            if ("1000".equals(code)) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void msgFeatureV3() {
        try {
            Map<String, String> mapParam = new HashMap<>();
            mapParam.putAll(commMap());
            Map<String, String> header = commMap();
//            header.put(mFieldMap.get("encyptFlag"), "1");
            header.put("nuclearFarSomeCredit", "1");

            LogUtils.logJson("---------encyptFlag-----------------" + mFieldMap.get("encyptFlag"));
            Map<String, File> fileMap = new HashMap<>();
//            String textByPath = FileUtils.getTextByPath("/Users/huhuijie/Documents/GitHub/springboot_apkparse/json/204.txt");
//            String textByPath = FileUtils.getTextByPath("/Users/huhuijie/Documents/GitHub/springboot_apkparse/json/182before.txt");

//            String textByPath = FileUtils.getTextByPath("/Users/huhuijie/Documents/GitHub/springboot_apkparse/json/9002.json");
//            String textByPath = FileUtils.getTextByPath("/Users/huhuijie/Documents/GitHub/springboot_apkparse/json/9003.json");
            String textByPath = FileUtils.getTextByPath("/Users/huhuijie/Documents/GitHub/springboot_apkparse/json/481.json");

            LogUtils.logJson(textByPath);
            textByPath = GzipUtil.compress(textByPath);
            LogUtils.logJson("---------compress-----------------");

//            FileUtils.witermessage(textByPath, "/Users/huhuijie/Documents/GitHub/springboot_apkparse/json", appssid + "_compress.json");
            LogUtils.logJson("---------compress----1-------------");

            LogUtils.logJson(textByPath);
//            textByPath = AESUtil.encrypt(textByPath, "c8dabcd349f334a269b183837deb43f6");//9002
//            textByPath = AESUtil.encrypt(textByPath, "eb5f389969d354350e3342b8d7a421b0");//9003
//            textByPath = AESUtil.encrypt(textByPath, "c26706dec62dbb92917e27d81dbe03be");//9003

            LogUtils.logJson("---------encrypt-----------------");
            FileUtils.witermessage(textByPath, "/Users/huhuijie/Documents/GitHub/springboot_apkparse/json", appssid + "_AES.json");

//            textByPath = AESUtil.encrypt(textByPath, "c17f62eb9b624c86a777e25998683baa");//151
//            textByPath = AESUtil.encrypt(textByPath, "cd417c070216a9d3da10f1d03b9446bd");//151
//            textByPath = AESUtil.encrypt(textByPath, "eb5f389969d354350e3342b8d7a421b0");//9003
//            header.put(mFieldMap.get("encyptFlag"),"1");
            LogUtils.logJson(textByPath);
            String respStr = OkHttpUtils.postJson(host + mPathMap.get("/feature/msgFeatureV3"), textByPath, header);
            LogUtils.logJson(respStr);
            JSONObject jsonObject = JSON.parseObject(respStr);
            String code = jsonObject.getString(mFieldMap.get("code"));
            if ("1000".equals(code)) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void msgFeatureV5() {
        try {
            Map<String, String> mapParam = new HashMap<>();
            mapParam.putAll(commMap());
            Map<String, String> header = commMap();
            Map<String, File> fileMap = new HashMap<>();
            String textByPath = FileUtils.getTextByPath("/Users/huhuijie/Documents/GitHub/springboot_apkparse/json/204.txt");
//            String textByPath = FileUtils.getTextByPath("/Users/huhuijie/Documents/GitHub/springboot_apkparse/json/182before.txt");

//            String textByPath = FileUtils.getTextByPath("/Users/huhuijie/Documents/GitHub/springboot_apkparse/json/9002.json");
//            String textByPath = FileUtils.getTextByPath("/Users/huhuijie/Documents/GitHub/springboot_apkparse/json/9003.json");

            LogUtils.logJson(textByPath);
            textByPath = GzipUtil.compress(textByPath);
            LogUtils.logJson(textByPath);
            textByPath = AESUtil.encrypt(textByPath, "cd417c070216a9d3da10f1d03b9446bd");
//            textByPath = AESUtil.encrypt(textByPath, "eb5f389969d354350e3342b8d7a421b0");

            LogUtils.logJson(textByPath);
            String respStr = OkHttpUtils.postJson(host + mPathMap.get("/feature/msgFeatureV5"), textByPath, header);
            LogUtils.logJson(respStr);
            JSONObject jsonObject = JSON.parseObject(respStr);
            String code = jsonObject.getString(mFieldMap.get("code"));
            if ("1000".equals(code)) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadFile(String url) {
        String t_name = TextUtils.createName();
        File file = new File("./tempApp/" + appssid + "_" + t_name + ".jpg");
        OkHttpUtils.downLoad(url, file.getAbsolutePath());

    }


    public Map<String, Object> queryProductInstallment() {
        Map<String, Object> preSubmitOrderResult = new HashMap<>();
        int appAllState = 1;
        List<Jentity> appList = new ArrayList<>();

        try {
            Map<String, String> mapParam = new HashMap<>();
            mapParam.putAll(commMap());
            Map<String, String> header = commMap();
            String respStr = OkHttpUtils.postForm(host + mPathMap.get("/installment/queryProductInstallment"), header, mapParam);
            LogUtils.logJson(respStr);
            JSONObject jsonObject = JSON.parseObject(respStr);
            JSONObject jsonData = jsonObject.getJSONObject(mFieldMap.get("data"));

            String productId = jsonData.getString(mFieldMap.get("productId"));
            JSONArray prodetailList = jsonData.getJSONArray(mFieldMap.get("proDetailList"));
            JSONObject item = prodetailList.getJSONObject(0);
            mapParam.put(mFieldMap.get("applyAmount"), item.getString(mFieldMap.get("maxCreditAmount")));
            mapParam.put(mFieldMap.get("detailId"), item.getString(mFieldMap.get("detailId")));
            mapParam.put(mFieldMap.get("productId"), productId);
            mapParam.put(mFieldMap.get("productId"), productId);
            mapParam.put(mFieldMap.get("orderType"), "1");

            respStr = OkHttpUtils.postForm(host + mPathMap.get("/installment/preAmountInstallment"), header, mapParam);
            LogUtils.logJson(respStr);

            respStr = OkHttpUtils.postForm(host + mPathMap.get("/order/commonContractList"), header, mapParam);
            LogUtils.logJson(respStr);

//
//            respStr = OkHttpUtils.postForm(host + mPathMap.get("/installment/submitOrderInstallment"), header, mapParam);
//            jsonObject = JSON.parseObject(respStr);
//            jsonData = jsonObject.getJSONObject(mFieldMap.get("data"));
//            LogUtils.logJson(respStr);
//            LogUtils.logJson("=====================0");
//
//            LogUtils.logJson(jsonData);
//
//            if (jsonData != null && jsonData.containsKey(mFieldMap.get("contractList"))) {
//
//                LogUtils.logJson("=====================1");
//
//                JSONArray contractList = jsonData.getJSONArray(mFieldMap.get("contractList"));
//                LogUtils.logJson("=====================2");
//
//                for (int i = 0; i < contractList.size(); i++) {
//                    int appListState = 1;
//                    JSONObject item1 = contractList.getJSONObject(i);
//                    String key = "url";
//                    Map<String, Object> app = new HashMap<>();
//                    String value = item1.getString(mFieldMap.get("url"));
//                    int i1 = checkRealPath(value);
//                    app.put(key, new Jentity(key, value, i1));
//                    if (i1 == 0) {
//                        appListState = 0;
//                    }
//                    if (appListState == 0) {
//                        appAllState = appListState;
//                    }
//                    appList.add(new Jentity(key, app, appListState));
//
//                }
//            } else {
//                appAllState = 0;
//            }
        } catch (Exception e) {
            appAllState = 0;
            e.printStackTrace();
        }
        Map<String, Object> result = new HashMap<>();
        result.put("value", appList);
        result.put("state", appAllState);
        result.put("msg", "");
        return result;
    }
}
