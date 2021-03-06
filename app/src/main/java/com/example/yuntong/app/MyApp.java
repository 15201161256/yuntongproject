package com.example.yuntong.app;

import android.app.Activity;
import android.app.Application;


import com.example.yuntong.network.CookieReadInterceptor;
import com.example.yuntong.network.CookiesSaveInterceptor;
import com.example.yuntong.utils.InterceptorUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @author 杨晓峰 
 * @create 2019/5/14
 * @Describe 
 */

public class MyApp extends Application {
    public static MyApp myApp;
    public static final int TIMEOUT = 60;
    private static OkHttpClient mOkHttpClient;
    private Stack<Activity> mActivityStack = new Stack<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
    }

    /**
     * 全局httpclient
     *
     * @return
     */
    public static OkHttpClient initOKHttp() {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)//设置连接超时时间
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS)//设置读取超时时间
                    .writeTimeout(TIMEOUT, TimeUnit.SECONDS)//设置写入超时时间
                    .addInterceptor(InterceptorUtil.LogInterceptor())//添加日志拦截器
                    //cookie
                    .addInterceptor(new CookieReadInterceptor())
                    .addInterceptor(new CookiesSaveInterceptor())
                    .build();
        }
        return mOkHttpClient;
    }


    public static Map<String, Activity> get_activity;

    public static void finsh_activity(String name) {
        if (get_activity.containsKey(name)) {
            if (!get_activity.get(name).isFinishing()) {
                get_activity.get(name).finish();
            }
        }
    }
    /*
       * 销毁activity的方法
       * */ {
        if (get_activity == null) {
            get_activity = new HashMap<>();
        }
    }
    /**
     * Remove the activity stack
     *
     * @param activity
     */
    public void pop(Activity activity) {
        if (mActivityStack != null) {
            mActivityStack.remove(activity);
            activity = null;
        }
    }

    /**
     * Add the activity stack
     *
     * @param activity
     */
    public void push(Activity activity) {
        if (mActivityStack != null) {
            mActivityStack.push(activity);
        }
    }

    /**
     * Exit the application
     */
    public void exitApp() {
        int activityNum = 0;
        while (!mActivityStack.empty()) {
            Activity activity = mActivityStack.peek();
            if (activity != null) {
                activity.finish();
                pop(activity);
                activityNum++;
            }
        }
    }

}
