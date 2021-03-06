package com.example.yuntong.mvp.activity.materals.put_materals_list;

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
import com.example.yuntong.adapter.MateralPutAdapter;
import com.example.yuntong.app.ApiAddress;
import com.example.yuntong.base.BaseActivity;
import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.bean.MaterialPutBean;
import com.example.yuntong.bean.MaterialsTypeBean;
import com.example.yuntong.mvp.activity.materals.add_put_materals.MateralsAddPutActivity;
import com.example.yuntong.mvp.activity.materals.put_materals_list.detail.PutDetailActivity;
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
 * @create 2019/6/6
 * @Describe 材料入库记录列表
 */
public class MateralsPutListActivity extends BaseActivity implements MaterialsPutListContract.View {


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


    private MateralPutAdapter adapter;
    private List<MaterialsTypeBean.RowsBean> putBeans = new ArrayList<>();
    private View emptyView;
    private int page = 1;
    private MaterialsPutListPresenter presenter;
    private BaseEntry<MaterialPutBean> listBean2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_materals_put_list;
    }

    @Override
    protected void initView() {
        emptyView = View.inflate(this, R.layout.null_item, null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyOrder.setLayoutManager(layoutManager);
        presenter = new MaterialsPutListPresenter(mActivity, this);
    }

    @Override
    protected void initData() {
        presenter.getList(page, 10, ApiAddress.materialId, "2");
        Init_Refresh();
    }

    private void Init_Refresh() {
        //刷新
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                putBeans.clear();
                page = 1;
                presenter.getList(page, 10, ApiAddress.materialId, "2");
                adapter.notifyDataSetChanged();
                refreshlayout.finishRefresh(1000);
            }
        });

        //加载更多
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.getList(page, 10, ApiAddress.materialId, "2");
                adapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore(1000);//加载完成
            }
        });
    }

    private void initRcDta() {
        if (adapter == null) {
            adapter = new MateralPutAdapter(mActivity, putBeans);
            //5，给recyclerView设置空布局
            if (putBeans == null || putBeans.size() == 0) {
                adapter.setEmptyView(emptyView);
            }
            recyOrder.setAdapter(adapter);
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    MaterialsTypeBean.RowsBean rowsBean=putBeans.get(position);
                    ApiAddress.material_Put_Id=rowsBean.getId()+"";
                    startActivity(new Intent(mActivity, PutDetailActivity.class));
                }
            });
        } else {
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //新增材料入库返回数据
        if (resultCode == 10) {
            if (requestCode == 200) {
                MaterialsTypeBean.RowsBean rowsBean = new MaterialsTypeBean.RowsBean();
                listBean2 = (BaseEntry<MaterialPutBean>) data.getSerializableExtra("bean");

                rowsBean.setCount(listBean2.getData().getItem().getCount());
                rowsBean.setType(listBean2.getData().getItem().getType());
                rowsBean.setId(listBean2.getData().getItem().getId());
                rowsBean.setApplicantName(listBean2.getData().getItem().getApplicantName());
                rowsBean.setRemark(listBean2.getData().getItem().getRemark());
                rowsBean.setAuditor(listBean2.getData().getItem().getAuditor());
                rowsBean.setMaterialName(listBean2.getData().getItem().getMaterialName());
                rowsBean.setCreateTime(listBean2.getData().getItem().getCreateTime());

                putBeans.add(0, rowsBean);
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
                startActivityForResult(new Intent(mActivity, MateralsAddPutActivity.class), 200);
                break;
        }
    }

    @Override
    public void setContent(String content) {
        DialogUtil.showAlertDialog(mActivity, content + "");
    }

    @Override
    public void getData(BaseEntry<MaterialsTypeBean> baseEntry) {
        putBeans.addAll(baseEntry.getData().getRows());
        if (baseEntry.getData().getRows().size() == 0 || baseEntry.getData().getRows().size() < 10) {
            mRefreshLayout.finishLoadMoreWithNoMoreData();  //全部加载完成,没有数据了调用此方法
        }
        initRcDta();
    }
}
