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
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

//due toosupport.v4 imports for loader manager and loader the app can not complie use the proper imports

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<EarthquakeData>> {

    final String USGS_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    ListView earthquakeListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);


        earthquakeListView = (ListView) findViewById(R.id.list);

        //create or get already available loader.
        //params==unique id,saved bundle data,context of present application
        getLoaderManager().initLoader(0, null, this);

        // Create a new {@link ArrayAdapter} of earthquakes



        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface

    }

    @Override
    public Loader<ArrayList<EarthquakeData>> onCreateLoader(int id, Bundle args) {
        return new LoaderEarthquake(getApplicationContext(), USGS_URL);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<EarthquakeData>> loader, ArrayList<EarthquakeData> data) {
        if (data != null && !data.isEmpty()) {
            updateUi(data);
        }


    }

    @Override
    public void onLoaderReset(Loader<ArrayList<EarthquakeData>> loader) {
        updateUi(new ArrayList<EarthquakeData>());

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
