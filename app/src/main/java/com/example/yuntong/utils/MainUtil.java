package com.example.yuntong.utils;

import android.util.Log;

/**
 * @author 杨晓峰 
 * @create 2019/5/14
 * @Describe 
 */

public class MainUtil {
    public static String logger = "logger";
    private static boolean isPrintLog = true; //是否打开日志打印

    //日志打印
    public static void printLogger(String logTxt) {
        if (isPrintLog) {
            Log.d(logger, logTxt);
        }
    }


    public static String SUCCESS_CODE = "200";//成功的code

    public static String loadTxt = "正在加载";
    public static String loadPut = "正在上传";
    public static String loadLogin = "正在登录";
    public static String loadPost = "正在创建";
    public static String loadLock = "正在锁定";
    public static String loadmin = "正在修改";
    public static String loadinquire = "正在查询";

}
