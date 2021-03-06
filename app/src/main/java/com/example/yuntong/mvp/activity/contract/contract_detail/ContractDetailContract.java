package com.example.yuntong.mvp.activity.contract.contract_detail;


import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BasePresenter;
import com.example.yuntong.base.BaseView;
import com.example.yuntong.bean.ContractDetailBean;


/**
 * @author 杨晓峰 
 * @create 2019/4/25
 * @Describe
 */

public interface ContractDetailContract {
    interface View extends BaseView<presenter> {
        void setContent(String content);  //设置内容
        void getData(BaseEntry<ContractDetailBean> baseEntry);

    }

    interface presenter extends BasePresenter {
        void getContractDetailData(String url);
    }
}
