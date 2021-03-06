package com.example.yuntong.mvp.activity.order.lock_order;


import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BasePresenter;
import com.example.yuntong.base.BaseView;
import com.example.yuntong.bean.EditContractBean;
import com.example.yuntong.bean.EditOrderBean;


/**
 * @author 杨晓峰 
 * @create 2019/5/8
 * @Describe 
 */

public interface LockOrderContract {
    interface View extends BaseView<presenter> {
        void setContent(String content);  //设置内容
        void getData(BaseEntry<EditOrderBean> baseEntry);

        void setEditContent(String content);
        void getEditData(BaseEntry<EditOrderBean> baseEntry);
    }

    interface presenter extends BasePresenter {
        void overOrder(String url);

        void editOrder(String id, String orderNo, String orderName,
                          String bigItemNo, String orderAmount, String startTime
                ,String missNo, String taxRate);
    }
}
