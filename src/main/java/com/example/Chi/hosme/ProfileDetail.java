package com.example.Chi.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ActivityProfileDetailBinding;

public class ProfileDetail extends AppCompatActivity {

    ActivityProfileDetailBinding binding;

    public static final String DB_NAME = "database_db.sqlite";
    public static SQLiteDatabase db = null;
    public static final String TBL_NAME = "PatientProfile";
    public static final String COL_PATIENTID = "PatientID";

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
        binding = ActivityProfileDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        addEventBack();
        displayProfileDetail();
    }


    private void displayProfileDetail() {
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
            binding.txtPatientName.setText(patientName);
            binding.txtPhoneNumber.setText(phoneNumber);
            binding.txtPatientIDCard.setText(patientIDCard);
            binding.txtPatientDOB.setText(patientDOB);
            binding.txtPatientGender.setText(patientGender);
            binding.txtPatientAddress.setText(patientAddress);
            binding.txtPatientEmail.setText(patientEmail);

            eventChooseEditProfile();
        }

    }

    private void eventChooseEditProfile() {
        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để chuyển dữ liệu sang EditProfile
                Intent intent = new Intent(ProfileDetail.this, EditProfile.class);
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
            }
        });

        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(ProfileDetail.this);
                dialog.setContentView(R.layout.activity_dialog_delete_profile);

                Button btnNo, btnYes;

                btnNo = dialog.findViewById(R.id.btnNo);
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnYes = dialog.findViewById(R.id.btnYes);
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
                        int rowsAffected = db.delete(TBL_NAME, COL_PATIENTID + " = ?", new String[]{patientID});
                        if (rowsAffected > 0) {
                            // Nếu có ít nhất một hàng bị ảnh hưởng, hiển thị thông báo xóa thành công
                            Toast.makeText(ProfileDetail.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            // Nếu không có hàng nào bị ảnh hưởng, hiển thị thông báo xóa không thành công
                            Toast.makeText(ProfileDetail.this, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT); //chinh kich thuoc dialog
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //loai bo bg trang
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