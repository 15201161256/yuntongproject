package com.example.yuntong.mvp.activity.contract.add_contract;


import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BasePresenter;
import com.example.yuntong.base.BaseView;
import com.example.yuntong.bean.EditContractBean;

/**
 * @author 杨晓峰
 * @create 2019/5/14
 * @Describe
 */

public interface AddContractContract {
    interface View extends BaseView<presenter> {
        void setContent(String content);  //设置内容

        void getData(BaseEntry<EditContractBean> data);  //设置内容

    }

    interface presenter extends BasePresenter {

        void creatContract(String region, String firstPartyName, String secondPartyName,
                           String contractNo, String contractName,  String signTime, String startTime,String contractAmount);
    }
}
