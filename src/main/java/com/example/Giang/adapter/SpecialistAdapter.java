package com.example.Giang.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Giang.model.Specialist;
import com.example.finalproject_hosme.R;

import java.util.List;

public class SpecialistAdapter extends BaseAdapter {
    Activity context;
    int item_layout;
    List<Specialist> specialistList;

    public SpecialistAdapter(Activity context, int item_layout, List<Specialist> specialistList) {
        this.context = context;
        this.item_layout = item_layout;
        this.specialistList = specialistList;
    }

    @Override
    public int getCount() {
        return specialistList.size();
    }

    @Override
    public Object getItem(int position) {
        return specialistList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SpecialistAdapter.ViewHolder holder;
        if(convertView == null){
            holder = new SpecialistAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(item_layout,null);
            holder.txtName = convertView.findViewById(R.id.txtNameSpecialist);
            holder.imvThumb = convertView.findViewById(R.id.imvSpecialist);
            convertView.setTag(holder);
        } else {
            holder = (SpecialistAdapter.ViewHolder) convertView.getTag();
        }
        Specialist s = specialistList.get(position);
        holder.imvThumb.setImageResource(s.getSpecialistThumb());
        holder.txtName.setText(s.getSpecialistName());

        return convertView;
    }

    public static class ViewHolder{
        ImageView imvThumb;
        TextView txtName;
    }
}
