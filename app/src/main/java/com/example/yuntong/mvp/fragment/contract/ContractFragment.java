package com.example.yuntong.mvp.fragment.contract;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.yuntong.R;
import com.example.yuntong.adapter.MsgChildAdapter;
import com.example.yuntong.app.ApiAddress;
import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BaseFragment;
import com.example.yuntong.bean.ContractListEntity;
import com.example.yuntong.bean.EditContractBean;
import com.example.yuntong.mvp.activity.contract.add_contract.AddContractActivity;
import com.example.yuntong.mvp.activity.contract.contract_detail.ContractDetailActivity;
import com.example.yuntong.mvp.activity.contract.lock_contract.LockContractActivity;
import com.example.yuntong.utils.DialogUtil;
import com.example.yuntong.utils.UiUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author 杨晓峰
 * @create 2019/3/28
 * @Describe 合同列表
 */
public class ContractFragment extends BaseFragment implements ContractContract.View {

    @BindView(R.id.recy)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    Unbinder unbinder;

    private MsgChildAdapter childAdapter;

    private List<ContractListEntity.RowsBean> contractListBeans = new ArrayList<>();
    private BaseEntry<EditContractBean> listBean2;
    private ContractListEntity.RowsBean listBean;
    private int page = 1;
    private ContractPresenter presenter;
    private View emptyView;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_contract;
    }

    @Override
    protected void initView() {
        emptyView = View.inflate(getContext(), R.layout.null_item, null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        presenter = new ContractPresenter(getContext(), this);
    }

    @Override
    protected void initData() {
        presenter.contractList(page, 10);
        Init_Refresh();
    }


    private void initRcDta() {
        if (childAdapter == null) {
            //创建适配器
            childAdapter = new MsgChildAdapter(getContext(), contractListBeans);
            //给RecyclerView设置适配器
            recyclerView.setAdapter(childAdapter);
            //5，给recyclerView设置空布局
            if (contractListBeans == null || contractListBeans.size() == 0) {
                childAdapter.setEmptyView(emptyView);
            }
            recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
                @Override
                public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                }

                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    super.onItemChildClick(adapter, view, position);
                    int itemViewId = view.getId();
                    listBean = contractListBeans.get(position);
                    ApiAddress.contractId = listBean.getId();
                    switch (itemViewId) {
                        case R.id.conten_ll:
                            startActivity(new Intent(getContext(), ContractDetailActivity.class));
                            break;
                        case R.id.show_ll:
                            Intent intent = new Intent(getContext(), LockContractActivity.class);
                            intent.putExtra("a", listBean);
                            intent.putExtra("position", position);
                            startActivityForResult(intent, 200);
                            break;
                    }
                }
            });
        } else {
            childAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //编辑修改合同返回数据
        if (resultCode == 2) {
            if (requestCode == 200) {
                listBean2 = (BaseEntry<EditContractBean>) data.getSerializableExtra("bean");
                int postion = data.getIntExtra("position", -1);
                if (postion != -1) {
                    contractListBeans.get(postion).setContractName(listBean2.getData().getItem().getContractName() + "");
                    contractListBeans.get(postion).setSecondPartyName(listBean2.getData().getItem().getSecondPartyName() + "");
                    contractListBeans.get(postion).setFirstPartyName(listBean2.getData().getItem().getFirstPartyName() + "");
                    contractListBeans.get(postion).setContractAmount(listBean2.getData().getItem().getContractAmount() + "");
                    contractListBeans.get(postion).setCreateTime(listBean2.getData().getItem().getCreateTime() + "");
                    contractListBeans.get(postion).setContractNo(listBean2.getData().getItem().getContractNo() + "");
                    contractListBeans.get(postion).setSignTime(listBean2.getData().getItem().getSignTime() + "");
                    contractListBeans.get(postion).setStartTime(listBean2.getData().getItem().getStartTime() + "");
                    contractListBeans.get(postion).setStatus(listBean2.getData().getItem().getStatus());
                }
                childAdapter.notifyDataSetChanged();
            }
        }

        //新增合同
        if (resultCode == 10) {
            if (requestCode == 200) {
                ContractListEntity.RowsBean listBean3 = new ContractListEntity.RowsBean();
                listBean2 = (BaseEntry<EditContractBean>) data.getSerializableExtra("bean");
                listBean3.setContractName(listBean2.getData().getItem().getContractName());
                listBean3.setSecondPartyName(listBean2.getData().getItem().getSecondPartyName());
                listBean3.setFirstPartyName(listBean2.getData().getItem().getFirstPartyName());
                listBean3.setContractAmount(listBean2.getData().getItem().getContractAmount() + "");
                listBean3.setCreateTime(listBean2.getData().getItem().getCreateTime());
                listBean3.setStatus(listBean2.getData().getItem().getStatus());
                listBean3.setRegion(listBean2.getData().getItem().getRegion());
                listBean3.setSignTime(listBean2.getData().getItem().getSignTime());
                listBean3.setStartTime(listBean2.getData().getItem().getStartTime());
                listBean3.setId(listBean2.getData().getItem().getId());
                listBean3.setContractNo(listBean2.getData().getItem().getContractNo());
                contractListBeans.add(0, listBean3);
            }
            childAdapter.notifyDataSetChanged();
        }
    }

    /*
     * 上拉下拉加载
     * */
    private void Init_Refresh() {
        //刷新
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                contractListBeans.clear();
                page = 1;
                presenter.contractList(page, 10);
                childAdapter.notifyDataSetChanged();
                refreshlayout.finishRefresh(1000);
            }
        });
        //加载更多
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.contractList(page, 10);
                childAdapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore(1000);//加载完成
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.add_contract_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_contract_rl:
                Intent intent = new Intent(getContext(), AddContractActivity.class);
                startActivityForResult(intent, 200);
                break;
        }
    }

    @Override
    public void setContent(String content) {
        DialogUtil.showAlertDialog(getActivity(), content + "");
    }

    @Override
    public void getLoginData(BaseEntry<ContractListEntity> contractListEntityBaseEntry) {
        contractListBeans.addAll(contractListEntityBaseEntry.getData().getRows());
        if (contractListEntityBaseEntry.getData().getRows().size() == 0 || contractListEntityBaseEntry.getData().getRows().size() < 10) {
            mRefreshLayout.finishLoadMoreWithNoMoreData();  //全部加载完成,没有数据了调用此方法
        }
        initRcDta();
    }
}
