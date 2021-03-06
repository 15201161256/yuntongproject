package com.example.yuntong.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.yuntong.R;
import com.example.yuntong.bean.MaterialsBean;

import java.util.List;

public class MaterialAdapter extends BaseQuickAdapter<MaterialsBean.RowsBean, BaseViewHolder> {
    private Context mContext;

    public MaterialAdapter(Context context, List<MaterialsBean.RowsBean> data) {
        super(R.layout.materals_item, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, MaterialsBean.RowsBean item) {
        viewHolder.setText(R.id.materialsName_tv, item.getName())
                .setText(R.id.type_tv, item.getType())
                .setText(R.id.unit_tv, item.getUnit())
                .setText(R.id.remainCount_tv, item.getRemainCount()+item.getUnit());
    }


}
