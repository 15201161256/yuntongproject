package com.example.yuntong.mvp.activity.materals.add_put_materals;

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
import com.example.yuntong.bean.MaterialPutBean;
import com.example.yuntong.utils.DialogUtil;
import com.example.yuntong.utils.UiUtils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 杨晓峰
 * @create 2019/6/6
 * @Describe 新增材料入库
 */
public class MateralsAddPutActivity extends BaseActivity implements MaterialsAddPutContract.View {

    @BindView(R.id.back_rl)
    RelativeLayout backRl;
    @BindView(R.id.count_et)
    EditText countEt;
    @BindView(R.id.come_time_tv)
    TextView comeTimeTv;
    @BindView(R.id.come_rl)
    RelativeLayout comeRl;
    @BindView(R.id.com_user_et)
    EditText comUserEt;
    @BindView(R.id.auditor_et)
    EditText auditorEt;
    @BindView(R.id.mat_remark_et)
    EditText matRemarkEt;
    @BindView(R.id.add_bt)
    Button addBt;
    private Dialog dialog2 = null;
    private MaterialsAddPutPresenter presenter;
    private int year, month, day;
    private String time;
    private DatePickerDialog dialog;

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
        presenter = new MaterialsAddPutPresenter(mActivity, this);
    }

    @Override
    protected int getLayoutId() {
        return (R.layout.activity_materals_add_put);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.back_rl, R.id.add_bt,R.id.come_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_rl:
                finish();
                break;
            case R.id.come_rl:
                dialog.show();
                break;
            case R.id.add_bt:
                String count = countEt.getText().toString().trim();
                String auditor = auditorEt.getText().toString().trim();
                String remark = matRemarkEt.getText().toString().trim();
                Check(count, auditor, remark);
                break;
        }
    }

    private void Check(String count, String auditor, String remark) {
        if (count == null || count.isEmpty()) {
            UiUtils.showToast(this, "请输入入库数量");
            return;
        }
        if (auditor == null || auditor.isEmpty()) {
            UiUtils.showToast(this, "请输入库管");
            return;
        }
        presenter.creatMaterialsPut(ApiAddress.materialId, "2", count,
                auditor, remark);
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
            comeTimeTv.setText(time);
        }

    };

    @Override
    public void setContent(String content) {
        DialogUtil.showAlertDialog(mActivity, content + "");
    }

    @Override
    public void getData(final BaseEntry<MaterialPutBean> data) {
        if (data.getCode().equals("200")) {
            dialog2 = DialogUtil.showDialog2(mActivity, "新增材料入库创建成功", new View.OnClickListener() {
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
