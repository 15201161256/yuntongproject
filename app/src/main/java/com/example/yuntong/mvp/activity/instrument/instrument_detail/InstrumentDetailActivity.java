package com.example.yuntong.mvp.activity.instrument.instrument_detail;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuntong.R;
import com.example.yuntong.app.ApiAddress;
import com.example.yuntong.base.BaseActivity;
import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.bean.InstrumentDetailBean;
import com.example.yuntong.utils.DialogUtil;
import com.example.yuntong.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 杨晓峰
 * @create 2019/4/2
 * @Describe 工具详情
 */
public class InstrumentDetailActivity extends BaseActivity implements InstrumentDetailContract.View{

    @BindView(R.id.back_rl)
    RelativeLayout backRl;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.type_tv)
    TextView typeTv;
    @BindView(R.id.purchaseTime_tv)
    TextView purchaseTimeTv;
    @BindView(R.id.startTime_tv)
    TextView startTimeTv;
    @BindView(R.id.endTime_tv)
    TextView endTimeTv;
    @BindView(R.id.username_tv)
    TextView usernameTv;
    @BindView(R.id.remark_tv)
    TextView remarkTv;
    private InstrumentDetailPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        presenter=new InstrumentDetailPresenter(mActivity,this);
        presenter.getInstrumentDetailData(ApiAddress.INSTRUMENT_DETAIL + ApiAddress.instrumentId);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_instrument_detail;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.back_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_rl:
                finish();
                break;
        }
    }

    @Override
    public void setContent(String content) {
        DialogUtil.showAlertDialog(mActivity, content + "");
    }

    @Override
    public void getData(BaseEntry<InstrumentDetailBean> baseEntry) {
        if (baseEntry != null) {
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getName(), nameTv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getType(), typeTv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getPurchaseTime(), purchaseTimeTv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getStartTime(), startTimeTv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getEndTime(), endTimeTv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getBorrower(), usernameTv);
            UiUtils.IsStringEmpty(baseEntry.getData().getItem().getRemark(), remarkTv);
        }

    }
}
