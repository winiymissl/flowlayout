package com.example.beautifulspinner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * @Author winiymissl
 * @Date 2023-12-05 14:22
 * @Version 1.0
 */
public class SpinnerAdapter extends BaseAdapter {
    List list;

    public SpinnerAdapter(List list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spinner, parent, false);
        } else {
            view = convertView;
        }
        ImageView iv = view.findViewById(R.id.iv_spinner);
        TextView tv = view.findViewById(R.id.tv_spinner);
        Character temp = (Character) list.get(position);
        iv.setImageResource(temp.getImageOne());
        tv.setText(temp.getDescOne());
        return view;
    }
}
