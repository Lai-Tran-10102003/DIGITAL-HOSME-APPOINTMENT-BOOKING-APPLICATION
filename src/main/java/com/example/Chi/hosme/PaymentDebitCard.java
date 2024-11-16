package com.example.Chi.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.Chi.adapters.BankAdapter;
import com.example.Chi.models.Bank;
import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ActivityPaymentDebitCardBinding;

import java.util.ArrayList;

public class PaymentDebitCard extends AppCompatActivity {

    ActivityPaymentDebitCardBinding binding;
    BankAdapter adapter;
    ArrayList<Bank> banks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentDebitCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();
        addEventBack();
        loadData();
        addEventClickBank();
    }

    private void initData() {
        banks = new ArrayList<>();

        banks.add(new Bank(R.drawable.logo_agribank, "Argibank", R.drawable.card_argibank));
        banks.add(new Bank(R.drawable.logo_bidv, "BIDV", R.drawable.card_bidv));
        banks.add(new Bank(R.drawable.logo_vietcombank, "Vietcombank", R.drawable.card_vietcombank));
        banks.add(new Bank(R.drawable.logo_mbbank, "MBBank", R.drawable.card_mbbank));
        banks.add(new Bank(R.drawable.logo_tpbank, "TBBank", R.drawable.card_tpbank));
        banks.add(new Bank(R.drawable.logo_techcombank, "Techcombank", R.drawable.card_techcombank));
    }
    private void loadData(){
        initData();
        adapter = new BankAdapter(PaymentDebitCard.this, R.layout.layout_bank, banks);
        binding.lvBank.setAdapter(adapter);
    }


    private void addEventClickBank() {
        binding.lvBank.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bank selectedbank = (Bank) adapter.getItem(position);
                Intent intent = new Intent(PaymentDebitCard.this, PaymentCardDetailedBank.class);

                intent.putExtra("bank", selectedbank);
                startActivity(intent);
            }
        });
    }


    private void addEventBack() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}