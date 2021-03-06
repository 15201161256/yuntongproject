package com.example.yuntong.mvp.activity.minor_term.minor_term_list;

import android.content.Context;

import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BaseObserver;
import com.example.yuntong.bean.MinorTermBean;
import com.example.yuntong.utils.MainUtil;
import com.example.yuntong.utils.RetrofitUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;



/**
 * @author 杨晓峰
 * @create 2019/4/16
 * @Describe
 */

public class MinorTermListPresenter implements MinorTermListContract.presenter {

    private Context context;
    private MinorTermListContract.View view;

    public MinorTermListPresenter(Context context, MinorTermListContract.View view) {
        this.context = context;
        this.view = view;
    }



    @Override
    public void getMinorTermList(int pageNumber, int pageSize, String contractOrderId) {
        RetrofitUtil.getInstance().initRetrofit().getMinorTermList(pageNumber,pageSize,contractOrderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MinorTermBean>(context, MainUtil.loadTxt) {
                    @Override
                    protected void onSuccees(BaseEntry<MinorTermBean> t) throws Exception {
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
