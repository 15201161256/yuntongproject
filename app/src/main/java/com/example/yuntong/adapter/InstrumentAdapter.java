package com.example.yuntong.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.yuntong.R;
import com.example.yuntong.bean.InstrumentBean;

import java.util.List;

public class InstrumentAdapter extends BaseQuickAdapter<InstrumentBean.RowsBean, BaseViewHolder> {
    private Context mContext;

    public InstrumentAdapter(Context context, List<InstrumentBean.RowsBean> data) {
        super(R.layout.instrument_item, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, InstrumentBean.RowsBean item) {
        viewHolder.setText(R.id.ins_name, item.getName())
                .setText(R.id.ins_model, item.getType())
                .setText(R.id.ins_buytime, item.getPurchaseTime())
                .setText(R.id.ins_user, item.getBorrower());
        viewHolder.addOnClickListener(R.id.show_ll);
        viewHolder.addOnClickListener(R.id.conten_ll);
//        Glide.with(mContext).load(item.getUserAvatar()).crossFade().into((ImageView) viewHolder.getView(R.id.iv));
    }


}
