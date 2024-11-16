package com.example.Giang.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.Giang.model.ResultMedicalRecord;
import com.example.finalproject_hosme.R;

import java.util.List;

public class ResultMedicalAdapter extends BaseAdapter {
    Activity context;
    int item_layout;
    List<ResultMedicalRecord> results;

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

    public View getView(int position, View convertView, ViewGroup parent) {
        ResultMedicalAdapter.ViewHolder holder;
        if(convertView == null){
            holder = new ResultMedicalAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(item_layout, null);

            holder.txtPatientID = convertView.findViewById(R.id.txtPatientCode);
            holder.txtPatientName = convertView.findViewById(R.id.txtPatientName);
            holder.txtSpecialist = convertView.findViewById(R.id.txtSpecialist);
            holder.txtTimeIn = convertView.findViewById(R.id.txtDateTimeIn);
            holder.txtTimeOut = convertView.findViewById(R.id.txtDateTimeOut);
            holder.txtPredict = convertView.findViewById(R.id.txtPredict);
            holder.txtPrescription = convertView.findViewById(R.id.txtPrescription);
            holder.txtDoctor = convertView.findViewById(R.id.txtDoctor);

            convertView.setTag(holder);
        }else{
            holder = (ResultMedicalAdapter.ViewHolder) convertView.getTag();
        }

        ResultMedicalRecord result = results.get(position);
        holder.txtPatientID.setText(result.getPatientID());
        holder.txtPatientName.setText(result.getPatientName());
        holder.txtSpecialist.setText(result.getSpecialist());
        holder.txtTimeIn.setText(result.getDateTimeIn());
        holder.txtTimeOut.setText(result.getDateTimeOut());
        holder.txtPredict.setText(result.getPredict());
        holder.txtPrescription.setText(result.getPrescription());
        holder.txtDoctor.setText(result.getDoctor());
        return convertView;
    }
    public static class ViewHolder{
        TextView txtPatientID, txtPatientName, txtSpecialist, txtTimeIn, txtTimeOut, txtPredict, txtPrescription, txtDoctor;
    }
}
