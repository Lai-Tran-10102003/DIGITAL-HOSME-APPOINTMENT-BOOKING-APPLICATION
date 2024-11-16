package com.example.Hân.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.Chi.hosme.EditProfile;
import com.example.Chi.hosme.ListProfiles;
import com.example.Hân.model.User;
import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ActivityDatmkmoiBinding;

public class datmkmoi extends AppCompatActivity {
    ActivityDatmkmoiBinding binding;
    private EditText editTextNewPassword, editTextConfirmPassword;
    private ImageView imageViewShowNewPassword, imageViewShowConfirmPassword;
    private Button buttonSave;
    private boolean isPasswordVisible = false;

    public static final String DB_NAME = "database_db.sqlite";
    public static SQLiteDatabase db = null;
    public static final String TBL_NAME = "UserData";
    public static final String PASSWORD = "Password";
    public static final String PHONENUMBER = "PhoneNumber";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityDatmkmoiBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        addEvent();

        editTextNewPassword = findViewById(R.id.edtNewPassword);
        editTextConfirmPassword = findViewById(R.id.edtConfirmNewPassword);
        imageViewShowNewPassword = findViewById(R.id.imageView_show_new_password);
        imageViewShowConfirmPassword = findViewById(R.id.imageView_show_confirm_password);
        buttonSave = findViewById(R.id.button_save);

    }

    private void addEvent() {
        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        String PatientName = sharedPreferences.getString("patientName", "");
        String PhoneNumber = sharedPreferences.getString("phoneNumber", "");
        binding.txtName.setText(PatientName);
        binding.txtPhoneNumber.setText(PhoneNumber);
        binding.imageViewShowNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPasswordVisible = !isPasswordVisible;
                int visibility = isPasswordVisible ? View.VISIBLE : View.GONE;
                editTextNewPassword.setInputType(isPasswordVisible ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                editTextNewPassword.setSelection(editTextNewPassword.getText().length());
                imageViewShowNewPassword.setImageResource(isPasswordVisible ? R.drawable.eye : R.drawable.eye);
            }
        });

        binding.imageViewShowConfirmPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPasswordVisible = !isPasswordVisible;
                int visibility = isPasswordVisible ? View.VISIBLE : View.GONE;
                editTextConfirmPassword.setInputType(isPasswordVisible ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                editTextConfirmPassword.setSelection(editTextConfirmPassword.getText().length());
                imageViewShowConfirmPassword.setImageResource(isPasswordVisible ? R.drawable.eye : R.drawable.eye);
            }
        });

        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPassword = editTextNewPassword.getText().toString().trim();
                String confirmPassword = editTextConfirmPassword.getText().toString().trim();

                if (TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(confirmPassword)) {
                    Toast.makeText(datmkmoi.this, "Vui lòng nhập mật khẩu mới và xác nhận mật khẩu.", Toast.LENGTH_SHORT).show();
                } else if (newPassword.length() < 8) {
                    Toast.makeText(datmkmoi.this, "Mật khẩu mới phải có ít nhất 8 ký tự.", Toast.LENGTH_SHORT).show();
                } else if (!newPassword.equals(confirmPassword)) {
                    Toast.makeText(datmkmoi.this, "Mật khẩu xác nhận không khớp.", Toast.LENGTH_SHORT).show();
                } else {

                    db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);

                    SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                    String PhoneNumber = sharedPreferences.getString("phoneNumber", "");

                    ContentValues values = new ContentValues();
                    values.put(PASSWORD, newPassword);

                    String whereClause = PHONENUMBER + " = ?";
                    String[] whereArgs = {PhoneNumber};

                    int rowsAffected = db.update(TBL_NAME, values, whereClause, whereArgs);

                    if (rowsAffected > 0) {
                        Toast.makeText(datmkmoi.this, "Cập nhật mật khẩu thành công.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(datmkmoi.this, nhapsdt.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(datmkmoi.this, "Cập nhật mật khẩu thất bại.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

}

