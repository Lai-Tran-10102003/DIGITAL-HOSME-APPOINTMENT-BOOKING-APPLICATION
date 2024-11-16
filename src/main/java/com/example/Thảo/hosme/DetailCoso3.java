package com.example.Thảo.hosme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.Thảo.adapters.ImageRecyclerViewAdapter;
import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ActivityDetailCoso3Binding;

import java.util.ArrayList;

public class DetailCoso3 extends AppCompatActivity {
    ActivityDetailCoso3Binding binding;
    ImageRecyclerViewAdapter adapter;
    ArrayList<Integer> image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailCoso3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        LoadData();
        addEvent();
    }

    private void addEvent() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void LoadData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.rvcoso.setLayoutManager(layoutManager);
        binding.rvcoso.setHasFixedSize(true);

        image = new ArrayList<>();
        image.add(R.drawable.cs21);
        image.add(R.drawable.cs22);
        image.add(R.drawable.cs23);
        image.add(R.drawable.cs24);
        image.add(R.drawable.cs25);

        adapter = new ImageRecyclerViewAdapter(getApplicationContext(), image);
        binding.rvcoso.setAdapter(adapter);
    }
}