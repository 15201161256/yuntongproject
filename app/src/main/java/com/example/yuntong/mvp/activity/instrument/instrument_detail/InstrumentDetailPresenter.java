package com.example.yuntong.mvp.activity.instrument.instrument_detail;

import android.content.Context;

import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BaseObserver;
import com.example.yuntong.bean.ContractDetailBean;
import com.example.yuntong.bean.InstrumentDetailBean;
import com.example.yuntong.utils.MainUtil;
import com.example.yuntong.utils.RetrofitUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @author 杨晓峰
 * @create 2019/4/16
 * @Describe
 */

public class InstrumentDetailPresenter implements InstrumentDetailContract.presenter {

    private Context context;
    private InstrumentDetailContract.View view;

    public InstrumentDetailPresenter(Context context, InstrumentDetailContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getInstrumentDetailData(String url) {
        RetrofitUtil.getInstance().initRetrofit().getInstrumentDetailData(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<InstrumentDetailBean>(context, MainUtil.loadTxt) {
                    @Override
                    protected void onSuccees(BaseEntry<InstrumentDetailBean> t) throws Exception {
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
