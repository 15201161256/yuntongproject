package com.example.yuntong.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数字匹配器,用正则来判断是手机号还是邮箱
 */

public class NumberMatcher {

    /**
     * 验证邮箱输入是否合法
     *
     * @param strEmail
     * @return
     */
    public static boolean isEmail(String strEmail) {
// String strPattern =
// "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        String strPattern = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }

    /**
     * 验证是否是手机号码
     *
     * @param str
     * @return
     */
    public static boolean isMobile(String str) {
        Pattern p = Pattern.compile("^1[3|4|5|7|8][0-9]\\d{8}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /*
    * 是否是正确密码
    * 密码请输入6-16位字母、数字或符号组合
    * */
    public static boolean isPwd(String mobiles) {
        Pattern p = Pattern.compile("^(?!(?:\\d*$))[A-Za-z0-9]{6,16}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /*
    * 编辑框输入的时候不能输入0
    * */
    public static boolean isZro(String str) {
        Pattern p = Pattern.compile("^[0-9]*[1-9][0-9]*$");
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
