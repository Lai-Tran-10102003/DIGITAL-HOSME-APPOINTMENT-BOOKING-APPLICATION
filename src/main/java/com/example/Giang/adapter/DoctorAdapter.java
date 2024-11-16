package com.example.Giang.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Giang.model.Doctor;
import com.example.finalproject_hosme.R;

import java.util.List;

public class DoctorAdapter extends BaseAdapter {
    Activity context;
    int item_layout;
    List<Doctor> doctors;

    public DoctorAdapter(Activity context, int item_layout, List<Doctor> doctors) {
        this.context = context;
        this.item_layout = item_layout;
        this.doctors = doctors;
    }

    @Override
    public int getCount() {
        return doctors.size();
    }

    @Override
    public Object getItem(int position) {
        return doctors.get(position);
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
            holder.txtName = convertView.findViewById(R.id.txtNameDoctor);
            holder.imvThumb = convertView.findViewById(R.id.imvDoctor);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Doctor s = doctors.get(position);
        holder.imvThumb.setImageResource(s.getDoctorThumb());
        holder.txtName.setText(s.getDoctorName());

        return convertView;
    }

    public static class ViewHolder{
        ImageView imvThumb;
        TextView txtName;
    }
}
