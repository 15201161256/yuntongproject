package com.example.yuntong.mvp.fragment.instrument;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.yuntong.R;
import com.example.yuntong.adapter.InstrumentAdapter;
import com.example.yuntong.app.ApiAddress;
import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BaseFragment;
import com.example.yuntong.bean.EditInstrumentBean;
import com.example.yuntong.bean.InstrumentBean;
import com.example.yuntong.mvp.activity.instrument.add_instrument.AddInstrumentActivity;
import com.example.yuntong.mvp.activity.instrument.edit_instrument.InstrumentEditActivity;
import com.example.yuntong.mvp.activity.instrument.instrument_detail.InstrumentDetailActivity;
import com.example.yuntong.utils.DialogUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author 杨晓峰
 * @create 2019/4/10
 * @Describe 工具管理页面
 */
public class InstrumentFragment extends BaseFragment implements InstrumentContract.View{

    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.add_instrument_rl)
    RelativeLayout addInstrumentRl;
    @BindView(R.id.recy)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    Unbinder unbinder;

    private InstrumentAdapter instrumentAdapter;
    private List<InstrumentBean.RowsBean> instrumentBeans=new ArrayList<>();
    private InstrumentBean.RowsBean instrumentBean;
    private int page=1;
    private View emptyView;
    private InstrumentPresenter presenter;
    private BaseEntry<EditInstrumentBean> listBean2;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_instrument;
    }

    @Override
    protected void initView() {
        emptyView = View.inflate(getContext(), R.layout.null_item, null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        presenter=new InstrumentPresenter(getContext(),this);
    }

    @Override
    protected void initData() {
        presenter.instrumentList(page, 10);
        Init_Refresh();
    }

    private void Init_Refresh() {
        //刷新
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                instrumentBeans.clear();
                page = 1;
                presenter.instrumentList(page, 10);
                instrumentAdapter.notifyDataSetChanged();
                refreshlayout.finishRefresh(1000);
            }
        });
        //加载更多
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.instrumentList(page, 10);
                instrumentAdapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore(1000);//加载完成
            }
        });

    }

    private void initRcDta() {
        if (instrumentAdapter == null) {
            //创建适配器
            instrumentAdapter = new InstrumentAdapter(getContext(), instrumentBeans);
            //给RecyclerView设置适配器
            recyclerView.setAdapter(instrumentAdapter);
            //5，给recyclerView设置空布局
            if (instrumentBeans == null || instrumentBeans.size() == 0) {
                instrumentAdapter.setEmptyView(emptyView);
            }
            recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
                @Override
                public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                }

                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    super.onItemChildClick(adapter, view, position);
                    int itemViewId = view.getId();
                    instrumentBean = instrumentBeans.get(position);
                    ApiAddress.instrumentId = instrumentBean.getId();
                    switch (itemViewId) {
                        case R.id.conten_ll:
                            startActivity(new Intent(getContext(), InstrumentDetailActivity.class));
                            break;
                        case R.id.show_ll:
                            Intent intent = new Intent(getContext(), InstrumentEditActivity.class);
                            intent.putExtra("bean", instrumentBean);
                            intent.putExtra("position", position);
                            startActivityForResult(intent, 200);
                            break;
                    }
                }
            });
        } else {
            instrumentAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //新增工具
        if (resultCode == 10) {
            if (requestCode == 200) {
                InstrumentBean.RowsBean listBean3 = new InstrumentBean.RowsBean();
                listBean2 = (BaseEntry<EditInstrumentBean>) data.getSerializableExtra("bean");
//                EditInstrumentBean.ItemBean.BorrowerBean borrowerBean=listBean2.getData().getItem().getBorrower();
//                InstrumentBean.RowsBean.BorrowerBean borrowerBean1 =new InstrumentBean.RowsBean.BorrowerBean(borrowerBean.getAvatar(),
//                        borrowerBean.getId(),borrowerBean.getNickname(),borrowerBean.getSex(),borrowerBean.getState(),
//                        borrowerBean.getStructureType(),borrowerBean.getUsername());

                listBean3.setName(listBean2.getData().getItem().getName());
                listBean3.setType(listBean2.getData().getItem().getType());
                listBean3.setPurchaseTime(listBean2.getData().getItem().getPurchaseTime());
                listBean3.setCreateTime(listBean2.getData().getItem().getCreateTime());
                listBean3.setStartTime(listBean2.getData().getItem().getStartTime() + "");
                listBean3.setEndTime(listBean2.getData().getItem().getEndTime());
                listBean3.setId(listBean2.getData().getItem().getId());
                listBean3.setBorrower(listBean2.getData().getItem().getBorrower());
                listBean3.setRemark(listBean2.getData().getItem().getRemark());
//                listBean3.setBorrower(borrowerBean1);
                instrumentBeans.add(0, listBean3);
            }
            instrumentAdapter.notifyDataSetChanged();
        }

        //编辑修改工具返回数据
        if (resultCode == 100) {
            if (requestCode == 200) {
                listBean2 = (BaseEntry<EditInstrumentBean>) data.getSerializableExtra("bean");
                int postion = data.getIntExtra("position", -1);
                if (postion != -1) {
                    instrumentBeans.get(postion).setName(listBean2.getData().getItem().getName());
                    instrumentBeans.get(postion).setType(listBean2.getData().getItem().getType());
                    instrumentBeans.get(postion).setPurchaseTime(listBean2.getData().getItem().getPurchaseTime());
                    instrumentBeans.get(postion).setBorrower(listBean2.getData().getItem().getBorrower());
                    instrumentBeans.get(postion).setCreateTime(listBean2.getData().getItem().getCreateTime());
                    instrumentBeans.get(postion).setStartTime(listBean2.getData().getItem().getStartTime());
                    instrumentBeans.get(postion).setEndTime(listBean2.getData().getItem().getEndTime());
                    instrumentBeans.get(postion).setId(listBean2.getData().getItem().getId());
                    instrumentBeans.get(postion).setRemark(listBean2.getData().getItem().getRemark());
                }
                instrumentAdapter.notifyDataSetChanged();
            }

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.title_tv, R.id.add_instrument_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_tv:
                break;
            case R.id.add_instrument_rl:
                startActivityForResult(new Intent(getContext(), AddInstrumentActivity.class),200);
                break;
        }
    }

    @Override
    public void setContent(String content) {
        DialogUtil.showAlertDialog(getActivity(), content + "");
    }

    @Override
    public void getData(BaseEntry<InstrumentBean> entry) {
        instrumentBeans.addAll(entry.getData().getRows());
        if (entry.getData().getRows().size() == 0 || entry.getData().getRows().size() < 10) {
            mRefreshLayout.finishLoadMoreWithNoMoreData();  //全部加载完成,没有数据了调用此方法
        }
        initRcDta();
    }
}
