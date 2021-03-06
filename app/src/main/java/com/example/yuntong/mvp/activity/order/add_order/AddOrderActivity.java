package com.example.yuntong.mvp.activity.order.add_order;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuntong.R;
import com.example.yuntong.app.ApiAddress;
import com.example.yuntong.base.BaseActivity;
import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.bean.OrderAddBean;
import com.example.yuntong.utils.DialogUtil;
import com.example.yuntong.utils.UiUtils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 杨晓峰
 * @create 2019/4/19
 * @Describe 新增订单
 */
public class AddOrderActivity extends BaseActivity implements AddOrderContract.View {

    @BindView(R.id.orderNo_et)
    EditText orderNoEt;
    @BindView(R.id.orderName_et)
    EditText orderNameEt;
    @BindView(R.id.bigItemNo_et)
    EditText bigItemNoEt;
    @BindView(R.id.orderAmount_et)
    EditText orderAmountEt;
    @BindView(R.id.time_start_tv)
    TextView timeStartTv;
    @BindView(R.id.start_time)
    RelativeLayout startTime;
    @BindView(R.id.show_1)
    LinearLayout show1;
    @BindView(R.id.back_rl)
    RelativeLayout backRl;
    @BindView(R.id.missNo_et)
    EditText missNoEt;
    @BindView(R.id.taxRate_et)
    EditText taxRateEt;
    @BindView(R.id.add_bt)
    Button addBt;

    private AddOrderPresenter addOrderPresenter;
    private Dialog dialog;
    private int year, month, day;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        addOrderPresenter = new AddOrderPresenter(mActivity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_order;
    }

    @Override
    protected void initView() {
        //获取当前年月日
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);//当前年
        month = calendar.get(Calendar.MONTH);//当前月
        day = calendar.get(Calendar.DAY_OF_MONTH);//当前日
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.back_rl, R.id.add_bt, R.id.start_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_rl:
                finish();
                break;
            case R.id.start_time:
                DatePickerDialog dialog = new DatePickerDialog(this, mdateListener, year, month, day);
                dialog.show();
                break;
            case R.id.add_bt:
                String contractId = ApiAddress.contractId;
                String orderNo = orderNoEt.getText().toString().trim();
                String orderName = orderNameEt.getText().toString().trim();
                String bigItemNo = bigItemNoEt.getText().toString().trim();
                String orderAmount = orderAmountEt.getText().toString().trim();
                String missNo = missNoEt.getText().toString().trim();
                String taxRate = taxRateEt.getText().toString().trim();
                Check(contractId, orderNo, orderName,
                        bigItemNo, orderAmount, time, missNo, taxRate);
                break;
        }
    }

    private void Check(String contractId, String orderNo, String orderName,
                       String bigItemNo, String orderAmount, String time,
                       String missNo, String taxRate) {

        if (missNo == null || missNo.isEmpty()) {
            UiUtils.showToast(this, "请输入MISS号");
            return;
        }
        if (taxRate == null || taxRate.isEmpty()) {
            UiUtils.showToast(this, "请输入税率");
            return;
        }

        if (orderNo == null || orderNo.isEmpty()) {
            UiUtils.showToast(this, "请输入订单编号");
            return;
        }
        if (orderName == null || orderName.isEmpty()) {
            UiUtils.showToast(this, "请输入订单名称");
            return;
        }
        if (bigItemNo == null || bigItemNo.isEmpty()) {
            UiUtils.showToast(this, "请输入大项编号");
            return;
        }
        if (orderAmount == null || orderAmount.isEmpty()) {
            UiUtils.showToast(this, "请输入订单金额");
            return;
        }
        if (time == null || time.isEmpty()) {
            UiUtils.showToast(this, "请选择时间");
            return;
        }
        addOrderPresenter.creatOrder(contractId, orderNo, orderName,
                bigItemNo, orderAmount, time, missNo, taxRate);
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
            timeStartTv.setText(time);

        }
    };

    @Override
    public void setContent(String content) {
        DialogUtil.showAlertDialog(mActivity, content + "");
    }

    @Override
    public void getData(final BaseEntry<OrderAddBean> data) {
        if (data.getCode().equals("200")) {
            dialog = DialogUtil.showDialog2(mActivity, "合同订单创建成功", new View.OnClickListener() {
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
}
