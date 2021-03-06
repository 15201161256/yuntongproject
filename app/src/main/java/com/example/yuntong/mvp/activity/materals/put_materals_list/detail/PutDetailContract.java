package com.example.yuntong.mvp.activity.materals.put_materals_list.detail;


import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BasePresenter;
import com.example.yuntong.base.BaseView;
import com.example.yuntong.bean.MaterialPutBean;
import com.example.yuntong.bean.MaterialsAddBean;


/**
 * @author 杨晓峰 
 * @create 2019/4/25
 * @Describe
 */

public interface PutDetailContract {
    interface View extends BaseView<presenter> {
        void setContent(String content);  //设置内容
        void getData(BaseEntry<MaterialPutBean> baseEntry);

    }

    interface presenter extends BasePresenter {
        void getMaterialsDetailData(String url);
    }
}
