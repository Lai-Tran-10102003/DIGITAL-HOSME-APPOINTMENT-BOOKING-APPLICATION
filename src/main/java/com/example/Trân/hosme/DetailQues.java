package com.example.Trân.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.Trân.model.Question;
import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ActivityDetailQuesBinding;

public class DetailQues extends AppCompatActivity {
    ActivityDetailQuesBinding binding;
    Question q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailQuesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getData();
        addEvent();

    }

    private void getData() {
        Intent intent = getIntent();
        q = (Question) intent.getSerializableExtra("data");
        assert q != null;
        binding.txtQue.setText(String.valueOf(q.getQueTitle()));
        binding.txtQueAn.setText(String.valueOf(q.getQueAnswer()));

    }

    private void addEvent() {

        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}