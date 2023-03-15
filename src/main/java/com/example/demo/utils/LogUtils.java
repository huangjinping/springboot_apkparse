package com.example.demo.utils;

import com.google.gson.Gson;

public class LogUtils {

    public static void log(Object msg) {
        System.out.println(msg);
    }

    public static void logJson(Object msg) {


        if (msg instanceof String) {
            System.out.println(msg);
        } else {
            Gson gson = new Gson();
            System.out.println(gson.toJson(msg));
        }

    }
}
