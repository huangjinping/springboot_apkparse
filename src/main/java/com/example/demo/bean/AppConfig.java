package com.example.demo.bean;

public interface AppConfig {


    interface AppType {
        String TYPE_RELEASE = "0";
        String TYPE_DEBUG1 = "1";
        String TYPE_RELEASE531 = "2";
        String TYPE_DEBUG531 = "3";

    }


    interface PackageLimit {
        long native_limit = 10 * 1024 * 1024;
        long OTHER_limit = 20 * 1024 * 1024;
    }


    interface MethodTarget {
        String onReceivedSslError = "onReceivedSslError";
        String getLine1Number = "getLine1Number";
        String WebSettings = "WebSettings";

    }

    interface APK_INFO {
        String VERSION_NAME = "versionName";
        String VERSION_CODE = "versionCode";
    }

}
