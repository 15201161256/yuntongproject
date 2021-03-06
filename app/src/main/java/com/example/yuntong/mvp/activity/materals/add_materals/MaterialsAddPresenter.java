package com.example.yuntong.mvp.activity.materals.add_materals;

import android.content.Context;
import android.os.Handler;

import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BaseObserver;
import com.example.yuntong.bean.MaterialsAddBean;
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

public class MaterialsAddPresenter implements MaterialsAddContract.presenter {

    private Context context;
    private MaterialsAddContract.View view;

    public MaterialsAddPresenter(Context context, MaterialsAddContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void creatMaterials(String name, String type, String unit, String remark) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("type", type);
        map.put("unit", unit);
        map.put("remark", remark);
        RetrofitUtil.getInstance().initRetrofit().createMaterials(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MaterialsAddBean>(context, MainUtil.loadPost) {
                    @Override
                    protected void onSuccees(final BaseEntry<MaterialsAddBean> t) throws Exception {
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
