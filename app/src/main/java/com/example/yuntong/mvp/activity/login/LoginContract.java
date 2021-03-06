package com.example.yuntong.mvp.activity.login;


import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BasePresenter;
import com.example.yuntong.base.BaseView;
import com.example.yuntong.bean.LoginEntity;


/**
 * @author 杨晓峰
 * @create 2019/5/14
 * @Describe
 */

public interface LoginContract {
    interface View extends BaseView<presenter> {
        void setContent(String content);  //设置内容
        void getLoginData(BaseEntry<LoginEntity> loginEntity);

    }

    interface presenter extends BasePresenter {
        void userLogin(String user, String pwd); //登录
    }
}
