package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.bean.ResponseCode;
import com.example.demo.bean.RestResponse;
import com.example.demo.bean.UserParam;
import com.example.demo.utils.CommandLineTool;
import com.example.demo.utils.FileUtils;
import com.example.demo.utils.JsonParser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        resultMap.put("result", strings);
        return RestResponse.success(resultMap);
    }

    @RequestMapping(value = "/msgFeature", method = RequestMethod.POST)
    public RestResponse uploadImageForJson(HttpServletRequest request) {
        InputStream is = null;
        Map<String, Object> resultMap = new HashMap<>();

        try {
            is = request.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuffer sbf = new StringBuffer();
            String lines;
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sbf.append(lines);
            }
            System.out.println("解压大小：" + sbf.toString());
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
//
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
