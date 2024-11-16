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

import com.example.Giang.adapter.DoctorInforAdapter;
import com.example.Giang.model.DoctorInfor;
import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.DoctorInformationBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DoctorDescription extends AppCompatActivity {
    DoctorInformationBinding binding;
    DoctorInforAdapter adapter;
    ArrayList<DoctorInfor> doctorInfors;
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
        binding = DoctorInformationBinding.inflate(getLayoutInflater());
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
        doctorInfors = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String doctorID = cursor.getString(1);
                String doctorName = cursor.getString(2);
                String departmentName = cursor.getString(4);
                String description = cursor.getString(8);
                byte[] imageBytes = cursor.getBlob(9);

                DoctorInfor doctor = new DoctorInfor(doctorID, doctorName, departmentName, description, imageBytes);
                doctorInfors.add(doctor);
            } while (cursor.moveToNext());
        }

        cursor.close();

        if (!doctorInfors.isEmpty()) {
            LinearLayout linearLayout = findViewById(R.id.linearLayoutDoctorInfor); // LinearLayout trong layout của bạn

            for (DoctorInfor doctorInfo : doctorInfors) {
                // Tạo một LinearLayout mới để chứa thông tin của mỗi bác sĩ
                LinearLayout doctorLayout = new LinearLayout(this);
                doctorLayout.setOrientation(LinearLayout.VERTICAL);

                // Tạo một ImageView để hiển thị ảnh của mỗi bác sĩ
                ImageView imageView = new ImageView(this);
                Bitmap bitmap = BitmapFactory.decodeByteArray(doctorInfo.getDoctorImage(), 0, doctorInfo.getDoctorImage().length);
                imageView.setImageBitmap(bitmap);
                doctorLayout.addView(imageView); // Thêm ImageView vào LinearLayout

                // Tạo một TextView để hiển thị tên của mỗi bác sĩ
                TextView textViewName = new TextView(this);
                textViewName.setText(doctorInfo.getDoctorName());
                doctorLayout.addView(textViewName); // Thêm TextView vào LinearLayout

                // Tạo một TextView để hiển thị mô tả của mỗi bác sĩ
                TextView textViewDescription = new TextView(this);
                textViewDescription.setText(doctorInfo.getDoctorDescription());
                doctorLayout.addView(textViewDescription); // Thêm TextView vào LinearLayout

                linearLayout.addView(doctorLayout); // Thêm LinearLayout chứa thông tin của mỗi bác sĩ vào LinearLayout chính
            }

        }
    }

    private void addEvents() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorDescription.this, DoctorList.class);
                startActivity(intent);
            }
        });
    }
}
