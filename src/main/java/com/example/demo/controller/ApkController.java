package com.example.demo.controller;

import com.example.demo.bean.ResponseCode;
import com.example.demo.bean.RestResponse;
import com.example.demo.utils.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ApkController {


    @RequestMapping(value = "/searchTaskPath", method = RequestMethod.POST)
    public RestResponse searchTaskPath(@RequestParam("savePos") String savePos) {
        Map<String, Object> resultMap = new HashMap<>();
        ThreadSearchP threadp = new ThreadSearchP();
        Map<String, Object> map = threadp.parseData(savePos);
        resultMap.putAll(map);

        return RestResponse.success(resultMap);
    }

    @RequestMapping(value = "/searchTaskFiled", method = RequestMethod.POST)
    public RestResponse searchTaskFiled(@RequestParam("savePos") String savePos) {
        Map<String, Object> resultMap = new HashMap<>();
        ThreadSearchM threadM = new ThreadSearchM();
        Map<String, Object> map = threadM.parseData(savePos);
        resultMap.putAll(map);
        return RestResponse.success(resultMap);
    }

    @RequestMapping(value = "/searchTaskJson", method = RequestMethod.POST)
    public RestResponse searchTaskJson(@RequestParam("savePos") String savePos) {
        Map<String, Object> resultMap = new HashMap<>();
        ThreadSearchJ threadJ = new ThreadSearchJ();
        Map<String, Object> map = threadJ.parseData(savePos);
        resultMap.putAll(map);
        return RestResponse.success(resultMap);
    }


    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public RestResponse uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("appType") String appType) {
        Map<String, Object> resultMap = new HashMap<>();

        if (file.isEmpty()) {
            return RestResponse.response(ResponseCode.INVALID_PARAM.getCode(), "publish file cannot be empty");
        }

        StringBuilder frontUrl = new StringBuilder();
        String fileName = file.getOriginalFilename();

        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String oldName = fileName;
        if (oldName.contains(".")) {
            oldName = oldName.substring(0, oldName.lastIndexOf("."));
        }
        String t_name = TextUtils.createName();
        fileName = t_name + suffix;
        oldName = t_name;

        File savePos = new File("./tempApp/" + t_name);
        if (!savePos.exists()) {  // 不存在，则创建该文件夹
            savePos.mkdir();
        }

        try {
            // 获取存放位置的规范路径
            String realPath = savePos.getCanonicalPath();
            // 上传该文件/图像至该文件夹下
            File resultFile = new File(realPath + "/" + fileName);
            if (resultFile.exists()) {
                resultFile.delete();
            }
            file.transferTo(resultFile);
            String unzipPath = realPath + "/" + oldName;
            File projectFile = savePos.getParentFile().getParentFile();
            String apktoolPath = projectFile.getAbsolutePath() + "/jks/apktool.jar";
            PackageParse packageParse = new PackageParse();
            packageParse.setmRealFilePath(resultFile.getAbsolutePath());
            System.out.println("=======suffix=====" + suffix);

            if (".apk".equals(suffix)) {
                Map<String, Object> map = packageParse.parseAndroidManifestByCmd(apktoolPath, resultFile.getAbsolutePath(), unzipPath, appType);
                map.put("savePos", savePos.getAbsolutePath() + "/" + t_name);
                resultMap.putAll(map);
            } else if (".aab".equals(suffix)) {
                String apksPath = unzipPath + ".apks";
                String aabPath = resultFile.getAbsolutePath();
                String bundletooPath = "java -jar " + projectFile.getAbsolutePath() + "/jks/bundletool.jar";
//                String cmdaabToApks = "bundletool build-apks --bundle=" + aabPath + "  --output=" + apksPath + " --ks=" + savePos.getParentFile().getAbsolutePath() + "/jks/firepayn1.jks --ks-pass=pass:firepayn1 --ks-key-alias=firepay --key-pass=pass:firepayn1";
                String cmdaabToApks = bundletooPath + " build-apks --bundle=" + aabPath + "  --output=" + apksPath + " --ks=" + projectFile.getAbsolutePath() + "/jks/firepayn1.jks --ks-pass=pass:firepayn1 --ks-key-alias=firepay --key-pass=pass:firepayn1";

//                System.out.println(cmdaabToApks);
//                System.out.println("====cmdaabToApks=========");

                Process process = Runtime.getRuntime().exec(cmdaabToApks);
                int value = process.waitFor();

                String deviceApkPath = unzipPath;
//                String cmd = "bundletool extract-apks --apks=" + apksPath + " --output-dir=" + deviceApkPath + " --device-spec=" + savePos.getParentFile().getAbsolutePath() + "/json/device-spec.json";
                String cmd = bundletooPath + " extract-apks --apks=" + apksPath + " --output-dir=" + deviceApkPath + " --device-spec=" + projectFile.getAbsolutePath() + "/json/device-spec.json";
//                System.out.println(cmd);

//                System.out.println("============waitFor=======0=====");
                process = Runtime.getRuntime().exec(cmd);
                value = process.waitFor();
//                System.out.println("============waitFor=======1=====");

                File deviceApkFile = new File(deviceApkPath);

                String masterApkPath = deviceApkPath + "/base-master.apk";

                for (File childFile : deviceApkFile.listFiles()) {
                    if (childFile.isFile() && childFile.getName().startsWith("base-master")) {
                        masterApkPath = childFile.getAbsolutePath();
                    }
                }

                String masterApkBPath = deviceApkPath + "/base-master";
                Map<String, Object> map = packageParse.parseAndroidManifestByCmd(apktoolPath, masterApkPath, masterApkBPath, appType);
                try {
                    ZIPUtils.unzip(aabPath, resultFile.getParentFile() + "/unzip");
                    FileUtils.moveFile(resultFile.getParentFile() + "/unzip/BUNDLE-METADATA/com.android.tools.build.obfuscation/proguard.map", masterApkBPath + "/proguard.map");
                    map.put("savePos", savePos.getAbsolutePath() + "/" + t_name);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                
                resultMap.putAll(map);
            }
//            resultMap.put("localUrl", localUrl.toString() + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        FileUtils.deleteDirWithPath(savePos.getAbsolutePath());
        return RestResponse.success(resultMap);
    }


}
