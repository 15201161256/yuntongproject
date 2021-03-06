package com.example.yuntong.mvp.fragment.instrument;


import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BasePresenter;
import com.example.yuntong.base.BaseView;
import com.example.yuntong.bean.ContractListEntity;
import com.example.yuntong.bean.InstrumentBean;

/**
 * @author 杨晓峰
 * @create 2019/5/14
 * @Describe
 */

public interface InstrumentContract {
    interface View extends BaseView<presenter> {
        void setContent(String content);  //设置内容
        void getData(BaseEntry<InstrumentBean> entry);

    }

    interface presenter extends BasePresenter {
        void instrumentList(int pageNumber, int pageSize);
    }
}
