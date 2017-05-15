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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        DownloadTask task = new DownloadTask();
        ArrayList<EarthquakeData> earthquakes = new ArrayList<>();
        earthquakes = QueryUtils.extractEarthquakes();

        // Create a fake list of earthquake locations.
       /* ArrayList<EarthquakeData> earthquakes = new ArrayList<>();
        earthquakes.add(new EarthquakeData("4-4-1099", "4.3", "San Francisco"));
        earthquakes.add(new EarthquakeData("4-4-1099", "4.3", "London"));
        earthquakes.add(new EarthquakeData("4-4-1099", "4.3", "Tokyo"));
        earthquakes.add(new EarthquakeData("4-4-1099", "4.3", "Mexico City"));
        earthquakes.add(new EarthquakeData("4-4-1099", "4.3", "Moscow"));
        earthquakes.add(new EarthquakeData("4-4-1099", "4.3", "Rio de Janeiro"));
        earthquakes.add(new EarthquakeData("4-4-1099", "4.3", "Paris"));
*/
        //task.execute("http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-01-31&minmag=6&limit=10");
        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);


        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
        final ArrayList<EarthquakeData> finalEarthquakes = earthquakes;
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
