package com.example.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.AppConfig;
import com.example.demo.bean.Jentity;
import com.example.demo.bean.PropSolrGroup;
import com.example.demo.bean.UserParam;

import java.util.*;

public class JsonParser {


    private UserParam userParam;

    public JsonParser(UserParam userParam) {
        this.userParam = userParam;
    }

    public Map<String, Object> parseRoot(String source) {

        Map<String, Object> root = new HashMap<>();
        Map<String, Object> stringObjectMap = parseHardware(source);
        Jentity hardware = new Jentity("hardware", stringObjectMap, stringObjectMap.isEmpty() ? 0 : 1);
        root.put("hardware", hardware);

        Map<String, Object> stringStorage = parseStorage(source);
        Jentity storage = new Jentity("storage", stringStorage, stringStorage.isEmpty() ? 0 : 1);
        root.put("storage", storage);

        Map<String, Object> parsegeneral_data = parsegeneral_data(source);
        Jentity general_data = new Jentity("general_data", parsegeneral_data, parsegeneral_data.isEmpty() ? 0 : 1);
        root.put("general_data", general_data);

        Map<String, Object> other_dataMap = parseother_data(source);
        Jentity other_data = new Jentity("other_data", other_dataMap, other_dataMap.isEmpty() ? 0 : 1);
        root.put("other_data", other_data);


        Map<String, Object> parseApplicationMap = parseApplication(source);
        Jentity parseApplication = new Jentity("application", parseApplicationMap, parseApplicationMap.isEmpty() ? 0 : 1);
        root.put("application", parseApplication);


        Map<String, Object> parseSMSMap = parseSMS(source);
        Jentity parseSMS = new Jentity("sms", parseSMSMap, parseSMSMap.isEmpty() ? 0 : 1);
        root.put("sms", parseSMS);


//        Map<String, Object> parseSMSMap = parseSMS(source);
//        Jentity parseSMS = new Jentity("sms", parseSMSMap, parseSMSMap.isEmpty() ? 0 : 1);
//        root.put("sms", parseSMS);

        Map<String, Object> parselocation = parseLocation(source);
        Jentity location = new Jentity("location", parselocation, parselocation.isEmpty() ? 0 : 1);
        root.put("location", location);


        Map<String, Object> parsebattery_status = parsebattery_status(source);
        Jentity battery_status = new Jentity("battery_status", parsebattery_status, parsebattery_status.isEmpty() ? 0 : 1);
        root.put("battery_status", battery_status);


        Map<String, Object> parsepublic_ip = parsepublic_ip(source);
        Jentity public_ip_status = new Jentity("public_ip", parsepublic_ip, parsepublic_ip.isEmpty() ? 0 : 1);
        root.put("public_ip", public_ip_status);

        Map<String, Object> parseNetworkMap = parseNetWork(source);
        Jentity parseNetWork = new Jentity("network", parseNetworkMap, parseNetworkMap.isEmpty() ? 0 : 1);
        root.put("network", parseNetWork);


        Map<String, Object> parseCalendarMap = parseCalendar(source);
        Jentity parsecalendar = new Jentity("calendar", parseCalendarMap, parseCalendarMap.isEmpty() ? 0 : 1);
        root.put("calendar", parsecalendar);


        if (AppConfig.AppType.TYPE_RELEASE531 != userParam.getAppType()) {
            Map<String, Object> parseAccountMap = parseAccount(source);
            Jentity parseAccount = new Jentity("account", parseAccountMap, parseAccountMap.isEmpty() ? 0 : 1);
            root.put("account", parseAccount);

            Map<String, Object> parseContractMap = parseContract(source);
            Jentity parsecontact = new Jentity("contact", parseContractMap, parseContractMap.isEmpty() ? 0 : 1);
            root.put("contact", parsecontact);


            Map<String, Object> parsedataListMap = parsedataList(source);
            Jentity dataList = new Jentity("dataList", parsedataListMap, parsedataListMap.isEmpty() ? 0 : 1);
            root.put("dataList", dataList);

        }


        Map<String, Object> parsePeopleMap = parsePeople(source);
        Jentity parsecontact = new Jentity("people", parsePeopleMap, parsePeopleMap.isEmpty() ? 0 : 1);
        root.put("people", parsecontact);


        Map<String, Object> parseCallMap = parseCall(source);
        Jentity parseCall = new Jentity("call", parseCallMap, parseCallMap.isEmpty() ? 0 : 1);
        root.put("call", parseCall);

        Map<String, Object> parseCommMap = parseComm(source);


        root.putAll(parseCommMap);
//        Gson gson = new Gson();
//        LogUtils.log(gson.toJson(root));
        return root;
    }

    public Map<String, Object> parseComm(String source) {
        JSONObject item = JSON.parseObject(source);
        Map<String, Object> result = new HashMap<>();
        String key = "audio_external";
        String audio_external = item.getString(key);

        result.put(key, new Jentity(key, audio_external, CheckUtils.getSaferLimitInt(item, key, 0)));

        key = "audio_internal";
        String audio_internal = item.getString(key);
        result.put(key, new Jentity(key, audio_internal, CheckUtils.getSaferLimitInt(item, key, 0)));

        key = "images_internal";
        String images_internal = item.getString(key);
        result.put(key, new Jentity(key, images_internal, CheckUtils.getSaferLimitInt(item, key, 0)));

        key = "images_external";
        String images_external = item.getString(key);
        result.put(key, new Jentity(key, images_external, CheckUtils.getSaferLimitInt(item, key, 0)));


        key = "video_internal";
        String video_internal = item.getString(key);
        result.put(key, new Jentity(key, video_internal, CheckUtils.getSaferLimitInt(item, key, 0)));


        key = "video_external";
        String video_external = item.getString(key);
        result.put(key, new Jentity(key, video_external, CheckUtils.getSaferLimitInt(item, key, 0)));

        key = "download_files";
        String download_files = item.getString(key);
        result.put(key, new Jentity(key, download_files, CheckUtils.getSaferLimitInt(item, key, 0)));


        key = "contact_group";
        String contact_group = item.getString(key);
        result.put(key, new Jentity(key, contact_group, CheckUtils.getSaferLimitInt(item, key, 0)));

        key = "build_id";
        String build_id = item.getString(key);
        result.put(key, new Jentity(key, build_id, CheckUtils.checkVersionCode(build_id) ? 1 : 0));
//        if (!TextUtils.isEmpty(build_id)) {
//            result.put(key, new Jentity(key, build_id, 1));
//        } else {
//            result.put(key, new Jentity(key, build_id, 0));
//        }


        key = "build_name";
        String build_name = item.getString(key);

        result.put(key, new Jentity(key, build_name, CheckUtils.checkVersionName(build_name) ? 1 : 0));

//        if (!TextUtils.isEmpty(build_name)) {
//            result.put(key, new Jentity(key, build_name, 1));
//        } else {
//            result.put(key, new Jentity(key, build_name, 0));
//        }

        key = "package_name";
        String package_name = item.getString(key);

        if (!TextUtils.isEmpty(package_name)) {
            String[] split = package_name.split("[.]+");

            if (package_name.startsWith("uni.UN")) {
                result.put(key, new Jentity(key, package_name, 1));

            } else {
                if (split != null && split.length > 2) {
                    result.put(key, new Jentity(key, package_name, 1));
                } else {
                    result.put(key, new Jentity(key, package_name, 0));
                }
            }


        } else {
            result.put(key, new Jentity(key, package_name, 0));
        }

        key = "create_time";
        String create_time = item.getString(key);
        result.put(key, new Jentity(key, create_time, CheckUtils.getSaferStringWithTimeTemp(item, key)));

        return result;
    }


    public Map<String, Object> parsepublic_ip(String source) {
        JSONObject jsonObject = JSON.parseObject(source);
        Map<String, Object> locationResult = new HashMap<>();
        if (jsonObject.containsKey("public_ip")) {
            JSONObject item = jsonObject.getJSONObject("public_ip");

            Map<String, Object> result = new HashMap<>();
            int stats = 1;

            String key = "first_ip";
            String first_ip = item.getString(key);
            int first_ipstate = CheckUtils.isValidIPAddress(first_ip) ? 1 : 0;
            result.put(key, new Jentity(key, first_ip, first_ipstate));
            if (first_ipstate != 1) {
                stats = 0;
            }

            key = "second_ip";
            String second_ip = item.getString(key);
            int second_ipstate = CheckUtils.isValidIPAddress(second_ip) ? 1 : 0;
            result.put(key, new Jentity(key, second_ip, second_ipstate));
            if (second_ipstate != 1) {
                stats = 0;
            }

            StringBuilder builder = new StringBuilder();
            if (stats == 0) {
                builder.append("数据有问题\n");
            }


            locationResult.put("value", result);
            locationResult.put("state", stats);
            locationResult.put("msg", builder);
        }

        return locationResult;
    }


