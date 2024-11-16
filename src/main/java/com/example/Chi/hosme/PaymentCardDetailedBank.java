package com.example.Chi.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.Chi.models.Bank;
import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ActivityPaymentCardDetailedBankBinding;

public class PaymentCardDetailedBank extends AppCompatActivity {

    ActivityPaymentCardDetailedBankBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentCardDetailedBankBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addEventBack();
        displayCardImage();
    }

    private void displayCardImage() {
        Intent intent = getIntent();
        if (intent != null) {
            Bank selectedBank = (Bank) intent.getSerializableExtra("bank");
            if (selectedBank != null) {
                int bankCardImage = selectedBank.getBankCardImage();
                binding.imvBankCardImage.setImageResource(bankCardImage);
            }
        }
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