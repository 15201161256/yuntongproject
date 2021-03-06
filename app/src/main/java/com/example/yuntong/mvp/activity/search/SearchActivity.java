package com.example.yuntong.mvp.activity.search;

import android.os.Bundle;

import com.example.yuntong.R;
import com.example.yuntong.base.BaseActivity;
import com.example.yuntong.utils.ELog;
import com.example.yuntong.utils.ListDataSave;
import com.example.yuntong.utils.UiUtils;
import com.example.yuntong.view.search.mSearchLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * @author 杨晓峰
 * @create 2019/7/29
 * @Describe 搜索页面
 */
public class SearchActivity extends BaseActivity {

    @BindView(R.id.msearchlayout)
    mSearchLayout msearchlayout;
    private ListDataSave dataSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        dataSave = new ListDataSave(this, "yang");
//        String shareData = "澳洲美食,长沙美食,韩国料理,日本料理,舌尖上的中国,意大利餐,山西菜";
//        List<String> skills = Arrays.asList(shareData.split(","));
        List<String> skills2 = dataSave.getDataList("string");

        String shareHotData = "邯郸肥乡,邯郸武安";
        List<String> skillHots = Arrays.asList(shareHotData.split(","));


        msearchlayout.initData(skills2, skillHots, new mSearchLayout.setSearchCallBackListener() {
            @Override
            public void Search(String str) {
                //进行或联网搜索
                ELog.e("进行或联网搜索", "进行或联网搜索");
                UiUtils.showToast(mActivity,"搜索");
            }

            @Override
            public void Back() {
                //取消
                ELog.e("取消", "取消");
            }

            @Override
            public void ClearOldData() {
                //清除历史搜索记录  更新记录原始数据
                ELog.e("ClearOldData", "ClearOldData");
            }

            @Override
            public void SaveOldData(ArrayList<String> AlloldDataList) {
                dataSave.setDataList("string", AlloldDataList);
                //保存所有的搜索记录
                ELog.e("保存所有的搜索记录", "保存所有的搜索记录" );

            }
        });
    }

    @Override
    protected void initData() {

    }
}
