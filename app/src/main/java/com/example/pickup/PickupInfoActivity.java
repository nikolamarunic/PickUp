package com.example.pickup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PickupInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String desc = intent.getStringExtra("Description");
        int maxPeople = intent.getIntExtra("MaxPeople", 0);
        int minPeople = intent.getIntExtra("MinPeople", 0);

        setContentView(R.layout.activity_pickup_info);

        TextView max_amount = findViewById(R.id.maxPeople);
        TextView min_amount= findViewById(R.id.minPeople);
        TextView description = findViewById(R.id.description);
//        System.out.println(max_amount);


        max_amount.setText(Integer.toString(maxPeople));
        min_amount.setText(Integer.toString(minPeople));
        description.setText(desc);



    }
}
