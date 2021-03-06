package com.example.yuntong.mvp.activity.contract.lock_contract;

import android.content.Context;
import android.os.Handler;

import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BaseObserver;
import com.example.yuntong.bean.BaseBean;
import com.example.yuntong.bean.ContractListEntity;
import com.example.yuntong.bean.EditContractBean;
import com.example.yuntong.mvp.activity.affirmpsd.AffirmPsdContract;
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

public class LockContractPresenter implements LockContractContract.presenter {

    private Context context;
    private LockContractContract.View view;

    public LockContractPresenter(Context context, LockContractContract.View view) {
        this.context = context;
        this.view = view;
    }


    @Override
    public void overContract(String url) {
        RetrofitUtil.getInstance().initRetrofit().overContract(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<EditContractBean>(context, MainUtil.loadLock) {
                    @Override
                    protected void onSuccees(final BaseEntry<EditContractBean> t) throws Exception {
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
    public void editContract(String id, String region, String firstPartyName,
                             String secondPartyName, String contractNo, String contractName,
                             String signTime, String startTime,String contractAmount) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("region", region);
        map.put("firstPartyName", firstPartyName);
        map.put("secondPartyName", secondPartyName);
        map.put("contractNo", contractNo);
        map.put("contractName", contractName);
        map.put("signTime", signTime);
        map.put("startTime", startTime);
        map.put("contractAmount", contractAmount);

        RetrofitUtil.getInstance().initRetrofit().editContract(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<EditContractBean>(context, MainUtil.loadPost) {
                    @Override
                    protected void onSuccees(final BaseEntry<EditContractBean> t) throws Exception {
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
