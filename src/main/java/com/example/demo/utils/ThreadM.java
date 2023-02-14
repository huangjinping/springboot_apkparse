package com.example.demo.utils;

import org.dom4j.DocumentException;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//https://www.cnblogs.com/skyfreedom/p/16417792.html
public class ThreadM {


    public ThreadM() {
    }


    public Map<String, Object> parseApkData(String apktoolPath, String apkFastPath, String outFilePath, String appType) {
        Map<String, Object> result = new HashMap<>();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 50, 4, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.CallerRunsPolicy());


        int poolLength = 5;//需要用的线程个数

        CountDownLatch countDownLatch = new CountDownLatch(poolLength);


        threadPoolExecutor.execute(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    File file = new File(apktoolPath);
                    Map<String, Object> aapt = ManiParse.parseAndroidApk(file.getParentFile().getAbsolutePath() + "/aapt", apkFastPath);
                    result.putAll(aapt);

                    Map<String, Object> map = ManiParse.parseAndroidManifest(outFilePath + "/AndroidManifest.xml", appType);
                    result.putAll(map);


                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                // 任务个数 - 1, 直至为0时唤醒await()
                countDownLatch.countDown();
            }
        }));

        threadPoolExecutor.execute(new Thread(new Runnable() {
            @Override
            public void run() {
                try {


                    Map<String, Object> stringObjectMap = ManiParse.parseMethod(outFilePath);
                    result.putAll(stringObjectMap);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 任务个数 - 1, 直至为0时唤醒await()
                countDownLatch.countDown();
            }
        }));

        threadPoolExecutor.execute(new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Map<String, Object> parsePackage = ManiParse.parsePackage(outFilePath);
                    result.putAll(parsePackage);


                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 任务个数 - 1, 直至为0时唤醒await()
                countDownLatch.countDown();
            }
        }));

        threadPoolExecutor.execute(new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Map<String, Object> domainName = ManiParse.parseDomainName(outFilePath);
                    result.putAll(domainName);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 任务个数 - 1, 直至为0时唤醒await()
                countDownLatch.countDown();
            }
        }));

        threadPoolExecutor.execute(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Map<String, Object> res = new HashMap<>();
                    Map<String, String> stringObjectMap = ManiParse.parseStringXML(outFilePath + "/res/values/strings.xml", "0");
                    res.put("Strings", stringObjectMap);
                    result.putAll(res);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 任务个数 - 1, 直至为0时唤醒await()
                countDownLatch.countDown();
            }
        }));

        try {
            // 让当前线程处于阻塞状态，直到锁存器计数为零
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new NullPointerException(e.getMessage());
        }

        return result;
    }


    public void threadMethod() {


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 50, 4, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.CallerRunsPolicy());

        String[] threads = {"", "", "", "", "", "", "", ""};
        CountDownLatch countDownLatch = new CountDownLatch(threads.length);

        for (String item : threads) {
            threadPoolExecutor.execute(new Thread(new Runnable() {
                @Override
                public void run() {
                    // 任务个数 - 1, 直至为0时唤醒await()
                    countDownLatch.countDown();
                }
            }));


        }


        try {
            // 让当前线程处于阻塞状态，直到锁存器计数为零
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new NullPointerException(e.getMessage());
        }


    }
}
