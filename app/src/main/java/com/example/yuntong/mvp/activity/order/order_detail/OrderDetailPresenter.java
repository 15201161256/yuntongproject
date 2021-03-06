package com.example.yuntong.mvp.activity.order.order_detail;

import android.content.Context;

import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BaseObserver;
import com.example.yuntong.bean.OrderDetailBean;
import com.example.yuntong.utils.MainUtil;
import com.example.yuntong.utils.RetrofitUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @author 杨晓峰
 * @create 2019/4/16
 * @Describe
 */

public class OrderDetailPresenter implements OrderDetailContract.presenter {

    private Context context;
    private OrderDetailContract.View view;

    public OrderDetailPresenter(Context context, OrderDetailContract.View view) {
        this.context = context;
        this.view = view;
    }


    @Override
    public void getOrderDetailData(String url) {
        RetrofitUtil.getInstance().initRetrofit().getOrderDetailData(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<OrderDetailBean>(context, MainUtil.loadTxt) {
                    @Override
                    protected void onSuccees(BaseEntry<OrderDetailBean> t) throws Exception {
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
