package com.example.android.quakereport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by arshdeep chimni on 26-05-2017.
 */

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //just pass the intent to start main activity
        Intent intent = new Intent(this, EarthquakeActivity.class);
        startActivity(intent);
        finish();
    }
}
