package com.example.yuntong.mvp.activity.minor_term.minor_term_list;


import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BasePresenter;
import com.example.yuntong.base.BaseView;
import com.example.yuntong.bean.MinorTermBean;

/**
 * @author 杨晓峰 
 * @create 2019/5/14
 * @Describe 
 */

public interface MinorTermListContract {
    interface View extends BaseView<presenter> {
        void setContent(String content);  //设置内容
        void getData(BaseEntry<MinorTermBean> baseEntry);

    }

    interface presenter extends BasePresenter {
        void getMinorTermList(int pageNumber, int pageSize, String contractOrderId);
    }
}
