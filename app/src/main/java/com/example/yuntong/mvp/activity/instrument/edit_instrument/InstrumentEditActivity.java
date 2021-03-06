package com.example.yuntong.mvp.activity.instrument.edit_instrument;

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
import com.example.yuntong.bean.InstrumentBean;
import com.example.yuntong.utils.DialogUtil;
import com.example.yuntong.utils.SharePreferencesUtils;
import com.example.yuntong.utils.UiUtils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 杨晓峰
 * @create 2019/5/21
 * @Describe 工具编辑页面
 */
public class InstrumentEditActivity extends BaseActivity implements EditInstrumentContract.View {

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
    private InstrumentBean.RowsBean instrumentBean;
    private int postion;
    private String id;

    private int year, month, day;
    private String time, flag;
    private DatePickerDialog dialog;
    private String startTime = "", purchaseTime = "";
    private String endTime = "";
    private EditInstrumentPresenter presenter;
    private Dialog dialog2;
    private Dialog dialog3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instrumentBean = (InstrumentBean.RowsBean) getIntent().getSerializableExtra("bean");
        id = instrumentBean.getId() + "";
        if (instrumentBean != null) {
            purchaseTime = instrumentBean.getPurchaseTime();
            startTime = instrumentBean.getStartTime();
            endTime = instrumentBean.getEndTime();

            UiUtils.IsStringEmptyEt(instrumentBean.getName(), instrumentNameEt);
            UiUtils.IsStringEmptyEt(instrumentBean.getType(), insModelEt);
            UiUtils.IsStringEmpty(instrumentBean.getPurchaseTime(), buytimeTv);
            UiUtils.IsStringEmpty(instrumentBean.getStartTime(), timeStartTv);
            UiUtils.IsStringEmpty(instrumentBean.getEndTime(), endTimeTv);
            UiUtils.IsStringEmpty(instrumentBean.getRemark(), remarkEt);
            UiUtils.IsStringEmpty(instrumentBean.getBorrower(), userEt);

        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_instrument_edit;
    }

    @Override
    protected void initView() {
        presenter = new EditInstrumentPresenter(mActivity, this);
        //获取当前年月日
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);//当前年
        month = calendar.get(Calendar.MONTH);//当前月
        day = calendar.get(Calendar.DAY_OF_MONTH);//当前日
        dialog = new DatePickerDialog(this, mdateListener, year, month, day);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.back_rl, R.id.ok_bt, R.id.buytime, R.id.start_time, R.id.end_time, R.id.add_bt})
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
            case R.id.ok_bt:
                dialog3 = DialogUtil.showDialog(mActivity, "确定要收回工具吗？", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog3.dismiss();
                        String name = instrumentNameEt.getText().toString().trim();
                        String type = insModelEt.getText().toString().trim();
                        String borrower = userEt.getText().toString().trim();
                        String remark = remarkEt.getText().toString().trim();

                        String mm = "";
                        String dd1 = "";
                        String a = mm.format("%02d", month+1);
                        String b = dd1.format("%02d", day);
                        endTime = year + "-" + a + "-" + b + "";
                        presenter.editInstrument(id, name, type,
                                purchaseTime, borrower, startTime, endTime, remark);
                    }
                });
                break;

            case R.id.add_bt:
                String name = instrumentNameEt.getText().toString().trim();
                String type = insModelEt.getText().toString().trim();
                String borrower = userEt.getText().toString().trim();
                String remark = remarkEt.getText().toString().trim();
                presenter.editInstrument(id, name, type,
                        purchaseTime, borrower, startTime, endTime, remark);
                break;
        }
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
                    purchaseTime = time;
                    buytimeTv.setText(purchaseTime);
                    break;
                case "2":
                    startTime = time;
                    timeStartTv.setText(startTime);
                    break;
                case "3":
                    endTime = time;
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
    public void getEditData(final BaseEntry<EditInstrumentBean> baseEntry) {
        if (baseEntry.getCode().equals("200")) {
            dialog2 = DialogUtil.showDialog2(mActivity, "工具修改成功", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog2.dismiss();
                    Intent intent = new Intent();
                    intent.putExtra("position", getIntent().getIntExtra("position", -1));
                    intent.putExtra("bean", baseEntry);
                    setResult(100, intent);
                    finish();
                }
            });
        }
    }
}
