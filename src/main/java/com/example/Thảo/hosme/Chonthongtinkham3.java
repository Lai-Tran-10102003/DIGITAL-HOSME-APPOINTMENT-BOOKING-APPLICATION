package com.example.Thảo.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.Thảo.models.Chuyenkhoa;
import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ActivityChonthongtinkham3Binding;

import java.util.ArrayList;

public class Chonthongtinkham3 extends AppCompatActivity {
    ActivityChonthongtinkham3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChonthongtinkham3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getAndSendData();
        addEvents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAndSendData();
    }

    private void getAndSendData() {
        ArrayList<Chuyenkhoa> chuyenKhoaList = getIntent().getParcelableArrayListExtra("chuyenKhoaList");
        if (chuyenKhoaList != null) {
            binding.lnChonchuyenkhoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Chonthongtinkham3.this, Chonchuyenkhoa.class);
                    intent.putParcelableArrayListExtra("chuyenKhoaList", chuyenKhoaList);
                    startActivity(intent);
                }
            });
        }
    }

    private void addEvents() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.lnChonchuyenkhoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chonthongtinkham3.this, Chonchuyenkhoa.class);
                startActivity(intent);
            }
        });

    }
}