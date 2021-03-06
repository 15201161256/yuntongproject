package com.example.yuntong.mvp.activity.materals.out_materals_list;


import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BasePresenter;
import com.example.yuntong.base.BaseView;
import com.example.yuntong.bean.MaterialsTypeBean;

/**
 * @author 杨晓峰 
 * @create 2019/5/14
 * @Describe 
 */

public interface MaterialsOutListContract {
    interface View extends BaseView<presenter> {
        void setContent(String content);  //设置内容
        void getData(BaseEntry<MaterialsTypeBean> baseEntry);

    }

    interface presenter extends BasePresenter {
        void getList(int pageNumber, int pageSize, String materialId, String type);
    }
}
