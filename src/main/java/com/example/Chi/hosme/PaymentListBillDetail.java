package com.example.Chi.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ActivityPaymentListBillDetailBinding;

public class PaymentListBillDetail extends AppCompatActivity {

    ActivityPaymentListBillDetailBinding binding;

    private String patientID;
    private String patientName;
    private String departmentName;
    private String addressHos;
    private String createDateTime;
    private double priceService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentListBillDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEventBack();
        payBill();

        showDetailedBill();
    }

    private void showDetailedBill() {

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("patientID")) {
            patientID = intent.getStringExtra("patientID");
            patientName = intent.getStringExtra("patientName");
            departmentName = intent.getStringExtra("departmentName");
            addressHos = intent.getStringExtra("addressHos");
            createDateTime = intent.getStringExtra("createDateTime");
            priceService = intent.getDoubleExtra("priceService", 0);
        }

        // Hiển thị thông tin chi tiết của bệnh nhân
        binding.txtPatientName.setText(patientName);
        binding.txtDepartmentName.setText(departmentName);

        String[] addressparts = addressHos.split(",");
        String address = addressparts[0];
        binding.txtAddressHos.setText("Cơ sở " + address);


        String[] dateTimeParts = createDateTime.split(" "); // Phân tách chuỗi theo khoảng trắng
        if (dateTimeParts.length == 2) {
            String[] dateParts = dateTimeParts[1].split("/");
            if (dateParts.length == 3) {
                String day = dateParts[0]; // Lấy ngày
                String month = dateParts[1]; // Lấy tháng
                String year = dateParts[2]; // Lấy năm
                String formattedDate = day + "/" + month + "/" + year; // Định dạng lại chuỗi ngày tháng năm
                binding.txtCreateDate.setText(formattedDate);
                binding.txtDateTime.setText(formattedDate);
            }
        }

        // Tách giờ từ chuỗi createDateTime
        String[] timeparts = createDateTime.split(" ");
        String time = timeparts[0];
        binding.txtCreateTime.setText(time);
        binding.txtPriceService.setText(String.valueOf(priceService) + " VND");
    }

    private void payBill() {
        binding.btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentListBillDetail.this, PaymentMethods.class);
                startActivity(intent);
            }
        });
    }

    private void addEventBack() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Back screen
                finish();
            }
        });
    }
}