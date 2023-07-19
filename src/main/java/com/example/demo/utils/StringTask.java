package com.example.demo.utils;

import com.example.demo.bean.CommonModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringTask {
    public static String[] searchLogs = {
            "CLICK_LOGIN_LOGIN",
            "SYSTEM_FIRST_REGISTER_SUCCESS",
            "CLICK_INDEX_APPLY",
            "CLICK_BASIC_INF_BOX",
            "CLICK_BASIC_INF_NP",
            "CLICK_BASIC_INF_SUBMIT",
            "CLICK_WORK_INF_BOX",
            "CLICK_WORK_INF_NP",
            "CLICK_WORK_INF_SUBMIT",
            "CLICK_CONTACT_INF_BOX",
            "CLICK_CONTACT_INF_NP",
            "CLICK_CONTACT_INF_SUBMIT",
//            "CLICK_CONTACT_INF_SUBMIT_T",
//            "SYSTEM_ENTER_ID_INF",
//            "CLICK_ID_PICTURE_SUBMIT_1",
//            "ID_PICTURE_SUBMIT_1_T",
//            "ID_PICTURE_SUBMIT_1_F",
//            "CLICK_ID_PICTURE_SUBMIT_2",
//            "ID_PICTURE_SUBMIT_2_T",
//            "ID_PICTURE_SUBMIT_2_F",
//            "ID_PICTURE_UPLOAD",
//            "CLICK_ID_FACE_SUBMIT",
//            "ID_FACE_SUBMIT_T",
//            "ID_FACE_SUBMIT_F",
//            "CLICK_ID_INF_SUBMIT",
//            "CLICK_BANK_CARD_BOX",
//            "CLICK_BANK_CARD_NP",
//            "CLICK_BANK_CARD_SUBMIT",
//            "CLICK_BANK_CARD_SUBMIT_T",
//            "CLICK_LOAN_SUBMIT"
    };

    public static String[] searchLogs1 = {

            "CLICK_CONTACT_INF_SUBMIT_T",
            "SYSTEM_ENTER_ID_INF",
            "CLICK_ID_PICTURE_SUBMIT_1",
            "ID_PICTURE_SUBMIT_1_T",
            "ID_PICTURE_SUBMIT_1_F",
            "CLICK_ID_PICTURE_SUBMIT_2",
            "ID_PICTURE_SUBMIT_2_T",
            "ID_PICTURE_SUBMIT_2_F",
            "ID_PICTURE_UPLOAD",
            "CLICK_ID_FACE_SUBMIT",
            "ID_FACE_SUBMIT_T",
            "ID_FACE_SUBMIT_F",
            "CLICK_ID_INF_SUBMIT",
            "CLICK_BANK_CARD_BOX",
            "CLICK_BANK_CARD_NP",
            "CLICK_BANK_CARD_SUBMIT",
            "CLICK_BANK_CARD_SUBMIT_T",
            "CLICK_LOAN_SUBMIT"
    };


    public static String[] searchLogs2 = {
            "ram_total_size",
            "ram_usable_size",
            "contain_sd",
            "locale_display_language",
            "time_zone_id",
            "last_time_contacted",
            "configured_wifi",
            "gps_address_street",
            "images_internal"

    };


    public static String[] searchLogs3 = {
            "maritalStatusDesc",
            "educationalDesc",
            "workTypeDesc",
            "incomeLevelDesc",
            "sourceOfIncomeDesc",
            "workYearsDesc",
            "paydayOneDesc",
            "paydayTwoDesc",
            "phoneNumberSec",
            "relationshipSec",
            "relationshipSecName",
            "relationshipName",
            "surnameSec",
            "bankAccountNumber",
            "bankNameDesc",
            "bankAccountTypeDesc",
            "collectionTypeDesc",
            "payTypeDesc",
            "curpTypeDesc",
            "familySizeDesc",
            "houseOwnershipDesc",
            "sexDesc",
            "educationalDesc",
            "maxCreditAmount",
            "minCreditAmount",
            "incrAmount",
            "overdueStatus",
            "prodetailList",
            "browserOpen",
            "newRepayTypeFlag",
            "regionlevel",
            "regionId",
            "regionName",
            "regionLevel",
            "frontImage",
            "frontUrl",
            "cardBackUrl"
    };

    public static String[] SearchLog4 = {
            "indexForMulApp",
            "uploadMobile",
            "batchPreSubmit",
            "updateRepayBankInfo",
            "custInfoQuery",
            "getRepaymentChannel",
            "getReborrowFlag",
            "vipProducts",
            "saveLinkman",
            "saveBasicCustInfo",
            "queryProduct",
            "custInfoBasicQuery",
            "getAppSetting",
            "getVerifCode",
            "loginForSms",
            "msgFeature",
            "getAppConfig",
            "orderListForMulApp",
            "uploadRiskPoint",
            "uploadOperation"
    };


    public StringTask() {

    }

    public static List<String> getLenIndex(String[] result, int len, int index) {

        List<String> strs = Arrays.asList(result);
        int cro = strs.size() / len;
        List<String> strings = new ArrayList<>();
        if (cro * (index + 1) > strs.size() - 1) {
            strings = strs.subList(cro * index, strs.size() - 1);
        } else {
            strings = strs.subList(cro * index, cro * (index + 1));
        }
        return strings;
    }

    public List<CommonModel> searchLogs(String dir, String[] arr) {
        List<String> commands = new ArrayList<>();
        List<String> strs = Arrays.asList(arr);
        String command = CommandLineTool.buildCMDbyList(dir, strs);
        LogUtils.log(command);
        commands.add(command);
        List<String> result = CommandLineTool.executeNewFlow(commands);
        List<CommonModel> resultData = new ArrayList<>();
        for (String item : result
        ) {
            CommonModel model = new CommonModel();
            String content = item.replace(dir, "").replace("smali", "a").replace("/", ".");
            if (content.length() > 200) {
                content = content.substring(0, 190);
            }
            model.setName(content);
            resultData.add(model);
        }
        return resultData;
    }

    public List<CommonModel> searchLogs(String dir, List<String> strs) {
        List<String> commands = new ArrayList<>();
        String command = CommandLineTool.buildCMDbyList(dir, strs);
        LogUtils.log(command);
        commands.add(command);
        List<String> result = CommandLineTool.executeNewFlow(commands);
        List<CommonModel> resultData = new ArrayList<>();
        for (String item : result
        ) {
            CommonModel model = new CommonModel();
            String content = item.replace(dir, "").replace("smali", "a").replace("/", ".");
            if (content.length() > 400) {
                content = content.substring(0, 395);
            }
            if (!checkContent(content)) {
                continue;
            }
            model.setName(content);
            resultData.add(model);
        }
        return resultData;
    }


    boolean checkContent(String content) {
        if (TextUtils.isEmpty(content)) {
            return false;
        }

        if (content.contains(".facebook.")) {
            return false;
        }
        if (content.contains(".google.")) {
            return false;

        }
        if (content.contains(".branch.")) {
            return false;

        }
        if (content.contains(".branch.")) {
            return false;
        }
        if (content.contains(".flutter.")) {
            return false;
        }
        if (content.contains(".dcloud.")) {
            return false;
        }
        if (content.contains(".android.billingclient.")) {
            return false;
        }
        if (content.contains(".hamcrest.core.")) {
            return false;
        }
        return true;
    }

}
