package com.example.demo.controller;

import com.example.demo.bean.ResponseCode;
import com.example.demo.bean.RestResponse;
import com.example.demo.bean.UserParam;
import com.example.demo.utils.CommandLineTool;
import com.example.demo.utils.FileUtils;
import com.example.demo.utils.GzipUtil;
import com.example.demo.utils.JsonParser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class JsonController {

//
//    @RequestMapping(value = "/msgFeatureTest", method = RequestMethod.POST)
//    public RestResponse _msgFeature(HttpServletRequest request) {
//        InputStream is = null;
//        try {
//            is = request.getInputStream();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//            StringBuffer sbf = new StringBuffer();
//            String lines;
//            while ((lines = reader.readLine()) != null) {
//                lines = new String(lines.getBytes(), "utf-8");
//                sbf.append(lines);
//            }
//            Map<String, Object> resultMap = new HashMap<>();
//
//            return RestResponse.success(resultMap);
//        } catch (Exception ex) {
//        }
//        return RestResponse.response(ResponseCode.SYS_EXCEPTION.getCode(), "system exception");
//    }

    @RequestMapping(value = "/resetDocUpdate")
    public RestResponse resetDocUpdate(HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        List<String> commands = new ArrayList<>();
        commands.add("./json/docUpdate.sh");
        List<String> strings = CommandLineTool.executeNewFlow(commands);
        return RestResponse.success(resultMap);
    }


    @RequestMapping(value = "/msgFeatureV4", method = RequestMethod.POST)
    public RestResponse msgFeatureV4(@RequestBody String compressedData) {
        InputStream is = null;
        Map<String, Object> resultMap = new HashMap<>();
        String s = null;
        try {
//            String s = GzipUtil.unCompress(compressedData);
            System.out.println("========server===111=================");
//            System.out.println(s);
            s = GzipUtil.unCompress(compressedData);
            resultMap.put("result", s);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return RestResponse.success(resultMap);
    }

    @RequestMapping(value = "/msgFeatureV3", method = RequestMethod.POST)
    public RestResponse msgFeatureV3(HttpServletRequest request) {

        InputStream is = null;
        Map<String, Object> resultMap = new HashMap<>();
        String s = null;
        try {
//            String s = GzipUtil.unCompress(compressedData);
            System.out.println("========server===111=================");
//            System.out.println(s);

            String result="{\n" +
                    "  \"video_external\": 35,\n" +
                    "  \"public_ip\": {\n" +
                    "    \"second_ip\": \"172.19.0.1\",\n" +
                    "    \"first_ip\": \"8.219.242.193\"\n" +
                    "  }\n" +
                    "}  ";

            is = request.getInputStream();
            s = FileUtils.inputStreamToString(is);
            System.out.println("========server===111================="+s);
            s = GzipUtil.unCompress(s);

//            BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
//            StringBuffer sbf = new StringBuffer();
//            String lines;
//            while ((lines = reader.readLine()) != null) {
//                lines = new String(lines.getBytes(), StandardCharsets.UTF_8);
//                sbf.append(lines);
//            }
//            s = GzipUtil.unCompress(sbf.toString());

//            System.out.println("解压大小：" + sbf);
//            String key = "c73c94fad68f55df6d5f46e7c79ba9f5";
            String jsontext = "ddd".toString();
////            jsontext = AESUtil.decrypt(jsontext, key);
////            jsontext = GzipUtil.unCompress(jsontext);
//            System.out.println("解压大小==uploadImageForJson====：" + jsontext);
            resultMap.put("result", s);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return RestResponse.success(resultMap);
    }


    @RequestMapping(value = "/msgFeature", method = RequestMethod.POST)
    public RestResponse uploadImageForJson(HttpServletRequest request) {
        InputStream is = null;
        Map<String, Object> resultMap = new HashMap<>();

        try {
            is = request.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            StringBuffer sbf = new StringBuffer();
            String lines;
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), StandardCharsets.UTF_8);
                sbf.append(lines);
            }
            System.out.println("解压大小：" + sbf);
            String key = "c73c94fad68f55df6d5f46e7c79ba9f5";
            String jsontext = sbf.toString();
//            jsontext = AESUtil.decrypt(jsontext, key);
//            jsontext = GzipUtil.unCompress(jsontext);
            System.out.println("解压大小==uploadImageForJson====：" + jsontext);
            resultMap.put("result", jsontext);

        } catch (Exception ex) {
        }
        return RestResponse.success(resultMap);
    }


    @RequestMapping(value = "/uploadBigJson", method = RequestMethod.POST)
    public RestResponse uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("appType") String appType) {
        Map<String, Object> resultMap = new HashMap<>();

        if (file.isEmpty()) {
            return RestResponse.response(ResponseCode.INVALID_PARAM.getCode(), "publish file cannot be empty");
        }

        String fileName = file.getOriginalFilename();
        String oldName = fileName;
        fileName = System.currentTimeMillis() + "";
        oldName = "" + System.currentTimeMillis();
        File savePos = new File("./.tempJson" + System.currentTimeMillis());
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
            String jsontext = FileUtils.getTextByPath(unzipPath);

//            String key = "c73c94fad68f55df6d5f46e7c79ba9f5";
//            jsontext = AESUtil.decrypt(jsontext, key);
//            jsontext = GzipUtil.unCompress(jsontext);
//            System.out.println("原文大小：" + jsontext.getBytes().length + " \n压缩前：");
//            String compress = GzipUtil.compress(jsontext);
//            System.out.println("解压大小：" + compress.getBytes().length + " \n压缩后：");
//
//            String uncompress = GzipUtil.unCompress(compress);
//            System.out.println("解压大小：" + uncompress.getBytes().length + " \n解压缩：");
            UserParam userParam = new UserParam();
            userParam.setAppType(appType);
            JsonParser jsonParser = new JsonParser(userParam);
            Map<String, Object> stringObjectMap = jsonParser.parseRoot(jsontext);
            resultMap.putAll(stringObjectMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        FileUtils.deleteDirWithPath(savePos.getAbsolutePath());
        return RestResponse.success(resultMap);
    }
}
