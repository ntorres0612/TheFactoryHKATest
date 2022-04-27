package com.ntorres.thefactoryhkatest.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.ntorres.thefactoryhkatest.models.RifType;
import java.util.ArrayList;
import java.util.List;

public class RifTypeAdapter extends ArrayAdapter<RifType> {

        private Context context;
        private List<RifType> values = new ArrayList<>();

        public RifTypeAdapter(Context context)
        {
            super(context, 0);
            this.context = context;
            this.values = values;
        }

        public void setResults(List<RifType> results) {
            Log.e("Size" , "" + results.size());
            this.values = results;
            notifyDataSetChanged();
        }

        public int getCount()
        {
            return values.size();
        }

        public RifType getItem(int position)
        {
            return values.get(position);
        }

        public long getItemId(int position)
        {
            return position;
        }
        public List<RifType> getItems()
        {
            return this.values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            TextView label = new TextView(context);
            label.setTextColor(Color.BLACK);
            label.setText(values.get(position).getCode() + "-" );
            return label;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent)
        {
            TextView label = new TextView(context);
            label.setPadding(10,20,5,20);
            label.setTextColor(Color.BLACK);
            label.setText(values.get(position).getCode());

            return label;
        }
}
