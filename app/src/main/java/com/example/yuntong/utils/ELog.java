package com.example.yuntong.utils;


import android.util.Log;

import com.example.yuntong.app.ConnData;


/**
 * @author 杨晓峰
 * @create 2018/11/13
 * @Describe 日志打印类
 */
public class ELog {
    /**
     * 普通信息
     */
    public static void d(String tag, String msg) {
        if (!ConnData.isProductEnvironment) {//不是生产环境
            Log.d(tag, msg);
        }
    }

    /**
     * 错误信息
     */
    public static void e(String tag, String msg) {
        if (!ConnData.isProductEnvironment) {//不是生产环境
            Log.e(tag, msg);
        }
    }

    /**
     * System.out
     */
    public static void print(String msg) {
        if (!ConnData.isProductEnvironment) {//不是生产环境
            System.out.println(msg);
        }
    }
}
