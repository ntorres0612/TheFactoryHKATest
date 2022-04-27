package com.ntorres.thefactoryhkatest.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
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
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

public class InvoiceActivity extends AppCompatActivity implements View.OnClickListener {

    Customer customer;
    Emitter emitter;
    LinearLayout addToCart, increase, decrease;
    RecyclerView listProducts;
    List<Product> products;
    ProductAdapter adapter;

    EditText inputCode, inputDescription, inputPrice, inputQuantity;
    TextView total;
    Button btnTotalize;
    private InvoiceViewModel invoiceViewModel;

    ConstraintLayout l1, l2, l3;
    LottieAnimationView airPlane;


    NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        AtomicReference<Intent> intent = new AtomicReference<>(getIntent());

        format.setCurrency(Currency.getInstance("USD"));
        format.setMinimumFractionDigits(0);


        l1 = findViewById(R.id.l1);
        l2 = findViewById(R.id.l2);
        l3 = findViewById(R.id.l3);
        airPlane = findViewById(R.id.airplane);


        products = new ArrayList<>();
        addToCart = findViewById(R.id.addToCart);
        customer = (Customer) intent.get().getSerializableExtra("customer");
        emitter = (Emitter) intent.get().getSerializableExtra("emitter");

        addToCart.setOnClickListener(this);

        listProducts = findViewById(R.id.listProducts);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getApplicationContext() );
        listProducts.setLayoutManager(linearLayoutManager);
        adapter = new ProductAdapter(products , getApplicationContext());
        listProducts.setAdapter(adapter);


        increase = findViewById(R.id.increase);
        decrease = findViewById(R.id.decrease);
        total = findViewById(R.id.total);

        btnTotalize = findViewById(R.id.btnTotalize);
        inputCode = findViewById(R.id.inputCode);
        inputDescription = findViewById(R.id.inputDescription);
        inputPrice = findViewById(R.id.inputPrice);
        inputQuantity = findViewById(R.id.inputQuantity);

        btnTotalize.setOnClickListener(this);
        increase.setOnClickListener(this);
        decrease.setOnClickListener(this);


        invoiceViewModel = ViewModelProviders.of(this).get(InvoiceViewModel.class);
        invoiceViewModel.init();
        invoiceViewModel.getInvoiceLiveData().observe(this, invoiceResponse -> {
            if (invoiceResponse != null) {

                if( invoiceResponse.getStatus().compareToIgnoreCase("success") == 0 ) {
                    ArrayList<Product> products = (ArrayList<Product>) adapter.getProducts();
                    intent.set(new Intent(this, ResumeInvoiceActivity.class));
                    intent.get().putExtra("customer", customer);
                    intent.get().putExtra("emitter", emitter);
                    intent.get().putExtra("products", products);
                    startActivity(intent.get());

                    l1.setVisibility(View.VISIBLE);
                    l2.setVisibility(View.VISIBLE);
                    l3.setVisibility(View.VISIBLE);
                    airPlane.setVisibility(View.GONE);


                } else {
                    l1.setVisibility(View.VISIBLE);
                    l2.setVisibility(View.VISIBLE);
                    l3.setVisibility(View.VISIBLE);
                    airPlane.setVisibility(View.GONE);
                }
            }
        });
    }
    @Override
    public void onClick(View view) {
        int quantity = 0;
        switch (view.getId()) {

            case R.id.btnTotalize:

                l1.setVisibility(View.GONE);
                l2.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);
                airPlane.setVisibility(View.VISIBLE);

                ArrayList<Product> products = (ArrayList<Product>) adapter.getProducts();
                InvoiceRequest invoiceRequest = new InvoiceRequest(customer, emitter, products);
                invoiceViewModel.createInvoice(invoiceRequest);
                break;
            case R.id.addToCart:
                if( !validateFields()) {
                    Product p = new Product(inputCode.getText().toString(), inputDescription.getText().toString(), Integer.parseInt(inputPrice.getText().toString()), Integer.parseInt(inputQuantity.getText().toString()));
                    adapter.addProduct(p);
                    clearFields();

                    total.setText( format.format( getTotal() ) );
                }
                break;

            case R.id.increase:
                if( !inputQuantity.getText().toString().trim().isEmpty() )
                    quantity = Integer.parseInt(inputQuantity.getText().toString().trim());
                inputQuantity.setText( String.valueOf(quantity + 1 ));
                break;

            case R.id.decrease:
                if( !inputQuantity.getText().toString().trim().isEmpty() )
                    quantity = Integer.parseInt(inputQuantity.getText().toString().trim());
                inputQuantity.setText( String.valueOf(quantity - 1 ));
                break;
        }
    }
    public void clearFields() {
        inputCode.setText("");
        inputDescription.setText("");
        inputPrice.setText("");
        inputQuantity.setText("");
    }
    public boolean validateFields() {
        boolean hasError = false;
        if( inputCode.getText().toString().trim().isEmpty() ) {
            inputCode.setError("Campo obligatorio");
            hasError = true;
        }
        if( inputDescription.getText().toString().trim().isEmpty() ) {
            inputDescription.setError("Campo obligatorio");
            hasError = true;
        }
        if( inputPrice.getText().toString().trim().isEmpty() ) {
            inputPrice.setError("Campo obligatorio");
            hasError = true;
        }
        if( inputQuantity.getText().toString().trim().isEmpty() ) {
            inputQuantity.setError("Campo obligatorio");
            hasError = true;
        }
        if( Integer.parseInt(inputQuantity.getText().toString()) < 0 ) {
            Toast.makeText(this, "Cantidad no permitida", Toast.LENGTH_SHORT).show();
        }

        return hasError;
    }
    public double getTotal() {
        double total = 0;
        for (Product p : products) {
            total += p.getPrice() * p.getQuantity() ;
        }
        return total;
    }
}