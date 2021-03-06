package com.example.yuntong.mvp.activity.minor_term.minor_term_list;

import android.app.Dialog;
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
import com.example.yuntong.bean.EditMinorTermBean;
import com.example.yuntong.bean.MinorTermAddBean;
import com.example.yuntong.bean.MinorTermBean;
import com.example.yuntong.bean.OrderDetailBean;
import com.example.yuntong.mvp.activity.minor_term.minor_item_lock.LockMinorTermActivity;
import com.example.yuntong.mvp.activity.minor_term.minor_term_add.MinortermAddActivity;
import com.example.yuntong.mvp.activity.minor_term.minor_term_detail.MinorTermDetailActivity;
import com.example.yuntong.utils.DialogUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 杨晓峰
 * @create 2019/4/19
 * @Describe 小项列表
 */
public class MinorTermListActivity extends BaseActivity implements MinorTermListContract.View {

    @BindView(R.id.recy)
    RecyclerView recy;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private MinorTermAdapter minorTermAdapter;
    private List<MinorTermBean.RowsBean> minorTermBeans = new ArrayList<>();
    private View emptyView;
    private int page = 1;
    private MinorTermListPresenter presenter;

    private MinorTermBean.RowsBean rowsBean;
    private BaseEntry<EditMinorTermBean> entry;

    private BaseEntry<OrderDetailBean> rowsBean2;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        rowsBean2 = (BaseEntry<OrderDetailBean>) getIntent().getSerializableExtra("bean");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_minor_term_list;
    }


    @Override
    protected void initView() {
        emptyView = View.inflate(this, R.layout.null_item, null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recy.setLayoutManager(layoutManager);
        presenter = new MinorTermListPresenter(this, this);

    }

    @Override
    protected void initData() {
        presenter.getMinorTermList(page, 10, ApiAddress.orderId);
        Init_Refresh();
    }


    private void Init_Refresh() {
        //刷新
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                minorTermBeans.clear();
                page = 1;
                presenter.getMinorTermList(page, 10, ApiAddress.orderId);
                minorTermAdapter.notifyDataSetChanged();
                refreshlayout.finishRefresh(1000);
            }
        });

        //加载更多
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.getMinorTermList(page, 10, ApiAddress.orderId);
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
                    rowsBean = minorTermBeans.get(position);
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
        //编辑小项修改
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
        //添加小项
        if (resultCode == 20) {
            if (requestCode == 100) {
                BaseEntry<MinorTermAddBean> entry = (BaseEntry<MinorTermAddBean>) data.getSerializableExtra("bean");
                rowsBean.setCheckAndAcceptDate(entry.getData().getItem().getCheckAndAcceptDate());
                rowsBean.setAmount(entry.getData().getItem().getAmount());
                rowsBean.setSecondPartyConstructionCost(entry.getData().getItem().getSecondPartyConstructionCost());
                rowsBean.setFirstPartyMaterialCost(entry.getData().getItem().getFirstPartyMaterialCost());
                rowsBean.setSecondPartyMaterialCost(entry.getData().getItem().getSecondPartyMaterialCost());
                rowsBean.setSecondPartySafeProductionCost(entry.getData().getItem().getSecondPartySafeProductionCost());
                rowsBean.setStipulatedFee(entry.getData().getItem().getStipulatedFee());
                rowsBean.setAmountCashed(entry.getData().getItem().getAmountCashed());
                rowsBean.setSecondPartyAuditFee(entry.getData().getItem().getSecondPartyAuditFee());
                rowsBean.setSecondPartyDeductedAmount(entry.getData().getItem().getSecondPartyDeductedAmount());
                rowsBean.setTaxAmount(entry.getData().getItem().getTaxAmount());
                rowsBean.setManagementFee(entry.getData().getItem().getManagementFee());
                rowsBean.setStructureType(entry.getData().getItem().getStructureType());
                rowsBean.setEndDate(entry.getData().getItem().getEndDate());
                rowsBean.setName(entry.getData().getItem().getName());
                rowsBean.setCooperator(entry.getData().getItem().getCooperator());
                rowsBean.setStartDate(entry.getData().getItem().getStartDate());
                rowsBean.setStatus(entry.getData().getItem().getStatus());
                rowsBean.setId(entry.getData().getItem().getId());
                minorTermBeans.add(0, rowsBean);
            }
            minorTermAdapter.notifyDataSetChanged();
        }
    }

    @OnClick({R.id.back_rl, R.id.add_contract_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_rl:
                finish();
                break;
            case R.id.add_contract_rl:
                String str = rowsBean2.getData().getItem().getTaxRate();
                try {
                    if (str != null && !str.isEmpty() && !str.equals("0.0")) {
                        startActivityForResult(new Intent(mActivity, MinortermAddActivity.class), 100);
                    } else {
                        dialog = DialogUtil.showDialog2(mActivity, "请先更新财务信息", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                } catch (NullPointerException e) {
                }


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
