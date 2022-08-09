package com.example.demo.bean;

import java.io.Serializable;
import java.util.Map;

public class RestResponse<T> implements Serializable {

    private int code = ResponseCode.SUCCESS.getCode();

    private String msg = "request success";

    private T data;

    public RestResponse() {
    }

    public RestResponse(T data) {
        this.data = data;
    }

    public RestResponse(ResponseCode responseCode) {
        if (responseCode != null) {
            this.code = responseCode.getCode();
            this.msg = responseCode.getName();
        }
    }

    public RestResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private RestResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private RestResponse(int code, String message, Map<String, String> error, Object exception, T data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> RestResponse<T> response(int code, String message) {
        return new RestResponse<T>(code, message);
    }

    public static <T> RestResponse<T> response(int code, String message, T result) {
        return new RestResponse<T>(code, message, result);
    }

    public static <T> RestResponse<T> response(int code, String message,Map<String, String> error,T result) {
        return new RestResponse<T>(code, message,error,null, result);
    }

    public static <T> RestResponse<T> success() {
        return response(ResponseCode.SUCCESS.getCode(), "request success", null);
    }

    public static <T> RestResponse<T> success(T result) {
        return response(ResponseCode.SUCCESS.getCode(), "request success", result);
    }

    public static <T> RestResponse<T> success(String message) {
        return response(ResponseCode.SUCCESS.getCode(), message, null);
    }

    public static <T> RestResponse<T> success(String message, T result) {
        return response(ResponseCode.SUCCESS.getCode(), message, result);
    }

    public static <T> RestResponse<T> loginRequired() {
        return response(ResponseCode.INVALID_TOKEN.getCode(), ResponseCode.INVALID_TOKEN.getName(), null);
    }

    public static <T> RestResponse<T> caughtException() {
        return response(ResponseCode.SYS_EXCEPTION.getCode(), ResponseCode.SYS_EXCEPTION.getName(), null);
    }

    public static <T> RestResponse<T> caughtException(String message) {
        return response(ResponseCode.SYS_EXCEPTION.getCode(), message, null);
    }
    public static <T> RestResponse<T> caughtException(ResponseCode responseCode) {
        return response(responseCode.getCode(), responseCode.getName(), null);
    }

    public static <T> RestResponse<T> caughtException(String message, Map<String, String> error) {
        return response(ResponseCode.SYS_EXCEPTION.getCode(), message, error, null);
    }



    public static <T> RestResponse<T> fail(String message) {
        return response(ResponseCode.FAIL.getCode(), message, null);
    }

}
