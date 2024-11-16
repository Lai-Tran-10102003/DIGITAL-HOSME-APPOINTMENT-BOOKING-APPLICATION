package com.example.Giang.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Giang.model.Function;
import com.example.finalproject_hosme.R;

import java.util.List;

public class FunctionAdapter extends BaseAdapter {
    Activity context;
    int item_layout;
    List<Function> functionList;

    public FunctionAdapter(Activity context, int item_layout, List<Function> functionList) {
        this.context = context;
        this.item_layout = item_layout;
        this.functionList = functionList;
    }

    @Override
    public int getCount() {
        return functionList.size();
    }

    @Override
    public Object getItem(int position) {
        return functionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(item_layout,null);
            holder.txtName = convertView.findViewById(R.id.txtNameFunction);
            holder.imvThumb = convertView.findViewById(R.id.imvMainFunction);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Function f = functionList.get(position);
        holder.imvThumb.setImageResource(f.getFunctionThumb());
        holder.txtName.setText(f.getFunctionName());

        return convertView;
    }

    public static class ViewHolder{
        ImageView imvThumb;
        TextView txtName;
    }
}
