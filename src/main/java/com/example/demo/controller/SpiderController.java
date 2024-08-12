package com.example.demo.controller;

import com.example.demo.bean.ResponseCode;
import com.example.demo.bean.RestResponse;
import com.example.demo.utils.FileUtils;
import com.example.demo.utils.InxServerSpider;
import com.example.demo.utils.LogUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SpiderController {


    @RequestMapping(value = "/serverTest", method = RequestMethod.POST)
    public RestResponse uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("appssid") String appssid, @RequestParam("domainname") String domainname, @RequestParam("phoneNo") String phoneNo) {
        Map<String, Object> resultMap = new HashMap<>();

        if (file.isEmpty()) {
            return RestResponse.response(ResponseCode.INVALID_PARAM.getCode(), "publish file cannot be empty");
        }
        String fileName = file.getOriginalFilename();
        String firstName = fileName;
        
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

            String appurlname = firstName.replace(".json", "");
            InxServerSpider serverSpider = new InxServerSpider(jsontext, appurlname, appssid, domainname, phoneNo);
            Map<String, Object> start = serverSpider.start();
            resultMap.putAll(start);

        } catch (Exception e) {
            LogUtils.logJson(e.getMessage());

            LogUtils.logJson("==========error==========");
            e.printStackTrace();
        }
        FileUtils.deleteDirWithPath(savePos.getAbsolutePath());
        return RestResponse.success(resultMap);
    }
