package com.ntorres.thefactoryhkatest.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ntorres.thefactoryhkatest.R;
import com.ntorres.thefactoryhkatest.models.Invoice;
import com.ntorres.thefactoryhkatest.models.Product;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class SearchInvoiceProductAdapter extends RecyclerView.Adapter<SearchInvoiceProductAdapter.ProductViewHolder> {

    private  List<Invoice> items;
    private  Context context;

    public SearchInvoiceProductAdapter(List<Invoice> items , Context context  ) {
        this.context = context;
        this.items = items;
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product_search_invoice, viewGroup, false);
        return new ProductViewHolder(v , context );
    }
    @Override
    public void onBindViewHolder(ProductViewHolder viewHolder, int i) {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        format.setCurrency(Currency.getInstance("USD"));
        format.setMinimumFractionDigits(0);

         String total = format.format( getTotal(items.get(i).getProducts()) );

        viewHolder.customer.setText( String.valueOf(items.get(i).getCustomer().getFullName()));
        viewHolder.emitter.setText( items.get(i).getEmitter().getBusinessName());
        viewHolder.quantity.setText( String.valueOf( items.get(i).getProducts().size() ));
        viewHolder.total.setText( total );

    }
    public double getTotal(ArrayList<Product> products) {
        double total = 0;
        for (Product p : products) {
            total += p.getPrice() * p.getQuantity() ;
        }
        return total;
    }
    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        public TextView customer;
        public TextView emitter;
        public TextView quantity;
        public TextView total;
        Context context;
        AlertDialog.Builder alertbox;

        public ProductViewHolder(View v , Context context ) {
            super(v);

            this.context = context;

            customer = v.findViewById(R.id.customer);
            emitter = v.findViewById(R.id.emitter);
            quantity = v.findViewById(R.id.quantity);
            total = v.findViewById(R.id.total);

        }


    }
    public void setInvoices( List<Invoice> invoices ){
        this.items = invoices;
        this.notifyDataSetChanged();
    }
    public void clearList() {
        this.items.clear();
        this.notifyDataSetChanged();
    }

}
