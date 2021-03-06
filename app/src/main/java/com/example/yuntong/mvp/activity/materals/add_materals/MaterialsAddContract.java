package com.example.yuntong.mvp.activity.materals.add_materals;


import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BasePresenter;
import com.example.yuntong.base.BaseView;
import com.example.yuntong.bean.MaterialsAddBean;
import com.example.yuntong.bean.MinorTermAddBean;

/**
 * @author 杨晓峰
 * @create 2019/5/14
 * @Describe
 */
public interface MaterialsAddContract {
    interface View extends BaseView<presenter> {
        void setContent(String content);  //设置内容

        void getData(BaseEntry<MaterialsAddBean> t);  //设置内容

    }

    interface presenter extends BasePresenter {

        void creatMaterials(String name, String type, String unit, String remark);
    }
}
