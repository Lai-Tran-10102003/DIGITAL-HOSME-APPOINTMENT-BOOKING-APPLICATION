package com.example.Trân.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.Trân.adapter.HealthFormAdapter;
import com.example.Trân.model.HealthForm;
import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.Activity3Binding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    public static final String DB_NAME = "database_db.sqlite";
    public static final String DB_FOLDER = "databases";
    public static SQLiteDatabase db = null;
    public static final String TBL_NAME = "BookingData";
    public static final String COL_PAID = "PatienID";
    public static final String COL_PANAME = "PatienName";
    public static final String COL_DEID = "DepartmentID";
    public static final String COL_DENAME = "DepartmentName";
    public static final String COL_ADDRESSHOS = "AddressHos";
    public static final String COL_QUEUE = "Queue";
    public static final String COL_ROOM = "Room";
    public static final String COL_DATE = "Date";
    public static final String COL_TIME = "Time";
    public static final String COL_STATUS = "Status";

    Activity3Binding binding;
    HealthForm selectedForm = null;
    HealthFormAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = Activity3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
        initAdapter();
    }

    private void initAdapter() {
        List<HealthForm> healthForms = loadDataFromDatabase();
        adapter = new HealthFormAdapter(this, R.layout.item_health_form, healthForms);
        binding.lvHealthForm.setAdapter(adapter);
    }

    private List<HealthForm> loadDataFromDatabase() {
        db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT BookingData.*, PatientProfile.DateofBirth, PatientProfile.Gender, PatientProfile.PhoneNumber FROM BookingData INNER JOIN PatientProfile ON BookingData.PatienID = PatientProfile.PatientID", null);

        List<HealthForm> healthForms = new ArrayList<>();
        while (cursor.moveToNext()) {

            // Tạo một đối tượng HealthForm từ dữ liệu trong Cursor
            HealthForm h = new HealthForm(cursor.getInt(0), cursor.getInt(1),
                    cursor.getString(2), cursor.getString(3).toUpperCase(),
                    cursor.getInt(4), cursor.getString(5),
                    cursor.getString(6), cursor.getInt(7),
                    cursor.getString(8), cursor.getString(9),
                    cursor.getString(10), cursor.getString(11),
                    cursor.getString(12),
                    cursor.getString(13), cursor.getString(14));
            healthForms.add(h);

        }

        // Đóng cursor sau khi sử dụng
        cursor.close();
        return healthForms;
    }


    private void addEvents() {
        binding.lvHealthForm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedForm = (HealthForm) adapter.getItem(position);
                Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
//            Attach data
                if (selectedForm != null){
                    intent.putExtra("data", selectedForm);
                    startActivity(intent);
                }
            }
        });

        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}