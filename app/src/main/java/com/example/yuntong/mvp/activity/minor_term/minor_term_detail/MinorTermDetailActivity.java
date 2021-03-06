package com.example.yuntong.mvp.activity.minor_term.minor_term_detail;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuntong.R;
import com.example.yuntong.app.ApiAddress;
import com.example.yuntong.base.BaseActivity;
import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.bean.BaseBean;
import com.example.yuntong.bean.MinorTermDetailBean;
import com.example.yuntong.utils.DialogUtil;
import com.example.yuntong.utils.UiUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 杨晓峰
 * @create 2019/4/19
 * @Describe 小项详情
 */
public class MinorTermDetailActivity extends BaseActivity implements MinorDetailContract.View {

    @BindView(R.id.back_rl)
    RelativeLayout backRl;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.cooperator)
    TextView cooperator;
    @BindView(R.id.startDate)
    TextView startDate;
    @BindView(R.id.finishDate)
    TextView finishDate;
    @BindView(R.id.checkAndAcceptDate)
    TextView checkAndAcceptDate;
    @BindView(R.id.amount)
    TextView amount;
    @BindView(R.id.secondPartyConstructionCost)
    TextView secondPartyConstructionCost;
    @BindView(R.id.firstPartyMaterialCost)
    TextView firstPartyMaterialCost;
    @BindView(R.id.secondPartyMaterialCost)
    TextView secondPartyMaterialCost;
    @BindView(R.id.secondPartySafeProductionCost)
    TextView secondPartySafeProductionCost;
    @BindView(R.id.stipulatedFee)
    TextView stipulatedFee;
    @BindView(R.id.amountCashed)
    TextView amountCashed;
    @BindView(R.id.secondPartyAuditFee)
    TextView secondPartyAuditFee;
    @BindView(R.id.secondPartyDeductedAmount)
    TextView secondPartyDeductedAmount;
    @BindView(R.id.taxAmount)
    TextView taxAmount;
    @BindView(R.id.managementFee)
    TextView managementFee;
    @BindView(R.id.actualPayment_tv)
    TextView actualPaymentTv;
    @BindView(R.id.pay_end_bt)
    Button payEndBt;
    @BindView(R.id.pay_status_tv)
    TextView payStatusTv;
    @BindView(R.id.invoiceAmount_tv)
    TextView invoiceAmountTv;
    @BindView(R.id.accountPayable_tv)
    TextView accountPayableTv;
    @BindView(R.id.balance_tv)
    TextView balanceTv;

    private Dialog dialog;
    private MinorDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_minor_term_detail;
    }

    @Override
    protected void initView() {
        presenter = new MinorDetailPresenter(this, this);
        presenter.getMinorDetailData(ApiAddress.MINOR_DETAIL + ApiAddress.minnorId);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void setContent(String content) {
        DialogUtil.showAlertDialog(mActivity, content + "");
    }

    @Override
    public void getData(BaseEntry<MinorTermDetailBean> baseEntry) {
        if (baseEntry != null) {
            if (baseEntry.getData().getItem().getTotalPaid().equals("true")) {
                payEndBt.setVisibility(View.GONE);
                payStatusTv.setVisibility(View.VISIBLE);
                payStatusTv.setText("已确认");
            } else {
                payEndBt.setVisibility(View.VISIBLE);
                payStatusTv.setVisibility(View.GONE);
            }
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getName(), name);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getCooperator(), cooperator);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getStartDate(), startDate);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getEndDate(), finishDate);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getCheckAndAcceptDate(), checkAndAcceptDate);
            UiUtils.IsStringEmptyYuan(baseEntry.getData().getItem().getAmount(), amount);
            UiUtils.IsStringEmptyYuan(baseEntry.getData().getItem().getSecondPartyConstructionCost(), secondPartyConstructionCost);
            UiUtils.IsStringEmptyYuan(baseEntry.getData().getItem().getFirstPartyMaterialCost(), firstPartyMaterialCost);
            UiUtils.IsStringEmptyYuan(baseEntry.getData().getItem().getSecondPartyMaterialCost(), secondPartyMaterialCost);
            UiUtils.IsStringEmptyYuan(baseEntry.getData().getItem().getSecondPartySafeProductionCost(), secondPartySafeProductionCost);
            UiUtils.IsStringEmptyYuan(baseEntry.getData().getItem().getStipulatedFee(), stipulatedFee);
            UiUtils.IsStringEmptyYuan(baseEntry.getData().getItem().getAmountCashed(), amountCashed);
            UiUtils.IsStringEmptyYuan(baseEntry.getData().getItem().getSecondPartyAuditFee(), secondPartyAuditFee);
            UiUtils.IsStringEmptyYuan(baseEntry.getData().getItem().getSecondPartyDeductedAmount(), secondPartyDeductedAmount);
            UiUtils.IsStringEmptyYuan(baseEntry.getData().getItem().getTaxAmount(), taxAmount);
            UiUtils.IsStringEmptyYuan(baseEntry.getData().getItem().getManagementFee(), managementFee);
            UiUtils.IsStringEmptyYuan(baseEntry.getData().getItem().getActualPayment(), actualPaymentTv);
            UiUtils.IsStringEmptyYuan(baseEntry.getData().getItem().getInvoiceAmount(), invoiceAmountTv);
            UiUtils.IsStringEmptyYuan(baseEntry.getData().getItem().getAccountPayable(), accountPayableTv);
            UiUtils.IsStringEmptyYuan(baseEntry.getData().getItem().getBalance(), balanceTv);
        }
    }

    @Override
    public void setContentEnd(String content) {
        DialogUtil.showAlertDialog(mActivity, content + "");
    }

    @Override
    public void getDataEnd(BaseEntry<BaseBean> baseEntry) {
        if (baseEntry.getCode().equals("200")) {
            dialog = DialogUtil.showDialog2(mActivity, "支付尾款已确认", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    payEndBt.setVisibility(View.GONE);
                    payStatusTv.setVisibility(View.VISIBLE);
                    payStatusTv.setText("已确认");
                }
            });
        }

    }

    @OnClick({R.id.back_rl, R.id.pay_end_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_rl:
                finish();
                break;
            case R.id.pay_end_bt:
                presenter.endPayStatus(ApiAddress.minnorId);
                break;
        }
    }
}
