package com.example.Giang.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.Giang.adapter.DetailResultAdapter;
import com.example.Giang.model.DetailResult;
import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.DetailMedicalRecordListBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DetailMedicalRecord extends AppCompatActivity {
    DetailMedicalRecordListBinding binding;
    DetailResultAdapter adapter;
    ArrayList<DetailResult> results;


    public static final String DB_NAME = "database_db.sqlite";
    public static final String DB_FOLDER = "/databases";
    public static SQLiteDatabase db = null;
    public static final String TBL_NAME = "TestData";
    public static final String COL_MEDICALID = "MedicalID";
    public static final String COL_TESTID = "TestID";
    public static final String COL_PATIENTID = "PatientID";
    public static final String COL_PATIENTNAME = "PatientName";
    public static final String COL_MAINEXAM = "MainExam";
    public static final String COL_TIMEIN = "DateTimeIn";
    public static final String COL_TIMEOUT = "DateTimeOut";
    public static final String COL_ADDRESS = "AddressHos";
    public static final String COL_IMAGE = "Image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DetailMedicalRecordListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addEvents();
        copyData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
    private void copyData() {
        File dbFile = getDatabasePath(DB_NAME);
        if (!dbFile.exists()) {
            if (copyDbFromAssets())
                Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Fail!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean copyDbFromAssets() {
        String dbPath = getApplicationInfo().dataDir + "/" + DB_FOLDER + "/" + DB_NAME;
        try {
            InputStream inputStream = getAssets().open(DB_NAME);
            File file = new File(getApplicationInfo().dataDir + "/" + DB_FOLDER + "/");
            if (!file.exists()) {
                file.mkdir();
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
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    //
    private void loadData() {
        db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        Cursor cursor = db.query(TBL_NAME, null, null, null, null, null, null);
        results = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String patientID = cursor.getString(2);
                String patientName = cursor.getString(3);
                String mainExam = cursor.getString(4);
                String timeIn = cursor.getString(5);
                String timeOut = cursor.getString(6);
                String doctor = cursor.getString(9);
                String address = cursor.getString(8);
                byte[] imageBytes = cursor.getBlob(7);

                DetailResult resultMedical = new DetailResult(patientID, patientName, mainExam, timeIn, timeOut, doctor, address,imageBytes);
                results.add(resultMedical);
            } while (cursor.moveToNext());
        }

        cursor.close();

        if (!results.isEmpty()) {
            adapter = new DetailResultAdapter(DetailMedicalRecord.this, R.layout.detail_medical_record, results);
            binding.lDetailMedicalRecord.setAdapter(adapter);
        }
    }

    private void addEvents() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailMedicalRecord.this, ResultsMedical.class);
                startActivity(intent);
            }
        });
    }
}