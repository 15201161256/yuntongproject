package com.example.yuntong.network;

import com.example.yuntong.app.MyApp;
import com.example.yuntong.utils.SharePreferencesUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @author 杨晓峰 
 * @create 2019/5/14
 * @Describe 
 */
public class CookiesSaveInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            String header =originalResponse.header("Set-Cookie");
            SharePreferencesUtils.setString(MyApp.myApp,"cookiess",header);
        }
        return originalResponse;
    }

}
