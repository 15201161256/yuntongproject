package com.example.yuntong.mvp.activity.affirmpsd;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.yuntong.R;
import com.example.yuntong.base.BaseActivity;
import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.bean.BaseBean;
import com.example.yuntong.utils.DialogUtil;
import com.example.yuntong.utils.NumberMatcher;
import com.example.yuntong.utils.SharePreferencesUtils;
import com.example.yuntong.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 杨晓峰
 * @create 2019/3/26
 * @Describe 确认密码
 */
public class AffirmPsdActivity extends BaseActivity implements AffirmPsdContract.View {

    @BindView(R.id.back_rl)
    RelativeLayout backRl;
    @BindView(R.id.set_psd_et)
    EditText setPsdEt;
    @BindView(R.id.ok_psd_et)
    EditText okPsdEt;
    @BindView(R.id.next_bt)
    Button nextBt;
    @BindView(R.id.originalP_et)
    EditText originalPEt;

    private String mPassword, mPassword_ok, originalP;
    private AffirmPsdPresenter affirmPsdPresenter;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        affirmPsdPresenter = new AffirmPsdPresenter(this, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_affirm_psd;
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
                originalP = originalPEt.getText().toString().trim();
                mPassword = setPsdEt.getText().toString().trim();
                mPassword_ok = okPsdEt.getText().toString().trim();
                if (prepareForNext()) {
                    return;
                }
                affirmPsdPresenter.modificationPsd(originalP, mPassword_ok);
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
        if (originalP.length() == 0) {
            UiUtils.showToast(mActivity, "请输入原密码");
            return true;
        }
        if (setPsdEt.length() == 0) {
            UiUtils.showToast(mActivity, "请输入密码");
            return true;
        }
//        if (setPsdEt.length() < 6) {
//            UiUtils.showToast(mActivity, "密码只能是6-16位数字和字母");
//            return true;
//        }
//        if (!NumberMatcher.isPwd(mPassword)) {
//            UiUtils.showToast(mActivity, "密码请输入6-16位字母、数字或符号组合");
//            return true;
//        }
//        if (okPsdEt.length() == 0) {
//            UiUtils.showToast(mActivity, "请输入确认密码");
//            return true;
//        }
//        if (!NumberMatcher.isPwd(mPassword_ok)) {
//            UiUtils.showToast(mActivity, "密码请输入6-16位字母、数字或符号组合");
//            return true;
//        }
        if (!mPassword.equals(mPassword_ok)) {
            UiUtils.showToast(mActivity, "密码不一致请重新确认");
            return true;
        }

        return false;
    }

    @Override
    public void setContent(String content) {
        DialogUtil.showAlertDialog(mActivity, content + "");
    }

    @Override
    public void getData(BaseEntry<BaseBean> baseEntry) {
        if (baseEntry.getCode().equals("200")) {
            dialog = DialogUtil.showDialog2(mActivity, "密码修改成功", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    finish();
                }
            });
        }
    }
}
