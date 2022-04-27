package com.ntorres.thefactoryhkatest.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.ntorres.thefactoryhkatest.R;
import com.ntorres.thefactoryhkatest.adapters.ProductAdapter;
import com.ntorres.thefactoryhkatest.adapters.SearchInvoiceProductAdapter;
import com.ntorres.thefactoryhkatest.models.Invoice;
import com.ntorres.thefactoryhkatest.models.Product;
import com.ntorres.thefactoryhkatest.viewmodels.InvoiceViewModel;

import java.util.ArrayList;

public class SearchInvoice extends AppCompatActivity {


    LottieAnimationView airplane;
    RecyclerView listInvoices;
    SearchInvoiceProductAdapter searchInvoiceProductAdapter;
    ArrayList<Invoice> invoices;
    InvoiceViewModel invoiceViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_invoice);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Recibos guardados");

        invoices = new ArrayList<>();
        airplane = findViewById(R.id.airplane);
        listInvoices = findViewById(R.id.listInvoices);

//        getActionBar().setHomeButtonEnabled(true);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
//        setTitle("Activity 2");


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getApplicationContext() );
        listInvoices.setLayoutManager(linearLayoutManager);
        searchInvoiceProductAdapter = new SearchInvoiceProductAdapter(invoices , getApplicationContext());
        listInvoices.setAdapter(searchInvoiceProductAdapter);


        invoiceViewModel = ViewModelProviders.of(this).get(InvoiceViewModel.class);
        invoiceViewModel.init();
        invoiceViewModel.getListInvoiceLiveData().observe(this, invoiceResponse -> {

            if (invoiceResponse != null) {
                if( invoiceResponse.getStatus().compareToIgnoreCase("success") == 0 ) {

                    invoices = invoiceResponse.getInvoices();
                    searchInvoiceProductAdapter.setInvoices(invoices);

                    airplane.setVisibility(View.GONE);
                    listInvoices.setVisibility(View.VISIBLE);

                }
            }
        });
        invoiceViewModel.getDeleteInvoiceLiveData().observe(this, invoiceResponse -> {

            if (invoiceResponse != null) {
                if( invoiceResponse.getStatus().compareToIgnoreCase("success") == 0 ) {
                    searchInvoiceProductAdapter.clearList();
                }
            }
        });
        invoiceViewModel.listInvoice();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_invoice, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.delete:
                AlertDialog.Builder alert = new AlertDialog.Builder(SearchInvoice.this);
                alert.setTitle("Delete");
                alert.setMessage("Are you sure you want to delete?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        invoiceViewModel.deleteInvoices();
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.show();
                break;

            default:
                break;
        }

        return true;
    }


}