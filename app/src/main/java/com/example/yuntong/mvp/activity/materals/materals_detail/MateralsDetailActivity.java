package com.example.yuntong.mvp.activity.materals.materals_detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuntong.R;
import com.example.yuntong.app.ApiAddress;
import com.example.yuntong.base.BaseActivity;
import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.bean.MaterialsAddBean;
import com.example.yuntong.mvp.activity.materals.out_materals_list.MateralsOutListActivity;
import com.example.yuntong.mvp.activity.materals.put_materals_list.MateralsPutListActivity;
import com.example.yuntong.utils.DialogUtil;
import com.example.yuntong.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 杨晓峰
 * @create 2019/4/2
 * @Describe 材料详情
 */
public class MateralsDetailActivity extends BaseActivity implements MaterialsDetailContract.View {

    @BindView(R.id.materialsName_tv)
    TextView materialsNameTv;
    @BindView(R.id.type_tv)
    TextView typeTv;
    @BindView(R.id.unit_tv)
    TextView unitTv;
    @BindView(R.id.remainCount_tv)
    TextView remainCountTv;

    private MaterialsDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        presenter = new MaterialsDetailPresenter(mActivity, this);
        presenter.getMaterialsDetailData(ApiAddress.MATERIALS_DETAIL + ApiAddress.materialId);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_materals_detail;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.back_rl, R.id.chuku_rl, R.id.ruku_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_rl:
                finish();
                break;
            case R.id.ruku_rl:
                startActivity(new Intent(mActivity, MateralsPutListActivity.class));
                finish();
                break;
            case R.id.chuku_rl:
                startActivity(new Intent(mActivity, MateralsOutListActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void setContent(String content) {
        DialogUtil.showAlertDialog(mActivity, content + "");
    }

    @Override
    public void getData(BaseEntry<MaterialsAddBean> baseEntry) {
        if (baseEntry != null) {
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getName(), materialsNameTv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getType(), typeTv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getUnit(), unitTv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getRemainCount()+baseEntry.getData().getItem().getUnit(), remainCountTv);
        }

    }
}
