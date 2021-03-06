package com.example.yuntong.mvp.activity.order.order_finance;

import android.content.Context;
import android.os.Handler;

import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BaseObserver;
import com.example.yuntong.bean.OrderDetailBean;
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

public class FinancePresenter implements FinanceContract.presenter {

    private Context context;
    private FinanceContract.View view;

    public FinancePresenter(Context context, FinanceContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void creatFinance(String id,  String invoiceAmount,

                             String amount1, String paidTime1,
                             String amount2, String paidTime2,
                             String amount3, String paidTime3) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("invoiceAmount", invoiceAmount);
        map.put("amount1", amount1);
        map.put("paidTime1", paidTime1);
        map.put("amount2", amount2);
        map.put("paidTime2", paidTime2);
        map.put("amount3", amount3);
        map.put("paidTime3", paidTime3);

        RetrofitUtil.getInstance().initRetrofit().createUpdateFinance(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<OrderDetailBean>(context, MainUtil.loadPost) {
                    @Override
                    protected void onSuccees(final BaseEntry<OrderDetailBean> t) throws Exception {
                        if (t.isSuccess()) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    view.getData(t);
                                }
                            }, 500);
                        } else {
                            view.setContent(t.getMessage() + "");
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.setContent("失败了");
                    }
                });


    }
}
