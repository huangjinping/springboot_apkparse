package com.example.demo.utils;

import com.example.demo.bean.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class UserManager {


    public static void main(String[] args) {

        List<User> beijing = new ArrayList<>();
        beijing.add(new User(0, "liukai", 1));
        beijing.add(new User(1, "wuqi", 1));
        beijing.add(new User(2, "liujing", 1));
        beijing.add(new User(3, "yuanchao", 1));
        beijing.add(new User(4, "zhangrongsheng", 1));
        beijing.add(new User(5, "huangjinping", 1));
        beijing.add(new User(6, "zhangmeng", 1));
        beijing.add(new User(7, "", 1));
        beijing.add(new User(8, "", 1));
        beijing.add(new User(9, "", 1));

        List<User> ceshi = new ArrayList<>();
        ceshi.add(new User(100, "wenyue", 1));
        ceshi.add(new User(101, "chenmeng", 1));
        ceshi.add(new User(102, "wangxiaocui", 1));
        ceshi.add(new User(103, "fengzhen", 1));
        ceshi.add(new User(104, "ceshi1", 1));
        ceshi.add(new User(105, "ceshi2", 1));
        ceshi.add(new User(106, "ceshi3", 1));
        ceshi.add(new User(107, "ceshi4", 1));
        ceshi.add(new User(108, "ceshi4", 1));
        ceshi.add(new User(109, "ceshi4", 1));


        List<User> chengdu = new ArrayList<>();
        chengdu.add(new User(201, "liuxiaofeng", 1));
        chengdu.add(new User(202, "zhangning", 1));
        chengdu.add(new User(203, "wangcheng", 1));
        chengdu.add(new User(204, "yuandanzhi", 1));
        chengdu.add(new User(205, "konglingfeng", 1));
        chengdu.add(new User(206, "chengyijia", 1));
        chengdu.add(new User(207, "wangshifu", 1));
        chengdu.add(new User(208, "kaifa2", 1));
        chengdu.add(new User(209, "kaifa3", 1));

        List<User> jianzhi = new ArrayList<>();
        jianzhi.add(new User(300, "ec_475", 1));
        jianzhi.add(new User(301, "ec_481", 1));
        jianzhi.add(new User(302, "ec_482", 1));
        jianzhi.add(new User(303, "ec_493", 1));
        jianzhi.add(new User(304, "ec_494", 1));
        jianzhi.add(new User(305, "ec_495", 1));
        jianzhi.add(new User(306, "ec_484", 1));
        jianzhi.add(new User(307, "", 1));
        jianzhi.add(new User(308, "", 1));
        jianzhi.add(new User(309, "", 1));

        String global_Key = "global";
        String boss_key = "0121170eedf910c65bf10b2cf5820200";
        List<User> allList = new ArrayList<>();
        allList.addAll(beijing);
        allList.addAll(ceshi);
        allList.addAll(chengdu);
        allList.addAll(jianzhi);

        for (int i = 0; i < allList.size(); i++) {
            User user = allList.get(i);
            user.setName(global_Key + "" + user.getId());
            String password = AESUtil.encrypt(global_Key + "" + user.getId(), boss_key).substring(0, 6);
//            user.setPassword(MD5Util.md5(password));
            user.setPassword(password);
        }
        Gson gson = new Gson();
        System.out.println(gson.toJson(allList));
    }
}
