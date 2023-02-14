package com.example.demo.bean;

public interface AppConfig {


    interface AppType {
        String TYPE_RELEASE = "0";
        String TYPE_DEBUG1 = "1";
        String TYPE_DEBUG2 = "2";

    }


    interface MethodTarget {
        String onReceivedSslError = "onReceivedSslError";
        String getLine1Number = "getLine1Number";
        String WebSettings = "WebSettings";

    }

}
