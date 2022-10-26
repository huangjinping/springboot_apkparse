package com.example.demo;

import com.example.demo.utils.InxSpider;

public class MainTest {
    public static void main(String[] args) {
//        System.out.println("vvvv");

        try {
//            Map<String, Object> map = ManiParse.parseAndroidManifest("/Users/huhuijie/Documents/GitHub/springboot_apkparse/src/main/resources/static/AndroidManifest.xml");
//            Gson gson = new Gson();
//            String result = gson.toJson(map);
//            System.out.println(result);
//            String textByPath = FileUtils.getTextByPath("/Users/huhuijie/Documents/GitHub/springboot_apkparse/json/282483.json");
//            LogUtils.log(5/20);
//            JsonParser.parseRoot(textByPath);

//            LogUtils.log(CheckUtils.percentage(2,10,2));
            InxSpider inxSpider = new InxSpider();
            inxSpider.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
