package com.example.yuntong.mvp.activity.contract.contract_detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuntong.R;
import com.example.yuntong.app.ApiAddress;
import com.example.yuntong.base.BaseActivity;
import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.bean.ContractDetailBean;
import com.example.yuntong.mvp.activity.order.order_list.OrderListActivity;
import com.example.yuntong.utils.DialogUtil;
import com.example.yuntong.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 杨晓峰
 * @create 2019/3/26
 * @Describe 合同详情
 */
public class ContractDetailActivity extends BaseActivity implements ContractDetailContract.View {

    @BindView(R.id.back_rl)
    RelativeLayout backRl;
    @BindView(R.id.detail_rl)
    RelativeLayout detailRl;
    @BindView(R.id.region_tv)
    TextView regionTv;
    @BindView(R.id.firstPartyName_tv)
    TextView firstPartyNameTv;
    @BindView(R.id.secondPartyName_tv)
    TextView secondPartyNameTv;
    @BindView(R.id.contractNo_tv)
    TextView contractNoTv;
    @BindView(R.id.contractName_tv)
    TextView contractNameTv;
    @BindView(R.id.contractOrderCount_tv)
    TextView contractOrderCountTv;
    @BindView(R.id.contractAmount_tv)
    TextView contractAmountTv;
    @BindView(R.id.signTime_tv)
    TextView signTimeTv;
    @BindView(R.id.startTime_tv)
    TextView startTimeTv;
    @BindView(R.id.createTime_tv)
    TextView createTimeTv;
    private ContractDetailPresenter contractDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        contractDetailPresenter = new ContractDetailPresenter(this, this);
        contractDetailPresenter.getContractDetailData(ApiAddress.CONTRACT_DETAIL + ApiAddress.contractId);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_contract_detail;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.back_rl, R.id.detail_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_rl:
                finish();
                break;
            case R.id.detail_rl:
                startActivity(new Intent(mActivity, OrderListActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void setContent(String content) {
        DialogUtil.showAlertDialog(mActivity, content + "");
    }

    @Override
    public void getData(BaseEntry<ContractDetailBean> baseEntry) {
        if (baseEntry != null) {
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getRegion(), regionTv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getFirstPartyName(), firstPartyNameTv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getSecondPartyName(), secondPartyNameTv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getContractNo(), contractNoTv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getContractName(), contractNameTv);
            UiUtils.IsStringEmpty("详情（" + baseEntry.getData().getItem().getContractOrderCount() + "）", contractOrderCountTv);
            UiUtils.IsStringEmptyYuan(baseEntry.getData().getItem().getContractAmount(), contractAmountTv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getSignTime(), signTimeTv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getStartTime(), startTimeTv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getEndTime(), createTimeTv);
        }

    }
}
