package com.example.yuntong.mvp.fragment.homefragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuntong.R;
import com.example.yuntong.app.ApiAddress;
import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BaseFragment;
import com.example.yuntong.bean.HomeBean;
import com.example.yuntong.mvp.activity.contract.contract_detail.ContractDetailActivity;
import com.example.yuntong.mvp.activity.instrument.instrument_detail.InstrumentDetailActivity;
import com.example.yuntong.mvp.activity.materals.materals_detail.MateralsDetailActivity;
import com.example.yuntong.utils.DialogUtil;
import com.example.yuntong.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author 杨晓峰
 * @create 2019/3/26
 * @Describe 合同首页
 */
public class HomeFragment extends BaseFragment implements HomeContract.View {
    @BindView(R.id.contractName_tv)
    TextView contractNameTv;
    @BindView(R.id.secondPartyName_tv)
    TextView secondPartyNameTv;
    @BindView(R.id.firstPartyName_tv)
    TextView firstPartyNameTv;
    @BindView(R.id.createTime_tv)
    TextView createTimeTv;

    @BindView(R.id.materialsName_tv)
    TextView materialsNameTv;
    @BindView(R.id.model_tv)
    TextView modelTv;
    @BindView(R.id.ma_unit_tv)
    TextView maUnitTv;
    @BindView(R.id.mat_Num_tv)
    TextView matNumTv;

    @BindView(R.id.ma_name_tv2)
    TextView maNameTv2;
    @BindView(R.id.ma_type_tv2)
    TextView maTypeTv2;
    @BindView(R.id.ma_unit_tv2)
    TextView maUnitTv2;
    @BindView(R.id.ma_num_tv2)
    TextView maNumTv2;
    @BindView(R.id.materials_rl_2)
    RelativeLayout materialsRl2;
    @BindView(R.id.in_name_tv)
    TextView inNameTv;
    @BindView(R.id.in_type_tv)
    TextView inTypeTv;
    @BindView(R.id.in_createTime_tv)
    TextView inCreateTimeTv;
    @BindView(R.id.in_borrower_tv)
    TextView inBorrowerTv;
    @BindView(R.id.instrument_rl_1)
    RelativeLayout instrumentRl1;
    @BindView(R.id.in_name_tv2)
    TextView inNameTv2;
    @BindView(R.id.in_type_tv2)
    TextView inTypeTv2;
    @BindView(R.id.in_createTime_tv2)
    TextView inCreateTimeTv2;
    @BindView(R.id.in_borrower_tv2)
    TextView inBorrowerTv2;
    @BindView(R.id.instrument_rl_2)
    RelativeLayout instrumentRl2;
    Unbinder unbinder;


