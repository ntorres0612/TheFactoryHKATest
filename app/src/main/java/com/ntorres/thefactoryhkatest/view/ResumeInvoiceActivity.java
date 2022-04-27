package com.ntorres.thefactoryhkatest.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ntorres.thefactoryhkatest.MainActivity;
import com.ntorres.thefactoryhkatest.R;
import com.ntorres.thefactoryhkatest.adapters.ProductAdapter;
import com.ntorres.thefactoryhkatest.models.Customer;
import com.ntorres.thefactoryhkatest.models.Emitter;
import com.ntorres.thefactoryhkatest.models.InvoiceRequest;
import com.ntorres.thefactoryhkatest.models.Product;
import com.ntorres.thefactoryhkatest.viewmodels.InvoiceViewModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

public class ResumeInvoiceActivity extends AppCompatActivity implements View.OnClickListener {

    private Customer customer;
    private Emitter emitter;
    ProductAdapter adapter;
    ArrayList<Product> products;
    RecyclerView listProducts;
    TextView txtTotal, emitterName, emitterType, customerName;
    Button btnFinish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_invoice);

        AtomicReference<Intent> intent = new AtomicReference<>(getIntent());
        customer = (Customer) intent.get().getSerializableExtra("customer");
        emitter = (Emitter) intent.get().getSerializableExtra("emitter");
        products =  (ArrayList<Product>)getIntent().getSerializableExtra("products");

        listProducts = findViewById(R.id.listProducts);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getApplicationContext() );
        listProducts.setLayoutManager(linearLayoutManager);
        adapter = new ProductAdapter(products , getApplicationContext());
        listProducts.setAdapter(adapter);

        txtTotal = findViewById(R.id.txtTotal);
        emitterName = findViewById(R.id.emitterName);
        emitterType = findViewById(R.id.emitterType);
        customerName = findViewById(R.id.customerName);
        btnFinish = findViewById(R.id.btnFinish);


        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        format.setCurrency(Currency.getInstance("USD"));
        format.setMinimumFractionDigits(0);

        String total = format.format( getTotal() );


        txtTotal.setText( total );
        emitterName.setText(emitter.getBusinessName());
        emitterType.setText(emitter.getBusinessName());
        customerName.setText(customer.getName());

        btnFinish.setOnClickListener(this);


    }
    public double getTotal() {
        double total = 0;
        for (Product p : products) {
            total += p.getPrice() * p.getQuantity() ;
        }
        return total;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnFinish:
                finish();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}