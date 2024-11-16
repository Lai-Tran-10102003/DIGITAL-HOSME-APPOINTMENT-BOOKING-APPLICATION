package com.example.Chi.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Chi.models.Bank;
import com.example.finalproject_hosme.R;

import java.util.List;

public class BankAdapter extends BaseAdapter {
    Activity context;
    int layout_bank;
    List<Bank> banks;

    public BankAdapter(Activity context, int layout_bank, List<Bank> banks) {
        this.context = context;
        this.layout_bank = layout_bank;
        this.banks = banks;
    }

    @Override
    public int getCount() {
        return banks.size();
    }

    @Override
    public Object getItem(int position) {
        return banks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout_bank, null);

            holder.imvBankThumb = convertView.findViewById(R.id.imvBankThumb);
            holder.txtBankName = convertView.findViewById(R.id.txtBankName);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //Binding data
        Bank bank = banks.get(position);
        holder.imvBankThumb.setImageResource(bank.getBankThumb());
        holder.txtBankName.setText(bank.getBankName());

        return convertView;
    }
    public static class ViewHolder{
        ImageView imvBankThumb;
        TextView txtBankName;
    }
}

