package com.example.yuntong.mvp.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuntong.R;
import com.example.yuntong.app.MyApp;
import com.example.yuntong.base.BaseActivity;
import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.bean.LoginEntity;
import com.example.yuntong.mvp.MainActivity;
import com.example.yuntong.mvp.activity.affirmpsd.AffirmPsdActivity;
import com.example.yuntong.mvp.activity.forgetpsd.ForgetPsdActivity;
import com.example.yuntong.mvp.activity.register.RegisterActivity;
import com.example.yuntong.utils.DialogUtil;
import com.example.yuntong.utils.SharePreferencesUtils;
import com.example.yuntong.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 杨晓峰
 * @create 2019/3/26
 * @Describe 登录
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.back_rl)
    RelativeLayout backRl;
    @BindView(R.id.login_register_tv)
    TextView loginRegisterTv;
    @BindView(R.id.username_et)
    EditText usernameEt;
    @BindView(R.id.userpassword_et)
    EditText userpasswordEt;
    @BindView(R.id.forget_tv)
    TextView forgetTv;
    @BindView(R.id.next_bt)
    Button nextBt;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this, this);
        if (SharePreferencesUtils.getString(mActivity,"username")!=null){
            usernameEt.setText(SharePreferencesUtils.getString(mActivity,"username"));
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.back_rl, R.id.login_register_tv, R.id.forget_tv, R.id.next_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_rl:
                finish();
                break;
            case R.id.login_register_tv:
                startActivity(new Intent(mActivity, RegisterActivity.class));
                break;
            case R.id.forget_tv://跳转到修改密码页面
                startActivity(new Intent(mActivity, AffirmPsdActivity.class));
                break;
            case R.id.next_bt:
                String user=usernameEt.getText().toString().trim();
                String pwd=userpasswordEt.getText().toString().trim();
                checkLogin(user, pwd);
                break;
        }
    }

    private void checkLogin(String user, String pwd) {
        if (user == null || user.isEmpty()) {
            UiUtils.showToast(this,"请输入账号");
            return;
        }
        if (pwd == null || pwd.isEmpty()) {
            UiUtils.showToast(this,"请输入密码");
            return;
        }
        loginPresenter.userLogin(user, pwd);
    }

    @Override
    public void setContent(String content) {
        DialogUtil.showAlertDialog(mActivity, content + "");
    }

    @Override
    public void getLoginData(BaseEntry<LoginEntity> loginEntity) {
        if (loginEntity.getCode().equals("200")){
            startActivity(new Intent(mActivity, MainActivity.class));
            finish();
            SharePreferencesUtils.setString(mActivity,"nickname",loginEntity.getData().getItem().getNickname());
            SharePreferencesUtils.setString(mActivity,"username",loginEntity.getData().getItem().getUsername());
            SharePreferencesUtils.setString(mActivity,"userId",loginEntity.getData().getItem().getId()+"");
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;//禁止物理键返回
        }
        return super.onKeyDown(keyCode, event);
    }
}
