package com.example.Hân.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ActivityNhapthongtinBinding;

public class nhapthongtin extends AppCompatActivity {
    ActivityNhapthongtinBinding binding;
    EditText nameEditText;
    EditText dobEditText;
    RadioGroup genderRadioGroup;
    String gender;
    String phoneNumber;
    String password;
    public static final String DB_NAME = "database_db.sqlite";
    public static final String DB_FOLDER = "/databases";
    public static SQLiteDatabase db = null;
    public static final String TBL_NAME = "UserData";
    public static final String USER_ID = "UserID";
    public static final String USER_NAME = "UserName";
    public static final String PHONENUMBER = "PhoneNumber";
    public static final String DAY_OF_BIRTH = "DayofBirth";
    public static final String GENDER = "Gender";
    public static final String EMAIL = "Email";
    public static final String PASSWORD = "Password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityNhapthongtinBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        getData();

        nameEditText = findViewById(R.id.nameEditText);
        dobEditText = findViewById(R.id.dobEditText);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);

        Button hoanThanhButton = findViewById(R.id.hoanThanhButton);
        hoanThanhButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString().trim();
                String dob = dobEditText.getText().toString().trim();
                int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(nhapthongtin.this, "Vui lòng nhập Họ và tên", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(dob)) {
                    Toast.makeText(nhapthongtin.this, "Vui lòng nhập Ngày sinh", Toast.LENGTH_SHORT).show();
                } else if (selectedGenderId == -1) {
                    Toast.makeText(nhapthongtin.this, "Vui lòng chọn Giới tính", Toast.LENGTH_SHORT).show();
                } else {
                    updateDB();
                }
            }
        });

        genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.maleRadioButton) {
                    gender = "Nam";
                } else if (checkedId == R.id.femaleRadioButton) {
                    gender = "Nữ";
                }
            }
        });
    }


    private void updateDB() {
        db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        ContentValues values = new ContentValues();

        String query = "SELECT MAX(" + USER_ID + ") FROM " + TBL_NAME;
        Cursor cursor = db.rawQuery(query, null);
        int maxUserId = 0;
        if (cursor.moveToFirst()) {
            maxUserId = cursor.getInt(0);
        }
        cursor.close();

        int newUserId = maxUserId + 1;

        values.put(USER_ID, newUserId);
        values.put(USER_NAME, binding.nameEditText.getText().toString());
        values.put(PHONENUMBER, phoneNumber);
        values.put(DAY_OF_BIRTH, binding.dobEditText.getText().toString());
        values.put(EMAIL, binding.edtEmail.getText().toString());
        values.put(GENDER, gender);
        values.put(PASSWORD, password);

        long result = db.insert(TBL_NAME, null, values);

        if (result != -1) {
            Toast.makeText(nhapthongtin.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(nhapthongtin.this, nhapsdt.class);
            startActivity(intent);
        } else {
            Toast.makeText(nhapthongtin.this, "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
        }
    }
    private void getData() {
        Intent intent = getIntent();
        if (intent != null) {
            phoneNumber = intent.getStringExtra("phoneNumber");
            password = intent.getStringExtra("password");
        }
    }

}
