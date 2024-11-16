package com.example.Giang.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.Giang.model.ResultMedicalRecord;
import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ResultsMedicalBinding;

import java.util.ArrayList;

public class ResultsMedical extends AppCompatActivity {
    ResultsMedicalBinding binding;
    ArrayList<ResultMedicalRecord> results = new ArrayList<>();
    ResultMedicalRecord selectedresults = null;

    public static final String DB_NAME = "database_db.sqlite";
    public static final String DB_FOLDER = "/databases";
    public static SQLiteDatabase db = null;
    public static final String TBL_NAME = "MedicalRecordData";
    public static final String COL_MEDICALID = "MedicalID";
    public static final String COL_PATIENTID = "PatientID";
    public static final String COL_PATIENTNAME = "PatientName";
    public static final String COL_DEPARTMENTNAME = "DepartmentName";
    public static final String COL_DATEIN = "Date";
    public static final String COL_DOCTOR = "EmployeeName";
    public static final String COL_DATEOUT = "ResultDate";
    public static final String COL_PREDICT = "Predict";
    public static final String COL_PRESCRIPTION = "Prescription";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ResultsMedicalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
        loadData();
    }

    private void loadData() {
        db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME, null);
        //Reset data
        results.clear();
        ResultMedicalRecord result;
        while (cursor.moveToNext()) {
            String patientID = cursor.getString(1);
            String patientName = cursor.getString(2);
            String department = cursor.getString(3);
            String timeIn = cursor.getString(4);
            String doctor = cursor.getString(5);
            String timeOut = cursor.getString(6);
            String predict = cursor.getString(7);
            String prescription = cursor.getString(8);

            result = new ResultMedicalRecord(patientID, patientName, department, timeIn, timeOut, predict, prescription, doctor);
            results.add(result);
        }
        cursor.close();

        if (!results.isEmpty()) {
            LinearLayout linearLayout = findViewById(R.id.linearLayoutResult); // LinearLayout trong layout của bạn

            for (ResultMedicalRecord record : results) {
                LinearLayout resultLayout = new LinearLayout(this);
                resultLayout.setOrientation(LinearLayout.VERTICAL);

                TextView textViewID = new TextView(this);
                textViewID.setText(record.getPatientID());
                resultLayout.addView(textViewID);

                TextView textViewName = new TextView(this);
                textViewName.setText(record.getPatientName());
                resultLayout.addView(textViewName);

                TextView textViewSpecialist= new TextView(this);
                textViewSpecialist.setText(record.getSpecialist());
                resultLayout.addView(textViewSpecialist);

                TextView textViewTimeIn= new TextView(this);
                textViewTimeIn.setText(record.getDateTimeIn());
                resultLayout.addView(textViewTimeIn);

                TextView textViewTimeOut= new TextView(this);
                textViewTimeOut.setText(record.getDateTimeOut());
                resultLayout.addView(textViewTimeOut);

                TextView textViewPredict= new TextView(this);
                textViewPredict.setText(record.getPredict());
                resultLayout.addView(textViewPredict);

                TextView textViewPrescription= new TextView(this);
                textViewPrescription.setText(record.getPrescription());
                resultLayout.addView(textViewPrescription);

                TextView textViewDoctor= new TextView(this);
                textViewDoctor.setText(record.getDoctor());
                resultLayout.addView(textViewDoctor);

                linearLayout.addView(resultLayout);
            }
        }
    }

    private void addEvents () {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsMedical.this, ChooseProfile.class);
                startActivity(intent);
            }
        });

        binding.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsMedical.this, DetailMedicalRecord.class);
                startActivity(intent);
            }
        });
    }
}