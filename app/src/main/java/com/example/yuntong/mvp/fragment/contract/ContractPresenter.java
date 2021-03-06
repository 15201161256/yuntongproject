package com.example.yuntong.mvp.fragment.contract;

import android.content.Context;

import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BaseObserver;
import com.example.yuntong.bean.ContractListEntity;
import com.example.yuntong.bean.LoginEntity;
import com.example.yuntong.mvp.activity.login.LoginContract;
import com.example.yuntong.utils.GsonUtil;
import com.example.yuntong.utils.MainUtil;
import com.example.yuntong.utils.RetrofitUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @author 杨晓峰
 * @create 2019/4/16
 * @Describe
 */

public class ContractPresenter implements ContractContract.presenter {

    private Context context;
    private ContractContract.View view;

    public ContractPresenter(Context context, ContractContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void contractList(int pageNumber, int pageSize) {
        RetrofitUtil.getInstance().initRetrofit().getContractList(pageNumber,pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ContractListEntity>(context, MainUtil.loadTxt) {
                    @Override
                    protected void onSuccees(BaseEntry<ContractListEntity> t) throws Exception {
                        if (t.isSuccess()) {
                            view.getLoginData(t);
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
