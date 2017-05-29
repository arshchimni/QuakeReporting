/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

//due toosupport.v4 imports for loader manager and loader the app can not complie use the proper imports

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<EarthquakeData>> {

    // final String USGS_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
    private static final String USGS_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query";
    ImageView emptyView;
    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    ListView earthquakeListView;
    ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        emptyView = (ImageView) findViewById(R.id.emptyView);
        earthquakeListView = (ListView) findViewById(R.id.list);
        spinner = (ProgressBar) findViewById(R.id.loading_spinner);
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            //create or get already available loader.
            //params==unique id,saved bundle data,context of present application
            getLoaderManager().initLoader(0, null, this);
        } else {
            spinner.setVisibility(View.INVISIBLE);
            emptyView.setImageResource(R.drawable.no_view);
            Toast.makeText(this, "NO INTERNET", Toast.LENGTH_SHORT).show();
        }





    }

    @Override
    public Loader<ArrayList<EarthquakeData>> onCreateLoader(int id, Bundle args) {

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String minMagnitude = sharedPrefs.getString(
                getString(R.string.settings_min_magnitude_key),
                getString(R.string.settings_min_magnitude_default));

        String orderBy = sharedPrefs.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default)
        );

        Uri baseUri = Uri.parse(USGS_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("format", "geojson");
        uriBuilder.appendQueryParameter("limit", "10");
        uriBuilder.appendQueryParameter("minmag", minMagnitude);
        uriBuilder.appendQueryParameter("orderby", orderBy);


        return new LoaderEarthquake(getApplicationContext(), uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<EarthquakeData>> loader, ArrayList<EarthquakeData> data) {
        spinner.setVisibility(View.INVISIBLE);
        if (data != null && !data.isEmpty()) {
            updateUi(data);
        } else {
            //set the image resource for he empty view here otherwise it will be visible over the listview items always
            emptyView.setImageResource(R.drawable.no_view);
            earthquakeListView.setEmptyView(emptyView);
        }

    }

    @Override
    public void onLoaderReset(Loader<ArrayList<EarthquakeData>> loader) {
        updateUi(new ArrayList<EarthquakeData>());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //iflate the menue xml
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateUi(ArrayList<EarthquakeData> earthquakeDatas) {
        EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakeDatas);

        earthquakeListView.setAdapter(adapter);
        final ArrayList<EarthquakeData> finalEarthquakes = earthquakeDatas;
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), WebInfo.class);
                EarthquakeData data = finalEarthquakes.get(i);
                intent.putExtra("URL", data.getUrl());
                startActivity(intent);

            }
        });

    }
}
