package com.example.Trân;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.example.finalproject_hosme.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class loaddata extends AppCompatActivity {
    public static final String DB_NAME = "database_db.sqlite";
    public static final String DB_FOLDER = "/databases";
    public static SQLiteDatabase db = null;
    public static final String TBL_NAME = "Department";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loaddata);
        copyDb();
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


}