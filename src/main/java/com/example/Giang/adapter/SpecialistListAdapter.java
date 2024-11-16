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

import com.example.Giang.model.SpecialistListView;
import com.example.finalproject_hosme.R;

import java.util.List;

public class SpecialistListAdapter extends BaseAdapter {
    Activity context;
    int item_layout;
    List<SpecialistListView> specialist;

    public SpecialistListAdapter(Activity context, int item_layout, List<SpecialistListView> specialist) {
        this.context = context;
        this.item_layout = item_layout;
        this.specialist = specialist;
    }

    @Override
    public int getCount() {
        return specialist.size();
    }

    @Override
    public Object getItem(int position) {
        return specialist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(item_layout, parent, false);

            holder = new SpecialistListAdapter.ViewHolder();

            holder.txtSpecialist = convertView.findViewById(R.id.txtNameSpecialist);
            holder.imvThumb = convertView.findViewById(R.id.imvSpecialist);
            convertView.setTag(holder);
        } else {
            holder = (SpecialistListAdapter.ViewHolder) convertView.getTag();
        }

        SpecialistListView s = specialist.get(position);
        holder.txtSpecialist.setText(s.getSpecialistName());

        byte[] imageBytes = s.getSpecialistThumb();
        if (holder.imvThumb != null) {
            if (imageBytes != null && imageBytes.length > 0) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                holder.imvThumb.setImageBitmap(bitmap);
            } else {
                holder.imvThumb.setImageResource(R.drawable.icon_hosme);
            }
        }
        return convertView;
    }

    public static class ViewHolder{
        ImageView imvThumb;
        TextView txtSpecialist;
    }
}
