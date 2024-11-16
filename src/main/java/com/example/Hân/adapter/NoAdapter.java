package com.example.Hân.adapter;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.Hân.model.Notifications;
import com.example.finalproject_hosme.R;

import java.util.List;

public class NoAdapter extends BaseAdapter {

    Activity context;
    int item_tb;
    List<Notifications> notifications;

    public NoAdapter(Activity context, int item_tb, List<Notifications> notifications) {
        this.context = context;
        this.item_tb = item_tb;
        this.notifications = notifications;
    }

    @Override
    public int getCount() {
        return notifications.size();
    }

    @Override
    public Object getItem(int position) {
        return notifications.get(position);
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
            convertView = inflater.inflate(R.layout.item_tb, null);

            holder.txtNo = convertView.findViewById(R.id.txtTitle);
            holder.txtDes = convertView.findViewById(R.id.txtDes);

            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }

//         Liên kết dữ liệu
        Notifications q = notifications.get(position);

        holder.txtNo.setText(q.getNoTitle());
        holder.txtDes.setText(q.getNoDes());

        return convertView;
    }

    public static class ViewHolder{
        TextView txtNo;
        TextView txtDes;
    }
}
