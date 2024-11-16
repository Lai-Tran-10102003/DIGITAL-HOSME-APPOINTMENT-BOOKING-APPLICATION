package com.example.Hân.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.Hân.model.Notifications;
import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ActivityDetailNotificationBinding;

public class DetailNotification extends AppCompatActivity {
    ActivityDetailNotificationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityDetailNotificationBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        getData();
        addEvents();
    }


    private void getData() {
        Intent intent = getIntent();
        Notifications q;
        q = (Notifications) intent.getSerializableExtra("data");
        assert q != null;
        binding.txtTitle.setText(String.valueOf(q.getNoTitle()));
        binding.txtDes.setText(String.valueOf(q.getNoDes()));
    }

    private void addEvents() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}