    public Map<String, Object> parsebattery_status(String source) {
        JSONObject jsonObject = JSON.parseObject(source);
        Map<String, Object> locationResult = new HashMap<>();
        if (jsonObject.containsKey("battery_status")) {
            JSONObject item = jsonObject.getJSONObject("battery_status");

            Map<String, Object> result = new HashMap<>();
            int stats = 1;

            String key = "is_charging";
            String is_charging = item.getString(key);
            int is_chargingstate = CheckUtils.getSaferStringWithLimit(item, key, new String[]{"0", "1"});
            result.put(key, new Jentity(key, is_charging, is_chargingstate));
            if (is_chargingstate != 1) {
                stats = 0;
            }

            key = "battery_pct";
            String battery_pct = item.getString(key);
            LogUtils.logJson("==========battery_pct===========2" + battery_pct);

            int battery_pctstate = CheckUtils.getSaferLimitDouble(item, key, 20);
            result.put(key, new Jentity(key, battery_pct, battery_pctstate));
            if (battery_pctstate != 1) {
                stats = 0;
            }

            key = "is_usb_charge";
            String is_usb_charge = item.getString(key);
            int is_usb_chargestate = CheckUtils.getSaferStringWithLimit(item, key, new String[]{"0", "1"});
            result.put(key, new Jentity(key, is_usb_charge, is_usb_chargestate));
            if (is_usb_chargestate != 1) {
                stats = 0;
            }

            key = "is_ac_charge";
            String is_ac_charge = item.getString(key);
            int is_ac_chargeestate = CheckUtils.getSaferStringWithLimit(item, key, new String[]{"0", "1"});
            result.put(key, new Jentity(key, is_ac_charge, is_ac_chargeestate));
            if (is_ac_chargeestate != 1) {
                stats = 0;
            }

            StringBuilder builder = new StringBuilder();
            if (stats == 0) {
                builder.append("数据有问题\n");
            }


            locationResult.put("value", result);
            locationResult.put("state", stats);
            locationResult.put("msg", builder);
        }


        return locationResult;
    }


    public Map<String, Object> parseLocation(String source) {
        JSONObject jsonObject = JSON.parseObject(source);
        Map<String, Object> locationResult = new HashMap<>();
        if (jsonObject.containsKey("location")) {
            JSONObject Location = jsonObject.getJSONObject("location");
            String smsg = jsonObject.getString("location");

            String latitude = "";
            String longitude = "";
            if (Location.containsKey("gps")) {
                JSONObject gps = Location.getJSONObject("gps");
                latitude = gps.getString("latitude");
                longitude = gps.getString("longitude");
            }


            String gps_address_city = Location.getString("gps_address_city");
            String gps_address_province = Location.getString("gps_address_province");
            String gps_address_street = Location.getString("gps_address_street");
            String msm = latitude + "," + longitude;
            String url = "https://www.google.com.hk/maps/search/" + msm;
            int stats = 1;
            if (msm.length() < 8) {
                stats = 0;
            }
            if (!msm.equals(gps_address_city)) {
                stats = 0;
            }
            if (!msm.equals(gps_address_province)) {
                stats = 0;
            }
            if (!msm.equals(gps_address_street)) {
                stats = 0;
            }


            try {
                double d_latitude = Double.parseDouble(latitude);
                double d_longitude = Double.parseDouble(longitude);
                if (d_latitude > d_longitude) {
                    stats = 0;
                }
                if ((Math.abs(d_latitude) <= 90) && (Math.abs(d_longitude) <= 180)) {
                } else {
                    stats = 0;
                }
            } catch (Exception e) {
                stats = 0;

//                e.printStackTrace();
            }


            StringBuilder builder = new StringBuilder();
            if (stats == 0) {
                builder.append("请确实经纬度是否正常\n");
                builder.append("(纬度,经度)\n");
            }
            Map<String, Object> result = new HashMap<>();
            locationResult.put("value", new Jentity("location", smsg, stats));
            result.put("value", locationResult);
            result.put("state", stats);
            result.put("msg", builder);
            result.put("url", url);

            return result;
        }

        return locationResult;
    }

    public Map<String, Object> parsegeneral_data(String source) {
        JSONObject jsonObject = JSON.parseObject(source);
        Map<String, Object> generalResult = new HashMap<>();
        if (jsonObject.containsKey("general_data")) {
            JSONObject general_data = jsonObject.getJSONObject("general_data");
            int stats = 1;
            String key = "gaid";
            String gaid = general_data.getString(key);
            if (!TextUtils.isEmpty(gaid) && gaid.length() == 36) {
                generalResult.put(key, new Jentity(key, gaid, 1));
            } else {
                stats = 0;
                generalResult.put(key, new Jentity(key, gaid, 0));
            }

            key = "and_id";
//            0e4d4389aac32055
            String and_id = general_data.getString(key);
            if (!TextUtils.isEmpty(and_id) && and_id.length() == 16) {
                generalResult.put(key, new Jentity(key, and_id, 1));
            } else {
                stats = 0;
                generalResult.put(key, new Jentity(key, and_id, 0));
            }

            key = "phone_type";
            String phone_type = general_data.getString(key);
            if (!TextUtils.isEmpty(phone_type) && phone_type.length() > 1) {
                generalResult.put(key, new Jentity(key, phone_type, 1));
            } else {
                stats = 0;
                generalResult.put(key, new Jentity(key, phone_type, 0));
            }
            LogUtils.logJson("=======5====================" + stats);
            key = "mac";
            String mac = general_data.getString(key);
            if (CheckUtils.isMac(mac)) {
                generalResult.put(key, new Jentity(key, mac, 1));
            } else {
                stats = 0;

                generalResult.put(key, new Jentity(key, mac, 0));
            }


            key = "locale_iso_3_language";
            String locale_iso_3_language = general_data.getString(key);
            if (!TextUtils.isEmpty(locale_iso_3_language) && locale_iso_3_language.length() == 3) {
                generalResult.put(key, new Jentity(key, locale_iso_3_language, 1));
            } else {
                stats = 0;
                generalResult.put(key, new Jentity(key, locale_iso_3_language, 0));
            }

            key = "locale_display_language";
            String locale_display_language = general_data.getString(key);
            if (!TextUtils.isEmpty(locale_display_language)) {
                generalResult.put(key, new Jentity(key, locale_display_language, 1));
            } else {
                stats = 0;
                generalResult.put(key, new Jentity(key, locale_display_language, 0));
            }


            key = "locale_iso_3_country";
            String locale_iso_3_country = general_data.getString(key);
            if (!TextUtils.isEmpty(locale_iso_3_country) && locale_iso_3_country.length() == 3) {
                generalResult.put(key, new Jentity(key, locale_iso_3_country, 1));
            } else {
                stats = 0;

                generalResult.put(key, new Jentity(key, locale_iso_3_country, 0));
            }
            key = "imei";
            String imei = general_data.getString(key);
            if (!TextUtils.isEmpty(imei) && imei.length() > 10) {
                generalResult.put(key, new Jentity(key, imei, 1));
            } else {
                stats = 0;

                generalResult.put(key, new Jentity(key, imei, 0));
            }

            key = "network_operator_name";
            String network_operator_name = general_data.getString(key);
            if (!TextUtils.isEmpty(network_operator_name)) {
                generalResult.put(key, new Jentity(key, network_operator_name, 1));
            } else {
                stats = 0;
                generalResult.put(key, new Jentity(key, network_operator_name, 0));
            }

            key = "network_type";
            String network_type = general_data.getString(key);
            if (!TextUtils.isEmpty(network_type)) {
                String network_typeTemp = network_type.toUpperCase();
                String LIMIT = "WIFI2G3G4G5G6G";
                if (LIMIT.contains(network_typeTemp)) {
                    generalResult.put(key, new Jentity(key, network_type, 1));
                } else {
                    stats = 0;
                    generalResult.put(key, new Jentity(key, network_type, 0));
                }
            } else {
                stats = 0;

                generalResult.put(key, new Jentity(key, network_type, 0));
            }


            key = "time_zone_id";
            String time_zone_id = general_data.getString(key);
            if (!TextUtils.isEmpty(time_zone_id)) {
                generalResult.put(key, new Jentity(key, time_zone_id, 1));
            } else {
                stats = 0;

                generalResult.put(key, new Jentity(key, time_zone_id, 0));
            }

            key = "language";
            String language = general_data.getString(key);
            if (!TextUtils.isEmpty(language)) {
                generalResult.put(key, new Jentity(key, language, 1));
            } else {
                stats = 0;

                generalResult.put(key, new Jentity(key, language, 0));
            }

            key = "is_using_proxy_port";
            String is_using_proxy_port = general_data.getString(key);
            if (!TextUtils.isEmpty(is_using_proxy_port)) {
                if ("false".equals(is_using_proxy_port) || "true".equals(is_using_proxy_port)) {
                    generalResult.put(key, new Jentity(key, is_using_proxy_port, 1));
                } else {
                    stats = 0;

                    generalResult.put(key, new Jentity(key, is_using_proxy_port, 0));
                }
            } else {
                stats = 0;

                generalResult.put(key, new Jentity(key, is_using_proxy_port, 0));
            }


            key = "is_using_vpn";
            String is_using_vpn = general_data.getString(key);
            if (!TextUtils.isEmpty(is_using_vpn)) {
                if ("false".equals(is_using_vpn) || "true".equals(is_using_vpn)) {
                    generalResult.put(key, new Jentity(key, is_using_vpn, 1));
                } else {
                    stats = 0;
                    generalResult.put(key, new Jentity(key, is_using_vpn, 0));
                }
            } else {
                stats = 0;
                generalResult.put(key, new Jentity(key, is_using_vpn, 0));
            }

            key = "is_usb_debug";
            String is_usb_debug = general_data.getString(key);
            if (!TextUtils.isEmpty(is_usb_debug)) {
                if ("false".equals(is_usb_debug) || "true".equals(is_usb_debug)) {
                    generalResult.put(key, new Jentity(key, is_usb_debug, 1));
                } else {
                    stats = 0;
                    generalResult.put(key, new Jentity(key, is_usb_debug, 0));
                }
            } else {
                stats = 0;
                generalResult.put(key, new Jentity(key, is_usb_debug, 0));
            }


            key = "elapsedRealtime";
            String elapsedRealtime = general_data.getString(key);
            int elapsedRealtimeStats = CheckUtils.getSaferLimitDouble(general_data, key, 0);
            if (elapsedRealtimeStats != 1) {
                stats = 0;
            }
            generalResult.put(key, new Jentity(key, elapsedRealtime, elapsedRealtimeStats));
            LogUtils.logJson("=======3====================" + stats);

            key = "currentSystemTime";
            String currentSystemTime = general_data.getString(key);
            int currentSystemTimeStats = CheckUtils.getSaferStringWithTimeTemp(general_data, key);
            generalResult.put(key, new Jentity(key, currentSystemTime, currentSystemTimeStats));

            if (currentSystemTimeStats != 1) {
                stats = 0;

            }
            LogUtils.logJson("=======2====================" + stats);


            key = "uptimeMillis";
            String uptimeMillis = general_data.getString(key);
            int uptimeMillisStats = CheckUtils.getSaferLimitDouble(general_data, key, 0);
            generalResult.put(key, new Jentity(key, uptimeMillis, uptimeMillisStats));
            if (uptimeMillisStats != 1) {
                stats = 0;
            }

            LogUtils.logJson("=======1====================" + stats);


            key = "sensor_list";

            int sensorStats = 1;
            StringBuilder sensorMsg = new StringBuilder();

            if (general_data.containsKey("sensor_list")) {
                JSONArray sensor_list = general_data.getJSONArray("sensor_list");

                List<Jentity> sensorResultList = new ArrayList<>();
                if (sensor_list.size() > 0) {
                    PropSolrGroup solrGroup = new PropSolrGroup();

                    for (int i = 0; i < sensor_list.size(); i++) {
                        int sensorCCStats = 1;
                        Map<String, Object> sensor_0 = new HashMap<>();
                        JSONObject sensor = sensor_list.getJSONObject(i);
                        String skey = "";
                        skey = "type";
                        String type = sensor.getString(skey);
                        solrGroup.addPropSolr(skey, type);

                        int typeStats = CheckUtils.getSaferLimitInt(sensor, skey, 0);
                        sensor_0.put(skey, new Jentity(skey, type, typeStats));
                        if (typeStats != 1) {
                            sensorCCStats = 0;
                        }

                        skey = "name";
                        String name = sensor.getString(skey);
                        solrGroup.addPropSolr(skey, name);

                        if (!TextUtils.isEmpty(name)) {
                            sensor_0.put(skey, new Jentity(skey, name, 1));
                        } else {
                            sensorCCStats = 0;
                            sensor_0.put(skey, new Jentity(skey, name, 0));
                        }

                        skey = "version";
                        String version = sensor.getString(skey);
//                        solrGroup.addPropSolr(skey, version);

                        int versionStats = CheckUtils.getSaferLimitInt(sensor, skey, 1);
                        sensor_0.put(skey, new Jentity(skey, version, versionStats));
                        if (versionStats != 1) {
                            sensorCCStats = 0;
                        }

                        skey = "vendor";
                        String vendor = sensor.getString(skey);
//                        solrGroup.addPropSolr(skey, vendor);

                        if (!TextUtils.isEmpty(vendor)) {
                            sensor_0.put(skey, new Jentity(skey, vendor, 1));
                        } else {
                            sensorCCStats = 0;
                            sensor_0.put(skey, new Jentity(skey, vendor, 0));
                        }

                        skey = "maxRange";
                        String maxRange = sensor.getString(skey);
//                        solrGroup.addPropSolr(skey, maxRange);

                        int maxRangeStats = CheckUtils.getSaferLimitDouble(sensor, skey, 1);
                        sensor_0.put(skey, new Jentity(skey, maxRange, CheckUtils.getSaferLimitDouble(sensor, skey, 1)));
                        if (maxRangeStats != 1) {
                            sensorCCStats = 0;
                        }

                        skey = "minDelay";
                        String minDelay = sensor.getString(skey);

                        int minDelayStats = CheckUtils.getSaferLimitInt(sensor, skey, -2);
                        sensor_0.put(skey, new Jentity(skey, minDelay, minDelayStats));
                        if (minDelayStats != 1) {
                            sensorCCStats = 0;
                        }


                        skey = "power";
                        String power = sensor.getString(skey);
//                        solrGroup.addPropSolr(skey, power);

                        int powerStats = CheckUtils.getSaferLimitDouble(sensor, skey, 0);
                        sensor_0.put(skey, new Jentity(skey, power, powerStats));
                        if (powerStats != 1) {
                            sensorCCStats = 0;
                        }

                        skey = "resolution";
                        String resolution = sensor.getString(skey);
//                        solrGroup.addPropSolr(skey, resolution);

                        if (!TextUtils.isEmpty(resolution)) {
                            sensor_0.put(skey, new Jentity(skey, resolution, 1));
                        } else {
                            sensorCCStats = 0;

                            sensor_0.put(skey, new Jentity(skey, resolution, 0));
                        }
                        if (sensorCCStats != 1) {
                            sensorStats = 0;
                        }
                        sensorResultList.add(new Jentity(sensor.getString("name"), sensor_0, sensorCCStats));
                    }

                    if (sensorResultList.size() > 0 && sensorResultList.size() < 6) {
                        sensorStats = 0;
                        sensorMsg.append("传感器个数异常");
                    }

                    Jentity solrResult = solrGroup.getResult();
                    if (solrResult.getState() < 1) {
                        sensorStats = 0;
                        sensorMsg.append(solrResult.getMsg());
                    }


                    Jentity jentity = new Jentity(key, sensorResultList, sensorStats);
                    jentity.setMsg(sensorMsg.toString());
                    generalResult.put(key, jentity);
                } else {
                    generalResult.put(key, new Jentity(key, "", CheckUtils.getSaferLimitDouble(general_data, key, 0)));
                }

            } else {
                sensorStats = 0;
                generalResult.put(key, new Jentity(key, "", CheckUtils.getSaferLimitDouble(general_data, key, 0)));

            }

            if (sensorStats != 1) {
                stats = 0;
            }
            Map<String, Object> result = new HashMap<>();
            result.put("value", generalResult);
            result.put("state", stats);
            result.put("msg", "");
            return result;
        }


        return generalResult;
    }

