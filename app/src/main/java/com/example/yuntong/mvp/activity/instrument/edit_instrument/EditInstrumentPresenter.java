package com.example.yuntong.mvp.activity.instrument.edit_instrument;

import android.content.Context;
import android.os.Handler;

import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BaseObserver;
import com.example.yuntong.bean.EditInstrumentBean;
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

public class EditInstrumentPresenter implements EditInstrumentContract.presenter {

    private Context context;
    private EditInstrumentContract.View view;

    public EditInstrumentPresenter(Context context, EditInstrumentContract.View view) {
        this.context = context;
        this.view = view;
    }


    @Override
    public void editInstrument(String id, String name, String type, String purchaseTime, String borrower,
                               String startTime, String endTime, String remark) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("type", type);
        map.put("purchaseTime", purchaseTime);
        map.put("borrower", borrower);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("remark", remark);

        RetrofitUtil.getInstance().initRetrofit().editInsutrument(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<EditInstrumentBean>(context, MainUtil.loadmin) {
                    @Override
                    protected void onSuccees(final BaseEntry<EditInstrumentBean> t) throws Exception {
                        if (t.isSuccess()) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    view.getEditData(t);
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
