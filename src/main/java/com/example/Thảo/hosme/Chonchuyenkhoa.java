package com.example.Thảo.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.Thảo.adapters.ChuyenKhoaAdapter;
import com.example.Thảo.models.Bacsi;
import com.example.Thảo.models.Chuyenkhoa;
import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ActivityChonchuyenkhoaBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class Chonchuyenkhoa extends AppCompatActivity {
    ActivityChonchuyenkhoaBinding binding;
    ChuyenKhoaAdapter chuyenKhoaArrayAdapter;
    ArrayList<Chuyenkhoa> chuyenKhoas;
    String selectedChuyenKhoaName;
    String selectedprice;
    ArrayList<Bacsi> validValues;

    public static final String DB_NAME = "database_db.sqlite";
    public static final String DB_FOLDER = "/databases";
    public static SQLiteDatabase db = null;
    public static final String TBL_NAME = "ScheduleData";
    public static final String COL_SCHEDULEID = "ScheduleID";
    public static final String COL_EMPLOYEEID = "EmployeeID";
    public static final String COL_EMPLOYEENAME = "EmployeeName";
    public static final String COL_DEPARTMENTID = "DepartmentID";
    public static final String COL_DEPARTMENTNAME = "DepartmentName";
    public static final String COL_ROOM= "Room";
    public static final String COL_POSITION = "Position";
    public static final String COL_SCHEDULE = "Schedule";
    public static final String COL_ADDRESSHOS = "AddressHos";
    public static final String COL_DECRIPTION = "Decription";
    public static final String COL_IMAGE = "Image";

    public static final String PREFERENCE_NAME = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChonchuyenkhoaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        getData();
        copyDb();
        addEvents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
        clearValidValues();

//        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
//        String savedChuyenKhoa = sharedPreferences.getString("chuyen_khoa", "");
//        // Sử dụng giá trị đã lưu
//        if (!savedChuyenKhoa.isEmpty()) {
//            selectedChuyenKhoaName = savedChuyenKhoa;
//            loadData();
//        }
    }

    private void clearValidValues() {
        if (validValues != null) {
            validValues.clear();
        }
    }

    private void loadData() {
        Bacsi b;
        db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        if (selectedChuyenKhoaName != null) {

            Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME + " WHERE " + COL_DEPARTMENTNAME + " LIKE ? ", new String[]{selectedChuyenKhoaName});

            validValues = new ArrayList<>();

            while (cursor.moveToNext()) {
                @SuppressLint("Range") int departmentID = cursor.getInt(cursor.getColumnIndex(COL_DEPARTMENTID));
                @SuppressLint("Range") String Room = cursor.getString(cursor.getColumnIndex(COL_ROOM));
                b = new Bacsi(cursor.getInt(1), cursor.getString(2), cursor.getString(7));
                validValues.add(b);

                saveDepartmentIDToSharedPreferences(departmentID);
                saveRoomToSharedPreferences(Room);
            }

            cursor.close();

            binding.lvChuyenKhoa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Bacsi selectedBacsi = validValues.get(position);

                    Intent intent = new Intent(getApplicationContext(), Chonbacsi.class);
                    intent.putParcelableArrayListExtra("BacSiList", (ArrayList<Bacsi>) validValues);
                    startActivity(intent);
                }
            });
        }

        // Lưu giá trị xuống SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("chuyen_khoa", selectedChuyenKhoaName);
        editor.putString("gia_dv", String.valueOf(selectedprice));
        editor.apply();

    }

    private void saveDepartmentIDToSharedPreferences(int departmentID) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("department_id", String.valueOf(departmentID));
        editor.apply();
    }

    private void saveRoomToSharedPreferences(String room) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("room", String.valueOf(room));
        editor.apply();
    }

    private void addEvents() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                chuyenKhoaArrayAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void getData() {
        Intent intent = getIntent();
        if (intent != null) {
            ArrayList<Chuyenkhoa> chuyenKhoaList = intent.getParcelableArrayListExtra("chuyenKhoaList");

            if (chuyenKhoaList != null) {
                chuyenKhoas = new ArrayList<>(chuyenKhoaList);
                chuyenKhoaArrayAdapter = new ChuyenKhoaAdapter(Chonchuyenkhoa.this, R.layout.item_layout, chuyenKhoaList);
                binding.lvChuyenKhoa.setAdapter(chuyenKhoaArrayAdapter);

                binding.lvChuyenKhoa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        Chuyenkhoa selectedChuyenKhoa = (Chuyenkhoa) chuyenKhoaArrayAdapter.getItem(position);
                        int originalPosition = chuyenKhoas.indexOf(selectedChuyenKhoa);

                        Chuyenkhoa originalSelectedChuyenKhoa = chuyenKhoas.get(originalPosition);

                        selectedChuyenKhoaName = originalSelectedChuyenKhoa.getChuyenKhoaName();
                        selectedprice = String.valueOf(selectedChuyenKhoa.getPriceService());
                        loadData();
                    }
                });
            }
        }
    }


    private void copyDb() {
        File dbFile = getDatabasePath(DB_NAME);
        if(!dbFile.exists()){
            if(copyDbFromAssets())
                Toast.makeText(this, "Sucess!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Fail!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean copyDbFromAssets() {
        String dbPath = getApplicationInfo().dataDir + "/" + DB_FOLDER + "/" + DB_NAME;
        try{
            InputStream inputStream = getAssets().open(DB_NAME);
            File file = new File(getApplicationInfo().dataDir + "/" + DB_FOLDER + "/");
            if(!file.exists()){
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
//    @Override
//    protected void onResume() {
//        super.onResume();
//        loadDb();
//    }
//    private void loadDb() {
//        db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
////        Cursor cursor = db.query(TBL_NAME, null, COL_ADDRESSHOS + " LIKE ? ", new String[]{"%215 Hồng Bàng, Phường 11, Quận 5,TP.HCM%"}, null, null, null);
//        Cursor cursor = db.query(TBL_NAME, null, null, null, null, null, null);
//        chuyenKhoaArrayAdapter.clear();
//
//        Chuyenkhoa k;
//        while (cursor.moveToNext()) {
//            k = new Chuyenkhoa(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2));
//            chuyenKhoaArrayAdapter.add(k);
//        }
//        cursor.close();

}