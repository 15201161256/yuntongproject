package com.example.yuntong.network;


import com.example.yuntong.app.MyApp;
import com.example.yuntong.utils.SharePreferencesUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author 杨晓峰
 * @create 2019/5/14
 * @Describe 读取cookie
 */

public class CookieReadInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("Cookie", SharePreferencesUtils.getString(MyApp.myApp, "cookiess", ""));
        return chain.proceed(builder.build());
    }
}
