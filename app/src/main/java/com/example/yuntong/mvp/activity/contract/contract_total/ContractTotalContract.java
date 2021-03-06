package com.example.yuntong.mvp.activity.contract.contract_total;


import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BasePresenter;
import com.example.yuntong.base.BaseView;
import com.example.yuntong.bean.BaseBean;
import com.example.yuntong.bean.ContractToatalBean;


/**
 * @author 杨晓峰
 * @create 2019/5/8
 * @Describe
 */

public interface ContractTotalContract {
    interface View extends BaseView<presenter> {
        void setContent(String content);  //设置内容
        void getData(BaseEntry<ContractToatalBean> baseEntry);
    }

    interface presenter extends BasePresenter {
        void contractMiTotal(String endTimeBegin, String endTimeEnd,String firstPartyName,String cooperator,String end);
    }
}
