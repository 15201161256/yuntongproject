package com.example.yuntong.mvp.activity.materals.add_out_materal;


import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BasePresenter;
import com.example.yuntong.base.BaseView;
import com.example.yuntong.bean.MaterialPutBean;

/**
 * @author 杨晓峰
 * @create 2019/5/14
 * @Describe
 */
public interface MaterialsAddOutContract {
    interface View extends BaseView<presenter> {
        void setContent(String content);  //设置内容

        void getData(BaseEntry<MaterialPutBean> t);  //设置内容

    }

    interface presenter extends BasePresenter {

        void creatMaterialsPut(String materialId, String type, String count,
                               String auditor, String remark);
    }
}
