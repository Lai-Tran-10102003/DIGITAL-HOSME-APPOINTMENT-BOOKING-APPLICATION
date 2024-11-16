package com.example.Thảo.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.Chi.hosme.EditProfile;
import com.example.Chi.hosme.PaymentListBill;
import com.example.Chi.hosme.PaymentListBillDetail;
import com.example.Chi.hosme.ProfileDetail;
import com.example.finalproject_hosme.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Xacnhanthongtin extends AppCompatActivity {
    com.example.finalproject_hosme.databinding.ActivityXacnhanthongtinBinding binding;
    public static final String DB_NAME = "database_db.sqlite";
    public static SQLiteDatabase db = null;
    public static final String TBL_NAME = "PatientProfile";
    public static final String COL_USERID = "UserID";
    public static final String COL_PATIENTID = "PatientID";
    public static final String COL_PATIENTNAME = "PatientName";
    public static final String COL_IDCARD = "IDCard";
    public static final String COL_PHONENUMBER = "PhoneNumber";
    public static final String COL_DOB = "DateOfBirth";
    public static final String COL_GENDER = "Gender";
    public static final String COL_ADDRESS = "Address";
    public static final String COL_EMAIL = "Email";

    public static final String TBL_NAME1 = "InvoiceData";
    public static final String INVOICEID = "InvoiceID";
    public static final String BOOKINGID = "BookingID";
    public static final String PATIENTNAME = "PatientName";
    public static final String PATIENTID = "PatienID";
    public static final String DEPARTMENTNAME = "DepartmentName";
    public static final String CREATEDATETIME = "CreateDateTime";
    public static final String PRICESERVICE = "PriceService";
    public static final String STATUS = "Status";


    public static final String TBL_NAME2 = "BookingData";
    public static final String BOOKING_ID = "BookingID";
    public static final String USER_ID = "UserID";
    public static final String PATIENT_ID = "PatienID";
    public static final String PATIENT_NAME = "PatienName";
    public static final String DEPARTMENT_ID = "DepartmentID";
    public static final String DEPARTMENT_NAME = "DepartmentName";
    public static final String FUNCTION = "Function";
    public static final String ADDRESSHOS = "AddressHos";
    public static final String QUEUE = "Queue";
    public static final String ROOM = "Room";
    public static final String DATE = "Date";
    public static final String TIME= "Time";
    public static final String STATUSBOOKING = "Status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.finalproject_hosme.databinding.ActivityXacnhanthongtinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
        loadData();
    }

    private void loadData() {
        db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);

        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        String PatientID = sharedPreferences.getString("patientID", "");

        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME + " WHERE " + COL_PATIENTID + " = ?",
                new String[]{PatientID});

        // Kiểm tra xem có dữ liệu được tìm thấy hay không
        if (cursor.moveToFirst()) {
            String patientName = cursor.getString(2);
            String patientIDCard = cursor.getString(3);
            String patientDOB = cursor.getString(4);
            String patientGender = cursor.getString(5);
            String patientAddress = cursor.getString(6);
            String phoneNumber = cursor.getString(7);
            String patientEmail = cursor.getString(9);

            binding.txtPatientID.setText(PatientID);
            binding.txtPatientName.setText(patientName);
            binding.txtPatientIDCard.setText(patientIDCard);
            binding.txtPhoneNumber.setText(phoneNumber);
            binding.txtPatientDOB.setText(patientDOB);
            binding.txtPatientGender.setText(patientGender);
            binding.txtPatientAddress.setText(patientAddress);
            binding.txtPatientEmail.setText(patientEmail);
        }
        cursor.close();
        db.close();
    }


    private void addEvents() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(Xacnhanthongtin.this);
                dialog.setContentView(R.layout.custom_dialog);

                Button btnCancel = dialog.findViewById(R.id.btnCancel);
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

                Button btnPayment = dialog.findViewById(R.id.btnPayment);
                btnPayment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
                        String currentTime = dateFormat.format(new Date());

                        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("currentDateTime", currentTime);
                        editor.apply();

                        Intent intent = new Intent(Xacnhanthongtin.this, PaymentListBill.class);
                        startActivity(intent);


                        db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);

                        String PatientID = sharedPreferences.getString("patientID", "");
                        String UserID = sharedPreferences.getString("userID", "");
                        String PatientName = sharedPreferences.getString("patientName", "");
                        String DepartmentName = sharedPreferences.getString("chuyen_khoa", "");
                        String Createdatetime = sharedPreferences.getString("currentDateTime", "");
                        String Priceservice = sharedPreferences.getString("gia_dv", "");



                        String savedData2 = sharedPreferences.getString("date", "");
                        String savedData3 = sharedPreferences.getString("time", "");
                        String savedData4  = sharedPreferences.getString("co_so", "");
                        String savedData5  = sharedPreferences.getString("department_id", "");
                        String savedData6  = sharedPreferences.getString("room", "");

                        db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);

                        String bookingIdQuery = "SELECT MAX(" + BOOKING_ID + ") FROM " + TBL_NAME2;
                        Cursor bookingIdCursor = db.rawQuery(bookingIdQuery, null);
                        int lastRowId = 0;
                        if (bookingIdCursor.moveToFirst()) {
                            lastRowId = bookingIdCursor.getInt(0);
                        }
                        bookingIdCursor.close();

                        int nextbookingID= lastRowId + 1 ;

                        editor.putString("bookingID", String.valueOf(nextbookingID));
                        editor.apply();

                        String queueQuery = "SELECT MAX(" + QUEUE + ") FROM " + TBL_NAME2;
                        Cursor queueCursor = db.rawQuery(queueQuery, null);
                        int maxQueueValue = 0;
                        if (queueCursor.moveToFirst()) {
                            maxQueueValue = queueCursor.getInt(0);
                        }
                        queueCursor.close();

                        int nextQueueValue = maxQueueValue + 1;

                        ContentValues values = new ContentValues();
                        values.put(BOOKING_ID, nextbookingID);
                        values.put(USER_ID, UserID);
                        values.put(PATIENT_ID, PatientID);
                        values.put(DEPARTMENT_ID, savedData5);
                        values.put(PATIENT_NAME, PatientName); // Thêm cột "PatientID" vào ContentValues
                        values.put(DEPARTMENT_NAME, DepartmentName);
                        values.put(ADDRESSHOS, savedData4);
                        values.put(DATE, savedData2);
                        values.put(TIME, savedData3);
                        values.put(ROOM, savedData6);
                        values.put(STATUS, "Chưa khám");
                        values.put(QUEUE, nextQueueValue);

                        long result = db.insert(TBL_NAME2, null, values);
                        if (result != -1) {
                            Toast.makeText(Xacnhanthongtin.this, "Successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Xacnhanthongtin.this, "Failed!", Toast.LENGTH_SHORT).show();
                        }



                        String invoiceQuery = "SELECT MAX(" + INVOICEID + ") FROM " + TBL_NAME1;
                        Cursor invoiceCursor = db.rawQuery(invoiceQuery, null);
                        int maxInvoiceValue = 0;
                        if (invoiceCursor.moveToFirst()) {
                            maxInvoiceValue = invoiceCursor.getInt(0);
                        }
                        invoiceCursor.close();

                        int nextInvoiceValue = maxInvoiceValue + 1;

                        String bookingID = sharedPreferences.getString("bookingID", "");

                        ContentValues values1 = new ContentValues();
                        values1.put(INVOICEID, nextInvoiceValue);
                        values1.put(PATIENTID, PatientID);
                        values1.put(BOOKINGID, bookingID);
                        values1.put(PATIENTNAME, PatientName);
                        values1.put(DEPARTMENTNAME, DepartmentName);
                        values1.put(CREATEDATETIME, Createdatetime);
                        values1.put(PRICESERVICE, Priceservice);
                        values1.put(STATUS, "Chưa thanh toán");
                        // Thêm các giá trị khác vào ContentValues

                        long result1 = db.insert(TBL_NAME1, null, values1);
                        if (result1 != -1) {
                            Toast.makeText(Xacnhanthongtin.this, "Successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Xacnhanthongtin.this, "Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
    }
}