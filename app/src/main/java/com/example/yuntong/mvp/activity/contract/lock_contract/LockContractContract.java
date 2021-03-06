package com.example.yuntong.mvp.activity.contract.lock_contract;


import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BasePresenter;
import com.example.yuntong.base.BaseView;
import com.example.yuntong.bean.BaseBean;
import com.example.yuntong.bean.ContractListEntity;
import com.example.yuntong.bean.EditContractBean;


/**
 * @author 杨晓峰
 * @create 2019/5/8
 * @Describe
 */

public interface LockContractContract {
    interface View extends BaseView<presenter> {
        void setContent(String content);  //设置内容

        void getData(BaseEntry<EditContractBean> baseEntry);

        void setEditContent(String content);

        void getEditData(BaseEntry<EditContractBean> baseEntry);
    }

    interface presenter extends BasePresenter {
        void overContract(String url);

        void editContract(String id, String region, String firstPartyName,
                          String secondPartyName, String contractNo, String contractName
                , String signTime, String startTime, String contractAmount);
    }
}
