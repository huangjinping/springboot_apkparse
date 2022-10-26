package com.example.demo.controller;

import com.example.demo.bean.ResponseCode;
import com.example.demo.bean.RestResponse;
import com.example.demo.utils.FileUtils;
import com.example.demo.utils.JsonParser;
import com.example.demo.utils.OkHttpUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SpiderController {


    @RequestMapping(value = "/onTestSpider", method = RequestMethod.POST)
    public RestResponse onTestSpider() {
        Map<String, Object> resultMap = new HashMap<>();


        return RestResponse.success(resultMap);
    }
}
