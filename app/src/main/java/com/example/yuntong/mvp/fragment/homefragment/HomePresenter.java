package com.example.yuntong.mvp.fragment.homefragment;

import android.content.Context;

import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BaseObserver;
import com.example.yuntong.bean.HomeBean;
import com.example.yuntong.bean.MaterialPutBean;
import com.example.yuntong.utils.MainUtil;
import com.example.yuntong.utils.RetrofitUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 杨晓峰
 * @create 2019/4/16
 * @Describe
 */

public class HomePresenter implements HomeContract.presenter {

    private Context context;
    private HomeContract.View view;

    public HomePresenter(Context context, HomeContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getHomeData(String url) {
        RetrofitUtil.getInstance().initRetrofit().getHomeData(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<HomeBean>(context, MainUtil.loadTxt) {
                    @Override
                    protected void onSuccees(BaseEntry<HomeBean> t) throws Exception {
                        if (t.isSuccess()) {
                            view.getData(t);
                        } else {
                            view.setContent("提示：" + t.getCode() + "" + t.getMessage() + "");
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.setContent("失败了----->" + e.getMessage());
                    }
                });
    }
}
