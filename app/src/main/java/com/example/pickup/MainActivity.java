package com.example.pickup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    String deviceID;
    String name;

    // COMMUNICATE W SERVER FOR USER ID

    //
    User user = new User(deviceID, name);
}