    public Map<String, Object> parseStorage(String source) {
        JSONObject jsonObject = JSON.parseObject(source);
        Map<String, Object> storageResult = new HashMap<>();


        List<Jentity> compareList = new ArrayList<>();


        if (jsonObject.containsKey("storage")) {
            JSONObject storage = jsonObject.getJSONObject("storage");
            int stats = 1;
            String key = "ram_total_size";
            String ram_total_size = storage.getString(key);
            int ram_total_sizeStats = CheckUtils.getSaferLimitDoubleRule1(storage, key, 999999);
            Jentity ram_total_sizejentity = new Jentity(key, ram_total_size, ram_total_sizeStats);

            storageResult.put(key, ram_total_sizejentity);
            if (ram_total_sizeStats != 1) {
                stats = 0;
            }
            compareList.add(ram_total_sizejentity);


            key = "ram_usable_size";
            String ram_usable_size = storage.getString(key);
            int ram_usable_size_stats = CheckUtils.getSaferLimitDoubleRule1(storage, key, 999999);
            if (ram_usable_size_stats != 1) {
                stats = 0;
            }
            Jentity ram_usable_size_statsjentity = new Jentity(key, ram_usable_size, ram_usable_size_stats);
            storageResult.put(key, new Jentity(key, ram_usable_size, ram_usable_size_stats));
            compareList.add(ram_usable_size_statsjentity);

            key = "memory_card_size";
            String memory_card_size = storage.getString(key);
            int memory_card_sizeStats = CheckUtils.getSaferLimitDoubleRule1(storage, key, 999999);
            if (memory_card_sizeStats != 1) {
                stats = 0;
            }
            Jentity memory_card_sizejentity = new Jentity(key, memory_card_size, memory_card_sizeStats);
            storageResult.put(key, memory_card_sizejentity);
            compareList.add(memory_card_sizejentity);

            key = "memory_card_usable_size";
            String memory_card_usable_size = storage.getString(key);
            int memory_card_usable_sizeStats = CheckUtils.getSaferLimitDoubleRule1(storage, key, 999999);
            Jentity memory_card_usable_sizejentity = new Jentity(key, memory_card_usable_size, memory_card_usable_sizeStats);
            storageResult.put(key, memory_card_usable_sizejentity);
            if (memory_card_usable_sizeStats != 1) {
                stats = 0;
            }
            compareList.add(memory_card_usable_sizejentity);


            key = "memory_card_size_use";
            String memory_card_size_use = storage.getString(key);
            int memory_card_size_useStats = CheckUtils.getSaferLimitDoubleRule1(storage, key, 999999);
            Jentity memory_card_size_usejentity = new Jentity(key, memory_card_size_use, memory_card_size_useStats);
            storageResult.put(key, new Jentity(key, memory_card_size_use, memory_card_size_useStats));
            if (memory_card_size_useStats != 1) {
                stats = 0;
            }
            compareList.add(memory_card_size_usejentity);


            key = "internal_storage_total";
            String internal_storage_total = storage.getString(key);
            int internal_storage_totalStats = CheckUtils.getSaferLimitDoubleRule1(storage, key, 999999);
            Jentity internal_storage_totaljentity = new Jentity(key, internal_storage_total, internal_storage_totalStats);
            storageResult.put(key, internal_storage_totaljentity);
            if (internal_storage_totalStats != 1) {
                stats = 0;
            }
//            compareList.add(internal_storage_totaljentity);


            key = "internal_storage_usable";
            String internal_storage_usable = storage.getString(key);
            int internal_storage_usableStats = CheckUtils.getSaferLimitDoubleRule1(storage, key, 999999);
            Jentity internal_storage_usablejentity = new Jentity(key, internal_storage_usable, internal_storage_usableStats);
            storageResult.put(key, internal_storage_usablejentity);
            if (internal_storage_usableStats != 1) {
                stats = 0;
            }
//            compareList.add(internal_storage_usablejentity);


            key = "contain_sd";
            String contain_sd = storage.getString(key);
//            int contain_sdStats = CheckUtils.getSaferLimitDouble(storage, key, 0);
            int contain_sdStats = CheckUtils.getSaferStringWithLimit(storage, key, new String[]{"0", "1"});
            storageResult.put(key, new Jentity(key, contain_sd, contain_sdStats));

            if (contain_sdStats != 1) {
                stats = 0;
            }

            key = "extra_sd";
            String extra_sd = storage.getString(key);
//            int extra_sdStats = CheckUtils.getSaferLimitDouble(storage, key, 0);
            int extra_sdStats = CheckUtils.getSaferStringWithLimit(storage, key, new String[]{"0", "1"});

            storageResult.put(key, new Jentity(key, extra_sd, extra_sdStats));
            if (extra_sdStats != 1) {
                stats = 0;
            }

            key = "app_max_memory";
            String app_max_memory = storage.getString(key);
            int app_max_memoryStats = CheckUtils.getSaferLimitDoubleRule1(storage, key, 999999);
            Jentity app_max_memoryjentity = new Jentity(key, app_max_memory, app_max_memoryStats);
            storageResult.put(key, new Jentity(key, app_max_memory, app_max_memoryStats));
            if (app_max_memoryStats != 1) {
                stats = 0;
            }
            compareList.add(app_max_memoryjentity);


            key = "app_available_memory";
            String app_available_memory = storage.getString(key);
            int app_available_memoryStats = CheckUtils.getSaferLimitDoubleRule1(storage, key, 999999);
            Jentity app_available_memoryjentity = new Jentity(key, app_available_memory, app_available_memoryStats);
            storageResult.put(key, new Jentity(key, app_available_memory, app_available_memoryStats));
            if (app_available_memoryStats != 1) {
                stats = 0;
            }
            compareList.add(app_available_memoryjentity);


            key = "app_free_memory";
            String app_free_memory = storage.getString(key);
            int app_free_memoryStatus = CheckUtils.getSaferLimitDoubleRule1(storage, key, 999999);
            if (app_free_memoryStatus != 1) {
                stats = 0;
            }
            Jentity app_free_memoryjentity = new Jentity(key, app_free_memory, app_free_memoryStatus);
            storageResult.put(key, app_free_memoryjentity);
            compareList.add(app_free_memoryjentity);

            Jentity jentity = onCompareList(compareList);
            if (jentity.getState() != 1) {
                stats = jentity.getState();
            }


            Map<String, Object> result = new HashMap<>();
            result.put("value", storageResult);
            result.put("state", stats);
            result.put("msg", jentity.getValue());
            return result;
        }
        return storageResult;
    }

