package com.example.yuntong.mvp.activity.materals.materals_detail;


import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BasePresenter;
import com.example.yuntong.base.BaseView;
import com.example.yuntong.bean.InstrumentDetailBean;
import com.example.yuntong.bean.MaterialsAddBean;
import com.example.yuntong.bean.MaterialsBean;


/**
 * @author 杨晓峰 
 * @create 2019/4/25
 * @Describe
 */

public interface MaterialsDetailContract {
    interface View extends BaseView<presenter> {
        void setContent(String content);  //设置内容
        void getData(BaseEntry<MaterialsAddBean> baseEntry);

    }

    interface presenter extends BasePresenter {
        void getMaterialsDetailData(String url);
    }
}
