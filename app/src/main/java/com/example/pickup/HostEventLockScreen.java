package com.example.pickup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class HostEventLockScreen extends AppCompatActivity {
    final int RADIUS = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_event_lock_screen);
        Intent intent = getIntent();
        int max = intent.getIntExtra("Max", 0);
        int min = intent.getIntExtra("Min", 0);
        String desc = intent.getStringExtra("Desc");

        System.out.println(desc);


        //CALL API FOR LAT AND LONG
        int lat = 0;
        int lng = 0;
        Event event = new Event(RADIUS, lat, lng, min, max, desc);

    }
}
