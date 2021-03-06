package com.example.yuntong.mvp.activity.materals.materals_detail;

import android.content.Context;

import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BaseObserver;
import com.example.yuntong.bean.MaterialsAddBean;
import com.example.yuntong.utils.MainUtil;
import com.example.yuntong.utils.RetrofitUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 杨晓峰
 * @create 2019/4/16
 * @Describe
 */

public class MaterialsDetailPresenter implements MaterialsDetailContract.presenter {

    private Context context;
    private MaterialsDetailContract.View view;

    public MaterialsDetailPresenter(Context context, MaterialsDetailContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getMaterialsDetailData(String url) {
        RetrofitUtil.getInstance().initRetrofit().getMaterialsDetailData(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MaterialsAddBean>(context, MainUtil.loadTxt) {
                    @Override
                    protected void onSuccees(BaseEntry<MaterialsAddBean> t) throws Exception {
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
