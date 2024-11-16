package com.example.Giang.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.Giang.adapter.ProfileAdapter;
import com.example.Giang.model.Profile;
import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ChooseProfileListBinding;

import java.util.ArrayList;

public class ChooseProfile extends AppCompatActivity {
    ChooseProfileListBinding binding;
    ArrayList<Profile> profiles = new ArrayList<>();
    Profile selectedprofile = null;

    public static final String DB_NAME = "database_db.sqlite";
    public static final String DB_FOLDER = "/databases";
    public static SQLiteDatabase db = null;
    public static final String TBL_NAME = "PatientProfile";
    public static final String COL_PATIENTID = "PatientID";
    public static final String COL_PATIENTNAME = "PatientName";
    public static final String COL_PHONENUMBER = "PhoneNumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ChooseProfileListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
        loadData();
    }

    private void loadData() {
        db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME, null );
        //Reset data
        profiles.clear();
        Profile profile;
        while (cursor.moveToNext()) {
            String patientID = cursor.getString(1);
            String patientName = cursor.getString(2);
            String phoneNumber = cursor.getString(7);

            profile = new Profile(patientID, patientName, phoneNumber);
            profiles.add(profile);
        }
        cursor.close();

        ProfileAdapter adapter = new ProfileAdapter(this, R.layout.choose_profile, profiles);
        binding.lvProfile.setAdapter(adapter);
    }

    private void addEvents() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseProfile.this, MainActivity.class);
                startActivity(intent);
            }
        });

        binding.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseProfile.this, EnterDate.class);
                startActivity(intent);
            }
        });
    }
}