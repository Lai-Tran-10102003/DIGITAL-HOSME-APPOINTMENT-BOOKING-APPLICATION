package com.example.Hân.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ActivityDialogForgotPasswordBinding;
import com.example.finalproject_hosme.databinding.DialogForgetPasswordBinding;

public class dialog_forgot_password extends AppCompatActivity {

    DialogForgetPasswordBinding binding;
    private EditText editTextOtp;
    private TextView textViewTimer;
    private TextView textViewSendOtp;
    private ImageButton btnClose;
    private CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = DialogForgetPasswordBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        editTextOtp = binding.editTextOtp;
        textViewTimer = binding.textViewTimer;
        textViewSendOtp = binding.btnResendOtp;
        btnClose = binding.btnClose;

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        startCountdownTimer();
    }

    private void startCountdownTimer() {
        countDownTimer = new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                textViewTimer.setText("0:" + String.format("%02d", seconds));
            }

            public void onFinish() {
                textViewTimer.setText("0:00");
                textViewSendOtp.setVisibility(View.VISIBLE);
            }
        }.start();

        textViewSendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code để gửi mã OTP
                textViewSendOtp.setVisibility(View.GONE);
                startCountdownTimer();
            }
        });

        editTextOtp.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String otp = editTextOtp.getText().toString().trim();
                    if (otp.length() == 4) {
                        // Kiểm tra mã OTP
                        if (otp.equals("1234")) { // Thay đổi thành mã OTP đúng
                            Intent intent = new Intent(dialog_forgot_password.this, datmkmoi.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(dialog_forgot_password.this, "Mã OTP không đúng", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(dialog_forgot_password.this, "Vui lòng nhập đúng mã OTP", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}