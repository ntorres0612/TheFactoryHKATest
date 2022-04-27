package com.ntorres.thefactoryhkatest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ntorres.thefactoryhkatest.view.CustomerActivity;
import com.ntorres.thefactoryhkatest.view.SearchInvoice;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCreate, btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreate = findViewById(R.id.btnCreate);
        btnSearch = findViewById(R.id.btnSearch);

        btnCreate.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent;
        switch (view.getId()) {
            case R.id.btnCreate:
                 intent = new Intent(this, CustomerActivity.class);
                startActivity(intent);
                break;
            case R.id.btnSearch:
                 intent = new Intent(this, SearchInvoice.class);
                startActivity(intent);
                break;
        }
    }
}