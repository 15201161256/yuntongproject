package com.example.yuntong.mvp.activity.contract.contract_total_minor_list;

import android.content.Context;

import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BaseObserver;
import com.example.yuntong.bean.MinorTermBean;
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

public class ContractTotalListPresenter implements ContractTotalListContract.presenter {

    private Context context;
    private ContractTotalListContract.View view;

    public ContractTotalListPresenter(Context context, ContractTotalListContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getMinorTermList(int pageNumber, int pageSize, String endTimeBegin, String endTimeEnd,
                                 String firstPartyName,String end,String cooperator) {

        Map<String, String> map = new HashMap<>();
        if (end.equals("true")){
            map.put("endTimeBegin", endTimeBegin);
        }else {
            map.put("createTimeBegin", endTimeBegin);
        }
        map.put("pageNumber", pageNumber+"");
        map.put("pageSize", pageSize+"");
        map.put("endTimeEnd", endTimeEnd);
        map.put("firstPartyName", firstPartyName);
        map.put("end", end);
        map.put("cooperator", cooperator);
        RetrofitUtil.getInstance().initRetrofit().contractMiTotalList(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MinorTermBean>(context, MainUtil.loadTxt) {
                    @Override
                    protected void onSuccees(BaseEntry<MinorTermBean> t) throws Exception {
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
