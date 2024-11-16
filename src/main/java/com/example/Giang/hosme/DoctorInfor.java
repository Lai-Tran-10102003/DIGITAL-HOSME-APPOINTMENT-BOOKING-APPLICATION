package com.example.Giang.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ActivityDoctorInforBinding;

import java.sql.Blob;

public class DoctorInfor extends AppCompatActivity {

    ActivityDoctorInforBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDoctorInforBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        displayDoctorInfo();
    }

    private void displayDoctorInfo() {
        Intent intent = getIntent();
        if (intent!=null){

//            byte[] doctorImage = intent.getStringExtra("DOCTOR_Image");
            String doctorName = intent.getStringExtra("DOCTOR_Name");
            String doctorDepartmentname = intent.getStringExtra("DOCTOR_DepartmentName");
            String doctorDescription = intent.getStringExtra("DOCTOR_Description");

            binding.txtDoctorNameInfor.setText(doctorName);
//            binding.imvDoctorImage.setImageResource(doctorImage);
            binding.txtDoctorSpecialistInfor.setText(doctorDepartmentname);
            binding.txtDoctorDescriptionInfo.setText(doctorDescription);
        }
    }
}