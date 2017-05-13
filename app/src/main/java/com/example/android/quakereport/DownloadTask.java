package com.example.android.quakereport;

import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by arshdeep chimni on 13-05-2017.
 */

public class DownloadTask extends AsyncTask<String, Void, ArrayList<EarthquakeData>> {


    @Override
    protected ArrayList<EarthquakeData> doInBackground(String... strings) {
       /* URL url;
        HttpURLConnection urlConnection=null;
        String result="";

        try {
            url=new URL(strings[0]);
            urlConnection=(HttpURLConnection)url.openConnection();
            InputStream inputStream= urlConnection.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            int data=inputStreamReader.read();
            while (data!=-1){
                char c =(char) data;
                result +=c;
                data=inputStreamReader.read();
            }

            JSONObject fullData=new JSONObject(result);
            JSONArray features=fullData.getJSONArray("features");
            for (int i=0;i<features.length();i++){
                System.out.println(features.getJSONObject(i));
            }



        } catch (Exception e) {
            e.printStackTrace();
        }*/

        return null;
    }


}

