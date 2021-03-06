package com.example.yuntong.mvp.activity.order.add_order;

import android.content.Context;
import android.os.Handler;

import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BaseObserver;
import com.example.yuntong.bean.OrderAddBean;
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

public class AddOrderPresenter implements AddOrderContract.presenter {

    private Context context;
    private AddOrderContract.View view;

    public AddOrderPresenter(Context context, AddOrderContract.View view) {
        this.context = context;
        this.view = view;
    }

    /**
     * @param contractId  合同ID
     * @param orderNo     订单编号
     * @param orderName   订单名称
     * @param bigItemNo   大项编号
     * @param orderAmount 订单金额
     */
    @Override
    public void creatOrder(String contractId, String orderNo, String orderName,
                           String bigItemNo, String orderAmount,String startTime
    ,String missNo, String taxRate) {
        Map<String, String> map = new HashMap<>();
        map.put("contractId", contractId);
        map.put("orderNo", orderNo);
        map.put("orderName", orderName);
        map.put("bigItemNo", bigItemNo);
        map.put("orderAmount", orderAmount);
        map.put("startTime", startTime);
        map.put("missNo", missNo);
        map.put("taxRate", taxRate);

        RetrofitUtil.getInstance().initRetrofit().creatOrder(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<OrderAddBean>(context, MainUtil.loadPost) {
                    @Override
                    protected void onSuccees(final BaseEntry<OrderAddBean> t) throws Exception {
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
