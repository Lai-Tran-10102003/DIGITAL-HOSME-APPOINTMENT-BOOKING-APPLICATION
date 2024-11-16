package com.example.Chi.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import com.example.Chi.adapters.ProfileAdapter;
import com.example.Chi.models.Profile;
import com.example.Giang.hosme.MainActivity;
import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ActivityListProfilesBinding;

import java.util.ArrayList;

public class ListProfiles extends AppCompatActivity {

    ActivityListProfilesBinding binding;

    public static final String DB_NAME = "database_db.sqlite";
    public static SQLiteDatabase db = null;
    public static final String TBL_NAME = "PatientProfile";
    public static final String COL_USERID = "UserID";

    ArrayList<Profile> profiles = new ArrayList<>();
    ProfileAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListProfilesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEventBack();
        loadDB();
        addEventProfile();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDB();
    }

    private void addEventProfile() {
        binding.linearAddInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Nevigate after choosing "addInfo"
                showaDialog();
            }
        });
    }

    private void showaDialog() {
        Dialog dialog = new Dialog(ListProfiles.this);
        dialog.setContentView(R.layout.activity_dialog_add_profile);

        Button btnOldProfile, btnNewProfile;
        btnOldProfile = dialog.findViewById(R.id.btnOldProfile);
        btnOldProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Nevigate enter code
                Intent intent = new Intent(ListProfiles.this, EnterCode.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        btnNewProfile = dialog.findViewById(R.id.btnNewProfile);
        btnNewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Nevigate to "ADD INFO by manual"
                Intent intent = new Intent(ListProfiles.this, AddProfile1.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT); //chinh kich thuoc dialog
        dialog.getWindow().setGravity(Gravity.BOTTOM); // dua dialog xuong duoi
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //loai bo bg trang
    }

    private void addEventBack() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Back home screen
                Intent intent = new Intent(ListProfiles.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void loadDB() {
        db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        String userID = sharedPreferences.getString("userId", "");
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME + " WHERE " + COL_USERID + " LIKE ?",
                new String[]{userID});

        profiles.clear();
        Profile profile;
        while (cursor.moveToNext()) {
            String patientID = cursor.getString(1);
            String patientName = cursor.getString(2);
            String patientIDCard = cursor.getString(3);
            String patientDOB = cursor.getString(4);
            String patientGender = cursor.getString(5);
            String patientAddress = cursor.getString(6);
            String phoneNumber = cursor.getString(7);
            String patientEmail = cursor.getString(9);

            profile = new Profile(patientID, patientName, phoneNumber, patientIDCard, patientDOB, patientGender, patientAddress, patientEmail);
            profiles.add(profile);
        }
        cursor.close();

        adapter = new ProfileAdapter(this, R.layout.profile_layout, profiles);
        binding.lvProfile.setAdapter(adapter);
        addEventClickProfile();
    }

    private void addEventClickProfile() {
        binding.lvProfile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Profile selectedProfile = (Profile) adapter.getItem(position);

                Intent intent = new Intent(ListProfiles.this, ProfileDetail.class);

                intent.putExtra("patientID", selectedProfile.getPatientID());
                intent.putExtra("patientName", selectedProfile.getPatientName());
                intent.putExtra("phoneNumber", selectedProfile.getPhoneNumber());
                intent.putExtra("patientIDCard", selectedProfile.getPatientIDCard());
                intent.putExtra("patientDOB", selectedProfile.getPatientDOB());
                intent.putExtra("patientGender", selectedProfile.getPatientGender());
                intent.putExtra("patientAddress", selectedProfile.getPatientAddress());
                intent.putExtra("patientEmail", selectedProfile.getPatientEmail());
                startActivity(intent);
            }
        });
    }



}