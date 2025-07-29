package com.example.demo.controller;

import com.example.demo.bean.KeepPackage;
import com.example.demo.bean.ResponseCode;
import com.example.demo.bean.RestResponse;
import com.example.demo.utils.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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


    @RequestMapping(value = "/checkSdk", method = RequestMethod.POST)
    public RestResponse checkSdk(@RequestParam("savePos") String savePos, @RequestParam("apkPath") String apkPath) {
        Map<String, Object> resultMap = new HashMap<>();
        LogUtils.log("=======checkSdk=======savePos===========" + savePos);
        LogUtils.log("=======checkSdk=======apkPath===========" + apkPath);

        ThreadSearchQ threadg = new ThreadSearchQ();
        Map<String, Object> map = threadg.parseData(savePos, apkPath);
        resultMap.putAll(map);

        return RestResponse.success(resultMap);
    }


    @RequestMapping(value = "/searchTaskLog", method = RequestMethod.POST)
    public RestResponse searchTaskLog(@RequestParam("savePos") String savePos) {
        Map<String, Object> resultMap = new HashMap<>();
        ThreadSearchG threadg = new ThreadSearchG();
        Map<String, Object> map = threadg.parseData(savePos);
        resultMap.putAll(map);
        return RestResponse.success(resultMap);
    }


    @RequestMapping(value = "/uploadAppImage", method = RequestMethod.POST)
    public RestResponse uploadAppImage(@RequestParam("file") MultipartFile file) {
        Map<String, Object> resultMap = new HashMap<>();
        StringBuilder frontUrl = new StringBuilder();
        String fileName = file.getOriginalFilename();
        LogUtils.log(fileName);

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
            String realPath = savePos.getCanonicalPath();
            // 上传该文件/图像至该文件夹下
            File resultFile = new File(realPath + "/" + fileName);


            LogUtils.log(resultFile.getAbsolutePath());
            if (resultFile.exists()) {
                resultFile.delete();
            }
            file.transferTo(resultFile);

        } catch (Exception e) {
            e.printStackTrace();
        }

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
//            System.out.println("=======suffix=====" + suffix);
//            System.out.println(unzipPath + "=======suffix=" + resultFile.getAbsolutePath() + "=2===" + savePos.getAbsolutePath());

            if (".apk".equals(suffix)) {
                Map<String, Object> map = packageParse.parseAndroidManifestByCmd(apktoolPath, resultFile.getAbsolutePath(), unzipPath, appType, PackageParse.ANDROID_PACKAGE_TYPE_APK);
                map.put("apkPath", resultFile.getAbsolutePath());

                map.put("savePos", savePos.getAbsolutePath() + "/" + t_name);
                map.putAll(PackageParse.getApkLengthByList(resultFile.getAbsolutePath()));
                map.putAll(PackageParse.parseApkLibs(unzipPath));
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
                System.out.println("" + cmd);
                process = Runtime.getRuntime().exec(cmd);
                value = process.waitFor();

                File deviceApkFile = new File(deviceApkPath);

//                =====解压缩====
                String unzipAbsolutePath = resultFile.getParentFile() + "/unzip";
                ZIPUtils.unzip(aabPath, unzipAbsolutePath);
//                ----------


                String masterApkPath = deviceApkPath + "/base-master.apk";

                for (File childFile : deviceApkFile.listFiles()) {
                    if (childFile.isFile() && childFile.getName().startsWith("base-master")) {
                        masterApkPath = childFile.getAbsolutePath();
                    }
                }

                String masterApkBPath = deviceApkPath + "/base-master";

                Map<String, Object> map = packageParse.parseAndroidManifestByCmd(apktoolPath, masterApkPath, masterApkBPath, appType, PackageParse.ANDROID_PACKAGE_TYPE_AAB);


//                LogUtils.log("---------------get-size total---------------------->");
                cmd = bundletooPath + " get-size total --apks " + apksPath + "";

//                cmd = bundletooPath + " get-size total --apks " + apksPath + " --device-spec=" + projectFile.getAbsolutePath() + "/json/device-spec.json";
                List<String> commands = new ArrayList<>();
                commands.add(cmd);
                List<String> result = CommandLineTool.executeNewFlow(commands);

                try {

                    try {
                        map.putAll(PackageParse.getAbbLengthByList(aabPath, result, unzipAbsolutePath));
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }

                    String mappingPath = resultFile.getParentFile() + "/unzip/BUNDLE-METADATA/com.android.tools.build.obfuscation/proguard.map";
                    String dependenciesPath = resultFile.getParentFile() + "/unzip/BUNDLE-METADATA/com.android.tools.build.libraries/dependencies.pb";

                    try {
                        List<KeepPackage> packageList = PackageParse.dependenciesRule(resultFile.getParentFile() + "/unzip/BUNDLE-METADATA/com.android.tools.build.libraries/dependencies.pb");

                        List<KeepPackage> keepPackageList = (List<KeepPackage>) map.get("keepPackage");
                        packageList.addAll(keepPackageList);

                        map.put("keepPackage", packageList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        File mappingFile = new File(mappingPath);
                        if (!mappingFile.exists()) {
                            map.put("minifyEnabled", "0");
//                            System.out.println("----------minifyEnabled0------。");

                        } else {
                            map.put("minifyEnabled", "1");
//                            System.out.println("----------minifyEnabled1------。");

                        }
                    } catch (Exception e) {
//                        System.out.println("----------minifyEnabled2------。");

                        e.printStackTrace();
                    }


                    FileUtils.moveFile(mappingPath, masterApkBPath + "/proguard.map");
//                    FileUtils.moveFile(dependenciesPath, masterApkBPath + "/dependencies.map");

                    commands.clear();
                    cmd = "Strings " + dependenciesPath + " >>" + masterApkBPath + "/dependencies.txt";
                    commands.add(cmd);
                    CommandLineTool.executeNewFlow(commands);

                    map.put("savePos", savePos.getAbsolutePath() + "/" + t_name);
                    map.put("apkPath", masterApkPath);
                    map.putAll(PackageParse.parseLibs(resultFile.getParentFile() + "/unzip/base/lib"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                resultMap.putAll(map);

            } else if (".ipa".equals(suffix)) {
                List<String> commands = new ArrayList<>();
                ApaParser apaParser = new ApaParser();
                apaParser.setAppType(appType);
//                LogUtils.logJson("======resultFile00========" + resultFile.getName());
                commands.add("./json/parseipa.sh " + savePos.getAbsolutePath() + " " + resultFile.getName());
                List<String> strings = CommandLineTool.executeNewFlow(commands);
                Map<String, Object> objectMap = apaParser.parseIOSipa(savePos.getAbsolutePath());
                resultMap.put("savePos", savePos.getAbsolutePath());
                resultMap.putAll(objectMap);
                LogUtils.logJson(strings);


            }

//            /**
//             * 拷贝文件
//             * application.packageName
//             */
//
//            Application application = (Application) resultMap.get("application");
//            String packageName = application.getPackageName();
//            File tempApp = new File("./tempApp/" + packageName);
//
//            if (tempApp.exists()) {
//                resultFile.delete();
//            }
//            if (!tempApp.exists()) {  // 不存在，则创建该文件夹
//                tempApp.mkdir();
//            }
//
//            FileUtils.moveFile(resultFile.getAbsolutePath(), tempApp.getAbsolutePath() + "/" + resultFile.getName());


//            resultMap.put("localUrl", localUrl.toString() + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        FileUtils.deleteDirWithPath(savePos.getAbsolutePath());
        return RestResponse.success(resultMap);
    }


//    @RequestMapping(value = "/getChannelPackage", method = RequestMethod.GET)
//    public void getChannelPackage(HttpServletResponse response, @RequestParam("gclid") String gclid) {
//        String file = "/Users/huhuijie/Documents/soft/walle/out1.apk";
//        try {
//            FileInputStream inputStream = new FileInputStream(file);
//            byte[] data = new byte[inputStream.available()];
//            inputStream.read(data);
//            String diskfilename = "final.avi";
//            response.setContentType("video/avi");
//
//            response.setHeader("Content-Disposition", "attachment; filename=\"" + diskfilename + "\"");
//            System.out.println("data.length " + data.length);
//            response.setContentLength(data.length);
//            response.setHeader("Content-Range", "" + Integer.valueOf(data.length - 1));
//            response.setHeader("Accept-Ranges", "bytes");
//            response.setHeader("Etag", "W/\"9767057-1323779115364\"");
//            OutputStream os = response.getOutputStream();
//
//            os.write(data);
//            //先声明的流后关掉！
//            os.flush();
//            os.close();
//            inputStream.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//
//    }

    /**
     * @param
     * @return http://127.0.0.1:8092/onTest
     */
    @RequestMapping(value = "/onTest", method = RequestMethod.GET)
    public RestResponse onTest() {

        Map<String, Object> resultMap = new HashMap<>();
        LogUtils.log("================================");


        return RestResponse.success(resultMap);


    }


    /**
     * @param gclid
     * @return http://127.0.0.1:8092/getChannelPackage?gclid=19828388283
     */
    @RequestMapping(value = "/getChannelPackage", method = RequestMethod.GET)
    public ResponseEntity<Resource> getChannelPackage(@RequestParam("gclid") String gclid) {
        LogUtils.log("gclid:" + gclid);
        String t_name = TextUtils.createName();
        File savePos = new File("./tempApp/" + t_name);
        if (!savePos.exists()) {  // 不存在，则创建该文件夹
            savePos.mkdir();
        }

        File projectFile = savePos.getParentFile().getParentFile();
        String outFilePath = savePos.getAbsolutePath() + "/" + gclid + "_" + TextUtils.createName() + ".apk";
        String wallePath = projectFile.getAbsolutePath() + "/jks/walle-cli-all.jar";
        String command = "java -jar " + wallePath + " put -c " + gclid + " /Users/huhuijie/Documents/soft/walle/android2023demo20231228074618.apk " + outFilePath;

        command = "java -jar " + wallePath + " put -c " + gclid + " -e buildtime=" + System.currentTimeMillis() + ",hash=xxxxxxx " + " /Users/huhuijie/Documents/soft/walle/android2023demo20240104114924.apk " + outFilePath;

//        java -jar walle-cli-all.jar put -c meituan -e buildtime=20161212,hash=xxxxxxx /Users/xxx/Downloads/app.apk

        LogUtils.log(command);
        List<String> commands = new ArrayList<>();
        commands.add(command);
        List<String> strings = CommandLineTool.executeNewFlow(commands);

        String contentDisposition = ContentDisposition
                .builder("attachment")
                .filename(outFilePath)
                .build().toString();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .contentType(MediaType.IMAGE_JPEG)
                .body(new FileSystemResource(outFilePath));

    }

}
