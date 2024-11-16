package com.example.Chi.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.Chi.models.Invoice;
import com.example.finalproject_hosme.R;

import java.util.List;

public class InvoiceAdapter extends BaseAdapter {
    Activity context;
    int listbill_layout;
    List<Invoice> invoices;

    public InvoiceAdapter(Activity context, int listbill_layout, List<Invoice> invoices) {
        this.context = context;
        this.listbill_layout = listbill_layout;
        this.invoices = invoices;
    }

    @Override
    public int getCount() {
        return invoices.size();
    }

    @Override
    public Object getItem(int position) {
        return invoices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InvoiceAdapter.ViewHolder holder;
        if (convertView==null){
            holder = new InvoiceAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(listbill_layout, null);


            holder.txtDate = convertView.findViewById(R.id.txtDate);
            holder.txtPatientName = convertView.findViewById(R.id.txtPatientName);
            holder.txtDepartmentName = convertView.findViewById(R.id.txtDepartmentName);
            convertView.setTag(holder);
        }else{
            holder = (InvoiceAdapter.ViewHolder) convertView.getTag();
        }
        //Binding data
        // Check if TextViews are null before setting text
        Invoice invoice = invoices.get(position);
        holder.txtDate.setText(invoice.getCreateDateTime());
        holder.txtPatientName.setText(invoice.getPatientName());
        holder.txtDepartmentName.setText(invoice.getDepartmentName());
        return convertView;
    }
    public static class ViewHolder{
        TextView txtDate, txtPatientName, txtDepartmentName;
    }
}

