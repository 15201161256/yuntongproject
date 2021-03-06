package com.example.yuntong.mvp.fragment.materials;

import android.content.Context;

import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BaseObserver;
import com.example.yuntong.bean.MaterialsBean;
import com.example.yuntong.utils.MainUtil;
import com.example.yuntong.utils.RetrofitUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @author 杨晓峰
 * @create 2019/4/16
 * @Describe
 */

public class MaterialsPresenter implements MaterialsContract.presenter {

    private Context context;
    private MaterialsContract.View view;

    public MaterialsPresenter(Context context, MaterialsContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void materialsList(int pageNumber, int pageSize) {
        RetrofitUtil.getInstance().initRetrofit().getMaterialsList(pageNumber,pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MaterialsBean>(context, MainUtil.loadTxt) {
                    @Override
                    protected void onSuccees(BaseEntry<MaterialsBean> t) throws Exception {
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
