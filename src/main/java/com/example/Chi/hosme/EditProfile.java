package com.example.Chi.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ActivityEditProfileBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditProfile extends AppCompatActivity {
    ActivityEditProfileBinding binding;
    public static final String DB_NAME = "database_db.sqlite";
    public static final String DB_FOLDER = "/databases";
    public static SQLiteDatabase db = null;
    public static final String TBL_NAME = "PatientProfile";
    public static final String COL_PATIENTID = "PatientID";
    public static final String COL_PATIENTNAME = "PatientName";
    public static final String COL_IDCARD = "IDCard";
    public static final String COL_PHONENUMBER = "PhoneNumber";
    public static final String COL_DOB = "DateOfBirth";
    public static final String COL_GENDER = "Gender";
    public static final String COL_ADDRESS = "Address";
    public static final String COL_EMAIL = "Email";
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
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEventBack();
        displayEditProfileDetail();
    }

    private void displayEditProfileDetail() {
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

            // Hiển thị thông tin chi tiết của bệnh nhân
            binding.txtPatientID.setText(patientID);
            binding.edtPatientName.setText(patientName);
            binding.edtPhoneNumber.setText(phoneNumber);
            binding.edtPatientIDCard.setText(patientIDCard);
            binding.edtPatientDOB.setText(patientDOB);
            binding.edtPatientAddress.setText(patientAddress);
            binding.edtPatientEmail.setText(patientEmail);

            // Thiết lập radio button dựa trên giới tính
            if (patientGender != null) {
                if (patientGender.equals("Nam")) {
                    binding.radioMale.setChecked(true); // Nếu là nam, tick vào radio button nam
                } else if (patientGender.equals("Nữ")) {
                    binding.radioFemale.setChecked(true); // Nếu là nữ, tick vào radio button nữ
                }
            }
            editProfile();
        }
    }

    private void editProfile() {
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
                    Toast.makeText(EditProfile.this, "Vui lòng nhập họ và tên", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(newDOB)) {
                    Toast.makeText(EditProfile.this, "Vui lòng nhập ngày sinh", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Kiểm tra định dạng ngày sinh
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                sdf.setLenient(false);

                try {
                    Date dob = sdf.parse(newDOB);
                } catch (ParseException e) {
                    Toast.makeText(EditProfile.this, "Định dạng ngày sinh không đúng (dd/mm/yyyy)", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isDigitsOnly(newIDCard)) {
                    Toast.makeText(EditProfile.this, "CCCD phải là số", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(newPhoneNumber)) {
                    Toast.makeText(EditProfile.this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!TextUtils.isDigitsOnly(newPhoneNumber)) {
                    Toast.makeText(EditProfile.this, "Số điện thoại phải là số", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (newAddress.isEmpty()) {
                    Toast.makeText(EditProfile.this, "Vui lòng nhập địa chỉ nơi ở", Toast.LENGTH_SHORT).show();
                    return;
                }


                // Mở cơ sở dữ liệu
                db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);

                // Truy vấn cơ sở dữ liệu để lấy giá trị hiện tại của bản ghi có patientID tương ứng
                Cursor cursor = db.query(TBL_NAME, null, COL_PATIENTID + "=?", new String[]{patientID}, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    // Lấy các giá trị hiện tại từ cơ sở dữ liệu
                    String currentName = cursor.getString(2);
                    String currentDOB = cursor.getString(4);
                    String currentIDCard = cursor.getString(3);
                    String currentEmail = cursor.getString(9);
                    String currentPhoneNumber = cursor.getString(7);
                    String currentAddress = cursor.getString(6);
                    String currentGender = cursor.getString(5);

                    // Kiểm tra xem có sự thay đổi không
                    boolean isDataChanged = !newName.equals(currentName) || !newDOB.equals(currentDOB) ||
                            !newIDCard.equals(currentIDCard) || !newEmail.equals(currentEmail) ||
                            !newPhoneNumber.equals(currentPhoneNumber) || !newAddress.equals(currentAddress) ||
                            !newGender.equals(currentGender);

                    // Nếu có sự thay đổi, thực hiện cập nhật dữ liệu
                    if (isDataChanged) {
                        ContentValues values = new ContentValues();
                        values.put(COL_PATIENTNAME, newName);
                        values.put(COL_DOB, newDOB);
                        values.put(COL_IDCARD, newIDCard);
                        values.put(COL_EMAIL, newEmail);
                        values.put(COL_PHONENUMBER, newPhoneNumber);
                        values.put(COL_ADDRESS, newAddress);
                        values.put(COL_GENDER, newGender);

                        // Thực hiện truy vấn update để cập nhật dữ liệu
                        int rowsAffected = db.update(TBL_NAME, values, COL_PATIENTID + "=?", new String[]{patientID});
                        // Kiểm tra xem có bao nhiêu hàng đã được cập nhật
                        if (rowsAffected > 0) {
                            // Hiển thị toast thông báo cập nhật thành công
                            Toast.makeText(EditProfile.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(EditProfile.this, ListProfiles.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // Hiển thị toast thông báo cập nhật không thành công
                            Toast.makeText(EditProfile.this, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Hiển thị toast thông báo không có sự thay đổi
                        Toast.makeText(EditProfile.this, "Không có sự thay đổi", Toast.LENGTH_SHORT).show();

                        // Chuyển về màn hình chính nhưng để tạm MainActivity sau khi cập nhật thành công
                        Intent intent = new Intent(EditProfile.this, ListProfiles.class);
                        startActivity(intent);
                        finish(); // Kết thúc màn hình hiện tại
                    }
                    // Đóng con trỏ
                    if (cursor != null) {
                        cursor.close();
                    }
                }
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