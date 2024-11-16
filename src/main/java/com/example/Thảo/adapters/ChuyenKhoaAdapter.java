package com.example.Thảo.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.Thảo.models.Chuyenkhoa;
import com.example.finalproject_hosme.R;

import java.util.ArrayList;
import java.util.List;

public class ChuyenKhoaAdapter extends BaseAdapter implements Filterable {
    Context context;
    int item_layout;
    List<Chuyenkhoa> chuyenkhoas;
    List<Chuyenkhoa> filteredList;

    public ChuyenKhoaAdapter(Context context, int item_layout, List<Chuyenkhoa> chuyenkhoas) {
        this.context = context;
        this.item_layout = item_layout;
        this.chuyenkhoas = chuyenkhoas;
        this.filteredList = new ArrayList<>(chuyenkhoas);
    }

    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(item_layout, parent, false);

            holder = new ViewHolder();
            holder.txtChuyenKhoa = convertView.findViewById(R.id.txtChuyenKhoa);
            holder.txtPrice = convertView.findViewById(R.id.txtPrice);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Chuyenkhoa chuyenkhoa = filteredList.get(position);
        holder.txtChuyenKhoa.setText(chuyenkhoa.getChuyenKhoaName());
        holder.txtPrice.setText(String.valueOf(Math.round(chuyenkhoa.getPriceService())));

        return convertView;
    }

    public static class ViewHolder {
        TextView txtChuyenKhoa, txtPrice;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<Chuyenkhoa> filteredResults = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    filteredResults.addAll(chuyenkhoas);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (Chuyenkhoa chuyenkhoa : chuyenkhoas) {
                        if (chuyenkhoa.getChuyenKhoaName().toLowerCase().contains(filterPattern)) {
                            filteredResults.add(chuyenkhoa);
                        }
                    }
                }

                results.values = filteredResults;
                results.count = filteredResults.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList.clear();
                filteredList.addAll((List<Chuyenkhoa>) results.values);
                notifyDataSetChanged();
            }
        };
    }
}