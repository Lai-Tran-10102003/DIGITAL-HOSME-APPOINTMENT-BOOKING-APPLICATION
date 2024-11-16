package com.example.Thảo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.Thảo.models.Bacsi;
import com.example.finalproject_hosme.R;

import java.util.ArrayList;
import java.util.List;

public class BacSiAdapter extends BaseAdapter implements Filterable {

    Context context;
    int item_layout;
    List<Bacsi> bacsi;
    List<Bacsi> filteredBacsi;

    public BacSiAdapter(Context context, int item_layout, List<Bacsi> bacsi) {
        this.context = context;
        this.item_layout = item_layout;
        this.bacsi = bacsi;
        this.filteredBacsi = new ArrayList<>(bacsi);
    }

    @Override
    public int getCount() {
        return filteredBacsi.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredBacsi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(item_layout, parent, false);

            holder.txtBacSi = convertView.findViewById(R.id.txtBacSi);
            holder.txtLichKham = convertView.findViewById(R.id.txtLichKham);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Bacsi b = filteredBacsi.get(position);
        holder.txtBacSi.setText(b.getBacsiName());
        holder.txtLichKham.setText(b.getLichkham());

        return convertView;
    }

    public void clear() {
        bacsi.clear();
        filteredBacsi.clear();
    }

    public void add(Bacsi k) {
        bacsi.add(k);
        filteredBacsi.add(k);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<Bacsi> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(bacsi);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (Bacsi item : bacsi) {
                        if (item.getBacsiName().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }

                results.values = filteredList;
                results.count = filteredList.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredBacsi.clear();
                filteredBacsi.addAll((List<Bacsi>) results.values);
                notifyDataSetChanged();
            }
        };
    }

    private static class ViewHolder {
        TextView txtBacSi, txtLichKham;
    }
}