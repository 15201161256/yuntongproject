package com.example.yuntong.mvp.activity.contract.contract_total;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.yuntong.R;
import com.example.yuntong.base.BaseActivity;
import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.bean.ContractToatalBean;
import com.example.yuntong.mvp.activity.contract.contract_total_minor_list.ContractToatalMinorActivity;
import com.example.yuntong.utils.DialogUtil;
import com.example.yuntong.utils.UiUtils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 杨晓峰
 * @create 2019/3/27
 * @Describe 合同汇总
 */
public class ContractTotalActivity extends BaseActivity implements ContractTotalContract.View {


    @BindView(R.id.back_rl)
    RelativeLayout backRl;
    @BindView(R.id.time_start_tv)
    TextView timeStartTv;
    @BindView(R.id.start_time)
    RelativeLayout startTime;
    @BindView(R.id.end_time_tv)
    TextView endTimeTv;
    @BindView(R.id.end_time)
    RelativeLayout endTime;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.gongying)
    RelativeLayout gongying;
    @BindView(R.id.contract_num)
    TextView contractNum;
    @BindView(R.id.zhichu_rl)
    RelativeLayout zhichuRl;
    @BindView(R.id.cashedAmount_tv)
    TextView cashedAmountTv;
    @BindView(R.id.payedAmount_tv)
    TextView payedAmountTv;
    @BindView(R.id.balanceAmount_tv)
    TextView balanceAmountTv;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.realTimeAmount_tv)
    TextView realTimeAmountTv;
    @BindView(R.id.actualAmount_tv)
    TextView actualAmountTv;
    @BindView(R.id.show_ll)
    LinearLayout showLl;
    @BindView(R.id.empty_ll)
    LinearLayout emptyLl;
    @BindView(R.id.ok_bt)
    Button okBt;
    @BindView(R.id.secondPartyName_et)
    EditText secondPartyNameEt;
    @BindView(R.id.spinner_min)
    Spinner spinnerMin;
    private int year, month, day;

    private String time, flag, endTimeBegin, endTimeEnd, firstPartyName = "移动", status = "已完成", status_end;
    private String[] ctype = new String[]{"移动", "联通", "电信", "铁塔", "省通建", "广电", "其他"};
    private String[] ctype2 = new String[]{"已完成", "未完成"};
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> adapter2;
    private DatePickerDialog dialog;
    private ContratTotalPresenter presenter;
    private String cooperator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ctype);  //创建一个数组适配器
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ctype2);  //创建一个数组适配器
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);     //设置下拉列表框的下拉选项样式
        adapter2.setDropDownViewResource(android.R.layout.simple_list_item_1);     //设置下拉列表框的下拉选项样式
        spinner.setAdapter(adapter);
        spinnerMin.setAdapter(adapter2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                firstPartyName = ctype[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerMin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                status = ctype2[position];
                if (status.equals("已完成")) {
                    status_end = "true";
                } else {
                    status_end = "false";
                    endTimeEnd = "";
                    endTimeTv.setText("请选择");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (status.equals("已完成")) {
            status_end = "true";
        } else {
            status_end = "false";
            endTimeEnd = "";
            endTimeTv.setText("请选择");
        }
        //获取当前年月日
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);//当前年
        month = calendar.get(Calendar.MONTH);//当前月
        day = calendar.get(Calendar.DAY_OF_MONTH);//当前日
        dialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_DARK, mdateListener, year, month, day);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_contract_total;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        presenter = new ContratTotalPresenter(mActivity, this);
    }

    @OnClick({R.id.back_rl, R.id.start_time, R.id.end_time, R.id.zhichu_rl, R.id.ok_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ok_bt:
                cooperator = secondPartyNameEt.getText().toString().trim();
                Check(endTimeBegin, endTimeEnd);
                break;
            case R.id.back_rl:
                finish();
                break;
            case R.id.start_time:
                flag = "1";
                dialog.show();
                break;
            case R.id.end_time:
                if (status.equals("已完成")) {
                    flag = "2";
                    dialog.show();
                } else {
                    DialogUtil.showAlertDialog(mActivity, "查询未结束小项，不能输入结束时间!");
                }
                break;
            case R.id.zhichu_rl://跳转到小项列表
                Intent intent = new Intent(mActivity, ContractToatalMinorActivity.class);
                intent.putExtra("endTimeBegin", endTimeBegin);
                intent.putExtra("endTimeEnd", endTimeEnd);
                intent.putExtra("firstPartyName", firstPartyName);
                intent.putExtra("end", status_end);
                intent.putExtra("cooperator", cooperator);
                startActivity(intent);
                break;
        }
    }

    private void Check(String endTimeBegin, String endTimeEnd) {

        if (endTimeBegin == null || endTimeBegin.isEmpty()) {
            UiUtils.showToast(this, "请选择开始时间");
            return;
        }
        if (!status_end.equals("false")) {
            if (endTimeEnd == null || endTimeEnd.isEmpty()) {
                UiUtils.showToast(this, "请选择结束时间");
                return;
            }
        }

        presenter.contractMiTotal(endTimeBegin, endTimeEnd, firstPartyName, cooperator, status_end);
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
                    endTimeBegin = time;
                    timeStartTv.setText(endTimeBegin);
                    break;
                case "2":
                    endTimeEnd = time;
                    endTimeTv.setText(endTimeEnd);
                    break;
            }

        }
    };

    @Override
    public void setContent(String content) {
        DialogUtil.showAlertDialog(mActivity, content + "");
    }

    @Override
    public void getData(BaseEntry<ContractToatalBean> baseEntry) {
        if (baseEntry != null && baseEntry.getData().getItem().getCount() > 0) {
            emptyLl.setVisibility(View.GONE);
            showLl.setVisibility(View.VISIBLE);

            UiUtils.IsStringEmptyYuan(baseEntry.getData().getItem().getCashedAmount(), cashedAmountTv);
            UiUtils.IsStringEmptyYuan(baseEntry.getData().getItem().getPayedAmount(), payedAmountTv);
            UiUtils.IsStringEmptyYuan(baseEntry.getData().getItem().getBalanceAmount(), balanceAmountTv);
            UiUtils.IsStringEmptyYuan(baseEntry.getData().getItem().getRealTimeAmount(), realTimeAmountTv);
            UiUtils.IsStringEmptyYuan(baseEntry.getData().getItem().getActualAmount(), actualAmountTv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getCount() + "", contractNum);

        } else {
            showLl.setVisibility(View.GONE);
            emptyLl.setVisibility(View.VISIBLE);
        }

    }
}
