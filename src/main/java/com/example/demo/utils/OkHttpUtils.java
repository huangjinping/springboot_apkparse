package com.example.demo.utils;

import okhttp3.*;

import javax.net.ssl.*;
import java.io.File;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class OkHttpUtils {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType FORM = MediaType.parse("multipart/form-data");
    public static final MediaType FORMX = MediaType.parse("application/x-www-form-urlencoded");
    public static final MediaType OCTETFORM = MediaType.parse("application/octet-stream");

    private static OkHttpClient client;
    private static OkHttpClient clientZip;
    private static OkHttpClient clientNoSSL;

    static {
        client = new OkHttpClient.Builder()
                .connectTimeout(40L, TimeUnit.SECONDS)
                .readTimeout(40L, TimeUnit.SECONDS)
                .writeTimeout(40L, TimeUnit.SECONDS)
                .build();
        clientZip = new OkHttpClient.Builder()
                .connectTimeout(40L, TimeUnit.SECONDS)
                .readTimeout(40L, TimeUnit.SECONDS)
                .writeTimeout(40L, TimeUnit.SECONDS)
                .addInterceptor(new GzipInterceptor())
                .build();
        clientNoSSL = new OkHttpClient.Builder()
                .connectTimeout(40L, TimeUnit.SECONDS)
                .readTimeout(40L, TimeUnit.SECONDS)
                .writeTimeout(40L, TimeUnit.SECONDS)
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory(), SSLSocketClient.getX509TrustManager())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                .build();
    }

    public static void main(String[] args) {


    }


    public static String postSimple(String url, String json) {
        try {
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public static String putJson(String url, String requestData) {

        return putJson(url, requestData, new HashMap<>());

    }

    public static String putJsonNoSSL(String url, String requestData, Map<String, String> header) {
        RequestBody requestBody = RequestBody.create(JSON, requestData);

        Request.Builder builer = new Request.Builder()
                .post(requestBody)
                .url(url);

        if (header != null) {
            Set<Map.Entry<String, String>> headerSet = header.entrySet();
            for (Map.Entry<String, String> entry : headerSet) {
                builer.addHeader(entry.getKey(), entry.getValue());
            }
        }

        Request request = builer.build();
        try {
            Response response = clientNoSSL.newCall(request).execute();
            return response.body().string();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String putJson(String url, String requestData, Map<String, String> header) {
        RequestBody requestBody = RequestBody.create(JSON, requestData);

        Request.Builder builer = new Request.Builder()
                .put(requestBody)
                .url(url);

        Set<Map.Entry<String, String>> headerSet = header.entrySet();
        for (Map.Entry<String, String> entry : headerSet) {
            builer.addHeader(entry.getKey(), entry.getValue());
        }

        Request request = builer.build();
        try {

            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient client = new OkHttpClient.Builder()
                    .sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .build();

            Response response = client.newCall(request).execute();

            return response.body().string();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public static String postJsonWithoutZip(String url, String requestData) {
        return postJsonWithoutZip(url, requestData, new HashMap<>());
    }

    public static String postJsonWithoutZip(String url, String requestData, Map<String, String> header) {
        RequestBody requestBody = RequestBody.create(JSON, requestData);

        Request.Builder builer = new Request.Builder()
                .post(requestBody)
                .url(url);

        Set<Map.Entry<String, String>> headerSet = header.entrySet();
        for (Map.Entry<String, String> entry : headerSet) {
            builer.addHeader(entry.getKey(), entry.getValue());
        }

//        builer.addHeader("Connection", "close");
        Request request = builer.build();
        try {
//            OkHttpClient client = new OkHttpClient.Builder()
//                    .connectTimeout(30L, TimeUnit.SECONDS)
//                    .connectionPool(new ConnectionPool(5, 1, TimeUnit.SECONDS))
//                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String postJson(String url, String requestData, Map<String, String> header) {
        LogUtils.log("=====url= start==" + url);
        LogUtils.logJson(header);
        LogUtils.log("=====url= end==" + url);
        RequestBody requestBody = RequestBody.create(JSON, requestData);

        Request.Builder builer = new Request.Builder()
                .post(requestBody)
                .url(url);

        Set<Map.Entry<String, String>> headerSet = header.entrySet();
        for (Map.Entry<String, String> entry : headerSet) {
            builer.addHeader(entry.getKey(), entry.getValue());
        }
//        builer.addHeader("Connection", "close");
        Request request = builer.build();
        try {
//            OkHttpClient client = new OkHttpClient.Builder()
//                    .connectTimeout(30L, TimeUnit.SECONDS)
//                    .readTimeout(30L, TimeUnit.SECONDS)
//                    .writeTimeout(30L, TimeUnit.SECONDS)
//                    .addInterceptor(new GzipInterceptor())
//                    .connectionPool(new ConnectionPool(5, 1, TimeUnit.SECONDS))
//                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String postJson(String url, String requestData) {

        return postJson(url, requestData, new HashMap<>());
    }

    public static String postFormWithImge(String url, Map<String, File> fileMap, Map<String, String> paramMap, Map<String, String> header) {
        LogUtils.log("=====url= start==" + url);
        LogUtils.logJson(header);
        LogUtils.logJson(paramMap);
        LogUtils.log("=====url= end==" + url);


        MultipartBody.Builder body = new MultipartBody.Builder();
        body.setType(FORM);

        if (fileMap != null) {
            Iterator<Map.Entry<String, File>> it = fileMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, File> entry = it.next();
                RequestBody requestBody = RequestBody.create(OCTETFORM, entry.getValue());
                body.addFormDataPart(entry.getKey(), entry.getValue().getName(), requestBody);
            }
        }

        if (paramMap != null) {
            Iterator<Map.Entry<String, String>> itStr = paramMap.entrySet().iterator();
            while (itStr.hasNext()) {
                Map.Entry<String, String> entryParam = itStr.next();
                body.addFormDataPart(entryParam.getKey(), entryParam.getValue());
            }
        }

        Request.Builder builer = new Request.Builder();
        if (header != null) {
            Set<Map.Entry<String, String>> headerSet = header.entrySet();
            for (Map.Entry<String, String> entry : headerSet) {
                builer.addHeader(entry.getKey(), entry.getValue());
            }
        }
//        builer.addHeader("Connection", "close");
        Request request = builer.post(body.build())
                .url(url).build();


//        Request request = new Request.Builder()
//                .post(body.build())
//                .url(url).build();


        try {
//            OkHttpClient client = new OkHttpClient.Builder()
//                    .connectTimeout(30L, TimeUnit.SECONDS)
//                    .connectionPool(new ConnectionPool(5, 1, TimeUnit.SECONDS))
//                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String postForm(String url, Map<String, String> paramMap) {
        return postForm(url, null, paramMap);

    }

    public static String postForm(String url, Map<String, String> header, Map<String, String> paramMap) {

        LogUtils.log("=====url===" + url);
        LogUtils.logJson(header);
        LogUtils.logJson(paramMap);

        FormBody.Builder body = new FormBody.Builder();


        if (paramMap != null) {
            Iterator<Map.Entry<String, String>> itStr = paramMap.entrySet().iterator();
            while (itStr.hasNext()) {
                Map.Entry<String, String> entryParam = itStr.next();
                body.add(entryParam.getKey(), entryParam.getValue());
            }
        }

        Request.Builder builer = new Request.Builder()
                .post(body.build())
                .url(url);

        if (header != null) {
            Set<Map.Entry<String, String>> headerSet = header.entrySet();
            for (Map.Entry<String, String> entry : headerSet) {
                builer.addHeader(entry.getKey(), entry.getValue());
            }

        }

        try {

            Response response = client.newCall(builer.build()).execute();


            return response.body().string();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String postFormWithImge(String url, Map<String, File> fileMap, Map<String, String> paramMap) {

        MultipartBody.Builder body = new MultipartBody.Builder();
        body.setType(FORM);
        Iterator<Map.Entry<String, File>> it = fileMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, File> entry = it.next();
            RequestBody requestBody = RequestBody.create(OCTETFORM, entry.getValue());
            body.addFormDataPart(entry.getKey(), entry.getValue().getName(), requestBody);
        }

        Iterator<Map.Entry<String, String>> itStr = paramMap.entrySet().iterator();
        while (itStr.hasNext()) {
            Map.Entry<String, String> entryParam = itStr.next();
            body.addFormDataPart(entryParam.getKey(), entryParam.getValue());
        }

        Request request = new Request.Builder()
//                .addHeader("Connection", "close")
                .post(body.build())
                .url(url).build();


        try {
//            OkHttpClient client = new OkHttpClient.Builder()
//                    .connectTimeout(30L, TimeUnit.SECONDS)
//                    .connectionPool(new ConnectionPool(5, 1, TimeUnit.SECONDS))
//                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String postFormWithBytes(String url, Map<String, byte[]> fileMap, Map<String, String> paramMap, Map<String, String> headerMap) {

        MultipartBody.Builder body = new MultipartBody.Builder();
        body.setType(FORM);
        Iterator<Map.Entry<String, byte[]>> it = fileMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, byte[]> entry = it.next();
            RequestBody requestBody = RequestBody.create(OCTETFORM, entry.getValue());
            body.addFormDataPart(entry.getKey(), null, requestBody);
        }

        Iterator<Map.Entry<String, String>> itStr = paramMap.entrySet().iterator();
        while (itStr.hasNext()) {
            Map.Entry<String, String> entryParam = itStr.next();
            body.addFormDataPart(entryParam.getKey(), entryParam.getValue());
        }


        Request.Builder builer = new Request.Builder();

        Set<Map.Entry<String, String>> headerSet = headerMap.entrySet();
        for (Map.Entry<String, String> entry : headerSet) {
            builer.addHeader(entry.getKey(), entry.getValue());
        }

        Request request = builer.post(body.build())
                .url(url).build();

        try {
//            OkHttpClient client = new OkHttpClient.Builder()
//                    .connectTimeout(30L, TimeUnit.SECONDS)
//                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public static String postFormWithBytes(String url, Map<String, byte[]> fileMap, Map<String, String> paramMap) {

        MultipartBody.Builder body = new MultipartBody.Builder();
        body.setType(FORM);
        Iterator<Map.Entry<String, byte[]>> it = fileMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, byte[]> entry = it.next();
            RequestBody requestBody = RequestBody.create(OCTETFORM, entry.getValue());
            body.addFormDataPart(entry.getKey(), null, requestBody);
        }

        Iterator<Map.Entry<String, String>> itStr = paramMap.entrySet().iterator();
        while (itStr.hasNext()) {
            Map.Entry<String, String> entryParam = itStr.next();
            body.addFormDataPart(entryParam.getKey(), entryParam.getValue());
        }

        Request request = new Request.Builder()
                .post(body.build())
                .url(url).build();


        try {
//            OkHttpClient client = new OkHttpClient.Builder()
//                    .connectTimeout(30L, TimeUnit.SECONDS)
//                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String get(String url) {
        return get(url, null);
    }

    public static String get(String url, Map<String, String> headerMap) {

        Request.Builder builer = new Request.Builder()
                .get()
                .url(url);

        if (headerMap != null && !headerMap.isEmpty()) {
            Set<Map.Entry<String, String>> headerSet = headerMap.entrySet();
            for (Map.Entry<String, String> entry : headerSet) {
                builer.addHeader(entry.getKey(), entry.getValue());
            }
        }

        Request request = builer.build();
        try {
//            OkHttpClient client = new OkHttpClient.Builder()
//                    .connectTimeout(30L, TimeUnit.SECONDS)
//                    .readTimeout(30L, TimeUnit.SECONDS)
//                    .writeTimeout(30L, TimeUnit.SECONDS)
//                    .connectionPool(new ConnectionPool(5, 1, TimeUnit.SECONDS))
//                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String delete(String url, Map<String, String> headerMap) {
        Request.Builder builder = new Request.Builder().url(url).delete();
        if (headerMap != null && !headerMap.isEmpty()) {
            Set<Map.Entry<String, String>> headerSet = headerMap.entrySet();
            for (Map.Entry<String, String> entry : headerSet) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }

        Request request = builder.build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
