package com.example.yuntong.mvp.activity.order.order_finance;

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
import com.example.yuntong.bean.OrderDetailBean;
import com.example.yuntong.utils.DialogUtil;
import com.example.yuntong.utils.UiUtils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 杨晓峰
 * @create 2019/4/23
 * @Describe 订单中的财务更新页面
 */
public class FinanceActivity extends BaseActivity implements FinanceContract.View {

    @BindView(R.id.invoiceAmount_et)
    EditText invoiceAmountEt;
    @BindView(R.id.add_bt_2)
    Button addBt2;
    @BindView(R.id.first_time_tv)
    TextView firstTimeTv;
    @BindView(R.id.second_time_tv)
    TextView secondTimeTv;
    @BindView(R.id.third_time_tv)
    TextView thirdTimeTv;
    @BindView(R.id.money_1)
    EditText money1;
    @BindView(R.id.money_2)
    EditText money2;
    @BindView(R.id.money_3)
    EditText money3;
    private String id;

    private FinancePresenter presenter;
    private Dialog dialog;
    private BaseEntry<OrderDetailBean> rowsBean;
    private int year, month, day;
    private String time, flag;
    private DatePickerDialog datePickerDialog;
    private String firstTime = "", secondTime = "", thirdTime = "", money_1 = "", money_2 = "", money_3 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        rowsBean = (BaseEntry<OrderDetailBean>) getIntent().getSerializableExtra("bean");
        if (rowsBean.getData().getItem().getInvoiceAmount() != null) {
            invoiceAmountEt.setText(rowsBean.getData().getItem().getInvoiceAmount() + "");
            money1.setText(rowsBean.getData().getItem().getAmount1() + "");
            money2.setText(rowsBean.getData().getItem().getAmount2() + "");
            money3.setText(rowsBean.getData().getItem().getAmount3() + "");

            firstTime = rowsBean.getData().getItem().getPaidTime1();
            secondTime = rowsBean.getData().getItem().getPaidTime2();
            thirdTime = rowsBean.getData().getItem().getPaidTime3();

            UiUtils.IsStringEmpty2(firstTime, firstTimeTv);
            UiUtils.IsStringEmpty2(secondTime, secondTimeTv);
            UiUtils.IsStringEmpty2(thirdTime, thirdTimeTv);
        }
        id = ApiAddress.orderId;
        presenter = new FinancePresenter(this, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_finance;
    }

    @Override
    protected void initView() {
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

    @OnClick({R.id.back_rl, R.id.add_bt_2, R.id.first_time, R.id.second_time, R.id.third_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.first_time:
                flag = "1";
                datePickerDialog.show();
                break;
            case R.id.second_time:
                flag = "2";
                datePickerDialog.show();
                break;
            case R.id.third_time:
                flag = "3";
                datePickerDialog.show();
                break;
            case R.id.back_rl:
                finish();
                break;
            case R.id.add_bt_2:
                String invoiceAmount = invoiceAmountEt.getText().toString().trim();
                money_1 = money1.getText().toString().trim();
                money_2 = money2.getText().toString().trim();
                money_3 = money3.getText().toString().trim();
                Check(id, invoiceAmount);
                break;
        }
    }

    private void Check(String id, String invoiceAmount) {
        if (invoiceAmount == null || invoiceAmount.isEmpty()) {
            UiUtils.showToast(this, "请输入开票金额");
            return;
        }
        presenter.creatFinance(id, invoiceAmount,
                money_1, firstTime, money_2, secondTime, money_3, thirdTime);
    }

    @Override
    public void setContent(String content) {
        DialogUtil.showAlertDialog(mActivity, content + "");
    }

    @Override
    public void getData(final BaseEntry<OrderDetailBean> data) {
        if (data.getCode().equals("200")) {
            dialog = DialogUtil.showDialog2(mActivity, "财务更新创建成功", new View.OnClickListener() {
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
                    firstTime = time;
                    firstTimeTv.setText(firstTime);
                    break;
                case "2":
                    secondTime = time;
                    secondTimeTv.setText(secondTime);
                    break;
                case "3":
                    thirdTime = time;
                    thirdTimeTv.setText(thirdTime);
                    break;
            }

        }
    };
}
