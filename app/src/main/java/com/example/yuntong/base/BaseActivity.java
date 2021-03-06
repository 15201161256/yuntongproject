package com.example.yuntong.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.example.yuntong.R;
import com.example.yuntong.utils.AppActivityManager;
import com.jaeger.library.StatusBarUtil;

import butterknife.ButterKnife;


public abstract class BaseActivity extends FragmentActivity {
    protected BaseActivity mActivity;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        // 设置不能横屏，防止生命周期的改变
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        this.setContentView(this.getLayoutId());
        ButterKnife.bind(this);
        mContext = this;
        AppActivityManager.getInstance().addActivity(this);//新建时添加到栈
        StatusBarUtil.setColor(this, Color.BLACK);
        this.initView();
        this.initData();

    }

    /**
     * 获取布局id
     *
     * @return
     */
    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onDestroy() {
        AppActivityManager.getInstance().removeActivity(this);
        super.onDestroy();
        System.gc();
    }
}
