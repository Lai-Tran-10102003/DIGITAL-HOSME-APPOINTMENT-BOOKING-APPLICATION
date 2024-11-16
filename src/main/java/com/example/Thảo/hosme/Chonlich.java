package com.example.Thảo.hosme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.Thảo.adapters.hourAdapter;
import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ActivityChonlichBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Chonlich extends AppCompatActivity {
    ActivityChonlichBinding binding;
    List<String> timeSlots;
    hourAdapter adapter;
    boolean isFixedDay;

    public static final String PREFERENCE_NAME = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChonlichBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addEvents();
        getData();
        loadTimeSlots();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//    }

    private void loadTimeSlots() {
        timeSlots = new ArrayList<>();
        timeSlots.add("8:00-9:00");
        timeSlots.add("9:00-10:00");
        timeSlots.add("10:00-11:00");
        timeSlots.add("11:00-12:00");
        timeSlots.add("13:00-14:00");
        timeSlots.add("14:00-15:00");
        timeSlots.add("15:00-16:00");
        timeSlots.add("16:00-17:00");

    }
    private void displayTimeSlots(List<String> timeSlots) {
        if (isFixedDay) {
            adapter = new hourAdapter(Chonlich.this, R.layout.item_hour, timeSlots);
            binding.timeSlotsListView.setAdapter(adapter);

        } else {
            adapter = new hourAdapter(Chonlich.this, R.layout.item_hour, new ArrayList<>());
            binding.timeSlotsListView.setAdapter(adapter);
        }
    }
    String lichKham;
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        Calendar selectedCalendar = Calendar.getInstance();
        selectedCalendar.set(year, month, dayOfMonth);

        String[] daysOfWeek = {"CN", "T2", "T3", "T4", "T5", "T6", "T7"};

        List<String> selectedDaysList = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String selectedDate= dateFormat.format(selectedCalendar.getTime());

        // Lưu ngày đã chọn vào SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("date", String.valueOf(selectedDate));
        editor.apply();

        for (String day : daysOfWeek) {
            if (lichKham.contains(day)) {
                selectedDaysList.add(day);
            }
        }


        String[] selectedDays = selectedDaysList.toArray(new String[0]);

        int dayOfWeek = selectedCalendar.get(Calendar.DAY_OF_WEEK);
        String selectedDay = daysOfWeek[dayOfWeek - 1];
        isFixedDay = false;

        for (String day : selectedDays) {
            if (day.trim().equalsIgnoreCase(selectedDay)) {
                isFixedDay = true;
                break;
            }
        }

        if (!isFixedDay) {
            String selectedDayOfWeek = daysOfWeek[dayOfWeek - 1];
            Toast.makeText(Chonlich.this, "Bác sĩ không có lịch khám " + selectedDayOfWeek, Toast.LENGTH_SHORT).show();
        }
        displayTimeSlots(timeSlots);
    }

    private void getData() {
        Intent intent = getIntent();
        lichKham = intent.getStringExtra("LichKham");
    }

    private void addEvents() {

        Calendar calendar = Calendar.getInstance();
        long today = calendar.getTimeInMillis();

        binding.calendarView.setMinDate(today);

        binding.calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Chonlich.this.onSelectedDayChange(view, year, month, dayOfMonth);
            }
        });

        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}