package com.example.yuntong.mvp.fragment.instrument;

import android.content.Context;

import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BaseObserver;
import com.example.yuntong.bean.InstrumentBean;
import com.example.yuntong.utils.MainUtil;
import com.example.yuntong.utils.RetrofitUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @author 杨晓峰
 * @create 2019/4/16
 * @Describe
 */

public class InstrumentPresenter implements InstrumentContract.presenter {

    private Context context;
    private InstrumentContract.View view;

    public InstrumentPresenter(Context context, InstrumentContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void instrumentList(int pageNumber, int pageSize) {
        RetrofitUtil.getInstance().initRetrofit().getInstrumentList(pageNumber,pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<InstrumentBean>(context, MainUtil.loadTxt) {
                    @Override
                    protected void onSuccees(BaseEntry<InstrumentBean> t) throws Exception {
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
