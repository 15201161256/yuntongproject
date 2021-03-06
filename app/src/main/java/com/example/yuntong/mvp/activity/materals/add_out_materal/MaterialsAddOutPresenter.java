package com.example.yuntong.mvp.activity.materals.add_out_materal;

import android.content.Context;
import android.os.Handler;

import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BaseObserver;
import com.example.yuntong.bean.MaterialPutBean;
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

public class MaterialsAddOutPresenter implements MaterialsAddOutContract.presenter {

    private Context context;
    private MaterialsAddOutContract.View view;

    public MaterialsAddOutPresenter(Context context, MaterialsAddOutContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void creatMaterialsPut(String materialId, String type, String count, String auditor, String remark) {
        Map<String, String> map = new HashMap<>();
        map.put("materialId", materialId);
        map.put("type", type);//类型(1：出库, 2: 入库)
        map.put("count", count);
        map.put("auditor", auditor);
        map.put("remark", remark);
        RetrofitUtil.getInstance().initRetrofit().createMaterialsPut(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MaterialPutBean>(context, MainUtil.loadPost) {
                    @Override
                    protected void onSuccees(final BaseEntry<MaterialPutBean> t) throws Exception {
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
