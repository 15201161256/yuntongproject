package com.example.yuntong.mvp.activity.contract.add_contract;

import android.content.Context;
import android.os.Handler;

import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BaseObserver;
import com.example.yuntong.bean.ContractAddBean;
import com.example.yuntong.bean.EditContractBean;
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

public class AddContractPresenter implements AddContractContract.presenter {

    private Context context;
    private AddContractContract.View view;

    public AddContractPresenter(Context context, AddContractContract.View view) {
        this.context = context;
        this.view = view;
    }

    /**
     *
     * @param region 地区
     * @param firstPartyName 甲方名称
     * @param secondPartyName 乙方名称
     * @param contractNo 合同编号
     * @param contractName 合同名称
     * @param signTime 合同签订时间(yyyy-MM-dd)
     * @param startTime 合同开始时间(yyyy-MM-dd)
     */
    @Override
    public void creatContract(String region, String firstPartyName, String secondPartyName,
                              String contractNo, String contractName,
                              String signTime, String startTime,String contractAmount) {
        Map<String, String> map = new HashMap<>();

        map.put("region", region);
        map.put("firstPartyName", firstPartyName);
        map.put("secondPartyName", secondPartyName);
        map.put("contractNo", contractNo);
        map.put("contractName", contractName);
        map.put("signTime", signTime);
        map.put("startTime", startTime);
        map.put("contractAmount", contractAmount);

        RetrofitUtil.getInstance().initRetrofit().creatContract(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<EditContractBean>(context, MainUtil.loadPost) {
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
}
