package com.example.demo.controller;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.log.Log;
import com.example.demo.bean.ResponseCode;
import com.example.demo.bean.RestResponse;
import com.example.demo.utils.FileUtils;
import com.example.demo.utils.ManiParse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipFile;

@RestController
public class ApkController {


    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public RestResponse uploadImage(@RequestParam("file") MultipartFile file) {
        Map<String, Object> resultMap = new HashMap<>();


        if (file.isEmpty()) {
            return RestResponse.response(ResponseCode.INVALID_PARAM.getCode(), "publish file cannot be empty");
        }

        StringBuilder frontUrl = new StringBuilder();
        String fileName = file.getOriginalFilename();

        String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        String oldName = fileName;
        if (oldName.contains(".")) {
            oldName = oldName.substring(0, oldName.lastIndexOf("."));
        }
        fileName = System.currentTimeMillis() + suffix;
        oldName = "" + System.currentTimeMillis();
//        File savePos = new File("src/main/resources/static/." + System.currentTimeMillis());
        File savePos = new File("./.tempApp" + System.currentTimeMillis());
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
            FileUtils.deleteDirWithPath(unzipPath);
            String apktoolPath = savePos.getParentFile().getAbsolutePath() + "/jks/apktool.jar";

            System.out.println("=======suffix=====" + suffix);
            if (".apk".equals(suffix)) {
//                String cmd = "apktool d " + resultFile.getAbsolutePath() + " -o " + unzipPath;
//                Process process = Runtime.getRuntime().exec(cmd);
//                int value = process.waitFor();
//                Map<String, Object> map = ManiParse.parseAndroidManifest(unzipPath + "/AndroidManifest.xml");
//
                Map<String, Object> map = ManiParse.parseAndroidManifestByCmd(apktoolPath,resultFile.getAbsolutePath(), unzipPath);
                resultMap.putAll(map);
            } else if (".aab".equals(suffix)) {

                String apksPath = unzipPath + ".apks";
                String aabPath = resultFile.getAbsolutePath();

                String bundletooPath = "java -jar " + savePos.getParentFile().getAbsolutePath() + "/jks/bundletool.jar";
//                String cmdaabToApks = "bundletool build-apks --bundle=" + aabPath + "  --output=" + apksPath + " --ks=" + savePos.getParentFile().getAbsolutePath() + "/jks/firepayn1.jks --ks-pass=pass:firepayn1 --ks-key-alias=firepay --key-pass=pass:firepayn1";
                String cmdaabToApks = bundletooPath + " build-apks --bundle=" + aabPath + "  --output=" + apksPath + " --ks=" + savePos.getParentFile().getAbsolutePath() + "/jks/firepayn1.jks --ks-pass=pass:firepayn1 --ks-key-alias=firepay --key-pass=pass:firepayn1";

                System.out.println(cmdaabToApks);
                System.out.println("====cmdaabToApks=========");

                Process process = Runtime.getRuntime().exec(cmdaabToApks);
                int value = process.waitFor();

                String deviceApkPath = unzipPath;
//                String cmd = "bundletool extract-apks --apks=" + apksPath + " --output-dir=" + deviceApkPath + " --device-spec=" + savePos.getParentFile().getAbsolutePath() + "/json/device-spec.json";
                String cmd = bundletooPath + " extract-apks --apks=" + apksPath + " --output-dir=" + deviceApkPath + " --device-spec=" + savePos.getParentFile().getAbsolutePath() + "/json/device-spec.json";

                process = Runtime.getRuntime().exec(cmd);
                value = process.waitFor();

                String masterApkPath = deviceApkPath + "/base-master.apk";
                String masterApkBPath = deviceApkPath + "/base-master";
                Map<String, Object> map = ManiParse.parseAndroidManifestByCmd(apktoolPath, masterApkPath, masterApkBPath);
                resultMap.putAll(map);
            }
//            resultMap.put("localUrl", localUrl.toString() + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileUtils.deleteDirWithPath(savePos.getAbsolutePath());
        return RestResponse.success(resultMap);
    }
}
