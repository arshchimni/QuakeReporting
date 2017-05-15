package com.example.android.quakereport;

/**
 * Created by arshdeep chimni on 12-05-2017.
 */

public class EarthquakeData {
    public String date;
    public double magnitude;
    public String origin;


    private String url;
    private long mTimeInMilliseconds;


    private String primaryLocation;
    private String secondaryLocation;

    public EarthquakeData(String date, double magnitude, String origin, long mTimeInMilliseconds, String url) {
        this.date = date;
        this.magnitude = magnitude;
        this.origin = origin;
        this.mTimeInMilliseconds = mTimeInMilliseconds;
        this.url = url;
        setTheLoacation(origin);

    }

    private void setTheLoacation(String origin) {
        String[] locations;
        if (origin.contains("of")) {
            locations = origin.split("of");
            secondaryLocation = locations[0] + " of";
            primaryLocation = locations[1];

        } else {
            secondaryLocation = "Near the";
            primaryLocation = origin;
        }
    }


    public String getPrimaryLocation() {
        return primaryLocation;
    }

    public String getSecondaryLocation() {
        return secondaryLocation;
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

    public String getUrl() {
        return url;
    }
}
