package com.example.demo.bean;

public enum ResponseCode {

    /**
     * ######通用码值
     未登录 -1001
     没权限 -1002
     系统错误 -1000
     **/
    SYS_EXCEPTION(-1000,"系统繁忙，请稍后再试"),
    INVALID_TOKEN(-1001,"请登录"),
    NO_PERMISSION(-1002,"没有足够的权限"),
    INVALID_PARAM(-1003,"无效的参数"),
    APP_VERSION_UPDATE(-1006,"App 版本强制更新"),
    RETURN_EMPTY(-1007,"返回结果为空"),
    TOO_MANY_REQUESTS(-1005,"接口请求频率超过最大次数"),
    APPSSID_IS_EXIST(-1008,"此租户id已存在"),
    NOTIFICATION(4000,"前端不判断，直接弹出提示语"),

    //1000 通用代码
    SUCCESS(1000, "成功"),


    /**
     * 订单 2000+
     * */
    TRADE_NOTIFICATION(2000,"bad request"),
    ALREADY_CONFIRMED(2030,"该货盘已生成订单,不能重复配载"),
    /**
     * 用户 3000+
     * */
    TRACE_NOTIFICATION(3000,"bad request"),

    AUTH_USER_FAIL(3001,"用户身份不存在"),
    NICK_PASSWORD_FAIL(3002,"用户登陆密码错误"),



    GATE_INVALID_PARAM(-1010,"参数有误!"),
    GATE_INVALID_SIGN(-1011,"签名有误!"),
    UC_ERROR_DATAFORMAT(-1012,"接口返回格式有改动"),
    UC_ERROR_HTTPTIMEOUT(-1013,"接口调用失败"),

    APPLY_REDUCTION_AMT_FAILED(5002,"applyReduction failed"),
    UC_ERROR_USERDISABLE(-1014,"当前登录用户已被禁用!"),

    FAIL(5000, "失败");
    private int code;

    private String name;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    ResponseCode(int code, String name) {
        this.code = code;
        this.name = name;
    }

}
