package com.example.yuntong.mvp.activity.affirmpsd;

import android.content.Context;
import android.os.Handler;

import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BaseObserver;
import com.example.yuntong.bean.BaseBean;
import com.example.yuntong.bean.LoginEntity;
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

public class AffirmPsdPresenter implements AffirmPsdContract.presenter {

    private Context context;
    private AffirmPsdContract.View view;

    public AffirmPsdPresenter(Context context, AffirmPsdContract.View view) {
        this.context = context;
        this.view = view;
    }


    @Override
    public void modificationPsd(String originalP, String newP) {
        Map<String, String> map = new HashMap<>();
        map.put("originalP", originalP);
        map.put("newP", newP);

        RetrofitUtil.getInstance().initRetrofit().modificationPsd(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseBean>(context, MainUtil.loadmin) {
                    @Override
                    protected void onSuccees(final BaseEntry<BaseBean> t) throws Exception {
                        if (t.isSuccess()) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    view.getData(t);
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
