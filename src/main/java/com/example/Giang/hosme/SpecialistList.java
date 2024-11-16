package com.example.Giang.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.Giang.adapter.SpecialistListAdapter;
import com.example.Giang.model.SpecialistListView;
import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.SpecialistListBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class SpecialistList extends AppCompatActivity {

    SpecialistListBinding binding;
    SpecialistListAdapter adapter;
    ArrayList<SpecialistListView> specialistListViewArrayList;
    //    String selectedDoctorName;
    public static final String DB_NAME = "database_db.sqlite";
    public static final String DB_FOLDER = "/databases";
    public static SQLiteDatabase db = null;
    public static final String TBL_NAME = "Department";
    public static final String COL_DEPARTMENTID = "DepartmentID";
    public static final String COL_DEPARTMENTNAME = "DepartmentName";
    public static final String COL_PRICESERVICE = "PriceService";
    public static final String COL_ADDRESSHOS = "AddressHos";
    public static final String COL_DECRIPTION = "Decription";
    public static final String COL_IMAGE = "Image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SpecialistListBinding.inflate(getLayoutInflater());
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
    private void loadData() {
        db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        Cursor cursor = db.query(TBL_NAME, null, null, null, null, null, null);
        specialistListViewArrayList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String departmentName = cursor.getString(1);
                byte[] imageBytes = cursor.getBlob(5);
                SpecialistListView Specialist = new SpecialistListView(imageBytes, departmentName);
                specialistListViewArrayList.add(Specialist);
            } while (cursor.moveToNext());
        }

        cursor.close();

        if (!specialistListViewArrayList.isEmpty()) {
            adapter = new SpecialistListAdapter(this, R.layout.specialist_information,specialistListViewArrayList);
            binding.lvSpecialist.setAdapter(adapter);
        }
    }

    private void addEvents() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpecialistList.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
