package com.example.yuntong.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by 柴天天 on 2018/10/31.
 */

public class UiUtils {

    public static void showToast(Context context, int str) {
        showToast(context, context.getString(str));
    }

    public static void showToast(Context context, String str) {
        if (str != null || !str.isEmpty()) {
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        }

    }

    public static void IsStringEmpty(String str, TextView textView) {
        try {
            if (str != null || !str.isEmpty()) {
                textView.setText(str);
            } else {
                textView.setText("暂无数据");
            }
        } catch (NullPointerException e) {
            textView.setText("暂无数据");
        }

    }
    public static void IsStringEmpty2(String str, TextView textView) {
        try {
            if (str != null || !str.isEmpty()) {
                textView.setText(str);
            }
        } catch (NullPointerException e) {
        }

    }
    public static void IsStringEmptyPer(String str, TextView textView) {
        try {
            if (str != null || !str.isEmpty()) {
                textView.setText(str+"%");
            } else {
                textView.setText("暂无数据");
            }
        } catch (NullPointerException e) {
            textView.setText("暂无数据");
        }

    }
    public static void IsStringEmptyYuan(String str, TextView textView) {
        try {
            if (str != null || !str.isEmpty()) {
                textView.setText(str+"元");
            } else {
                textView.setText("0元");
            }
        } catch (NullPointerException e) {
            textView.setText("0元");
        }

    }
    public static void IsStringEmptyEt(String str, EditText textView) {
        try {
            if (str != null || !str.isEmpty()) {
                textView.setText(str);
            } else {
                textView.setText("暂无数据");
            }
        } catch (NullPointerException e) {
            textView.setText("暂无数据");
        }

    }

    /**
     * 判断当前网络是否连接
     *
     * @return
     */
    public static boolean isNetworkConnection(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }


}
