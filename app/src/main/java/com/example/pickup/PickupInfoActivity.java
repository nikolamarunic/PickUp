package com.example.pickup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PickupInfoActivity extends AppCompatActivity {
    Button going;
    Button notGoing;
    Intent outgoingIntent;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent receivedIntent = getIntent();
        String desc = receivedIntent.getStringExtra("Description");
        int maxPeople = receivedIntent.getIntExtra("MaxPeople", 0);
        int minPeople = receivedIntent.getIntExtra("MinPeople", 0);
        int radius = receivedIntent.getIntExtra("radius", 0);
        double longitude = receivedIntent.getDoubleExtra("longitude", 0);
        double latitude = receivedIntent.getDoubleExtra("latitude", 0);
        id = receivedIntent.getStringExtra("id");

        setContentView(R.layout.activity_pickup_info);

        TextView max_amount = findViewById(R.id.maxPeople);
        TextView min_amount= findViewById(R.id.minPeople);
        TextView description = findViewById(R.id.description);

        going = findViewById(R.id.goingButton);
        notGoing = findViewById(R.id.cancelButton);
        addListeners();

        max_amount.setText(Integer.toString(maxPeople));
        min_amount.setText(Integer.toString(minPeople));
        description.setText(desc);

        outgoingIntent = new Intent(this, GuestLockScreenActivity.class);
        outgoingIntent.putExtra("Description", desc);
        outgoingIntent.putExtra("MinPeople", minPeople);
        outgoingIntent.putExtra("MaxPeople", maxPeople);
        outgoingIntent.putExtra("latitude", latitude);
        outgoingIntent.putExtra("longitude", longitude);
        outgoingIntent.putExtra("radius", radius);
        outgoingIntent.putExtra("id", id);
    }

    private void addListeners() {
        going.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(outgoingIntent);
            }
        });

        notGoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
