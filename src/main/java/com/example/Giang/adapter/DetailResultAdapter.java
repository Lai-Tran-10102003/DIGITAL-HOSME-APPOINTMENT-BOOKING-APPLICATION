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

import com.example.Giang.model.DetailResult;
import com.example.finalproject_hosme.R;

import java.util.List;

public class DetailResultAdapter extends BaseAdapter {
    Activity context;
    int item_layout;
    List<DetailResult> results;

    public DetailResultAdapter(Activity context, int item_layout, List<DetailResult> results) {
        this.context = context;
        this.item_layout = item_layout;
        this.results = results;
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public Object getItem(int position) {
        return results.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DetailResultAdapter.ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(item_layout, parent, false);

            holder = new DetailResultAdapter.ViewHolder();
            holder.txtID = convertView.findViewById(R.id.txtPatientCodeDetail);
            holder.txtName = convertView.findViewById(R.id.txtPatientNameDetail);
            holder.txtMainExam = convertView.findViewById(R.id.txtMainExamDetail);
            holder.txtTimeIn = convertView.findViewById(R.id.txtDateTimeInDetail);
            holder.txtTimeOut = convertView.findViewById(R.id.txtDateTimeOutDetail);
            holder.txtAddress = convertView.findViewById(R.id.txtAddressDetail);
            holder.txtDoctor = convertView.findViewById(R.id.txtDoctorDetail);
            holder.imvThumb = convertView.findViewById(R.id.imvResultDetail);
            convertView.setTag(holder);
        } else {
            holder = (DetailResultAdapter.ViewHolder) convertView.getTag();
        }

        DetailResult result = results.get(position);
        holder.txtID.setText(result.getPatientID());
        holder.txtName.setText(result.getPatientID());
        holder.txtMainExam.setText(result.getMainExam());
        holder.txtTimeIn.setText(result.getDateTimeIn());
        holder.txtTimeOut.setText(result.getDateTimeOut());
        holder.txtAddress.setText(result.getAddress());
        holder.txtDoctor.setText(result.getDoctor());

        byte[] imageBytes = result.getResultImage();
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

    private static class ViewHolder {
        ImageView imvThumb;
        TextView txtID, txtName, txtMainExam, txtTimeIn, txtTimeOut, txtAddress, txtDoctor;
    }
}
