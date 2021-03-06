package com.example.yuntong.mvp.activity.materals.add_out_materal;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 杨晓峰
 * @create 2019/6/6
 * @Describe 新增材料出库
 */
public class MateralOutAddActivity extends BaseActivity implements MaterialsAddOutContract.View{

    @BindView(R.id.back_rl)
    RelativeLayout backRl;
    @BindView(R.id.materialName_et)
    EditText materialNameEt;
    @BindView(R.id.come_time_tv)
    TextView comeTimeTv;
    @BindView(R.id.come_rl)
    RelativeLayout comeRl;
    @BindView(R.id.mat_model_et)
    EditText matModelEt;
    @BindView(R.id.mat_remark_et)
    EditText matRemarkEt;
    @BindView(R.id.add_bt)
    Button addBt;
    private MaterialsAddOutPresenter presenter;
    private Dialog dialog2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=new MaterialsAddOutPresenter(mActivity,this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_materal_out_add;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.back_rl, R.id.add_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_rl:
                finish();
                break;
            case R.id.add_bt:
                String count = materialNameEt.getText().toString().trim();
                String auditor = matModelEt.getText().toString().trim();
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
        presenter.creatMaterialsPut(ApiAddress.materialId, "1", count,
                auditor, remark);
    }
    @Override
    public void setContent(String content) {
        DialogUtil.showAlertDialog(mActivity, content + "");
    }

    @Override
    public void getData(final BaseEntry<MaterialPutBean> data) {
        if (data.getCode().equals("200")) {
            dialog2 = DialogUtil.showDialog2(mActivity, "新增材料出库创建成功", new View.OnClickListener() {
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
