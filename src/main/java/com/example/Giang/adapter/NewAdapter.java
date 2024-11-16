package com.example.Giang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Giang.model.New;
import com.example.finalproject_hosme.R;

import java.util.ArrayList;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.ViewHolder> {
    Context context;
    ArrayList<New> news;

    public NewAdapter(Context context, ArrayList<New> news) {
        this.context = context;
        this.news = news;
    }

    @NonNull
    @Override
    public NewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_new_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewAdapter.ViewHolder holder, int position) {
        holder.imvThumb.setImageResource(news.get(position).getNewThumb());
        holder.txtName.setText(news.get(position).getNewName());
        holder.txtAuthor.setText(news.get(position).getNewAuthor());
        holder.txtTime.setText(news.get(position).getNewTime());
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvThumb;
        TextView txtName;
        TextView txtAuthor;
        TextView txtTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvThumb = itemView.findViewById(R.id.imvNewRecycler);
            txtName = itemView.findViewById(R.id.txtNameNewRecycler);
            txtAuthor = itemView.findViewById(R.id.txtNameAuthorRecycler);
            txtTime = itemView.findViewById(R.id.txtTimeNewRecycler);
        }
    }
}
