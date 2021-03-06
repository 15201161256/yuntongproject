package com.example.yuntong.base;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.yuntong.utils.ProgressHUD;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author 杨晓峰
 * @create 2019/4/16
 * @Describe 自定义Observer
 */

public abstract class BaseObserver<T> implements Observer<BaseEntry<T>> {
    protected Context mContext;
    private KProgressHUD progressHUD;
    private String labelTxt;


    public BaseObserver(Context cxt, String text) {
        this.mContext = cxt;
        this.labelTxt = text;
        progressHUD = ProgressHUD.show(mContext);
    }


    //开始
    @Override
    public void onSubscribe(Disposable d) {
        onRequestStart();
    }

    //获取数据
    @Override
    public void onNext(BaseEntry<T> tBaseEntity) {
        try {
            onSuccees(tBaseEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //失败
    @Override
    public void onError(Throwable e) {
        onRequestEnd();
        try {
            if (e instanceof ConnectException
                    || e instanceof TimeoutException
                    || e instanceof NetworkErrorException
                    || e instanceof UnknownHostException) {
                onFailure(e, true);  //网络错误
            } else {
                onFailure(e, false);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    //结束
    @Override
    public void onComplete() {
        onRequestEnd();//请求结束
    }

    /**
     * 返回成功
     *
     * @param t
     * @throws Exception
     */
    protected abstract void onSuccees(BaseEntry<T> t) throws Exception;


    /**
     * 返回失败
     *
     * @param e
     * @param isNetWorkError 是否是网络错误
     * @throws Exception
     */
    protected abstract void onFailure(Throwable e, boolean isNetWorkError) throws Exception;

    protected void onRequestStart() {
        if (progressHUD != null) {
            progressHUD.setLabel(labelTxt);
        }
    }

    protected void onRequestEnd() {
        if (progressHUD != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressHUD.dismiss();
                }
            }, 300);
//            progressHUD = null;
        }
    }

}
