package com.example.demo.utils;

public class PermissionUtils {


    public static final String[] permissionKillList = new String[]{
            "android.permission.MANAGE_EXTERNAL_STORAGE",
            "android.permission.CALL_PHONE",
            "android.permission.RECEIVE_SMS",
            "android.permission.WRITE_CONTACTS",
            "android.permission.QUERY_ALL_PACKAGES",
            "android.permission.ACCESS_BACKGROUND_LOCATION",
            "android.permission.REQUEST_INSTALL_PACKAGES"
    };


    public static final String[] permissionsReleaseMast = new String[]{
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.ACCESS_NETWORK_STATE",
            "android.permission.ACCESS_WIFI_STATE",
            "android.permission.READ_PHONE_STATE",
            "android.permission.CAMERA",
            "android.permission.READ_CONTACTS",
            "android.permission.READ_SMS",
            "android.permission.READ_CALENDAR",
            "android.permission.WRITE_CALENDAR",
            "android.permission.ACCESS_COARSE_LOCATION",
            "android.permission.INTERNET",
            "android.permission.GET_ACCOUNTS",
            "android.permission.READ_CALL_LOG",
            "com.google.android.gms.permission.AD_ID"
    };

    public static final String[] permissionsReleaseMast33 = new String[]{
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.ACCESS_NETWORK_STATE",
            "android.permission.ACCESS_WIFI_STATE",
            "android.permission.READ_PHONE_STATE",
            "android.permission.CAMERA",
            "android.permission.READ_CONTACTS",
            "android.permission.READ_SMS",
            "android.permission.READ_CALENDAR",
            "android.permission.WRITE_CALENDAR",
            "android.permission.ACCESS_COARSE_LOCATION",
            "android.permission.INTERNET",
            "android.permission.GET_ACCOUNTS",
            "android.permission.READ_CALL_LOG",
            "com.google.android.gms.permission.AD_ID",
            "android.permission.READ_MEDIA_IMAGES"
    };


    public static final String[] permissionsDebugMast = new String[]{
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.ACCESS_NETWORK_STATE",
            "android.permission.ACCESS_WIFI_STATE",
            "android.permission.READ_PHONE_STATE",
            "android.permission.CAMERA",
            "android.permission.READ_CONTACTS",
//            "android.permission.READ_SMS",
            "android.permission.READ_CALENDAR",
            "android.permission.WRITE_CALENDAR",
            "android.permission.ACCESS_COARSE_LOCATION",
            "android.permission.INTERNET",
            "android.permission.GET_ACCOUNTS",
//            "android.permission.READ_CALL_LOG",
            "com.google.android.gms.permission.AD_ID"
    };


    public static final String[] permissionsAll = new String[]{
            "android.permission.REORDER_TASKS",
            "android.permission.READ_CALL_LOG",
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.ACCESS_NETWORK_STATE",
            "android.permission.ACCESS_WIFI_STATE",
            "android.permission.READ_PHONE_STATE",
            "android.permission.CAMERA",
            "android.permission.READ_CONTACTS",
            "android.permission.READ_SMS",
            "android.permission.READ_CALENDAR",
            "android.permission.WRITE_CALENDAR",
            "android.permission.ACCESS_COARSE_LOCATION",
            "android.permission.INTERNET",
            "android.permission.GET_ACCOUNTS",
            "com.google.android.gms.permission.AD_ID",
            "android.permission.CHANGE_NETWORK_STATE",
            "android.permission.CHANGE_WIFI_STATE",
            "android.permission.FLASHLIGHT",
            "android.permission.MOUNT_UNMOUNT_FILESYSTEMS",
            "android.permission.READ_LOGS",
            "android.permission.RECEIVE_BOOT_COMPLETED",
            "android.permission.VIBRATE",
            "android.permission.WAKE_LOCK",
            "android.permission.WRITE_SETTINGS",
            "android.permission.RECEIVE_USER_PRESENT",
            "android.permission.WAKE_LOCK",
            "android.permission.ACCESS_FINE_LOCATION",
            "com.huawei.android.launcher.permission.CHANGE_BADGE",
            "com.vivo.notification.permission.BADGE_ICON",
            "android.permission.LOCAL_MAC_ADDRESS",
            "android.permission.READ_PRIVILEGED_PHONE_STATE",
            "com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE",
            "com.google.android.c2dm.permission.RECEIVE",
            "android.permission.NEARBY_WIFI_DEVICES",
            "android.permission.READ_MEDIA_AUDIO",
            "android.permission.READ_MEDIA_IMAGES",
            "android.permission.READ_MEDIA_VIDEO",
            "android.permission.POST_NOTIFICATIONS",
            "android.permission.BLUETOOTH",
            "android.permission.GET_TASKS",
            "android.permission.FOREGROUND_SERVICE"


    };


