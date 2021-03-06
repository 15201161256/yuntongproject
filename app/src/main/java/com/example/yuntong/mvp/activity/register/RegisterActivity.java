package com.example.yuntong.mvp.activity.register;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.yuntong.R;
import com.example.yuntong.app.MyApp;
import com.example.yuntong.base.BaseActivity;
import com.example.yuntong.mvp.activity.login.LoginActivity;
import com.example.yuntong.mvp.activity.setpsd.SetPsdActivity;
import com.example.yuntong.utils.AppActivityManager;
import com.example.yuntong.utils.NumberMatcher;
import com.example.yuntong.utils.UiUtils;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 杨晓峰
 * @create 2019/3/26
 * @Describe 注册
 */
public class RegisterActivity extends BaseActivity {

    @BindView(R.id.back_rl)
    RelativeLayout backRl;
    @BindView(R.id.mobile_num_et)
    EditText mobileNumEt;
    @BindView(R.id.chechcode_et)
    EditText chechcodeEt;
    @BindView(R.id.checking)
    Button checking;
    @BindView(R.id.register_bt)
    Button registerBt;

    private TimeCount time;
    private String moblie, mPassword, mCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {
        time = new TimeCount(60000, 1000);
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.back_rl, R.id.register_bt,R.id.checking})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_rl:
                finish();
                break;
            case R.id.checking:
                time.start();
                break;
            case R.id.register_bt:
                startActivity(new Intent(mActivity, SetPsdActivity.class));
//                moblie=mobileNumEt.getText().toString().trim();
//                if (prepareForRegister()) {//注册前验证
//                    return;
//                }
                break;
        }
    }

    /**
     * 注册前验证
     *
     * @return
     */
    private boolean prepareForRegister() {

        if (!UiUtils.isNetworkConnection(mActivity)) {
            UiUtils.showToast(mActivity, "当前没有可用的网络链接");
            return true;
        }
        if (mobileNumEt.length() == 0) {
            UiUtils.showToast(mActivity, "请输入手机号");
            return true;
        }
        if (!NumberMatcher.isMobile(moblie)) {
            UiUtils.showToast(mActivity, "请输入正确手机号码");
            return true;
        }
        if (mCode.length() == 0) {
            UiUtils.showToast(mActivity, "请输入验证码");
            return true;
        }
        if (mCode.length() < 6) {
            UiUtils.showToast(mActivity, "请输入6位验证码");
            return true;
        }
        return false;
    }
    /*
  * 设置倒计时
  * */
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            checking.setClickable(false);
            checking.setText(millisUntilFinished / 1000 + "秒后重试");
            checking.setBackgroundResource(R.mipmap.signin_btn_test_no);
            checking.setTextColor(Color.rgb(147, 147, 147));
        }

        @Override
        public void onFinish() {
            checking.setText("重新验证");
            checking.setClickable(true);
            checking.setBackgroundResource(R.drawable.select_login);
            checking.setTextColor(Color.rgb(255, 255, 255));
        }
    }
}
