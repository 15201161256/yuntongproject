package com.example.yuntong.mvp.activity.minor_term.minor_item_lock;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuntong.R;
import com.example.yuntong.app.ApiAddress;
import com.example.yuntong.base.BaseActivity;
import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.bean.EditMinorTermBean;
import com.example.yuntong.bean.MinorTermBean;
import com.example.yuntong.utils.DialogUtil;
import com.example.yuntong.utils.UiUtils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 杨晓峰
 * @create 2019/5/9
 * @Describe 编辑结束小项页面
 */
public class LockMinorTermActivity extends BaseActivity implements LockMinorTermContract.View {

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
    private String time, startDate,checkAndAcceptDate,flag;
    private String id;
    private Dialog dialog;
    private MinorTermBean.RowsBean rowsBean;
    private LockMinorTermPresenter termPresenter;
    private  DatePickerDialog datePickerDialog;
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
        rowsBean = (MinorTermBean.RowsBean) getIntent().getSerializableExtra("bean");
        //必填
        id = rowsBean.getId() + "";
        nameEt.setText(rowsBean.getName());
        cooperatorEt.setText(rowsBean.getCooperator());
        timeStartTv.setText(rowsBean.getStartDate());
        startDate = rowsBean.getStartDate();
        //选填
        checkAndAcceptDateTv.setText(rowsBean.getCheckAndAcceptDate());
        checkAndAcceptDate = rowsBean.getCheckAndAcceptDate();
        amountEt.setText(rowsBean.getAmount());
        secondPartyConstructionCostEt.setText(rowsBean.getSecondPartyConstructionCost());
        firstPartyMaterialCostEt.setText(rowsBean.getFirstPartyMaterialCost());
        secondPartyMaterialCostEt.setText(rowsBean.getSecondPartyMaterialCost());
        secondPartySafeProductionCostEt.setText(rowsBean.getSecondPartySafeProductionCost());
        stipulatedFeeEt.setText(rowsBean.getStipulatedFee());
        amountCashedEt.setText(rowsBean.getAmountCashed());
        secondPartyAuditFeeEt.setText(rowsBean.getSecondPartyAuditFee());
        secondPartyDeductedAmountEt.setText(rowsBean.getSecondPartyDeductedAmount());
        taxAmountEt.setText(rowsBean.getTaxAmount());
        managementFeeEt.setText(rowsBean.getManagementFee());




    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lock_minor_term;
    }

    @Override
    protected void initView() {
        termPresenter = new LockMinorTermPresenter(this, this);
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

    @OnClick({R.id.back_rl, R.id.ok_bt, R.id.start_time, R.id.add_bt, R.id.checkAndAcceptDate_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_rl:
                finish();
                break;
            case R.id.ok_bt:
                termPresenter.overOrder(ApiAddress.OVER_MINOR_TERM + ApiAddress.minnorId);
                break;
            case R.id.start_time:
                flag = "1";
                datePickerDialog.show();
                break;
            case R.id.checkAndAcceptDate_rl:
                flag = "2";
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
        termPresenter.editOrder(id, name, cooperator, startDate, checkAndAcceptDate,
                amount, secondPartyConstructionCost,
                firstPartyMaterialCost, secondPartyMaterialCost, secondPartySafeProductionCost,
                stipulatedFee, amountCashed, secondPartyAuditFee, secondPartyDeductedAmount,
                taxAmount, managementFee);
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
                    checkAndAcceptDate = time;
                    checkAndAcceptDateTv.setText(checkAndAcceptDate);
                    break;
            }

        }
    };

    @Override
    public void setContent(String content) {
        DialogUtil.showAlertDialog(mActivity, content + "");
    }

    @Override
    public void getData(final BaseEntry<EditMinorTermBean> baseEntry) {
        if (baseEntry.getCode().equals("200")) {
            dialog = DialogUtil.showDialog2(mActivity, "小项锁定成功", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    Intent intent = new Intent();
                    intent.putExtra("position", getIntent().getIntExtra("position", -1));
                    intent.putExtra("bean", baseEntry);
                    setResult(300, intent);
                    finish();
                }
            });
        }
    }

    @Override
    public void setEditContent(String content) {
        DialogUtil.showAlertDialog(mActivity, content + "");
    }

    @Override
    public void getEditData(final BaseEntry<EditMinorTermBean> data) {
        if (data.getCode().equals("200")) {
            dialog = DialogUtil.showDialog2(mActivity, "小项修改成功", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    Intent intent = new Intent();
                    intent.putExtra("position", getIntent().getIntExtra("position", -1));
                    intent.putExtra("bean", data);
                    setResult(300, intent);
                    finish();
                }
            });
        }
    }
}
