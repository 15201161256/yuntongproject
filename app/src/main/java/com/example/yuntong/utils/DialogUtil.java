package com.example.yuntong.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.yuntong.R;

import java.util.List;


/**
 * 类说明： 对话框工具类
 *
 * @author fangke
 * @version 1.0
 * @date 2014-7-22
 */
public class DialogUtil {
    private static Dialog loadingDialog;

    /**
     * 自定义内容的对话框
     *
     * @param ctx         上下文环境变量
     * @param message     对话框内容
     * @param posListener 确认按钮监听器
     * @return 对话框
     */
    public static Dialog showDialog(Context ctx, String message, OnClickListener posListener) {
        final Dialog dialog = new Dialog(ctx, R.style.myDialog);
        dialog.setContentView(R.layout.mydialog_style);
        TextView tvMessage = (TextView) dialog.getWindow().getDecorView().findViewById(R.id.tvMessage);
        tvMessage.setText(message);
        Button pos = (Button) dialog.getWindow().getDecorView().findViewById(R.id.btPos);
        pos.setOnClickListener(posListener);
        Button neg = (Button) dialog.getWindow().getDecorView().findViewById(R.id.btNeg);
        neg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.cancel();
            }
        });
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }


    /**
     * 自定义内容的对话框
     *
     * @param ctx         上下文环境变量
     * @param message     对话框内容
     * @return 对话框
     */
    public static Dialog showAlertDialog(Context ctx, String message) {
        final Dialog dialog = new Dialog(ctx, R.style.myDialog);
        dialog.setContentView(R.layout.mydialog_style1);
        TextView tvMessage = (TextView) dialog.getWindow().getDecorView().findViewById(R.id.tvMessage);
        tvMessage.setText(message);
        Button pos = (Button) dialog.getWindow().getDecorView().findViewById(R.id.btPos);
        pos.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
    /**
     * 自定义内容的对话框
     *
     * @param ctx         上下文环境变量
     * @param message     对话框内容
     * @return 对话框
     */
    public static Dialog showDialog2(final Context ctx, String message, OnClickListener posListener) {
        final Dialog dialog = new Dialog(ctx, R.style.myDialog);
        dialog.setContentView(R.layout.mydialog_style1);
        TextView tvMessage = (TextView) dialog.getWindow().getDecorView().findViewById(R.id.tvMessage);
        tvMessage.setText(message);
        Button pos = (Button) dialog.getWindow().getDecorView().findViewById(R.id.btPos);
        pos.setOnClickListener(posListener);
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }


}
