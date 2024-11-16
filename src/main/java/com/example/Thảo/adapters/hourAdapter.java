package com.example.Thảo.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.Thảo.hosme.Chonthongtinkham4;
import com.example.finalproject_hosme.R;

import java.util.List;

public class hourAdapter extends BaseAdapter {

    Context context;
    int item_hour;
    List<String> list;
    public static final String PREFERENCE_NAME = "data";

    public hourAdapter(Context context, int item_hour, List<String> list) {
        this.context = context;
        this.item_hour = item_hour;
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_hour, parent, false);

            holder = new ViewHolder();
            holder.txtHour = convertView.findViewById(R.id.txtHour);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String timeSlot = list.get(position);
        holder.txtHour.setText(timeSlot);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Xử lý khi click vào mục giờ
                // Lưu giờ đã chọn vào SharedPreferences
                SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("time", timeSlot);
                editor.apply();


                // Chuyển đến màn hình xác nhận thông tin với thông tin giờ đã chọn

                // Tạo Intent để chuyển đến màn hình xác nhận thông tin
                Intent intent = new Intent(context, Chonthongtinkham4.class);
                // Truyền thông tin giờ đã chọn qua Intent
                intent.putExtra("GioKham", timeSlot);
                // Chạy Intent
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    public static  class ViewHolder {
        TextView txtHour;
    }
}
