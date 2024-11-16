package com.example.Giang.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Giang.adapter.SpecialistInforAdapter;
import com.example.Giang.model.SpecialistInfor;
import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.SpecialistDescriptionBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class SpecialistDescription extends AppCompatActivity {
    SpecialistDescriptionBinding binding;
    SpecialistInforAdapter adapter;
    ArrayList<SpecialistInfor> specialistInfors;
    public static final String DB_NAME = "database_db.sqlite";
    public static final String DB_FOLDER = "/databases";
    public static SQLiteDatabase db = null;
    public static final String TBL_NAME = "Department";
    public static final String COL_DEPARTMENTID = "DepartmentID";
    public static final String COL_DEPARTMENTNAME = "DepartmentName";
    public static final String COL_PRICE = "PriceService";
    public static final String COL_ADDRESS = "AddressHos";
    public static final String COL_DECRIPTION = "Decription";
    public static final String COL_IMAGE = "Image";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SpecialistDescriptionBinding.inflate(getLayoutInflater());
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
        specialistInfors = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String specialistName = cursor.getString(1);
                String description = cursor.getString(4);
                byte[] imageBytes = cursor.getBlob(5);

                SpecialistInfor specialist = new SpecialistInfor(specialistName, description, imageBytes);
                specialistInfors.add(specialist);
            } while (cursor.moveToNext());
        }

        cursor.close();

        if (!specialistInfors.isEmpty()) {
            LinearLayout linearLayout = findViewById(R.id.linearLayoutSpecialistInfor); // LinearLayout trong layout của bạn

            for (SpecialistInfor specialistInfor : specialistInfors) {
                LinearLayout specialistLayout = new LinearLayout(this);
                specialistLayout.setOrientation(LinearLayout.VERTICAL);

                ImageView imageView = new ImageView(this);
                Bitmap bitmap = BitmapFactory.decodeByteArray(specialistInfor.getSpecialistImage(), 0, specialistInfor.getSpecialistImage().length);
                imageView.setImageBitmap(bitmap);
                specialistLayout.addView(imageView);

                TextView textViewName = new TextView(this);
                textViewName.setText(specialistInfor.getSpecialistName());
                specialistLayout.addView(textViewName);

                TextView textViewDescription = new TextView(this);
                textViewDescription.setText(specialistInfor.getSpecialistDescription());
                specialistLayout.addView(textViewDescription); // Thêm TextView vào LinearLayout

                linearLayout.addView(specialistLayout); // Thêm LinearLayout chứa thông tin của mỗi bác sĩ vào LinearLayout chính
            }

        }
    }

    private void addEvents() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpecialistDescription.this, SpecialistList.class);
                startActivity(intent);
            }
        });
    }
}
