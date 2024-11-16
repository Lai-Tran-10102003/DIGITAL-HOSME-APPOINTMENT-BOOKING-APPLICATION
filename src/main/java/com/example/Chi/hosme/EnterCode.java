package com.example.Chi.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ActivityEnterCodeBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class EnterCode extends AppCompatActivity {

    ActivityEnterCodeBinding binding;
    public static final String DB_NAME = "database_db.sqlite";
    public static final String DB_FOLDER = "/databases";
    public static SQLiteDatabase db = null;
    public static final String TBL_NAME = "PatientProfile";
    public static final String COL_PATIENTID = "PatientID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEnterCodeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEventBack();
        FindProfile();
        forgetCode();
        copyDB(); //chạy từ lúc mở app đăng nhập, để tạm đây
    }

    private void forgetCode() {
        binding.txtForgetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnterCode.this, ForgetCode.class);
                startActivity(intent);
            }
        });
    }

    private void FindProfile() {
        binding.btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Kiem tra key MSBN để get Profile theo MSBN
                String id = binding.edtPatientID.getText().toString();
                db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
                Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME + " WHERE " + COL_PATIENTID + " LIKE ?", new String[]{"%" + id + "%"});

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
                    Intent intent = new Intent(EnterCode.this, MainProfile.class);
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
                    Toast.makeText(EnterCode.this, "Patient not found", Toast.LENGTH_SHORT).show();
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