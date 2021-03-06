package com.example.yuntong.mvp.activity.instrument.add_instrument;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuntong.R;
import com.example.yuntong.base.BaseActivity;
import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.bean.EditInstrumentBean;
import com.example.yuntong.utils.DialogUtil;
import com.example.yuntong.utils.SharePreferencesUtils;
import com.example.yuntong.utils.UiUtils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 杨晓峰
 * @create 2019/4/10
 * @Describe 新增工具
 */
public class AddInstrumentActivity extends BaseActivity implements AddInstrumentContract.View {

    @BindView(R.id.back_rl)
    RelativeLayout backRl;
    @BindView(R.id.instrument_name_et)
    EditText instrumentNameEt;
    @BindView(R.id.ins_model_et)
    EditText insModelEt;
    @BindView(R.id.buytime_tv)
    TextView buytimeTv;
    @BindView(R.id.time_start_tv)
    TextView timeStartTv;
    @BindView(R.id.end_time_tv)
    TextView endTimeTv;
    @BindView(R.id.user_et)
    EditText userEt;
    @BindView(R.id.remark_et)
    EditText remarkEt;
    @BindView(R.id.add_bt)
    Button addBt;

    private int year, month, day;
    private String time, flag;
    private DatePickerDialog dialog;
    private Dialog dialog2 = null;

    private AddInstrumentPresenter presenter;
    private String borrower, remark;
    private String startTime = "", purchaseTime = "";
    private String endTime = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
//        borrowerId = SharePreferencesUtils.getString(mActivity, "userId");
        //获取当前年月日
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);//当前年
        month = calendar.get(Calendar.MONTH);//当前月
        day = calendar.get(Calendar.DAY_OF_MONTH);//当前日
        dialog = new DatePickerDialog(this, mdateListener, year, month, day);

        presenter = new AddInstrumentPresenter(mActivity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_instrument;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.back_rl, R.id.buytime, R.id.start_time, R.id.end_time, R.id.add_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_rl:
                finish();
                break;
            case R.id.buytime:
                flag = "1";
                dialog.show();
                break;
            case R.id.start_time:
                flag = "2";
                dialog.show();
                break;
            case R.id.end_time:
                flag = "3";
                dialog.show();
                break;
            case R.id.add_bt:
                String name = instrumentNameEt.getText().toString().trim();
                String type = insModelEt.getText().toString().trim();
                String borrower = userEt.getText().toString().trim();
                remark = remarkEt.getText().toString().trim();
                Check(name, type, purchaseTime,borrower);
                break;
        }
    }

    private void Check(String name, String type, String purchaseTime,String borrower) {
        if (name == null || name.isEmpty()) {
            UiUtils.showToast(this, "请输入工具名称");
            return;
        }
        if (type == null || type.isEmpty()) {
            UiUtils.showToast(this, "请输入规格型号");
            return;
        }
        if (purchaseTime == null || purchaseTime.isEmpty()) {
            UiUtils.showToast(this, "请输入购买时间");
            return;
        }
        presenter.creatInstrument(name, type, purchaseTime,
                borrower, startTime, endTime, remark);
    }

    /**
     * 日期选择的回调监听
     */
    private DatePickerDialog.OnDateSetListener mdateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int years, int monthOfYear, int dayOfMonth) {
            int month = monthOfYear + 1;
            String mm = "";
            String dd1 = "";
            String a = mm.format("%02d", month);
            String b = dd1.format("%02d", dayOfMonth);
            time = years + "-" + a + "-" + b + "";
            switch (flag) {
                case "1":
                    purchaseTime=time;
                    buytimeTv.setText(purchaseTime);
                    break;
                case "2":
                    startTime=time;
                    timeStartTv.setText(startTime);
                    break;
                case "3":
                    endTime=time;
                    endTimeTv.setText(endTime);
                    break;
            }

        }
    };

    @Override
    public void setContent(String content) {
        DialogUtil.showAlertDialog(mActivity, content + "");
    }

    @Override
    public void getData(final BaseEntry<EditInstrumentBean> data) {
        if (data.getCode().equals("200")) {
            dialog2 = DialogUtil.showDialog2(mActivity, "工具创建成功", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog2.dismiss();
                    Intent intent = new Intent();
                    intent.putExtra("bean", data);
                    setResult(10, intent);
                    finish();
                }
            });
        }
    }
}
