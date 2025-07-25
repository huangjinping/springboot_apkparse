package com.example.demo.utils;

import com.example.demo.bean.CommonModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadSearchQ {


    public Map<String, Object> parseData(String outFilePath, String apkPath) {
        Map<String, Object> result = new HashMap<>();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 50, 4, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.CallerRunsPolicy());

        int poolLength = 11;//需要用的线程个数

        CountDownLatch countDownLatch = new CountDownLatch(poolLength);

        List<CommonModel> searchField = new ArrayList<>();
//        for (int i = 0; i < poolLength; i++) {
//            final int index = i;
//            threadPoolExecutor.execute(new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        StringTask stringTask = new StringTask();
//                        List<CommonModel> strings = stringTask.searchLogs(outFilePath, StringTask.getLenIndex(StringTask.getSearchLog5, poolLength, index));
//                        searchField.addAll(strings);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    countDownLatch.countDown();
//                }
//            }));
//        }

        threadPoolExecutor.execute(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<String> commands = new ArrayList<>();
//                LogUtils.logJson("======resultFile00========" + resultFile.getName());
                    commands.add("./json/veridex/appcompat.sh --dex-file=" + apkPath);
                    List<String> strings = CommandLineTool.executeNewFlow(commands);


                    for (String item : strings) {
                        LogUtils.logJson("ThreadSearchQ   " + item);
                    }

//                    StringTask stringTask = new StringTask();
//                    List<CommonModel> strings = stringTask.searchLogs(outFilePath, StringTask.getLenIndex(StringTask.getSearchLog5, poolLength, index));
//                    searchField.addAll(strings);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }
        }));

        result.put("searchJson", searchField);
        try {
            // 让当前线程处于阻塞状态，直到锁存器计数为零
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new NullPointerException(e.getMessage());
        }
        return result;
    }


}
