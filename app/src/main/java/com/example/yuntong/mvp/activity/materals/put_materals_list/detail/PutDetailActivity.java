package com.example.yuntong.mvp.activity.materals.put_materals_list.detail;

import android.os.Bundle;
import android.view.View;
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

public class PutDetailActivity extends BaseActivity implements PutDetailContract.View {

    @BindView(R.id.back_rl)
    RelativeLayout backRl;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.add_contract_rl)
    RelativeLayout addContractRl;
    @BindView(R.id.materialName_tv)
    TextView materialNameTv;
    @BindView(R.id.count_tv)
    TextView countTv;
    @BindView(R.id.applicantName_tv)
    TextView applicantNameTv;
    @BindView(R.id.auditor_tv)
    TextView auditorTv;
    @BindView(R.id.remark_tv)
    TextView remarkTv;
    @BindView(R.id.createTime_tv)
    TextView createTimeTv;
    private PutDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new PutDetailPresenter(mActivity, this);
        presenter.getMaterialsDetailData(ApiAddress.PUT_DETAIL + ApiAddress.material_Put_Id);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_put_detail;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.back_rl, R.id.add_contract_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_rl:
                finish();
                break;
            case R.id.add_contract_rl:
                break;
        }
    }

    @Override
    public void setContent(String content) {
        DialogUtil.showAlertDialog(mActivity, content + "");
    }

    @Override
    public void getData(BaseEntry<MaterialPutBean> baseEntry) {
        if (baseEntry != null) {
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getMaterialName(), materialNameTv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getApplicantName(), applicantNameTv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getAuditor(), auditorTv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getCreateTime(), createTimeTv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getCount(), countTv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getRemark(), remarkTv);
        }
    }
}
