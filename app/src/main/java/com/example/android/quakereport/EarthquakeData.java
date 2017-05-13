package com.example.android.quakereport;

/**
 * Created by arshdeep chimni on 12-05-2017.
 */

public class EarthquakeData {
    public String date;
    public String magnitude;
    public String origin;

    public EarthquakeData(String date, String magnitude, String origin) {
        this.date = date;
        this.magnitude = magnitude;
        this.origin = origin;
    }

    public String getDate() {
        return date;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public String getOrigin() {
        return origin;
    }
}
