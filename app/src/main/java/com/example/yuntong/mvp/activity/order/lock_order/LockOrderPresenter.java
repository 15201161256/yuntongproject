package com.example.yuntong.mvp.activity.order.lock_order;

import android.content.Context;
import android.os.Handler;

import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BaseObserver;
import com.example.yuntong.bean.EditContractBean;
import com.example.yuntong.bean.EditOrderBean;
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

public class LockOrderPresenter implements LockOrderContract.presenter {

    private Context context;
    private LockOrderContract.View view;

    public LockOrderPresenter(Context context, LockOrderContract.View view) {
        this.context = context;
        this.view = view;
    }


    @Override
    public void overOrder(String url) {
        RetrofitUtil.getInstance().initRetrofit().overOrder(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<EditOrderBean>(context, MainUtil.loadLock) {
                    @Override
                    protected void onSuccees(final BaseEntry<EditOrderBean> t) throws Exception {
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

    @Override
    public void editOrder(String id, String orderNo, String orderName, String bigItemNo,
                          String orderAmount, String startTime ,String missNo, String taxRate) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("orderNo", orderNo);
        map.put("orderName", orderName);
        map.put("bigItemNo", bigItemNo);
        map.put("orderAmount", orderAmount);
        map.put("startTime", startTime);
        map.put("missNo", missNo);
        map.put("taxRate", taxRate);

        RetrofitUtil.getInstance().initRetrofit().editOrder(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<EditOrderBean>(context, MainUtil.loadPost) {
                    @Override
                    protected void onSuccees(final BaseEntry<EditOrderBean> t) throws Exception {
                        if (t.isSuccess()) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    view.getEditData(t);
                                }
                            }, 500);
                        } else {
                            view.setEditContent(t.getMessage() + "");
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.setEditContent("失败了");
                    }
                });
    }


}
