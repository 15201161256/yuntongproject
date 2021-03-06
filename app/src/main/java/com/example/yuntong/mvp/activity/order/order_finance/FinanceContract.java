package com.example.yuntong.mvp.activity.order.order_finance;


import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BasePresenter;
import com.example.yuntong.base.BaseView;
import com.example.yuntong.bean.OrderDetailBean;


/**
 * @author 杨晓峰
 * @create 2019/5/8
 * @Describe
 */

public interface FinanceContract {
    interface View extends BaseView<presenter> {
        void setContent(String content);  //设置内容

        void getData(BaseEntry<OrderDetailBean> t);  //code=200 设置内容

    }

    interface presenter extends BasePresenter {

        void creatFinance(
                String id,  String invoiceAmount,

                String amount1, String paidTime1,
                String amount2, String paidTime2,
                String amount3, String paidTime3);
    }
}
