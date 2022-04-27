package com.ntorres.thefactoryhkatest.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ntorres.thefactoryhkatest.R;
import com.ntorres.thefactoryhkatest.models.Customer;

public class CustomerActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnGoToEmitterForm;
    private EditText customer_document, customer_name, customer_lastname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_activity);


        ImageView back = findViewById(R.id.back);

        btnGoToEmitterForm = findViewById(R.id.btnGoToEmitterForm);
        customer_document = findViewById(R.id.emitter_document);
        customer_name = findViewById(R.id.customer_name);
        customer_lastname = findViewById(R.id.customer_lastname);

        Intent intent = getIntent();
        if( intent.hasExtra("customer")) {
            Customer customer = (Customer) intent.getSerializableExtra("customer");
            customer_document.setText(customer.getDocument_number());
            customer_name.setText(customer.getName());
            customer_lastname.setText(customer.getLastname());
        }



        back.setOnClickListener(view -> onBackPressed());
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);

        btnGoToEmitterForm.setOnClickListener(this);


    }
    public boolean validateFields() {
        boolean hasError = false;
        if( customer_document.getText().toString().trim().isEmpty() ) {
            customer_document.setError("Campo obligatorio");
            hasError = true;
        }
        if( customer_name.getText().toString().trim().isEmpty() ) {
            customer_name.setError("Campo obligatorio");
            hasError = true;
        }
        if( customer_lastname.getText().toString().trim().isEmpty() ) {
            customer_lastname.setError("Campo obligatorio");
            hasError = true;
        }
        return hasError;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnGoToEmitterForm:
                if(!validateFields()) {
                    Customer customer = new Customer(customer_document.getText().toString(), customer_name.getText().toString(), customer_lastname.getText().toString());
                    Intent intent = new Intent(this, EmitterActivity.class);
                    intent.putExtra("customer", customer);
                    startActivity(intent);
                }
                break;
        }
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }
//    @Override
//    public boolean onSupportNavigateUp() {
//        onBackPressed();
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//        if (id == R.id.action_create_invoice) {
//            Intent intent = new Intent(this, SearchInvoice.class);
//            startActivity(intent);
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}