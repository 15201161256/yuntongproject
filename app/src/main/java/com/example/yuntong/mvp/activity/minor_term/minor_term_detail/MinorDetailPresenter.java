package com.example.yuntong.mvp.activity.minor_term.minor_term_detail;

import android.content.Context;

import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BaseObserver;
import com.example.yuntong.bean.BaseBean;
import com.example.yuntong.bean.HomeBean;
import com.example.yuntong.bean.MinorTermDetailBean;
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

public class MinorDetailPresenter implements MinorDetailContract.presenter {

    private Context context;
    private MinorDetailContract.View view;

    public MinorDetailPresenter(Context context, MinorDetailContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getMinorDetailData(String url) {
        RetrofitUtil.getInstance().initRetrofit().getMinorDetailData(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MinorTermDetailBean>(context, MainUtil.loadTxt) {
                    @Override
                    protected void onSuccees(BaseEntry<MinorTermDetailBean> t) throws Exception {
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

    @Override
    public void endPayStatus(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("status", "true");
        RetrofitUtil.getInstance().initRetrofit().endPayStatus(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseBean>(context, MainUtil.loadPut) {
                    @Override
                    protected void onSuccees(BaseEntry<BaseBean> t) throws Exception {
                        if (t.isSuccess()) {
                            view.getDataEnd(t);
                        } else {
                            view.setContentEnd("提示：" + t.getCode() + "" + t.getMessage() + "");
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.setContentEnd("失败了----->" + e.getMessage());
                    }
                });
    }
}
