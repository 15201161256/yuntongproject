package com.example.yuntong.mvp.activity.order.order_detail;


import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BasePresenter;
import com.example.yuntong.base.BaseView;
import com.example.yuntong.bean.OrderDetailBean;


/**
 * @author 杨晓峰 
 * @create 2019/4/25
 * @Describe
 */

public interface OrderDetailContract {
    interface View extends BaseView<presenter> {
        void setContent(String content);  //设置内容
        void getData(BaseEntry<OrderDetailBean> baseEntry);
    }

    interface presenter extends BasePresenter {
        void getOrderDetailData(String url);
    }
}
