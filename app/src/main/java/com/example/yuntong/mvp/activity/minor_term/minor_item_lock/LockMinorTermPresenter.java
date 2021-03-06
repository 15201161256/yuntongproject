package com.example.yuntong.mvp.activity.minor_term.minor_item_lock;

import android.content.Context;
import android.os.Handler;

import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BaseObserver;
import com.example.yuntong.bean.EditMinorTermBean;
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

public class LockMinorTermPresenter implements LockMinorTermContract.presenter {

    private Context context;
    private LockMinorTermContract.View view;

    public LockMinorTermPresenter(Context context, LockMinorTermContract.View view) {
        this.context = context;
        this.view = view;
    }


    @Override
    public void overOrder(String url) {
        RetrofitUtil.getInstance().initRetrofit().overMinorTerm(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<EditMinorTermBean>(context, MainUtil.loadLock) {
                    @Override
                    protected void onSuccees(final BaseEntry<EditMinorTermBean> t) throws Exception {
                        if (t.isSuccess()) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    view.getData(t);
                                }
                            }, 500);

                        } else {
                            view.setContent(t.getCode() + "----->" + t.getMessage());
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.setContent("失败了----->" + e.getMessage());
                    }
                });
    }

    @Override
    public void editOrder(String id, String name, String cooperator, String startDate,
                          String checkAndAcceptDate,String amount,String secondPartyConstructionCost,
                          String firstPartyMaterialCost,String secondPartyMaterialCost,String secondPartySafeProductionCost,
                          String stipulatedFee,String amountCashed,String secondPartyAuditFee,String secondPartyDeductedAmount,
                          String taxAmount,String managementFee) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("cooperator", cooperator);
        map.put("startDate", startDate);
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

        RetrofitUtil.getInstance().initRetrofit().editMinorTerm(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<EditMinorTermBean>(context, MainUtil.loadPost) {
                    @Override
                    protected void onSuccees(final BaseEntry<EditMinorTermBean> t) throws Exception {
                        if (t.isSuccess()) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    view.getEditData(t);
                                }
                            }, 500);
                        } else {
                            view.setEditContent(t.getMessage() + "");
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.setEditContent("失败了");
                    }
                });
    }

}

