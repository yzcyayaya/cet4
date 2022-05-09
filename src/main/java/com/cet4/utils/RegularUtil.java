package com.cet4.utils;

/**
 * 正则表达式工具类
 */
public class RegularUtil {

    //电话正则
    private static String phoneReg = "1[3456789]\\d{9}";
    //邮箱正则
    private static String emailReg = "\\w{1,}@(\\w+\\.)+\\w+";

    public static boolean regularPhone(String data) {
        if (data.matches(phoneReg)) {
            return true;
        }
        return false;
    }

    public static boolean regularEmail(String data) {
        if (data.matches(emailReg)) {
            return true;
        }
        return false;
    }
}
