package com.example.yuntong.mvp.activity.order.order_list;


import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BasePresenter;
import com.example.yuntong.base.BaseView;
import com.example.yuntong.bean.ContractListEntity;
import com.example.yuntong.bean.OrderListbean;

/**
 * @author 杨晓峰 
 * @create 2019/5/14
 * @Describe 
 */

public interface OrderListContract {
    interface View extends BaseView<presenter> {
        void setContent(String content);  //设置内容
        void getData(BaseEntry<OrderListbean> baseEntry);

    }

    interface presenter extends BasePresenter {
        void getOrderList(int pageNumber, int pageSize,String contractId);
    }
}
