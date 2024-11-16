package com.example.Giang.hosme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.Chi.hosme.ListProfiles;
import com.example.Chi.hosme.PaymentListBill;
import com.example.Giang.adapter.DoctorAdapter;
import com.example.Giang.adapter.FunctionAdapter;
import com.example.Giang.adapter.NewAdapter;
import com.example.Giang.adapter.SpecialistAdapter;
import com.example.Giang.model.Doctor;
import com.example.Giang.model.Function;
import com.example.Giang.model.New;
import com.example.Giang.model.Specialist;
import com.example.Hân.hosme.thongbao;
import com.example.Thảo.hosme.Chonhoso;
import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ActivityMain2Binding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMain2Binding binding;
    FunctionAdapter adapter;
    ArrayList<Function> functions;
    SpecialistAdapter specialistAdapter;
    ArrayList<Specialist> specialist;
    DoctorAdapter doctorAdapter;
    ArrayList<Doctor> doctors;
    NewAdapter newAdapter;
    ArrayList<New> news;

    public static final String DB_NAME = "database_db.sqlite";
    public static final String DB_FOLDER = "/databases";
    public static SQLiteDatabase db = null;
    public static final String TBL_NAME = "PatientProfile";
    public static final String USER_ID = "UserID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();
        loadData();
        addEvents();

        initDataSpecialist();
        loadDataSpecialist();
        addEventsSpecialist();

        initDataDoctor();
        loadDataDoctor();
        addEventsDoctor();

        customAndLoadData();

        addEventsOpenHome();
    }

    private void addEventsOpenHome() {
        binding.btnDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DoctorList.class);
                startActivity(intent);
            }
        });

        binding.btnSpecialist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SpecialistList.class);
                startActivity(intent);
            }
        });

        binding.btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewList.class);
                startActivity(intent);
            }
        });

    }

    private void customAndLoadData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.rvNew.setLayoutManager(layoutManager);
        binding.rvNew.setHasFixedSize(true);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.rvNew.getContext(), DividerItemDecoration.HORIZONTAL);
        binding.rvNew.addItemDecoration(dividerItemDecoration);
        binding.rvNew.setItemAnimator(new DefaultItemAnimator());

        news = new ArrayList<>();
        news.add(new New(R.drawable.new_1_1, "Đầm ấm Tết cổ truyền", "Lai Thị Bảo Trân", "11/04/2024"));
        news.add(new New(R.drawable.new_2_1, "Chung kết Miss Hypa 2024", "Nguyễn Ngọc Mai Chi", "09/01/2024"));
        news.add(new New(R.drawable.new_3_1, "Sinh hoạt cộng đồng", "Hoàng Thanh Giang", "07/01/2024"));
        news.add(new New(R.drawable.new_4_1, "Hội thi văn hóa 2023", "Nguyễn Huỳnh Mai Hân", "01/01/2024"));

        newAdapter = new NewAdapter(getApplicationContext(), news);
        binding.rvNew.setAdapter(newAdapter);
    }

    private void addEventsDoctor() {
        binding.gvDoctor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Doctor selectedSpecialist = (Doctor) doctorAdapter.getItem(position);
            }
        });
    }

    private void loadDataDoctor() {
        initDataDoctor();
        doctorAdapter = new DoctorAdapter(this, R.layout.doctor, doctors);
        binding.gvDoctor.setAdapter(doctorAdapter);

    }

    private void initDataDoctor() {
        doctors = new ArrayList<>();
        doctors.add(new Doctor(R.drawable.saumot_trannhutthianhphuong, "Trần Nhựt Thị Ánh Phương"));
        doctors.add(new Doctor(R.drawable.saunam_phamkienhuu, "Phạm Kiên Hữu"));
        doctors.add(new Doctor(R.drawable.sausau_votranthanhnhan, "Võ Trần Thành Nhân"));
        doctors.add(new Doctor(R.drawable.sautam_nguyenthithuha, "Nguyễn Thị Thu Hà"));
    }

    private void addEventsSpecialist() {
        binding.gvSpecialist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Specialist selectedSpecialist = (Specialist) specialistAdapter.getItem(position);
            }
        });
    }

    private void loadDataSpecialist() {
        initDataSpecialist();
        specialistAdapter = new SpecialistAdapter(this, R.layout.specialist, specialist);
        binding.gvSpecialist.setAdapter(specialistAdapter);

    }

    private void initDataSpecialist() {
        specialist = new ArrayList<>();
        specialist.add(new Specialist(R.drawable.icon_ngoaitonghop, "Khoa ngoại"));
        specialist.add(new Specialist(R.drawable.icon_chuyenkhoadinhduong, "Dinh dưỡng"));
        specialist.add(new Specialist(R.drawable.khoa_dalieu, "Da liễu"));
        specialist.add(new Specialist(R.drawable.khoa_nhi, "Khoa nhi"));
        specialist.add(new Specialist(R.drawable.khoa_noihohap, "Hô hấp"));
        specialist.add(new Specialist(R.drawable.khoa_noitiet, "Nội tiết"));
        specialist.add(new Specialist(R.drawable.khoa_ranghammat, "Răng hàm mặt"));
        specialist.add(new Specialist(R.drawable.khoa_sanphukhoa, "Phụ khoa"));
        specialist.add(new Specialist(R.drawable.icon_tieuhoa, "Tiêu hóa"));
        specialist.add(new Specialist(R.drawable.khoa_taimuihong, "Tai mũi họng"));
    }

    private void addEvents() {
        binding.grMainFunction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Function selectedFunction = (Function) adapter.getItem(position);
            }
        });

        binding.grMainFunction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Function selectedFunction = (Function) adapter.getItem(position);

                if (selectedFunction.getFunctionName().equals("Tư vấn")) {
                    Intent intent = new Intent(MainActivity.this, com.example.Trân.hosme.MainActivity2.class);
                    startActivity(intent);
                } else if (selectedFunction.getFunctionName().equals("Phiếu khám")) {
                    Intent intent = new Intent(MainActivity.this, com.example.Trân.hosme.MainActivity3.class);
                    startActivity(intent);
                } else if (selectedFunction.getFunctionName().equals("Thanh toán")) {
                    Intent intent = new Intent(MainActivity.this, PaymentListBill.class);
                    startActivity(intent);
                } else if (selectedFunction.getFunctionName().equals("Đặt khám")) {
                    Intent intent = new Intent(MainActivity.this, Chonhoso.class);
                    startActivity(intent);
                }
            }
        });

        binding.lnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.Trân.hosme.MainActivity.class);
                startActivity(intent);
            }
        });

        binding.lnRing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, thongbao.class);
                startActivity(intent);
            }
        });

