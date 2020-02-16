package com.example.pickup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import io.radar.sdk.Radar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Radar.initialize("prj_live_pk_598126e8bc2d50f1da77cf8261a9255c9c7d63fc");
        Radar.startTracking();
    }

    @Override
    protected void onDestroy(Bundle savedInstanceState) {
    	Radar.stopTracking();
    }
    String deviceID;
    String name;

    // COMMUNICATE W SERVER FOR USER ID

    //
    User user = new User(deviceID, name);
}
