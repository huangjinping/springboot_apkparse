package com.example.demo;

import com.example.demo.bean.AppPermissions;
import com.example.demo.utils.ManiParse;
import com.example.demo.utils.PermissionUtils;
import com.google.gson.Gson;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;
import org.dom4j.xpath.DefaultXPath;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainTest {
    public static void main(String[] args) {
        System.out.println("vvvv");
        try {
            Map<String, Object> map = ManiParse.parseAndroidManifest("/Users/huhuijie/Documents/GitHub/springboot_apkparse/src/main/resources/static/AndroidManifest.xml");
            Gson gson = new Gson();
            String result = gson.toJson(map);
//            System.out.println(result);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static String getTextByPath(String path) {
        String reader = null;
        BufferedReader br = null;
        File f = new File(path);
        String result = "";
        if (f.exists()) {
            try {
                br = new BufferedReader(new FileReader(f));
                while ((reader = br.readLine()) != null) {
                    result += reader;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