    @BindView(R.id.ma_show_ll)
    LinearLayout maShowLl;
    @BindView(R.id.ma_show_tv)
    TextView maShowTv;
    @BindView(R.id.materials_rl_1)
    RelativeLayout materialsRl1;
    @BindView(R.id.ma_show_ll2)
    LinearLayout maShowLl2;
    @BindView(R.id.ma_show_tv2)
    TextView maShowTv2;
    @BindView(R.id.in_show_ll)
    LinearLayout inShowLl;
    @BindView(R.id.in_show_tv)
    TextView inShowTv;
    @BindView(R.id.in_show_ll2)
    LinearLayout inShowLl2;
    @BindView(R.id.in_show_tv2)
    TextView inShowTv2;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.rl_1)
    RelativeLayout rl1;
    @BindView(R.id.vie)
    View vie;
    @BindView(R.id.show_iv)
    ImageView showIv;
    @BindView(R.id.state_tv)
    TextView stateTv;
    @BindView(R.id.cn_show_ll)
    LinearLayout cnShowLl;
    @BindView(R.id.cn_show_tv)
    TextView cnShowTv;
    @BindView(R.id.contract_ll)
    RelativeLayout contractLl;
    private HomePresenter presenter;
    private String contractId, materialId_1, materialId_2, instrumentId_1, instrumentId_2;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    protected void initView() {
        presenter = new HomePresenter(getContext(), this);
    }


    @Override
    protected void initData() {
        presenter.getHomeData(ApiAddress.HOME_DATA);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.materials_rl_1, R.id.materials_rl_2, R.id.instrument_rl_1, R.id.instrument_rl_2, R.id.contract_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.contract_ll:
                ApiAddress.contractId = contractId;
                startActivity(new Intent(getContext(), ContractDetailActivity.class));
                break;
            case R.id.materials_rl_1:
                ApiAddress.materialId = materialId_1;
                startActivity(new Intent(getContext(), MateralsDetailActivity.class));
                break;
            case R.id.materials_rl_2:
                ApiAddress.materialId = materialId_2;
                startActivity(new Intent(getContext(), MateralsDetailActivity.class));
                break;
            case R.id.instrument_rl_1:
                ApiAddress.instrumentId = instrumentId_1;
                startActivity(new Intent(getContext(), InstrumentDetailActivity.class));
                break;
            case R.id.instrument_rl_2:
                ApiAddress.instrumentId = instrumentId_2;
                startActivity(new Intent(getContext(), InstrumentDetailActivity.class));
                break;
        }
    }

    @Override
    public void setContent(String content) {
        DialogUtil.showAlertDialog(getActivity(), content + "");
    }

    @Override
    public void getData(BaseEntry<HomeBean> baseEntry) {
        if (baseEntry != null) {
            if (baseEntry.getData().getContract() != null) {
                contractId = baseEntry.getData().getContract().getId() + "";
                UiUtils.IsStringEmpty(baseEntry.getData().getContract().getContractName(), contractNameTv);
                UiUtils.IsStringEmpty(baseEntry.getData().getContract().getFirstPartyName(), firstPartyNameTv);
                UiUtils.IsStringEmpty(baseEntry.getData().getContract().getSecondPartyName(), secondPartyNameTv);
                UiUtils.IsStringEmpty(baseEntry.getData().getContract().getCreateTime(), createTimeTv);

                int status = baseEntry.getData().getContract().getStatus();
                switch (status) {
                    case 1:
                        showIv.setImageResource(R.mipmap.invest_point_two);
                        stateTv.setText("进行中");
                        stateTv.setTextColor(Color.rgb(78, 221, 179));
                        break;
                    case 2:
                        showIv.setImageResource(R.mipmap.invest_list_point_ongoing);
                        stateTv.setText("已结束");
                        stateTv.setTextColor(Color.rgb(255, 76, 66));
                        break;
                    default:
                        stateTv.setText("未知");
                        break;
                }
            } else {
                contractLl.setClickable(false);
                cnShowLl.setVisibility(View.GONE);
                cnShowTv.setVisibility(View.VISIBLE);
            }

            if (baseEntry.getData().getMaterialList().size() > 0) {
                materialId_1 = baseEntry.getData().getMaterialList().get(0).getId() + "";
                UiUtils.IsStringEmpty(baseEntry.getData().getMaterialList().get(0).getName(), materialsNameTv);
                UiUtils.IsStringEmpty(baseEntry.getData().getMaterialList().get(0).getType(), modelTv);
                UiUtils.IsStringEmpty(baseEntry.getData().getMaterialList().get(0).getUnit(), maUnitTv);
                UiUtils.IsStringEmpty(baseEntry.getData().getMaterialList().get(0).getRemainCount() + "", matNumTv);

                if (baseEntry.getData().getMaterialList().size() > 1) {

                    materialId_2 = baseEntry.getData().getMaterialList().get(1).getId() + "";
                    UiUtils.IsStringEmpty(baseEntry.getData().getMaterialList().get(1).getName(), maNameTv2);
                    UiUtils.IsStringEmpty(baseEntry.getData().getMaterialList().get(1).getType(), maTypeTv2);
                    UiUtils.IsStringEmpty(baseEntry.getData().getMaterialList().get(1).getUnit(), maUnitTv2);
                    UiUtils.IsStringEmpty(baseEntry.getData().getMaterialList().get(1).getRemainCount() + "", maNumTv2);

                } else {
                    materialsRl2.setClickable(false);
                    maShowLl2.setVisibility(View.GONE);
                    maShowTv2.setVisibility(View.VISIBLE);
                }
            } else {
                materialsRl1.setClickable(false);
                maShowLl.setVisibility(View.GONE);
                maShowTv.setVisibility(View.VISIBLE);

                materialsRl2.setClickable(false);
                maShowLl2.setVisibility(View.GONE);
                maShowTv2.setVisibility(View.VISIBLE);
            }


            if (baseEntry.getData().getInstrumentList().size() > 0) {
                instrumentId_1 = baseEntry.getData().getInstrumentList().get(0).getId() + "";
                UiUtils.IsStringEmpty(baseEntry.getData().getInstrumentList().get(0).getName(), inNameTv);
                UiUtils.IsStringEmpty(baseEntry.getData().getInstrumentList().get(0).getType(), inTypeTv);
                UiUtils.IsStringEmpty(baseEntry.getData().getInstrumentList().get(0).getPurchaseTime(), inCreateTimeTv);
                UiUtils.IsStringEmpty(baseEntry.getData().getInstrumentList().get(0).getBorrower(), inBorrowerTv);

                if (baseEntry.getData().getInstrumentList().size() > 1) {
                    instrumentId_2 = baseEntry.getData().getInstrumentList().get(1).getId() + "";
                    UiUtils.IsStringEmpty(baseEntry.getData().getInstrumentList().get(1).getName(), inNameTv2);
                    UiUtils.IsStringEmpty(baseEntry.getData().getInstrumentList().get(1).getType(), inTypeTv2);
                    UiUtils.IsStringEmpty(baseEntry.getData().getInstrumentList().get(1).getPurchaseTime(), inCreateTimeTv2);
                    UiUtils.IsStringEmpty(baseEntry.getData().getInstrumentList().get(1).getBorrower(), inBorrowerTv2);

                } else {
                    instrumentRl2.setClickable(false);
                    inShowLl2.setVisibility(View.GONE);
                    inShowTv2.setVisibility(View.VISIBLE);
                }
            } else {
                instrumentRl1.setClickable(false);
                inShowLl.setVisibility(View.GONE);
                inShowTv.setVisibility(View.VISIBLE);

                instrumentRl2.setClickable(false);
                inShowLl2.setVisibility(View.GONE);
                inShowTv2.setVisibility(View.VISIBLE);
            }


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