//        binding.lnPersonalInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
//
//                SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
//                String userID = sharedPreferences.getString("userId", "");
//
//                Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME + " WHERE " + USER_ID + " = ?", new String[]{userID});
//
//                if (cursor.moveToFirst()) {
//                    int userIdIndex = cursor.getColumnIndex(USER_ID);
//                    String userIdFromDatabase = cursor.getString(userIdIndex);
//
//                    if (userID.equals(userIdFromDatabase)) {
//                        Intent intent = new Intent(MainActivity.this, ListProfiles.class);
//                        startActivity(intent);
//                    } else {
//                        Intent intent = new Intent(MainActivity.this, com.example.Chi.hosme.MainActivity.class);
//                        startActivity(intent);
//                    }
//                }
//
//                cursor.close();
//            }
//        });
        binding.lnPersonalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);

                SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                String userID = sharedPreferences.getString("userId", "");

                Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME + " WHERE " + USER_ID + " = ?", new String[]{userID});

                if (cursor.moveToFirst()) {
                    int userIdIndex = cursor.getColumnIndex(USER_ID);
                    String userIdFromDatabase = cursor.getString(userIdIndex);

                    if (userID.equals(userIdFromDatabase)) {
                        Intent intent = new Intent(MainActivity.this, ListProfiles.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(MainActivity.this, com.example.Chi.hosme.MainActivity.class);
                        startActivity(intent);
                    }
                } else {
                    Intent intent = new Intent(MainActivity.this, com.example.Chi.hosme.MainActivity.class);
                    startActivity(intent);
                }

                cursor.close();
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        String userName = sharedPreferences.getString("userName", "");

        binding.txtNameUser.setText(userName);

    }

    private void loadData() {
        initData();
        adapter = new FunctionAdapter(MainActivity.this, R.layout.main_function, functions);
        binding.grMainFunction.setAdapter(adapter);
    }

    private void initData() {
        functions = new ArrayList<>();
        functions.add(new Function(R.drawable.icon_calendar, "Đặt khám"));
        functions.add(new Function(R.drawable.icon_findtask, "Đặt xét nghiệm"));
        functions.add(new Function(R.drawable.icon_document, "Hồ sơ bệnh án"));
        functions.add(new Function(R.drawable.icon_phone, "Thanh toán"));
        functions.add(new Function(R.drawable.icon_checktask, "Phiếu khám"));
        functions.add(new Function(R.drawable.icon_idcard, "Tư vấn"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity", "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity", "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity", "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MainActivity", "onRestart");
    }
}
