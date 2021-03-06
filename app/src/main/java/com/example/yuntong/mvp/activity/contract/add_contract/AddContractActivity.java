package com.example.yuntong.mvp.activity.contract.add_contract;

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
import android.widget.Spinner;
import android.widget.TextView;

import com.example.yuntong.R;
import com.example.yuntong.base.BaseActivity;
import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.bean.EditContractBean;
import com.example.yuntong.utils.DialogUtil;
import com.example.yuntong.utils.UiUtils;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 杨晓峰
 * @create 2019/3/28
 * @Describe 新增合同
 */
public class AddContractActivity extends BaseActivity implements AddContractContract.View {


    @BindView(R.id.regionList_et)
    EditText regionListEt;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.secondPartyName_et)
    EditText secondPartyNameEt;
    @BindView(R.id.contractNo_et)
    EditText contractNoEt;
    @BindView(R.id.sign_tv)
    TextView signTv;
    @BindView(R.id.time_start_tv)
    TextView timeStartTv;
    @BindView(R.id.end_time_tv)
    TextView endTimeTv;
    @BindView(R.id.contractName_et)
    EditText contractNameEt;
    @BindView(R.id.contractAmount_et)
    EditText contractAmountEt;
    @BindView(R.id.add_bt)
    Button addBt;
    @BindView(R.id.city_tv)
    TextView cityTv;
    private int year, month, day;

    private String time, flag;
    private DatePickerDialog dialog;

    private String[] ctype = new String[]{"移动", "联通", "电信", "铁塔", "省通建", "广电", "其他"};
    private ArrayAdapter<String> adapter;

    private AddContractPresenter addContractPresenter;
    private String firstPartyName = "移动";
    private Dialog dialog2 = null;
    private String signTime = "";
    private String startTime = "";
    private String contractAmount = "0";
    private String region;

    //申明对象
    private CityPickerView mPicker = new CityPickerView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        //获取当前年月日
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);//当前年
        month = calendar.get(Calendar.MONTH);//当前月
        day = calendar.get(Calendar.DAY_OF_MONTH);//当前日
        dialog = new DatePickerDialog(this, mdateListener, year, month, day);
        mPicker.init(this);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ctype);  //创建一个数组适配器
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);     //设置下拉列表框的下拉选项样式
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                UiUtils.showToast(ContractTotalActivity.this,"点击了"+ctype[position]);
                firstPartyName = ctype[position] + "";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        addContractPresenter = new AddContractPresenter(mActivity, this);

//        showCityView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_contract;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.back_rl, R.id.start_time, R.id.add_bt, R.id.signtime, R.id.city_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_rl:
                finish();
                break;
//            case R.id.city_rl:
//                //显示
//                mPicker.showCityPicker();
//                break;
            case R.id.add_bt:
                region = regionListEt.getText().toString().trim();
                String secondPartyName = secondPartyNameEt.getText().toString().trim();
                String contractNo = contractNoEt.getText().toString().trim();
                String contractName = contractNameEt.getText().toString().trim();
                contractAmount = contractAmountEt.getText().toString().trim();
                Check(region, firstPartyName, secondPartyName,
                        contractNo, contractName,
                        signTime, startTime);
                break;
            case R.id.signtime:
                flag = "1";
                dialog.show();
                break;
            case R.id.start_time:
                flag = "2";
                dialog.show();
                break;
//            case R.id.end_time:
//                flag = "3";
//                dialog.show();
//                break;
        }
    }

    private void showCityView() {
        CityConfig cityConfig = new CityConfig.Builder()
                .title("选择城市")//标题
                .titleTextSize(18)//标题文字大小
                .titleTextColor("#585858")//标题文字颜  色
                .titleBackgroundColor("#E9E9E9")//标题栏背景色
                .confirTextColor("#585858")//确认按钮文字颜色
                .confirmText("确认")//确认按钮文字
                .confirmTextSize(16)//确认按钮文字大小
                .cancelTextColor("#585858")//取消按钮文字颜色
                .cancelText("取消")//取消按钮文字
                .cancelTextSize(16)//取消按钮文字大小
                .setCityWheelType(CityConfig.WheelType.PRO_CITY_DIS)//显示类，只显示省份一级，显示省市两级还是显示省市区三级
                .showBackground(true)//是否显示半透明背景
                .visibleItemsCount(5)//显示item的数量
                .province("河北省")//默认显示的省份
                .city("邯郸市")//默认显示省份下面的城市
                .district("肥乡县")//默认显示省市下面的区县数据
                .provinceCyclic(true)//省份滚轮是否可以循环滚动
                .cityCyclic(true)//城市滚轮是否可以循环滚动
                .districtCyclic(true)//区县滚轮是否循环滚动
//                .setCustomItemLayout(R.layout.item_city)//自定义item的布局
//                .setCustomItemTextViewId(R.id.item_city_name_tv)//自定义item布局里面的textViewid
                .drawShadows(false)//滚轮不显示模糊效果
                .setLineColor("#03a9f4")//中间横线的颜色
                .setLineHeigh(5)//中间横线的高度
                .setShowGAT(true)//是否显示港澳台数据，默认不显示
                .build();
        mPicker.setConfig(cityConfig);
        //监听选择点击事件及返回结果
        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {

                //省份
                if (province != null) {

                }

                //城市
                if (city != null) {

                }

                //地区
                if (district != null) {

                }
                region = province.getName() + city.getName() + district.getName();
                cityTv.setText(region);
            }
        });

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
//        if (contractAmount == null || contractAmount.isEmpty()) {
//            UiUtils.showToast(this, "请输入合同金额");
//            return;
//        }
        addContractPresenter.creatContract(region, firstPartyName, secondPartyName,
                contractNo, contractName,
                signTime, startTime, contractAmount);
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
//                case "3":
//                    endTimeTv.setText(time);
//                    break;
            }

        }
    };

    @Override
    public void setContent(String content) {
        DialogUtil.showAlertDialog(mActivity, content + "");
    }

    @Override
    public void getData(final BaseEntry<EditContractBean> data) {
        if (data.getCode().equals("200")) {
            dialog2 = DialogUtil.showDialog2(mActivity, "框架合同创建成功", new View.OnClickListener() {
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
