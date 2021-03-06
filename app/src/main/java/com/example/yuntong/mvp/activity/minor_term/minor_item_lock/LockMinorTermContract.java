package com.example.yuntong.mvp.activity.minor_term.minor_item_lock;


import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BasePresenter;
import com.example.yuntong.base.BaseView;
import com.example.yuntong.bean.EditMinorTermBean;
import com.example.yuntong.bean.EditOrderBean;


/**
 * @author 杨晓峰 
 * @create 2019/5/8
 * @Describe 
 */

public interface LockMinorTermContract {
    interface View extends BaseView<presenter> {
        void setContent(String content);  //设置内容
        void getData(BaseEntry<EditMinorTermBean> baseEntry);

        void setEditContent(String content);
        void getEditData(BaseEntry<EditMinorTermBean> baseEntry);
    }

    interface presenter extends BasePresenter {
        void overOrder(String url);

        void editOrder(String id, String name, String cooperator, String startDate,
                       String checkAndAcceptDate,String amount,String secondPartyConstructionCost,
                       String firstPartyMaterialCost,String secondPartyMaterialCost,String secondPartySafeProductionCost,
                       String stipulatedFee,String amountCashed,String secondPartyAuditFee,String secondPartyDeductedAmount,
                       String taxAmount,String managementFee);
    }
}
