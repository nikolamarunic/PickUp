package com.example.pickup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

public class HostEventLockScreen extends ListActivity {
    final int RADIUS = 1000;
    int counter = 0;
    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_event_lock_screen);
        Button leaveButton = findViewById(R.id.leaveBtn);
        leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        int max = intent.getIntExtra("Max", 0);
        int min = intent.getIntExtra("Min", 0);
        String desc = intent.getStringExtra("Desc");

        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        setListAdapter(adapter);


        //CALL API FOR LAT AND LONG
        int lat = 0;
        int lng = 0;
        Event event = new Event(RADIUS, lat, lng, min, max, desc);
    }

    public void addItems(View v) {
        listItems.add("Clicked : "+counter++);
        adapter.notifyDataSetChanged();
    }

    public void addItems(String item) {
        listItems.add(item);
        adapter.notifyDataSetChanged();
    }

    public void subtractItems(String item) {
        listItems.remove(item);
        adapter.notifyDataSetChanged();
    }

}
