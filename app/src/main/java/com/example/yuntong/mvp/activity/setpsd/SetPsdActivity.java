package com.example.yuntong.mvp.activity.setpsd;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.yuntong.R;
import com.example.yuntong.base.BaseActivity;
import com.example.yuntong.utils.NumberMatcher;
import com.example.yuntong.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 杨晓峰
 * @create 2019/3/26
 * @Describe 设置密码页面
 */
public class SetPsdActivity extends BaseActivity {

    @BindView(R.id.back_rl)
    RelativeLayout backRl;
    @BindView(R.id.set_psd_et)
    EditText setPsdEt;
    @BindView(R.id.ok_psd_et)
    EditText okPsdEt;
    @BindView(R.id.next_bt)
    Button nextBt;

    private String mPassword, mPassword_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set_psd;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.back_rl, R.id.next_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_rl:
                finish();
                break;
            case R.id.next_bt:
                mPassword = setPsdEt.getText().toString().trim();
                mPassword_ok = okPsdEt.getText().toString().trim();
                if (prepareForNext()) {
                    return;
                }
                UiUtils.showToast(mActivity, "设置密码成功");
                break;
        }
    }

    /**
     * 验证
     *
     * @return
     */
    private boolean prepareForNext() {

        if (!UiUtils.isNetworkConnection(mActivity)) {
            UiUtils.showToast(mActivity, "当前没有可用的网络链接");
            return true;
        }
        if (setPsdEt.length() == 0) {
            UiUtils.showToast(mActivity, "请输入密码");
            return true;
        }
        if (setPsdEt.length() < 6) {
            UiUtils.showToast(mActivity, "密码只能是6-16位数字和字母");
            return true;
        }
        if (!NumberMatcher.isPwd(mPassword)) {
            UiUtils.showToast(mActivity, "密码请输入6-16位字母、数字或符号组合");
            return true;
        }
        if (okPsdEt.length() == 0) {
            UiUtils.showToast(mActivity, "请输入确认密码");
            return true;
        }
        if (!NumberMatcher.isPwd(mPassword_ok)) {
            UiUtils.showToast(mActivity, "密码请输入6-16位字母、数字或符号组合");
            return true;
        }
        if (!mPassword.equals(mPassword_ok)) {
            UiUtils.showToast(mActivity, "密码不一致请重新确认");
            return true;
        }

        return false;
    }
}
