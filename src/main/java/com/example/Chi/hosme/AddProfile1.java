package com.example.Chi.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ActivityAddProfile1Binding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddProfile1 extends AppCompatActivity {

    ActivityAddProfile1Binding binding;
    public static final String DB_NAME = "database_db.sqlite";
    public static SQLiteDatabase db = null;
    public static final String TBL_NAME = "PatientProfile";
    public static final String COL_USERID = "UserID";
    public static final String COL_PATIENTID = "PatientID";
    public static final String COL_PATIENTNAME = "PatientName";
    public static final String COL_IDCARD = "IDCard";
    public static final String COL_PHONENUMBER = "PhoneNumber";
    public static final String COL_DOB = "DateOfBirth";
    public static final String COL_GENDER = "Gender";
    public static final String COL_ADDRESS = "Address";
    public static final String COL_EMAIL = "Email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddProfile1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEventBack();
        NevigateMainProfile();
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

    private void NevigateMainProfile() {
        binding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Lấy giá trị mới từ các EditText
                String newName = binding.edtPatientName.getText().toString();
                String newDOB = binding.edtPatientDOB.getText().toString();
                String newIDCard = binding.edtPatientIDCard.getText().toString();
                String newEmail = binding.edtPatientEmail.getText().toString();
                String newPhoneNumber = binding.edtPhoneNumber.getText().toString();
                String newAddress = binding.edtPatientAddress.getText().toString();

                // Lấy giá trị của giới tính
                String newGender = "";
                int selectedRadioButtonId = binding.radioGroup.getCheckedRadioButtonId();
                if (selectedRadioButtonId == R.id.radio_Male) {
                    newGender = "Nam";
                } else if (selectedRadioButtonId == R.id.radio_Female) {
                    newGender = "Nữ";
                }

                // Kiểm tra xem các trường bắt buộc đã được nhập đúng chưa
                if (newName.isEmpty()) {
                    Toast.makeText(AddProfile1.this, "Vui lòng nhập họ và tên", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(newDOB)) {
                    Toast.makeText(AddProfile1.this, "Vui lòng nhập ngày sinh", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Kiểm tra định dạng ngày sinh
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                sdf.setLenient(false);

                try {
                    Date dob = sdf.parse(newDOB);
                } catch (ParseException e) {
                    Toast.makeText(AddProfile1.this, "Định dạng ngày sinh không đúng (dd/mm/yyyy)", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!newGender.equals("Nam") && !newGender.equals("Nữ")) {
                    // Nếu giới tính không phải 'Nam' hoặc 'Nữ', hiển thị thông báo và dừng lại
                    Toast.makeText(AddProfile1.this, "Vui lòng chọn giới tính 'Nam' hoặc 'Nữ'", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!TextUtils.isDigitsOnly(newIDCard)) {
                    Toast.makeText(AddProfile1.this, "CCCD phải là số", Toast.LENGTH_SHORT).show();
                    return;
                }


//                -----

                if (TextUtils.isEmpty(newPhoneNumber)) {
                    Toast.makeText(AddProfile1.this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!TextUtils.isDigitsOnly(newPhoneNumber)) {
                    Toast.makeText(AddProfile1.this, "Số điện thoại phải là số", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Kiểm tra số điện thoại đã tồn tại trong cơ sở dữ liệu chưa
                db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
                String phoneExistQuery = "SELECT COUNT(*) FROM " + TBL_NAME + " WHERE " + COL_PHONENUMBER + "=?";
                Cursor checkCursor = db.rawQuery(phoneExistQuery, new String[]{newPhoneNumber});
                checkCursor.moveToFirst();
                int count = checkCursor.getInt(0);
                checkCursor.close();
                if (count > 0) {
                    // Số điện thoại đã tồn tại trong cơ sở dữ liệu
                    Toast.makeText(AddProfile1.this, "Số điện thoại đã tồn tại", Toast.LENGTH_SHORT).show();
                    return;
                }
//                -----

                if (newAddress.isEmpty()) {
                    Toast.makeText(AddProfile1.this, "Vui lòng nhập địa chỉ nơi ở", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Mở cơ sở dữ liệu
                db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);


                // Truy vấn SQL để tìm PATIENT_ID lớn nhất trong cột PATIENT_ID
                String query = "SELECT MAX(" + COL_PATIENTID + ") FROM " + TBL_NAME;
                Cursor cursor = db.rawQuery(query, null);

                String maxPatientId = null;

                if (cursor.moveToFirst()) {
                    maxPatientId = cursor.getString(0);
                }
                cursor.close();

                SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                String userID = sharedPreferences.getString("userId", "");

                String newPatientId;
                int currentNumber = Integer.parseInt(maxPatientId.split("-")[1]);
                int newNumber = currentNumber + 1;
                newPatientId = "CN24-00" + newNumber;
                // Tạo một đối tượng ContentValues và đặt các giá trị của dòng mới
                ContentValues values = new ContentValues();

                //Thiếu một cái value thực cho UserID tại đây, để tạm 1
                values.put(COL_USERID, userID);
                values.put(COL_PATIENTID, newPatientId);
                values.put(COL_PATIENTNAME, newName);
                values.put(COL_DOB, newDOB);
                values.put(COL_IDCARD, newIDCard);
                values.put(COL_EMAIL, newEmail);
                values.put(COL_PHONENUMBER, newPhoneNumber);
                values.put(COL_ADDRESS, newAddress);
                values.put(COL_GENDER, newGender);

                long rowId = db.insert(TBL_NAME, null, values);

                if (rowId != -1) {
                    Toast.makeText(AddProfile1.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddProfile1.this, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                }
                // Chuyển về màn hình chính ListProfile sau khi cập nhật thành công
                Intent intent = new Intent(AddProfile1.this, ListProfiles.class);
                startActivity(intent);
                finish(); // Kết thúc màn hình hiện tại
            }
        });
    }
}