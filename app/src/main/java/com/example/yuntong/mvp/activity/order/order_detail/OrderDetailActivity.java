package com.example.yuntong.mvp.activity.order.order_detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuntong.R;
import com.example.yuntong.app.ApiAddress;
import com.example.yuntong.base.BaseActivity;
import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.bean.OrderDetailBean;
import com.example.yuntong.mvp.activity.minor_term.minor_term_list.MinorTermListActivity;
import com.example.yuntong.mvp.activity.order.order_finance.FinanceActivity;
import com.example.yuntong.utils.DialogUtil;
import com.example.yuntong.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 杨晓峰
 * @create 2019/4/19
 * @Describe 订单详情
 */
public class OrderDetailActivity extends BaseActivity implements OrderDetailContract.View {

    @BindView(R.id.back_rl)
    RelativeLayout backRl;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.detail_rl)
    RelativeLayout detailRl;
    @BindView(R.id.add_contract_rl)
    RelativeLayout addContractRl;
    @BindView(R.id.orderNo_tv)
    TextView orderNoTv;
    @BindView(R.id.orderName_tv)
    TextView orderNameTv;
    @BindView(R.id.bigItemNo_tv)
    TextView bigItemNoTv;
    @BindView(R.id.orderAmount_tv)
    TextView orderAmountTv;
    @BindView(R.id.missNo_tv)
    TextView missNoTv;
    @BindView(R.id.invoiceAmount_tv)
    TextView invoiceAmountTv;
    @BindView(R.id.taxRate_tv)
    TextView taxRateTv;
    @BindView(R.id.amountCashed_tv)
    TextView amountCashedTv;
    @BindView(R.id.smallItemCount_tv)
    TextView smallItemCountTv;
    @BindView(R.id.amountCashedPercent)
    TextView amountCashedPercent;
    @BindView(R.id.amountBalance)
    TextView amountBalance;
    @BindView(R.id.startTime_tv)
    TextView startTimeTv;
    @BindView(R.id.endTime_tv)
    TextView endTimeTv;

    @BindView(R.id.amount1_tv)
    TextView amount1Tv;
    @BindView(R.id.amount2_tv)
    TextView amount2Tv;
    @BindView(R.id.amount3_tv)
    TextView amount3Tv;
    @BindView(R.id.paidTime1_tv)
    TextView paidTime1Tv;
    @BindView(R.id.paidTime2_tv)
    TextView paidTime2Tv;
    @BindView(R.id.paidTime3_tv)
    TextView paidTime3Tv;
    private OrderDetailPresenter presenter;
    private BaseEntry<OrderDetailBean> rowsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        presenter = new OrderDetailPresenter(this, this);
        presenter.getOrderDetailData(ApiAddress.ORDER_DETAIL + ApiAddress.orderId);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.back_rl, R.id.detail_rl, R.id.add_contract_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_rl:
                finish();
                break;
            case R.id.add_contract_rl://财务更新
                Intent intent = new Intent(mActivity, FinanceActivity.class);
                intent.putExtra("bean", rowsBean);
                startActivityForResult(intent, 100);
                break;
            case R.id.detail_rl:
                Intent intent2 = new Intent(mActivity, MinorTermListActivity.class);
                intent2.putExtra("bean", rowsBean);
                startActivity(intent2);
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 20) {
            if (requestCode == 100) {
                BaseEntry<OrderDetailBean> data2 = (BaseEntry<OrderDetailBean>) data.getSerializableExtra("bean");
                rowsBean=data2;
//                UiUtils.IsStringEmpty(data2.getData().getItem().getMissNo(), missNoTv);
//                UiUtils.IsStringEmptyPer(data2.getData().getItem().getTaxRate(), taxRateTv);
                UiUtils.IsStringEmptyYuan(data2.getData().getItem().getInvoiceAmount(), invoiceAmountTv);
                UiUtils.IsStringEmptyYuan(data2.getData().getItem().getAmountCashed(), amountCashedTv);
                UiUtils.IsStringEmpty(data2.getData().getItem().getAmountCashedPercent(), amountCashedPercent);
                UiUtils.IsStringEmpty(data2.getData().getItem().getAmountBalance(), amountBalance);

                UiUtils.IsStringEmptyYuan(data2.getData().getItem().getAmount1(), amount1Tv);
                UiUtils.IsStringEmpty(data2.getData().getItem().getPaidTime1(), paidTime1Tv);

                UiUtils.IsStringEmptyYuan(data2.getData().getItem().getAmount2(), amount2Tv);
                UiUtils.IsStringEmpty(data2.getData().getItem().getPaidTime2(), paidTime2Tv);

                UiUtils.IsStringEmptyYuan(data2.getData().getItem().getAmount3(), amount3Tv);
                UiUtils.IsStringEmpty(data2.getData().getItem().getPaidTime3(), paidTime3Tv);

            }
        }

    }

    @Override
    public void setContent(String content) {
        DialogUtil.showAlertDialog(mActivity, content + "");
    }

    @Override
    public void getData(BaseEntry<OrderDetailBean> baseEntry) {
        if (baseEntry != null) {
            rowsBean = baseEntry;
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getOrderNo(), orderNoTv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getOrderName(), orderNameTv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getBigItemNo(), bigItemNoTv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getStartTime(), startTimeTv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getEndTime(), endTimeTv);
            UiUtils.IsStringEmptyYuan(baseEntry.getData().getItem().getOrderAmount(), orderAmountTv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getMissNo(), missNoTv);
            UiUtils.IsStringEmpty("详情（" + baseEntry.getData().getItem().getSmallItemCount() + "）", smallItemCountTv);
            UiUtils.IsStringEmptyYuan(baseEntry.getData().getItem().getInvoiceAmount(), invoiceAmountTv);
            UiUtils.IsStringEmptyPer(baseEntry.getData().getItem().getTaxRate(), taxRateTv);
            UiUtils.IsStringEmptyYuan(baseEntry.getData().getItem().getAmountCashed(), amountCashedTv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getAmountCashedPercent(), amountCashedPercent);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getAmountBalance(), amountBalance);

            UiUtils.IsStringEmptyYuan(baseEntry.getData().getItem().getAmount1(), amount1Tv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getPaidTime1(), paidTime1Tv);

            UiUtils.IsStringEmptyYuan(baseEntry.getData().getItem().getAmount2(), amount2Tv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getPaidTime2(), paidTime2Tv);

            UiUtils.IsStringEmptyYuan(baseEntry.getData().getItem().getAmount3(), amount3Tv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getPaidTime3(), paidTime3Tv);
            switch (baseEntry.getData().getItem().getStatus()) {
                case 1:
                    addContractRl.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    addContractRl.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }
    }
}
