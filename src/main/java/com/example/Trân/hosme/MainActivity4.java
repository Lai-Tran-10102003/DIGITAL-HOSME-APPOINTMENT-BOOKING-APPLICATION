package com.example.Trân.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.Trân.model.HealthForm;
import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.Activity4Binding;

public class MainActivity4 extends AppCompatActivity {
    Activity4Binding binding;
    HealthForm h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = Activity4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getData();
        addEvent();

    }

    private void getData() {
        Intent intent = getIntent();
        h = (HealthForm) intent.getSerializableExtra("data");
        assert h != null;
        binding.txtSTT.setText(String.valueOf(h.getQueue()));
        binding.txtBookID.setText(String.valueOf(h.getBookID()));
        binding.txtName.setText(h.getPatientName());
        binding.txtNgaySinh.setText(h.getNgaySinh());
        binding.txtGender.setText(h.getGioitinh());
        binding.txtPhone.setText(h.getPhoneNumber());
        binding.txtdepartname.setText(h.getDepartmentName());
        binding.txtdepartid.setText(h.getRoom());
        binding.txtadrressHos.setText(h.getAddressHos());
        binding.txtNgayKham.setText(h.getDate());
        binding.txtTimeKham.setText(h.getTime());
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