package com.example.yuntong.mvp.activity.instrument.add_instrument;


import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BasePresenter;
import com.example.yuntong.base.BaseView;
import com.example.yuntong.bean.EditContractBean;
import com.example.yuntong.bean.EditInstrumentBean;

/**
 * @author 杨晓峰
 * @create 2019/5/14
 * @Describe
 */

public interface AddInstrumentContract {
    interface View extends BaseView<presenter> {
        void setContent(String content);  //设置内容

        void getData(BaseEntry<EditInstrumentBean> data);  //设置内容

    }

    interface presenter extends BasePresenter {

        void creatInstrument(String name, String type, String purchaseTime,
                           String borrower, String startTime, String endTime, String remark);
    }
}