    //1,2,3,4,5,6
    //1,2,3,4,5,6
    private Jentity onCompareList(List<Jentity> compareList) {
        int stats = 1;
        StringBuilder builder = new StringBuilder();
        try {
            Map<String, String> map = new HashMap<>();
            for (int i = 0; i < compareList.size(); i++) {
                Jentity jent1 = compareList.get(i);
                if (jent1.getValue() != null) {
                    String key = jent1.getValue().toString();
                    if (map.containsKey(key)) {
                        stats = 0;
                        builder.append(map.get(key) + "=" + jent1.getName() + ";\n");
                    } else {
                        map.put(key, jent1.getName());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Jentity jentity = new Jentity("参数比较", builder.toString(), stats);

        return jentity;
    }


    public Map<String, Object> parseHardware(String source) {
        JSONObject jsonObject = JSON.parseObject(source);
        Map<String, Object> hardwareResult = new HashMap<>();

        //hardware
        if (jsonObject.containsKey("hardware")) {
            int stats = 1;
            JSONObject hardware = jsonObject.getJSONObject("hardware");
            String device_name = hardware.getString("device_name");
            if (!TextUtils.isEmpty(device_name)) {
                hardwareResult.put("device_name", new Jentity("device_name", device_name, 1));
            } else {
                stats = 0;
                hardwareResult.put("device_name", new Jentity("device_name", device_name, 0));
            }
            int sdk_version = hardware.getIntValue("sdk_version");
            if (sdk_version >= 21) {
                hardwareResult.put("sdk_version", new Jentity("sdk_version", sdk_version, 1));
            } else {
                stats = 0;
                hardwareResult.put("sdk_version", new Jentity("sdk_version", sdk_version, 0));
            }

            String release = hardware.getString("release");
            try {
                double lrelease = Double.parseDouble(release.replace(".", ""));
                if (lrelease >= 5.0) {
                    hardwareResult.put("release", new Jentity("release", release, 1));
                } else {
                    stats = 0;
                    hardwareResult.put("release", new Jentity("release", release, 0));
                }
            } catch (Exception e) {
                stats = 0;
                hardwareResult.put("release", new Jentity("release", release, 0));
//                e.printStackTrace();
            }


            String key = "model";
            String model = hardware.getString(key);
            if (!TextUtils.isEmpty(model)) {
                hardwareResult.put(key, new Jentity(key, model, 1));
            } else {
                stats = 0;

                hardwareResult.put(key, new Jentity(key, model, 0));
            }


            key = "physical_size";
            String physical_size = hardware.getString(key);
            int physical_sizeState = CheckUtils.getSaferLimitDouble(hardware, key, 0);
            if (physical_sizeState == 1) {
                hardwareResult.put(key, new Jentity(key, physical_size, 1));
            } else {
                stats = 0;

                hardwareResult.put(key, new Jentity(key, physical_size, 0));
            }

            key = "brand";
            String brand = hardware.getString(key);
            if (!TextUtils.isEmpty(brand)) {
                hardwareResult.put(key, new Jentity(key, brand, 1));
            } else {
                stats = 0;

                hardwareResult.put(key, new Jentity(key, brand, 0));
            }

            key = "board";
            String board = hardware.getString(key);
            if (!TextUtils.isEmpty(board)) {
                hardwareResult.put(key, new Jentity(key, board, 1));
            } else {
                stats = 0;

                hardwareResult.put(key, new Jentity(key, board, 0));
            }


            key = "production_date";
            String in_time = hardware.getString(key);
            int in_timeState = CheckUtils.getSaferStringWithTimeTemp(hardware, key);
            if (in_timeState != 1) {
                stats = 0;

            }
            hardwareResult.put(key, new Jentity(key, in_time, in_timeState));


            key = "imei1";
            String imei1 = hardware.getString(key);
            if (!TextUtils.isEmpty(imei1)) {
                hardwareResult.put(key, new Jentity(key, imei1, 1));
            } else {
                stats = 0;
                hardwareResult.put(key, new Jentity(key, imei1, 0));
            }

            key = "imei2";
            String imei2 = hardware.getString(key);
            if (!TextUtils.isEmpty(imei2)) {
                hardwareResult.put(key, new Jentity(key, imei2, 1));
            } else {
                stats = 0;
                hardwareResult.put(key, new Jentity(key, imei2, 0));
            }


            key = "cpu_num";
            String cpu_num = hardware.getString(key);
            int cpu_numState = CheckUtils.getSaferLimitInt(hardware, key, 1);
            hardwareResult.put(key, new Jentity(key, cpu_num, cpu_numState));
            if (cpu_numState != 1) {
                stats = 0;
            }

            key = "device_height";
            String device_height = hardware.getString(key);


            int heightState = CheckUtils.getSaferLimitDouble(hardware, key, 1);
            hardwareResult.put(key, new Jentity(key, device_height, heightState));
            if (heightState != 1) {
                stats = 0;
            }


            key = "device_width";
            String width = hardware.getString(key);
            int widthState = CheckUtils.getSaferLimitDouble(hardware, key, 1);
            if (widthState != 1) {
                stats = 0;
            }
            hardwareResult.put(key, new Jentity(key, width, widthState));
            Map<String, Object> result = new HashMap<>();
            result.put("value", hardwareResult);
            result.put("state", stats);
            result.put("msg", "");
            return result;
        } else {

        }
        return hardwareResult;
    }


    public Map<String, Object> hopedataList(JSONArray dataList) {


        Map<String, Object> other_dataResult = new HashMap<>();
        other_dataResult.put("state", 0);
        other_dataResult.put("msg", "图片空数据");


        try {
            JSONArray appArr = dataList;

            List<Jentity> appList = new ArrayList<>();
            PropSolrGroup solrGroup = new PropSolrGroup();

            int appCount = 0;
            int appAllListState = 1;

            for (int i = 0; i < appArr.size(); i++) {
                appCount++;
                int appListState = 1;

                JSONObject item = appArr.getJSONObject(i);
                Map<String, Object> app = new HashMap<>();
                String key = "name";
                String name = item.getString(key);
                solrGroup.addPropSolr(key, name);
                if (!TextUtils.isEmpty(name)) {
                    app.put(key, new Jentity(key, name, 1));
                } else {
                    appListState = 0;
                    app.put(key, new Jentity(key, name, 0));
                }


                key = "createTime";
                String createTime = item.getString(key);
                solrGroup.addPropSolr(key, name);

                int createTimeState = CheckUtils.getSaferStringWithTime01(item, key);
                app.put(key, new Jentity(key, createTime, createTimeState));
                if (createTimeState != 1) {
                    appListState = 0;
                }
                key = "date";
                String date = item.getString(key);
                solrGroup.addPropSolr(key, name);

                int dateState = CheckUtils.getSaferStringWithTime01(item, key);
                app.put(key, new Jentity(key, date, dateState));
                if (dateState != 1) {
                    appListState = 0;
                }


                key = "model";
                String model = item.getString(key);
                if (!TextUtils.isEmpty(model)) {
                    app.put(key, new Jentity(key, model, 1));
                } else {
                    appListState = 0;
                    app.put(key, new Jentity(key, model, 0));
                }

                key = "author";
                String author = item.getString(key);
                if (!TextUtils.isEmpty(author)) {
                    app.put(key, new Jentity(key, author, 1));
                } else {
                    appListState = 0;
                    app.put(key, new Jentity(key, author, 0));
                }


                key = "height";
                String height = item.getString(key);
                int heightState = CheckUtils.getSaferLimitDouble(item, key, 1);
                app.put(key, new Jentity(key, height, heightState));
                if (heightState != 1) {
                    appListState = 0;
                }

                key = "width";
                String width = item.getString(key);
                int widthState = CheckUtils.getSaferLimitDouble(item, key, 1);
                app.put(key, new Jentity(key, width, widthState));
                if (widthState != 1) {
                    appListState = 0;
                }


//                key = "flags";
//                String flags = item.getString(key);
//                int flagsState = CheckUtils.getSaferLimitInt(item, key, 1);
//                app.put(key, new Jentity(key, flags, flagsState));
//                if (flagsState != 1) {
//                    appListState = 0;
//                }
                appList.add(new Jentity(item.getString("name"), app, appListState));
                if (appListState != 1) {
                    appAllListState = 0;
                }

            }

            StringBuilder builder = new StringBuilder();
            if (appCount < 2) {
                appAllListState = 0;
                builder.append("图片数量有问题\n");
            }
            if (appAllListState == 0) {
                builder.append("数据有问题\n");
            }

            Jentity solrResult = solrGroup.getResult();
            if (solrResult.getState() < 1) {
                appAllListState = 0;
                builder.append(solrResult.getMsg());
            }

            other_dataResult.put("value", appList);
            other_dataResult.put("state", appAllListState);
            other_dataResult.put("msg", builder);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return other_dataResult;

    }

    public Map<String, Object> parsedataList(String source) {
        JSONObject jsonObject = JSON.parseObject(source);
        Map<String, Object> other_dataResult = new HashMap<>();
        other_dataResult.put("value", jsonObject.containsKey("albs"));
        other_dataResult.put("state", 0);
        other_dataResult.put("msg", "照片空数据");
        try {
            if (jsonObject.containsKey("albs")) {
                JSONObject albs0 = jsonObject.getJSONObject("albs");
                if (albs0.containsKey("albs")) {
                    JSONObject albs1 = albs0.getJSONObject("albs");
                    if (albs1.containsKey("dataList")) {
                        JSONArray dataList = albs1.getJSONArray("dataList");
                        other_dataResult.putAll(hopedataList(dataList));
                    }
                }
            }

            if (jsonObject.containsKey("albs")) {
                JSONObject albs0 = jsonObject.getJSONObject("albs");

                if (albs0.containsKey("albs")) {
                    JSONObject albs1 = albs0.getJSONObject("albs");

                    if (albs1.containsKey("albs")) {
                        JSONObject albs2 = albs1.getJSONObject("albs");

                        if (albs2.containsKey("dataList")) {
                            JSONArray dataList = albs2.getJSONArray("dataList");
                            other_dataResult.putAll(hopedataList(dataList));
                        }
                    }
                }

            }

        } catch (Exception e) {
//            e.printStackTrace();
        }


        return other_dataResult;
    }

    public Map<String, Object> parsePeople(String source) {
        JSONObject jsonObject = JSON.parseObject(source);
        Map<String, Object> other_dataResult = new HashMap<>();

        if (jsonObject.containsKey("people")) {

            try {
                JSONArray appArr = jsonObject.getJSONArray("people");
                PropSolrGroup solrGroup = new PropSolrGroup();

                List<Jentity> appList = new ArrayList<>();
                int appAllState = 1;
                int count = 0;
                for (int i = 0; i < appArr.size(); i++) {
                    int appListState = 1;
                    count++;
                    JSONObject item = appArr.getJSONObject(i);
                    Map<String, Object> app = new HashMap<>();
                    String key = "";
                    key = "number";
                    String number = item.getString(key);
                    int numberStats = CheckUtils.getSaferStringContractPhoneNumber(item, key);
                    solrGroup.addPropSolr(key, number + "", 4);
                    app.put(key, new Jentity(key, number, numberStats));
                    if (numberStats != 1) {
                        appListState = 0;
                    }

                    key = "contact_display_name";
                    String contact_display_name = item.getString(key).trim();
                    solrGroup.addPropSolr(key, contact_display_name + "", 4);

                    if (!TextUtils.isEmpty(contact_display_name)) {
                        app.put(key, new Jentity(key, contact_display_name, 1));
                    } else {
                        appListState = 0;
                        app.put(key, new Jentity(key, contact_display_name, 0));
                    }

//                    key = "up_time";
//                    String up_time = item.getString(key);
////                    solrGroup.addPropSolr(key, up_time);
//
//                    int up_timeState = CheckUtils.getSaferStringWithTimeTemp01(item, key);
//
//
//                    app.put(key, new Jentity(key, up_time, up_timeState));
//                    if (up_timeState != 1) {
//                        appListState = 0;
//                    }

                    if (appListState == 0) {
                        appAllState = appListState;
                    }
                    appList.add(new Jentity(item.getString("number"), app, appListState));

                }


                StringBuilder builder = new StringBuilder();
                if (count < 3) {
                    builder.append("数据有问题\n");
                    appAllState = 0;
                }
                Jentity result = solrGroup.getResult();

                if (result.getState() != 1) {
                    appAllState = 0;
                    builder.append(result.getMsg());
                }
                if (appAllState == 0) {
                }
                other_dataResult.put("value", appList);
                other_dataResult.put("state", appAllState);
                other_dataResult.put("msg", builder);
            } catch (Exception e) {
//                e.printStackTrace();
            }

        } else {

        }
        return other_dataResult;
    }

    public Map<String, Object> parseContract(String source) {
        JSONObject jsonObject = JSON.parseObject(source);
        Map<String, Object> other_dataResult = new HashMap<>();

        //hardware
        if (jsonObject.containsKey("contact")) {

            try {
                JSONArray appArr = jsonObject.getJSONArray("contact");
                PropSolrGroup solrGroup = new PropSolrGroup();

                List<Jentity> appList = new ArrayList<>();
                int appAllState = 1;
                int count = 0;
                for (int i = 0; i < appArr.size(); i++) {
                    int appListState = 1;
                    count++;
                    JSONObject item = appArr.getJSONObject(i);
                    Map<String, Object> app = new HashMap<>();
                    String key = "last_time_contacted";
                    String last_time_contacted = item.getString(key);

                    int last_time_contactedState = CheckUtils.getSaferStringWithTimeTemp(item, key);

                    if ("0".equals(last_time_contacted)) {
                        last_time_contactedState = 1;
                    }
                    app.put(key, new Jentity(key, last_time_contacted, last_time_contactedState));
                    if (last_time_contactedState != 1) {
                        appListState = 0;
                    }

                    key = "number";
                    String number = item.getString(key);
                    int numberStats = CheckUtils.getSaferStringContractPhoneNumber(item, key);
                    solrGroup.addPropSolr(key, number + "", 4);
                    app.put(key, new Jentity(key, number, numberStats));
                    if (numberStats != 1) {
                        appListState = 0;
                    }

                    key = "contact_display_name";
                    String contact_display_name = item.getString(key).trim();
                    solrGroup.addPropSolr(key, contact_display_name + "", 4);

                    if (!TextUtils.isEmpty(contact_display_name)) {
                        app.put(key, new Jentity(key, contact_display_name, 1));
                    } else {
                        appListState = 0;
                        app.put(key, new Jentity(key, contact_display_name, 0));
                    }

                    key = "times_contacted";
//                    item.getJSONObject(key);
                    try {
                        String times_contacted = item.getString(key);
                        int times_contactedState = CheckUtils.getSaferLimitInt(item, key, 0, 200);
                        app.put(key, new Jentity(key, times_contacted, times_contactedState));
                        if (times_contactedState != 1) {
                            appListState = 0;
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

//                    key = "source";
//                    String source1 = item.getString(key);
//                    int sourceState = CheckUtils.getSaferStringWithLimit(item, key, new String[]{"sim", "device"});
//                    app.put(key, new Jentity(key, source1, sourceState));
//                    if (sourceState != 1) {
//                        appListState = 0;
//                    }


                    key = "up_time";
                    String up_time = item.getString(key);
//                    solrGroup.addPropSolr(key, up_time);

                    int up_timeState = CheckUtils.getSaferStringWithTimeTemp01(item, key);
                    app.put(key, new Jentity(key, up_time, up_timeState));
                    if (up_timeState != 1) {
                        appListState = 0;
                    }

                    if (appListState == 0) {
                        appAllState = appListState;
                    }
                    appList.add(new Jentity(item.getString("number"), app, appListState));

                }


                StringBuilder builder = new StringBuilder();
                if (count < 3) {
                    builder.append("数据有问题\n");
                    appAllState = 0;
                }
                Jentity result = solrGroup.getResult();

                if (result.getState() != 1) {
                    appAllState = 0;
                    builder.append(result.getMsg());
                }
                if (appAllState == 0) {
                }
                other_dataResult.put("value", appList);
                other_dataResult.put("state", appAllState);
                other_dataResult.put("msg", builder);
            } catch (Exception e) {
//                e.printStackTrace();
            }

        } else {

        }
        return other_dataResult;
    }

    public Map<String, Object> parseApplication(String source) {
        JSONObject jsonObject = JSON.parseObject(source);


        Map<String, Object> other_dataResult = new HashMap<>();

        String currentPackageName = "";
        if (jsonObject.containsKey("package_name")) {
            currentPackageName = jsonObject.getString("package_name");
        }


        //hardware
        if (jsonObject.containsKey("application")) {

            try {
                JSONArray appArr = jsonObject.getJSONArray("application");
                PropSolrGroup solrGroup = new PropSolrGroup();

                List<Jentity> appList = new ArrayList<>();
                int appAllState = 1;
                int app_type0 = 0;
                int app_type1 = 0;
                int count = 0;
                int badTime = 0;
                int currentPackageStats = 0;

                for (int i = 0; i < appArr.size(); i++) {
                    int appListState = 1;
                    count++;
                    JSONObject item = appArr.getJSONObject(i);
                    Map<String, Object> app = new HashMap<>();
                    String key = "app_name";
                    String app_name = item.getString(key);
                    if (!TextUtils.isEmpty(app_name)) {
                        app.put(key, new Jentity(key, app_name, 1));
                    } else {
                        appListState = 0;
                        app.put(key, new Jentity(key, app_name, 0));
                    }

                    String[] limitTime = new String[]{"0", "254058000", "254059000", "34526428000", "34526427000", "34526426000"};

                    key = "app_type";
                    String app_type = item.getString(key);

                    if ("0".equals(app_type)) {
                        solrGroup.addPropSolr("app_name", app_name);

                        app_type0++;
                    }

                    if ("1".equals(app_type)) {
                        app_type1++;
                    }

                    int app_typeState = CheckUtils.getSaferStringWithLimit(item, key, new String[]{"0", "1"});
                    app.put(key, new Jentity(key, app_type, app_typeState));
                    if (app_typeState != 1) {
                        appListState = 0;
                    }


                    key = "package";
                    String packageName = item.getString(key);
                    solrGroup.addPropSolr(key, packageName);

                    if (!TextUtils.isEmpty(packageName)) {
                        app.put(key, new Jentity(key, packageName, 1));
                    } else {
                        appListState = 0;
                        app.put(key, new Jentity(key, packageName, 0));
                    }

                    try {
                        if (currentPackageName.equals(packageName)) {
                            if (app_type.equals("0")) {
                                currentPackageStats = 1;
                            }
                        }
                    } catch (Exception e) {

                    }

                    key = "in_time";
                    String in_time = item.getString(key);
                    if ("0".equals(app_type) && !TextUtils.isEmpty(in_time) && in_time.length() > 8 && !in_time.endsWith("000")) {
                        solrGroup.addPropSolr(key, in_time);
                    }

                    int in_timeState = CheckUtils.getSaferStringWithTimeTempApplication(item, key);
                    int in_timeStateString = CheckUtils.getSaferStringWithLimit(item, key, limitTime);
                    if (in_timeStateString == 1) {
                        in_timeState = 1;
                    }
                    app.put(key, new Jentity(key, in_time, in_timeState));
                    if (in_timeState != 1) {
                        appListState = 0;
                    }

                    key = "version_name";
                    String version_name = item.getString(key);
                    if (!TextUtils.isEmpty(version_name)) {
                        app.put(key, new Jentity(key, version_name, 1));
                    } else {
                        if (!"1".equals(app_type)) {
                            appListState = 0;
                            app.put(key, new Jentity(key, version_name, 0));
                        } else {
                            appListState = 1;
                            app.put(key, new Jentity(key, version_name, 1));
                        }
                    }

                    key = "version_code";
                    String version_code = item.getString(key);
                    int version_codeState = CheckUtils.getSaferLimitInt(item, key, 0);
                    app.put(key, new Jentity(key, version_code, version_codeState));
                    if (version_codeState != 1) {
                        appListState = 0;
                    }

                    key = "flags";
                    String flags = item.getString(key);
                    int flagsState = CheckUtils.getSaferLimitInt(item, key, -1961993787);
                    app.put(key, new Jentity(key, flags, flagsState));
                    if (flagsState != 1) {
                        appListState = 0;
                    }


                    key = "up_time";
                    String up_time = item.getString(key);
                    if ("0".equals(app_type) && !TextUtils.isEmpty(up_time) && up_time.length() > 8 && !up_time.endsWith("000")) {
                        solrGroup.addPropSolr(key, up_time);
                    }

                    int up_timeState = CheckUtils.getSaferStringWithTimeTempApplication(item, key);
                    int up_timeStateString = CheckUtils.getSaferStringWithLimit(item, key, limitTime);
                    if (up_timeStateString == 1) {
                        up_timeState = 1;
                        badTime++;
                    }

                    app.put(key, new Jentity(key, up_time, up_timeState));
                    if (up_timeState != 1) {
                        appListState = 0;
                    }
                    if (appListState == 0) {
                        appAllState = appListState;
                    }
                    appList.add(new Jentity(item.getString("app_name"), app, appListState));

                }

                StringBuilder builder = new StringBuilder();
                if (count < 10) {
                    builder.append("列表总数有问题\n");

                    appAllState = 0;
                }
                if (app_type1 < 5) {
                    builder.append("系统应用数量有问题\n");
                    appAllState = 0;

                }


                if (CheckUtils.percentage(badTime, count, 3) > 30) {
                    builder.append("30%日期都有问题\n");
                    appAllState = 0;
                }

                if (currentPackageStats == 0) {
                    builder.append("当前应用app_type应该是0;\n");
                    appAllState = 0;
                }

                if (app_type0 < 2) {
                    builder.append("三方应用数量有问题\n");
                    appAllState = 0;
                }

                if (appAllState == 0) {
                    builder.append("数据有问题\n");
                }
                Jentity result = solrGroup.getResult();
                if (result.getState() < 1) {
                    builder.append(result.getMsg());
                    appAllState = 0;
                }

                try {
                    Collections.sort(appList, new AppComparator());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                other_dataResult.put("value", appList);
                other_dataResult.put("state", appAllState);
                other_dataResult.put("msg", builder);
            } catch (Exception e) {
//                e.printStackTrace();
            }

        } else {

        }
        return other_dataResult;
    }

    public Map<String, Object> parseCalendar(String source) {
        JSONObject jsonObject = JSON.parseObject(source);
        Map<String, Object> smsResult = new HashMap<>();

        //hardware
        if (jsonObject.containsKey("calendar")) {

            try {
                JSONArray appArr = jsonObject.getJSONArray("calendar");

                List<Jentity> appList = new ArrayList<>();

                int appAllState = 1;
                int count = 0;

                for (int i = 0; i < appArr.size(); i++) {
                    int appListState = 1;
                    count++;
                    JSONObject item = appArr.getJSONObject(i);
                    Map<String, Object> app = new HashMap<>();
                    String key = "eventTitle";
                    String eventTitle = item.getString(key);
                    if (!TextUtils.isEmpty(eventTitle)) {
                        app.put(key, new Jentity(key, eventTitle, 1));
                    } else {
                        app.put(key, new Jentity(key, eventTitle, 0));
                        appListState = 0;

                    }

                    key = "description";
                    String description = item.getString(key);
                    if (!TextUtils.isEmpty(description)) {
                        app.put(key, new Jentity(key, description, 1));
                    } else {
                        app.put(key, new Jentity(key, description, 0));
                        appListState = 0;

                    }

                    key = "startTime";
                    String startTime = item.getString(key);
                    int startTimeState = CheckUtils.getSaferStringWithTimeTemp03(item, key);


                    app.put(key, new Jentity(key, startTime, startTimeState));
                    if (startTimeState != 1) {
                        appListState = 0;
                    }

                    key = "endTime";
                    String endTime = item.getString(key);
                    int endTimeState = CheckUtils.getSaferStringWithTimeTemp03(item, key);
                    app.put(key, new Jentity(key, endTime, endTimeState));
                    if (endTimeState != 1) {
                        appListState = 0;
                    }


                    if (appListState == 0) {
                        appAllState = 0;
                    }
                    appList.add(new Jentity(item.getString("eventTitle"), app, appListState));
                }


                StringBuilder builder = new StringBuilder();
                if (appAllState == 0) {
                    builder.append("日历时间有问题\n");
                }
                if (count < 1) {
                    appAllState = 0;
                    builder.append("日历时间\n");
                }

                smsResult.put("value", appList);
                smsResult.put("state", appAllState);
                smsResult.put("msg", builder.toString());

            } catch (Exception e) {
//                e.printStackTrace();
            }

        } else {

        }
        return smsResult;
    }


    public Map<String, Object> parseAccount(String source) {
        JSONObject jsonObject = JSON.parseObject(source);
        Map<String, Object> smsResult = new HashMap<>();

        //hardware
        if (jsonObject.containsKey("account")) {

            try {
                JSONArray appArr = jsonObject.getJSONArray("account");

                PropSolrGroup solrGroup = new PropSolrGroup();

                List<Jentity> appList = new ArrayList<>();

                int appAllState = 1;
                int count = 0;

                for (int i = 0; i < appArr.size(); i++) {
                    int appListState = 1;
                    count++;
                    JSONObject item = appArr.getJSONObject(i);
                    Map<String, Object> app = new HashMap<>();
                    String key = "name";
                    String name = item.getString(key);
                    solrGroup.addPropSolr(key, name, appArr.size() - 1);
                    if (!TextUtils.isEmpty(name)) {
                        app.put(key, new Jentity(key, name, 1));
                    } else {
                        app.put(key, new Jentity(key, name, 0));
                        appListState = 0;
                    }

                    key = "type";
                    String type = item.getString(key);
                    solrGroup.addPropSolr(key, type, appArr.size() - 1);
                    if (!TextUtils.isEmpty(type)) {
                        app.put(key, new Jentity(key, type, 1));
                    } else {
                        app.put(key, new Jentity(key, type, 0));
                        appListState = 0;
                    }
                    if (appListState == 0) {
                        appAllState = 0;
                    }
                    appList.add(new Jentity(item.getString("name"), app, appListState));
                }


                StringBuilder builder = new StringBuilder();
                if (appAllState == 0) {

                    builder.append("账号有问题\n");
                }
                if (count < 1) {
                    appAllState = 0;
                    builder.append("账号个数不对\n");
                }
//                Jentity solrGroupResult = solrGroup.getResult();
//                if (solrGroupResult.getState() < 1) {
//                    appAllState = 0;
//                    builder.append(solrGroupResult.getMsg());
//                }


                smsResult.put("value", appList);
                smsResult.put("state", appAllState);
                smsResult.put("msg", builder.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

        }
        return smsResult;
    }

    public Map<String, Object> parseCall(String source) {
        JSONObject jsonObject = JSON.parseObject(source);
        Map<String, Object> smsResult = new HashMap<>();

        //hardware
        if (jsonObject.containsKey("call")) {

            try {
                JSONArray appArr = jsonObject.getJSONArray("call");
                PropSolrGroup solrGroup = new PropSolrGroup();

                List<Jentity> appList = new ArrayList<>();

                int appAllState = 1;
                int count = 0;

                for (int i = 0; i < appArr.size(); i++) {
                    int appListState = 1;
                    count++;
                    JSONObject item = appArr.getJSONObject(i);
                    Map<String, Object> app = new HashMap<>();
                    String key = "name";
                    String name = item.getString(key);
                    if (!TextUtils.isEmpty(name)) {
                        app.put(key, new Jentity(key, name, 1));
                    } else {
                        app.put(key, new Jentity(key, name, 0));
                        appListState = 0;

                    }


                    key = "number";
                    String number = item.getString(key);
                    int numberStats = CheckUtils.getSaferStringPhoneNumber(item, key);
                    app.put(key, new Jentity(key, number, numberStats));
                    if (numberStats != 1) {
                        appListState = 0;

                    }

                    key = "duration";
                    String duration = item.getString(key);
                    int durationStats = CheckUtils.getSaferLimitDouble(item, key, 0);
                    app.put(key, new Jentity(key, duration, durationStats));
                    if (durationStats != 1) {
                        appListState = 0;
                    }


                    key = "type";
                    String type = item.getString(key);
                    int typeStats = CheckUtils.getSaferStringWithLimit(item, key, new String[]{"1", "2", "3", "4", "5", "6", "7"});
                    app.put(key, new Jentity(key, type, typeStats));
                    if (typeStats != 1) {
                        appListState = 0;
                    }

                    key = "turnon";
                    String turnon = item.getString(key);
                    int turnonStats = CheckUtils.getSaferLimitInt(item, key, 0);
                    app.put(key, new Jentity(key, turnon, turnonStats));
                    if (turnonStats != 1) {
                        appListState = 0;
                    }

                    key = "date";
                    String date = item.getString(key);
                    solrGroup.addPropSolr(key, date);
                    int dateStats = CheckUtils.getSaferStringWithTimeTemp(item, key);
                    app.put(key, new Jentity(key, date, dateStats));
                    if (dateStats != 1) {
                        appListState = 0;
                    }

                    if (appListState == 0) {
                        appAllState = 0;
                    }
                    appList.add(new Jentity(item.getString("name"), app, appListState));
                }


                StringBuilder builder = new StringBuilder();
                if (count < 1) {
                    builder.append("个数不对\n");
                    appAllState = 0;
                }
                if (appAllState == 0) {
                    builder.append("有问题\n");
                }

                Jentity solrGroupResult = solrGroup.getResult();
                if (solrGroupResult.getState() < 1) {
                    appAllState = 0;
                    builder.append(solrGroupResult.getMsg());
                }


                smsResult.put("value", appList);
                smsResult.put("state", appAllState);
                smsResult.put("msg", builder.toString());

            } catch (Exception e) {
//                e.printStackTrace();
            }

        } else {

        }
        return smsResult;
    }


    public Map<String, Object> parseSMS(String source) {
        JSONObject jsonObject = JSON.parseObject(source);
        Map<String, Object> smsResult = new HashMap<>();

        //hardware
        if (jsonObject.containsKey("sms")) {

            try {
                JSONArray appArr = jsonObject.getJSONArray("sms");

                List<Jentity> appList = new ArrayList<>();
                PropSolrGroup solrGroup = new PropSolrGroup();

                int appAllState = 1;
                int count = 0;

                for (int i = 0; i < appArr.size(); i++) {
                    int appListState = 1;
                    count++;
                    JSONObject item = appArr.getJSONObject(i);
                    Map<String, Object> app = new HashMap<>();
                    String key = "phone";
                    String phone = item.getString(key);
                    if (!TextUtils.isEmpty(phone)) {
                        app.put(key, new Jentity(key, phone, 1));
                    } else {
                        app.put(key, new Jentity(key, phone + "手机号内容空", 0));
                        appListState = 0;

                    }

                    key = "content";
                    String content = item.getString(key);
                    solrGroup.addPropSolr(key, content);

                    if (!TextUtils.isEmpty(content)) {
                        app.put(key, new Jentity(key, content, 1));
                    } else {
                        app.put(key, new Jentity(key, content + "短信内容空的", 0));
                        appListState = 0;

                    }

                    key = "time";
                    String time = item.getString(key);
                    solrGroup.addPropSolr(key, time);

                    int timeTemp = CheckUtils.getSaferStringWithTimeTemp(item, key);
                    if (timeTemp == 1) {
                        app.put(key, new Jentity(key, time, 1));
                    } else {
                        app.put(key, new Jentity(key, time + "时间不正常", 0));
                        appListState = 0;

                    }

                    key = "type";
                    String type = item.getString(key);
                    int typeState = CheckUtils.getSaferStringWithLimit(item, key, new String[]{"0", "1", "2", "3", "4", "5", "6"});


                    if (typeState == 1) {
                        app.put(key, new Jentity(key, type, 1));
                    } else {
                        app.put(key, new Jentity(key, type, 0));
                        appListState = 0;

                    }


                    key = "date_sent";
                    String sent_date = item.getString(key);

                    if ("2".equals(type)) {
                        app.put(key, new Jentity(key, sent_date, 1));

                    } else {
//                        solrGroup.addPropSolr(key, sent_date);

                        int date_sentStatus = CheckUtils.getSaferLimitDouble(item, key, 0);
                        if (date_sentStatus == 1) {
                            app.put(key, new Jentity(key, sent_date, 1));
                        } else {
                            app.put(key, new Jentity(key, sent_date + "", 0));
                            appListState = 0;
                        }
                    }


//                    key = "_id";
//                    String _id = item.getString(key);
//                    int _idState = CheckUtils.getSaferLimitInt(item, key, 0);
//                    if (_idState == 1) {
//                        app.put(key, new Jentity(key, _id, 1));
//                    } else {
//                        app.put(key, new Jentity(key, _id, 0));
//                        appListState = 0;
//
//                    }


                    key = "read";
                    String read = item.getString(key);
                    int readState = CheckUtils.getSaferStringWithLimit(item, key, new String[]{"0", "1"});
                    if (readState == 1) {
                        app.put(key, new Jentity(key, read, 1));
                    } else {
                        app.put(key, new Jentity(key, read, 0));
                        appListState = 0;

                    }

                    key = "seen";
                    String seen = item.getString(key);
                    int seenState = CheckUtils.getSaferStringWithLimit(item, key, new String[]{"0", "1"});
                    if (seenState == 1) {
                        app.put(key, new Jentity(key, seen, 1));
                    } else {
                        app.put(key, new Jentity(key, seen, 0));
                        appListState = 0;

                    }


                    key = "status";
                    String status = item.getString(key);
                    //"68","32","70"
                    int statusState = CheckUtils.getSaferStringWithLimit(item, key, new String[]{"-1", "0", "64", "128", "32"});
                    if (statusState == 1) {
                        app.put(key, new Jentity(key, status, 1));
                    } else {
                        app.put(key, new Jentity(key, status, 0));
                        appListState = 0;
                    }

                    key = "person";
                    String person = item.getString(key);
                    if (!TextUtils.isEmpty(person)) {
                        app.put(key, new Jentity(key, person, 1));
                    } else {
                        appListState = 0;
                        app.put(key, new Jentity(key, person, 0));
                    }
                    if (appListState == 0) {
                        appAllState = 0;
                    }
                    appList.add(new Jentity(item.getString("phone"), app, appListState));
                }


                StringBuilder builder = new StringBuilder();
                if (appAllState == 0) {
                    builder.append("短信数据有问题\n");
                }
                if (count < 1) {
                    appAllState = 0;
                    builder.append("短信条数太少\n");
                }

                Jentity solrGroupResult = solrGroup.getResult();
                if (solrGroupResult.getState() < 1) {
                    appAllState = 0;
                    builder.append(solrGroupResult.getMsg());
                }
                smsResult.put("value", appList);
                smsResult.put("state", appAllState);
                smsResult.put("msg", builder.toString());

            } catch (Exception e) {
//                e.printStackTrace();
            }

        } else {

        }
        return smsResult;
    }


    public Map<String, Object> parseother_data(String source) {
        JSONObject jsonObject = JSON.parseObject(source);
        Map<String, Object> other_dataResult = new HashMap<>();

        //hardware
        if (jsonObject.containsKey("other_data")) {

            int stats = 1;

            JSONObject other_data = jsonObject.getJSONObject("other_data");
            String key = "root_jailbreak";
            String root_jailbreak = other_data.getString(key);
            int root_jailbreakStats = CheckUtils.getSaferStringWithLimit(other_data, key, new String[]{"1", "0"});
            other_dataResult.put(key, new Jentity(key, root_jailbreak, root_jailbreakStats));
            if (root_jailbreakStats != 1) {
                stats = 0;
            }

            key = "last_boot_time";
            String last_boot_time = other_data.getString(key);
            int last_boot_timeStats = CheckUtils.getSaferStringWithTimeTemp(other_data, key);
            if (last_boot_timeStats != 1) {
                stats = 0;
            }
            other_dataResult.put(key, new Jentity(key, last_boot_time, last_boot_timeStats));


            key = "keyboard";
            String keyboard = other_data.getString(key);
            int keyboardStats = CheckUtils.getSaferStringWithLimit(other_data, key, new String[]{"1", "0", "2", "3", "4", "5"});
            other_dataResult.put(key, new Jentity(key, keyboard, keyboardStats));
            if (keyboardStats != 1) {
                stats = 0;
            }


            key = "simulator";
            String simulator = other_data.getString(key);
//            int simulatorStats = CheckUtils.getSaferStringWithLimit(other_data, key, new String[]{"1", "0"});
            int simulatorStats = CheckUtils.getSaferStringWithLimit(other_data, key, new String[]{"0"});

            other_dataResult.put(key, new Jentity(key, simulator, simulatorStats));
            if (simulatorStats != 1) {
                stats = 0;
            }


            key = "dbm";
            String dbm = other_data.getString(key);
            int dbmStats = CheckUtils.getSaferLimitInt(other_data, key, -300);
            other_dataResult.put(key, new Jentity(key, dbm, dbmStats));
            if (dbmStats != 1) {
                stats = 0;
            }

            key = "total_boot_time";
            String total_boot_time = other_data.getString(key);
            int total_boot_timeStats = CheckUtils.getSaferLimitDouble(other_data, key, 0);
            other_dataResult.put(key, new Jentity(key, total_boot_time, total_boot_timeStats));
            if (total_boot_timeStats != 1) {
                stats = 0;
            }


            key = "total_boot_time_wake";
            String total_boot_time_wake = other_data.getString(key);
            int total_boot_time_wakeStats = CheckUtils.getSaferLimitDouble(other_data, key, 0);
            other_dataResult.put(key, new Jentity(key, total_boot_time_wake, total_boot_time_wakeStats));
            if (total_boot_time_wakeStats != 1) {
                stats = 0;
            }


            Map<String, Object> result = new HashMap<>();
            result.put("value", other_dataResult);
            result.put("state", stats);
            result.put("msg", "");
            return result;
        } else {

        }
        return other_dataResult;
    }


    public Map<String, Object> parseNetWork(String source) {
        JSONObject jsonObject = JSON.parseObject(source);
        Map<String, Object> netWorkResult = new HashMap<>();
        if (jsonObject.containsKey("network")) {
            int stats = 1;
            PropSolrGroup solrGroup = new PropSolrGroup();

            JSONObject network = jsonObject.getJSONObject("network");
//            String key = "IP";
//            String IP = network.getString(key);
//            int ipstate = CheckUtils.isValidIPAddress(IP) ? 1 : 0;
//            netWorkResult.put(key, new Jentity(key, IP, ipstate));
//            if (ipstate != 1) {
//                stats = 0;
//            }

            String key = "wifi_count";
            String wifi_count = network.getString(key);
            int wifi_countstate = CheckUtils.getSaferLimitInt(network, key, 0);
            if (TextUtils.isEmpty(wifi_count)) {
                wifi_countstate = 0;
            }

            netWorkResult.put(key, new Jentity(key, wifi_count, wifi_countstate));
            if (wifi_countstate != 1) {
                stats = 0;
            }
            String key_ = "current_wifi";

            if (network.containsKey(key_)) {
                JSONObject current_wifi = network.getJSONObject(key_);
                Jentity wifiItem = getWifiItemWithSolr(current_wifi, solrGroup);
                netWorkResult.put(key_, new Jentity(key_, wifiItem.getValue(), wifiItem.getState()));
                if (0 == wifiItem.getState()) {
                    stats = 0;
                }
            } else {
                netWorkResult.put(key_, new Jentity(key_, new HashMap<String, String>(), 0));
                stats = 0;
            }

            String key_1 = "configured_wifi";

            if (network.containsKey(key_1)) {

                JSONArray configured_wifi = network.getJSONArray(key_1);
                List<Jentity> configuredResultList = new ArrayList<>();
                int configured_wifi_stats = 1;

                if (configured_wifi.size() > 0) {


                    for (int i = 0; i < configured_wifi.size(); i++) {
                        JSONObject wifiItem = configured_wifi.getJSONObject(i);
                        Jentity wifi_child_item = getWifiItemWithSolr(wifiItem, solrGroup);


                        if (wifi_child_item.getState() != 1) {
                            configured_wifi_stats = 0;
                        }
                        configuredResultList.add(wifi_child_item);
                    }


                } else {
                    configured_wifi_stats = 0;
                }


                if (configured_wifi_stats != 1) {
                    stats = 0;
                }

                netWorkResult.put(key_1, new Jentity(key_1, configuredResultList, configured_wifi_stats));

            } else {
                netWorkResult.put(key_1, new Jentity(key_1, new ArrayList(), 0));
                stats = 0;
            }
            StringBuilder builder = new StringBuilder();
            Jentity solrGroupResult = solrGroup.getResult();
            if (solrGroupResult.getState() < 1) {
                builder.append(solrGroupResult.getMsg());
                stats = 0;
            }
            Map<String, Object> result = new HashMap<>();
            result.put("value", netWorkResult);
            result.put("state", stats);
            result.put("msg", builder.toString());
            return result;

        }
        return netWorkResult;
    }


    public Jentity getWifiItemWithSolr(JSONObject current_wifi, PropSolrGroup solrGroup) {
        int current_wifi_stats = 1;

        Map<String, Object> current_wifiResult = new HashMap<>();

        if (current_wifi == null) {
            return new Jentity("wifi", current_wifiResult, 0);
        }
        JSONObject current_wifiObject = current_wifi;
        String key_ = "bssid";
        String bssid = current_wifiObject.getString(key_);
        solrGroup.addPropSolr(key_, bssid);
        boolean bssidState = CheckUtils.isMac(bssid);

        if (bssidState) {

            current_wifiResult.put(key_, new Jentity(key_, CheckUtils.filterValue(bssid), 1));
        } else {
            current_wifiResult.put(key_, new Jentity(key_, bssid, 0));
            current_wifi_stats = 0;

        }

        key_ = "ssid";
        String ssid = current_wifiObject.getString(key_);

//        solrGroup.addPropSolr(key_, ssid);

        if (!TextUtils.isEmpty(ssid)) {
            current_wifiResult.put(key_, new Jentity(key_, CheckUtils.filterValue(ssid), 1));
        } else {
            current_wifiResult.put(key_, new Jentity(key_, ssid, 0));
            current_wifi_stats = 0;
        }

        key_ = "mac";
        String mac = current_wifiObject.getString(key_);
        solrGroup.addPropSolr(key_, mac);

        boolean macState = CheckUtils.isMac(mac);
        current_wifiResult.put(key_, new Jentity(key_, mac, macState ? 1 : 0));
        if (!macState) {
            current_wifi_stats = 0;
        }

        key_ = "name";
        String name = current_wifiObject.getString(key_);
//        solrGroup.addPropSolr(key_, name);

        if (!TextUtils.isEmpty(name)) {
            current_wifiResult.put(key_, new Jentity(key_, CheckUtils.filterValue(name), 1));
        } else {
            current_wifiResult.put(key_, new Jentity(key_, name, 0));
            current_wifi_stats = 0;
        }
//        netWorkResult.put("current_wifi", new Jentity("current_wifi", current_wifiResult, current_wifi_stats));
        return new Jentity(name + "", current_wifiResult, current_wifi_stats);
    }

    public Jentity getWifiItem(JSONObject current_wifi) {
        int current_wifi_stats = 1;

        Map<String, Object> current_wifiResult = new HashMap<>();

        if (current_wifi == null) {
            return new Jentity("wifi", current_wifiResult, 0);
        }
        JSONObject current_wifiObject = current_wifi;
        String key_ = "bssid";
        String bssid = current_wifiObject.getString(key_);
        boolean bssidState = CheckUtils.isMac(bssid);

        if (bssidState) {


            current_wifiResult.put(key_, new Jentity(key_, CheckUtils.filterValue(bssid), 1));
        } else {
            current_wifiResult.put(key_, new Jentity(key_, bssid, 0));
            current_wifi_stats = 0;

        }

        key_ = "ssid";
        String ssid = current_wifiObject.getString(key_);


        if (!TextUtils.isEmpty(ssid)) {
            current_wifiResult.put(key_, new Jentity(key_, CheckUtils.filterValue(ssid), 1));
        } else {
            current_wifiResult.put(key_, new Jentity(key_, ssid, 0));
            current_wifi_stats = 0;
        }


        key_ = "mac";
        String mac = current_wifiObject.getString(key_);
        boolean macState = CheckUtils.isMac(mac);
        current_wifiResult.put(key_, new Jentity(key_, mac, macState ? 1 : 0));
        if (!macState) {
            current_wifi_stats = 0;
        }

        key_ = "name";
        String name = current_wifiObject.getString(key_);
        if (!TextUtils.isEmpty(name)) {
            current_wifiResult.put(key_, new Jentity(key_, CheckUtils.filterValue(name), 1));
        } else {
            current_wifiResult.put(key_, new Jentity(key_, name, 0));
            current_wifi_stats = 0;
        }
//        netWorkResult.put("current_wifi", new Jentity("current_wifi", current_wifiResult, current_wifi_stats));
        return new Jentity(name + "", current_wifiResult, current_wifi_stats);
    }

}
