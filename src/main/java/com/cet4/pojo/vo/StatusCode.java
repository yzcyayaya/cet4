package com.cet4.pojo.vo;

import java.util.HashMap;

public class StatusCode {

    public static final int SUCCESS = 200;
    public static final int ERROR = 500;
    public static final int InvalidParams = 400;

    //成员错误
    public static final int ErrorExistUser = 10002;
    public static final int ErrorNotExistUser = 10003;
    public static final int ErrorFailEncryption = 10006;
    public static final int ErrorNotCompare = 10007;


    public static final int ErrorAuthCheckTokenFail = 30001; //token 错误
    public static final int ErrorAuthCheckTokenTimeout = 30002; //token 过期
    public static final int ErrorAuthToken = 30003;
    public static final int ErrorAuth = 30004;


    public static final int ErrorDatabase = 40001;
    public static final int ErrorCaptcha = 40002;               //验证码错误

    private static HashMap<Integer, String> flagMap;

    private static void InitFlagMap() {
        flagMap = new HashMap<Integer, String>() {
            {
                flagMap.put(SUCCESS, "ok");
                flagMap.put(ERROR, "fail");
                flagMap.put(InvalidParams, "请求参数错误");
                flagMap.put(ErrorFailEncryption,"加密失败");
                //100xx
                flagMap.put(ErrorExistUser,"用户已存在");
                flagMap.put(ErrorNotExistUser,"用户不存在");

                flagMap.put(ErrorNotCompare, "不匹配");
                //300xx
                flagMap.put(ErrorAuthCheckTokenFail, "鉴权失败");
                flagMap.put(ErrorAuthCheckTokenTimeout, "登录已超时");
                flagMap.put(ErrorAuthToken, "Token生成失败");
                flagMap.put(ErrorAuth, "Token错误");

                //400xx
                flagMap.put(ErrorDatabase, "数据库操作出错,请重试");
                flagMap.put(ErrorCaptcha, "验证码错误");
            }
        };
    }

    public static String getMsg(int code) {
        InitFlagMap();
        //获取消息
        return flagMap.get(code);
    }
}
