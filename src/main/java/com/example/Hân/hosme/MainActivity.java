package com.example.Hân.hosme;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ActivityMain5Binding;

public class MainActivity extends AppCompatActivity {

    ActivityMain5Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMain5Binding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, nhapsdt.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
    private void showForgotPasswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_forget_password, null);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

