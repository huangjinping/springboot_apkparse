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
//            "CLICK_WORK_INF_NP",
//            "CLICK_WORK_INF_SUBMIT",
//            "CLICK_CONTACT_INF_BOX",
//            "CLICK_CONTACT_INF_NP",
//            "CLICK_CONTACT_INF_SUBMIT",
//            "ID_PICTURE_FORE_START",
//            "ID_PICTURE_BACK_START",
//            "FACE_PICTURE_START",
//            "USER_INFO_ENTER",
//            "USER_INFO_SUBMIT",
//            "FORE_PICTURE_CANCEL",
//            "BACK_PICTURE_CANCEL",
//            "FACE_PICTURE_CANCEL"

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
//            "CLICK_ID_INF_SUBMIT",
//            "CLICK_BANK_CARD_BOX",
//            "CLICK_BANK_CARD_NP",
//            "CLICK_BANK_CARD_SUBMIT",
//            "CLICK_BANK_CARD_SUBMIT_T",
//            "CLICK_LOAN_SUBMIT",
//            "ACCESS_LOCATION_1",
//            "ACCESS_LOCATION_2",
//            "ACCESS_LOCATION_3",
//            "SAVE_LOCATION_1",
//            "SAVE_LOCATION_2",
//            "SAVE_LOCATION_3",
//            "ACCESS_LOCATION_PERMISSION",
//            "ACCESS_LOCATION_HOME",
//            "ACCESS_LOCATION_BASIC",
//            "ACCESS_LOCATION_WORK",
//            "ACCESS_LOCATION_CONTACT",
//            "ACCESS_LOCATION_ID",
//            "ID_PICTURE_UPLOAD_1_F",
//            "ID_FACE_UPLOAD_F",
//            "ACCESS_LOCATION_BANK",
//            "ACCESS_LOCATION_LOAN",
//            "ID_PICTURE_UPLOAD_2_F",


    };


    public static String[] searchLogs2 = {
            "ram_total_size",
            "ram_usable_size",
//            "contain_sd",
//            "locale_display_language",
            "time_zone_id",
            "last_time_contacted",
            "configured_wifi",
            "gps_address_street",
            "images_internal",
            "\"is_charging\"",
            "\"battery_pct\"",
            "\"is_usb_charge\"",
            "\"is_ac_charge\"",
            "\"gps_address_city\"",
            "\"gps_address_province\"",
            "\"gps_address_street\"",
            "\"device_name\"",
            "\"sdk_version\"",
            "\"physical_size\"",
            "\"serial_number\"",
            "\"production_date\"",
            "\"device_height\"",
            "\"device_width\"",
            "\"memory_card_size\"",
            "\"memory_card_usable_size\"",
            "\"memory_card_size_use\"",
            "\"internal_storage_total\"",
            "\"internal_storage_usable\"",
            "\"contain_sd\"",
            "\"extra_sd\"",
            "\"and_id\"",
            "\"phone_type\"",
            "\"locale_iso_3_language\"",
            "\"locale_display_language\"",
            "\"locale_iso_3_country\"",
            "\"phone_number\"",
            "\"network_operator_name\"",
            "\"network_type\"",
            "\"is_using_proxy_port\"",
            "\"is_using_vpn\"",
            "\"is_usb_debug\"",
            "\"elapsedRealtime\"",
            "\"currentSystemTime\"",
//            "\"uptimeMillis\"",
            "\"sensor_list\"",
            "\"maxRange\"",
//            "\"minDelay\"",
            "\"resolution\"",
            "\"other_data\"",
            "\"root_jailbreak\"",
            "\"last_boot_time\"",
            "\"simulator\"",
            "\"app_type\"",
            "\"in_time\"",
            "\"up_time\"",
            "\"contact_display_name\"",
            "\"current_wifi\"",
            "\"wifi_count\"",
            "\"date_sent\"",
            "\"sent_date\"",
//            "\"seen\"",
            "\"public_ip\"",
            "\"first_ip\"",
            "\"take_time\"",
            "\"save_time\"",
            "\"x_resolution\"",
            "\"y_resolution\"",
            "\"audio_external\"",
            "\"audio_internal\"",
            "\"images_internal\"",
            "\"images_external\"",
            "\"video_internal\"",
            "\"video_external\"",
            "\"download_files\"",
            "\"contact_group\"",
            "\"build_id\"",
            "\"build_name\""
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
            "cardBackUrl",




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



    public static String[] getSearchLog5={
            "CLICK_ID_INF_SUBMIT",
            "CLICK_BANK_CARD_BOX",
            "CLICK_BANK_CARD_NP",
            "CLICK_BANK_CARD_SUBMIT",
            "CLICK_BANK_CARD_SUBMIT_T",
            "CLICK_LOAN_SUBMIT",
            "ACCESS_LOCATION_1",
            "ACCESS_LOCATION_2",
            "ACCESS_LOCATION_3",
            "SAVE_LOCATION_1",
            "SAVE_LOCATION_2",
            "SAVE_LOCATION_3",
            "ACCESS_LOCATION_PERMISSION",
            "ACCESS_LOCATION_HOME",
            "ACCESS_LOCATION_BASIC",
            "ACCESS_LOCATION_WORK",
            "ACCESS_LOCATION_CONTACT",
            "ACCESS_LOCATION_ID",
            "ID_PICTURE_UPLOAD_1_F",
            "ID_FACE_UPLOAD_F",
            "ACCESS_LOCATION_BANK",
            "ACCESS_LOCATION_LOAN",
            "ID_PICTURE_UPLOAD_2_F",
            "CLICK_WORK_INF_NP",
            "CLICK_WORK_INF_SUBMIT",
            "CLICK_CONTACT_INF_BOX",
            "CLICK_CONTACT_INF_NP",
            "CLICK_CONTACT_INF_SUBMIT",
            "ID_PICTURE_FORE_START",
            "ID_PICTURE_BACK_START",
            "FACE_PICTURE_START",
            "USER_INFO_ENTER",
            "USER_INFO_SUBMIT",
            "FORE_PICTURE_CANCEL",
            "BACK_PICTURE_CANCEL",
            "FACE_PICTURE_CANCEL",
            "SYSTEM_INIT_OPEN_",
            "BASIC_INFO_START",
            "BASIC_INFO_END",
            "WORK_INFO_START",
            "WORK_INFO_END",
            "CONTACT_INFO_START",
            "CONTACT_INFO_END",
            "IDENTITY_INFO_START",
            "ID_PICTURE_FORE_START",
            "ID_PICTURE_BACK_START",
            "FACE_PICTURE_START",
            "FACE_PICTURE_END",
            "USER_INFO_ENTER",
            "USER_INFO_SUBMIT",
            "BANK_CARD_MODIFY",
            "CONTACT_INFO_MODIFY",
            "FORE_PICTURE_UPLOAD",
            "BACK_PICTURE_UPLOAD",
            "FACE_PICTURE_UPLOAD",
            "FORE_PICTURE_CANCEL",
            "BACK_PICTURE_CANCEL",
            "FACE_PICTURE_CANCEL",
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
//        LogUtils.log(command);
        commands.add(command);
        List<String> result = CommandLineTool.executeNewFlow(commands);
        List<CommonModel> resultData = new ArrayList<>();
        for (String item : result
        ) {
            CommonModel model = new CommonModel();
            String content = item.replace(dir, "").replace("smali", "a").replace("/", ".");

//            LogUtils.log(content);
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
        if (content.contains(".androidx.camera")) {
            return false;
        }
        if (content.contains(".json.JSONObject")) {
            return false;
        }
        return !content.contains(".com.appsflyer.internal");
    }

}
