package com.example.Chi.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ActivityForgetCodeBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ForgetCode extends AppCompatActivity {

    ActivityForgetCodeBinding binding;
    public static final String DB_NAME = "database_db.sqlite";
    public static final String DB_FOLDER = "/databases";
    public static SQLiteDatabase db = null;
    public static final String TBL_NAME = "PatientProfile";
    public static final String COL_PATIENTNAME = "PatientName";
    public static final String COL_PHONENUMBER = "PhoneNumber";
    public static final String COL_DOB = "DateOfBirth";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityForgetCodeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEventBack();
        FindByInfo();
    }

    private void FindByInfo() {
        binding.btnFindByInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Find Info by Edt
                String Name = binding.edtPatientName.getText().toString();
                String DOB = binding.edtPatientDOB.getText().toString();
                String Phone = binding.edtPhoneNumber.getText().toString();
                // Kiểm tra xem các trường bắt buộc đã được nhập đúng chưa
                if (Name.isEmpty()) {
                    Toast.makeText(ForgetCode.this, "Vui lòng nhập họ và tên", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(DOB)) {
                    Toast.makeText(ForgetCode.this, "Vui lòng nhập ngày sinh", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Kiểm tra định dạng ngày sinh
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                sdf.setLenient(false);

                try {
                    Date dob = sdf.parse(DOB);
                } catch (ParseException e) {
                    Toast.makeText(ForgetCode.this, "Định dạng ngày sinh không đúng (dd/mm/yyyy)", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(Phone)) {
                    Toast.makeText(ForgetCode.this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!TextUtils.isDigitsOnly(Phone)) {
                    Toast.makeText(ForgetCode.this, "Số điện thoại phải là số", Toast.LENGTH_SHORT).show();
                    return;
                }


                db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
                Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME + " WHERE " + COL_PHONENUMBER + " LIKE ?", new String[]{Phone});
                if (cursor.moveToFirst()) {
                    String patientID = cursor.getString(1);
                    String patientName = cursor.getString(2);
                    String patientIDCard = cursor.getString(3);
                    String patientDOB = cursor.getString(4);
                    String patientGender = cursor.getString(5);
                    String patientAddress = cursor.getString(6);
                    String phoneNumber = cursor.getString(7);
                    String patientEmail = cursor.getString(9);
                    // Tạo Intent để chuyển dữ liệu sang MainProfile
                    Intent intent = new Intent(ForgetCode.this, MainProfile.class);
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
                } else {
                    Toast.makeText(ForgetCode.this, "Patient not found", Toast.LENGTH_SHORT).show();
                }
                cursor.close();
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