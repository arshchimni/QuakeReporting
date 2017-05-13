package com.example.android.quakereport;

/**
 * Created by arshdeep chimni on 12-05-2017.
 */

public class EarthquakeData {
    public String date;
    public double magnitude;
    public String origin;
    private long mTimeInMilliseconds;

    public EarthquakeData(String date, double magnitude, String origin, long mTimeInMilliseconds) {
        this.date = date;
        this.magnitude = magnitude;
        this.origin = origin;
        this.mTimeInMilliseconds = mTimeInMilliseconds;
    }

    public String getDate() {
        return date;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getOrigin() {
        return origin;
    }

    public long getmTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }
}
