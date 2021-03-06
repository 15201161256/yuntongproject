package com.example.yuntong.mvp.fragment.mefragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuntong.R;
import com.example.yuntong.base.BaseFragment;
import com.example.yuntong.mvp.activity.affirmpsd.AffirmPsdActivity;
import com.example.yuntong.mvp.activity.contract.contract_total.ContractTotalActivity;
import com.example.yuntong.mvp.activity.login.LoginActivity;
import com.example.yuntong.mvp.activity.search.SearchActivity;
import com.example.yuntong.utils.SharePreferencesUtils;
import com.example.yuntong.view.pulltozoomview.PullToZoomScrollViewEx;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @author 杨晓峰
 * @create 2019/3/26
 * @Describe 我的页面
 */
public class MeFragment extends BaseFragment implements View.OnClickListener {


    @BindView(R.id.pull_view)
    PullToZoomScrollViewEx scrollView;
    Unbinder unbinder;
    private View headView, contentView;
    private LinearLayout bt_ll;
    private RelativeLayout rl_fund, forget_rl,search_rl;

    private TextView tv_user_name;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        pullToZomView();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        tv_user_name.setText(SharePreferencesUtils.getString(getContext(), "nickname"));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    private void pullToZomView() {
        headView = LayoutInflater.from(baseActivity).inflate(R.layout.my_pullto_headview, null, false);
        View zoomView = LayoutInflater.from(baseActivity).inflate(R.layout.view_my_pullto_zoomview, null, false);
        contentView = LayoutInflater.from(baseActivity).inflate(R.layout.view_my_pull, null, false);
        tv_user_name = headView.findViewById(R.id.tv_user_name);

        rl_fund = (RelativeLayout) contentView.findViewById(R.id.rl_fund);
        forget_rl = (RelativeLayout) contentView.findViewById(R.id.forget_rl);
        search_rl = (RelativeLayout) contentView.findViewById(R.id.search_rl);
        scrollView.setHeaderView(headView);
        scrollView.setZoomView(zoomView);
        scrollView.setScrollContentView(contentView);
        scrollView.setHeaderLayoutParams(getHeaderParams());
        bt_ll = (LinearLayout) headView.findViewById(R.id.bt_ll);
        bt_ll.setOnClickListener(this);
        search_rl.setOnClickListener(this);
        rl_fund.setOnClickListener(this);
        forget_rl.setOnClickListener(this);
    }

    protected LinearLayout.LayoutParams getHeaderParams() {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        baseActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenHeight = localDisplayMetrics.heightPixels;
        int mScreenWidth = localDisplayMetrics.widthPixels;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
        return localObject;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_ll://登录
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;
            case R.id.rl_fund://合同汇总
                startActivity(new Intent(getContext(), ContractTotalActivity.class));
                break;
            case R.id.forget_rl://修改密码
                startActivity(new Intent(getContext(), AffirmPsdActivity.class));
                break;
            case R.id.search_rl://搜索
                startActivity(new Intent(getContext(), SearchActivity.class));
                break;
        }
    }
}
