package com.example.yuntong.mvp.activity.materals.put_materals_list;

import android.content.Context;

import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BaseObserver;
import com.example.yuntong.bean.MaterialsTypeBean;
import com.example.yuntong.utils.MainUtil;
import com.example.yuntong.utils.RetrofitUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @author 杨晓峰
 * @create 2019/4/16
 * @Describe
 */

public class MaterialsPutListPresenter implements MaterialsPutListContract.presenter {

    private Context context;
    private MaterialsPutListContract.View view;

    public MaterialsPutListPresenter(Context context, MaterialsPutListContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getList(int pageNumber, int pageSize, String materialId,String type) {
        RetrofitUtil.getInstance().initRetrofit().getMaPutList(pageNumber,pageSize,materialId,type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MaterialsTypeBean>(context, MainUtil.loadTxt) {
                    @Override
                    protected void onSuccees(BaseEntry<MaterialsTypeBean> t) throws Exception {
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
