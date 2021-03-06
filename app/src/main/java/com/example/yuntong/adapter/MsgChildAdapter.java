package com.example.yuntong.adapter;

import android.content.Context;
import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.yuntong.R;
import com.example.yuntong.bean.ContractListEntity;

import java.util.List;

public class MsgChildAdapter extends BaseQuickAdapter<ContractListEntity.RowsBean, BaseViewHolder> {

    public MsgChildAdapter(Context context, List<ContractListEntity.RowsBean> data) {
        super(R.layout.home_list_item, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, ContractListEntity.RowsBean item) {
        int status=item.getStatus();
        viewHolder.setText(R.id.contract_tv, item.getContractName())
                .setText(R.id.company_tv, item.getFirstPartyName())
//                .setText(R.id.money_tv, item.getContractAmount()+"元")
                .setText(R.id.day_tv, item.getCreateTime())
                .setText(R.id.win_company_tv, item.getSecondPartyName());
        viewHolder.addOnClickListener(R.id.show_ll);
        viewHolder.addOnClickListener(R.id.conten_ll);
        switch (status) {
            case 1:
                viewHolder.setVisible(R.id.show_ll,true);
                viewHolder.setImageResource(R.id.show_iv,R.mipmap.invest_point_two);
                viewHolder.setText(R.id.state_tv, "进行中");
                viewHolder.setTextColor(R.id.state_tv,Color.rgb(78,221,179));
                break;
            case 2:
                viewHolder.setVisible(R.id.show_ll,false);
                viewHolder.setImageResource(R.id.show_iv,R.mipmap.invest_list_point_ongoing);
                viewHolder.setText(R.id.state_tv, "已结束");
                viewHolder.setTextColor(R.id.state_tv,Color.rgb(255,76,66));
                break;
            default:
                viewHolder.setText(R.id.state_tv, "未知");
                break;
        }
//        Glide.with(mContext).load(item.getUserAvatar()).crossFade().into((ImageView) viewHolder.getView(R.id.iv));
    }


}
