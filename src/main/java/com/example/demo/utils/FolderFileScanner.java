package com.example.demo.utils;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * @author 邪恶小先生
 */
public class FolderFileScanner {


    public static final String[] PACKAGE_KILL_LIST = new String[]{
            "/com/umeng/",//友盟
            "/com/tencent/",//腾讯库
            "/com/alibaba/sdk/android/",//阿里云
            "/com/baidu/",//百度定位
            "/com/amap/api",//高德定位
            "/me/jessyan/autosize",//头条适配
            "/com/alibaba/android/arouter",//router
            "/com/meituan/",//美团
            "/com/didi/",//滴滴
            "/io/branch/"
    };


    public static final String[] PACKAGE_SHOULD_LIST = new String[]{
            "com/google/firebase/crashlytics",
            "com/google/android/gms/location",
            "com/android/installreferrer",
//            "com/google/android/play/core/review"
    };

    public static final String[] STRING_SHOULD_LIST = new String[]{
            "com.google.firebase.crashlytics.mapping_file_id",
            "google_app_id",
            "google_crash_reporting_api_key",
            "firebase_database_url"
    };


    public static ArrayList<Object> scanFiles = new ArrayList<Object>();

    /**
     * linkedList实现
     **/
    public static LinkedList<File> queueFiles = new LinkedList<File>();


    public static String getRealNamePage(String packageLine) {
        packageLine = packageLine.replace(".smali", "");
        String[] packageList = packageLine.split("/smal");
        String resName = packageList[1];

        int i = resName.indexOf("/");
        resName = resName.substring(i);
        String[] split = resName.split("/");
        List<String> list = Arrays.asList(split);
        List<String> myList = new ArrayList<>(list);
        int size = myList.size();
        if (myList.size() > 2) {
            myList.remove(size - 1);
        }
        String result = String.join(".", myList);
        return result + ".**";
    }


    public static ArrayList<Object> startScanFilesWithRecursion(String folderPath) {
        scanFiles.clear();
        queueFiles.clear();
        ArrayList<Object> objects = scanFilesWithRecursion(folderPath);
        return objects;
    }

    /**
     * TODO:递归扫描指定文件夹下面的指定文件
     *
     * @return ArrayList<Object>
     * @author 邪恶小先生（LQ）
     * @time 2017年11月3日
     */
    public static ArrayList<Object> scanFilesWithRecursion(String folderPath) {

        ArrayList<String> dirctorys = new ArrayList<String>();
        File directory = new File(folderPath);
        if (!directory.isDirectory()) {
//            throw new Exception('"' + folderPath + '"' + " input path is not a Directory , please input the right path of the Directory. ^_^...^_^");
        }
        if (directory.isDirectory()) {
            File[] filelist = directory.listFiles();
            for (int i = 0; i < filelist.length; i++) {
                /**如果当前是文件夹，进入递归扫描文件夹**/
                if (filelist[i].isDirectory()) {
                    dirctorys.add(filelist[i].getAbsolutePath());
                    /**递归扫描下面的文件夹**/
                    scanFilesWithRecursion(filelist[i].getAbsolutePath());
                }
                /**非文件夹**/
                else {
                    scanFiles.add(filelist[i].getAbsolutePath());
                }
            }
        }
        return scanFiles;
    }

    /**
     * TODO:非递归方式扫描指定文件夹下面的所有文件
     *
     * @param folderPath 需要进行文件扫描的文件夹路径
     * @return ArrayList<Object>
     * @author 邪恶小先生（LQ）
     * @time 2017年11月3日
     */
    public static ArrayList<Object> scanFilesWithNoRecursion(String folderPath) {
        File directory = new File(folderPath);
        if (!directory.isDirectory()) {
//            throw new ScanFilesException('"' + folderPath + '"' + " input path is not a Directory , please input the right path of the Directory. ^_^...^_^");
        } else {
            //首先将第一层目录扫描一遍
            File[] files = directory.listFiles();
            //遍历扫出的文件数组，如果是文件夹，将其放入到linkedList中稍后处理
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    queueFiles.add(files[i]);
                } else {
                    //暂时将文件名放入scanFiles中
                    scanFiles.add(files[i].getAbsolutePath());
                }
            }

            //如果linkedList非空遍历linkedList
            while (!queueFiles.isEmpty()) {
                //移出linkedList中的第一个
                File headDirectory = queueFiles.removeFirst();
                File[] currentFiles = headDirectory.listFiles();
                for (int j = 0; j < currentFiles.length; j++) {
                    if (currentFiles[j].isDirectory()) {
                        //如果仍然是文件夹，将其放入linkedList中
                        queueFiles.add(currentFiles[j]);
                    } else {
                        scanFiles.add(currentFiles[j].getAbsolutePath());
                    }
                }
            }
        }

        return scanFiles;
    }
}