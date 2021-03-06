package com.example.yuntong.mvp.activity.contract.contract_total;

import android.content.Context;
import android.os.Handler;

import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BaseObserver;
import com.example.yuntong.bean.BaseBean;
import com.example.yuntong.bean.ContractToatalBean;
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

public class ContratTotalPresenter implements ContractTotalContract.presenter {

    private Context context;
    private ContractTotalContract.View view;

    public ContratTotalPresenter(Context context, ContractTotalContract.View view) {
        this.context = context;
        this.view = view;
    }


    @Override
    public void contractMiTotal(String endTimeBegin, String endTimeEnd,String firstPartyName,String cooperator,String end) {
        Map<String, String> map = new HashMap<>();
        if (end.equals("true")){
            map.put("endTimeBegin", endTimeBegin);
        }else {
            map.put("createTimeBegin", endTimeBegin);
        }
        map.put("endTimeEnd", endTimeEnd);
        map.put("firstPartyName", firstPartyName);
        map.put("cooperator", cooperator);
        map.put("end", end);


        RetrofitUtil.getInstance().initRetrofit().contractMiTotal(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ContractToatalBean>(context, MainUtil.loadinquire) {
                    @Override
                    protected void onSuccees(final BaseEntry<ContractToatalBean> t) throws Exception {
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
