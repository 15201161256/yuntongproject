package com.example.yuntong.mvp.activity.instrument.edit_instrument;


import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BasePresenter;
import com.example.yuntong.base.BaseView;
import com.example.yuntong.bean.EditInstrumentBean;
import com.example.yuntong.bean.EditOrderBean;


/**
 * @author 杨晓峰 
 * @create 2019/5/8
 * @Describe 
 */

public interface EditInstrumentContract {
    interface View extends BaseView<presenter> {
        void setContent(String content);  //设置内容
        void getEditData(BaseEntry<EditInstrumentBean> baseEntry);
    }

    interface presenter extends BasePresenter {
        void editInstrument(String id, String name, String type,
                       String purchaseTime, String borrower, String startTime,String endTime,String remark);
    }
}
