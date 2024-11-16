package com.example.Trân.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.Trân.model.HealthForm;
import com.example.finalproject_hosme.R;

import java.util.List;

public class HealthFormAdapter extends BaseAdapter {
    Activity context;
    int item_list;
    List<HealthForm> healthForms;

    public HealthFormAdapter(Activity context, int item_list, List<HealthForm> healthForms) {
        this.context = context;
        this.item_list = item_list;
        this.healthForms = healthForms;
    }

    @Override
    public int getCount() {
        return healthForms.size();
    }

    @Override
    public Object getItem(int position) {
        return healthForms.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
//            Liên kết các view trên giao diện item List
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(item_list, null);

            holder.txtName = convertView.findViewById(R.id.txtName);
            holder.txtKhoaKham = convertView.findViewById(R.id.txtKhoaKham);
            holder.txtNgayKham = convertView.findViewById(R.id.txtNgayKham);

            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }

//         Liên kết dữ liệu
        HealthForm h = healthForms.get(position);

        holder.txtName.setText(h.getPatientName());
        holder.txtKhoaKham.setText(h.getDepartmentName());
        holder.txtNgayKham.setText(h.getDate());

        return convertView;
    }

    public static class ViewHolder{
        TextView txtName, txtKhoaKham, txtNgayKham;
    }
}
