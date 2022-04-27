package com.ntorres.thefactoryhkatest.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ntorres.thefactoryhkatest.R;
import com.ntorres.thefactoryhkatest.adapters.RifTypeAdapter;
import com.ntorres.thefactoryhkatest.models.Customer;
import com.ntorres.thefactoryhkatest.models.Emitter;
import com.ntorres.thefactoryhkatest.models.RifType;
import com.ntorres.thefactoryhkatest.models.RifTypeResponse;
import com.ntorres.thefactoryhkatest.viewmodels.RifTypeViewModel;

public class EmitterActivity extends AppCompatActivity implements View.OnClickListener {

    private RifTypeViewModel rifTypeViewModel;
    private RifTypeAdapter rifTypeAdapter;
    private Button btnGoToInvoiceForm, btnGoBackCustomer;
    private Spinner rifType;
    private EditText emitter_document;
    private EditText business_name;
    private RifType rifTypeSelected;
    Intent intent;
    Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emitter);

        intent = getIntent();
        customer = (Customer) intent.getSerializableExtra("customer");

        emitter_document = findViewById(R.id.emitter_document);
        business_name = findViewById(R.id.business_name);
        rifType = findViewById(R.id.rifType);
        rifTypeAdapter = new RifTypeAdapter( getApplicationContext());

        rifTypeViewModel = ViewModelProviders.of(this).get(RifTypeViewModel.class);
        rifTypeViewModel.init();

        rifTypeViewModel.getRifTypeLiveData().observe(this, rifTypeResponse -> {
            if (rifTypeResponse != null) {
                rifTypeAdapter.setResults(rifTypeResponse.getRifTypes());
                rifTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                rifType.setAdapter(rifTypeAdapter);

                rifTypeSelected = rifTypeResponse.getRifTypes().get(0);
            }
        });
        rifTypeViewModel.listRifType();

        btnGoBackCustomer = findViewById(R.id.btnGoBackCustomer);
        btnGoToInvoiceForm = findViewById(R.id.btnGoToInvoiceForm);
        btnGoToInvoiceForm.setOnClickListener(this);
        btnGoBackCustomer.setOnClickListener(this);


        rifType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                rifTypeSelected = (RifType) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public boolean validateFields() {
        boolean hasError = false;
        if( business_name.getText().toString().trim().isEmpty() ) {
            business_name.setError("Campo obligatorio");
            hasError = true;
        }
        if( emitter_document.getText().toString().trim().isEmpty() ) {
            emitter_document.setError("Campo obligatorio");
            hasError = true;
        }
        return hasError;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnGoToInvoiceForm:
                if( !validateFields()) {
                    Emitter emitter = new Emitter(emitter_document.getText().toString(), rifTypeSelected.getId(), business_name.getText().toString());
                    intent = new Intent(this, InvoiceActivity.class);
                    intent.putExtra("emitter", emitter);
                    intent.putExtra("customer", customer);
                    startActivity(intent);
                }
                break;

            case R.id.btnGoBackCustomer:
                Emitter emitter = new Emitter(emitter_document.getText().toString(), rifTypeSelected.getId(), business_name.getText().toString());
                intent = new Intent(this, CustomerActivity.class);
                intent.putExtra("emitter", emitter);
                intent.putExtra("customer", customer);
                startActivity(intent);
                break;
        }
    }
}