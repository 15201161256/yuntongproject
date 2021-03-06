package com.example.yuntong.mvp.activity.minor_term.minor_term_add;

import android.content.Context;
import android.os.Handler;

import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BaseObserver;
import com.example.yuntong.bean.MinorTermAddBean;
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

public class MinortermAddPresenter implements MinortermAddContract.presenter {

    private Context context;
    private MinortermAddContract.View view;

    public MinortermAddPresenter(Context context, MinortermAddContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void creatMinorterm(String contractOrderId, String name, String cooperator, String startDate,
                               String finishDate,String checkAndAcceptDate,String amount,String secondPartyConstructionCost,
                               String firstPartyMaterialCost,String secondPartyMaterialCost,String secondPartySafeProductionCost,
                               String stipulatedFee,String amountCashed,String secondPartyAuditFee,String secondPartyDeductedAmount,
                               String taxAmount,String managementFee) {
        Map<String, String> map = new HashMap<>();
        map.put("contractOrderId", contractOrderId);
        map.put("name", name);
        map.put("cooperator", cooperator);
        map.put("startDate", startDate);
        map.put("finishDate", finishDate);
        map.put("checkAndAcceptDate", checkAndAcceptDate);
        map.put("amount", amount);
        map.put("secondPartyConstructionCost", secondPartyConstructionCost);
        map.put("firstPartyMaterialCost", firstPartyMaterialCost);
        map.put("secondPartyMaterialCost", secondPartyMaterialCost);
        map.put("secondPartySafeProductionCost", secondPartySafeProductionCost);
        map.put("stipulatedFee", stipulatedFee);
        map.put("amountCashed", amountCashed);
        map.put("secondPartyAuditFee", secondPartyAuditFee);
        map.put("secondPartyDeductedAmount", secondPartyDeductedAmount);
        map.put("taxAmount", taxAmount);
        map.put("managementFee", managementFee);
        RetrofitUtil.getInstance().initRetrofit().creatMinor(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MinorTermAddBean>(context, MainUtil.loadPost) {
                    @Override
                    protected void onSuccees(final BaseEntry<MinorTermAddBean> t) throws Exception {
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
