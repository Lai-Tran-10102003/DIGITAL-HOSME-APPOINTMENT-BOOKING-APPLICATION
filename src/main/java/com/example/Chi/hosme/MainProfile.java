package com.example.Chi.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ActivityMainProfileBinding;

public class MainProfile extends AppCompatActivity {

    ActivityMainProfileBinding binding;

    private String patientID;
    private String patientName;
    private String phoneNumber;
    private String patientIDCard;
    private String patientDOB;
    private String patientGender;
    private String patientAddress;
    private String patientEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addEventBack();
//        loadDB();
        displayPatientProfile();
    }

    private void displayPatientProfile() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("patientID")) {
            patientID = intent.getStringExtra("patientID");
            patientName = intent.getStringExtra("patientName");
            phoneNumber = intent.getStringExtra("phoneNumber");
            patientIDCard = intent.getStringExtra("patientIDCard");
            patientDOB = intent.getStringExtra("patientDOB");
            patientGender = intent.getStringExtra("patientGender");
            patientAddress = intent.getStringExtra("patientAddress");
            patientEmail = intent.getStringExtra("patientEmail");

            // Hiển thị thông tin bệnh nhân
            binding.txtPatientID.setText(patientID);
            binding.txtPatientName.setText(patientName);
            binding.txtPhoneNumber.setText(phoneNumber);
            eventChooseProfileDetail();
        }
    }



    private void eventChooseProfileDetail() {
        binding.linearInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để chuyển dữ liệu sang ProfileDetail
                Intent intent = new Intent(MainProfile.this, ProfileDetail.class);
                // Đưa thông tin bệnh nhân vào Intent
                intent.putExtra("patientID", patientID);
                intent.putExtra("patientName", patientName);
                intent.putExtra("phoneNumber", phoneNumber);
                intent.putExtra("patientIDCard", patientIDCard);
                intent.putExtra("patientDOB", patientDOB);
                intent.putExtra("patientGender", patientGender);
                intent.putExtra("patientAddress", patientAddress);
                intent.putExtra("patientEmail", patientEmail);
                startActivity(intent);
            }
        });
    }

    private void addEventBack() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Back home screen
                finish();
            }
        });
    }
}