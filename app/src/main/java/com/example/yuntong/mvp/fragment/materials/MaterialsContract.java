package com.example.yuntong.mvp.fragment.materials;


import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BasePresenter;
import com.example.yuntong.base.BaseView;
import com.example.yuntong.bean.InstrumentBean;
import com.example.yuntong.bean.MaterialsBean;

/**
 * @author 杨晓峰
 * @create 2019/5/14
 * @Describe
 */

public interface MaterialsContract {
    interface View extends BaseView<presenter> {
        void setContent(String content);  //设置内容
        void getData(BaseEntry<MaterialsBean> entry);

    }

    interface presenter extends BasePresenter {
        void materialsList(int pageNumber, int pageSize);
    }
}
