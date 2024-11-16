package com.example.Giang.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Giang.model.DoctorInfor;
import com.example.finalproject_hosme.R;

import java.util.List;

public class DoctorInforAdapter extends BaseAdapter {
    Activity context;
    int doctor_mainlist;
    List<DoctorInfor> doctors;

    public DoctorInforAdapter(Activity context, int doctor_mainlist, List<DoctorInfor> doctors) {
        this.context = context;
        this.doctor_mainlist = doctor_mainlist;
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
            convertView = inflater.inflate(doctor_mainlist, null);
            holder.txtName = convertView.findViewById(R.id.txtDoctorNameList);
            holder.txtSpecialist = convertView.findViewById(R.id.txtSpecialistList);
            holder.imvThumb = convertView.findViewById(R.id.imvDoctorList);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        DoctorInfor doctor = doctors.get(position);
        holder.txtSpecialist.setText(doctor.getSpecialistName());
        holder.txtName.setText(doctor.getDoctorName());

        byte[] imageBytes = doctor.getDoctorImage();
        if (holder.imvThumb != null) {
            if (imageBytes != null && imageBytes.length > 0) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                holder.imvThumb.setImageBitmap(bitmap);
            } else {
                // Xử lý trường hợp không có hình ảnh
                holder.imvThumb.setImageResource(R.drawable.icon_hosme); // Đặt hình ảnh mặc định
            }
        }

        return convertView;
    }

    public static class ViewHolder{
        ImageView imvThumb;
        TextView txtName, txtSpecialist;
    }
}
