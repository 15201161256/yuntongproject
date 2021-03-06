package com.example.yuntong.mvp.activity.contract.contract_total_minor_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.yuntong.R;
import com.example.yuntong.adapter.MinorTermAdapter;
import com.example.yuntong.app.ApiAddress;
import com.example.yuntong.base.BaseActivity;
import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.bean.ContractToatalBean;
import com.example.yuntong.bean.EditMinorTermBean;
import com.example.yuntong.bean.MinorTermAddBean;
import com.example.yuntong.bean.MinorTermBean;
import com.example.yuntong.mvp.activity.contract.contract_total.ContractTotalContract;
import com.example.yuntong.mvp.activity.minor_term.minor_item_lock.LockMinorTermActivity;
import com.example.yuntong.mvp.activity.minor_term.minor_term_detail.MinorTermDetailActivity;
import com.example.yuntong.utils.DialogUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 杨晓峰
 * @create 2019/5/23
 * @Describe 合同汇总小项列表
 */
public class ContractToatalMinorActivity extends BaseActivity implements ContractTotalListContract.View {

    @BindView(R.id.back_rl)
    RelativeLayout backRl;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.recy)
    RecyclerView recy;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private ContractTotalListPresenter presenter;
    private MinorTermAdapter minorTermAdapter;
    private List<MinorTermBean.RowsBean> minorTermBeans = new ArrayList<>();
    private View emptyView;
    private int page = 1;
    private String endTimeBegin, endTimeEnd, firstPartyName, end,cooperator;
    private BaseEntry<EditMinorTermBean> entry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_contract_toatal_minor;
    }

    @Override
    protected void initView() {
        emptyView = View.inflate(this, R.layout.null_item, null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recy.setLayoutManager(layoutManager);
        presenter = new ContractTotalListPresenter(mActivity, this);
    }

    @Override
    protected void initData() {
        if (getIntent() != null) {
            endTimeBegin = getIntent().getStringExtra("endTimeBegin");
            endTimeEnd = getIntent().getStringExtra("endTimeEnd");
            firstPartyName = getIntent().getStringExtra("firstPartyName");
            end = getIntent().getStringExtra("end");
            cooperator = getIntent().getStringExtra("cooperator");
            presenter.getMinorTermList(page, 10, endTimeBegin, endTimeEnd, firstPartyName, end,cooperator);
        }
        Init_Refresh();
    }

    private void Init_Refresh() {
        //刷新
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                minorTermBeans.clear();
                page = 1;
                presenter.getMinorTermList(page, 10, endTimeBegin, endTimeEnd, firstPartyName, end,cooperator);
                minorTermAdapter.notifyDataSetChanged();
                refreshlayout.finishRefresh(1000);
            }
        });

        //加载更多
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.getMinorTermList(page, 10, endTimeBegin, endTimeEnd, firstPartyName, end,cooperator);
                minorTermAdapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore(1000);//加载完成
            }
        });
    }


    private void initRcDta() {
        if (minorTermAdapter == null) {
            minorTermAdapter = new MinorTermAdapter(mActivity, minorTermBeans);
            //5，给recyclerView设置空布局
            if (minorTermBeans == null || minorTermBeans.size() == 0) {
                minorTermAdapter.setEmptyView(emptyView);
            }
            recy.setAdapter(minorTermAdapter);
            recy.addOnItemTouchListener(new OnItemChildClickListener() {
                @Override
                public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                }

                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    super.onItemChildClick(adapter, view, position);
                    int itemViewId = view.getId();
                    MinorTermBean.RowsBean rowsBean = minorTermBeans.get(position);
                    ApiAddress.minnorId = rowsBean.getId() + "";
                    switch (itemViewId) {
                        case R.id.conten_ll:
                            startActivity(new Intent(mActivity, MinorTermDetailActivity.class));
                            break;
                        case R.id.show_ll:
                            Intent intent = new Intent(mActivity, LockMinorTermActivity.class);
                            intent.putExtra("bean", rowsBean);
                            intent.putExtra("position", position);
                            startActivityForResult(intent, 200);
                            break;
                    }
                }
            });
        } else {
            minorTermAdapter.notifyDataSetChanged();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 300) {
            if (requestCode == 200) {
                int postion = data.getIntExtra("position", -1);
                entry = (BaseEntry<EditMinorTermBean>) data.getSerializableExtra("bean");
                if (postion != -1) {
                    minorTermBeans.get(postion).setEndDate(entry.getData().getItem().getEndDate());
                    minorTermBeans.get(postion).setStructureType(entry.getData().getItem().getStructureType());
                    minorTermBeans.get(postion).setManagementFee(entry.getData().getItem().getManagementFee());
                    minorTermBeans.get(postion).setTaxAmount(entry.getData().getItem().getTaxAmount());
                    minorTermBeans.get(postion).setSecondPartyDeductedAmount(entry.getData().getItem().getSecondPartyDeductedAmount());
                    minorTermBeans.get(postion).setSecondPartyAuditFee(entry.getData().getItem().getSecondPartyAuditFee());
                    minorTermBeans.get(postion).setAmountCashed(entry.getData().getItem().getAmountCashed());
                    minorTermBeans.get(postion).setStipulatedFee(entry.getData().getItem().getStipulatedFee());
                    minorTermBeans.get(postion).setSecondPartySafeProductionCost(entry.getData().getItem().getSecondPartySafeProductionCost());
                    minorTermBeans.get(postion).setSecondPartyMaterialCost(entry.getData().getItem().getSecondPartyMaterialCost());
                    minorTermBeans.get(postion).setFirstPartyMaterialCost(entry.getData().getItem().getFirstPartyMaterialCost());
                    minorTermBeans.get(postion).setSecondPartyConstructionCost(entry.getData().getItem().getSecondPartyConstructionCost());
                    minorTermBeans.get(postion).setAmount(entry.getData().getItem().getAmount());
                    minorTermBeans.get(postion).setCheckAndAcceptDate(entry.getData().getItem().getCheckAndAcceptDate());
                    minorTermBeans.get(postion).setContractOrderId(entry.getData().getItem().getContractOrderId());
                    minorTermBeans.get(postion).setId(entry.getData().getItem().getId());
                    minorTermBeans.get(postion).setName(entry.getData().getItem().getName());
                    minorTermBeans.get(postion).setCooperator(entry.getData().getItem().getCooperator());
                    minorTermBeans.get(postion).setStartDate(entry.getData().getItem().getStartDate());
                    minorTermBeans.get(postion).setStatus(entry.getData().getItem().getStatus());
                }
                minorTermAdapter.notifyDataSetChanged();
            }

        }


    }


    @OnClick({R.id.back_rl, R.id.title_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_rl:
                finish();
                break;
            case R.id.title_tv:
                break;
        }
    }

    @Override
    public void setContent(String content) {
        DialogUtil.showAlertDialog(mActivity, content + "");
    }

    @Override
    public void getData(BaseEntry<MinorTermBean> baseEntry) {
        minorTermBeans.addAll(baseEntry.getData().getRows());
        if (baseEntry.getData().getRows().size() == 0 || baseEntry.getData().getRows().size() < 10) {
            mRefreshLayout.finishLoadMoreWithNoMoreData();  //全部加载完成,没有数据了调用此方法
        }
        initRcDta();
    }


}
