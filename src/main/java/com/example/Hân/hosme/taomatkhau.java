package com.example.Hân.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ActivityTaomatkhauBinding;

public class taomatkhau extends AppCompatActivity {
    ActivityTaomatkhauBinding binding;
    EditText editTextNewPassword, editTextConfirmPassword;
    ImageView imageViewShowNewPassword, imageViewShowConfirmPassword;
    Button buttonSave;
    boolean isPasswordVisible = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityTaomatkhauBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        getData();

        editTextNewPassword = findViewById(R.id.editText_new_password);
        editTextConfirmPassword = findViewById(R.id.editText_confirm_password);
        imageViewShowNewPassword = findViewById(R.id.imageView_show_new_password);
        imageViewShowConfirmPassword = findViewById(R.id.imageView_show_confirm_password);
        buttonSave = findViewById(R.id.button_save);
        imageViewShowNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPasswordVisible = !isPasswordVisible;
                int visibility = isPasswordVisible ? View.VISIBLE : View.GONE;
                editTextNewPassword.setInputType(isPasswordVisible ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                editTextNewPassword.setSelection(editTextNewPassword.getText().length());
                imageViewShowNewPassword.setImageResource(isPasswordVisible ? R.drawable.eye : R.drawable.eye);
            }
        });

        imageViewShowConfirmPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPasswordVisible = !isPasswordVisible;
                int visibility = isPasswordVisible ? View.VISIBLE : View.GONE;
                editTextConfirmPassword.setInputType(isPasswordVisible ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                editTextConfirmPassword.setSelection(editTextConfirmPassword.getText().length());
                imageViewShowConfirmPassword.setImageResource(isPasswordVisible ? R.drawable.eye : R.drawable.eye);
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPassword = editTextNewPassword.getText().toString().trim();
                String confirmPassword = editTextConfirmPassword.getText().toString().trim();

                if (TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(confirmPassword)) {
                    Toast.makeText(taomatkhau.this, "Vui lòng nhập mật khẩu mới và xác nhận mật khẩu.", Toast.LENGTH_SHORT).show();
                } else if (newPassword.length() < 8) {
                    Toast.makeText(taomatkhau.this, "Mật khẩu mới phải có ít nhất 8 ký tự.", Toast.LENGTH_SHORT).show();
                } else if (!newPassword.equals(confirmPassword)) {
                    Toast.makeText(taomatkhau.this, "Mật khẩu xác nhận không khớp.", Toast.LENGTH_SHORT).show();
                } else {
                    String phoneNumber = binding.txtPhoneNumber.getText().toString();
                    Intent intent = new Intent(taomatkhau.this, nhapthongtin.class);
                    intent.putExtra("phoneNumber", phoneNumber);
                    intent.putExtra("password", binding.editTextConfirmPassword.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }

    private void getData() {
        Intent intent = getIntent();
        if (intent != null) {
            String phoneNumber = getIntent().getStringExtra("phoneNumber");
            if (phoneNumber != null) {
                binding.txtPhoneNumber.setText(phoneNumber);
            }
        }
    }
}