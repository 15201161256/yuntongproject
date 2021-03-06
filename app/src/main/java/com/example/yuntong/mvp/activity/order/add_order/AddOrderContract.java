package com.example.yuntong.mvp.activity.order.add_order;


import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BasePresenter;
import com.example.yuntong.base.BaseView;
import com.example.yuntong.bean.OrderAddBean;

/**
 * @author 杨晓峰 
 * @create 2019/5/14
 * @Describe 
 */

public interface AddOrderContract {
    interface View extends BaseView<presenter> {
        void setContent(String content);  //设置内容
        void getData(BaseEntry<OrderAddBean> t);  //设置内容

    }

    interface presenter extends BasePresenter {

        void creatOrder(String contractId, String orderNo, String orderName,
                           String bigItemNo, String orderAmount,String startTime
        ,String missNo, String taxRate);
    }
}
