package com.example.yuntong.mvp.activity.minor_term.minor_term_add;


import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BasePresenter;
import com.example.yuntong.base.BaseView;
import com.example.yuntong.bean.MinorTermAddBean;

/**
 * @author 杨晓峰
 * @create 2019/5/14
 * @Describe
 */
public interface MinortermAddContract {
    interface View extends BaseView<presenter> {
        void setContent(String content);  //设置内容

        void getData(BaseEntry<MinorTermAddBean> t);  //设置内容

    }

    interface presenter extends BasePresenter {

        void creatMinorterm(String contractOrderId, String name, String cooperator, String startDate,
                            String finishDate,String checkAndAcceptDate,String amount,String secondPartyConstructionCost,
                            String firstPartyMaterialCost,String secondPartyMaterialCost,String secondPartySafeProductionCost,
                            String stipulatedFee,String amountCashed,String secondPartyAuditFee,String secondPartyDeductedAmount,
                            String taxAmount,String managementFee);
    }
}
