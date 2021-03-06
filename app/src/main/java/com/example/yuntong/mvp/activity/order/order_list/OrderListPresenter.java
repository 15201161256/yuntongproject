package com.example.yuntong.mvp.activity.order.order_list;

import android.content.Context;

import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BaseObserver;
import com.example.yuntong.bean.OrderListbean;
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

public class OrderListPresenter implements OrderListContract.presenter {

    private Context context;
    private OrderListContract.View view;

    public OrderListPresenter(Context context, OrderListContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getOrderList(int pageNumber, int pageSize,String contractId) {
        RetrofitUtil.getInstance().initRetrofit().getOrderList(pageNumber,pageSize,contractId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<OrderListbean>(context, MainUtil.loadTxt) {
                    @Override
                    protected void onSuccees(BaseEntry<OrderListbean> t) throws Exception {
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
