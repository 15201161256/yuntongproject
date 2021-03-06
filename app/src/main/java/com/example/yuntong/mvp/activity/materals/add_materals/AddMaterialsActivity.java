package com.example.yuntong.mvp.activity.materals.add_materals;

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
import com.example.yuntong.bean.MaterialsAddBean;
import com.example.yuntong.utils.DialogUtil;
import com.example.yuntong.utils.UiUtils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 杨晓峰
 * @create 2019/4/10
 * @Describe 新增材料
 */
public class AddMaterialsActivity extends BaseActivity implements MaterialsAddContract.View {

    @BindView(R.id.back_rl)
    RelativeLayout backRl;
    @BindView(R.id.materialName_et)
    EditText materialNameEt;
    @BindView(R.id.mat_model_et)
    EditText matModelEt;
    @BindView(R.id.mat_unit_et)
    EditText matUnitEt;
    @BindView(R.id.mat_comeNum_et)
    EditText matComeNumEt;
    @BindView(R.id.come_time_tv)
    TextView comeTimeTv;
    @BindView(R.id.come_rl)
    RelativeLayout comeRl;
    @BindView(R.id.enter_time_tv)
    TextView enterTimeTv;
    @BindView(R.id.start_time)
    RelativeLayout startTime;
    @BindView(R.id.mat_totalNum_et)
    EditText matTotalNumEt;
    @BindView(R.id.mat_receiver_et)
    EditText matReceiverEt;
    @BindView(R.id.mat_keeper_et)
    EditText matKeeperEt;
    @BindView(R.id.mat_remark_et)
    EditText matRemarkEt;
    @BindView(R.id.add_bt)
    Button addBt;

    private int year, month, day;
    private String time, flag, remark = "";
    private DatePickerDialog dialog;
    private MaterialsAddPresenter presenter;
    private Dialog dialog2 = null;

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

        presenter = new MaterialsAddPresenter(mActivity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_materials;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.back_rl, R.id.come_rl, R.id.start_time, R.id.add_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_rl:
                finish();
                break;
            case R.id.come_rl:
                flag = "1";
                dialog.show();
                break;
            case R.id.start_time:
                flag = "2";
                dialog.show();
                break;
            case R.id.add_bt:
                String name = materialNameEt.getText().toString().trim();
                String type = matModelEt.getText().toString().trim();
                String unit = matUnitEt.getText().toString().trim();
                Check(name, type, unit);
                break;
        }
    }

    private void Check(String name, String type, String unit) {
        if (name == null || name.isEmpty()) {
            UiUtils.showToast(this, "请输入材料名称");
            return;
        }
        if (type == null || type.isEmpty()) {
            UiUtils.showToast(this, "请输入材料型号");
            return;
        }
        if (unit == null || unit.isEmpty()) {
            UiUtils.showToast(this, "请输入材料单位");
            return;
        }
        presenter.creatMaterials(name, type, unit, remark);
    }


    /**
     * 日期选择的回调监听
     */
    private DatePickerDialog.OnDateSetListener mdateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int years, int monthOfYear, int dayOfMonth) {
            int month = monthOfYear + 1;
            time = years + "年" + month + "月" + dayOfMonth + "日";
            switch (flag) {
                case "1":
                    comeTimeTv.setText(time);
                    break;
                case "2":
                    enterTimeTv.setText(time);
                    break;

            }

        }
    };

    @Override
    public void setContent(String content) {
        DialogUtil.showAlertDialog(mActivity, content + "");
    }

    @Override
    public void getData(final BaseEntry<MaterialsAddBean> data) {
        if (data.getCode().equals("200")) {
            dialog2 = DialogUtil.showDialog2(mActivity, "新增材料创建成功", new View.OnClickListener() {
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
