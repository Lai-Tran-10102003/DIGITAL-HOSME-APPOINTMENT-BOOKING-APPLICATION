package com.example.Giang.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.finalproject_hosme.databinding.NewListBinding;

public class NewList extends AppCompatActivity {
    NewListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = NewListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addEvents();
    }

    private void addEvents() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewList.this, MainActivity.class);
                startActivity(intent);
            }
        });

        binding.linearLayoutNewMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewList.this, MainNew.class);
                startActivity(intent);
            }
        });

        binding.linearLayoutNew1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewList.this, New1.class);
                startActivity(intent);
            }
        });

        binding.linearLayoutNew2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewList.this, New2.class);
                startActivity(intent);
            }
        });

        binding.linearLayoutNew3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewList.this, New3.class);
                startActivity(intent);
            }
        });

        binding.linearLayoutNew4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewList.this, New4.class);
                startActivity(intent);
            }
        });
    }
}
