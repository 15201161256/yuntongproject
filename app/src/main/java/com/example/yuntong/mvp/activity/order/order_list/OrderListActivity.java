package com.example.yuntong.mvp.activity.order.order_list;

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
import com.example.yuntong.adapter.OrderAdapter;
import com.example.yuntong.app.ApiAddress;
import com.example.yuntong.base.BaseActivity;
import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.bean.EditOrderBean;
import com.example.yuntong.bean.OrderAddBean;
import com.example.yuntong.bean.OrderListbean;
import com.example.yuntong.mvp.activity.contract.contract_detail.ContractDetailActivity;
import com.example.yuntong.mvp.activity.contract.lock_contract.LockContractActivity;
import com.example.yuntong.mvp.activity.order.add_order.AddOrderActivity;
import com.example.yuntong.mvp.activity.order.lock_order.LockOrderActivity;
import com.example.yuntong.mvp.activity.order.order_detail.OrderDetailActivity;
import com.example.yuntong.utils.DialogUtil;
import com.example.yuntong.utils.UiUtils;
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
 * @Describe 订单列表
 */
public class OrderListActivity extends BaseActivity implements OrderListContract.View {

    @BindView(R.id.back_rl)
    RelativeLayout backRl;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.add_contract_rl)
    RelativeLayout addContractRl;
    @BindView(R.id.recy_order)
    RecyclerView recyOrder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private OrderAdapter adapter;
    private List<OrderListbean.RowsBean> orderbeans = new ArrayList<>();
    private OrderListPresenter listPresenter;
    private View emptyView;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_list;
    }

    @Override
    protected void initView() {
        emptyView = View.inflate(this, R.layout.null_item, null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyOrder.setLayoutManager(layoutManager);
        listPresenter = new OrderListPresenter(this, this);
    }

    @Override
    protected void initData() {
        listPresenter.getOrderList(page, 10, ApiAddress.contractId);
        Init_Refresh();
    }

    private void Init_Refresh() {
        //刷新
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                orderbeans.clear();
                page = 1;
                listPresenter.getOrderList(page, 10, ApiAddress.contractId);
                adapter.notifyDataSetChanged();
                refreshlayout.finishRefresh(1000);
            }
        });

        //加载更多
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                listPresenter.getOrderList(page, 10, ApiAddress.contractId);
                adapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore(1000);//加载完成
            }
        });
    }

    private void initRcDta() {
        if (adapter == null) {
            adapter = new OrderAdapter(mActivity, orderbeans);
            //5，给recyclerView设置空布局
            if (orderbeans == null || orderbeans.size() == 0) {
                adapter.setEmptyView(emptyView);
            }
            recyOrder.setAdapter(adapter);
            recyOrder.addOnItemTouchListener(new OnItemChildClickListener() {
                @Override
                public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                }

                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    super.onItemChildClick(adapter, view, position);
                    int itemViewId = view.getId();
                    OrderListbean.RowsBean rowsBean = orderbeans.get(position);
                    ApiAddress.orderId = rowsBean.getId() + "";
                    switch (itemViewId) {
                        case R.id.conten_ll:
                            startActivity(new Intent(mActivity, OrderDetailActivity.class));
                            break;
                        case R.id.show_ll:
                            Intent intent = new Intent(mActivity, LockOrderActivity.class);
                            intent.putExtra("bean", rowsBean);
                            intent.putExtra("position", position);
                            startActivityForResult(intent, 200);
                            break;
                    }
                }
            });
        } else {
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 100) {
            if (requestCode == 200) {
                int postion = data.getIntExtra("position", -1);
                BaseEntry<EditOrderBean> baseEntry = (BaseEntry<EditOrderBean>) data.getSerializableExtra("bean");
                if (postion != -1) {
                    orderbeans.get(postion).setOrderNo(baseEntry.getData().getItem().getOrderNo());
                    orderbeans.get(postion).setOrderName(baseEntry.getData().getItem().getOrderName());
                    orderbeans.get(postion).setBigItemNo(baseEntry.getData().getItem().getBigItemNo());
                    orderbeans.get(postion).setOrderAmount(baseEntry.getData().getItem().getOrderAmount());
                    orderbeans.get(postion).setStatus(baseEntry.getData().getItem().getStatus());
                    orderbeans.get(postion).setMissNo(baseEntry.getData().getItem().getMissNo());
                    orderbeans.get(postion).setTaxRate(baseEntry.getData().getItem().getTaxRate());
                    orderbeans.get(postion).setId(baseEntry.getData().getItem().getId());
                    orderbeans.get(postion).setContractId(baseEntry.getData().getItem().getContractId());
                }
                adapter.notifyDataSetChanged();
            }
        }

        if (resultCode==20){
            if (requestCode == 200){
                OrderListbean.RowsBean orderAddBean=new OrderListbean.RowsBean();
                BaseEntry<OrderAddBean> data2= (BaseEntry<OrderAddBean>) data.getSerializableExtra("bean");
                orderAddBean.setOrderNo(data2.getData().getItem().getOrderNo());
                orderAddBean.setOrderName(data2.getData().getItem().getOrderName());
                orderAddBean.setBigItemNo(data2.getData().getItem().getBigItemNo());
                orderAddBean.setOrderAmount(data2.getData().getItem().getOrderAmount());
                orderAddBean.setStatus(data2.getData().getItem().getStatus());
                orderAddBean.setId(data2.getData().getItem().getId());
                orderAddBean.setStartTime(data2.getData().getItem().getStartTime());
                orderAddBean.setMissNo(data2.getData().getItem().getMissNo());
                orderAddBean.setTaxRate(data2.getData().getItem().getTaxRate());
                orderAddBean.setContractId(data2.getData().getItem().getContractId());
                orderbeans.add(0, orderAddBean);
            }
            adapter.notifyDataSetChanged();
        }


    }

    @OnClick({R.id.back_rl, R.id.add_contract_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_rl:
                finish();
                break;
            case R.id.add_contract_rl:
                startActivityForResult(new Intent(mActivity, AddOrderActivity.class),200);
                break;
        }
    }

    @Override
    public void setContent(String content) {
        DialogUtil.showAlertDialog(mActivity, content + "");
    }

    @Override
    public void getData(BaseEntry<OrderListbean> baseEntry) {
        orderbeans.addAll(baseEntry.getData().getRows());
        if (baseEntry.getData().getRows().size() == 0 || baseEntry.getData().getRows().size() < 10) {
            mRefreshLayout.finishLoadMoreWithNoMoreData();  //全部加载完成,没有数据了调用此方法
        }
        initRcDta();
    }
}
