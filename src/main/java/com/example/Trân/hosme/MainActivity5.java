package com.example.Trân.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.Trân.model.User;
import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.Activity5Binding;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity5 extends AppCompatActivity {

    Activity5Binding binding;

    public static final String DB_NAME = "database_db.sqlite";
    public static final String DB_FOLDER = "databases";
    public static SQLiteDatabase db = null;
    public static final String TBL_NAME = "UserData";
    public static final String USER_ID = "UserID";
    public static final String USER_NAME = "UserName";
    public static final String USER_PHONE = "PhoneNumber";
    public static final String USER_BIRTH = "DayofBirth";
    public static final String USER_GENDER = "Gender";
    public static final String USER_EMAIL = "Email";
    public static final String USER_PASS = "Password";
    public static final String USER_IMAGE = "UserImage";

    User u = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = Activity5Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        copyDB();
        addEvents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void updateData(String newName, String newBirth, String newGender, String newEmail) {
        try {
            // Mở cơ sở dữ liệu để ghi
            SQLiteDatabase db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);

            // Tạo một ContentValues để chứa các giá trị mới
            ContentValues values = new ContentValues();
            values.put(USER_NAME, newName);
            values.put(USER_BIRTH, newBirth);
            values.put(USER_GENDER, newGender);
            values.put(USER_EMAIL, newEmail);

            if (newName.isEmpty()) {
                binding.txtFull.setVisibility(View.VISIBLE);// Yêu cầu nhập đầy đủ
                return;
            } else {
                binding.txtFull.setVisibility(View.GONE);
            }

            if (newBirth.isEmpty()) {
                binding.txtFull1.setVisibility(View.VISIBLE);// Yêu cầu nhập đầy đủ
                binding.txtBirthValid.setVisibility(View.GONE);
                return;
            } else {
                binding.txtFull1.setVisibility(View.GONE);
            }


            if (!isValidDateFormat(newBirth)) {
                binding.txtBirthValid.setVisibility(View.VISIBLE);// Yêu cầu nhập đúng định dạng
                return;
            } else {
                binding.txtBirthValid.setVisibility(View.GONE);
            }

            if (!isValidEmailFormat(newEmail)) {
                binding.txtEmailValid.setVisibility(View.VISIBLE);// Yêu cầu nhập đúng định dạng
                return;
            } else {
                binding.txtEmailValid.setVisibility(View.GONE);
            }

            // Cập nhật dữ liệu trong bảng User data với các giá trị mới
            int result = db.update(TBL_NAME, values, USER_ID + " = ?", new String[]{String.valueOf(u.getUserID())});


            // Đóng cơ sở dữ liệu sau khi thực hiện truy vấn
            db.close();

            // Kiểm tra xem cập nhật có thành công không và hiển thị thông báo tương ứng
            if (result != -1) {
                Toast.makeText(this, "User information updated successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to update user information", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to update user information", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidDateFormat(String date) {
        // Biểu thức chính quy để kiểm tra định dạng dd/mm/yyyy
        String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";
        return date.matches(regex);
    }

    private boolean isValidEmailFormat(String email) {
        // Biểu thức chính quy để kiểm tra định dạng email
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(regex);
    }


    private void loadData() {
        db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);

        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        String userID = sharedPreferences.getString("userId", "");

        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME + " WHERE " + USER_ID + " = ?", new String[]{userID});
        ////        Cursor cursor = db.query(TBL_NAME, null, COL_NAME + " LIKE ?", new String[]{"%h%"}, null, null, null);

        while (cursor.moveToNext()) {

            u = new User(cursor.getInt(0), cursor.getString(1),cursor.getInt
                    (2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6), cursor.getBlob
                    (7));
            // Lấy dữ liệu từ Cursor
            String userName = cursor.getString(1);
            String userPhone = cursor.getString(2);
            String userBirth = cursor.getString(3);
            String userGender = cursor.getString(4);
            String userEmail = cursor.getString(5);

            // Load hình ảnh từ cơ sở dữ liệu
            byte[] imageData = cursor.getBlob(7);
            if (imageData != null) {
                // Load hình ảnh từ dữ liệu blob
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
                // Hiển thị hình ảnh lên ImageView
                binding.imvPhoto.setImageBitmap(bitmap);
            } else {
                // Ẩn ImageView nếu cột hình ảnh là null
                binding.imvPhoto.setImageResource(R.drawable.icon_user);
            }


            // Hiển thị dữ liệu vào các trường trong XML
            binding.edtName.setText(userName);
            binding.txtPhone.setText(maskPhoneNumber(userPhone));
            binding.edtBirth.setText(userBirth);

            if (userGender.equals("Nam")) {
                binding.radioMale.setChecked(true);
            } else if (userGender.equals("Nữ")) {
                binding.radioFemale.setChecked(true);
            }

            binding.edtEmail.setText(userEmail);

        }

        // Đóng cursor sau khi sử dụng
        cursor.close();
    }

    private String maskPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() >= 7) {
            // Lấy 3 số đầu
            String firstThree = phoneNumber.substring(0, 3);
            // Lấy 3 số cuối
            String lastThree = phoneNumber.substring(phoneNumber.length() - 3);
            // Tạo dấu sao cho số chữa giữa
            String middleMask = "";
            for (int i = 0; i < phoneNumber.length() - 6; i++) {
                middleMask += "*";
            }
            // Kết hợp các phần lại với nhau
            return firstThree + middleMask + lastThree;
        } else {
            // Trả về số điện thoại gốc nếu không đủ kí tự
            return phoneNumber;
        }
    }

    private void addEvents() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = binding.edtName.getText().toString();
                String newBirth = binding.edtBirth.getText().toString();
                String newGender = binding.rdGender.getCheckedRadioButtonId() == R.id.radioMale ? "Nam" : "Nữ";
                String newEmail = binding.edtEmail.getText().toString();

                updateData(newName, newBirth, newGender, newEmail);
            }
        });

        binding.imvPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đổi hình ảnh thành mảng byte
                byte[] imageData = convertBitmapToByteArray(((BitmapDrawable) binding.imvPhoto.getDrawable()).getBitmap());

                // Mở hoạt động ImagePersonal với dữ liệu hình ảnh
                goToImagePersonalActivity(imageData);
            }
        });
    }

    private byte[] convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    private void goToImagePersonalActivity(byte[] imageData) {
        Intent intent = new Intent(MainActivity5.this, ImagePersonal.class);
        intent.putExtra("data", u);
        intent.putExtra("image", imageData);
        startActivity(intent);
    }

    private void copyDB() {
        File dbFile = getDatabasePath(DB_NAME);
        if(!dbFile.exists()){
            //Copy
            if (copyDbFromAssets())
                Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Fail!", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean copyDbFromAssets(){
        String dbPath = getApplicationInfo().dataDir + "/" + DB_FOLDER + "/" + DB_NAME;
        //data/data/packageName/product_db.db
        try {
            InputStream inputStream = getAssets().open(DB_NAME);
            File f = new File(getApplicationInfo().dataDir + "/" + DB_FOLDER + "/");
            if (!f.exists()) {
                f.mkdir();
            }
            OutputStream outputStream = new FileOutputStream(dbPath);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

}