//    http://

    @RequestMapping(value = "/onTestSpider", method = RequestMethod.POST)
    public RestResponse onTestSpider() {
        InputStream is = null;

        Map<String, Object> resultMap = new HashMap<>();


        return RestResponse.success(resultMap);
    }

    @RequestMapping(value = "/onMishie")
    public String onMishie(HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();

        String strDateFormat = "yyyyMMddHHmmssSSS";//设置日期格式



        System.out.println("===============1============");
        try {
            Thread.sleep(55*1000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("===============2============");

        Date date = new Date();
        date.setTime(System.currentTimeMillis());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strDateFormat);
        LogUtils.log(simpleDateFormat.format(date) + "      " + request.getRemoteAddr());

        return "\n" +
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\n" +
                "<head>\n" +
                "    <base href=\\ \"https://via.banorte.com:443/secure3d/\\\">\n" +
                "    <meta charset=\\ \"ISO-8859-1\\\">\n" +
                "    <title>AuthEnroll</title>\n" +
                "    <script type=\\ \"text/javascrip\">\n" +
                "    //<![CDATA[window[\\\"_csrf_\\\"] = \\\"08f86e36758498040c3e87a28c92c7e2f24269de98a95b23ed363aaa60823353f1fd33d656d74262421b93c715de590b7d762f7876dfbdae3250202043a38b7536da05ff91914e1560ac2765d16876ca333469c446e7ca551b93bd4e2bab19c951c7cf797dd9c7b1955147c6cf13e7bb71af869b7b5427861c3109ebb91f34f5886f5087db80f53506f868eb87dfeebef194d02e6729cc5601cf2e1feb72532234de786d2942f60d5e65f6ce4fbd57dcb3870269ffd8974558551564fb945d247d0cea1dce3dd6c82b51c3b80f41141fe8eb6d5f0992ce106da194b506f2237d17eec4f8e0579b88f5f7993160dda8ef798d8b62d088e4361efa0b275351962d2b1ca28ecd5ead750e5de233ee7110469cac49235f6edaa2ec946cd71d90c9c90a01f056ec51d1da2dfe7cf6b84c17ac84c082d5fd214ac15b7891126a14f565922f71ee439239aef355762224aac5315635905ffc3be46f39772d4bdcab483acffe0001591d0f00cfd402922ed75b1f390bb882709701217d69cd16f9f087e07dfd03d34f10c7c3f1e7b6f640f582207b18ee325fe25b99c9f58c3d3b7c17d9114bedf3dbf2658689bf8e3a51b73a4762b979576301194953d536a36fe6b272bf4f26758c6fdaca758dbee2864eb8d4e98ee380f61e7c55c9cce448772f20c045ce520921b20f14d01061ec8f17e555886b509906a3f59c7ba99e5c910f9b515af096912643c32e39953bb2e7e70bf9cfa76bfd57bd513ec58c84395659965afdeffc1fec65fba1bedb2faee805c53cf0a56d9bb0e8af75054c4e75653daa7ed80722f1d241723467d937f1ec19fe8723d46048aa001ecb98d140048296357e216fae4158f52aafdeaa76abd5879db0db05019feda86b22063e3563464ca97a8bb12bef1a0d45f3d92675981f2b5731c10adf4a6b7a1948e98b04e4e6544bd2138474ce9bccc0279aa284fbe51e9e6c57216d699a3c65143cb73c27829d22ece2bf3deee14b94ea6dc9b72e30f3d5968c11c41fef916fe3c7834412857a4a459ea9d015f5ecd2879babb1cbdd5010faa872abc474037e97bdad7a5ddb3e882c0ecb58da88f4cc600b4ecb1592d0a27e0d89b3c1b4da189766237256dbe078944a45edd311afdd8e56038743fcb82add4efb4b073f24b438f1e0bc21c63b537d96bf7095b7339f60828818f8a62d6e7743833adda3bfeabe1b4d53b5842954d160e3dab7d38b4660ab48ec3ad1c4054685c87b47f14767bea85426635354c94606e6a8d0b643c7d01c20626ad7d6b2dba7aa61c299ab4aa59ab8a156a61861633b56322730b3456aed921fce3e6df5d7f51ce430bcb24b7216f2483ab1f4fcbe78bbcf9ce1371f963e9afc78cf7db6c0beee6204d4418072267f74cf9d90693e911238e5b705acdfdfdc76ea29aeb39157252a7318d50348930a95ddc27beb8ed64523c0871dffbacf8d5a720ae9927b8cc1b1660d562c5eba642a2caab41e347d34694bce26e9b534eb10bb2181f0bf2d9a4fbcc4001e7a5da389274bb3a094ec041dfb997edcbbf0751e0d23eea2722b5e5cb4a59f95f5551ede9e5558c47d6a2ca94473e9966bf1bf07c8deec38a4a8983a72af670cb056ec78bba7ebf4c6f22eb80326433a6a589e590386caa76339a16213fd7e61700d186d7fd5ab58e2\\\";//]]>\n" +
                "    </script>\n" +
                "    <script type=\\ \"text/javascrip\" src=\\ \"/TSbd/08ea205343ab2000b82b8625003985e7a0b0cdc41f3f04fec1f4b3f38517e77b9e540da6e9c4507f?type=2\\\"></script>\n" +
                "</head>\n" +
                "\n" +
                "<body background=\\ \"images/logo.jpg\\\"><input type=\\ \"hidde\" id=\\ \"JWTContaine\" value=\\\n" +
                "\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI0NTE4OTlfMjA5ODgyMF8xNjczMjMzMDM1OTU1IiwiaWF0IjoxNjczMjMzMDM1LCJpc3MiOiI1ZjQxYmI2ZGY5YTkxMTZkNDdkYTdkYjgiLCJPcmdVbml0SWQiOiI1YmUwYWYzNzM1NmRjZTE1MzQzMWY0MjAiLCJQYXlsb2FkIjoie1wiT3JkZXJEZXRhaWxzXCI6e1wiT3JkZXJOdW1iZXJcIjpcIjdiOGY5MzIzYjVmZTQ2YlwiLFwiQW1vdW50XCI6XCIxMDVcIixcIkN1cnJlbmN5Q29kZVwiOlwiTVhOXCJ9fSIsIk9iamVjdGlmeVBheWxvYWQiOmZhbHNlLCJSZWZlcmVuY2VJZCI6IjQ1MTg5OV8yMDk4ODIwXzE2NzMyMzMwMzU5NTUiLCJleHAiOjE2NzQ5MDYyNjh9.MpjQ7z-5oHfjpTXvcVcJmoiJYM6YFjnctSUQLfGT-MY\\\" /><input type=\\ \"hidde\" id=\\ \"bi\" value=\\ \"421747005\\\" /><input type=\\ \"hidde\" id=\\ \"reqCybe\" value='bUwrU1pRbzhEMGFWbkZOWTR6SlRqdXQ0NnZOdW5VaWRSdk5Kcm5RRnZ3SDFoSzlFdWpVTEpISnBDQys4R3V5L2htMC9TNGtvU2M1SkdXR2tqd0taVVBCNm80S3RuVHI4WUdDaW5nancwMWJ0S1FSUDYrbmljNU9VZENUVWZoUXVzUHU0cjV6Wnd6S3dQaW9Xb0Vqb01pTXZZdGVjYmg4T2R0QUd2NE5lK1BhTExSd1RGaUFVRTFtcWpTY0hDNSs0RXFTRWZabmVIdU5yL3g5MXdtVzVCazRvQlcweldmRFBYM0x1MDg1c2x1QW5UL3pJMHpPQlVpWVVXbG1weDNtRDFpMFYyZEd6ajRyRjJSTmRWTGFpUXkwVWlUN043Ry9rQVpNOHhhQmxFYmVkRnJDTGJZODd0M0VDTlFKVEJ2MkdVM1JRblBqMEl3Qjg5bm8rVmxKdSswVE5zZytXSDVBZG9mbVJGVjdueHczczhaOFZTUmIxc2dVYnNPODJ4S2hwcG4reHl2QWtDR3ZjWjFPZVYzY1prbk1UeVh0ZTFKZGlNa1FPUzVmZEFHOGt3SDdzVG9TV1NNSWVQdUFCQ01XVmVIZjU0S04yWldadGdDT0taWG5JTFhVSGc4VlhuaFRXdlE1OHJTYXkrbU1BS2pxakRLMjYwQk5CcXR5Y1ZTblpCUk1aUTFxK2ZrVmFDNjJrMkxUK096QVhSOG0vUTRiTkNCWHp4eS9DQ3pPdkpvQTU0OW45R0pzUTJkOGx4SXBjRGFVOTBDOXA4NzNzdjlmSkFVZGhiSEc4cUgydkwySmNKK3VvYmVydU5PWWhGalcwYU1LaTVvcm1EU29ET3R1VjZ4TFJDTTBwWEFGRW9PTVN1YVVqajhLQ1lKaW1jaXFPc2lod2VCMWlwQkVZSzJ5OWxMclRYTk1DdjBVekEvZW9VQllxdUtnZmxkZktoK1RhU2VhL1Z1NUQ3YkhTMzJaVVlpc1doSUdkYndGckRwQktSODNYOUQySkdiVWV5OTlRYnBDc09rUUJQOEtaUGFIZWlZM0NGdHpXdWdJdDIyQURQQlBjbzUrSWFkNDdVY0hYTDVscDRlUkpuVWpzckpGZXk4RDU1Mm8rVjFrVzFMcVZVQXkzMmRNc0ZUWEgzSTB2NzlrK3lFOUs3TGtlUXUwSnIxK2tsandHYjNHb0FQWUpzWEg4ckZOc0hBTkN6TzlpRXNXUVQyWkZRL3ZIYWlBMDNHSUR3N1hoQ2xIUTJxOGtoWUJOMzQrL216WG0raWlCSTN1RmlJMmpxeXRUWUc4eHdncFVqWEpJTTE2VVlNb3Yra1hEQjZQOXNsYzQyY01IK1RzME5kQVh4RllXeUxDeW5yRnhVTzFkdThzclpMVTB5L2FxYnN4SmZPOEd5MFhjNkxQYTVzYWNVRU9nR3JKWEhsMUVsQ1dqWmwzeGZXMWVCV3BuZno0bHNKVFBwQVdNcHBySC8zNnNjeWRHcW5ranJ2dVBVK1VRMk92bU5DdWNPZzZGcUY4Vm5RMG9maHk2d1BLajJ3V1VzRExMYVNMNWx0L09ZcHRZUUEvUkxKeXg5elc0b1VFY1lNU0ZrQWtObXZHbUg4RTFuYkUva3FoQlRCSWNRUGdWbk9hN1FDL0FCR2laTmRaNmtBbUVPSWY1dXNPU010VzFuMG95djFlK0F3ckE3MUtDOXIvWGxwbnNueVcrQ29OR0FyL1lxYWw4NG9QUXVQMjllTVEwMzJOUjUyYWZEVGNZT2NqUG5rdFNLWGtNditVR1A5Z3pxd2JSMUxXTm1YK28yM2tOSE54bE02NjJ0WlZjOXkxdjlrb08yUjAvbkN3ZWU1Nkl5S0w5d21neGtWMXhhYVFxL295NzlmWG1uNWtKSjZ6ZXE2ekpoOCtiM25mNVBmK0NYZWcwY0Z5aDQ0OTR2S3JiaWRkc1lFZDJqQjJQQ1lMOVFjQnU1TzVvckZUR2tBek9tVWVpVmZ6QVBGMGtyYjVpUDQwN1BidTcwemVnRXN4WGkrd09GeEZzVVY0MXlKU1dkSDZpVjFYRUtOTUxMRzMwVm01QjB6N3BWdnV1NE1XT3RoUmdKbXhDNjdUM1J6cUdvRFVGWkIrd0R2Vmh4ODNhNTFEb1RIcnBHQnl4bHhUMU5tTWg1a2I3aEZSMlJmSEkveHpSUUkveUNjbC8vcW13SFNlWGJTM1ltaUZWWTIrTW4yODhXSFJyTUs3ZUNlcmdSWmZXRTdRRlg3UDR0TTMzdVVrQ1dNMlRTSUVHMldGSW9JNHFYZmlSaUpya0QvZ0VxeS9C'\n" +
                "/>\n" +
                "<form id=\\ \"formResJW\" action=\\ \"https://via.banorte.com/secure3d/ResponseJWT.htm\\\" method=\\ \"POS\"> </form>\n" +
                "<script src=\\ \"https://code.jquery.com/jquery-3.6.1.min.js\\\"></script>\n" +
                "<script src=\\ \"https://songbird.cardinalcommerce.com/edge/v1/songbird.js\\\"></script>\n" +
                "<script type=\\ \"text/javascrip\">\n" +
                "\n" +
                "   tvar respCyber = document.getElementById(\\\"reqCybe\").value ;var bines = document.getElementById(\\\"bi\").value;var jwts = document.getElementById(\\\"JWTContaine\").valuevar orderObject = {  Consumer: {    Account: {      AccountNumber: document.getElementById(\\\"bi\").value    }  }};    Cardinal.configure({    logging: {        level: \\\"o\"    }});  function init() {  Cardinal.setup('init', {  jwt: document.getElementById(\\\"JWTContaine\").value,order: orderObject});  }    Cardinal.on('payments.setupComplete', function(){    collectData();  });  function collectData() {  collectResponse();};function collectResponse(){var inputJwt = \\\"<input type='hidden' id='vJwt' name='vJwt' value='\\\"+ jwts + \\\"'/>\\\";var inputrespCyber = \\\"<input type='hidden' id='respCyber' name='respCybersource' value='\\\"+ respCyber + \\\"'/>\\\";var inputBin = \\\"<input type='hidden' id='vBin' name='vBin' value='\\\"+ bines + \\\"'/>\\\"document.getElementById(\\\"formResJW\").innerHTML=inputrespCyber+inputJwt+inputBin;document.forms[0].submit();};$(document).ready(function() {        init();});\n" +
                "</script></body>\n" +
                "\n" +
                "</html>\n";
    }


}
