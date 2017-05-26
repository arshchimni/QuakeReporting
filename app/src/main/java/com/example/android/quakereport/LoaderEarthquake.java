package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by arshdeep chimni on 26-05-2017.
 */

public class LoaderEarthquake extends AsyncTaskLoader<ArrayList<EarthquakeData>> {

    //return type that is needed from this background thread

    private String mUrl;

    public LoaderEarthquake(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<EarthquakeData> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        ArrayList<EarthquakeData> earthquakes;
        earthquakes = QueryUtils.getEarthquakeData(mUrl);

        return earthquakes;

    }


}
