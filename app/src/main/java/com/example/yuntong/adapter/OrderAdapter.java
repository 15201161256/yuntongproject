package com.example.yuntong.adapter;

import android.content.Context;
import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.yuntong.R;
import com.example.yuntong.bean.OrderListbean;

import java.util.List;

public class OrderAdapter extends BaseQuickAdapter<OrderListbean.RowsBean, BaseViewHolder> {
    private Context mContext;

    public OrderAdapter(Context context, List<OrderListbean.RowsBean> data) {
        super(R.layout.order_item, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, OrderListbean.RowsBean item) {
        int status=item.getStatus();
        viewHolder.setText(R.id.orderNo_tv, item.getOrderNo())
                .setText(R.id.orderName_tv, item.getOrderName())
                .setText(R.id.bigItemNo_tv, item.getBigItemNo())
                .setText(R.id.orderAmount_tv, item.getOrderAmount() + "元");

        viewHolder.addOnClickListener(R.id.show_ll);
        viewHolder.addOnClickListener(R.id.conten_ll);
        switch (status) {
            case 1:
                viewHolder.setVisible(R.id.show_ll,true);
                viewHolder.setImageResource(R.id.show_iv,R.mipmap.invest_point_two);
                viewHolder.setText(R.id.state_tv, "进行中");
                viewHolder.setTextColor(R.id.state_tv, Color.rgb(78,221,179));
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
