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
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    ListView earthquakeListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        final String USGS_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";


        earthquakeListView = (ListView) findViewById(R.id.list);
        DownLoadtask task = new DownLoadtask();
        task.execute(USGS_URL);

        // Create a new {@link ArrayAdapter} of earthquakes



        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface

    }

    private class DownLoadtask extends AsyncTask<String, Void, ArrayList<EarthquakeData>> {

        @Override
        protected ArrayList<EarthquakeData> doInBackground(String... strings) {
            ArrayList<EarthquakeData> earthquakes;
            earthquakes = QueryUtils.getEarthquakeData(strings[0]);

            return earthquakes;
        }

        @Override
        protected void onPostExecute(ArrayList<EarthquakeData> earthquakeDatas) {
            updateUi(earthquakeDatas);

            super.onPostExecute(earthquakeDatas);
        }
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