    public static final String[] permissions531All = new String[]{
            "android.permission.REORDER_TASKS",
            "android.permission.READ_CALL_LOG",
//            "android.permission.READ_EXTERNAL_STORAGE",
//            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.ACCESS_NETWORK_STATE",
            "android.permission.ACCESS_WIFI_STATE",
            "android.permission.READ_PHONE_STATE",
            "android.permission.CAMERA",
//            "android.permission.READ_CONTACTS",
            "android.permission.READ_SMS",
            "android.permission.READ_CALENDAR",
            "android.permission.WRITE_CALENDAR",
            "android.permission.ACCESS_COARSE_LOCATION",
            "android.permission.INTERNET",
            "android.permission.GET_ACCOUNTS",
            "com.google.android.gms.permission.AD_ID",
            "android.permission.CHANGE_NETWORK_STATE",
            "android.permission.CHANGE_WIFI_STATE",
            "android.permission.FLASHLIGHT",
            "android.permission.MOUNT_UNMOUNT_FILESYSTEMS",
            "android.permission.READ_LOGS",
            "android.permission.RECEIVE_BOOT_COMPLETED",
            "android.permission.VIBRATE",
            "android.permission.WAKE_LOCK",
            "android.permission.WRITE_SETTINGS",
            "android.permission.WRITE_SETTINGS",
            "android.permission.BLUETOOTH",
            "android.permission.GET_TASKS",

            "android.permission.RECEIVE_USER_PRESENT",
            "android.permission.WAKE_LOCK",
//            "android.permission.ACCESS_FINE_LOCATION",
            "com.huawei.android.launcher.permission.CHANGE_BADGE",
            "com.vivo.notification.permission.BADGE_ICON",
            "android.permission.LOCAL_MAC_ADDRESS",
            "android.permission.READ_PRIVILEGED_PHONE_STATE",
            "com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE",
            "com.google.android.c2dm.permission.RECEIVE",
            "android.permission.NEARBY_WIFI_DEVICES",
            "android.permission.SYSTEM_ALERT_WINDOW",
//            "android.permission.READ_MEDIA_AUDIO",
//            "android.permission.READ_MEDIA_IMAGES",
//            "android.permission.READ_MEDIA_VIDEO",
            "android.permission.POST_NOTIFICATIONS",
            "android.permission.FOREGROUND_SERVICE"


    };

    public static final String[] permissionsReleaseMast531 = new String[]{
//            "android.permission.READ_EXTERNAL_STORAGE",
//            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.ACCESS_NETWORK_STATE",
            "android.permission.ACCESS_WIFI_STATE",
            "android.permission.READ_PHONE_STATE",
            "android.permission.CAMERA",
//            "android.permission.READ_CONTACTS",
            "android.permission.READ_SMS",
            "android.permission.READ_CALENDAR",
            "android.permission.WRITE_CALENDAR",
            "android.permission.ACCESS_COARSE_LOCATION",
            "android.permission.INTERNET",
//            "android.permission.GET_ACCOUNTS",
            "android.permission.READ_CALL_LOG",
            "com.google.android.gms.permission.AD_ID"
    };

    public static final String[] permissionsDebugMast531 = new String[]{
//            "android.permission.READ_EXTERNAL_STORAGE",
//            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.ACCESS_NETWORK_STATE",
            "android.permission.ACCESS_WIFI_STATE",
            "android.permission.READ_PHONE_STATE",
            "android.permission.CAMERA",
//            "android.permission.READ_CONTACTS",
            "android.permission.READ_SMS",
            "android.permission.READ_CALENDAR",
            "android.permission.WRITE_CALENDAR",
            "android.permission.ACCESS_COARSE_LOCATION",
            "android.permission.INTERNET",
//            "android.permission.GET_ACCOUNTS",
//            "android.permission.READ_CALL_LOG",
            "com.google.android.gms.permission.AD_ID"
    };

    public static final String[] permissions2024All = new String[]{
            "android.permission.INTERNET"
    };

    public static final String[] permissions2024DebugMast = new String[]{
            "android.permission.INTERNET"
    };


}
