package com.example.Thảo.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ActivityChonthongtinkham4Binding;

public class Chonthongtinkham4 extends AppCompatActivity {
    ActivityChonthongtinkham4Binding binding;

//    public static final String DB_NAME = "database_db.sqlite";
//    public static final String DB_FOLDER = "/databases";
//    public static SQLiteDatabase db = null;
//    public static final String TBL_NAME = "BookingData";
//    public static final String BOOKING_ID = "BookingID";
//    public static final String USER_ID = "UserID";
//    public static final String PATIENT_ID = "PatientID";
//    public static final String PATIENT_NAME = "PatientName";
//    public static final String DEPARTMENT_ID = "DepartmentID";
//    public static final String DEPARTMENT_NAME = "DepartmentName";
//    public static final String FUNCTION = "Function";
//    public static final String ADDRESSHOS = "AddressHos";
//    public static final String QUEUE = "Queue";
//    public static final String ROOM = "Room";
//    public static final String DATE = "Date";
//    public static final String HOUR = "Hour";
//    public static final String STATUS = "Status";

    public static final String PREFERENCE_NAME = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChonthongtinkham4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addEvents();
    }



    private void addEvents() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String savedData = sharedPreferences.getString("chuyen_khoa", "");
        String savedData1 = sharedPreferences.getString("bac_si", "");
        String savedData2 = sharedPreferences.getString("date", "");
        String savedData3 = sharedPreferences.getString("time", "");
        binding.txtChuyenKhoa.setText(savedData);
        binding.txtBacSi.setText(savedData1);
        binding.txtDate.setText(savedData2);
        binding.txtHour.setText(savedData3);

        binding.lnChuyenkhoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chonthongtinkham4.this, Choncosoyte.class);
                startActivity(intent);
            }
        });
        binding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chonthongtinkham4.this, Xacnhanthongtin.class);
                startActivity(intent);
            }
        });


    }

}