package com.example.Thảo.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Thảo.models.Chuyenkhoa;
import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ActivityChoncosoyteBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Choncosoyte extends AppCompatActivity {
    ActivityChoncosoyteBinding binding;
    public static final String DB_NAME = "database_db.sqlite";
    public static final String DB_FOLDER = "/databases";
    public static SQLiteDatabase db = null;
    public static final String TBL_NAME = "Department";
    public static final String COL_ID = "DepartmentId";
    public static final String COL_NAME = "DepartmentName";
    public static final String COL_PRICE = "PriceService";
    public static final String COL_ADDRESSHOS = "AddressHos";
    public static final String COL_DECRIPTION = "Decription";
    public static final String COL_IMAGE = "Image";

    public static final String PREFERENCE_NAME = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChoncosoyteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        copyDb();
        loadData();
        addEvents();

    }

//    private void loadData() {
//        binding.txtcoso4.setText(binding.txtcoso1.getText());
//    }

    //    Department d;
    Chuyenkhoa k;
    private void loadData() {
        db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);

        List<TextView> txtcosoList = new ArrayList<>();
        txtcosoList.add(binding.txtcoso1);
        txtcosoList.add(binding.txtcoso2);
        txtcosoList.add(binding.txtcoso3);


        List<List<Chuyenkhoa>> validValuesList = new ArrayList<>();

        for (int i = 0; i < txtcosoList.size(); i++) {
            TextView txtcoso = txtcosoList.get(i);

            Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME + " WHERE " + COL_ADDRESSHOS + " LIKE ? ", new String[]{txtcoso.getText().toString()});

            List<Chuyenkhoa> validValues = new ArrayList<>();
            while (cursor.moveToNext()) {
                k = new Chuyenkhoa(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
                validValues.add(k);
            }
            cursor.close();

            validValuesList.add(validValues);

            View.OnClickListener onClickListener1 = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String selectedCoso = binding.txtcoso1.getText().toString();
                    SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("co_so", selectedCoso);
                    editor.apply();

                    List<Chuyenkhoa> selectedChuyenKhoaList = validValuesList.get(0);

                    Intent intent = new Intent(getApplicationContext(), Chonthongtinkham3.class);
                    intent.putExtra("chuyenKhoaList", (ArrayList<Chuyenkhoa>) selectedChuyenKhoaList);
                    startActivity(intent);
                }
            };
            View.OnClickListener onClickListener2 = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String selectedCoso = binding.txtcoso2.getText().toString();
                    SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("co_so", selectedCoso);
                    editor.apply();

                    List<Chuyenkhoa> selectedChuyenKhoaList = validValuesList.get(1);

                    Intent intent = new Intent(getApplicationContext(), Chonthongtinkham3.class);
                    intent.putExtra("chuyenKhoaList", (ArrayList<Chuyenkhoa>) selectedChuyenKhoaList);
                    startActivity(intent);
                }
            };
            View.OnClickListener onClickListener3 = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String selectedCoso = binding.txtcoso3.getText().toString();
                    SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("co_so", selectedCoso);
                    editor.apply();

                    List<Chuyenkhoa> selectedChuyenKhoaList = validValuesList.get(2);

                    Intent intent = new Intent(getApplicationContext(), Chonthongtinkham3.class);
                    intent.putExtra("chuyenKhoaList", (ArrayList<Chuyenkhoa>) selectedChuyenKhoaList);
                    startActivity(intent);
                }
            };

            binding.containerCoso.setOnClickListener(onClickListener1);
            binding.btnBooking.setOnClickListener(onClickListener1);
            binding.containerCoso1.setOnClickListener(onClickListener2);
            binding.btnBooking1.setOnClickListener(onClickListener2);
            binding.containerCoso2.setOnClickListener(onClickListener3);
            binding.btnBooking2.setOnClickListener(onClickListener3);

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


    private void addEvents() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Choncosoyte.this, DetailCoso1.class);
                startActivity(intent);
            }
        });

        binding.btnDetail1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Choncosoyte.this, DetailCoso2.class);
                startActivity(intent);
            }
        });

        binding.btnDetail2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Choncosoyte.this, DetailCoso3.class);
                startActivity(intent);
            }
        });

    }
}
