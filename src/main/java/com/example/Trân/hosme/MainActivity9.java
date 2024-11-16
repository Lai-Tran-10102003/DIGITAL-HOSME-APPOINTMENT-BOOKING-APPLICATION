package com.example.Trân.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.Trân.adapter.QuestionAdapter;
import com.example.Trân.model.Question;
import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.Activity9Binding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity9 extends AppCompatActivity {
    Activity9Binding binding;

    public static final String DB_NAME = "questiondata.sqlite";
    public static final String DB_FOLDER = "databases";
    public static SQLiteDatabase db = null;
    public static final String TBL_NAME = "QueData";
    public static final String COL_CODE = "QuestionCode";
    public static final String COL_TITLE = "QuestionTitle";
    public static final String COL_ANSWER = "QuestionAnswer";

    Question selectedQue = null;
    QuestionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = Activity9Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        copyDB();
        addEvents();
        initAdapter();
    }

    private void initAdapter() {
        List<Question> questions = loadDataFromDatabase();
        adapter = new QuestionAdapter(this, R.layout.item_question, questions);
        binding.lvQue.setAdapter(adapter);
    }

    private List<Question> loadDataFromDatabase() {
        db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME, null);

        List<Question> questions = new ArrayList<>();
        while (cursor.moveToNext()) {

            // Tạo một đối tượng HealthForm từ dữ liệu trong Cursor
            Question q = new Question(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2));
            questions.add(q);

        }

        // Đóng cursor sau khi sử dụng
        cursor.close();
        return questions;
    }

    private void addEvents() {

        binding.lvQue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedQue = (Question) adapter.getItem(position);
                Intent intent = new Intent(MainActivity9.this, DetailQues.class);
//            Attach data
                if (selectedQue != null){
                    intent.putExtra("data", selectedQue);
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

    private void copyDB() {
        File dbFile = getDatabasePath(DB_NAME);
        if(!dbFile.exists()){
            //Copy
            if (copyDbFromAssets())
                Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Fail!", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean copyDbFromAssets(){
        String dbPath = getApplicationInfo().dataDir + "/" + DB_FOLDER + "/" + DB_NAME;
        //data/data/packageName/product_db.db
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