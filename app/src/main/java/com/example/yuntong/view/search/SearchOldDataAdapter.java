package com.example.yuntong.view.search;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.yuntong.R;

import java.util.ArrayList;

/**
 * @author 杨晓峰
 * @create 2018/11/13
 * @Describe 历史搜索adapter
 */
public class SearchOldDataAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> list = new ArrayList<String>();

    public SearchOldDataAdapter(Context context, ArrayList<String> strs) {
        this.context = context;
        list = strs;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = View.inflate(context, R.layout.search_olddata_item, null);
            holder.tv = (TextView) view.findViewById(R.id.text);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv.setText(list.get(i));
        return view;
    }

    public class ViewHolder {
        TextView tv;
    }


}
