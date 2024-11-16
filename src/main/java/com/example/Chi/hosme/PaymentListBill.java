package com.example.Chi.hosme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.Chi.adapters.InvoiceAdapter;
import com.example.Chi.models.Invoice;
import com.example.finalproject_hosme.R;
import com.example.finalproject_hosme.databinding.ActivityPaymentListBillBinding;

import java.util.ArrayList;

public class PaymentListBill extends AppCompatActivity {
    ActivityPaymentListBillBinding binding;

    public static final String DB_NAME = "database_db.sqlite";
    public static final String DB_FOLDER = "/databases";
    public static SQLiteDatabase db = null;
    public static final String TBL_NAME = "InvoiceData";
    public static final String TBL_NAME_ANOTHER = "BookingData";
    public static final String COL_INVOICEID = "InvoiceID";
    public static final String COL_PATIENTID = "PatienID";
    public static final String COL_STATUS = "Status";
    public static final String COL_ADDRESS = "AddressHos";



    ArrayList<Invoice> invoices = new ArrayList<>();
    InvoiceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentListBillBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEventBack();
        showListBill();
        enterInvoiceCode();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showListBill();
    }

    private void enterInvoiceCode() {
        binding.imvSearchBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = binding.edtInvoiceID.getText().toString();
                db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
                Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME + " INNER JOIN " + TBL_NAME_ANOTHER + " ON " + TBL_NAME + "." + COL_PATIENTID + " = " + TBL_NAME_ANOTHER + "." + COL_PATIENTID + " WHERE " + TBL_NAME + "." + COL_INVOICEID + " LIKE ?", new String[]{"%" + id + "%"});

                if (cursor.moveToFirst()) {
                    int invoiceID = cursor.getInt(0);
                    String patientID = cursor.getString(2);
                    String patientName = cursor.getString(3);
                    String departmentName = cursor.getString(5);
                    String createDateTime = cursor.getString(4);
                    double priceService = cursor.getDouble(6);
                    String status = cursor.getString(7);
                    SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                    String addressHos = sharedPreferences.getString("co_so", "");



                    Intent intent = new Intent(PaymentListBill.this, PaymentListBillDetail.class);

                    intent.putExtra("patientID", patientID);
                    intent.putExtra("patientName", patientName);
                    intent.putExtra("departmentName", departmentName);
                    intent.putExtra("addressHos", addressHos);
                    intent.putExtra("createDateTime", createDateTime);
                    intent.putExtra("priceService", priceService);

                    startActivity(intent);

                }else {
                    Toast.makeText(PaymentListBill.this, "Invoice not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showListBill() {
        invoices = new ArrayList<>();

        db = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);

        String PatientID = sharedPreferences.getString("patientID", "");

        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME + " INNER JOIN " + TBL_NAME_ANOTHER + " ON " + TBL_NAME + "." + COL_PATIENTID + " = " + TBL_NAME_ANOTHER + "." + COL_PATIENTID + " WHERE " + TBL_NAME + "." + COL_PATIENTID + " LIKE ? AND " + TBL_NAME + "." + COL_STATUS + " = ?",
                new String[]{PatientID, "Chưa thanh toán"});

        while (cursor.moveToNext()) {
            int invoiceID = cursor.getInt(0);
            String patientID = cursor.getString(2);
            String patientName = cursor.getString(3);
            String departmentName = cursor.getString(5);
            String createDateTime = cursor.getString(4);
            double priceService = cursor.getDouble(6);
            String status = cursor.getString(7);
            String addressHos = sharedPreferences.getString("co_so", "");

            Invoice invoice = new Invoice(invoiceID, patientID, patientName, departmentName, addressHos, createDateTime, priceService, status);

            // Kiểm tra xem invoice đã tồn tại trong danh sách chưa
            boolean invoiceExists = false;
            for (Invoice existingInvoice : invoices) {
                if (existingInvoice.getInvoiceID() == invoiceID) {
                    invoiceExists = true;
                    break;
                }
            }

            // Nếu invoice chưa tồn tại, thêm vào danh sách
            if (!invoiceExists) {
                invoices.add(invoice);
            }
        }

        cursor.close();

        adapter = new InvoiceAdapter(this, R.layout.listbill_layout, invoices);
        binding.lvBill.setAdapter(adapter);

        addEventClickBill();
    }


    private void addEventClickBill() {
        binding.lvBill.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Invoice selectedInvoice = (Invoice) adapter.getItem(position);

                Intent intent = new Intent(PaymentListBill.this, PaymentListBillDetail.class);

                intent.putExtra("patientID", selectedInvoice.getPatientID());
                intent.putExtra("patientName", selectedInvoice.getPatientName());
                intent.putExtra("departmentName", selectedInvoice.getDepartmentName());
                intent.putExtra("addressHos", selectedInvoice.getAddressHos());
                intent.putExtra("createDateTime", selectedInvoice.getCreateDateTime());
                intent.putExtra("priceService", selectedInvoice.getPriceService());

                startActivity(intent);
            }
        });
    }


    private void addEventBack() {
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Back home screen
                finish();
            }
        });
    }
}