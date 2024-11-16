package com.example.Chi.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.Chi.models.Profile;
import com.example.finalproject_hosme.R;

import java.util.List;

public class ProfileAdapter extends BaseAdapter {
    Activity context;
    int profile_layout;
    List<Profile> profiles;
    public ProfileAdapter(Activity context, int profile_layout, List<Profile> profiles) {
        this.context = context;
        this.profile_layout = profile_layout;
        this.profiles = profiles;
    }
    @Override
    public int getCount() {
        return profiles.size();
    }

    @Override
    public Object getItem(int position) {
        return profiles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            holder = new ProfileAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(profile_layout, null);


            holder.txtPatientID = convertView.findViewById(R.id.txtPatientID);
            holder.txtPatientName = convertView.findViewById(R.id.txtPatientName);
            holder.txtPhoneNumber = convertView.findViewById(R.id.txtPhoneNumber);
            convertView.setTag(holder);
        }else{
            holder = (ProfileAdapter.ViewHolder) convertView.getTag();
        }
        //Binding data
        // Check if TextViews are null before setting text
        Profile profile = profiles.get(position);
        holder.txtPatientID.setText(profile.getPatientID());
        holder.txtPatientName.setText(profile.getPatientName());
        holder.txtPhoneNumber.setText(profile.getPhoneNumber());
        return convertView;
    }
    public static class ViewHolder{
        TextView txtPatientID, txtPatientName, txtPhoneNumber;
    }
}

