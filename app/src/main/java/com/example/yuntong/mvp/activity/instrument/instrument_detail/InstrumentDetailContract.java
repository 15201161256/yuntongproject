package com.example.yuntong.mvp.activity.instrument.instrument_detail;


import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BasePresenter;
import com.example.yuntong.base.BaseView;
import com.example.yuntong.bean.ContractDetailBean;
import com.example.yuntong.bean.InstrumentDetailBean;


/**
 * @author 杨晓峰 
 * @create 2019/4/25
 * @Describe
 */

public interface InstrumentDetailContract {
    interface View extends BaseView<presenter> {
        void setContent(String content);  //设置内容
        void getData(BaseEntry<InstrumentDetailBean> baseEntry);

    }

    interface presenter extends BasePresenter {
        void getInstrumentDetailData(String url);
    }
}
