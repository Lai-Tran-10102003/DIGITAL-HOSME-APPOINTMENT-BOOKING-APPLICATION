package com.example.Hân.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.Hân.adapter.NoAdapter;
import com.example.Hân.model.Notifications;
import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ActivityThongbaoBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class thongbao extends AppCompatActivity {
    ActivityThongbaoBinding binding;
    public static final String DB_NAME = "notificationdata.sqlite";
    public static final String DB_FOLDER = "databases";
    public static final String TBL_NAME;

    static {
        TBL_NAME = "NoData";
    }

    public static SQLiteDatabase db = null;
    public static final String COL_ID = "NoCode";
    public static final String COL_TITLE = "NoTitle";
    public static final String COL_DES = "NoDescription";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThongbaoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        copyData();
        addEvents();
        initAdapter();


    }

    private void initAdapter() {
        List<Notifications> notifications = loadDataFromDatabase();
        NoAdapter adapter = new NoAdapter(thongbao.this, R.layout.item_tb, notifications);
        binding.lvNo.setAdapter(adapter);
    }

    private List<Notifications> loadDataFromDatabase() {
        db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        Cursor cursor;
        String query = "SELECT * FROM " + TBL_NAME;
        cursor = db.rawQuery(query, null);

        List<Notifications> notifications = new ArrayList<>();
        Notifications n;
        while (cursor.moveToNext()) {

            // Tạo một đối tượng HealthForm từ dữ liệu trong Cursor

            n = new Notifications(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            notifications.add(n);
        }

        // Đóng cursor sau khi sử dụng
        cursor.close();
        return notifications;
    }

    private void addEvents() {

        binding.lvNo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NoAdapter adapter = (NoAdapter) parent.getAdapter();
                Notifications selectedNo = (Notifications) adapter.getItem(position);
                if (selectedNo != null) {
                    Intent intent = new Intent(thongbao.this, DetailNotification.class);
                    intent.putExtra("data", selectedNo);
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
    private void copyData() {
        File dbFile = getDatabasePath(DB_NAME);
        if(!dbFile.exists()){
            //Copy
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
            File f = new File(getApplicationInfo().dataDir + "/" + DB_FOLDER + "/");
            if (!f.exists()) {
                f.mkdir();
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
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }

    }
}