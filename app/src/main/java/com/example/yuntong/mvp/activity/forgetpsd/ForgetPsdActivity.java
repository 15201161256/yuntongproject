package com.example.yuntong.mvp.activity.forgetpsd;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.yuntong.R;
import com.example.yuntong.base.BaseActivity;
import com.example.yuntong.mvp.activity.affirmpsd.AffirmPsdActivity;
import com.example.yuntong.mvp.activity.register.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 杨晓峰
 * @create 2019/3/26
 * @Describe 忘记密码
 */
public class ForgetPsdActivity extends BaseActivity {

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget_psd;
    }

    @Override
    protected void initData() {
        time = new TimeCount(60000, 1000);
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.back_rl, R.id.checking, R.id.register_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_rl:
                finish();
                break;
            case R.id.checking:
                time.start();
                break;
            case R.id.register_bt:
                startActivity(new Intent(mActivity, AffirmPsdActivity.class));
                break;
        }
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
