package com.example.yuntong.mvp.fragment.contract;


import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BasePresenter;
import com.example.yuntong.base.BaseView;
import com.example.yuntong.bean.ContractListEntity;

/**
 * @author 杨晓峰
 * @create 2019/5/14
 * @Describe
 */

public interface ContractContract {
    interface View extends BaseView<presenter> {
        void setContent(String content);  //设置内容
        void getLoginData(BaseEntry<ContractListEntity> contractListEntityBaseEntry);

    }

    interface presenter extends BasePresenter {
        void contractList(int pageNumber, int pageSize);
    }
}
