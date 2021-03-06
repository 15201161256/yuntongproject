package com.example.yuntong.mvp.activity.affirmpsd;


import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BasePresenter;
import com.example.yuntong.base.BaseView;
import com.example.yuntong.bean.BaseBean;
import com.example.yuntong.bean.LoginEntity;
import com.example.yuntong.bean.OrderListbean;



/**
 * @author 杨晓峰
 * @create 2019/5/8
 * @Describe
 */

public interface AffirmPsdContract {
    interface View extends BaseView<presenter> {
        void setContent(String content);  //设置内容
        void getData(BaseEntry<BaseBean> baseEntry);
    }

    interface presenter extends BasePresenter {
        void modificationPsd(String originalP,String newP);
    }
}
