package com.example.Trân.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.Trân.model.Question;
import com.example.finalproject_hosme.R;

import java.util.List;

public class QuestionAdapter extends BaseAdapter {

    Activity context;
    int item_list;
    List<Question> questions;

    public QuestionAdapter(Activity context, int item_list, List<Question> questions) {
        this.context = context;
        this.item_list = item_list;
        this.questions = questions;
    }

    @Override
    public int getCount() {
        return questions.size();
    }

    @Override
    public Object getItem(int position) {
        return questions.get(position);
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

            holder.txtQue = convertView.findViewById(R.id.txtQue);

            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }

//         Liên kết dữ liệu
        Question q = questions.get(position);

        holder.txtQue.setText(q.getQueTitle());

        return convertView;
    }

    public static class ViewHolder{
        TextView txtQue;
    }
}
