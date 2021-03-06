package com.example.yuntong.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.yuntong.R;
import com.example.yuntong.bean.MaterialsTypeBean;
import com.example.yuntong.bean.MaterialsTypeBean.RowsBean;

import java.util.List;

public class MateralOutAdapter extends BaseQuickAdapter<MaterialsTypeBean.RowsBean, BaseViewHolder> {
    private Context mContext;

    public MateralOutAdapter(Context context, List<MaterialsTypeBean.RowsBean> data) {
        super(R.layout.materal_out_item, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, MaterialsTypeBean.RowsBean item) {
        viewHolder.setText(R.id.out_num_tv, item.getCount())
                .setText(R.id.out_time_tv, item.getCreateTime())
//                .setText(R.id.materialName_tv, item.getMaterialName())
                .setText(R.id.out_user_tv, item.getAuditor());

//        switch (status) {
//            case 1:
//                viewHolder.setVisible(R.id.show_ll,true);
//                viewHolder.setImageResource(R.id.show_iv,R.mipmap.invest_point_two);
//                viewHolder.setText(R.id.state_tv, "进行中");
//                viewHolder.setTextColor(R.id.state_tv, Color.rgb(78,221,179));
//                break;
//            case 2:
//                viewHolder.setVisible(R.id.show_ll,false);
//                viewHolder.setImageResource(R.id.show_iv,R.mipmap.invest_list_point_ongoing);
//                viewHolder.setText(R.id.state_tv, "已结束");
//                viewHolder.setTextColor(R.id.state_tv,Color.rgb(255,76,66));
//                break;
//            default:
//                viewHolder.setText(R.id.state_tv, "未知");
//                break;
//        }

//        Glide.with(mContext).load(item.getUserAvatar()).crossFade().into((ImageView) viewHolder.getView(R.id.iv));
    }


}
