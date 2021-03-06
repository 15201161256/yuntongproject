package com.example.yuntong.mvp.fragment.manage;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.example.yuntong.R;
import com.example.yuntong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author 杨晓峰
 * @create 2019/3/28
 * @Describe 材料管理、工具管理
 */
public class ManageFragment extends BaseFragment {


    @BindView(R.id.rb_materials)
    RadioButton rbMaterials;
    @BindView(R.id.rb_instrument)
    RadioButton rbInstrument;
    @BindView(R.id.rg)
    RadioGroup rg;
    Unbinder unbinder;
    @BindView(R.id.show_1)
    LinearLayout show1;
    @BindView(R.id.show_2)
    LinearLayout show2;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_manage;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rbMaterials.getId() == checkedId) {
                    show1.setVisibility(View.VISIBLE);
                    show2.setVisibility(View.GONE);
                }

                if (rbInstrument.getId() == checkedId) {
                    show2.setVisibility(View.VISIBLE);
                    show1.setVisibility(View.GONE);
                }
            }
        });
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
