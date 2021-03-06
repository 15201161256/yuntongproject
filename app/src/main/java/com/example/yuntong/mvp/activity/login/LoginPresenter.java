package com.example.yuntong.mvp.activity.login;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BaseObserver;
import com.example.yuntong.bean.LoginEntity;
import com.example.yuntong.utils.GsonUtil;
import com.example.yuntong.utils.MainUtil;
import com.example.yuntong.utils.RetrofitUtil;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @author 杨晓峰
 * @create 2019/4/16
 * @Describe
 */

public class LoginPresenter implements LoginContract.presenter {

    private Context context;
    private LoginContract.View view;

    public LoginPresenter(Context context, LoginContract.View view) {
        this.context = context;
        this.view = view;
    }

    /**
     * 登录
     *
     * @param user
     * @param pwd
     */
    @Override
    public void userLogin(final String user, final String pwd) {

        Map<String, String> map = new HashMap<>();
        map.put("username", user);
        map.put("password", pwd);

        RetrofitUtil.getInstance().initRetrofit().userLogin(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<LoginEntity>(context, MainUtil.loadLogin) {
                    @Override
                    protected void onSuccees(final BaseEntry<LoginEntity> t) throws Exception {
                        if (t.isSuccess()) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    view.getLoginData(t);
                                }
                            }, 500);

                        } else {
                            view.setContent(t.getCode() + "----->" + t.getMessage());
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.setContent("失败了----->" + e.getMessage());
                    }
                });
    }
}
