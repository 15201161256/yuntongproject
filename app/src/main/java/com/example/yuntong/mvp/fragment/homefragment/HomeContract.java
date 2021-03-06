package com.example.yuntong.mvp.fragment.homefragment;


import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BasePresenter;
import com.example.yuntong.base.BaseView;
import com.example.yuntong.bean.HomeBean;
import com.example.yuntong.bean.MaterialPutBean;


/**
 * @author 杨晓峰 
 * @create 2019/4/25
 * @Describe
 */

public interface HomeContract {
    interface View extends BaseView<presenter> {
        void setContent(String content);  //设置内容
        void getData(BaseEntry<HomeBean> baseEntry);

    }

    interface presenter extends BasePresenter {
        void getHomeData(String url);
    }
}
