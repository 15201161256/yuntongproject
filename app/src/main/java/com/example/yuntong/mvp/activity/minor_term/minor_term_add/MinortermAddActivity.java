package com.example.yuntong.mvp.activity.minor_term.minor_term_add;

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
import com.example.yuntong.app.ApiAddress;
import com.example.yuntong.base.BaseActivity;
import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.bean.MinorTermAddBean;
import com.example.yuntong.utils.DialogUtil;
import com.example.yuntong.utils.UiUtils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 杨晓峰
 * @create 2019/4/19
 * @Describe 新增小项
 */
public class MinortermAddActivity extends BaseActivity implements MinortermAddContract.View {

    @BindView(R.id.back_rl)
    RelativeLayout backRl;
    @BindView(R.id.add_bt)
    Button addBt;
    @BindView(R.id.name_et)
    EditText nameEt;
    @BindView(R.id.cooperator_et)
    EditText cooperatorEt;
    @BindView(R.id.time_start_tv)
    TextView timeStartTv;
    @BindView(R.id.start_time)
    RelativeLayout startTime;
    @BindView(R.id.time_finishDate_tv)
    TextView timeFinishDateTv;
    @BindView(R.id.finishDate_time_rl)
    RelativeLayout finishDateTimeRl;
    @BindView(R.id.checkAndAcceptDate_tv)
    TextView checkAndAcceptDateTv;
    @BindView(R.id.checkAndAcceptDate_rl)
    RelativeLayout checkAndAcceptDateRl;
    @BindView(R.id.amount_et)
    EditText amountEt;
    @BindView(R.id.secondPartyConstructionCost_et)
    EditText secondPartyConstructionCostEt;
    @BindView(R.id.firstPartyMaterialCost_et)
    EditText firstPartyMaterialCostEt;
    @BindView(R.id.secondPartyMaterialCost_et)
    EditText secondPartyMaterialCostEt;
    @BindView(R.id.secondPartySafeProductionCost_et)
    EditText secondPartySafeProductionCostEt;
    @BindView(R.id.stipulatedFee_et)
    EditText stipulatedFeeEt;
    @BindView(R.id.amountCashed_et)
    EditText amountCashedEt;
    @BindView(R.id.secondPartyAuditFee_et)
    EditText secondPartyAuditFeeEt;
    @BindView(R.id.secondPartyDeductedAmount_et)
    EditText secondPartyDeductedAmountEt;
    @BindView(R.id.taxAmount_et)
    EditText taxAmountEt;
    @BindView(R.id.managementFee_et)
    EditText managementFeeEt;

    private int year, month, day;
    private MinortermAddPresenter presenter;
    private String time, startDate,finishDate, checkAndAcceptDate,flag;
    private DatePickerDialog datePickerDialog;
    private Dialog dialog;
    private String amount = "";
    private String secondPartyConstructionCost = "";
    private String firstPartyMaterialCost = "";
    private String secondPartyMaterialCost = "";
    private String secondPartySafeProductionCost = "";
    private String stipulatedFee = "";
    private String amountCashed = "";
    private String secondPartyAuditFee = "";
    private String secondPartyDeductedAmount = "";
    private String taxAmount = "";
    private String managementFee = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_minorterm_add;
    }

    @Override
    protected void initView() {
        presenter = new MinortermAddPresenter(this, this);
        //获取当前年月日
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);//当前年
        month = calendar.get(Calendar.MONTH);//当前月
        day = calendar.get(Calendar.DAY_OF_MONTH);//当前日
        datePickerDialog = new DatePickerDialog(this, mdateListener, year, month, day);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.back_rl, R.id.add_bt, R.id.start_time, R.id.finishDate_time_rl, R.id.checkAndAcceptDate_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_rl:
                finish();
                break;
            case R.id.start_time:
                flag = "1";
                datePickerDialog.show();
                break;
            case R.id.finishDate_time_rl:
                flag = "2";
                datePickerDialog.show();
                break;
            case R.id.checkAndAcceptDate_rl:
                flag = "3";
                datePickerDialog.show();
                break;
            case R.id.add_bt:
                String name = nameEt.getText().toString().trim();
                String cooperator = cooperatorEt.getText().toString().trim();
                amount = amountEt.getText().toString().trim();
                secondPartyConstructionCost = secondPartyConstructionCostEt.getText().toString().trim();
                firstPartyMaterialCost = firstPartyMaterialCostEt.getText().toString().trim();
                secondPartyMaterialCost = secondPartyMaterialCostEt.getText().toString().trim();
                secondPartySafeProductionCost = secondPartySafeProductionCostEt.getText().toString().trim();
                stipulatedFee = stipulatedFeeEt.getText().toString().trim();
                amountCashed = amountCashedEt.getText().toString().trim();
                secondPartyAuditFee = secondPartyAuditFeeEt.getText().toString().trim();
                secondPartyDeductedAmount = secondPartyDeductedAmountEt.getText().toString().trim();
                taxAmount = taxAmountEt.getText().toString().trim();
                managementFee = managementFeeEt.getText().toString().trim();
                Check(name, cooperator);
                break;
        }
    }

    private void Check(String name, String cooperator) {
        if (name == null || name.isEmpty()) {
            UiUtils.showToast(this, "请输入小项名称");
            return;
        }
        if (cooperator == null || cooperator.isEmpty()) {
            UiUtils.showToast(this, "请输入合作单位");
            return;
        }
        if (startDate == null || startDate.isEmpty()) {
            UiUtils.showToast(this, "请选择开始日期");
            return;
        }
        presenter.creatMinorterm(ApiAddress.orderId, name, cooperator, startDate, finishDate, checkAndAcceptDate,
                amount, secondPartyConstructionCost,
                firstPartyMaterialCost, secondPartyMaterialCost, secondPartySafeProductionCost,
                stipulatedFee, amountCashed, secondPartyAuditFee, secondPartyDeductedAmount,
                taxAmount, managementFee);
    }

    @Override
    public void setContent(String content) {
        DialogUtil.showAlertDialog(mActivity, content + "");
    }

    @Override
    public void getData(final BaseEntry<MinorTermAddBean> data) {
        if (data.getCode().equals("200")) {
            dialog = DialogUtil.showDialog2(mActivity, "订单小项创建成功", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    Intent intent = new Intent();
                    intent.putExtra("bean", data);
                    setResult(20, intent);
                    finish();
                }
            });
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
                    startDate = time;
                    timeStartTv.setText(startDate);
                    break;
                case "2":
                    finishDate = time;
                    timeFinishDateTv.setText(finishDate);
                    break;
                case "3":
                    checkAndAcceptDate = time;
                    checkAndAcceptDateTv.setText(checkAndAcceptDate);
                    break;
            }

        }
    };


}
