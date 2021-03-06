package com.example.yuntong.mvp.activity.contract.lock_contract;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.yuntong.R;
import com.example.yuntong.app.ApiAddress;
import com.example.yuntong.base.BaseActivity;
import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.bean.ContractListEntity;
import com.example.yuntong.bean.EditContractBean;
import com.example.yuntong.utils.DialogUtil;
import com.example.yuntong.utils.UiUtils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 杨晓峰
 * @create 2019/4/26
 * @Describe
 */
public class LockContractActivity extends BaseActivity implements LockContractContract.View {
    @BindView(R.id.back_rl)
    RelativeLayout backRl;
    @BindView(R.id.ok_bt)
    Button okBt;
    @BindView(R.id.regionList_et)
    EditText regionListEt;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.secondPartyName_et)
    EditText secondPartyNameEt;
    @BindView(R.id.contractNo_et)
    EditText contractNoEt;
    @BindView(R.id.contractName_et)
    EditText contractNameEt;
    @BindView(R.id.sign_tv)
    TextView signTv;
    @BindView(R.id.time_start_tv)
    TextView timeStartTv;
    @BindView(R.id.contractAmount_et)
    EditText contractAmountEt;
    private String[] ctype = new String[]{"移动", "联通", "电信", "铁塔", "省通建", "广电", "其他"};
    private ArrayAdapter<String> adapter;
    private String firstPartyName;
    private LockContractPresenter contractPresenter;
    private Dialog dialog;
    private ContractListEntity.RowsBean rowsBean;
    private int postion;
    private String id, signTime, startTime, flag, time;
    private int year, month, day;
    private DatePickerDialog datePickerDialog;
    private String contractAmount = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取当前年月日
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);//当前年
        month = calendar.get(Calendar.MONTH);//当前月
        day = calendar.get(Calendar.DAY_OF_MONTH);//当前日
        datePickerDialog = new DatePickerDialog(this, mdateListener, year, month, day);

        rowsBean = (ContractListEntity.RowsBean) getIntent().getSerializableExtra("a");
        id = rowsBean.getId() + "";
        spinner.setSelection(getString(rowsBean.getFirstPartyName()));

        regionListEt.setText(rowsBean.getRegion());
        secondPartyNameEt.setText(rowsBean.getSecondPartyName());
        contractNoEt.setText(rowsBean.getContractNo());
        contractNameEt.setText(rowsBean.getContractName());
        contractAmountEt.setText(rowsBean.getContractAmount());
        signTv.setText(rowsBean.getSignTime());
        timeStartTv.setText(rowsBean.getStartTime());

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
                    signTime = time;
                    signTv.setText(signTime);
                    break;
                case "2":
                    startTime = time;
                    timeStartTv.setText(startTime);
                    break;
            }

        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lock_contract;
    }

    @Override
    protected void initView() {
        adapter = new ArrayAdapter<String>(this, R.layout.spring_item, ctype);  //创建一个数组适配器
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);     //设置下拉列表框的下拉选项样式
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                firstPartyName = ctype[position] + "";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        contractPresenter = new LockContractPresenter(this, this);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.back_rl, R.id.edit_bt, R.id.ok_bt, R.id.start_time, R.id.signtime})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_rl:
                finish();
                break;
            case R.id.signtime:
                flag = "1";
                datePickerDialog.show();
                break;
            case R.id.start_time:
                flag = "2";
                datePickerDialog.show();
                break;
            case R.id.edit_bt:
                String region = regionListEt.getText().toString().trim();
                String secondPartyName = secondPartyNameEt.getText().toString().trim();
                String contractNo = contractNoEt.getText().toString().trim();
                String contractName = contractNameEt.getText().toString().trim();
                contractAmount = contractAmountEt.getText().toString().trim();
                Check(region, firstPartyName, secondPartyName,
                        contractNo, contractName,
                        signTime, startTime);
                break;
            case R.id.ok_bt:
                contractPresenter.overContract(ApiAddress.CONTRACT_OVER + ApiAddress.contractId);
                break;
        }
    }

    private void Check(String region, String firstPartyName, String secondPartyName,
                       String contractNo, String contractName,
                       String signTime, String startTime) {
        if (region == null || region.isEmpty()) {
            UiUtils.showToast(this, "请输入合同区域");
            return;
        }
        if (secondPartyName == null || secondPartyName.isEmpty()) {
            UiUtils.showToast(this, "请输入中标单位");
            return;
        }
        if (contractNo == null || contractNo.isEmpty()) {
            UiUtils.showToast(this, "请输入合同编号");
            return;
        }
        if (contractName == null || contractName.isEmpty()) {
            UiUtils.showToast(this, "请输入合同名称");
            return;
        }
        contractPresenter.editContract(id, region, firstPartyName, secondPartyName,
                contractNo, contractName, signTime, startTime,contractAmount);
    }

    @Override
    public void setContent(String content) {
        DialogUtil.showAlertDialog(mActivity, content + "");
    }

    @Override
    public void getData(final BaseEntry<EditContractBean> data) {
        if (data.getCode().equals("200")) {
            dialog = DialogUtil.showDialog2(mActivity, "合同锁定成功", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    Intent intent = new Intent();
                    intent.putExtra("position", getIntent().getIntExtra("position", -1));
                    intent.putExtra("bean", data);
                    setResult(2, intent);
                    finish();
                }
            });
        }
    }

    private int getString(String s) {
        switch (s) {
            case "移动":
                postion = 0;
                firstPartyName = "移动";
                break;
            case "联通":
                postion = 1;
                firstPartyName = "联通";
                break;
            case "电信":
                postion = 2;
                firstPartyName = "电信";
                break;
            case "铁塔":
                postion = 3;
                firstPartyName = "铁塔";
                break;
            case "省通建":
                postion = 4;
                firstPartyName = "省通建";
                break;
            case "广电":
                postion = 5;
                firstPartyName = "广电";
                break;
            case "其他":
                postion = 6;
                firstPartyName = "其他";
                break;
        }
        return postion;
    }


    @Override
    public void setEditContent(String content) {
        DialogUtil.showAlertDialog(mActivity, content + "");
    }

    @Override
    public void getEditData(final BaseEntry<EditContractBean> data) {
        if (data.getCode().equals("200")) {
            dialog = DialogUtil.showDialog2(mActivity, "合同修改成功", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    Intent intent = new Intent();
                    intent.putExtra("position", getIntent().getIntExtra("position", -1));
                    intent.putExtra("bean", data);
                    setResult(2, intent);
                    finish();
                }
            });
        }
    }
}
