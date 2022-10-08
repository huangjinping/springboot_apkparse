package com.example.demo.controller;

import com.example.demo.bean.ResponseCode;
import com.example.demo.bean.RestResponse;
import com.example.demo.utils.FileUtils;
import com.example.demo.utils.JsonParser;
import com.example.demo.utils.LogUtils;
import com.example.demo.utils.ManiParse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
public class JsonController {


    @RequestMapping(value = "/uploadBigJson", method = RequestMethod.POST)
    public RestResponse uploadImage(@RequestParam("file") MultipartFile file) {
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
            Map<String, Object> stringObjectMap = JsonParser.parseRoot(jsontext);
            resultMap.putAll(stringObjectMap);


        } catch (Exception e) {
            e.printStackTrace();
        }
        FileUtils.deleteDirWithPath(savePos.getAbsolutePath());
        return RestResponse.success(resultMap);
    }
}
