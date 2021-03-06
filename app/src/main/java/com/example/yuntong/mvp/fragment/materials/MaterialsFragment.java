package com.example.yuntong.mvp.fragment.materials;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.yuntong.R;
import com.example.yuntong.adapter.MaterialAdapter;
import com.example.yuntong.app.ApiAddress;
import com.example.yuntong.base.BaseEntry;
import com.example.yuntong.base.BaseFragment;
import com.example.yuntong.bean.MaterialsAddBean;
import com.example.yuntong.bean.MaterialsBean;
import com.example.yuntong.mvp.activity.materals.add_materals.AddMaterialsActivity;
import com.example.yuntong.mvp.activity.materals.materals_detail.MateralsDetailActivity;
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
import butterknife.Unbinder;

/**
 * @author 杨晓峰
 * @create 2019/4/10
 * @Describe 材料管理页面
 */
public class MaterialsFragment extends BaseFragment implements MaterialsContract.View {


    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.add_material_rl)
    RelativeLayout addMaterialRl;
    @BindView(R.id.recy)
    RecyclerView recy;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    Unbinder unbinder;

    private MaterialAdapter materialAdapter;
    private int page = 1;
    private List<MaterialsBean.RowsBean> materialsBeans = new ArrayList<>();
    private View emptyView;
    private MaterialsPresenter presenter;
    private BaseEntry<MaterialsAddBean> listBean2;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_materials;
    }

    @Override
    protected void initView() {
        emptyView = View.inflate(getContext(), R.layout.null_item, null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recy.setLayoutManager(layoutManager);
        presenter = new MaterialsPresenter(getContext(), this);
    }

    @Override
    protected void initData() {
        presenter.materialsList(page, 10);
        Init_Refresh();
    }

    private void initRcDta() {

        if (materialAdapter == null) {
            //创建适配器
            materialAdapter = new MaterialAdapter(getContext(), materialsBeans);
            //给RecyclerView设置适配器
            recy.setAdapter(materialAdapter);
            //5，给recyclerView设置空布局
            if (materialsBeans == null || materialsBeans.size() == 0) {
                materialAdapter.setEmptyView(emptyView);
            }
            materialAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    MaterialsBean.RowsBean rowsBean = materialsBeans.get(position);
                    ApiAddress.materialId=rowsBean.getId()+"";
                    startActivity(new Intent(getContext(), MateralsDetailActivity.class));
                }
            });
        } else {
            materialAdapter.notifyDataSetChanged();
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
                materialsBeans.clear();
                page = 1;
                presenter.materialsList(page, 10);
                materialAdapter.notifyDataSetChanged();
                refreshlayout.finishRefresh(1000);
            }
        });
        //加载更多
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.materialsList(page, 10);
                materialAdapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore(1000);//加载完成
            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //新增材料返回数据
        if (resultCode == 10) {
            if (requestCode == 200) {
                MaterialsBean.RowsBean rowsBean = new MaterialsBean.RowsBean();
                listBean2 = (BaseEntry<MaterialsAddBean>) data.getSerializableExtra("bean");

                rowsBean.setName(listBean2.getData().getItem().getName());
                rowsBean.setType(listBean2.getData().getItem().getType());
                rowsBean.setId(listBean2.getData().getItem().getId());
                rowsBean.setRemainCount(listBean2.getData().getItem().getRemainCount());
                rowsBean.setUnit(listBean2.getData().getItem().getUnit());

                materialsBeans.add(0, rowsBean);
            }
            materialAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.title_tv, R.id.add_material_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_tv:
                break;
            case R.id.add_material_rl:
                startActivityForResult(new Intent(getContext(), AddMaterialsActivity.class), 200);
                break;
        }
    }

    @Override
    public void setContent(String content) {
        DialogUtil.showAlertDialog(getActivity(), content + "");
    }

    @Override
    public void getData(BaseEntry<MaterialsBean> entry) {
        materialsBeans.addAll(entry.getData().getRows());
        if (entry.getData().getRows().size() == 0 || entry.getData().getRows().size() < 10) {
            mRefreshLayout.finishLoadMoreWithNoMoreData();  //全部加载完成,没有数据了调用此方法
        }
        initRcDta();
    }
}
