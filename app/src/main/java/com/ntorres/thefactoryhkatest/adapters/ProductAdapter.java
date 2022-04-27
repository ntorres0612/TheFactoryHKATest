package com.ntorres.thefactoryhkatest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ntorres.thefactoryhkatest.R;
import com.ntorres.thefactoryhkatest.models.Product;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final List<Product> items;
    private final Context context;

    public ProductAdapter(List<Product> items , Context context  ) {
        this.context = context;
        this.items = items;
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product, viewGroup, false);
        return new ProductViewHolder(v , context );
    }
    @Override
    public void onBindViewHolder(ProductViewHolder viewHolder, int i) {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        format.setCurrency(Currency.getInstance("USD"));
        format.setMinimumFractionDigits(0);

        String total = format.format( items.get(i).getPrice() * items.get(i).getQuantity()  );

        viewHolder.code.setText( String.valueOf(items.get(i).getCode()));
        viewHolder.quantity_x_price.setText( items.get(i).getQuantity() + "x" + items.get(i).getPrice());
        viewHolder.description.setText( items.get(i).getDescription() );
        viewHolder.subtotal.setText( total );

    }
    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        public TextView code;
        public TextView quantity_x_price;
        public TextView description;
        public TextView subtotal;
        Context context;

        public ProductViewHolder(View v , Context context ) {
            super(v);

            this.context = context;

            code = v.findViewById(R.id.code);
            quantity_x_price = v.findViewById(R.id.quantity_x_price);
            description = v.findViewById(R.id.description);
            subtotal = v.findViewById(R.id.subtotal);
        }
    }
    public void addProduct( Product product ){
        items.add(product);
        this.notifyDataSetChanged();
    }

    public List<Product> getProducts() {
        return this.items;
    }
}
