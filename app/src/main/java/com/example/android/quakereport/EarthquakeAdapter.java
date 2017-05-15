package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

        Date dateObject = new Date(currentData.getmTimeInMilliseconds());
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        String formattedTime = timeFormat.format(dateObject);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.


        String formattedMagnitude = formatMagnitude(currentData.getMagnitude());

        TextView magTextView = (TextView) listItemView.findViewById(R.id.magnitude);

        TextView primaryLocTextView = (TextView) listItemView.findViewById(R.id.primaryLoc);

        TextView secondaryLocTextView = (TextView) listItemView.findViewById(R.id.secondaryLoc);

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);

        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);

        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentData.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        magTextView.setText(formattedMagnitude);

        timeTextView.setText(formattedTime);

        primaryLocTextView.setText(currentData.getPrimaryLocation());

        secondaryLocTextView.setText(currentData.getSecondaryLocation());

        dateTextView.setText(currentData.getDate());
        return listItemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int mag = (int) Math.floor(magnitude);
        switch (mag) {
            case 1:
                int magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude1);
                return magnitude1Color;

            case 2:
                int magnitude2Color = ContextCompat.getColor(getContext(), R.color.magnitude2);
                return magnitude2Color;


            case 3:
                int magnitude3Color = ContextCompat.getColor(getContext(), R.color.magnitude3);
                return magnitude3Color;


            case 4:
                int magnitude4Color = ContextCompat.getColor(getContext(), R.color.magnitude4);
                return magnitude4Color;


            case 5:
                int magnitude5Color = ContextCompat.getColor(getContext(), R.color.magnitude5);
                return magnitude5Color;


            case 6:
                int magnitude6Color = ContextCompat.getColor(getContext(), R.color.magnitude6);
                return magnitude6Color;


            case 7:
                int magnitude7Color = ContextCompat.getColor(getContext(), R.color.magnitude7);
                return magnitude7Color;


            case 8:
                int magnitude8Color = ContextCompat.getColor(getContext(), R.color.magnitude8);
                return magnitude8Color;


            case 9:
                int magnitude9Color = ContextCompat.getColor(getContext(), R.color.magnitude9);
                return magnitude9Color;


            case 10:
                int magnitude10Color = ContextCompat.getColor(getContext(), R.color.magnitude10plus);
                return magnitude10Color;

        }
        return 0;

    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat dec = new DecimalFormat("0.0");
        return dec.format(magnitude);
    }
}
