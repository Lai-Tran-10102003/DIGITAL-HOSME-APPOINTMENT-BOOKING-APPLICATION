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

import com.example.Giang.model.SpecialistInfor;
import com.example.finalproject_hosme.R;

import java.util.List;

public class SpecialistInforAdapter extends BaseAdapter {
    Activity context;
    int item_layout;
    List<SpecialistInfor> specialists;
    @Override
    public int getCount() {
        return specialists.size();
    }

    @Override
    public Object getItem(int position) {
        return specialists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SpecialistInforAdapter.ViewHolder holder;
        if(convertView == null){
            holder = new SpecialistInforAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(item_layout,null);
            holder.txtName = convertView.findViewById(R.id.txtSpecialistNameInfor);
            holder.txtDescription = convertView.findViewById(R.id.txtSpecialistDescriptionInfor);
            holder.imvThumb = convertView.findViewById(R.id.imvSpecialistInfor);
            convertView.setTag(holder);
        }else {
            holder = (SpecialistInforAdapter.ViewHolder) convertView.getTag();
        }
        SpecialistInfor specialist = specialists.get(position);
        holder.txtDescription.setText(specialist.getSpecialistDescription());
        holder.txtName.setText(specialist.getSpecialistName());

        byte[] imageBytes = specialist.getSpecialistImage();
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
        TextView txtName, txtDescription;
    }
}
