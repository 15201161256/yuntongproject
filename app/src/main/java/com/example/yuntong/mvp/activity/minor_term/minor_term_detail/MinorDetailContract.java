package com.example.yuntong.mvp.activity.minor_term.minor_term_detail;


import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BasePresenter;
import com.example.yuntong.base.BaseView;
import com.example.yuntong.bean.BaseBean;
import com.example.yuntong.bean.MinorTermDetailBean;
import com.example.yuntong.bean.OrderDetailBean;


/**
 * @author 杨晓峰 
 * @create 2019/4/25
 * @Describe
 */

public interface MinorDetailContract {
    interface View extends BaseView<presenter> {
        void setContent(String content);  //设置内容
        void getData(BaseEntry<MinorTermDetailBean> baseEntry);

        void setContentEnd(String content);  //设置内容
        void getDataEnd(BaseEntry<BaseBean> baseEntry);
    }

    interface presenter extends BasePresenter {
        void getMinorDetailData(String url);
        void endPayStatus(String url);
    }

}
