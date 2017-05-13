package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by arshdeep chimni on 12-05-2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<EarthquakeData> {
    public EarthquakeAdapter(@NonNull Context context, ArrayList<EarthquakeData> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);

        }

        EarthquakeData currentData = getItem(position);
        TextView magTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        TextView originTextView = (TextView) listItemView.findViewById(R.id.origin);
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);

        magTextView.setText(currentData.getMagnitude());
        originTextView.setText(currentData.getOrigin());
        dateTextView.setText(currentData.getDate());

        return listItemView;
    }
}
