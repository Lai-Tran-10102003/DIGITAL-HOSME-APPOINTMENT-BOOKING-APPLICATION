package com.example.Giang.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.Giang.adapter.DoctorInforAdapter;
import com.example.Giang.model.DoctorInfor;
import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.DoctorListBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DoctorList extends AppCompatActivity {
    DoctorListBinding binding;
   DoctorInforAdapter adapter;
    ArrayList<DoctorInfor> doctorListArrayList;

    public static final String DB_NAME = "database_db.sqlite";
    public static final String DB_FOLDER = "/databases";
    public static SQLiteDatabase db = null;
    public static final String TBL_NAME = "ScheduleData";
    public static final String COL_SHEDULEID = "ScheduleID";
    public static final String COL_EMPLOYEEID = "EmployeeID";
    public static final String COL_EMPLOYEENAME = "EmployeeName";
    public static final String COL_DEPARTMENTID = "DepartmentID";
    public static final String COL_DEPARTMENTNAME = "DepartmentName";
    public static final String COL_POSITION = "Position";
    public static final String COL_ROOM = "Room";
    public static final String COL_SCHEDULE = "Schedule";
    public static final String COL_ADDRESSHOS = "AddressHos";
    public static final String COL_DECRIPTION = "Decription";
    public static final String COL_IMAGE = "Image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DoctorListBinding.inflate(getLayoutInflater());
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
        doctorListArrayList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            String doctorID = cursor.getString(1);
            String doctorName = cursor.getString(2);
            String departmentName = cursor.getString(4);
            String doctorDescription = cursor.getString(9);
            byte[] imageBytes = cursor.getBlob(10);


            DoctorInfor doctor = new DoctorInfor(doctorID, doctorName, departmentName, doctorDescription, imageBytes);
            doctorListArrayList.add(doctor);
        }

            cursor.close();
            adapter = new DoctorInforAdapter(DoctorList.this, R.layout.doctor_mainlist, doctorListArrayList);
            binding.lvDoctor.setAdapter(adapter);
    }

    private void addEvents() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorList.this, MainActivity.class);
                startActivity(intent);
            }
        });

        binding.lvDoctor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DoctorInfor selectedDoctor = (DoctorInfor) adapter.getItem(position);

                Intent intent = new Intent(DoctorList.this, DoctorInfor.class);
                intent.putExtra("DOCTOR_Image", selectedDoctor.getDoctorImage());
                intent.putExtra("DOCTOR_Name", selectedDoctor.getDoctorName());
                intent.putExtra("DOCTOR_DepartmentName", selectedDoctor.getSpecialistName());
                intent.putExtra("DOCTOR_Description", selectedDoctor.getDoctorDescription());
                startActivity(intent);
            }
        });
    }
}